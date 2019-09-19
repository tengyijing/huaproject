package cn.huacao.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.huacao.common.jedis.JedisClient;
import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.IDUtils;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.mapper.TbItemDescMapper;
import cn.huacao.mapper.TbItemMapper;
import cn.huacao.pojo.TbItem;
import cn.huacao.pojo.TbItemDesc;
import cn.huacao.pojo.TbItemDescExample;
import cn.huacao.pojo.TbItemDescExample.Criteria;
import cn.huacao.pojo.TbItemExample;
import cn.huacao.service.ItemService;

/**
 * 商品管理Service
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	
	
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	//activeMQ消息发送模板
	@Autowired
	private JmsTemplate jsmTemplate;
	
	//activeMQ消息发送的方式
	@Resource(name="itemAddTopic")
	private Destination destination;
	
	@Autowired
	private JedisClient jedisClient;
	
	//商品数据在缓存中的前缀
	@Value("${ITEM_INFO}")
	private String INTE_INFO;
	
	//商品数据在缓存中的有效时长,默认半天
	@Value("${TIME_EXPIRE}")
	private Integer TIME_EXPIRE;
	//根据ID查询商品
	@Override
	public TbItem fundItemById(long itemId) {
		//首先在缓存中查询
		try {
			String json = jedisClient.get(INTE_INFO+":"+itemId+":BASE");
			if(StringUtils.isNoneBlank(json)) {
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//缓存中没有查询到时从数据库中查询
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		try {
			//将查询结果放入缓存
			jedisClient.set(INTE_INFO+":"+itemId+":BASE", JsonUtils.objectToJson(item));
			//设置过期时间，提高利用率
			jedisClient.expire(INTE_INFO+":"+itemId+":BASE", TIME_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	
	//查询商品并分页
	@Override
	public EasyUiDataGridResult fundItemList(int page, int rows) {
		//设置分页信息
		PageHelper.startPage(page, rows);
		//查询数据
		TbItemExample example = new TbItemExample(); 
		List<TbItem> list = itemMapper.selectByExample(example);
		//获取分页信息
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		//创建返回结果
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		result.setTotal(pageInfo.getTotal());
		//返回结果
		return result;
	}
	
	//添加商品
	@Override
	public HuaCaoResult addItem(TbItem item, String desc) {
		//生成商品id:使用时间戳+随机数
		long id = IDUtils.genItemId();
		item.setId(id);
		//补全TbItem属性
		//商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte) 1);
		//设置商品创建时间
		Date data = new Date();
		item.setCreated(data);
		//设置商品修改时间
		item.setUpdated(data);
		//向商品表插入数据
		itemMapper.insert(item);
		//创建一个TbItemDesc对象
		TbItemDesc tbItemDesc = new TbItemDesc(); 
		//补全TbItemDesc属性
		tbItemDesc.setItemDesc(desc);
		//设置描述商品id
		tbItemDesc.setItemId(id);
		//设置描述创建时间
		tbItemDesc.setCreated(data);
		//设置描述修改时间
		tbItemDesc.setUpdated(data);
		//向描述表中插入数据
		itemDescMapper.insert(tbItemDesc);
		//向activeMQ发送消息 （同步索引库）
		jsmTemplate.send(destination, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage(id+"");
				return message;
			}
		});
		//返回HuaCaoResult.ok 返回一个HuaCaoResult对象
		return HuaCaoResult.ok();
	}

	@Override
	//修改商品信息
	public HuaCaoResult updateItem(TbItem item , String desc) {
		//补全商品属性
		//修改时间
		Date date = new Date();
		item.setUpdated(date);
		//根据商品ID获取描述对象
		TbItemDescExample example = new TbItemDescExample();
		//添加查询条件
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(item.getId());
	    List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
	    //如果没有描述就新添加，有的话就修改
	    if(list.isEmpty()) {
	    	//创建一个描述对象
	    	TbItemDesc itemDesc = new TbItemDesc();
	    	//添加属性
	    	itemDesc.setCreated(date);
	    	itemDesc.setItemDesc(desc);
	    	itemDesc.setItemId(item.getId());
	    	itemDesc.setUpdated(date);
	    	//添加到数据库
	    	itemDescMapper.insert(itemDesc);
	    }else {
	    	//获取描述对象
	    	TbItemDesc itemDesc = list.get(0);
	    	//修改属性
	    	itemDesc.setItemDesc(desc);
	    	itemDesc.setUpdated(date);
	    	//修改数据
	    	itemDescMapper.updateByPrimaryKeyWithBLOBs(itemDesc);
	    }
	    //执行商品修改方法
	  	itemMapper.updateByPrimaryKeySelective(item);
	  	
	  	//向activeMQ发送消息 （同步索引库）
  		jsmTemplate.send(destination, new MessageCreator() {
  			@Override
  			public Message createMessage(Session session) throws JMSException {
  				TextMessage message = session.createTextMessage(item.getId()+"");
  				return message;
  			}
  		});
		return HuaCaoResult.ok();
	}
	
	//修改商品状态 1-上架 2-下架 3-删除
	@Override
	public HuaCaoResult updateStatus(List<Long> itemIds , byte status) {
		//循环遍历ID
		for(long itemId : itemIds) {
			//修改商品状态
			itemMapper.updateItemStatus(itemId, status);
		}
		return HuaCaoResult.ok();
	}
}

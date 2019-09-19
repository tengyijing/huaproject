package cn.huacao.content.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.huacao.common.jedis.JedisClient;
import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.content.service.ContentService;
import cn.huacao.mapper.TbContentMapper;
import cn.huacao.pojo.TbContent;
import cn.huacao.pojo.TbContentExample;
import cn.huacao.pojo.TbContentExample.Criteria;

/**
 * 广告内容管理实现类
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	
	//缓存中首页滑动的hash名称
	@Value("${INDEX_CONTENT}")
	private String INDEX_CONTENT;
	
	@Autowired
	private TbContentMapper contentMapper;
	
	//缓存客户端
	@Autowired
	private JedisClient jedisClient;
	//根据分类查询广告内容
	@Override
	public EasyUiDataGridResult fundContentBycateGoryId(int page, int rows,Long cateGoryId) {
		//初始化分页信息
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andCategoryIdEqualTo(cateGoryId);
		//执行
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example );
		//获取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		//创建返回值
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		//设置页面展示的数
		result.setRows(list);
		//设置数据总数
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//增加广告内容
	@Override
	public HuaCaoResult addContent(TbContent content) {
		//补全属性
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		//插入数据
		contentMapper.insert(content);
		//同步缓存
		jedisClient.hdel(INDEX_CONTENT, content.getCategoryId().toString());
		return HuaCaoResult.ok();
	}

	//修改广告内容
	@Override
	public HuaCaoResult updateContent(TbContent content) {
		//修改时间
		content.setUpdated(new Date());
		//修改数据
		contentMapper.updateByPrimaryKeyWithBLOBs(content);
		//同步缓存
		jedisClient.hdel(INDEX_CONTENT, content.getCategoryId().toString());
		return HuaCaoResult.ok();
	}
	
	//删除广告内容
	@Override
	public HuaCaoResult deleteContentByid(List<Long> idList) {
		for(Long id: idList) {
			contentMapper.deleteByPrimaryKey(id);
			//同步缓存
			jedisClient.hdel(INDEX_CONTENT, id.toString());
		}
		return HuaCaoResult.ok();
	}
	//查询广告展示到前台页面
	@Override
	public List<TbContent> fundContent(Long categoryId) {
		//先从redis缓存中查找数据
		try {
			//从redis缓存中查询数据
			String json = jedisClient.hget(INDEX_CONTENT, categoryId+"");
			//将数据转换成list集合
			if(StringUtils.isNoneBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//没有查到，再去数据库中查		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		//查到数据后将数据添加到redis缓存中
		jedisClient.hset(INDEX_CONTENT, categoryId+"", JsonUtils.objectToJson(list));
		return list;
	}	
}
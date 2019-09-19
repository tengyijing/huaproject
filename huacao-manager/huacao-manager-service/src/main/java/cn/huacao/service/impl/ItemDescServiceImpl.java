package cn.huacao.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.huacao.common.jedis.JedisClient;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.mapper.TbItemDescMapper;
import cn.huacao.pojo.TbItemDesc;
import cn.huacao.service.ItemDescService;
/*
 * 获取商品描述
 */
@Service
public class ItemDescServiceImpl implements ItemDescService{
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private JedisClient jedisClient;
	
	//商品描述数据在缓存中的前缀
	@Value("${ITEM_INFO}")
	private String INTE_INFO;
	
	//商品描述数据在缓存中的有效时长,默认半天
	@Value("${TIME_EXPIRE}")
	private Integer TIME_EXPIRE;
	//根据商品id查询商品描述(后台)
	@Override
	public HuaCaoResult fundItemDesc(long itemId) {
		
		TbItemDesc tbItemDesc = this.fundItemDescById(itemId);
		//判断是否有描述
		if(tbItemDesc == null) {
			//如果不存在
			return HuaCaoResult.ok();
		}
		return HuaCaoResult.ok(tbItemDesc);
	}

	//前台获取商品描述页
	@Override
	public TbItemDesc fundItemDescById(Long itemId) {
		//先从缓存中获取数据
		try {
			String json = jedisClient.get(INTE_INFO+":"+itemId+":DESC");
			if(StringUtils.isNoneBlank(json)) {
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
				return itemDesc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//缓存中没有时从数据库中获取数据
		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
		try {
			//将查询结果放入缓存
			jedisClient.set(INTE_INFO+":"+itemId+":DESC", JsonUtils.objectToJson(itemDesc));
			//设置过期时间，提高利用率
			jedisClient.expire(INTE_INFO+":"+itemId+":DESC", TIME_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemDesc;
	}
}

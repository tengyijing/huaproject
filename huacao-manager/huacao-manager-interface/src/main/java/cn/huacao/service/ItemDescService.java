package cn.huacao.service;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbItemDesc;

public interface ItemDescService {
	//后台后台获取商品描述
	public HuaCaoResult fundItemDesc(long itemId);
	//前台获取商品描述
	public TbItemDesc fundItemDescById(Long itemId);
}

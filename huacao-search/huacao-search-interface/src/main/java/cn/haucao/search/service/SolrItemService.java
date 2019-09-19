package cn.haucao.search.service;

import cn.huacao.common.pojo.HuaCaoResult;

public interface SolrItemService {
	//将所有的商品导入索引库
	HuaCaoResult importAllItems() throws Exception;
}

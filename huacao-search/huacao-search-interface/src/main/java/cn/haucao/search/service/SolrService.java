package cn.haucao.search.service;

import cn.huacao.common.pojo.SearchResult;

/**
 * 处理页面搜索请求
 * @author Administrator
 *
 */
public interface SolrService {
	//搜索商品
	SearchResult search(String queryString , Integer page , Integer rows);
}

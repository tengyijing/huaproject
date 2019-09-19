package cn.huacao.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.haucao.search.service.SolrService;
import cn.huacao.common.pojo.SearchResult;
import cn.huacao.search.dao.SearchDao;


/**
 * solr搜索业务层
 * @author Administrator
 *
 */
@Service
public class SolrServiceImpl implements SolrService {

	@Autowired
	private SearchDao searchDao;
	@Override
	public SearchResult search(String queryString, Integer page, Integer rows) {
		//根据查询条件拼装查询对象
		//创建一个SolrQuery对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置分页条件
		if(page<1) page=1;
		query.setStart((page-1)*rows);//起始记录
		if(rows<10) rows=10;
		query.setRows(rows);
		//设置默认域
		query.set("df","item_title");
		//设置高亮显示
		query.setHighlight(true);
		//设置高亮域
		query.addHighlightField("item_title");
		//设置高亮前缀
		query.setHighlightSimplePre("<font color='red'>");
		//设置高亮后缀
		query.setHighlightSimplePost("</font>");
		//调用Dao查询
		SearchResult result = searchDao.search(query);
		//计算查询结果的总页数
		Long recordCount = result.getSum();
		Long pageCount = recordCount / rows;
		if(recordCount%rows>0)
			pageCount++;
		result.setTotalPages(pageCount);
		//返回结果
		return result;
	}
}

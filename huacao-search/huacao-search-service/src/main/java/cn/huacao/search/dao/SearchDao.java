package cn.huacao.search.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.huacao.common.pojo.SearchItem;
import cn.huacao.common.pojo.SearchResult;

@Repository
public class SearchDao {
	
	@Autowired
	private SolrServer server;
	
	//查询
	public SearchResult search(SolrQuery query){
		try {
			//执行查询
			QueryResponse response = server.query(query);
			//获取查询结果
			SolrDocumentList results = response.getResults();
			//创建返回对象
			SearchResult result = new SearchResult();
			//设置数据总数
			result.setSum(results.getNumFound());
			//创建商品集合
			List<SearchItem> items = new ArrayList<>();
			//遍历数据集合
			for (SolrDocument doc : results) {
				//创建SearchItem对象
				SearchItem searchItem = new SearchItem();
				//设置属性
				searchItem.setId((String) doc.get("id"));
				//获取高亮名称
				String item_title =(String) doc.get("item_title");
				List<String> list = response.getHighlighting().get(doc.get("id")).get("item_title");
				if(list!=null && list.size()>0) {
					item_title = list.get(0);
				}
				searchItem.setTitle(item_title);
				searchItem.setSell_point((String) doc.get("item_sell_point"));
				searchItem.setPrice((long) doc.get("item_price"));
				searchItem.setImage((String) doc.get("item_image"));
				//添加到集合中
				items.add(searchItem);
			}
			//设置返回结果属性
			result.setItemList(items);
			//返回结果
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}

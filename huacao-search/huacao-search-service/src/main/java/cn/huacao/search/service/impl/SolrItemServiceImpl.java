package cn.huacao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.haucao.search.service.SolrItemService;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.pojo.SearchItem;
import cn.huacao.search.mapper.SearchItemMapper;

/**
 * Solr数据同步业务层
 * @author Administrator
 *
 */
@Service
public class SolrItemServiceImpl implements SolrItemService {

	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrserver;
	
	//将所有的商品导入索引库中
	@Override
	public HuaCaoResult importAllItems() throws Exception  {
			//查询出所有商品
			List<SearchItem> itemList = searchItemMapper.getItemList();
			//循环商品列表
			for(SearchItem item : itemList) {
				//创建solr文档
				SolrInputDocument document = new  SolrInputDocument();
				//添加属性
				document.addField("id", item.getId());
				document.addField("item_title",item.getTitle() );
				document.addField("item_sell_point", item.getSell_point());
				document.addField("item_price", item.getPrice());
				document.addField("item_image", item.getImage());
				document.addField("item_catagory_name", item.getCategory_name());
				document.addField("item_desc", item.getItem_desc());
				//添加到索引库
				solrserver.add(document);
			}
			//提交
			solrserver.commit();
			return HuaCaoResult.ok();
	}
}
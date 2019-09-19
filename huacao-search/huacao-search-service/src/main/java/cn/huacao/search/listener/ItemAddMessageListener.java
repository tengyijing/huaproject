package cn.huacao.search.listener;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.huacao.common.pojo.SearchItem;
import cn.huacao.search.mapper.SearchItemMapper;

/**
 * 监听商品添加时间，同步索引库
 * @author Administrator
 *
 */
public class ItemAddMessageListener implements MessageListener {
	
	@Autowired
	private SearchItemMapper searchItemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void onMessage(Message message) {
		try {
			//1获取消息中的商品id
			TextMessage textMessage = (TextMessage) message;
			String strId = textMessage.getText();
			long itemId = Long.parseLong(strId);
			//等待事务提交(保证商品已经添加到数据库)
			Thread.sleep(3000);
			//2根据商品id查询id
			SearchItem item = searchItemMapper.getItemByid(itemId);
			//3创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			//4向文档对象中添加域
			document.addField("id", item.getId());
			document.addField("item_title",item.getTitle() );
			document.addField("item_sell_point", item.getSell_point());
			document.addField("item_price", item.getPrice());
			document.addField("item_image", item.getImage());
			document.addField("item_catagory_name", item.getCategory_name());
			document.addField("item_desc", item.getItem_desc());
			//5把文档对象写入索引库
			solrServer.add(document);
			//6提交
			solrServer.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}



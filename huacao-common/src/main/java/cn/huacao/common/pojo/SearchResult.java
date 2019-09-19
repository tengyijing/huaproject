package cn.huacao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * solr搜索返回结果对象
 * @author Administrator
 *
 */
public class SearchResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//数据总页数
	private Long totalPages;
	//数据总数
	private Long sum;
	//数据列表
	private List<SearchItem> itemList;
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	
	public Long getSum() {
		return sum;
	}
	public void setSum(Long sum) {
		this.sum = sum;
	}
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
}

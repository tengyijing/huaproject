package cn.huacao.common.pojo;

import java.io.Serializable;

/**
 * 搜索服务返回的pojo对象
 * @author Administrator
 *
 */
public class SearchItem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; //商品id
	private String title; //商品名称
	private String sell_point;//商品买点
	private long price;//价格
	private String image;//图片
	private String category_name;//分类名称
	private String item_desc;//商品描述
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSell_point() {
		return sell_point;
	}
	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getItem_desc() {
		return item_desc;
	}
	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}
}

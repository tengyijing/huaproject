package cn.huacao.order.pojo;

import java.io.Serializable;
import java.util.List;

import cn.huacao.pojo.TbOrder;
import cn.huacao.pojo.TbOrderItem;
import cn.huacao.pojo.TbOrderShipping;

/**
 * 订单包装类
 * @author Administrator
 *
 */
public class OrderInfo extends TbOrder implements Serializable{
	
	//订单项
	private List<TbOrderItem> orderItems;
	
	private TbOrderShipping orderShipping;

	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}

	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}

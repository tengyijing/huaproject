package cn.huacao.order.service;

import java.util.List;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.order.pojo.OrderInfo;
import cn.huacao.pojo.TbOrder;

/**
 *订单处理接口
 * @author Administrator
 *
 */
public interface OrderService {
	//创建订单
	public HuaCaoResult  createOrder(OrderInfo orderInfo);

	public List<TbOrder> getUserOrderList(Long userId);

}

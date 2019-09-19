package cn.huacao.order.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.huacao.common.jedis.JedisClient;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.mapper.TbOrderItemMapper;
import cn.huacao.mapper.TbOrderMapper;
import cn.huacao.mapper.TbOrderShippingMapper;
import cn.huacao.order.pojo.OrderInfo;
import cn.huacao.order.service.OrderService;
import cn.huacao.pojo.TbOrder;
import cn.huacao.pojo.TbOrderExample;
import cn.huacao.pojo.TbOrderExample.Criteria;
import cn.huacao.pojo.TbOrderItem;
/**
 * 订单请求实现类
 * @author Administrator
 *
 */
import cn.huacao.pojo.TbOrderShipping;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	//订单信息
	private TbOrderMapper orderMapper;
	@Autowired
	//订单商品信息
	private TbOrderItemMapper orderItemMapper;
	//订单地址信息
	@Autowired
	private TbOrderShippingMapper orderShippingMapper; 
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("${ORDER_ID_GEN_KEY}")
	private String ORDER_ID_GEN_KEY;
	
	@Value("${ORDER_ID_BEGEN_VALUE}")
	private String ORDER_ID_BEGEN_VALUE;
	
	@Value("${ORDER_ITEM_ID_GEN_KEY}")
	private String ORDER_ITEM_ID_GEN_KEY;
	
	//生成订单
	public HuaCaoResult  createOrder(OrderInfo orderInfo) {
		//生成订单号，使用redis的incr自增长
		if(!jedisClient.exists(ORDER_ID_GEN_KEY)) {
			//设置初始值
			jedisClient.set(ORDER_ID_GEN_KEY, ORDER_ID_BEGEN_VALUE);
		}
		String orderId = jedisClient.incr(ORDER_ID_GEN_KEY).toString();
		//向订单表中插入数据，需要补全pojo的属性
		orderInfo.setOrderId(orderId);
		//免邮费
		orderInfo.setPostFee("0");
		//设置状态 1、未付款 2、已付款 3、未发货 4、已发货 5、交易成功 6、交易关闭
		orderInfo.setStatus(1);
		Date date = new Date();
		orderInfo.setCreateTime(date);
		orderInfo.setUpdateTime(date);
		//向订单表插入数据
		orderMapper.insert(orderInfo);
		//向订订单明细表插入数据
		List<TbOrderItem> orderItems = orderInfo.getOrderItems();
		for (TbOrderItem tbOrderItem : orderItems) {
			//获取id
			String oid = jedisClient.incr(ORDER_ITEM_ID_GEN_KEY).toString();
			tbOrderItem.setId(oid);
			//设置所属订单id
			tbOrderItem.setOrderId(orderId);
			//插入
			orderItemMapper.insert(tbOrderItem);
		}
		//向订单物流表中插入数据
		TbOrderShipping orderShipping = orderInfo.getOrderShipping();
		orderShipping.setOrderId(orderId);
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		orderShippingMapper.insert(orderShipping);
		//移除购物车商品
		
		//返回订单号
		return HuaCaoResult.ok(orderId);
	}

	//获取订单列表
	@Override
	public List<TbOrder>  getUserOrderList(Long userId) {
		TbOrderExample example = new TbOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<TbOrder> orderlist = orderMapper.selectByExample(example);
		return orderlist;
	}
}

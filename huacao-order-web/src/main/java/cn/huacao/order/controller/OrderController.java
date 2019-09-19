package cn.huacao.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.CookieUtils;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.order.pojo.OrderInfo;
import cn.huacao.order.service.OrderService;
import cn.huacao.pojo.TbItem;
import cn.huacao.pojo.TbOrder;
import cn.huacao.pojo.TbOrderItem;

/**
 * 订单请求页面
 * @author Administrator
 *
 */

@Controller
public class OrderController {

	@Value("${CART_KEY}")
	private String CART_KEY;

	@Autowired
	private OrderService orderService;
	
	//展示订单确认页面
	@RequestMapping("/order/order-cart")
	public String showOrderCart(HttpServletRequest request) {
		//从cookie中取购物车商品信息
		List<TbItem> cartList = getCartItemList(request);
		//保存到request域
		request.setAttribute("cartList", cartList);
		//返回逻辑视图
		return "order-cart";
	}
	
	//获取购物车列表
	private List<TbItem> getCartItemList(HttpServletRequest request){
		//从cookie中获取购物车列表
		String json = CookieUtils.getCookieValue(request, CART_KEY , true);
		//判断是否为空
		if(StringUtils.isBlank(json)) {
			//为空
			return new ArrayList<>();
		}
		List<TbItem> itemList = JsonUtils.jsonToList(json, TbItem.class);
		return itemList;
	}
	
	//订单生成请求
	@RequestMapping(value="/order/create" , method = RequestMethod.POST)
	public String createOrder(OrderInfo orderInfo , Model model , HttpServletRequest request) {
		//生成订单
		HuaCaoResult result = orderService.createOrder(orderInfo);
		//订单号
		model.addAttribute("orderId", result.getData());
		//消费金额
		model.addAttribute("payment", orderInfo.getPayment());
		//预计到达时间
		DateTime dateTime = new DateTime();
		dateTime = dateTime.plus(3);
		model.addAttribute("date",dateTime.toString("yyyy-MM-dd"));
		//返回逻辑视图
		return "success";
	}
	
	//获取个人订单列表
	@RequestMapping("/order/getUserOrderList")
	public String getUserOrderList(@Param("userId")Long userId  , Model model) {
		List<TbOrder> orderList = orderService.getUserOrderList(userId);
		model.addAttribute("orderList", orderList);
		return "order-list";
	}
	
	//修改订单状态
	public String updateOrderStatus(String orderId , Integer status) {
		
		return "";
	}
}

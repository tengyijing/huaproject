package cn.huacao.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.CookieUtils;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.pojo.TbItem;
import cn.huacao.service.ItemService;


/**
 * 购物车模块
 * @author Administrator
 *
 */
@Controller
public class CartController {
	
	
	@Autowired
	private ItemService itemService;
	
	@Value("${CART_KEY}")
	private String CART_KEY;
	
	@Value("${CART_EXPIER}")
	private Integer CART_EXPIER;
	
	
	//商品加入购物车请求
	@RequestMapping("/cart/add/{itemId}")
	public String addItemCart(@PathVariable Long itemId ,
		@RequestParam(defaultValue="1") Integer num , HttpServletRequest request
		,HttpServletResponse response) {
		//获取购物车商品列表
		List<TbItem> cartList = getCartItemList(request);
		//判断商品是否存在购物车中
		boolean flag = false;
		for (TbItem tbItem : cartList) {
			if(tbItem.getId().longValue() == itemId.longValue()) {
				//存在 商品数量加一
				tbItem.setNum(tbItem.getNum()+num);
				flag = true;
				break;
			}
		}
		//不存在 , 在购物车中新加一种商品
		if(!flag) {
			//获取商品信息
			TbItem item = itemService.fundItemById(itemId);
			//设置商品数量
			item.setNum(num);
			//取第一张图片
			String imgs = item.getImage();
			if(StringUtils.isNoneBlank(imgs)) {
				//取第一张图片
				item.setImage(imgs.split(",")[0]);
			}
			//把商品加入列表
			cartList.add(item);
		}
		//把购物车列表写入cookie
		CookieUtils.setCookie(request, response, CART_KEY, JsonUtils.objectToJson(cartList), CART_EXPIER, true);
		//返回添加成功页面
		return "cartSuccess";
	}
	
	
	//展示购物车列表
	@RequestMapping("/cart/cart")
	public String showCartList(HttpServletRequest request) {
		//从cookie中或取购物车列表
		List<TbItem> cartList = getCartItemList(request);
		//保存到reques域中
		request.setAttribute("cartList", cartList);
		request.setAttribute("itemNum", cartList.size());
		return "cart";
	}
	
	//更新购物车商品数量
	@RequestMapping("/cart/update/num/{itemId}/{num}")
	@ResponseBody
	public HuaCaoResult updateItemNum(@PathVariable Long itemId 
			,@PathVariable Integer num, HttpServletRequest request
			,HttpServletResponse response) {
		//从cookie中获取购物车列表
		List<TbItem> cartItemList = getCartItemList(request);
		//找到对应的商品
		for (TbItem tbItem : cartItemList) {
			if(tbItem.getId().longValue()==itemId.longValue()) {
				//更新商品数量
				tbItem.setNum(num);
				break;
			}
		}
		//从新存入cookie中
		CookieUtils.setCookie(request, response, CART_KEY, 
				JsonUtils.objectToJson(cartItemList), CART_EXPIER, true);
		request.setAttribute("itemNum", cartItemList.size());
		//返回成功结果
		return HuaCaoResult.ok();
	}
	
	//删除购物车商品
	@RequestMapping("/cart/delete/{itemId}")
	public String deleteCartItem(@PathVariable Long itemId , HttpServletRequest request
			,HttpServletResponse response , HttpSession session) {
		//从cookie中或取购物车列表
		List<TbItem> cartItemList = getCartItemList(request);
		//找到商品对象
		TbItem item = null;
		for (TbItem tbItem : cartItemList) {
			if(tbItem.getId().longValue()==itemId.longValue()) {
				item = tbItem;
				break;
			}
		}
		//删除商品
		cartItemList.remove(item);
		//从新存入cookie中
		CookieUtils.setCookie(request, response, CART_KEY, 
				JsonUtils.objectToJson(cartItemList), CART_EXPIER, true);
		session.setAttribute("itemNum", cartItemList.size());
		//重定向到页面
		return "redirect:/cart/cart.html";
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
}




package cn.huacao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huacao.item.pojo.Item;
import cn.huacao.pojo.TbItem;
import cn.huacao.pojo.TbItemDesc;
import cn.huacao.service.ItemDescService;
import cn.huacao.service.ItemService;

/**
 * 商品详情控制器
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemDescService itemDescService;
	//查询商品详情
	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId , Model model) {
		//查询商品详情
		TbItem tbItem = itemService.fundItemById(itemId);
		//处理商品详情
		Item item = new Item(tbItem);
		//查询商品描述
		TbItemDesc itemDesc = itemDescService.fundItemDescById(itemId);
		//添加到模型中
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
}

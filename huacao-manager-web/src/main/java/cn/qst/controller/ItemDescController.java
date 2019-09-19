package cn.qst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.service.ItemDescService;


/**
 * 处理商品描述请求
 */
@Controller
public class ItemDescController {
	
	@Autowired
	private ItemDescService itemDsecService;
	
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public HuaCaoResult fundItemDsec(@PathVariable Long itemId) {
		//获取结果
		HuaCaoResult result = itemDsecService.fundItemDesc(itemId);
		return result;
	}
}

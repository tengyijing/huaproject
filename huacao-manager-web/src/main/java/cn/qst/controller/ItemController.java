package cn.qst.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品管理Controller
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbItem;
import cn.huacao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private  ItemService itemService;
	
	//根据商品id查询商品
	@RequestMapping("/fundTbItemById/{itemId}")
	@ResponseBody
	public TbItem fundTbItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.fundItemById(itemId);
		return tbItem;
	}
	
	//查询多商品并分页
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUiDataGridResult fundTbItemList(int page, int rows) {
		EasyUiDataGridResult result = itemService.fundItemList(page, rows);
		return result;
	}
	
	//添加商品
	@RequestMapping("/item/save")
	@ResponseBody
	public HuaCaoResult  saveItem(TbItem item , String desc) {
		//添加商品
		HuaCaoResult HuaCaoResult = itemService.addItem(item, desc);
		//设置返回
		return HuaCaoResult;
	}
	
	//修改商品
	@RequestMapping("/item/update")
	@ResponseBody
	public HuaCaoResult updateItem(TbItem item , String desc) {
		//执行修改
		HuaCaoResult result = itemService.updateItem(item,desc);
		return result;
	}
	
	//删除商品
	@RequestMapping("/item/delete")
	@ResponseBody
	public HuaCaoResult deleteItem(String ids) {
		//转换id
		List<Long> itemIds = stringToIds(ids);
		//调用业务层
		HuaCaoResult result = itemService.updateStatus(itemIds,(byte) 3);
		return result;
	}
	
	//下架商品
	@RequestMapping("/item/instock")
	@ResponseBody
	public HuaCaoResult instockItem(String ids) {
		List<Long> itemIds = stringToIds(ids);
		//调用业务层
		HuaCaoResult result = itemService.updateStatus(itemIds,(byte) 2);
		return result;
	}
	
	//上架商品
	@RequestMapping("/item/reshelf")
	@ResponseBody
	public HuaCaoResult reshelf(String ids) {
		List<Long> itemIds = stringToIds(ids);
		//调用业务层
		HuaCaoResult result = itemService.updateStatus(itemIds,(byte) 1);
		return result;
	}
	
	//切割itemId 转换类型
	public List<Long> stringToIds(String ids){
		//分割id
		String[] split = ids.trim().split(",");
		//创建idList
		List<Long> itemIds = new ArrayList<>();
		//转换id格式
		for(String id : split) {
			itemIds.add(Long.parseLong(id));
		}
		return itemIds;
	}
}

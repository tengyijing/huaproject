package cn.qst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.EasyUiTreeNode;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbItemCat;
import cn.huacao.service.ItemCatService;


/**
 * 商品分类控制器
 * @author Administrator
 *
 */
@Controller
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	
	@RequestMapping("/item/cat/list")
	@ResponseBody
	//指定属性名映射，设置默认值
	public List<EasyUiTreeNode> getItemCatList(@RequestParam(name="id",defaultValue="0") 
												long parentId){
		//获取分类数据
		List<EasyUiTreeNode> catList = itemCatService.getItemCatList(parentId);
		//返回
		return catList;
	}
	
	//添加内容分类
	@RequestMapping("/item/cat/create")
	@ResponseBody
	public HuaCaoResult addItemCat(Long parentId , String name) {
		HuaCaoResult result = itemCatService.addItemCat(parentId, name);
		return result;
	}
	
	//修改分类名
	@RequestMapping("/item/cat/update")
	@ResponseBody
	public HuaCaoResult updateItemCatName(TbItemCat itemCat) {
		HuaCaoResult result = itemCatService.updateItemCatName(itemCat);
		return result;
	}
	
	//删除分类
	@RequestMapping("/item/cat/delete")
	@ResponseBody
	public HuaCaoResult deleteItemCat(Long id , Long parentId) {
		//删除分类
		itemCatService.deleteItemCat(id);
		//判断该类的父类是否还有子节点 没有的话把自身改为子目录
		HuaCaoResult result = itemCatService.updateItemCatIsParent(parentId);
		return result;
	}
}

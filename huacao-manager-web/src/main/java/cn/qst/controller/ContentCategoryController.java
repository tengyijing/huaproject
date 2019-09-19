package cn.qst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.EasyUiTreeNode;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.content.service.ContentCategoryService;
import cn.huacao.pojo.TbContentCategory;


/**
 * 内容分类控制层
 * @author Administrator
 *
 */
@Controller
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	//根据parentId查询内容分类
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUiTreeNode> fundContentCategory(@RequestParam(name="id",defaultValue="0")Long parentId){
		List<EasyUiTreeNode> list = contentCategoryService.fundContentCategory(parentId);
		return list;
	}
	
	//添加内容分类
	@RequestMapping("/content/category/create")
	@ResponseBody
	public HuaCaoResult addContentCategory(Long parentId , String name) {
		HuaCaoResult result = contentCategoryService.addContentCategory(parentId, name);
		return result;
	}
	
	//修改分类名
	@RequestMapping("/content/category/update")
	@ResponseBody
	public HuaCaoResult updateContentCategoryName(TbContentCategory contentCategory) {
		HuaCaoResult result = contentCategoryService.updateContentCategoryName(contentCategory);
		return result;
	}
	
	//删除分类
	@RequestMapping("/content/category/delete")
	@ResponseBody
	public HuaCaoResult deleteContentCategory(Long id , Long parentId) {  
		//删除分类
		contentCategoryService.deleteContentCategory(id);
		//判断该类的父类是否还有子节点 没有的话把自身改为子目录
		HuaCaoResult result = contentCategoryService.updateContentCategoryIsParent(parentId);
		return result;
	}
}

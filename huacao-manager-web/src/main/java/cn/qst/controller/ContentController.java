package cn.qst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.content.service.ContentService;
import cn.huacao.pojo.TbContent;

/**
 * 广告内容控制层
 * @author Administrator
 *
 */
@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	//根据分类ID查询广告内容
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUiDataGridResult fundContentByCategoryId(int page, int rows ,Long categoryId) {
		EasyUiDataGridResult result =contentService.fundContentBycateGoryId(page,rows,categoryId);
		return result;
	}
	
	//插入广告内容
	@RequestMapping("/content/save")
	@ResponseBody
	public HuaCaoResult addContent(TbContent content) {
		HuaCaoResult result = contentService.addContent(content);
		return result;
	}
	
	//修改广告内容
	@RequestMapping("/content/edit")
	@ResponseBody
	public HuaCaoResult updateContent(TbContent content) {
		HuaCaoResult result = contentService.updateContent(content);
		return result;
	}
	
	//删除广告内容
	@RequestMapping("/content/delete")
	@ResponseBody
	public HuaCaoResult deleteContent(String ids) {
		String[] split = ids.split(",");
		//装换id类型
		List<Long> idList = new ArrayList<>();
		for(String id : split) {
			idList.add(Long.valueOf(id));
		}
		HuaCaoResult result = contentService.deleteContentByid(idList);
		return result;
	}	
}

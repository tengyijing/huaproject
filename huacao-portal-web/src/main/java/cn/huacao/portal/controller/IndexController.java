package cn.huacao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.huacao.common.utils.JsonUtils;
import cn.huacao.content.service.ContentService;
import cn.huacao.pojo.TbContent;
import cn.huacao.portal.pojo.AD1Node;

/**
 * 网站首页请求
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	
	//滑动模块id
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/index")
	public String indexJsp(Model model) {
		//根据分类id查询出广告
		List<TbContent> contentList = contentService.fundContent(AD1_CATEGORY_ID);
		//封装成广告节点集合
		List<AD1Node> nodeList = new ArrayList<>();
		for(TbContent content : contentList) {
			//创建节点
			AD1Node node = new AD1Node();
			node.setHeight(AD1_HEIGHT);
			node.setHeightB(AD1_HEIGHT_B);
			node.setAlt(content.getTitle());
			node.setHref(content.getUrl());
			node.setSrc(content.getPic());
			node.setSrcB(content.getPic2());
			node.setWidth(AD1_WIDTH);
			node.setWidthB(AD1_WIDTH_B);
			//添加节点
			nodeList.add(node);
		}
		//将数据转换成Json对象
		String ad1Json = JsonUtils.objectToJson(nodeList);
		//添加返回的内容
		model.addAttribute("ad1", ad1Json);
		//返回的视图
		return "index";
	}
}

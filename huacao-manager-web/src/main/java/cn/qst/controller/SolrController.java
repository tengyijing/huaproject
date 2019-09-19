package cn.qst.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.haucao.search.service.SolrItemService;
import cn.huacao.common.pojo.HuaCaoResult;


/**
 * solr服务控制层
 * @author Administrator
 *
 */
@Controller
public class SolrController {
	@Autowired
	private SolrItemService solrservice;
	
	//将商品信息导入索引库
	@RequestMapping("/index/importall")
	@ResponseBody
	public HuaCaoResult importItems() {
		HuaCaoResult result;
		try {
			result = solrservice.importAllItems();
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return HuaCaoResult.build(500, "导入数据失败~~~~");
		}
	}
}

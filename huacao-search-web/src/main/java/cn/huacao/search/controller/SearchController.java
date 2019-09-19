package cn.huacao.search.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.haucao.search.service.SolrService;
import cn.huacao.common.pojo.SearchItem;
import cn.huacao.common.pojo.SearchResult;

/**
 * 搜索服务控制
 * @author Administrator
 *
 */
@Controller
public class SearchController {
	
	@Autowired
	private SolrService solrservice;
	
	//注入分页数量
	@Value("${SEARCH_RESULT_ROWS}")
	private Integer SEARCH_RESULT_ROWS;
	
	@RequestMapping("/search")
	public String seach(@RequestParam("q") String queryString ,
			@RequestParam(defaultValue="1") Integer page , Model model) throws Exception {
		//解决get请求乱码
		queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
		//查询数据
		SearchResult result = solrservice.search(queryString, page, SEARCH_RESULT_ROWS);
		model.addAttribute("query", queryString);
		model.addAttribute("totalPages",result.getTotalPages());//总页数
		//每个商品取第一张图片
		List<SearchItem> itemList = result.getItemList();
		for (SearchItem searchItem : itemList) {
			if(StringUtils.isNoneBlank(searchItem.getImage())) {
				searchItem.setImage(searchItem.getImage().split(",")[0]);
			}
		}
		model.addAttribute("itemList",itemList);//每页数据
		model.addAttribute("sum",result.getSum());//总数量
		model.addAttribute("page",page);//当前页
		return "search";
	}
}

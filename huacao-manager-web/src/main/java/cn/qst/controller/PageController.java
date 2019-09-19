package cn.qst.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 后台页面跳转控制器
 * @author Administrator
 *
 */
@Controller
public class PageController {
	
	
	//请求首页
	@RequestMapping("/")   
	public String IndexJsp() {
		return "index";
	}
	
	//其他页面
	@RequestMapping("/{page}")
	public String pageJsp(@PathVariable String page) {
		return page;
	}
}

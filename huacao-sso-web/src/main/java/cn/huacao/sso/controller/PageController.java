package cn.huacao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 页面跳转控制器
 * @author Administrator
 *
 */
@Controller
public class PageController {
	
	//请求注册页面
	@RequestMapping("/page/register")   
	public String pageRegister() {
		return "register";
	}
	
	//请求登录页面
	@RequestMapping("/page/login")   
	public String pageLogin(String url , Model model) {
		model.addAttribute("redirect", url);
		return "login";
	}
}

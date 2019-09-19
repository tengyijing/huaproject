package cn.huacao.sso.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.CookieUtils;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.pojo.TbUser;
import cn.huacao.sms.PhoneCode;
import cn.huacao.sso.service.UserService;

/**
 * 用户请求控制层
 * @author Administrator
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	//注册数据校验请求数据
	@RequestMapping("/user/check")
	@ResponseBody
	public HuaCaoResult checkUserData(String param , int type) {
		HuaCaoResult result = userService.checkData(param, type);
		return result;
	}
	
	//用户注册请求
	@RequestMapping(value="/user/register" , method=RequestMethod.POST)
	@ResponseBody
	public HuaCaoResult register(TbUser user , HttpSession session ,@Param("phoneCode") String phoneCode) {
		HuaCaoResult result =null;
		//验证输入验证码是否正确
		String code = (String) session.getAttribute("phoneCode");
		if(!code.equals(phoneCode)) {
			result = HuaCaoResult.build(0, "验证码错误!!");
			return result;
		}
		//进行用户注册
		result = userService.register(user);
		return result;
	}
	
	//用户登录请求
	@RequestMapping(value="/user/login" , method=RequestMethod.POST)
	@ResponseBody
	public HuaCaoResult login(String username , String password
			,HttpServletRequest request ,HttpServletResponse  response) {
		HuaCaoResult result = userService.login(username, password);
		//把token保存到Cookie中
		if(result.getStatus()==200)
			CookieUtils.setCookie(request, response,TOKEN_KEY,result.getData().toString());
		return result;
	}
	
	//查询用户是否登录请求
	@RequestMapping(value="/user/token/{token}",method=RequestMethod.GET
			//指定响应数据的conten-type
			,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getUserByToken(@PathVariable String token , String callback ) {
		HuaCaoResult result = userService.getUserByToken(token);
		  //判断是否是jsonp请求
		if(StringUtils.isNotBlank(callback)) {
			return callback+"("+JsonUtils.objectToJson(result)+")";
		}
		return JsonUtils.objectToJson(result);
	}
	//用户安全退出请求
	@RequestMapping(value="/user/logout/{token}",method=RequestMethod.GET)
	public String logOut(@PathVariable String token) {
		HuaCaoResult result = userService.userLogOut(token);
		return "login";
	}
	
	//发送短信请求
	@RequestMapping("/user/sendPhoneCode")
	@ResponseBody
	public String sendPhoneCode(@Param("phone") String phone ,HttpSession session) {
		//发送短信获取验证码
		String code = PhoneCode.getPhonemsg(phone);
		//将验证码存入session
		session.setAttribute("phoneCode", code);
		return "ok";
	}
}

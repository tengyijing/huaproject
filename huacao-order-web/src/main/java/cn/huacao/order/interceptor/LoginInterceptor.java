package cn.huacao.order.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.CookieUtils;
import cn.huacao.sso.service.UserService;
/**
 * 判断用户的是否登录的拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;
	
	@Value("${TOKEN_KEY}")
	private String TOKEN_KEY;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		// TODO Auto-generated method stub
		//handler执之后，modelandview返回之前
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		//执行handler之前执行此方法 返回true：放行
		//1从cookie中取token
		String token = CookieUtils.getCookieValue(request, TOKEN_KEY );
		//2如果没有取到token，跳转到sso登录页面，需要把当前的请求URL传递给sso，sso登录成功调回请求页面
		if(StringUtils.isBlank(token)) {
			//取当前请求的URL
			String url = request.getRequestURL().toString();
			//跳转到登录页面
			response.sendRedirect("http://localhost:8088/page/login?url="+url);
			//拦截
			return false;
		}
		//3.取到token，调用sso系统的服务判断用户是否登录
		HuaCaoResult result = userService.getUserByToken(token);
		//4.如果没有取到 跳转到sso登录页面，需要把当前的请求URL传递给sso，sso登录成功调回请求页面
		if(result.getStatus()!=200) {
			String url = request.getRequestURL().toString();
			//跳转到登录页面
			response.sendRedirect("http://localhost:8088/page/login?url="+url);
			//拦截
			return false;
		}
		//5.取到用户信息放行
		//把用户信息放入request中
		request.setAttribute("user", result.getData());
		return true;
	}
}

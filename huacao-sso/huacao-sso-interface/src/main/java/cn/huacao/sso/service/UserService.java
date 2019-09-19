package cn.huacao.sso.service;

import java.util.List;

import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbUser;

/**
 * 用户请求业务层接口
 * @author Administrator
 *
 */
public interface UserService {
	
	//校验请求数据
	public HuaCaoResult checkData(String param , int type);
	//用户注册请求
	public HuaCaoResult register(TbUser user);
	//用户登录
	public HuaCaoResult login(String username , String password);
	//判断用户是否登录
	public HuaCaoResult getUserByToken(String token);
	//用户安全退出
	public HuaCaoResult userLogOut(String token);
	//用户list
	public EasyUiDataGridResult fundUserList(int page, int rows);
	//修改用户状态
	public HuaCaoResult updateStatus(List<Integer> itemIds, int status);
}

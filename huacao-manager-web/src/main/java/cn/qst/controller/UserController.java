package cn.qst.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.sso.service.UserService;

/**
 * 管理后台用户控制层
 * @author Administrator
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	//查询多用户并分页
	@RequestMapping("/user/list")
	@ResponseBody
	public EasyUiDataGridResult fundTbItemList(int page, int rows) {
		EasyUiDataGridResult result = userService.fundUserList(page, rows);
		return result;
	}
	
	//冻结用户
	@RequestMapping("/user/instock")
	@ResponseBody
	public HuaCaoResult instockItem(String ids) {
		List<Integer> itemIds = stringToIds(ids);
		//调用业务层
		HuaCaoResult result = userService.updateStatus(itemIds,1);
		return result;
	}
	
	//解冻用户
	@RequestMapping("/user/reshelf")
	@ResponseBody
	public HuaCaoResult reshelf(String ids) {
		List<Integer> itemIds = stringToIds(ids);
		//调用业务层
		HuaCaoResult result = userService.updateStatus(itemIds,0);
		return result;
	}
	
	//切割itemId 转换类型
	public List<Integer> stringToIds(String ids){
		//分割id
		String[] split = ids.trim().split(",");
		//创建idList
		List<Integer> userIds = new ArrayList<>();
		//转换id格式
		for(String id : split) {
			userIds.add(Integer.parseInt(id));
		}
		return userIds;
	}
}

package cn.huacao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.huacao.common.jedis.JedisClient;
import cn.huacao.common.pojo.EasyUiDataGridResult;

/**
 * 用户请求业务实现类
 */

import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.common.utils.JsonUtils;
import cn.huacao.mapper.TbUserMapper;
import cn.huacao.pojo.TbItem;
import cn.huacao.pojo.TbItemExample;
import cn.huacao.pojo.TbUser;
import cn.huacao.pojo.TbUserExample;
import cn.huacao.pojo.TbUserExample.Criteria;
import cn.huacao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private TbUserMapper userMapper;
	
	@Autowired
	private JedisClient jedisClient;
	//redis中保存用户1session的前缀
	@Value("${USER_SESSION}")
	private String USER_SESSION;
	//用户session的过期时间
	@Value("${SESSION_EXPIRE}")
	private Integer SESSION_EXPIRE;
	//校验请求数据
	@Override
	public HuaCaoResult checkData(String param, int type) {
		
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		//判断用户名是否可有
		if(type==1) {
			criteria.andUsernameEqualTo(param);
		}else if(type==2) {//判断手机号是否可有
			criteria.andPhoneEqualTo(param);
		}else if(type==3) {//判断邮箱是否可用
			criteria.andEmailEqualTo(param);
		}else {
			return HuaCaoResult.build(400, "非法数据!!");
		}
		//根据条件查询
		List<TbUser> list = userMapper.selectByExample(example );
		//判断是否存在
		if(list!=null && list.size()>0) {
			return HuaCaoResult.ok(false);
		}
		return HuaCaoResult.ok(true);
	}
	
	
	//用户注册请求
	@Override
	public HuaCaoResult register(TbUser user) {
		//补全属性
		Date date = new Date();
		user.setCreated(date);
		user.setUpdated(date);
		//MD5加密密码
		String pwd = DigestUtils.md5Hex(user.getPassword().getBytes());
		user.setPassword(pwd);
		//插入数据
		userMapper.insert(user);
		return HuaCaoResult.ok();
	}


	//用户登录
	@Override
	public HuaCaoResult login(String username , String password) {
		
		//密码要进行MD5加密
		String pwd = DigestUtils.md5Hex(password.getBytes());
		//根据用户名密码查询用户
		List<TbUser> userList = userMapper.selectUserByNameAndPwd(username, pwd);
		if(userList==null||userList.size()<=0) {
			return HuaCaoResult.build(400, "用户名或密码错误!!!!");
		}
		if(userList.get(0).getStatus()==1) {
			return HuaCaoResult.build(400, "您已经被冻结!!!!");
		}
		TbUser user = userList.get(0);
		//生成token,使用UUID
		String token = UUID.randomUUID().toString();
		//设置用户密码为空，保证安全性
		user.setPassword(null);
		//把用户信息保存到redis,key值为token,值为用户信息
		jedisClient.set(USER_SESSION+":"+token, JsonUtils.objectToJson(user));
		//设置key的过期时间 默认半小时
		jedisClient.expire(USER_SESSION+":"+token,SESSION_EXPIRE);
		//返回请求信息
		return HuaCaoResult.ok(token);
	}

	//查询用户是否登录
	@Override
	public HuaCaoResult getUserByToken(String token) {
		//根据token在redis中查询用户信息
		String json = jedisClient.get(USER_SESSION+":"+token);
		//判断用户是否存在
		if(StringUtils.isBlank(json)) {
			return HuaCaoResult.build(400, "用户登录过期!!!");
		}
		//重制用户过期时间
		jedisClient.expire(USER_SESSION+":"+token,SESSION_EXPIRE);
		//将json转换成用户对象
		TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
		return HuaCaoResult.ok(user);
	}
	
	//用户安全退出请求
	public HuaCaoResult userLogOut( String token) {
		//根据token将用户信息删除
		Long del = jedisClient.del(USER_SESSION+":"+token);
		return HuaCaoResult.ok();
	}


	//获取用户list
	@Override
	public EasyUiDataGridResult fundUserList(int page, int rows) {
		//查询数据 
		List<TbUser> list = userMapper.fundUserList((page-1)*rows,rows);
		//创建返回结果
		EasyUiDataGridResult result = new EasyUiDataGridResult();
		result.setRows(list);
		result.setTotal(Long.parseLong(list.size()+""));
		//返回结果
		return result;
	}

	
	//修改用户状态
	@Override
	public HuaCaoResult updateStatus(List<Integer> userIds, int status) {
		//循环遍历ID
		for(Integer userId : userIds) {
			//修改商品状态
			userMapper.updateUserStatus(userId, status);
		}
		return HuaCaoResult.ok();
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
    <title>注册-个人用户</title>
    <link type="text/css" rel="stylesheet" href="/css/regist.personal.css"/>
    <link type="text/css" rel="stylesheet" href="/css/passport.base.css"/>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<div class="w" id="logo">
    <div>
    	<a href="http://localhost:8082">
    		<img src="/images/logo.gif" alt="花草网" width="170" height="60"/>
    	</a> <b></b>
    </div>
</div>

<div class="w" id="regist">
    <div class="mt">
        <ul class="tab">
            <li class="curr">个人用户</li>
        </ul>
        <div class="extra">
        <span>我已经注册，现在就&nbsp;
        	<a href="/page/login" class="flk13">登录</a>
        </span>
        </div>
    </div>
    <div class="mc">
        <form id="personRegForm" method="post" onsubmit="return false">
            <div class="form" onselectstart="return false;">
                <div class="item" id="select-regName">
                    <span class="label"><b class="ftx04">*</b>用户名：</span>
                    <div class="fl item-ifo">
                        <div class="o-intelligent-regName">
                            <input type="text" id="regName" name="username" class="text" 
                            	value="" onblur="checkUsername()"/>
                            <i class="i-name"></i>
                        </div>
                    </div>
                    <font id="usernameError" color="red"></font>
                </div>
                <div id="o-password">
                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请设置密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwd" name="password" class="text" 
                            		value="" onblur="checkPassword()"/>
                            <i class="i-pass"></i>
                        </div>
                        <font id="pwdError" color="red"></font>
                    </div>

                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请确认密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwdRepeat" name="pwdRepeat" class="text"
                            		onblur="checkPwdRepeat()"/>
                            		<i class="i-pass"></i>
                        </div>
                        <font id="pwdRepeatError" color="red"></font>
                    </div>
					<div class="item" id="dphone">
						<span class="label"><b class="ftx04">*</b>手机：</span>
						<div class="fl item-ifo">
							<input type="text" id="phone" maxlength="11" name="phone"
								class="text" onblur="checkPhone()" /> 
								<i class="i-phone"></i>
						</div>
						<font id="phoneError" color="red"></font>
					</div>
					<div class="item" id="dphoneCode">
						<span class="label"><b class="ftx04">*</b>手机验证码：</span>
						<div class="fl item-ifo">
							<input type="text" style="width: 90px" id="phoneCode" maxlength="6" name="phoneCode"
								class="text" /> 
						</div> 
						<div>
							 <input type="button" style="width: 100px;height:36px " class="btn-img" id="getPhoneCode" value="点击获取验证码" tabindex="8"
                           onclick="getPhonecode();"/>
						</div>
						<font id="phoneCodeError" color="red"></font>
					</div>
					</div>
                <div class="item item-new">
                    <span class="label">&nbsp;</span>

                    <div class="fl item-ifo">
                        <input type="checkbox" class="checkbox" checked="checked" id="readme"/>
                        <label for="protocol">我已阅读并同意<a href="#" class="blue" id="protocol">《花草网用户注册协议》</a></label>
                        <span class="clr"></span>
                        <label id="protocol_error" class="error hide"></label>
                    </div>
                </div>
                <div class="item">
                    <span class="label">&nbsp;</span>
                    <input type="button" class="btn-img btn-regist" id="registsubmit" value="立即注册" tabindex="8"
                           clstag="regist|keycount|personalreg|07"
                           onclick="register();"/>
                </div>
            </div>
            
            <span class="clr"></span>
        </form>
    </div>
<script type="text/javascript">
	function checkUsername(){
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		var name = $("#regName").val();
		if(name.length==0||re.test(name)){
			$("#usernameError").text("用户名不能为空");
		}else if(name.length<2||name.length>11){
			$("#usernameError").text("用户名长度为2~11");
		}else{
			$.post("/user/check",{param:name,type:1},function(data){
				if(data.data==true){
					$("#usernameError").text("");
				}else{
					$("#usernameError").text("用户名已存在");
				}
			})
		}
	}
	function checkPassword(){
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		var pwd = $("#pwd").val();
		if(pwd.length==0||re.test(pwd)){
			$("#pwdError").text("密码不能为空");
		}else if(pwd.length<6||pwd.length>11){
			$("#pwdError").text("密码长度为6~11位");
		}else{
			$("#pwdError").text("");
		}
	}
	
	function checkPwdRepeat(){
		var pwd = $("#pwd").val();
		var pwdRepeat = $("#pwdRepeat").val();
		if(pwd == pwdRepeat){
			$("#pwdRepeatError").text("");
		}else{
			$("#pwdRepeatError").text("两次密码输入不一致");
		}
	}
	
	function checkPhone(){
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		var phone = $("#phone").val();
		if(phone.length==0||re.test(phone)){
			$("#phoneError").text("手机号不能为空");
		}else if(!(/^1[34578]\d{9}$/.test(phone))){ 
			$("#phoneError").text("手机号填写有误");
	    } else{
			$.post("/user/check",{param:phone,type:2},function(data){
				if(data.data==true){
					$("#phoneError").text("");
				}else{
					$("#phoneError").text("手机号已被注册");
				}
			})
		}
	}
	
	
	
	//倒计时时间
	var wait=0;
	//发送手机验证码
	function getPhonecode(){
		var phone1 = $.trim($("#phone").val());
		if(phone1.length==0){
			$("#phoneError").text("手机号不能为空");
		}else if(!(/^1[34578]\d{9}$/.test(phone1))){ 
			$("#phoneError").text("手机号填写有误");
	    } else if(wait==0){
			$.post("/user/sendPhoneCode",{phone:phone1},function(data){
				wait=60;
				time();
			})
		}
	}
	
	//获取验证码倒计时
	function time(){
		if(wait==0){
			$("#getPhoneCode").val("点击获取验证码");
		}else{
			$("#getPhoneCode").val("重新发送("+wait+")");
			wait--;
			setTimeout(function(){
				time();
			},1000)
		}
	}
	
	
	//用户注册请求
	function register(){
		var usernameError =  $("#usernameError").text();
		var pwdError =  $("#pwdError").text();
		var pwdRepeatError = $("#pwdRepeatError").text();
		var phoneError = $("#phoneError").text();
		var name = $("#regName").val();
		if(usernameError==""&&pwdError==""&&
				pwdRepeatError==""&&phoneError=="" && name.length>0){
			$.post("/user/register",$("#personRegForm").serialize(), function(data){
				if(data.status == 200){
					alert('用户注册成功，请登录！');
					location.href = "/page/login";
				} else if(data.status == 0) {
					$("#phoneCodeError").text("验证码输入错误!!");
				} else{
					alert("系统错误无法注册!!");
				}
			});
		}
	}
</script>
</body>
</html>

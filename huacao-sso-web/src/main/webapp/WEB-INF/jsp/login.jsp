<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
    <title>登录花草网</title>
    <link type="text/css" rel="stylesheet" href="/css/login.css"/>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
<div class="w">
    <div id="logo">
    	<a href="http://localhost:8082" clstag="passport|keycount|login|01">
    		<img src="/images/logo.gif" alt="淘淘" width="170" height="60"/>
    	</a><b></b>
   	</div>
</div>
<form id="formlogin" method="post" onsubmit="return false;">
    <div class=" w1" id="entry">
        <div class="mc " id="bgDiv">
            <div id="entry-bg" clstag="passport|keycount|login|02" style="width: 1113px; height: 404px; position: absolute; left: -201px; top: -44px; background: url(/images/20160331145919_8098.jpg) 0px 0px no-repeat;">
			</div>
            <div class="form ">
                <div class="item fore1">
                    <span>用户名</span>
                    <div class="item-ifo">
                        <input type="text" id="loginname" name="username" class="text"  tabindex="1" 
                        		autocomplete="off" onblur="checkUsername()"/>
                        <div class="i-name ico"></div>
                    </div>
                </div>
                <font id="usernameError" color="red"></font>
                <script type="text/javascript">
                    setTimeout(function () {
                        if (!$("#loginname").val()) {
                            $("#loginname").get(0).focus();
                        }
                    }, 0);
                </script>
                <div id="capslock"><i></i><s></s>键盘大写锁定已打开，请注意大小写</div>
                <div class="item fore2">
                    <span>密码</span>
                    <div class="item-ifo">
                        <input type="password" id="loginpwd" name="password" class="text" 
                        	onblur="checkPassword()"/>
                        <div class="i-pass ico"></div>
                    </div>
                </div>
                <font id="pwdError" color="red"></font>
                <div class="item login-btn2013">
                    <input type="button" class="btn-img btn-entry" id="loginsubmit" value="登录" onclick="login()"/>
                </div>
                <font id="loginError" color="red"></font>
            </div>
        </div>
        <div class="free-regist">
            <span><a href="/page/register" clstag="passport|keycount|login|08">免费注册&gt;&gt;</a></span>
        </div>
    </div>
</form>
<script type="text/javascript">
	function checkUsername(){
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		var name = $("#loginname").val();
		if(name.length==0||re.test(name)){
			$("#usernameError").text("用户名不能为空");
		}else{
			$("#usernameError").text("");
		}
	}
	
	function checkPassword(){
		var regu = "^[ ]+$";
		var re = new RegExp(regu);
		var pwd = $("#loginpwd").val();
		if(pwd.length==0||re.test(pwd)){
			$("#pwdError").text("密码不能为空");
		}else{
			$("#pwdError").text("");
		}
	}
	
	function login(){
		var redirectUrl="${redirect}";
		var usernameError =  $("#usernameError").text();
		var pwdError =  $("#pwdError").text();
		if(usernameError==""&&pwdError==""){
			$.post("/user/login",$("#formlogin").serialize(), function(data){
				if(data.status == 200){
					if(redirectUrl==""){
						location.href = "http://localhost:8082";
					}else{
						location.href = redirectUrl;
					}
					
				}else {
					$("#loginError").text(data.msg);
				}
			});
		}
	}
</script>
</body>
</html>
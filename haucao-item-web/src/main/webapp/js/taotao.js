var TT = TAOTAO = {
	checkLogin : function(){
		var _ticket = $.cookie("HUACAO_TOKEN");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8088/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					$("#order").empty();
					var username = data.data.username;
					var html = username + "，欢迎来到花草网！<a href=\"http://localhost:8088/user/logout/"+_ticket+"\" class=\"link-logout\">[退出]</a>";
					var html1 = "<s></s><a  href=\"http://localhost:8091/order/getUserOrderList?userId="+data.data.id+"\" rel=\"nofollow\">我的订单</a>";
					$("#loginbar").html(html);
					$("#order").html(html1);
				}else{
					$("#order").empty();
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	TT.checkLogin();
});
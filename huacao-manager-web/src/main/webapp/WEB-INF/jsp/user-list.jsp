<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="userList" title="商品列表" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/user/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">用户ID</th>
            <th data-options="field:'username',width:200">用户名称</th>
            <th data-options="field:'phone',width:100">用户手机</th>
            <th data-options="field:'email',width:100">用户邮箱</th>
            <th data-options="field:'status',width:60,align:'center',formatter:TAOTAO.formatUserStatus">状态</th>
            <th data-options="field:'created',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建日期</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:TAOTAO.formatDateTime">更新日期</th>
        </tr>
    </thead>
</table>
<script>

	//获取选中的商品id
    function getSelectionsUserIds(){
    	var userList = $("#userList");
    	var sels = userList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'冻结',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsUserIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定冻结ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/user/instock",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','冻结用户成功!',undefined,function(){
            					$("#userList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'解冻',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsUserIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中用户!');
        		return ;
        	}
        	$.messager.confirm('确认','确定解冻ID为 '+ids+' 的用户吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/user/reshelf",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','解冻成功!',undefined,function(){
            					$("#userList").datagrid("reload");
             				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body >
<table id="roleDatagrid"></table>
		<div id="tb">
			<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add',plain:true">添加</a>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a class="easyui-linkbutton" onclick="remove()" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div id="role_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
			<form id="role_form" method="post">
				<input type="hidden" name = "id"  id = "id"/>
				<table>
					<tr>
						<td width="100" align="right">角色名称:</td>
						<td><div  id="roleName" name="roleName" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>	
					</tr>
					<tr>
						<td width="100" align="right">角色状态:</td>
						<td><div id="status" name="status" class="easyui-combobox" style="width: 150px;" data-options="	
							valueField: 'id',
							textField: 'text',
							panelHeight:100,
							required:true,
							data: [{
								id: '0',
								text: '禁用'
							},{
								id: '1',
								text: '正常'
							}]"></div></td>	
					</tr>
					<tr>
						<td width="100" align="right">角色权限:</td>
						<td><input type="text"  id="permissionId" name="permissions" style="width: 150px;" class="easyui-combotree"
							data-options="url:'/sams/getAllPermissions',multiple:true,required:true,panelHeight:300"/></td>	
					</tr>
				</table>
			</form>
		</div>
		<script type="text/javascript">
	$(function name() {
		$("#roleDatagrid").datagrid({
			url:'/sams/getAllPageRole',
			striped:true,
			pagination:true,
			toolbar:"#tb",
			columns:[[
				{field:'ck',checkbox:true},
				{field:"id",sortable:true, title:"id",width:50,align:'left'},
				{field:"roleName",title:"角色名称",width:200,align:'center'},
				{field:"status",title:"角色状态",width:200,align:'center',formatter:function(value,rows,index){
					//value:原本这个列的值，rows:包含本行所用数据列的数据值对象，index:行的索引
					if(value == 1){
						return "正常";
					}else{
						return "禁用";
					}
				}},
				{field:"permissions",title:"角色权限",align:'center',formatter:function(value,rows,index){
					var resourceStr="";
					if(value != null && value.length > 0){
						for(var i=0;i<value.length;i++){
							resourceStr += "【" + value[i].resource + "】";
						}
					}
					return resourceStr;
				}}
			]]
		});
	})
		
	
	function openAdd(){
		$("#role_form").form("clear");
		$("#role_Dialog").dialog({
			title:'添加角色',
			closed:false,
			modal:true,
			buttons:[
				{
					text:'确定',
					iconCls:'icon-ok',
					handler:role_add
				},
				{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#role_Dialog").dialog('close');
					}
				}],
		});
	}
	
	function openUpdate(){
		$("#role_form").form("clear");
		var rows = $("#roleDatagrid").datagrid("getSelections");
		if(rows.length > 1){
			$.messager.alert("信息提示","一次只能修改一条数据!","info");
		}else if(rows.length == 0){
			$.messager.alert("信息提示","请选择您要修改的数据!","info");
		}else{
			 var role = rows[0];
			$("#role_form").form("load",role);
			$("#role_Dialog").dialog({
				title:'修改角色',
				closed:false,
				modal:true,
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:role_update
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#role_Dialog").dialog('close');
						}
					}],
			}); 
		}
		
		
	}
	
	function remove(){
		
				var rows = $("#roleDatagrid").datagrid("getSelections");
				if(rows.length < 1){
					$.messager.alert("信息提示","请选择您要删除的数据!","info");
				}else{
					$.messager.confirm("信息提示","你确认要删除选中的记录吗？",function(result){
					if(result){
					var ids = [];
					$(rows).each(function(){
						ids.push(this.id);
					});
					$.ajax({
						url:'/sams/role_remove',
						method:'post',
						data:{
							ids:ids
						},
						success:function(data){ //"ok","error"
							if(data == "ok"){
								$("#roleDatagrid").datagrid("reload");
							}else{
								$.messager.alert("信息提示","删除失败！","info");
							}
						}
					});
				}
			});	
		}
	}
	
	function role_add() {
		doSubmit("/sams/add_role");
	}
	
	function role_update(){
		doSubmit("/sams/update_role");
	}
	
	function doSubmit(param){
		//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
		var permissionArr = $("#permissionId").val().split(",");
		var permissionObjStr = "[";
		for(var i=0;i<permissionArr.length;i++){
			permissionObjStr += "{id:"+permissionArr[i]+"},";
		}
		//[{id:1},{id:2},{id:3}]
		permissionObjStr = permissionObjStr.substring(0,permissionObjStr.length-1) + "]";
		var permissionObj = eval("(" + permissionObjStr + ")");
		$.ajax({
			url:param,
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				id:$("#id").val(),
				roleName:$("#roleName").val(),
				status:$("#status").val(),
				permissions: permissionObj
			}),
			beforeSend:function(){    
	            var isValid = $("#role_form").form('validate');
	            return isValid; // 返回false终止表单提交
	        },
			success:function(data){ //"ok","error"
				if(data == "ok"){
					$("#role_Dialog").dialog("close");
					$("#roleDatagrid").datagrid("reload");
				}else{
					$.messager.alert("信息提示","提交失败！","info");
				}
			}
		});
	}
	
	function cancel() {
		$("#roleDatagrid").datagrid("rejectChanges");
	}
	function reload() {
		$("#roleDatagrid").datagrid("reload");
	}
</script>
</body>
</html>
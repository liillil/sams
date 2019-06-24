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
<table id="permissionDatagrid"></table>
		<div id="tb">
			<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add',plain:true">添加</a>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a class="easyui-linkbutton" onclick="remove()" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div id="permission_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
			<form id="permission_form" method="post">
				<input type="hidden" name = "id"  id = "id"/>
				<table>
					<tr>
						<td width="100" align="right">权限名称:</td>
						<td><div  id="permissionName" name="resource" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>	
					</tr>
					<tr>
						<td width="100" align="right">权限状态:</td>
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
				</table>
			</form>
		</div>
		<script type="text/javascript">
	$(function name() {
		$("#permissionDatagrid").datagrid({
			url:'/sams/getAllPagepermission',
			striped:true,
			pagination:true,
			toolbar:"#tb",
			columns:[[
				{field:'ck',checkbox:true},
				{field:"id",sortable:true, title:"id",align:'left'},
				{field:"resource",title:"权限名称",align:'center'},
				{field:"status",title:"角色状态",align:'center',formatter:function(value,rows,index){
					//value:原本这个列的值，rows:包含本行所用数据列的数据值对象，index:行的索引
					if(value == 1){
						return "正常";
					}else{
						return "禁用";
					}
				}},
			]]
		});
	})
		
	
	function openAdd(){
		$("#permission_form").form("clear");
		$("#permission_Dialog").dialog({
			title:'添加权限',
			closed:false,
			modal:true,
			buttons:[
				{
					text:'确定',
					iconCls:'icon-ok',
					handler:permission_add
				},
				{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#permission_Dialog").dialog('close');
					}
				}],
		});
	}
	
	function openUpdate(){
		$("#permission_form").form("clear");
		var rows = $("#permissionDatagrid").datagrid("getSelections");
		if(rows.length > 1){
			$.messager.alert("信息提示","一次只能修改一条数据!","info");
		}else if(rows.length == 0){
			$.messager.alert("信息提示","请选择您要修改的数据!","info");
		}else{
			 var permission = rows[0];
			$("#permission_form").form("load",permission);
			$("#permission_Dialog").dialog({
				title:'修改权限',
				closed:false,
				modal:true,
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:permission_update
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#permission_Dialog").dialog('close');
						}
					}],
			}); 
		}
		
		
	}
	
	function remove(){
		
				var rows = $("#permissionDatagrid").datagrid("getSelections");
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
						url:'/sams/permission_remove',
						method:'post',
						data:{
							ids:ids
						},
						success:function(data){ //"ok","error"
							if(data == "ok"){
								$("#permissionDatagrid").datagrid("reload");
							}else{
								$.messager.alert("信息提示","删除失败！","info");
							}
						}
					});
				}
			});	
		}
	}
	
	function permission_add() {
		doSubmit("/sams/add_permission");
	}
	
	function permission_update(){
		doSubmit("/sams/update_permission");
	}
	
	function doSubmit(param){
		$.ajax({
			url:param,
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				id:$("#id").val(),
				resource:$("#permissionName").val(),
				status:$("#status").val(),
			}),
			beforeSend:function(){    
	            var isValid = $("#permission_form").form('validate');
	            return isValid; // 返回false终止表单提交
	        },
			success:function(data){ //"ok","error"
				if(data == "ok"){
					$("#permission_Dialog").dialog("close");
					$("#permissionDatagrid").datagrid("reload");
				}else{
					$.messager.alert("信息提示","提交失败！","info");
				}
			}
		});
	}
	
	function cancel() {
		$("#permissionDatagrid").datagrid("rejectChanges");
	}
	function reload() {
		$("#permissionDatagrid").datagrid("reload");
	}
</script>
</body>
</html>
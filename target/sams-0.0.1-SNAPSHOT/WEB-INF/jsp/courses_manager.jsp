<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table id="coursesDatagrid"></table>
	<div id="tb">
		<div>
			<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add1',plain:true">添加</a>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a class="easyui-linkbutton" onclick="remove()" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div>
			课程编号:<div id="tb_coursesNum" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
			课程名称:<div id="tb_coursesName" class="easyui-textbox" style="width: 120px;height: 25px;"></div>
			学分:<div id="tb_credit" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
			学时:<div id="tb_period" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
			<a href="#" id = "search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="height: 25px;">查询</a>
		</div>
	</div>
	<div id="courses_dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
		<form id="courses_form" method="post">
			<input type="hidden" name = "id"  id = "id"/>
			<table>
				<tr>
					<td width="100" align="right">课程编号</td>
					<td><div id="coursesNum" name="coursesNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">课程名称</td>
					<td><div id="coursesName" name="coursesName" class="easyui-textbox" data-options="required:true" style="width:150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">学　　时</td>
					<td><div id="period" name="period" class="easyui-numberbox" data-options="required:true" style="width:150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">学　　分</td>
					<td><div id="credit" name="credit" class="easyui-numberbox" data-options="required:true" style="width:150px;"></div></td>
				</tr>
			</table>
		</form>
	</div>
	
<script type="text/javascript">
	$(function(){
		$("#coursesDatagrid").datagrid({
			url:'/sams/getAllCoursesByPage',
			striped:true,
			pagination:true,
			toolbar:"#tb",
			columns:[[
				{field:'ck',checkbox:true},
				{field:'id',title:'id',width:150,align:'center'},
				{field:'coursesNum',title:'课程编号',width:150,align:'center'},
				{field:'coursesName',title:'课程名称',width:150,align:'center'},
				{field:'credit',title:'学分',width:150,align:'center'},
				{field:'period',title:'学时',width:150,align:'center'}
			]]
		});
	});
	
	
	$("#search_btn").click(function(){		
		var coursesNum = $("#tb_coursesNum").numberbox("getValue");
		var coursesName = $("#tb_coursesName").textbox("getValue");
		var credit = $("#tb_credit").numberbox("getValue");
		var period = $("#tb_period").numberbox("getValue");
		var queryParams = $("#coursesDatagrid").datagrid("options").queryParams;
		queryParams.coursesName = $.trim(coursesName);
		queryParams.coursesNum = coursesNum;
		queryParams.credit=credit;
		queryParams.period=period;
		$("#coursesDatagrid").datagrid("load");
	});
	
	function openAdd(){
		$("#courses_form").form("clear");
		$("#courses_dialog").dialog({
			title:'添加课程',
			closed:false,
			modal:true,
			buttons:[
				{
					text:'确定',
					iconCls:'icon-ok',
					handler:courses_add
				},
				{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#courses_dialog").dialog('close');
					}
				}],
		});
	}
	
	function openUpdate(){
		$("#courses_form").form("clear");
		var rows = $("#coursesDatagrid").datagrid("getSelections");
		if(rows.length > 1){
			$.messager.alert("信息提示","一次只能修改一条数据!","info");
		}else if(rows.length == 0){
			$.messager.alert("信息提示","请选择您要修改的数据!","info");
		}else{
			 var courses = rows[0];
			$("#courses_form").form("load",courses);
			$("#courses_dialog").dialog({
				title:'修改课程信息',
				closed:false,
				modal:true,
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:courses_update
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#courses_dialog").dialog('close');
						}
					}],
			}); 
		}
	}
	
	function remove(){
		var rows = $("#coursesDatagrid").datagrid("getSelections");
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
						url:'/sams/courses_remove',
						method:'post',
						data:{
							ids:ids
						},
						success:function(data){ //"ok","error"
							if(data == "ok"){
								$("#coursesDatagrid").datagrid("reload");
							}else {
								$.messager.alert("信息提示","删除失败！","info");
							}
						}
					});
				}
			});	
	    }
}
	
	function courses_add() {
		doSubmit("/sams/add_courses");
	}
	
	function courses_update(){
		doSubmit("/sams/update_courses");
	}
	
	function doSubmit(param){
		$.ajax({
			url:param,
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				id:$("#id").val(),
				coursesNum:$("#coursesNum").val(),
				coursesName:$("#coursesName").val(),
				period:$("#period").val(),
				credit:$("#credit").val(),
				
			}),
			beforeSend:function(){    
	            var isValid = $("#courses_form").form('validate');
	            return isValid; // 返回false终止表单提交
	        },
			success:function(data){ //"ok","error"
				if(data == "ok"){
					$("#coursesDatagrid").datagrid("reload");
					$("#courses_dialog").dialog("close");		
				}else if (data == "info") {
					$.messager.alert("信息提示","用户名已存在！","info");
				}else{
					$.messager.alert("信息提示","提交失败！","info");
				}
			}
		});
	}
	
	
	function cancel() {
		$("#coursesDatagrid").datagrid("rejectChanges");
		
	}
	function reload() {
		$("#coursesDatagrid").datagrid("reload");
	}
</script>		
</body>
</html>
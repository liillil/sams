<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专业课程信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div id="majorCoursesDatagrig"></div>
	<div id="tb">
		<div>
			<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add1',plain:true">添加</a>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a class="easyui-linkbutton" onclick="remove()" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div>
			
			专业:<div id="tb_major" class="easyui-combobox" data-options="url:'/sams/getAllMajor',valueField:'id',textField:'text'" style="width: 120px;height: 25px;"></div>		
			<a href="#" id = "search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="height: 25px;">查询</a>
		</div>
	</div>
	<div id="major_courses_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
		<form id="major_courses_form" method="post">
			<input type="hidden" name = "id"  id = "id"/>
			<table>
				<tr>
					<td width="100" align="right">专业编号:</td>
					<td><div id="majorNum" name="majorNum" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">专业名称:</td>
					<td><div id="majorName" name="majorName" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">院系名称:</td>
					<td><div id="depName" name="depName" class="easyui-combobox" data-options="url:'/sams/getAllDepartment',valueField:'id',textField:'text',required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">专业课程:</td>
					<td><div id="coursesName" name="coursesName" class="easyui-combotree" data-options="url:'/sams/getAllCourses',multiple:true,required:true,panelHeight:133" style="width: 150px;"></div></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<script type="text/javascript">
	$(function name(){
		$("#majorCoursesDatagrig").datagrid({
			url:'/sams/getMajor',
			striped:true,
			pagination:true,
			toolbar:"#tb",
			columns:[[
				{field:'ck',checkbox:true},	
				{field:'majorNum',title:'专业编号',width:150,align:'center'},
				{field:'majorName',title:'专业名称',width:150,align:'center'},
				{field:"department",title:"所属院系",width:100,align:'center',formatter:function(value,rows,index){
					return value.departmentName;
				}},
				{field:"majorCourses",title:"专业课程",align:'center',formatter:function(value,rows,index){
					var resourceStr="";
					if(value != null && value.length > 0){
						for(var i=0;i<value.length;i++){
							resourceStr += "【" + value[i].coursesName + "】";
						}
					}
					return resourceStr;
				}}
				]]
		});
		
			$(".datetime").datebox("setValue",formatterDate(new Date()));
	});
	
	$("#search_btn").click(function(){//按条件查询用户信息	
		var majorId = $("#tb_major").combobox("getValue");
		var queryParams = $("#majorCoursesDatagrig").datagrid("options").queryParams;	
		queryParams.majorId = majorId;
		$("#majorCoursesDatagrig").datagrid("load");
	});
	
	
	
	function openAdd(){
		$("#major_courses_form").form("clear");
		$("#major_courses_Dialog").dialog({
			title:'添加专业信息',
			closed:false,
			modal:true,
			buttons:[
				{
					text:'确定',
					iconCls:'icon-ok',
					handler:major_courses_add
				},
				{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#major_courses_Dialog").dialog('close');
					}
				}],
		});
	}
	
	
	function openUpdate(){
		$("#major_courses_form").form("clear");
		var rows = $("#majorCoursesDatagrid").datagrid("getSelections");
		if(rows.length > 1){
			$.messager.alert("信息提示","一次只能修改一条数据!","info");
		}else if(rows.length == 0){
			$.messager.alert("信息提示","请选择您要修改的数据!","info");
		}else{
			 var student = rows[0];
			$("#major_courses_form").form("load",student);
			$("#major_courses_Dialog").dialog({
				title:'修改专业信息',
				closed:false,
				modal:true,
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:major_courses_update
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#major_courses_Dialog").dialog('close');
						}
					}],
			}); 
		}	
	}
	
	function remove(){		
		var rows = $("#majorCoursesDatagrid").datagrid("getSelections");
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
					url:'/sams/remove_major_courses',
					method:'post',
					data:{
						ids:ids
					},
					success:function(data){ //"ok","error"
						if(data == "ok"){
							$("#majorCoursesDatagrid").datagrid("reload");
						}else {
							$.messager.alert("信息提示","删除失败！","info");
						}
					}
				});
			   }
		});	
	}
}
	
	function major_courses_add() {
		doSubmit("/sams/add_major_courses");
	}
	
	function major_courses_update(){
		doSubmit("/sams/update_major_courses");
	}
	
	function doSubmit(param){
		//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
		var depId = $("#depName").val();
		var depObjStr = "";
		depObjStr += "{id:"+depId+"}";

		//[{id:1},{id:2},{id:3}]
		var depObj = eval("(" + depObjStr + ")");
		
		
		var coursesArr = $("#coursesName").val().split(",");
		var coursesObjStr = "[";
		for(var i=0;i<coursesArr.length;i++){
			coursesObjStr += "{id:"+coursesArr[i]+"},";
		}
		//[{id:1},{id:2},{id:3}]
		coursesObjStr = coursesObjStr.substring(0,coursesObjStr.length-1) + "]";
		var coursesObj = eval("(" + coursesObjStr + ")");
		$.ajax({
			url:param,
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				id:$("#id").val(),
				majorNum:$("#majorNum").val(),
				majorName:$("#majorName").val(),				
				department: depObj,
				majorCourses:coursesObj
			}),
			beforeSend:function(){    
	            var isValid = $("#major_courses_form").form('validate');
	            return isValid; // 返回false终止表单提交
	        },
			success:function(data){ //"ok","error"
				if(data == "ok"){
					$("#major_courses_Dialog").dialog("close");
					$("#majorCoursesDatagrid").datagrid("reload");
				}else if (data == "info") {
					$.messager.alert("信息提示","用户名已存在！","info");
				}else{
					$.messager.alert("信息提示","提交失败！","info");
				}
			}
		});
	}
	
	function cancel() {
		$("#majorCoursesDatagrid").datagrid("rejectChanges");
	}
	function reload() {
		$("#majorCoursesDatagrid").datagrid("reload");
	}
	
	</script>
</body>
</html>
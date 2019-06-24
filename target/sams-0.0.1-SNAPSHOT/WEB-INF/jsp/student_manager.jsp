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
<body>
	<table id="studentDatagrid"></table>
	<div id="tb">
				<div >
					<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add1',plain:true">添加</a>
					<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
					<a class="easyui-linkbutton" onclick="remove()" data-options="iconCls:'icon-remove',plain:true">删除</a>
					<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	            	<a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
				</div>
				<div >
				入学日期:<div id="startDate" class="easyui-datebox" style="width: 120px;height: 25px;"></div>
						-<div id="endDate" class="easyui-datebox" style="width: 120px;height: 25px;"></div>
				专业:<div id="tb_major" class="easyui-combobox" data-options="url:'/sams/getAllMajor',valueField:'id',textField:'text'"
				 style="width: 120px;height: 25px;"></div>	
				班级编号:<div id="tb_classNum" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
				学号:<div id="tb_studentNum" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
				学生姓名:<div id="tb_studentName" class="easyui-textbox" style="width: 120px;height: 25px;"></div>					
				状态:<div id="tb_status" class="easyui-combobox" style="width: 120px;height: 25px;" data-options="	
						valueField: 'id',
						textField: 'text',
						panelHeight:100,
						data: [{
							id: '0',
							text: '禁用'
						},{
							id: '1',
							text: '正常'
						}]"></div>						
				<a href="#" id = "search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="height: 25px;">查询</a>
				</div>
		</div>
		<div id="student_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
			<form id="student_form" method="post">
				<input type="hidden" name = "id"  id = "id"/>
				<table>
					<tr>
						<td width="100" align="right">学	　　号:</td>
						<td><div id="studentNum" name="studentNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
					</tr>
					<tr>
						<td width="100" align="right">姓　　名:</td>
						<td><div id="studentName" name="studentName" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
					</tr>
					<tr>
						<td width="100" align="right">班级编号:</td>
						<td><div id="classNum" name="classNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
					</tr>
					<tr>
						<td width="100" align="right">专　　业:</td>
						<td><div id="major" name="major" class="easyui-combobox" data-options="url:'/sams/getAllMajor',valueField:'id',textField:'text',required:true" style="width: 150px;"></div></td>
					</tr>				
					<tr>
						<td width="100" align="right">性　　别:</td>
						<td><div id="sex" name="sex" class="easyui-combobox" style="width: 150px;" data-options="	
							valueField: 'id',
							textField: 'text',
							panelHeight:100,
							required:true,
							data: [{
								id: '0',
								text: '男'
							},{
								id: '1',
								text: '女'  
							}]"></div></td>	
					</tr>
					<tr>
						<td width="100" align="right">入学日期:</td>
						<td><div id="enrollmentDate" name="enrollmentDate" class="easyui-datebox" data-options="required:true" style="width: 150px;"></div></td>
					</tr>
					<tr>
						<td width="100" align="right">状　　态:</td>
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

	formatterDate = function (date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
	};

	$(function() {
		$("#studentDatagrid").datagrid({
			url:'/sams/getAllStudentByPage',
			striped:true,
			pagination:true,
			toolbar:"#tb",
			columns:[[
				{field:'ck',checkbox:true},
				{field:'studentNum',title:'学号',width:150,align:'center'},
				{field:'studentName',title:'姓名',width:100,align:'center'},								
				{field:"major",title:"专业",width:100,align:'center',formatter:function(value,rows,index){
					return value.majorName;
				}},
				{field:"classNum",title:"班级编号",width:100,align:'center'},			
				{field:'sex',title:'性别',width:50,align:'center',formatter:function(value,rows,index){
					//value:原本这个列的值，rows:包含本行所用数据列的数据值对象，index:行的索引
					if(value == 1){
						return "女";
					}else{
						return "男";
					}
				}},
				{field:"enrollmentDate",title:"入学日期",width:200,align:'center'},
				{field:"status",title:"用户状态",width:100,align:'center',formatter:function(value,rows,index){
					//value:原本这个列的值，rows:包含本行所用数据列的数据值对象，index:行的索引
					if(value == 1){
						return "正常";
					}else{
						return "禁用";
					}
				}}
		    ]] 
		});
		
		$("#startDate").datebox("setValue",formatterDate(new Date()));
		$("#endDate").datebox("setValue",formatterDate(new Date()));
	});	
	
	
	$("#search_btn").click(function(){//按条件查询用户信息
		var studentNum = $("#tb_studentNum").numberbox("getValue");
		var studentName = $("#tb_studentName").textbox("getValue");
		var classNum = $("#tb_classNum").numberbox("getValue");
		var status = $("#tb_status").combobox("getValue");
		var startDate = $("#startDate").datebox("getValue");
		var endDate = $("#endDate").datebox("getValue");
		var MajorId = $("#tb_major").combobox("getValue");
		var queryParams = $("#studentDatagrid").datagrid("options").queryParams;
		queryParams.studentNum = $.trim(studentNum);
		queryParams.studentName = $.trim(studentName);
		queryParams.classNum = $.trim(classNum);
		queryParams.status = status;
		queryParams.MajorId=MajorId;
		queryParams.startDate = startDate;
		queryParams.endDate = endDate;
		$("#studentDatagrid").datagrid("load");
	});
	
	
	$.extend($.fn.validatebox.defaults.rules,{
		
		equals: {//重写equals
			validator: function(value,param){
				return value == $(param[0]).val();
			},
			message:'前后输入的密码不一致'
		},

		
		minLength: {//自定义函数
			validator: function(value,param){
				return value.length>=param[0];
			},
			message:'密码长度不足'
		}
	});
	
	function openAdd(){
		$("#student_form").form("clear");
		$("#student_Dialog").dialog({
			title:'添加学生信息',
			closed:false,
			modal:true,
			buttons:[
				{
					text:'确定',
					iconCls:'icon-ok',
					handler:user_add
				},
				{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#student_Dialog").dialog('close');
					}
				}],
		});
	}
	
	
	function openUpdate(){
		$("#student_form").form("clear");
		var rows = $("#studentDatagrid").datagrid("getSelections");
		if(rows.length > 1){
			$.messager.alert("信息提示","一次只能修改一条数据!","info");
		}else if(rows.length == 0){
			$.messager.alert("信息提示","请选择您要修改的数据!","info");
		}else{
			 var student = rows[0];
			$("#student_form").form("load",student);
			$("#student_Dialog").dialog({
				title:'修改学生信息',
				closed:false,
				modal:true,
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:user_update
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#student_Dialog").dialog('close');
						}
					}],
			}); 
		}	
	}
	
	function remove(){		
		var rows = $("#studentDatagrid").datagrid("getSelections");
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
					url:'/sams/student_remove',
					method:'post',
					data:{
						ids:ids
					},
					success:function(data){ //"ok","error"
						if(data == "ok"){
							$("#studentDatagrid").datagrid("reload");
						}else {
							$.messager.alert("信息提示","删除失败！","info");
						}
					}
				});
			   }
		});	
	}
}
	
	function user_add() {
		doSubmit("/sams/add_student");
	}
	
	function user_update(){
		doSubmit("/sams/update_student");
	}
	
	function doSubmit(param){
		//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
		var majorId = $("#major").val();
		var majorObjStr = "";
		majorObjStr += "{id:"+majorId+"}";

		//[{id:1},{id:2},{id:3}]
		var majorObj = eval("(" + majorObjStr + ")");
		$.ajax({
			url:param,
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				id:$("#id").val(),
				studentNum:$("#studentNum").val(),
				studentName:$("#studentName").val(),
				classNum:$("#classNum").val(),
				status:$("#status").val(),
				sex:$("#sex").val(),
				enrollmentDate:$("#enrollmentDate").val(),
				major: majorObj
			}),
			beforeSend:function(){    
	            var isValid = $("#student_form").form('validate');
	            return isValid; // 返回false终止表单提交
	        },
			success:function(data){ //"ok","error"
				if(data == "ok"){
					$("#student_Dialog").dialog("close");
					$("#studentDatagrid").datagrid("reload");
				}else if (data == "info") {
					$.messager.alert("信息提示","用户名已存在！","info");
				}else{
					$.messager.alert("信息提示","提交失败！","info");
				}
			}
		});
	}
	
	function cancel() {
		$("#studentDatagrid").datagrid("rejectChanges");
	}
	function reload() {
		$("#studentDatagrid").datagrid("reload");
	}
</script>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师任课管理</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	
	<div id="teacherCoursesDatagrig"></div>
	<div id="tb">
		<div>
			<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add1',plain:true">添加</a>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div>
			开课日期:<div id="tb_sDate" class="easyui-datebox datetime" style="width:120px;height: 25px;"></div>
			专业:<div id="tb_major" class="easyui-combobox" data-options="url:'/sams/getAllMajor',valueField:'id',textField:'text',
			onSelect: function(rec){
							var url = '/sams/getMajorCourses?id='+rec.id;
							var uri = '/sams/getClassNum?id='+rec.id;
							$('#tb_coursesName').combobox('reload', url);
							$('#tb_classNum').combobox('reload',uri);
							}
			" style="width: 120px;height: 25px;"></div>		
			专业课程:<div id="tb_coursesName" class="easyui-combobox" data-options="valueField:'id',textField:'text'" style="width: 120px;height: 25px;"></div>
			班级编号:<div id="tb_classNum" class="easyui-combobox" data-options="valueField:'id',textField:'text'" style="width:120px;height: 25px;"></div>
			教师编号:<div id="tb_teacherNum" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
			教师名称:<div id="tb_teacherName" class="easyui-textbox" style="width: 120px;height: 25px;"></div>
			<a href="#" id = "search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="height: 25px;">查询</a>
		</div>
	</div>
	<div id="teacher_courses_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
		<form id="teacher_courses_form" method="post">
			<table>
				<tr>
					<td width="100" align="right">开课日期</td>
					<td><div id="sDate" name="sDate" class="easyui-datebox datetime" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">专　　业</td>
					<td><div id="major" name="major" class="easyui-combobox" data-options="url:'/sams/getAllMajor',valueField:'id',
					textField:'text',required:true,
					onSelect: function(rec){
							var url = '/sams/getMajorCourses?id='+rec.id;
							var uri = '/sams/getClassNum?id='+rec.id;
							$('#coursesName').combobox('reload', url);						
							$('#classNum').combobox('reload',uri);
							}
					" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">专业课程</td>
					<td><div id="coursesName" name="coursesName" class="easyui-combobox" data-options="valueField:'id',textField:'text',required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">班级编号</td>
					<td><div id="classNum" name="classNum" class="easyui-combobox" data-options="valueField:'id',textField:'text',required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">教师编号</td>
					<td><div id="teacherNum" name="teacherNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">教师名称</td>
					<td><div  id="teacherName" name="teacherName" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
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
		$("#sDate").datebox().datebox("calendar").calendar({
			validator:function(date){//小于当前日期
				var now = new Date();
				return date <= now;//符合选择的条件
			}
		});
		$(function name(){
			$("#teacherCoursesDatagrig").datagrid({
				url:'/sams/getTeacherCourses',
				striped:true,
				pagination:true,
				toolbar:"#tb",
				columns:[[
					{field:'ck',checkbox:true},	
					{field:'teacherNum',title:'教师编号',width:150,align:'center'},
					{field:'teacherName',title:'教师姓名',width:150,align:'center'},
					{field:"major",title:"任课专业",width:100,align:'center',formatter:function(value,rows,index){
						return value.majorName;
					}},
					{field:"classNum",title:"任课班级编号",width:100,align:'center'},
					{field:"sDate",title:'开课日期',width:100,align:'center'},
					{field:'coursesName',title:'课程名称',width:100,align:'center'}
					]]
			});
			
				$(".datetime").datebox("setValue",formatterDate(new Date()));
		});
		
		$("#search_btn").click(function(){//按条件查询用户信息
			var teacherNum = $("#tb_teacherNum").numberbox("getValue");
			var teacherName = $("#tb_teacherName").textbox("getValue");
			var coursesName = $("#tb_coursesName").combobox('getText');
			var classNum = $("#tb_classNum").combobox('getText');
			var majorId = $("#tb_major").combobox("getValue");
			var sDate = $("#tb_sDate").datebox("getValue");
			var queryParams = $("#teacherCoursesDatagrig").datagrid("options").queryParams;
			queryParams.teacherNum = $.trim(teacherNum);
			queryParams.teacherName = $.trim(teacherName);
			queryParams.classNum = $.trim(classNum);
			queryParams.coursesName = $.trim(coursesName);
			queryParams.majorId = majorId;
			queryParams.sDate = sDate;
			$("#teacherCoursesDatagrig").datagrid("load");
		});
			
		function openAdd(){
			$("#teacher_courses_form").form("clear");
			$("#teacher_courses_Dialog").dialog({
				title:'添加课程',
				closed:false,
				modal:true,
				onOpen:function(){
					$("#sDate").datebox({disabled:false});
					$("#coursesName").combobox({disabled:false});
					$("#classNum").combobox({disabled:false});
					$("#major").combobox({disabled:false});
				},
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:teacher_courses_add
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#teacher_courses_Dialog").dialog('close');
						}
					}],
			});
		}
		
		function openUpdate(){
			$("#teacher_courses_form").form("clear");
			var rows = $("#teacherCoursesDatagrig").datagrid("getSelections");
			if(rows.length > 1){
				$.messager.alert("信息提示","一次只能修改一条数据!","info");
			}else if(rows.length == 0){
				$.messager.alert("信息提示","请选择您要修改的数据!","info");
			}else{
				 var teacher_courses = rows[0];
				$("#teacher_courses_form").form("load",teacher_courses);
				$("#teacher_courses_Dialog").dialog({
					title:'修改课程信息',
					closed:false,
					modal:true,
					 onOpen:function(){
						$("#sDate").textbox({disabled:true});
						$("#coursesName").textbox({disabled:true});
						$("#classNum").textbox({disabled:true});
						$("#major").combobox({disabled:true});
					}, 
					buttons:[
						{
							text:'确定',
							iconCls:'icon-ok',
							handler:teacher_courses_update
						},
						{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$("#teacher_courses_Dialog").dialog('close');
							}
						}],
				}); 
			}
		}
		
/* 		function remove(){
			var rows = $("#teacher_courses_Dialog").datagrid("getSelections");
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
							url:'/sams/teacher_courses_remove',
							method:'post',
							data:{
								ids:ids
							},
							success:function(data){ //"ok","error"
								if(data == "ok"){
									$("#teacherCoursesDatagrig").datagrid("reload");
								}else {
									$.messager.alert("信息提示","删除失败！","info");
								}
							}
						});
					}
				});	
		    }
	}	 */
		
		function teacher_courses_add() {
			doSubmit("/sams/add_teacher_courses");
		}
		
		function teacher_courses_update(){
			doSubmit("/sams/update_teacher_courses");
		}
		
		function doSubmit(param){
			$.ajax({
				url:param,
				method:"post",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({
					sDate:$("#sDate").val(),
					majorId:$("#major").val(),
					coursesName:$("#coursesName").combobox('getText'),
					classNum:$("#classNum").combobox('getText'),
					teacherNum:$("#teacherNum").val(),
					teacherName:$("#teacherName").val()
				}),
				beforeSend:function(){    
		            var isValid = $("#teacher_courses_form").form('validate');
		            return isValid; // 返回false终止表单提交
		        },
				success:function(data){ //"ok","error"
					if(data == "ok"){
						$("#teacherCoursesDatagrig").datagrid("reload");
						$("#teacher_courses_Dialog").dialog("close");		
					}else if (data == "info") {
						$.messager.alert("信息提示","用户名已存在！","info");
					}else{
						$.messager.alert("信息提示","提交失败！","info");
					}
				}
			});
		}	
		
		
		
		
		
		function cancel() {
			$("#teacherCoursesDatagrig").datagrid("rejectChanges");
			
		}
		function reload() {
			$("#teacherCoursesDatagrig").datagrid("reload");
		}
	</script>
	
</body>
</html>
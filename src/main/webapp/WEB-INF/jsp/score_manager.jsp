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
	<table id="scoreDatagrid"></table>
	<div id="tb">
		<div>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>			
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div>
			专业:<div id="tb_major" class="easyui-combobox" data-options="url:'/sams/getAllMajor',valueField:'id',textField:'text',
			onSelect: function(rec){
							var url = '/sams/getMajorCourses?id='+rec.id;
							var uri = '/sams/getClassNum?id='+rec.id;
							$('#tb_coursesName').combobox('reload', url);
							$('#tb_classNum').combobox('reload',uri);
							}
			" style="width: 120px;height: 25px;"></div>		
			专业课程:<div id="tb_coursesName" class="easyui-combobox" data-options="valueField:'id',textField:'text'" style="width: 120px;height: 25px;"></div>
			班级编号:<div id="tb_classNum" class="easyui-combobox" data-options="valueField:'id',textField:'text'" style="width: 120px;height: 25px;"></div>
			学号:<div id="tb_studentNum" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
			学生姓名:<div id="tb_studentName" class="easyui-textbox" style="width: 120px;height: 25px;"></div>
			开课日期:<div id="tb_sDate" class="easyui-datebox" style="width: 120px;height: 25px;"></div>	
					-<div id="tb_eDate" class="easyui-datebox" style="width: 120px;height: 25px;"></div>			
			<a href="#" id = "search_btn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="height: 25px;">查询</a>		
		</div>
	</div>
 	<div id="score_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
		<form id="score_form" method="post">
			
			<table>
				<tr>				
					<td>专　　业:</td>
					<td><div id="major" name="major" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>课程名称:</td>
					<td><div id="coursesName" name="coursesName" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>班级编号:</td>
					<td><div id="classNum" name="classNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>学　　号:</td>
					<td><div id="studentNum" name="studentNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>学生姓名:</td>
					<td><div id="studentName" name="studentName" class="easyui-textbox"  style="width: 150px;"></div></td>				
				</tr>				
				<tr>
					<td>成　　绩:</td>
					<td><div id="score" name="score" class="easyui-numberbox" data-options="max:100" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>第一次重修:</td>
					<td><div id="info1" name="info1" class="easyui-numberbox" data-options="max:100" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>第二次重修:</td>
					<td><div id="info2" name="info2" class="easyui-numberbox" data-options="max:100" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td>第三次重修:</td>
					<td><div id="info3" name="info3" class="easyui-numberbox" data-options="max:100" style="width: 150px;"></div></td>
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
		
		$(function name() {
			$("#scoreDatagrid").datagrid({
				url:'/sams/getStudentScore',
				striped:true,
				pagination:true,
				toolbar:"#tb",
				columns:[[
					{field:'ck',checkbox:true},					
					{field:"major",title:"专业",width:100,align:'center',formatter:function(value,rows,index){
						return value.majorName;
					}},
					{field:'classNum',title:'班级编号',width:150,align:'center'},
					{field:'studentNum',title:'学号',width:150,align:'center'},
					{field:'studentName',title:'学生姓名',width:150,align:'center'},
					{field:'coursesName',title:'课程名',width:150,align:'center'},					
					{field:'score',title:'成绩',width:150,align:'center',editor:{type:"numberbox"},
						options:{
							min:0,
							max:100
						}	
					},
					{field:'info1',title:'第一次重修',width:150,align:'center',editor:{type:"numberbox",
						options:{
							min:0,
							max:100
						}	
					}},
					{field:'info2',title:'第二次重修',width:150,align:'center',editor:{type:"numberbox",
						options:{
							min:0,
							max:100
						}
					}},
					{field:'info3',title:'第三次重修',width:150,align:'center',editor:{type:"numberbox",
						options:{
							min:0,
							max:100
						}
					}},
				]],
				onClickRow:function(index){
					if(editIndex != index){
						$("#scoreDatagrid").datagrid("rejectChanges");
					}
					$("#scoreDatagrid").datagrid("beginEdit",index);
					$("#scoreDatagrid").datagrid("selectRow",index);
					editIndex = index;
					},
			
				onAfterEdit:function(rowIndex,rowData,changes){
				//	var row = $("#scoreDatagrid").datagrid("getRows")[editIndex];
					$("#scoreDatagrid").dategrid("acceptChanges");
				//	$('#scoreDatagrid').datagrid('getChanges', 'updated');
					$.ajax({
						url:param,
						method:"post",
						contentType:"application/json;charset=utf-8",
						data:JSON.stringify({
							//id:$("#id").val(),
							//majorId:$("#major").val(),	
							classNum:rowData.classNum,
							coursesName:rowData.coursesName,
							studentNum:rowData.studentNum,
							studentName:rowData.studentName,
							score:rowData.score,
							info1:rowData.info1,
							info2:rowData.info2,
							info3:rowData.info3
						}),
						beforeSend:function(){    
				            var isValid = $("#score_form").form('validate');
				            return isValid; // 返回false终止表单提交
				        },
						success:function(data){ //"ok","error"
							if(data == "ok"){
								$("#scoreDatagrid").datagrid("reload");
							}else{
								$.messager.alert("信息提示","提交失败！","info");
							}
						}
					});
				}
			});
			
			var editIndex;
			
			$("#tb_sDate").datebox("setValue",formatterDate(new Date()));
			$("#tb_eDate").datebox("setValue",formatterDate(new Date()));
			
		});
		
		$("#search_btn").click(function(){//按条件查询用户信息
			var studentNum = $("#tb_studentNum").numberbox("getValue");
			var studentName = $("#tb_studentName").textbox("getValue");
			var classNum = $("#tb_classNum").combobox('getText');
			var majorId = $("#tb_major").combobox("getValue");
			var coursesName = $("#tb_coursesName").combobox("getText");	
			var sDate = $("#tb_sDate").datebox("getValue");
			var eDate = $("#tb_eDate").datebox("getValue");
			var queryParams = $("#scoreDatagrid").datagrid("options").queryParams;
			queryParams.studentNum = $.trim(studentNum);
			queryParams.studentName = $.trim(studentName);
			queryParams.classNum = $.trim(classNum);
			queryParams.majorId = $.trim(majorId);
			queryParams.coursesName = coursesName;
			queryParams.sDate = sDate;
			queryParams.eDate = eDate;
			$("#scoreDatagrid").datagrid("load");
		});
		
		
		
		function openUpdate(){
			$("#score_form").form("clear");
			var rows = $("#scoreDatagrid").datagrid("getSelections");
			if(rows.length > 1){
				$.messager.alert("信息提示","一次只能修改一条数据!","info");
			}else if(rows.length == 0){
				$.messager.alert("信息提示","请选择您要修改的数据!","info");
			}else{
				 var studentScore = rows[0];
				$("#score_form").form("load",studentScore);
				var majorName =  studentScore.major.majorName;
				$("#major").textbox("setValue",majorName);
				$("#score_Dialog").dialog({
					title:'修改用户',
					closed:false,
					modal:true,
					onOpen:function(){
						$("#major").textbox({disabled:true});
						$("#classNum").numberbox({disabled:true});
						$("#studentNum").numberbox({disabled:true});
						$("#studentName").textbox({disabled:true});
						$("#coursesName").textbox({disabled:true});
					},
					buttons:[
						{
							text:'确定',
							iconCls:'icon-ok',
							handler:score_update
						},
						{
							text:'取消',
							iconCls:'icon-cancel',
							handler:function(){
								$("#score_Dialog").dialog('close');
							}
						}],
				}); 
			}	
		}
		
		/* function remove(){		
			var rows = $("#scoreDatagrid").datagrid("getSelections");
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
						url:'/sams/score_remove',
						method:'post',
						data:{
							ids:ids
						},
						success:function(data){ //"ok","error"
							if(data == "ok"){
								$("#scoreDatagrid").datagrid("reload");
							}else {
								$.messager.alert("信息提示","删除失败！","info");
							}
						}
					});
				  	}
			});	
		}
	} */
		
		/* function score_add() {
			doSubmit("/sams/add_score");
		} */
		
		function score_update(){
			doSubmit("/sams/update_score");
		}
		
		function doSubmit(param){
			//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
			/* var majorId = $("#major").val();
			var majorObjStr = "";
			majorObjStr += "{id:"+majorId+"}"; 

			//[{id:1},{id:2},{id:3}]
			var majorObj = eval("(" + majorObjStr + ")");*/
			$.ajax({
				url:param,
				method:"post",
				contentType:"application/json;charset=utf-8",
				data:JSON.stringify({
					//id:$("#id").val(),
					//majorId:$("#major").val(),	
					classNum:$("#classNum").combobox('getText'),
					coursesName:$("#coursesName").combobox('getText'),
					studentNum:$("#studentNum").val(),
					studentName:$("#studentName").val(),
					score:$("#score").val(),
					info1:$("#info1").val(),
					info2:$("#info2").val(),
					info3:$("#info3").val()
				}),
				beforeSend:function(){    
		            var isValid = $("#score_form").form('validate');
		            return isValid; // 返回false终止表单提交
		        },
				success:function(data){ //"ok","error"
					if(data == "ok"){
						$("#score_Dialog").dialog("close");
						$("#scoreDatagrid").datagrid("reload");
					}else{
						$.messager.alert("信息提示","提交失败！","info");
					}
				}
			});
		}
		
		function cancel() {
			$("#scoreDatagrid").datagrid("rejectChanges");
		}
		function reload() {
			$("#scoreDatagrid").datagrid("reload");
		}
		
	</script>
</body>
</html>
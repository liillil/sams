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
	<table id="teacherDatagrig"></table>
	<div id="tb">
		<div>
			<a class="easyui-linkbutton" onclick="openAdd()" data-options="iconCls:'icon-add1',plain:true">添加</a>
			<a class="easyui-linkbutton" onclick="openUpdate()" data-options="iconCls:'icon-edit',plain:true">修改</a>
			<a class="easyui-linkbutton" onclick="remove()" data-options="iconCls:'icon-remove',plain:true">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
	        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
		</div>
		<div>
		入职日期:<div id="tb_startDate" class="easyui-datebox  datetime" style="width:120px;height:25px;"></div>
				 -<div id="tb_endDate" class="easyui-datebox  datetime" style="width:120px;height:25px;"></div>	
			教师编号:<div id="tb_teacherNum" class="easyui-numberbox" style="width: 120px;height: 25px;"></div>
			教师名称:<div id="tb_teacherName" class="easyui-textbox" style="width:120px;height:25px;"></div>
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
	<div id="teacher_Dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width: 350px;padding: 10px;">
		<form id="teacher_form" method="post">
		<input type="hidden" name = "id"  id = "id"/>
			<table>
				<tr>
					<td width="100" align="right">教师编号</td>
					<td><div  id="teacherNum" name="teacherNum" class="easyui-numberbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">教师名称</td>
					<td><div  id="teacherName" name="teacherName" class="easyui-textbox" data-options="required:true" style="width: 150px;"></div></td>
				</tr>
				<tr>
					<td width="100" align="right">密　　码</td>
					<td><a href="#" id = "Tooltip"><input id="password" name="password" type="password" class="easyui-textbox pwd" style="width: 150px;" data-options="required:true" /></a></td>
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
					<td width="100" align="right">入职日期</td>
					<td><div  id="dateOfEnrollment" name="dateOfEnrollment" class="easyui-datebox datetime" data-options="required:true" style="width: 150px;"></div></td>
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
				<tr>
					<td width="100" align="right">用户角色:</td>
					<td><input type="text"  id="rolesId" name="roles" style="width: 150px;" class="easyui-combotree"
						data-options="url:'/sams/getAllRoles',multiple:true,required:true,panelHeight:133"/></td>	
				</tr>
			</table>
		</form>
	</div>
	
<script type="text/javascript">
	$(function name() {
		$("#teacherDatagrig").datagrid({
			url:'/sams/getTeacherByPage',
			striped:true,
			pagination:true,
			toolbar:"#tb",
			columns:[[
				{field:'ck',checkbox:true},	
				{field:'id',title:'id',width:150,align:'center'},
				{field:'teacherNum',title:'教师编号',width:150,align:'center'},
				{field:'teacherName',title:'姓名',width:150,align:'center'},
				{field:"status",title:"用户状态",width:100,align:'center',formatter:function(value,rows,index){
					//value:原本这个列的值，rows:包含本行所用数据列的数据值对象，index:行的索引
					if(value == 1){
						return "正常";
					}else{
						return "禁用";
					}
				}},
				{field:'sex',title:'性别',width:50,align:'center',formatter:function(value,rows,index){
					//value:原本这个列的值，rows:包含本行所用数据列的数据值对象，index:行的索引
					if(value == 1){
						return "女";
					}else{
						return "男";
					}
				}},
				{field:"dateOfEnrollment",title:"注册日期",width:200,align:'center'},
				{field:"roles",title:"用户角色",width:200,align:'center',formatter:function(value,rows,index){
					var roleNameStr="";
					if(value != null && value.length > 0){
						for(var i=0;i<value.length;i++){
							roleNameStr += "【" + value[i].roleName + "】";
						}
					}
					return roleNameStr;
				}}
			]]
		});	
		
		$("#search_btn").click(function(){
			var teacherNum = $("#tb_teacherNum").numberbox("getValue");
			var teacherName = $("#tb_teacherName").textbox("getValue");
			var status = $("#tb_status").combobox("getValue");
			var startDate = $("#tb_startDate").datebox("getValue");
			var endDate = $("#tb_endDate").datebox("getValue");
			var queryParams = $("#teacherDatagrig").datagrid("options").queryParams;
			queryParams.teacherNum = teacherNum;
			queryParams.teacherName = teacherName;
			queryParams.status = status;
			queryParams.startDate = startDate;
			queryParams.endDate = endDate;
			$("#teacherDatagrig").datagrid("load");
		});
		
		formatterDate = function (date) {
			var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
			var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
			+ (date.getMonth() + 1);
			var hor = date.getHours();
			var min = date.getMinutes();
			var sec = date.getSeconds();
			return date.getFullYear() + '-' + month + '-' + day+" "+hor+":"+min+":"+sec;
			};
		$(".datetime").datebox({
			value:formatterDate(new Date()),
			required:true
		});
		$(".datetime").datebox().datebox("calendar").calendar({
			validator:function(date){//小于当前日期
				var now = new Date();
				return date <= now;//符合选择的条件
			}
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
		
	});
	
	function openAdd(){
		$("#teacher_form").form("clear");
		$("#teacher_Dialog").dialog({
			title:'添加教师',
			closed:false,
			modal:true,
			onOpen:function(){
				$("#teacherName").textbox({disabled:false});
				$("#password").textbox({required:true});
				$("#teacherNum").textbox({disabled:false});
				$('#Tooltip').tooltip("destroy");
			},
			buttons:[
				{
					text:'确定',
					iconCls:'icon-ok',
					handler:teacher_add
				},
				{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#teacher_Dialog").dialog('close');
					}
				}],
		});
	}
	
	function openUpdate(){
		$("#teacher_form").form("clear");
		var rows = $("#teacherDatagrig").datagrid("getSelections");
		if(rows.length > 1){
			$.messager.alert("信息提示","一次只能修改一条数据!","info");
		}else if(rows.length == 0){
			$.messager.alert("信息提示","请选择您要修改的数据!","info");
		}else{
			 var teacher = rows[0];
			$("#teacher_form").form("load",teacher);
			$("#password").textbox("setValue",null);
			$("#teacher_Dialog").dialog({
				title:'修改用户',
				closed:false,
				modal:true,
				onOpen:function(){
					$("#teacherName").textbox({disabled:true});
					$("#teacherNum").textbox({disabled:true});
					$("#password").textbox({required:false});
					$('#Tooltip').tooltip({
				        position: 'right',
				        content: '<span style="color:#fff">为空时密码不修改</span>',
				        onShow: function(){
				    		$(this).tooltip('tip').css({
				    			backgroundColor: '#666',
				    			borderColor: '#666'
				    		});
				        }
				    });
					
				},
				buttons:[
					{
						text:'确定',
						iconCls:'icon-ok',
						handler:teacher_update
					},
					{
						text:'取消',
						iconCls:'icon-cancel',
						handler:function(){
							$("#teacher_Dialog").dialog('close');
						}
					}],
			}); 
		}
	}
	
	function remove(){
		var rows = $("#teacherDatagrig").datagrid("getSelections");
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
						url:'/sams/teacher_remove',
						method:'post',
						data:{
							ids:ids
						},
						success:function(data){ //"ok","error"
							if(data == "ok"){
								$("#teacherDatagrig").datagrid("reload");
							}else {
								$.messager.alert("信息提示","删除失败！","info");
							}
						}
					});
				}
			});	
	    }
}
	
	function teacher_add() {
		doSubmit("/sams/add_teacher");
	}
	
	function teacher_update(){
		doSubmit("/sams/update_teacher");
	}
	
	function doSubmit(param){
		//先拿到rolesId：1,2,3  --->  [{id:1},{id:2}]
		var rolesArr = $("#rolesId").val().split(",");
		var roleObjStr = "[";
		for(var i=0;i<rolesArr.length;i++){
			roleObjStr += "{id:"+rolesArr[i]+"},";
		}
		//[{id:1},{id:2},{id:3}]
		roleObjStr = roleObjStr.substring(0,roleObjStr.length-1) + "]";
		var roleObj = eval("(" + roleObjStr + ")");
		$.ajax({
			url:param,
			method:"post",
			contentType:"application/json;charset=utf-8",
			data:JSON.stringify({
				id:$("#id").val(),
				teacherName:$("#teacherName").val(),
				teacherNum:$("#teacherNum").val(),
				sex:$("#sex").val(),
				dateOfEnrollment:$("#dateOfEnrollment").val(),
				status:$("#status").val(),
				password:$("#password").val(),
				roles: roleObj
			}),
			beforeSend:function(){    
	            var isValid = $("#teacher_form").form('validate');
	            return isValid; // 返回false终止表单提交
	        },
			success:function(data){ //"ok","error"
				if(data == "ok"){
					$("#teacherDatagrig").datagrid("reload");
					$("#teacher_Dialog").dialog("close");		
				}else if (data == "info") {
					$.messager.alert("信息提示","用户名已存在！","info");
				}else{
					$.messager.alert("信息提示","提交失败！","info");
				}
			}
		});
	}
	
	function cancel() {
		$("#teacherDatagrig").datagrid("rejectChanges");
		
	}
	function reload() {
		$("#teacherDatagrig").datagrid("reload");
	}
</script>	
</body>
</html>
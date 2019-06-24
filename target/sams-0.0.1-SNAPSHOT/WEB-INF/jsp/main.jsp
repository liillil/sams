<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/easyui/themes/default/easyui.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/icon.css"></link>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>
<body class="easyui-layout">
	<div class="sams_header" data-options="region:'north',border:false,split:true,maxHeight:70,minHeight:70">
		<div class="header_left">
			<h1>学生成绩管理系统</h1>
		</div>
		<div class="header_right">
			<p><strong>${loginTeacher.teacherName}</strong>,欢迎您</p>
			<p><a href="#">网站首页</a>|<a href="#">帮助中心</a>|<a href="${pageContext.request.contextPath}/logout">安全退出</a></p>
		</div>
	</div>
	<div class="sams_west" data-options="region:'west',border:true,split:true,title:'导航菜单' ,maxWidth:160,minWidth:160" >
		<div class="easyui-accordion" data-options="border:false,multiple:true,selected:true,animate:false">
			<div title="学生管理" data-options="iconCls:'icon-advancedsettings'" style="padding: 5px;">
				<ul class="easyui-tree sidebar_tree">
					<li iconCls="icon-users"><a href="#" data-icon="icon-users" data-link="${pageContext.request.contextPath}/studentManager" iframe="1">学生信息管理</a></li>
					<li iconCls="icon-book"><a href="#" data-icon="icon-book" data-link="${pageContext.request.contextPath}/scoreManager" iframe="1">学生成绩管理</a></li>
				</ul>
			</div>
			<div title="课程管理" data-options="iconCls:'icon-advancedsettings'" style="padding: 5px;">
				<ul class="easyui-tree sidebar_tree">
					<li iconCls="icon-book"><a href="#" data-icon="icon-book" data-link="${pageContext.request.contextPath}/coursesManager" iframe="1">课程信息</a></li>
					<li iconCls="icon-book"><a href="#" data-icon="icon-book" data-link="${pageContext.request.contextPath}/majorCoursesManager" iframe="1">专业课程信息</a></li>
				</ul>
			</div>
			<div title="教师管理" data-options="iconCls:'icon-advancedsettings'" style="padding: 5px;">
				<ul class="easyui-tree sidebar_tree">
					<li iconCls="icon-user-group"><a href="#" data-icon="icon-user-group" data-link="${pageContext.request.contextPath}/teacherManager" iframe="1">教师信息管理</a></li>
					<li iconCls="icon-wrench"><a href="#" data-icon="icon-wrench" data-link="${pageContext.request.contextPath}/teacherCoursesManager" iframe="1">教师任课信息</a></li>
				</ul>
			</div>
			<div title="权限管理" data-options="iconCls:'icon-advancedsettings'" style="padding: 5px;">
				<ul class="easyui-tree sidebar_tree">
					<li iconCls="icon-user-group"><a href="#" data-icon="icon-user-group" data-link="${pageContext.request.contextPath}/roleManager" iframe="1">角色管理</a></li>
					<li iconCls="icon-book"><a href="#" data-icon="icon-book" data-link="${pageContext.request.contextPath}/permissionManager" iframe="1">资源管理</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="ssj_easyui_main" data-options="region:'center'">
		<div id="sams_tabs" class="easyui-tabs" data-options="border:false,fit:true">
			
		</div>
	</div>
</body>
</html>
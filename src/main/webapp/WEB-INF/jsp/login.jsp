<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/login.css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery.min.js"></script>
</head>
<body>
	<div class="login">
		<div class="pic">
		</div>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div class="user">
				<input type="text" name="teacherNum" placeholder="请输入账户">
				<div class="icon1"></div>
			</div>
			<div class="psd">
				<input type="password" name="password" placeholder="请输入密码">
				<div class="icon2"></div>
			</div>
			<!-- <div class="check">
				<label><input checked name="user" id="student" type="radio">&nbsp;学生</label>
				<label><input name="user" id="teacher" type="radio">&nbsp;老师</label>1
				<label><input name="user" id="admin" type="radio">&nbsp;管理员</label>
			</div> -->
			<input type="submit" value="登录">
		</form>
	</div>
</body>
</html>
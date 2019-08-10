<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>注册</title>
<script src="js/jquery-2.0.0.min.js"></script>
<script src="js/jsencrypt.min.js"></script>
</head>
<body>
	<form id="register" class="form1" action="user/regist" method="post">
		<div class="one">
			<div class="user style">
				<span>
					<i>*</i>
					用户名
				</span>
				<input class="border-box admin" type="text" name="username" placeholder="请输入您的用户名" />
				<label>请输入用户名</label>
			</div>
			<div class="password style">
				<span>
					<i>*</i>
					登录密码
				</span>
				<input class="passwordone border-box" type="password" name="password" placeholder="请输入密码" />
				<label>请输入密码</label>
			</div>
			<div class="password style">
				<span>
					<i>*</i>
					确认密码
				</span>
				<input class="passwordtwo border-box" type="password" placeholder="请输入密码" />
				<label>请再次输入密码</label>
			</div>
		</div>
		<div class="sublimeButton stle">
			<span>
				<i></i>
			</span>
			<input id="registbutton" class="border-box" type="submit" value="注&nbsp;&nbsp;&nbsp;册" />
			<input id="registreg" name="registreg" type="hidden" value="1" show="true" />
		</div>
	</form>
</body>
</html>

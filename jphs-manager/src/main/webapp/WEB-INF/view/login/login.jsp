<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<body style="background: #fff;">
<div class="login_bg">
	<img src="/static/img/login/icon.png" />
</div>
<form class="form-horizontal" style="margin-top: 150px;" id="loginForm"
	method="post" data-role="validate-form" action="/login.jhtml">
	<div class="public-cell public-cell-about login_input">
		<img src="/static/img/login/zhanghao.png" />
		<div class="public_primary">
			<input type="text" id="userName" name="userName" placeholder="管理员名称" />
		</div>
	</div>
	<div class="public-cell public-cell-about login_input">
		<img src="/static/img/login/mima.png" />
		<div class="public_primary">
			<input type="password" id="password" name="password"
				placeholder="管理员密码" />
		</div>
	</div>
	<div class="login_foot">
		<button type="submit" id="save" class="login_btn">登录</button>
	</div>
	<div class="public-cell public-cell-about login_input">
		<span >sdaf</span>
	</div>
</form></body>
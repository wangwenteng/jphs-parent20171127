<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="marage_right_content">
	<form class="form-horizontal" style="margin-top: 150px;" id="loginForm"
		method="post" data-role="validate-form" action="/login.jhtml">
		<div class="event_handle_body">
			<div class="form-group">
				<label class="control-label col-md-3" for="input01">用户名：</label>
				<div class="controls col-md-6">
					<input type="text" id="userName" name="userName"
						placeholder="请输入手机号或者公司邮箱" class="form-control" value="13800000000">
				</div>
			</div>
			<div class="form-group">

				<!-- 文本输入 -->
				<label class="control-label col-md-3" for="input01">密码：</label>
				<div class="controls col-md-6">
					<input type="password" style="width: 320px;" placeholder="请输入密码"
						id="password" name="password" class="form-control" value="88888888">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3" for="input01"></label>
				<div class="controls col-md-6">
					<span >${message }</span>
				</div>
			</div>
			<div class="clearfix" style="margin-left: 290px; margin-top: 10px;">
				<button type="submit" id="save"
					class="public-info public_btn public_btn_left"
					style="font-size: 20px;" data-role="save-btn">登录</button>
				<!-- <input type="button" class="public-info public_btn public_btn_left"
					name="button1" id="button1" value="返回" onclick="history.go(-1)"> -->
			</div>
		</div>
	</form>
</div>

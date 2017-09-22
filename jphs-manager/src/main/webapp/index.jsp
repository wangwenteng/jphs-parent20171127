<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<html>
<head>
<title>后台管理系统</title>
<link href="../static/css/build/build_standalone.less" rel="stylesheet" type="text/css" />
<link href="../static/css/build/build.less" rel="stylesheet" type="text/css" />
<link href="../static/css/less/datetimepicker.less" rel="stylesheet" type="text/css" />
<script src="../static/js/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="../static/css/base.css">
<link href="../static/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css" />
<link href="../static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../static/css/mystyle.css">
<link rel="stylesheet" href="../static/css/min.css">
<link rel="stylesheet" type="text/css" href="../static/EasyUI/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="../static/EasyUI/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="../static/EasyUI/themes/icon.css">
<link href="../static/css/fileinput.css" rel="stylesheet" type="text/css" />

<script src="../static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../static/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="../static/js/mui.min.js" type="text/javascript"></script>
<script src="../static/js/echarts-all.js" type="text/javascript"></script>
<script src="../static/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="../static/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>

<script src="/static/js/fileinput.js" type="text/javascript"></script>
<script src="/static/js/fileinput_locale_zh.js" type="text/javascript"></script>
<script src="/static/js/page.js" type="text/javascript"></script>
<script type="text/javascript" src="/static/EasyUI/jquery.easyui.min.js"></script>
<script src="/static/js/btjs/distpicker.data.js"></script>
<script src="/static/js/btjs/distpicker.js"></script>
<script src="/static/js/btjs/main.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script src="/static/js/jquery.metadata.js"></script>
<script src="/static/js/commons.js" type="text/javascript"></script>
<script src="/static/js/login/login.js" type="text/javascript"></script>
</head>
<body style="background: #fff;">
	<div class="login_bg">
		<img src="/static/img/login/icon.png" />
	</div>
	<form class="form-horizontal" style="margin-top: 50px;" id="loginForm"
		method="post" data-role="validate-form" action="/login.jhtml">
		<div class="public-cell public-cell-about login_input">
			<img src="/static/img/login/zhanghao.png" />
			<div class="public_primary">
				<input type="text" id="userName" name="userName" placeholder="企业邮箱或手机号码" />
			</div>
		</div>
		<div class="public-cell public-cell-about login_input">
			<img src="/static/img/login/mima.png" />
			<div class="public_primary">
				<input type="password" id="password" name="password"
					placeholder="" />
			</div>
		</div>
		<div class="login_foot">
			<button type="submit" id="save" class="login_btn">登录</button>
		</div>
		<div style="color: red;text-align: center;font-size: 18px" class="login_foot">
			${message }
		</div>
		
	</form>
</body>
</html>

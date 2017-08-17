<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>金牌护士后台管理系统-<tiles:insertAttribute name="title" ignore="true" /></title>
<link href="/static/css/build/build_standalone.less" rel="stylesheet" type="text/css" />
<link href="/static/css/build/build.less" rel="stylesheet" type="text/css" />
<link href="/static/css/less/datetimepicker.less" rel="stylesheet" type="text/css" />
<script src="/static/js/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="/static/css/base.css">
<link href="/static/css/bootstrap-datetimepicker.css" rel="stylesheet" type="text/css" />
<link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/static/css/mystyle.css">
<link rel="stylesheet" type="text/css" href="/static/EasyUI/themes/metro/easyui.css">
<link rel="stylesheet" type="text/css" href="/static/EasyUI/themes/mobile.css">
<link rel="stylesheet" type="text/css" href="/static/EasyUI/themes/icon.css">
<link href="/static/css/fileinput.css" rel="stylesheet" type="text/css" />
<tilesx:useAttribute id="css" name="css" classname="java.util.List" />
<c:forEach var="item" items="${css}">
	<link href="<c:out value='${item}'></c:out>" rel="stylesheet"
		type="text/css" />
</c:forEach>
<script src="/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/static/js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="/static/js/mui.min.js" type="text/javascript"></script>
<script src="/static/js/echarts-all.js" type="text/javascript"></script>
<script src="/static/js/bootstrap-datetimepicker.js" type="text/javascript"></script>
<script src="/static/js/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

<script src="/static/js/fileinput.js" type="text/javascript"></script>
<script src="/static/js/fileinput_locale_zh.js" type="text/javascript"></script>
<script src="/static/EasyUI/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="/static/js/btjs/distpicker.data.js"></script>
<script src="/static/js/btjs/distpicker.js"></script>
<script src="/static/js/btjs/main.js"></script>
<script src="/static/js/jquery.validate.js"></script>
<script src="/static/js/jquery.metadata.js"></script>
<script src="/static/js/commons.js" type="text/javascript"></script>
<tilesx:useAttribute id="scripts" name="scripts"
	classname="java.util.List" />
<c:forEach var="item" items="${scripts}">
	<script src='<c:out value="${item}"></c:out>' type="text/javascript"></script>
</c:forEach>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<section class="manager_container clearfix"> 
		<tiles:insertAttribute name="left" />
		<div class="common_right">
			<tiles:insertAttribute name="body" />
			<div class="clearfix"></div>
		</div>
	</section>
	<script>
			$(".manager_header ul li").click(function() {
				$(".manager_header ul li").removeClass("active");
				$(this).addClass("active");
			})

			$(".common_left dt").click(function() {			
				$(this).toggleClass("public_left_active");
				$(this).find("i").toggleClass("public-horn-45");
				$(this).find("span").toggleClass("common_icon_active");
				$(this).next(".public_left_list").slideToggle();

			})
			$(".public_left_list").find("dd").click(function() {				
				$(".public_left_list").find("dd").removeClass("public_left_active");
				$(this).toggleClass("public_left_active");
			})
	</script>
	<tiles:insertAttribute name="footer" />
</body>
</html>

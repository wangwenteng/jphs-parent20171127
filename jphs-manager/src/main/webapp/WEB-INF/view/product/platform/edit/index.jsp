﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />产品管理 <i
		class="public1-horn-45"></i> 平台管理 <i class="public1-horn-45"></i>${platform.id == null?'添加平台':'编辑平台'}
</div>
<form class="form-horizontal edit_form" id="platformForm" method="post"
	data-role="validate-form" action="/platform/insert.jhtml">
	<div class="marage_right_content">
		<div class="clearfix">
			<button type="submit" id="save"
				class="public-info public_btn public_btn_left" data-role="save-btn">保存</button>
			<input type="button" class="public-info public_btn public_btn_left"
				name="button1" id="button1" value="返回" onclick="history.go(-1)">
		</div>
		<hr class="mt-5" />
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</form>

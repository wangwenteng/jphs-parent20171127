﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />活动管理<i class="public1-horn-45"></i>活动管理<i class="public1-horn-45"></i>优惠券管理
	<i class="public1-horn-45"></i>优惠券列表
</div>
<div class="marage_right_title">
	<jsp:include page="search.jsp"></jsp:include>
</div>

<div class="marage_right_content">
	<!-- 左对齐按钮 -->
	<jphs:hasPermission url="/voucher/redirectAddPage.jhtml">
		<button id="redirectAddPage" type="button" class="public-info public_btn">新增优惠券</button>
	</jphs:hasPermission>
	<!-- 右对齐按钮 -->
	<jsp:include page="list.jsp"></jsp:include>

</div>
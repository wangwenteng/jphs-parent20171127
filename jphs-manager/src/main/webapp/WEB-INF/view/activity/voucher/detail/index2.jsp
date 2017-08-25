<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
		<img src="/static/images/yousanjiaox.png" />活动管理<i class="public1-horn-45"></i>活动管理<i class="public1-horn-45"></i>优惠券管理
	<i class="public1-horn-45"></i>添加使用用户
</div>
<div class="marage_right_title" style="display:none; ">
	<jsp:include page="search2.jsp"></jsp:include>
</div>

<form class="form-horizontal add_body" id="platformForm" method="post"
	data-role="validate-form" >
	<div class="marage_right_content">
		<div class="clearfix">
			<input type="button" class="public-info public_btn public_btn_left" name="button1" id="button1"
				value="返回" onclick="history.go(-1)">
		</div>
			<hr class="mt-5" />
		<jsp:include page="addUser.jsp"></jsp:include>
	</div>
</form>


<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
		<img src="/static/images/yousanjiaox.png" />护士管理<i
		class="public1-horn-45"></i>资质认证 <i class="public1-horn-45"></i>资质认证审核
</div>
<div class="marage_right_content">
	<div class="clearfix">
		<jphs:hasPermission url="/audit/insert.jhtml">
			<button class="public-info public_btn public_btn_left"
				data-toggle="modal" data-target="#myModal">审核</button>
		</jphs:hasPermission>
		<input type="button" class="public-info public_btn public_btn_left"
			name="button1" id="button1" value="返回" onclick="history.go(-1)">
	</div>
	<hr class="mt-5" />
	<div class="mr-50 ml-50 pb-20">
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</div>
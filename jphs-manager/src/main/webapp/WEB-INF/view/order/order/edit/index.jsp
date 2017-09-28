<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />订单管理<i
		class="public1-horn-45"></i> 订单详情<i class="public1-horn-45"></i>
		<c:choose>
			<c:when test="${order.acceptUserId!=null&& order.acceptUserId!=''}">修改接单人
			</c:when>
			<c:otherwise>派单</c:otherwise>
		</c:choose> 
</div>
<div class="marage_right_title">
	<jsp:include page="search.jsp"></jsp:include>
</div>
<div class="marage_right_content">
	<input type="button" class="public-info public_btn public_btn_left"
		name="button1" id="button1" value="返回" onclick="history.go(-1)">
	<jsp:include page="form.jsp"></jsp:include>
</div>

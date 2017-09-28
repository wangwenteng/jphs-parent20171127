<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 10px 10px 10px;">
	<div class="common_right_title">
		<img src="/static/images/yousanjiaox.png" />订单管理 <i
			class="public1-horn-45"></i> 订单列表<i class="public1-horn-45"></i>订单详情
	</div>
	<div class="marage_right_content">
	<div class="clearfix">
	
	
	<!-- <button class="public-info public_btn public_btn_left" >打印</button> -->
	<c:if test="${order.schedule>0&&order.schedule<5 }">
		<button class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#phoneModal">修改患者联系电话</button>
		<button class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#timeModal">修改预约时间</button>
		<button class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#addressModal">修改服务地址</button>
		<c:choose>
			<c:when test="${order.acceptUserId!=null&& order.acceptUserId!=''}">
				<button class="public-info public_btn public_btn_center" onclick="redirectUpdatePage('${order.id}')">修改接单人</button>
			</c:when>
			<c:otherwise>
				<button class="public-info public_btn public_btn_center" onclick="redirectUpdatePage('${order.id}')">派单</button>
			</c:otherwise>
		</c:choose>
	</c:if>
	<c:if test="${order.schedule==6&&transactionUser!=null&&refund==null }">
		<button class="public-info public_btn public_btn_center" data-toggle="modal" data-target="#amountModal">退款</button>
	</c:if>
		<input type="button" class="public-info public_btn public_btn_left"
				name="button1" id="button1" value="返回" onclick="history.go(-1)" />
	</div>
	<hr class="mt-5" />
	<div class="mr-50 ml-50 pb-20" >
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</div>

<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="width:100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th>昵称</th>
			<th>手机号</th>
			<th>订单编号</th>
			<th>金额</th>
			<th>积分</th>
			<th>操作</th>
			<th>支付方式</th>
			<th>交易号</th>
			<th>交易时间</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.name}" /></td>
						<td><c:out value="${e.phone}" /></td>
						<td><c:out value="${e.orderId}" /></td>
						<td>
							<c:if test="${e.amount == null}">0.0</c:if>
							<c:if test="${e.amount != null}">${e.amount}</c:if>
						</td>
						<td><c:out value="${e.score}" /></td>
						<td><c:if test="${e.operate == 2}">充值</c:if> <c:if
								test="${e.operate == 3}">消费</c:if></td>
						<td><c:if test="${e.payType == 1}">支付宝</c:if> <c:if
								test="${e.payType == 2}">微信</c:if> <c:if
								test="${e.payType == 3}">余额宝</c:if> <c:if
								test="${e.payType == 4}">银联</c:if> <c:if
								test="${e.payType == 5}">vip卡支付</c:if></td>
						<td><c:out value="${e.outTradeNo}" /></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="20" align="center">没有可显示的记录。</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<div class="page">
	<jphs:page pageInfos="${pageInfo}"></jphs:page>
</div>
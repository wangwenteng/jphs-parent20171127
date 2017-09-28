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
			<th>订单编号</th>
			<th>昵称</th>
			<th>手机号</th>
			<th>服务套餐</th>
			<th>预约时间</th>
			<th>接单护士</th>
			<th>状态</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.orderNo}" /></td>
						<td><c:out value="${e.userName}" /></td>
						<td><c:out value="${e.phone}" /></td>
						<td><c:out value="${e.title}" /></td>
						<td><fmt:formatDate value="${e.appointmentTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><c:out value="${e.nurseName}" /></td>
						<td><c:if test="${e.schedule == 0}">待支付</c:if> <c:if
								test="${e.schedule == 1}">待接单</c:if> <c:if
								test="${e.schedule == 2}">已接单</c:if> <c:if
								test="${e.schedule == 3}">执行中</c:if> <c:if
								test="${e.schedule == 4}">待确定</c:if> <c:if
								test="${e.schedule == 5}">已完成</c:if> <c:if
								test="${e.schedule == 6}">已取消</c:if> <c:if
								test="${e.schedule == 7}">申诉中</c:if></td>
						<td>
							<jphs:hasPermission url="/order/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')"> <img
									src="/static/images/chakan.png">
							</a>
							</jphs:hasPermission>
						 </td>
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
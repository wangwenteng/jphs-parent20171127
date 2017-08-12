<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="text-center">
	<thead>
		<tr>
			<th width="30"></th>
			<th>昵称</th>
			<th>手机号</th>
			<th>优惠券编号</th>
			<th>优惠券类型</th>
			<th>优惠券金额</th>
			<th>获取时间</th>
			<th>到期时间</th>
			<th>使用时间</th>
			<th>状态</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.creatorName}" /></td>
						<td><c:out value="${e.phone}" /></td>
						<td><c:out value="${e.id}" /></td>
						<td>
							<c:if test="${e.type ==1 }">现金券</c:if>
							<c:if test="${e.type ==2 }">满减券</c:if>
							<c:if test="${e.type ==3 }">折扣券</c:if>
							</td>
						<td><c:out value="${e.amount}" /></td>
						<td><fmt:formatDate value="${e.startTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${e.endTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${e.useTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td>
							<c:if test="${e.status ==0 }"><span style="color: #F0BB1C;">未使用</span></c:if>
							<c:if test="${e.status ==1 }"><span style="color: #34BC2C;">已使用</span></c:if>
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

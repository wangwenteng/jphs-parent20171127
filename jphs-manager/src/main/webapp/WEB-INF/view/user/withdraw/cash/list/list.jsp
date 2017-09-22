<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="width: 100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th>提现金额</th>
			<th>账户姓名</th>
			<th>支付宝账号</th>
			<th>审核时间</th>
			<th>备注 驳回时间</th>
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
						<td><c:out value="${e.amount}" /></td>
						<td><c:out value="${e.accountName}" /></td>
						<td><c:out value="${e.alipayAccount}" /></td>
						<td><fmt:formatDate value="${e.auditTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><c:out value="${e.remark}" /></td>
						<td>
								<c:if test="${e.status==1}"><span style="color: #34BC2C;">已提现</span></c:if>
								<c:if test="${e.status==0}"><span style="color: #F0BB1C;">待审核</span></c:if>
								<c:if test="${e.status==-1}"><span >驳回</span></c:if>
						</td>
						<td><jphs:hasPermission url="/withdraw/cash/detail.jhtml">
								<c:if test="${e.status!=1}">
									<a onclick="redirectDetailPage('${e.id}')" title="详情"> <img
										src="/static/images/chakan.png">
									</a>
								</c:if>
							</jphs:hasPermission> <jphs:hasPermission url="/withdraw/cash/redirectUpdate.jhtml">
								<c:if test="${e.status==0}">
									<a onclick="redirectUpdatePage('${e.id}')" title="审核"> <img
										src="/static/img/examine.png">
									</a>
								</c:if>
							</jphs:hasPermission></td>
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
</div>

</div>
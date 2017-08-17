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
			<th>会员id</th>
			<th>订单id</th>
			<th>康复建议</th>
			<th>评估内容</th>
			<th>疾病原因</th>
			<th>身体症状</th>
			<th>现病史</th>
			<th>疾病史</th>
			<th>用药提醒</th>
			<th>复诊时间</th>
			<th>注意事项</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.userId}" /></td>
						<td><c:out value="${e.orderId}" /></td>
						<td><c:out value="${e.advise}" /></td>
						<td><c:out value="${e.evaluateContent}" /></td>
						<td><c:out value="${e.diseaseCauses}" /></td>
						<td><c:out value="${e.physicalSymptoms}" /></td>
						<td><c:out value="${e.presentIllness}" /></td>
						<td><c:out value="${e.diseasesHistory}" /></td>
						<td><c:out value="${e.medicationRemind}" /></td>
						<td><fmt:formatDate value="${e.appointmentTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><c:out value="${e.notes}" /></td>
						<td><a onclick="redirectDetailPage('${e.id}')"> <img
								src="/static/images/chakan.png">
						</a> <a onclick="redirectUpdatePage('${e.id}')"> <img
								src="/static/images/xiugai.png">
						</a> <a onclick="deleteById('${e.id}')"> <img
								src="/static/images/shanchu.png">
						</a></td>
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

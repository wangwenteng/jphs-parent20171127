﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
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
			<th>职称类型名称</th>
			<th>创建人姓名</th>
			<th>创建时间</th>
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
						<td><c:out value="${e.name}" /></td>
						<td><c:out value="${e.creatorName}" /></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td><c:if test="${e.status==0}">使用中</c:if> <c:if
								test="${e.status==-1}">已停用</c:if></td>
						<td><jphs:hasPermission url="/jobtitle/type/detail.jhtml">
								<a onclick="redirectUpdatePage('${e.id}')"> <img
									src="/static/images/xiugai.png">
								</a>
							</jphs:hasPermission> <jphs:hasPermission url="/jobtitle/type/delete.jhtml">
								<a onclick="deleteById('${e.id}')"> <img
									src="/static/images/shanchu.png">
								</a>
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

﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center" style="width:100%;">
	<thead>
		<tr >
			<th width="30"></th>
			<th>姓名</th>
			<th>手机号</th>
			<th>备注</th>
			<th>咨询时间</th>
			<th>回访客服</th>
			<th>回访时间</th>
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
							<td><c:out value="${e.name}"/></td>
							<td><c:out value="${e.phone}"/></td>
							<td>
								<c:if test="${fn:length(e.remark)>10 }">
								${fn:substring(e.remark, 0, 10)}... 
								</c:if>
								<c:if test="${fn:length(e.remark)<10 }">
									${e.remark} 
								</c:if>
							</td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><c:out value="${e.visitor}"/></td>
							<td><fmt:formatDate value="${e.visitTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td>
								<c:if test="${e.status==0 }"><span style="color: #F0BB1C;">待回访</span></c:if>
								<c:if test="${e.status==1 }"><span style="color: #34BC2C;">已回访</span></c:if>
							</td>
							<td>
								<jphs:hasPermission url="/custom/service/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
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
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>

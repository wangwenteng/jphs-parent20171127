<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
	<thead>
		<tr >
			<th width="30"></th>
			<th>订单编号</th>
			<th>商品名称</th>
			<th>接单护士</th>
			<th>评价星级</th>
			<th>评价内容</th>
			<th>评价时间</th>
			<th>状态</th>
			<th width="130">审核</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.orderId}"/></td>
							<td><c:out value="${e.title}"/></td>
							<td><c:out value="${e.creatorName}"/></td>
							<td><c:out value="${e.level}"/></td>
							<td title="${e.content} ">
								<c:if test="${fn:length(e.content)>10 }">
								${fn:substring(e.content, 0, 10)}... 
								</c:if>
								<c:if test="${fn:length(e.content)<10 }">
									${e.content} 
								</c:if>
							</td>
							<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
							<td> 
								<c:if test="${e.status ==-1 }">审核不通过</c:if>
								<c:if test="${e.status ==0 }">未审核</c:if>
								<c:if test="${e.status ==1 }">审核通过</c:if>
							</td>
							<td>
							<c:if test="${e.status ==0 }">
								<a onclick="UpdateStatus('${e.id}','-1')">
								审核不通过
							</a>								
							<a onclick="UpdateStatus('${e.id}','1')">
								审核通过
							</a>
							</c:if>
								<c:if test="${e.status !=0 }">已审核</c:if>
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
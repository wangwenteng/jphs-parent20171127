<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center" style="width:100%;">
	<thead>
		<tr >
			<th width="30"></th>
			<th>订单id</th>
			<th>科室id</th>
			<th>医院</th>
			<th>地址-省</th>
			<th>详细地址</th>
			<th>是否有药品</th>
			<th>是否有工具</th>
			<th>通用字段</th>
			<th>通用字段</th>
			<th></th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.orderId}"/></td>
							<td><c:out value="${e.departmentId}"/></td>
							<td><c:out value="${e.hospital}"/></td>
							<td><c:out value="${e.address}"/></td>
							<td><c:out value="${e.detailAddress}"/></td>
							<td><c:out value="${e.drug}"/></td>
							<td><c:out value="${e.tool}"/></td>
							<td><c:out value="${e.publicName}"/></td>
							<td><c:out value="${e.publicContent}"/></td>
							<td><c:out value="${e.remarks}"/></td>
							<td>
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							<a onclick="redirectUpdatePage('${e.id}')">
								<img  src="/static/images/xiugai.png">
							</a>									
							<a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a>
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
	</div>
	
</div>
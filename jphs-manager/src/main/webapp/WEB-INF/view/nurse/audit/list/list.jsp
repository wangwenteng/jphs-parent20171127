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
			<th>职称类型</th>
			<th>资质名称</th>
			<th>姓名</th>
			<th>身份证号</th>
			<th>联系方式</th>
			<th>在职医院</th>
			<th>所属科室</th>
			<th>认证时间</th>
			<th>状态</th>
			<th width="30">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.jobtitleTypeName}" /></td>
						<td><c:out value="${e.jobtitleName}" /></td>
						<td><c:out value="${e.name}" /></td>
						<td><c:out value="${e.sfz}" /></td>
						<td><c:out value="${e.phone}" /></td>
						<%-- <td><c:if test="${fn:length(e.sfz)>0 }">
								<c:set var="idcard" value="${fn:substring(e.sfz,6,10) }" />
								<jsp:useBean id="nowDate" class="java.util.Date" />
								<fmt:formatDate var="nowStr" value="${nowDate}" pattern="yyyy" />
								<c:if test="${nowStr-idcard<=0 }">1</c:if>
								<c:if test="${nowStr-idcard>0 }">${nowStr-idcard}</c:if>
							</c:if></td>
						<td><fmt:formatDate var="str" value="${e.workYears}"
								pattern="yyyy" /> <fmt:formatDate var="nowStr"
								value="${nowDate}" pattern="yyyy" /> <c:if
								test="${nowStr-str<=0 }">1</c:if> <c:if test="${nowStr-str>0 }">${nowStr-str}</c:if>
						</td> --%>
						<td <c:if test="${fn:length(e.hospital)>10 }">title="${e.hospital}"</c:if> >
							<c:if test="${fn:length(e.hospital)>10 }">
								${fn:substring(e.hospital,0,10) }
							</c:if>
							<c:if test="${fn:length(e.hospital)<=10 }">
								${e.hospital} 
							</c:if>
						
						<%-- <td><c:out value="${e.hospital}" /></td> --%>
						<td><c:forEach items="${department}" var="d">
								<c:if test="${e.departmentId==d.id}">${d.name }</c:if>
							</c:forEach></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yyyy-MM-dd" /></td>
						<td><c:if test="${e.status==0 }"><span style="color: #F0BB1C;">待审核</span></c:if>
							<c:if test="${e.status==1 }"><span style="color: #34BC2C;">已审核</span></c:if>
						</td>
						<td><jphs:hasPermission url="/audit/redirectUpdate.jhtml">
								<c:if test="${e.status==0 }">
									<a onclick="redirectUpdatePage('${e.id}','${e.creatorId }')" title="审核"> <img
									src="/static/img/examine.png">
									</a>
								</c:if>
							</jphs:hasPermission>
							<jphs:hasPermission url="/audit/detail.jhtml">
								<c:if test="${e.status==1 }">
									<a onclick="redirectDetailPage('${e.id}','${e.creatorId }')" title="详情"> <img
									src="/static/images/chakan.png">
									</a>
								</c:if>
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
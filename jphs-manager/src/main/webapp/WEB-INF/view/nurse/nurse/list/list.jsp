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
			<th>姓名</th>
			<th>联系电话</th>
			<th>年龄</th>
			<th>工龄</th>
			<th>在职医院</th>
			<th>所属科室</th>
			<th>注册时间</th>
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
						<td><c:out value="${e.user.name}" /></td>
						<td><c:out value="${e.user.phone}" /></td>
						<td><c:if test="${fn:length(e.sfz)>0 }">
								<c:set var="idcard" value="${fn:substring(e.sfz,6,10) }" />
								<jsp:useBean id="nowDate" class="java.util.Date" />
								<fmt:formatDate var="nowStr" value="${nowDate}" pattern="yyyy" />
								<c:if test="${nowStr-idcard==0 }">1</c:if>
								<c:if test="${nowStr-idcard!=0 }">${nowStr-idcard}</c:if>
							</c:if></td>
						<td><fmt:formatDate var="str" value="${e.workYears}"
								pattern="yyyy" /> <fmt:formatDate var="nowStr"
								value="${nowDate}" pattern="yyyy" /> <c:if
								test="${nowStr-str==0 }">1</c:if> <c:if test="${nowStr-str!=0 }">${nowStr-str}</c:if>
						</td>
						<td><c:out value="${e.hospital}" /></td>
						<td><c:forEach items="${department}" var="d">
								<c:if test="${e.departmentId==d.id}">${d.name }</c:if>
							</c:forEach></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yyyy-MM-dd" /></td>
						<td><c:if test="${e.status==1 }">已认证</c:if></td>
						<td><jphs:hasPermission url="/nurse/detail.jhtml">
								<a onclick="redirectDetailPage('${e.id}')" title="详情"> <img
									src="/static/images/chakan.png">
								</a>
							</jphs:hasPermission> <jphs:hasPermission url="/nurse/redirectUpdate.jhtml">
								<a onclick="redirectUpdatePage('${e.id}')" title="编辑"> <img
									src="/static/images/xiugai.png">
								</a>
							</jphs:hasPermission> <%-- <a onclick="deleteById('${e.id}')" title="删除"> <img
								src="/static/images/shanchu.png">
						</a> --%></td>
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
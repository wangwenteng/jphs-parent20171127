<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<%
	int i = 1;
%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="text-center">
	<thead>
		<tr>
			<th width="30"></th>
			<th>职称类型</th>
			<th>职称名称</th>
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
					<c:choose>
						<c:when test="${fn:length(e.jobtitle) >0}">
							<c:forEach items="${e.jobtitle}" var="f" varStatus="t">
								<tr class="bg_list_body">
									<td width="30"><%=i++%></td>
									<td><c:out value="${e.name}" /></td>
									<td><c:out value="${f.name}" /></td>
									<td><c:out value="${f.creatorName}" /></td>
									<td><fmt:formatDate value="${f.createTime}"
											pattern="yy-MM-dd HH:mm" /></td>
									<td><c:if test="${f.status==1}"><span style="color: #34BC2C;">使用中</span></c:if> <c:if
											test="${f.status==0}"><span style="color: #F0BB1C;">待开启</span></c:if></td>
									<td><jphs:hasPermission url="/jobtitle/delete.jhtml">
											<c:if test="${f.status == 1}">
												<a onclick="deleteById('${f.id}','0')" title="取消发布"> <img
													style="width: 20px; height: 20px;"
													src="/static/images/blockup.png">
												</a>
											</c:if>
											<c:if test="${f.status == 0}">
												<a onclick="deleteById('${f.id}','1')" title="发布"> <img
													style="width: 20px; height: 20px;"
													src="/static/images/startup.png">
												</a>
											</c:if>
										</jphs:hasPermission> <jphs:hasPermission url="/jobtitle/redirectUpdate.jhtml">
											<a onclick="redirectUpdatePage('${f.id}')" title="编辑"> <img
												src="/static/images/xiugai.png">
											</a>
										</jphs:hasPermission> <jphs:hasPermission url="/jobtitle/delete.jhtml">
											<a onclick="deleteById('${f.id}','-1')" title="删除"> <img
												src="/static/images/shanchu.png">
											</a>
										</jphs:hasPermission></td>
								</tr>
							</c:forEach>
						</c:when>
					</c:choose>
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
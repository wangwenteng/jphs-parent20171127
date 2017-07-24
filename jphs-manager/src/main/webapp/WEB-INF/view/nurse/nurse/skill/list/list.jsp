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
			<td><input type="checkbox" id="all"
							  /></td>
			<th width="30"></th>
			<th>护士姓名</th>
			<th>技能名称</th>
			<th>发布价格</th>
			<th>平台价格</th>
			<th>服务区域</th>
			<th>空闲时间</th>
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
						<td><input type="checkbox" id="skillId"
							name="skillId" value="${e.id }" /></td>
						<td width="30">${s.index+1}</td>
						<td><c:out value="${e.creatorName}" /></td>
						<td><c:out value="${e.skill.name}" /></td>
						<td><c:out value="${e.price}" /></td>
						<td><c:out value="${e.skill.amount}" /></td>
						<td><c:out value="${e.location.content}" /></td>
						<td><c:out value="${e.freeCycle}" /><!-- &nbsp;&nbsp; -->（<c:out value="${e.leisureTime}" />）</td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
						<td>
								<c:if test="${e.status==0}">开启</c:if>
								<c:if test="${e.status==-1}">关闭</c:if>
						</td>
						<td>
							<jphs:hasPermission url="/nurse/skill/detail.jhtml">
								<a onclick="redirectDetailPage('${e.id}')" title="详情"> <img
									src="/static/images/chakan.png">
								</a> 
							</jphs:hasPermission>
							<jphs:hasPermission url="/nurse/skill/redirectUpdate.jhtml">
								<a onclick="redirectUpdatePage('${e.id}')" title="编辑"> <img
										src="/static/images/xiugai.png">
								</a> 
							</jphs:hasPermission>
							<jphs:hasPermission url="/nurse/skill/delete.jhtml">
								<a onclick="deleteById('${e.id}')" title="删除"> <img
										src="/static/images/shanchu.png">
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
	<jphs:page pageInfos="${pageInfo}"></jphs:page>
</div>

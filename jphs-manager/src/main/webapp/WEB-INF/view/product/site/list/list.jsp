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
			<th>站点名称</th>
			<th>服务器地址</th>
			
			<!-- <th>站点服务区域</th> -->
			<th>备注</th>
			<th width="100">创建人</th>
			<th width="118">创建时间</th>
			<th width="60">状态</th>
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
						<td><c:out value="${e.url}" /></td>
						<td><c:out value="${e.remark}" /></td>
						
						<td><c:out value="${e.creatorName}"/></td>
							<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
								<td><c:if test="${e.status == 1}">
									<span style="color: #34BC2C;"><c:out value="使用中" /></span>
								</c:if> <c:if test="${e.status == 0}">
									<span style="color: #F0BB1C;"><c:out value="已停用" /></span>
								</c:if></td>
						<%-- <td><c:out value="${e.locationId}" /></td> --%>
						
						<td>
							<jphs:hasPermission url="/site/delete.jhtml">	
								<c:if test="${e.status == 0}">
									<a onclick="deleteById('${e.id}','1')" title="开启"> 
										<img style="width: 20px;height: 20px;" src="/static/images/blockup.png">
									</a>
								</c:if>
								<c:if test="${e.status == 1}">
									<a onclick="deleteById('${e.id}','0')" title="停用">
										<img style="width: 20px;height: 20px;" src="/static/images/startup.png">
									</a>
								</c:if>
							</jphs:hasPermission>
						<jphs:hasPermission url="/site/detail.jhtml">
						<a onclick="redirectDetailPage('${e.id}')" title="详情"> <img
								src="/static/images/chakan.png">
						</a>
						</jphs:hasPermission>
						<jphs:hasPermission url="/site/redirectUpdate.jhtml">
						 <a onclick="redirectUpdatePage('${e.id}')" title="修改"> <img
								src="/static/images/xiugai.png">
						</a> 
						</jphs:hasPermission>
						<jphs:hasPermission url="/site/delete.jhtml">	
						<a onclick="deleteById('${e.id}','-1')" title="删除"> <img
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
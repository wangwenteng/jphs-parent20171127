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
			<th width="30"></th><!-- 
			<th>频道id</th> -->
			<th>标题</th>
			<th>预览数量</th>
			<th>置顶</th>
			<!-- <th>置顶时间</th> -->
			<!-- <th>出处</th> -->
			<th>作者</th>
			
			<th width="100px">创建人</th>
			<th width="150px">创时间</th>
			<th>状态</th>
			<th width="145">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td><%-- 
						<td><c:out value="${e.informationChannelId}" /></td> --%>
						<td><c:out value="${e.title}" /></td>
						<td><c:out value="${e.previewNumber}" /></td>
						<td>
							<c:if test="${e.top == 1}">
								已置顶
							</c:if>
							<c:if test="${e.top == 0}">
								未置顶
							</c:if>
						</td>
						<%-- <td><fmt:formatDate value="${e.topTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
						<%-- <td><c:out value="${e.source}" /></td> --%>
						<td><c:out value="${e.author}" /></td>
						<td><c:out value="${e.creatorName}" /></td>
						<td><fmt:formatDate value="${e.createTime}"
								pattern="yy-MM-dd HH:mm:ss" /></td>
								
						<td>
							<c:if test="${e.status == -1}">
								已停用
							</c:if>
							<c:if test="${e.status == 0}">
								使用中
							</c:if>
						</td>
						<td>
						<c:if test="${e.top == 1}">
							<a onclick="stickId('${e.id}','0')">
								<img style="width: 40px;height: 30px;" src="/static/images/cancel_stick.png">
							</a>
						</c:if>
						<c:if test="${e.top == 0}">
							<a onclick="stickId('${e.id}','1')">
								<img style="width: 40px;height: 30px;" src="/static/images/stick.png">
							</a>
						</c:if>
						<a onclick="redirectDetailPage('${e.id}')"> <img
								src="/static/images/chakan.png">
						</a> <a onclick="redirectUpdatePage('${e.id}')"> <img
								src="/static/images/xiugai.png">
						</a> 
							<c:if test="${e.status == -1}">
								<a onclick="deleteById('${e.id}','0')"> 
										<img style="width: 20px;height: 20px;" src="/static/images/blockup.png">
								</a>
							</c:if>
							<c:if test="${e.status == 0}">
								<a onclick="deleteById('${e.id}','-1')">
									<img style="width: 20px;height: 20px;" src="/static/images/startup.png">
								</a>
							</c:if>
							
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
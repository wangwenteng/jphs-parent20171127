<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<%
	int i = 1;
%>
<table  id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center" style="width:100%;">
	<thead>
		<tr>
			<th width="30"></th>
			<th>服务名称</th>
			<th>服务品类</th>
			<!-- <th>服务别名</th> -->
			<th>服务类型</th>
			<th width="50">排序</th>
			<th width="80">创建人</th>
			<th width="118">创建时间</th>
			<th width="60">状态</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<c:forEach items="${e.goodsList}" var="goodsOne" varStatus="status">
						<tr class="bg_list_body">
							<td width="30"><%=i++%></td>							
							<td><c:out value="${goodsOne.title}" /></td>
							<td><c:out value="${e.title}" /></td>
							<%-- <td><c:out value="${goodsOne.subTitle}" /></td> --%>
							<%-- <td><c:out value="${e.content}"/></td> --%>
							<%-- <td><c:out value="${e.amount}"/></td>
								<td><c:out value="${e.profit}"/></td> --%>
							<%-- <td><c:out value="${e.tijianTypeId}"/></td>
								<td><c:out value="${e.tijianHospitalId}"/></td> --%>
							<td>
								<c:if test="${goodsOne.type == 1}">
									<c:out value="开放型订单" />
								</c:if> <c:if test="${goodsOne.type != 1}">
									<c:out value="内部订单" />
								</c:if>
							</td>
							<td><c:out value="${goodsOne.sort}" /></td>
							<%-- <td><c:out value="${e.remark}"/></td> --%>
							
								<td><c:out value="${goodsOne.creatorName}"/></td>
							<td><fmt:formatDate value="${goodsOne.createTime}"
								pattern="yy-MM-dd HH:mm" /></td>
								<td><c:if test="${goodsOne.status == 1}">
									<span style="color: #34BC2C;"><c:out value="使用中" /></span>
								</c:if> <c:if test="${goodsOne.status == 0}">
									<span style="color: #F0BB1C;"><c:out value="已停用" /></span>
								</c:if></td>
							<td>
							
								<jphs:hasPermission url="/goods/delete.jhtml">	
									<c:if test="${goodsOne.status == 0}">
										<a onclick="deleteById('${goodsOne.id}','1')"> 
											<img style="width: 20px;height: 20px;" src="/static/images/blockup.png">
										</a>
									</c:if>
									<c:if test="${goodsOne.status == 1}">
										<a onclick="deleteById('${goodsOne.id}','0')">
											<img style="width: 20px;height: 20px;" src="/static/images/startup.png">
										</a>
									</c:if>
								</jphs:hasPermission>
								<jphs:hasPermission url="/site/detail.jhtml">
								<a onclick="redirectDetailPage('${goodsOne.id}')">
									<img src="/static/images/chakan.png">
								</a>
								</jphs:hasPermission>
								<jphs:hasPermission url="/site/redirectUpdate.jhtml">
								<a onclick="redirectUpdatePage('${goodsOne.id}')">
									<img src="/static/images/xiugai.png">
								</a>
								</jphs:hasPermission>
								<jphs:hasPermission url="/goods/delete.jhtml">	
								<a onclick="deleteById('${goodsOne.id}','-1')">
									<img src="/static/images/shanchu.png">
								</a>
								</jphs:hasPermission>
							</td>
						</tr>
					</c:forEach>
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
	<jphs:page pageInfos="${pageInfo }"></jphs:page>	
</div>
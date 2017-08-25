<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="data_table text-center" style="width:100%;">
	<thead>
		<tr >
			<th width="30px"></th>
			<!-- <th width="20px"></th> -->
			<th>咨询名称</th>
			<th>精华</th>
			<!-- <th>评价内容</th> -->
			<th width="100px">评论人</th>
			<th width="150px">评论时间</th>
			<th>评论渠道</th>
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
							<%-- <td><input type="checkbox" name="status" value="${e.id}" /></td> --%>
							<td>
							
								<c:if test="${fn:length(e.information.title) > 15}">
									<c:out value="${fn:substring(e.information.title, 0, 14)}..." />
								</c:if>
								<c:if test="${fn:length(e.information.title) < 16}">
									<c:out value="${e.information.title}" />
								</c:if>
							<td>
								<c:if test="${e.essence == 0}">
									是
								</c:if>
								<c:if test="${e.essence != 0}">
									否
								</c:if>
							</td>
							<%-- <td><c:out value="${e.content}"/></td> --%>
							<td><c:out value="${e.creatorName}" /></td>
							<td><fmt:formatDate value="${e.createTime}" pattern="yy-MM-dd HH:mm" /></td>
							<td>
								<c:if test="${e.device == 1 }">
									IOS-APP
								</c:if>
								<c:if test="${e.device == 2 }">
									安卓-APP
								</c:if>
								<c:if test="${e.device == 0 }">
									官网
								</c:if>
								<c:if test="${e.device == 3 }">
									微信公众号
								</c:if>
							</td>
							<td>
								<c:if test="${e.status == 1 }">
									已审核-未通过
								</c:if>
								<c:if test="${e.status == 0 }">
									已审核-通过
								</c:if>
								<c:if test="${e.status == -1 }">
									待审核
								</c:if>
							</td>
							<td>
							<%-- <a onclick="essenceById('${e.id}','${e.essence }')">
								<img style="width: 20px;height: 20px;"  src="/static/images/essence.png">
							</a>
							<a onclick="byauditid('${e.id}');" data-toggle="modal" data-target="#myModal">
								<img style="width: 20px;height: 20px;" src="/static/images/audit.png">
							</a> --%>
							<a onclick="redirectUpdatePage('${e.id}')">
								<img style="width: 20px;height: 20px;"  src="/static/images/audit.png">
							</a>
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
							</a>								
							<%-- <a onclick="deleteById('${e.id}')">
								<img src="/static/images/shanchu.png">
							</a> --%>
							
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
		<!-- <div>
			<button id="statusAll" class="public_btn2 m-10">全选</button>
			<button id="statusNoAll" class="public_btn2 m-10">全不选</button>
			<button onclick="byauditid('0')" data-toggle="modal" data-target="#myModal" class="public_btn2 m-10">批量审核</button>
		</div> -->
		
		<div class="page">
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
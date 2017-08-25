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
			<th>批次编号</th>
			<th>优惠券类型</th>
			<th>支持品类/服务</th>
			<th>面值</th>
			<th>兑换开始时间</th>
			<th>兑换结束时间</th>
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
							<td><c:out value="${e.batchNo}"/></td>
							<td>
								<c:if test="${e.type ==1 }">现金券</c:if>
								<c:if test="${e.type ==2 }">满减券</c:if>
								<c:if test="${e.type ==3 }">折扣券</c:if>
							</td>
							<td> 
								<c:if test="${fn:length(e.productName)>10 }">
									${fn:substring(e.productName, 0, 10)}... 
								</c:if> <c:if test="${fn:length(e.productName)<10 }">
									${e.productName} 
								</c:if> 
								<c:if test="${fn:length(e.goodsName)>10 }">
									${fn:substring(e.goodsName, 0, 10)}... 
								</c:if> <c:if test="${fn:length(e.goodsName)<10 }">
									${e.goodsName} 
								</c:if>
							</td>
							<td><c:out value="${e.amount }"></c:out></td>
							<td><fmt:formatDate value="${e.startTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td><fmt:formatDate value="${e.endTime}" pattern="yy-MM-dd HH:mm"/></td>
							<td>
								<c:if test="${e.status ==0 }"><span style="color: #34BC2C;">生效</span></c:if>
								<c:if test="${e.status ==-1 }"><span style="color: #F0BB1C;">停用</span></c:if>
							</td>
							<td>
							<jphs:hasPermission url="/voucher/detail.jhtml">
							<a onclick="redirectDetailPage('${e.id}')">
								<img src="/static/images/chakan.png">
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
			<jphs:page pageInfos="${pageInfo}" ></jphs:page>	
		</div>
	</div>
</div>
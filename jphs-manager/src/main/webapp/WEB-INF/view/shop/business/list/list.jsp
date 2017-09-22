<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
	<thead>
		<tr >
			<th width="30"></th>
			<th width="100">家商名称</th>
			<th width="100">官网地址</th>
			<th width="100">商家地址</th>
			<th width="100">联系人</th>
			<th width="100">联系电话</th>
			<th width="100">算结周期</th>
			<th width="100">支付方式</th>
			<th width="100">银行</th>
			<th width="100">开户行</th>
			<th width="100">卡号</th>
			<th width="100">开卡人</th>
			<th width="100">备注</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
			<c:choose>
				<c:when test="${fn:length(list) >0}">
					<c:forEach items="${list}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:out value="${e.name}"/></td>
							<td><c:out value="${e.officialWebsiteUrl}"/></td>
							<td><c:out value="${e.address}"/></td>
							<td><c:out value="${e.contactsName}"/></td>
							<td><c:out value="${e.contactsPhone}"/></td>
							<td>${e.settlementCycle}
								 ${e.unit}
							</td>
							<td>${e.payType }</td>
							<td><c:out value="${e.bank}"/></td>
							<td><c:out value="${e.openBankAddress}"/></td>
							<td><c:out value="${e.cardNumber}"/></td>
							<td><c:out value="${e.cardName}"/></td>
							<td><c:out value="${e.remark}"/></td>
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
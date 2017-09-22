<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" cellpadding="0" cellspacing="0"
	class="data_table text-center" style="text-align: center;">
	<thead>
		<tr>
			<th width="30"></th>
			<th width="118">图片</th>
			<th width="118">商品名称</th>
			<th width="118">所属商家</th>
			<th width="70">商品类型</th>
			<th width="118">型号/规格</th>
			<th width="300" colspan="2">商品SKU/库存</th>
			<th width="50">销量</th>
			<th width="50">状态</th>
			<th width="125">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(list) >0}">
				<c:forEach items="${list}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30">${s.index+1}</td>
						<td><img alt="" height="50" width="50"
							id="aptitude_positives" src="${e.url}"></td>
						<td><c:out value="${e.title}" /></td>
						<td style="text-align: center;"><c:if test="${fn:length(e.businessName)>5 }">
								${fn:substring(e.businessName, 0, 5)}... 
							</c:if> <c:if test="${fn:length(e.businessName)<5 }">
								${e.businessName} 
							</c:if></td>
						<td><c:out value="${e.commodityType}" /></td>
						<td><c:out value="${e.model}" /></td>
						<td><c:out value="${e.totals}" /></td>
						<td>
							<c:choose>
								<c:when test="${fn:length(e.skuNameList) >0}">
									<table>
										<c:forEach items="${e.skuNameList}" var="k" varStatus="s">
										<tr>
										<td style="border:0">
											<c:out value="${k.name}" />
										 </td>
										 <td style="border:0">
											<c:out value="${k.number}" />
											</td>
										</tr>
										</c:forEach>
									</table>
								</c:when>
							</c:choose>
						</td>

						<td><c:out value="${e.count}" /></td>
						<td>
							<c:if test="${e.status ==1}">
							运营中
							</c:if>
							<c:if test="${e.status !=1}">
							暂停销售
							</c:if>

						<td><a onclick="redirectDetailPage('${e.id}')"> <img
								src="/static/images/chakan.png">
						</a> <a onclick="redirectUpdatePage('${e.id}')"> <img
								src="/static/images/xiugai.png">
						</a> <a onclick="deleteById('${e.id}')"> <img
								src="/static/images/shanchu.png">
						</a></td>
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
</div>

</div>
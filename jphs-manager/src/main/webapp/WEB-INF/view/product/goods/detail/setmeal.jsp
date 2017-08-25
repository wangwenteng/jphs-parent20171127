<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<c:choose>
	<c:when test="${fn:length(goods.priceGrade) >0}">
		<c:forEach var="priceOne_g" items="${goods.priceGrade }"
			varStatus="status_g">
			<span class="price_add_title">${priceOne_g.gradeName }</span>
			<div style="margin-top: 0; padding-top: 0;">
				<table id="dateTable" class="data_table text-center" style="width:883px;">
					<thead>
						<tr class="headClass" >
							<th width="30"></th>
							<th >名称/次数/时长</th>										
							<th width="100">成本</th>
							<th width="100">毛利润</th>
							<th width="100">最高售价</th>
							<th width="100" title="自主发布服务时的售价超出标准成本后的抽成比">超出比</th>						
							<th width="45">排序</th>
							<th width="100">操作</th>
							<!-- 
										<td width="60px" >授权</td> -->
						</tr>
					</thead>
					<tbody id="addtrprice${status_g.index }">
						<c:forEach var="priceOne" items="${priceOne_g.price }"
							varStatus="status">
							<tr>
								<td ></td>
								<td >${priceOne.title }&nbsp;/&nbsp;${priceOne.serviceNumber }次&nbsp;/&nbsp;${priceOne.serviceTime }${priceOne.unit }</td>
								<td style="text-align: right;padding-right: 8px;">
									<fmt:formatNumber value="${priceOne.pricePart.costPrice }" pattern="#,#00.0#"></fmt:formatNumber></td>
								<td style="text-align: right;padding-right: 8px;">
									<fmt:formatNumber value="${priceOne.pricePart.profit }" pattern="#,#00.0#"></fmt:formatNumber>
								</td>	
								<td ></td>	
								<td ></td>						
								<td >${priceOne.sort }</td>
								<td ></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</c:when>
</c:choose>
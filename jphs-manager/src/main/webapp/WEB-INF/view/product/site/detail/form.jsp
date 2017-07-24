<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="siteTable" class="tableStyle">
		<tr>
			<td align="right"width="100">名称：</td>
			<td>
				<c:out value="${site.name}"/>
			</td>
		</tr>
		<tr>
			<td align="right">服务器地址：</td>
			<td>
				<c:out value="${site.url}"/>
			</td>
		</tr>
		<tr>
			<td align="right">服务区域：</td>
			<td>
				<table style="width: 300px;">
					<c:forEach items="${site.location}" var="e" varStatus="s">
						<tr>
							<c:set value="${ fn:split(e.content, '-') }" var="str1" />
							<c:forEach items="${ str1 }" var="s">
								<td>${s }</td>
							</c:forEach>
		
						</tr>
					</c:forEach>
				</table>			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${site.remark}"/>
			</td>
		</tr>
	</table>
	<div class="clearfix">
			<c:forEach items="${listProduct }" var="productOne" varStatus="status">
				<button type="button" class="public-info public_btn public_btn_left" onclick="tr_product('${status.index}');" >${productOne.title }</button>
			</c:forEach>
	</div>
	<div class="marage_right_content"  style="margin-top:0;padding-top:0">
			<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
				<tbody>
					<tr >
						<td width="40px">序号</td>
						<td width="100px">服务名称</td>
						<td width="60px">服务等级</td>
						<td width="60px">服务名称</td>
						<td width="60px">服务次数</td>
						<td width="60px">服务套餐名称</td>
						<td width="60px" >销售价格</td>
						<td width="60px" >原价</td>
						<td width="60px" >利润</td>
						<td width="60px" >派单模式</td>
					</tr>
					<c:forEach items="${listProduct }" var="productOne" varStatus="productStatus">
						<c:if test="${productStatus.index == 0 }">
							<c:forEach var="goodsOne" items="${productOne.goodsList }" varStatus="goodsStatus">
								<c:forEach var="priceOne" items="${goodsOne.priceList }" varStatus="priceStatus">
									<tr class="price${productStatus.index }">
										<td>
											${priceStatus.index+1 }
										</td>
										<td>${goodsOne.title }</td>
										<td>${priceOne.grade }
										<td>${priceOne.gradeName }
											<%-- <c:if test="${priceOne.grade == 0 }">
												初级
											</c:if>
											<c:if test="${priceOne.grade == 1 }">
												中级
											</c:if>
											<c:if test="${priceOne.grade == 2 }">
												高级
											</c:if> --%>
										</td>
										<td>${priceOne.serviceNumber }</td>
										<td>${priceOne.title }</td>
										<td>${priceOne.pricePart.price }</td>
										<td>${priceOne.pricePart.oldPrice }</td>
										<td>${priceOne.pricePart.profit }</td>
										<td><c:if test="${goodsOne.type == 0 }">
												内部订单
											</c:if>
											<c:if test="${goodsOne.type == 1 }">
												抢单
											</c:if></td>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:if>
						<c:if test="${productStatus.index != 0 }">
							<c:forEach var="goodsOne" items="${productOne.goodsList }" varStatus="goodsStatus">
								<c:forEach var="priceOne" items="${goodsOne.priceList }" varStatus="priceStatus">
									<tr class="price${productStatus.index }" style="display: none;">
										<td>
											${priceStatus.index+1 }
										</td>
										<td>${goodsOne.title }</td>
										<td>${priceOne.grade }</td>
										<td>${priceOne.gradeName }
										<td>${priceOne.serviceNumber }</td>
										<td>${priceOne.title }</td>
										<td>${priceOne.pricePart.price }</td>
										<td>${priceOne.pricePart.oldPrice }</td>
										<td>${priceOne.pricePart.profit }</td>
										<td><c:if test="${goodsOne.type == 0 }">
												内部订单
											</c:if>
											<c:if test="${goodsOne.type == 1 }">
												抢单
											</c:if></td>
									</tr>
								</c:forEach>
							</c:forEach>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
</div>
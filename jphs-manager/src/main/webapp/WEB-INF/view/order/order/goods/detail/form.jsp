<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="orderGoodsTable" class="tableStyle">
		<tr>
			<td align="right"width="100">订单id：</td>
			<td>
				<c:out value="${orderGoods.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品id：</td>
			<td>
				<c:out value="${orderGoods.goodsId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品名称：</td>
			<td>
				<c:out value="${orderGoods.title}"/>
			</td>
		</tr>
		<tr>
			<td align="right">销售价：</td>
			<td>
				<c:out value="${orderGoods.price}"/>
			</td>
		</tr>
		<tr>
			<td align="right">实付金额：</td>
			<td>
				<c:out value="${orderGoods.payPrice}"/>
			</td>
		</tr>
		<tr>
			<td align="right">利润：</td>
			<td>
				<c:out value="${orderGoods.profit}"/>
			</td>
		</tr>
		<tr>
			<td align="right">指定人：</td>
			<td>
				<c:out value="${orderGoods.expectorId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">执行人：</td>
			<td>
				<c:out value="${orderGoods.executorId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${orderGoods.remark}"/>
			</td>
		</tr>
	</table>
</div>
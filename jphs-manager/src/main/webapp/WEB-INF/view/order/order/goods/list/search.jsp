<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/order/goods/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>订单id：</td>
			<td width="200">
				<input type="text" name="orderId" id="orderId" value="${orderGoods.orderId}" />
			</td>
			<td>商品id：</td>
			<td width="200">
				<input type="text" name="goodsId" id="goodsId" value="${orderGoods.goodsId}" />
			</td>
			<td>商品名称：</td>
			<td width="200">
				<input type="text" name="title" id="title" value="${orderGoods.title}" />
			</td>
			<td>销售价：</td>
			<td width="200">
				<input type="text" name="price" id="price" value="${orderGoods.price}" />
			</td>
			<td>实付金额：</td>
			<td width="200">
				<input type="text" name="payPrice" id="payPrice" value="${orderGoods.payPrice}" />
			</td>
			<td>利润：</td>
			<td width="200">
				<input type="text" name="profit" id="profit" value="${orderGoods.profit}" />
			</td>
			<td>指定人：</td>
			<td width="200">
				<input type="text" name="expectorId" id="expectorId" value="${orderGoods.expectorId}" />
			</td>
			<td>执行人：</td>
			<td width="200">
				<input type="text" name="executorId" id="executorId" value="${orderGoods.executorId}" />
			</td>
			<td>备注：</td>
			<td width="200">
				<input type="text" name="remark" id="remark" value="${orderGoods.remark}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


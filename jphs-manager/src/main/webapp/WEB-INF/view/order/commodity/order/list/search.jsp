<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get"
	action="/commodity/order/index.jhtml">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>订单号：</td>
				<td width="200"><input type="text" name="orderNo" id="orderNo"
					value="${commodityOrder.orderNo}" /></td>
				<td>下单人：</td>
				<td width="200"><input type="text" name="payTime" id="payTime"
					value="${commodityOrder.payTime}" /></td>
				<td>状态：</td>
				<td width="200"><select class="marage_select" id="schedule"
					name="schedule">

						<option value="" <c:if test="">selected="selected"</c:if>>选择状态</option>
						
						<option value="0"
							<c:if test="${commodityOrder.schedule == 0}">selected="selected"</c:if>>待支付</option>
						<option value="1"
							<c:if test="${commodityOrder.schedule == 1}">selected="selected"</c:if>>待发货</option>
						<option value="2"
							<c:if test="${commodityOrder.schedule == 2}">selected="selected"</c:if>>待收货</option>
						<%-- <option value="7"
					<c:if test="${commodityOrder.schedule == 3}">selected="selected"</c:if>>7</option> --%>
						<option value="4"
							<c:if test="${commodityOrder.schedule == 4}">selected="selected"</c:if>>已完成</option>
						<option value="5"
							<c:if test="${commodityOrder.schedule == 5}">selected="selected"</c:if>>已删除</option>
						<option value="-2"
							<c:if test="${commodityOrder.schedule == -2}">selected="selected"</c:if>>退款中</option>
							<option value="-1"
							<c:if test="${commodityOrder.schedule == -1}">selected="selected"</c:if>>取消订单</option>
							<option value="-3"
							<c:if test="${commodityOrder.schedule == -3}">selected="selected"</c:if>>已退款</option>
				</select></td>
			</tr>
			<tr>
				<td>运单号：</td>
				<td width="200"><input type="text" name="takeTime"
					id="takeTime" value="${commodityOrder.takeTime}" /></td>
				<td>下单时间：</td>
				<td width="200"><input type="text" name="confirmTime"
					id="confirmTime" value="${commodityOrder.confirmTime}" /></td>
				<td>商品名称：</td>
				<td width="200"><input type="text" name="remindTime"
					id="remindTime" value="${commodityOrder.remindTime}" /></td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>


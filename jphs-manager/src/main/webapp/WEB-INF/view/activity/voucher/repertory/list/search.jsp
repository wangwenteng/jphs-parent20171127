<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/voucher/repertory/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>优惠券id：</td>
			<td width="200">
				<input type="text" name="voucherId" id="voucherId" value="${voucherRepertory.voucherId}" />
			</td>
			<td>卡号：</td>
			<td width="200">
				<input type="text" name="no" id="no" value="${voucherRepertory.no}" />
			</td>
			<td>兑换码：</td>
			<td width="200">
				<input type="text" name="code" id="code" value="${voucherRepertory.code}" />
			</td>
			<td>金额：</td>
			<td width="200">
				<input type="text" name="amount" id="amount" value="${voucherRepertory.amount}" />
			</td>
			<td>满xx减：</td>
			<td width="200">
				<input type="text" name="conditionAmount" id="conditionAmount" value="${voucherRepertory.conditionAmount}" />
			</td>
			<td>满xx折：</td>
			<td width="200">
				<input type="text" name="discountAmount" id="discountAmount" value="${voucherRepertory.discountAmount}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


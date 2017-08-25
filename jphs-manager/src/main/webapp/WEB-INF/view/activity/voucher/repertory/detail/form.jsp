<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="voucherRepertoryTable" class="tableStyle">
		<tr>
			<td align="right"width="100">优惠券id：</td>
			<td>
				<c:out value="${voucherRepertory.voucherId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">卡号：</td>
			<td>
				<c:out value="${voucherRepertory.no}"/>
			</td>
		</tr>
		<tr>
			<td align="right">兑换码：</td>
			<td>
				<c:out value="${voucherRepertory.code}"/>
			</td>
		</tr>
		<tr>
			<td align="right">金额：</td>
			<td>
				<c:out value="${voucherRepertory.amount}"/>
			</td>
		</tr>
		<tr>
			<td align="right">满xx减：</td>
			<td>
				<c:out value="${voucherRepertory.conditionAmount}"/>
			</td>
		</tr>
		<tr>
			<td align="right">满xx折：</td>
			<td>
				<c:out value="${voucherRepertory.discountAmount}"/>
			</td>
		</tr>
	</table>
</div>
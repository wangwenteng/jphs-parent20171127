<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="transactionTable" class="tableStyle">
		<tr>
			<td align="right"width="100">订单id：</td>
			<td>
				<c:out value="${transaction.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">金额：</td>
			<td>
				<c:out value="${transaction.amount}"/>
			</td>
		</tr>
		<tr>
			<td align="right">积分：</td>
			<td>
				<c:out value="${transaction.score}"/>
			</td>
		</tr>
		<tr>
			<td align="right">操作(1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整)：</td>
			<td>
				<c:out value="${transaction.operate}"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${transaction.remark}"/>
			</td>
		</tr>
		<tr>
			<td align="right">是否已提现 1=是 0=否：</td>
			<td>
				<c:out value="${transaction.withdraw}"/>
			</td>
		</tr>
		<tr>
			<td align="right">支付方式：</td>
			<td>
				<c:out value="${transaction.payType}"/>
			</td>
		</tr>
		<tr>
			<td align="right">交易号：</td>
			<td>
				<c:out value="${transaction.outTradeNo}"/>
			</td>
		</tr>
	</table>
</div>
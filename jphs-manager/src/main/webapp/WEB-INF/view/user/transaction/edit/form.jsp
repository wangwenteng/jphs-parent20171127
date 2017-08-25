<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${transaction.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${transaction.id == null?'添加':'编辑'}</div>
		<table id="transactionTable" class="tableStyle">
			<tr>
				<td align="right"width="100">订单id：</td>
				<td>
					<input value="${transaction.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">金额：</td>
				<td>
					<input value="${transaction.amount}" 
						id="amount"
						name="amount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">积分：</td>
				<td>
					<input value="${transaction.score}" 
						id="score"
						name="score"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">操作(1.提现, 2.充值, 3.消费 , 4.收入, 5.系统调整)：</td>
				<td>
					<input value="${transaction.operate}" 
						id="operate"
						name="operate"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td>
					<textArea id="remark"
						name="remark"
						rows="12"
					 	data-validation-engine="validate[${validates}]">${fn:escapeXml(transaction.remark)}</textArea>
				</td>
			</tr>
			<tr>
				<td align="right">是否已提现 1=是 0=否：</td>
				<td>
					<input value="${transaction.withdraw}" 
						id="withdraw"
						name="withdraw"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支付方式：</td>
				<td>
					<input value="${transaction.payType}" 
						id="payType"
						name="payType"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">交易号：</td>
				<td>
					<input value="${transaction.outTradeNo}" 
						id="outTradeNo"
						name="outTradeNo"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[${validates}]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
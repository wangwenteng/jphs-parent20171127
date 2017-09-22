<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${withdrawCash.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${withdrawCash.id == null?'提现申请记录
添加':'提现申请记录
编辑'}</div>
		<table id="withdrawCashTable" class="tableStyle">
			<tr>
				<td align="right"width="100">提现金额：</td>
				<td>
					<input value="${withdrawCash.amount}" 
						id="amount"
						name="amount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">账户姓名：</td>
				<td>
					<input value="${withdrawCash.accountName}" 
						id="accountName"
						name="accountName"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">支付宝账号：</td>
				<td>
					<input value="${withdrawCash.alipayAccount}" 
						id="alipayAccount"
						name="alipayAccount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">审核时间：</td>
				<td>
					<input value="<fmt:formatDate value="${withdrawCash.auditTime}" type="both" pattern="yyyy-MM-dd" />"
						id="auditTime"
						name="auditTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">备注 驳回时间：</td>
				<td>
					<input value="${withdrawCash.remark}" 
						id="remark"
						name="remark"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
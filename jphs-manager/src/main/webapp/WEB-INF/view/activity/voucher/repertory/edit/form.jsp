<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${voucherRepertory.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${voucherRepertory.id == null?'添加':'编辑'}</div>
		<table id="voucherRepertoryTable" class="tableStyle">
			<tr>
				<td align="right"width="100">优惠券id：</td>
				<td>
					<input value="${voucherRepertory.voucherId}" 
						id="voucherId"
						name="voucherId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">卡号：</td>
				<td>
					<input value="${voucherRepertory.no}" 
						id="no"
						name="no"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">兑换码：</td>
				<td>
					<input value="${voucherRepertory.code}" 
						id="code"
						name="code"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">金额：</td>
				<td>
					<input value="${voucherRepertory.amount}" 
						id="amount"
						name="amount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">满xx减：</td>
				<td>
					<input value="${voucherRepertory.conditionAmount}" 
						id="conditionAmount"
						name="conditionAmount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">满xx折：</td>
				<td>
					<input value="${voucherRepertory.discountAmount}" 
						id="discountAmount"
						name="discountAmount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
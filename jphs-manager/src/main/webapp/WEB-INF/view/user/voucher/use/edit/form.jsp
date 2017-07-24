<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${voucherUse.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${voucherUse.id == null?'添加':'编辑'}</div>
		<table id="voucherUseTable" class="tableStyle">
			<tr>
				<td align="right"width="100">优惠券仓库id：</td>
				<td>
					<input value="${voucherUse.voucherRepertoryId}" 
						id="voucherRepertoryId"
						name="voucherRepertoryId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">领取人手机号：</td>
				<td>
					<input value="${voucherUse.phone}" 
						id="phone"
						name="phone"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">优惠券金额：</td>
				<td>
					<input value="${voucherUse.amount}" 
						id="amount"
						name="amount"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">有效期开始时间：</td>
				<td>
					<input value="<fmt:formatDate value="${voucherUse.startTime}" type="both" pattern="yyyy-MM-dd" />"
						id="startTime"
						name="startTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">有效期结束时间：</td>
				<td>
					<input value="<fmt:formatDate value="${voucherUse.endTime}" type="both" pattern="yyyy-MM-dd" />"
						id="endTime"
						name="endTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">使用时间：</td>
				<td>
					<input value="<fmt:formatDate value="${voucherUse.useTime}" type="both" pattern="yyyy-MM-dd" />"
						id="useTime"
						name="useTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${insurance.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${insurance.id == null?'添加':'编辑'}</div>
		<table id="insuranceTable" class="tableStyle">
			<tr>
				<td align="right"width="100">主订单id：</td>
				<td>
					<input value="${insurance.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">受保人：</td>
				<td>
					<input value="${insurance.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">受保人身份证：</td>
				<td>
					<input value="${insurance.sfz}" 
						id="sfz"
						name="sfz"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td>
					<input value="${insurance.remark}" 
						id="remark"
						name="remark"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
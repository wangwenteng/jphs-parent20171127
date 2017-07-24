<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${orderOther.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${orderOther.id == null?'添加':'编辑'}</div>
		<table id="orderOtherTable" class="tableStyle">
			<tr>
				<td align="right"width="100">订单id：</td>
				<td>
					<input value="${orderOther.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">科室id：</td>
				<td>
					<input value="${orderOther.departmentId}" 
						id="departmentId"
						name="departmentId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">医院：</td>
				<td>
					<input value="${orderOther.hospital}" 
						id="hospital"
						name="hospital"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">地址-省：</td>
				<td>
					<input value="${orderOther.address}" 
						id="address"
						name="address"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">详细地址：</td>
				<td>
					<input value="${orderOther.detailAddress}" 
						id="detailAddress"
						name="detailAddress"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">是否有药品：</td>
				<td>
					<input value="${orderOther.drug}" 
						id="drug"
						name="drug"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">是否有工具：</td>
				<td>
					<input value="${orderOther.tool}" 
						id="tool"
						name="tool"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">通用字段：</td>
				<td>
					<input value="${orderOther.publicName}" 
						id="publicName"
						name="publicName"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">通用字段：</td>
				<td>
					<input value="${orderOther.publicContent}" 
						id="publicContent"
						name="publicContent"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<input value="${orderOther.remarks}" 
						id="remarks"
						name="remarks"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
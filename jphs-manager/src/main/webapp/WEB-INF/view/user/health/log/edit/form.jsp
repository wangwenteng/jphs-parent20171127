<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${healthLog.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${healthLog.id == null?'添加':'编辑'}</div>
		<table id="healthLogTable" class="tableStyle">
			<tr>
				<td align="right"width="100">会员id：</td>
				<td>
					<input value="${healthLog.userId}" 
						id="userId"
						name="userId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">订单id：</td>
				<td>
					<input value="${healthLog.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">康复建议：</td>
				<td>
					<input value="${healthLog.advise}" 
						id="advise"
						name="advise"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">评估内容：</td>
				<td>
					<input value="${healthLog.evaluateContent}" 
						id="evaluateContent"
						name="evaluateContent"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">疾病原因：</td>
				<td>
					<input value="${healthLog.diseaseCauses}" 
						id="diseaseCauses"
						name="diseaseCauses"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">身体症状：</td>
				<td>
					<input value="${healthLog.physicalSymptoms}" 
						id="physicalSymptoms"
						name="physicalSymptoms"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">现病史：</td>
				<td>
					<input value="${healthLog.presentIllness}" 
						id="presentIllness"
						name="presentIllness"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">疾病史：</td>
				<td>
					<input value="${healthLog.diseasesHistory}" 
						id="diseasesHistory"
						name="diseasesHistory"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">用药提醒：</td>
				<td>
					<input value="${healthLog.medicationRemind}" 
						id="medicationRemind"
						name="medicationRemind"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[255]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">复诊时间：</td>
				<td>
					<input value="<fmt:formatDate value="${healthLog.appointmentTime}" type="both" pattern="yyyy-MM-dd" />"
						id="appointmentTime"
						name="appointmentTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">注意事项：</td>
				<td>
					<input value="${healthLog.notes}" 
						id="notes"
						name="notes"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
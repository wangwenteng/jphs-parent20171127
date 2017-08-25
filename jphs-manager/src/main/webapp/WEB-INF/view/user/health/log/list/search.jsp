<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/health/log/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>会员id：</td>
			<td width="200">
				<input type="text" name="userId" id="userId" value="${healthLog.userId}" />
			</td>
			<td>订单id：</td>
			<td width="200">
				<input type="text" name="orderId" id="orderId" value="${healthLog.orderId}" />
			</td>
			<td>康复建议：</td>
			<td width="200">
				<input type="text" name="advise" id="advise" value="${healthLog.advise}" />
			</td>
			<td>评估内容：</td>
			<td width="200">
				<input type="text" name="evaluateContent" id="evaluateContent" value="${healthLog.evaluateContent}" />
			</td>
			<td>疾病原因：</td>
			<td width="200">
				<input type="text" name="diseaseCauses" id="diseaseCauses" value="${healthLog.diseaseCauses}" />
			</td>
			<td>身体症状：</td>
			<td width="200">
				<input type="text" name="physicalSymptoms" id="physicalSymptoms" value="${healthLog.physicalSymptoms}" />
			</td>
			<td>现病史：</td>
			<td width="200">
				<input type="text" name="presentIllness" id="presentIllness" value="${healthLog.presentIllness}" />
			</td>
			<td>疾病史：</td>
			<td width="200">
				<input type="text" name="diseasesHistory" id="diseasesHistory" value="${healthLog.diseasesHistory}" />
			</td>
			<td>用药提醒：</td>
			<td width="200">
				<input type="text" name="medicationRemind" id="medicationRemind" value="${healthLog.medicationRemind}" />
			</td>
			<td>复诊时间：</td>
			<td width="200">
				<input type="text" name="appointmentTime" id="appointmentTime" value="${healthLog.appointmentTime}" />
			</td>
			<td>注意事项：</td>
			<td width="200">
				<input type="text" name="notes" id="notes" value="${healthLog.notes}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


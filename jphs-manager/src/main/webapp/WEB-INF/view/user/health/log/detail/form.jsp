<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="healthLogTable" class="tableStyle">
		<tr>
			<td align="right"width="100">会员id：</td>
			<td>
				<c:out value="${healthLog.userId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">订单id：</td>
			<td>
				<c:out value="${healthLog.orderId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">康复建议：</td>
			<td>
				<c:out value="${healthLog.advise}"/>
			</td>
		</tr>
		<tr>
			<td align="right">评估内容：</td>
			<td>
				<c:out value="${healthLog.evaluateContent}"/>
			</td>
		</tr>
		<tr>
			<td align="right">疾病原因：</td>
			<td>
				<c:out value="${healthLog.diseaseCauses}"/>
			</td>
		</tr>
		<tr>
			<td align="right">身体症状：</td>
			<td>
				<c:out value="${healthLog.physicalSymptoms}"/>
			</td>
		</tr>
		<tr>
			<td align="right">现病史：</td>
			<td>
				<c:out value="${healthLog.presentIllness}"/>
			</td>
		</tr>
		<tr>
			<td align="right">疾病史：</td>
			<td>
				<c:out value="${healthLog.diseasesHistory}"/>
			</td>
		</tr>
		<tr>
			<td align="right">用药提醒：</td>
			<td>
				<c:out value="${healthLog.medicationRemind}"/>
			</td>
		</tr>
		<tr>
			<td align="right">复诊时间：</td>
			<td>
				<fmt:formatDate value="${healthLog.appointmentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">注意事项：</td>
			<td>
				<c:out value="${healthLog.notes}"/>
			</td>
		</tr>
	</table>
</div>
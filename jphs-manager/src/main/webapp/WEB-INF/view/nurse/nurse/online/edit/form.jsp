<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${nurseOnline.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${nurseOnline.id == null?'添加':'编辑'}</div>
		<table id="nurseOnlineTable" class="tableStyle">
			<tr>
				<td align="right"width="100">护士ID：</td>
				<td>
					<input value="${nurseOnline.nurseId}" 
						id="nurseId"
						name="nurseId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">在线标识：</td>
				<td>
					<input value="${nurseOnline.online}" 
						id="online"
						name="online"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">上线时间：</td>
				<td>
					<input value="<fmt:formatDate value="${nurseOnline.onlineTime}" type="both" pattern="yyyy-MM-dd" />"
						id="onlineTime"
						name="onlineTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">下线时间：</td>
				<td>
					<input value="<fmt:formatDate value="${nurseOnline.offlineTime}" type="both" pattern="yyyy-MM-dd" />"
						id="offlineTime"
						name="offlineTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">在线时长：</td>
				<td>
					<input value="${nurseOnline.timeLong}" 
						id="timeLong"
						name="timeLong"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
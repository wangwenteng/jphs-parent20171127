<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${orderService.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${orderService.id == null?'添加':'编辑'}</div>
		<table id="orderServiceTable" class="tableStyle">
			<tr>
				<td align="right"width="100">主订单id：</td>
				<td>
					<input value="${orderService.orderId}" 
						id="orderId"
						name="orderId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">单次价格：</td>
				<td>
					<input value="${orderService.price}" 
						id="price"
						name="price"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[number]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">执行人：</td>
				<td>
					<input value="${orderService.nurseId}" 
						id="nurseId"
						name="nurseId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">患者姓名：</td>
				<td>
					<input value="${orderService.patientName}" 
						id="patientName"
						name="patientName"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">患者电话：</td>
				<td>
					<input value="${orderService.patientPhone}" 
						id="patientPhone"
						name="patientPhone"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">指定医生：</td>
				<td>
					<input value="${orderService.expectorDoctor}" 
						id="expectorDoctor"
						name="expectorDoctor"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">进度：</td>
				<td>
					<input value="${orderService.schedule}" 
						id="schedule"
						name="schedule"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[custom[integer]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">预约时间：</td>
				<td>
					<input value="<fmt:formatDate value="${orderService.appointmentTime}" type="both" pattern="yyyy-MM-dd" />"
						id="appointmentTime"
						name="appointmentTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">出发时间：</td>
				<td>
					<input value="<fmt:formatDate value="${orderService.setoutTime}" type="both" pattern="yyyy-MM-dd" />"
						id="setoutTime"
						name="setoutTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">开始服务时间：</td>
				<td>
					<input value="<fmt:formatDate value="${orderService.startServiceTime}" type="both" pattern="yyyy-MM-dd" />"
						id="startServiceTime"
						name="startServiceTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">结束服务时间：</td>
				<td>
					<input value="<fmt:formatDate value="${orderService.endServiceTime}" type="both" pattern="yyyy-MM-dd" />"
						id="endServiceTime"
						name="endServiceTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">提醒时间：</td>
				<td>
					<input value="<fmt:formatDate value="${orderService.remindTime}" type="both" pattern="yyyy-MM-dd" />"
						id="remindTime"
						name="remindTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">完成订单时间：</td>
				<td>
					<input value="<fmt:formatDate value="${orderService.confirmTime}" type="both" pattern="yyyy-MM-dd" />"
						id="confirmTime"
						name="confirmTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
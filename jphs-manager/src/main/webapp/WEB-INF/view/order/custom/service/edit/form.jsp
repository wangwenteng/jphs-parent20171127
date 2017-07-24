<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${customService.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${customService.id == null?'添加':'编辑'}</div>
		<table id="customServiceTable" class="tableStyle">
			<tr>
				<td align="right"width="100">姓名：</td>
				<td>
					<input value="${customService.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">联系方式：</td>
				<td>
					<input value="${customService.phone}" 
						id="phone"
						name="phone"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">回访时间：</td>
				<td>
					<input value="<fmt:formatDate value="${customService.visitTime}" type="both" pattern="yyyy-MM-dd" />"
						id="visitTime"
						name="visitTime"
						type="date"
						class="inputText"
						readonly="readonly"
						onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'%y-%M-%d'})"></input>
				</td>
			</tr>
			<tr>
				<td align="right">回访客服：</td>
				<td>
					<input value="${customService.visitor}" 
						id="visitor"
						name="visitor"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">：</td>
				<td>
					<textArea id="remark"
						name="remark"
						rows="12"
					 	data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(customService.remark)}</textArea>
				</td>
			</tr>
		</table>
	</div>
</div>
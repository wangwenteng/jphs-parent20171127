<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${aptitude.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${aptitude.id == null?'添加':'编辑'}</div>
		<table id="aptitudeTable" class="tableStyle">
			<tr>
				<td align="right"width="100">资质名称or技能名称：</td>
				<td>
					<input value="${aptitude.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">资质证明：</td>
				<td>
					<input value="${aptitude.prove}" 
						id="prove"
						name="prove"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td>
					<textArea id="remark"
						name="remark"
						rows="12"
					 	data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(aptitude.remark)}</textArea>
				</td>
			</tr>
		</table>
	</div>
</div>
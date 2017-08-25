<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${aptitudeNurse.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${aptitudeNurse.id == null?'添加':'编辑'}</div>
		<table id="aptitudeNurseTable" class="tableStyle">
			<tr>
				<td align="right"width="100">资质ID：</td>
				<td>
					<input value="${aptitudeNurse.aptitudeId}" 
						id="aptitudeId"
						name="aptitudeId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">护士ID：</td>
				<td>
					<input value="${aptitudeNurse.nurseId}" 
						id="nurseId"
						name="nurseId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">审核标识：</td>
				<td>
					<input value="${aptitudeNurse.auditId}" 
						id="auditId"
						name="auditId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">资质商品ID：</td>
				<td>
					<input value="${aptitudeNurse.aptitudeGoodsIds}" 
						id="aptitudeGoodsIds"
						name="aptitudeGoodsIds"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[500]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${area.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${area.id == null?'区域表添加':'区域表编辑'}</div>
		<table id="areaTable" class="tableStyle">
			<tr>
				<td align="right"width="100">护士ID：</td>
				<td>
					<input value="${area.sourceId}" 
						id="sourceId"
						name="sourceId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">区域ID：<label style="color: red;">*</label></td>
				<td>
					<input value="${area.location}" 
						id="location"
						name="location"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[required,maxSize[100]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
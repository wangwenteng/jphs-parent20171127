<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${nurseImages.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${nurseImages.id == null?'添加':'编辑'}</div>
		<table id="nurseImagesTable" class="tableStyle">
			<tr>
				<td align="right"width="100">图片地址：</td>
				<td>
					<input value="${nurseImages.url}" 
						id="url"
						name="url"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[200]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">服务器物理地址：</td>
				<td>
					<input value="${nurseImages.path}" 
						id="path"
						name="path"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[200]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">来源：</td>
				<td>
					<input value="${nurseImages.sourceId}" 
						id="sourceId"
						name="sourceId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[maxSize[50]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
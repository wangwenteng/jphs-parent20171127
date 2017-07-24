<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${feedback.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${feedback.id == null?'添加':'编辑'}</div>
		<table id="feedbackTable" class="tableStyle">
			<tr>
				<td align="right"width="100">内容：</td>
				<td>
					<textArea id="content"
						name="content"
						rows="12"
					 	data-validation-engine="validate[${validates}]">${fn:escapeXml(feedback.content)}</textArea>
				</td>
			</tr>
		</table>
	</div>
</div>
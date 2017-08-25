<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="auditTable" class="tableStyle">
		<tr>
			<td align="right"width="100">审核结果：</td>
			<td>
				<c:out value="${audit.audit}"/>
			</td>
		</tr>
		<tr>
			<td align="right">审核时间：</td>
			<td>
				<fmt:formatDate value="${audit.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">审核人ID：</td>
			<td>
				<c:out value="${audit.auditUserId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">审核备注：</td>
			<td>
				<c:out value="${audit.remark}"/>
			</td>
		</tr>
	</table>
</div>
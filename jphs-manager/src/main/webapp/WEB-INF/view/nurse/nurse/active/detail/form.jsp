<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="nurseActiveTable" class="tableStyle">
		<tr>
			<td align="right"width="100">被封护士ID：</td>
			<td>
				<c:out value="${nurseActive.nurseId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">封号标识：</td>
			<td>
				<c:out value="${nurseActive.active}"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${nurseActive.remark}"/>
			</td>
		</tr>
	</table>
</div>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="nurseOnlineTable" class="tableStyle">
		<tr>
			<td align="right"width="100">护士ID：</td>
			<td>
				<c:out value="${nurseOnline.nurseId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">在线标识：</td>
			<td>
				<c:out value="${nurseOnline.online}"/>
			</td>
		</tr>
		<tr>
			<td align="right">上线时间：</td>
			<td>
				<fmt:formatDate value="${nurseOnline.onlineTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">下线时间：</td>
			<td>
				<fmt:formatDate value="${nurseOnline.offlineTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
		</tr>
		<tr>
			<td align="right">在线时长：</td>
			<td>
				<c:out value="${nurseOnline.timeLong}"/>
			</td>
		</tr>
	</table>
</div>
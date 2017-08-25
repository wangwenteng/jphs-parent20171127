<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/nurse/online/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>护士ID：</td>
			<td width="200">
				<input type="text" name="nurseId" id="nurseId" value="${nurseOnline.nurseId}" />
			</td>
			<td>在线标识：</td>
			<td width="200">
				<input type="text" name="online" id="online" value="${nurseOnline.online}" />
			</td>
			<td>上线时间：</td>
			<td width="200">
				<input type="text" name="onlineTime" id="onlineTime" value="${nurseOnline.onlineTime}" />
			</td>
			<td>下线时间：</td>
			<td width="200">
				<input type="text" name="offlineTime" id="offlineTime" value="${nurseOnline.offlineTime}" />
			</td>
			<td>在线时长：</td>
			<td width="200">
				<input type="text" name="timeLong" id="timeLong" value="${nurseOnline.timeLong}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


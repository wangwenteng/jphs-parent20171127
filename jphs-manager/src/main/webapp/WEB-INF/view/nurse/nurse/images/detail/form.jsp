<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="nurseImagesTable" class="tableStyle">
		<tr>
			<td align="right"width="100">图片地址：</td>
			<td>
				<c:out value="${nurseImages.url}"/>
			</td>
		</tr>
		<tr>
			<td align="right">服务器物理地址：</td>
			<td>
				<c:out value="${nurseImages.path}"/>
			</td>
		</tr>
		<tr>
			<td align="right">来源：</td>
			<td>
				<c:out value="${nurseImages.sourceId}"/>
			</td>
		</tr>
	</table>
</div>
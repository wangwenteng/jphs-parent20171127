<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="aptitudeNurseTable" class="tableStyle">
		<tr>
			<td align="right"width="100">资质ID：</td>
			<td>
				<c:out value="${aptitudeNurse.aptitudeId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">护士ID：</td>
			<td>
				<c:out value="${aptitudeNurse.nurseId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">审核标识：</td>
			<td>
				<c:out value="${aptitudeNurse.auditId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">资质商品ID：</td>
			<td>
				<c:out value="${aptitudeNurse.aptitudeGoodsIds}"/>
			</td>
		</tr>
	</table>
</div>
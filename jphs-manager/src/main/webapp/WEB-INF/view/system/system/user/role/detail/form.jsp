<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt">系统用户角色表</div>
	<table id="systemUserRoleTable" class="tableStyle">
		<tr>
			<td align="right"width="100">用户id：<label style="color: red;">*</label></td>
			<td>
				<c:out value="${systemUserRole.systemUserId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">角色id：<label style="color: red;">*</label></td>
			<td>
				<c:out value="${systemUserRole.systemRoleId}"/>
			</td>
		</tr>
	</table>
</div>
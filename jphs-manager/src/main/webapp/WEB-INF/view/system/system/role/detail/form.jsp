<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt">系统角色表</div>
	<table id="systemRoleTable" class="tableStyle">
		<tr>
			<td align="right"width="100">角色名称：<label style="color: red;">*</label></td>
			<td>
				<c:out value="${systemRole.name}"/>
			</td>
		</tr>
		<tr>
			<td align="right">角色描述：</td>
			<td>
				<c:out value="${systemRole.describe}"/>
			</td>
		</tr>
	</table>
</div>
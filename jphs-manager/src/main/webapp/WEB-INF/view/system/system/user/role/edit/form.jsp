<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<table id="dateTable" style="width: 600px;" cellpadding="0" cellspacing="0"
	class="text-center">
	<thead>
		<tr>
			<th width="30"></th>
			<th>角色名称</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${fn:length(userRole) >0}">
				<c:forEach items="${userRole}" var="e" varStatus="s">
					<tr class="bg_list_body">
						<td width="30"><input type="checkbox" id="roleId"
							name="roleId" value="${e.id }"
							<c:if test="${e.checked }">checked="checked"</c:if> /></td>
						<td><c:out value="${e.name}" /></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
</table>
<input type="hidden" id="userId" name="userId" value="${userId }" />
<input type="hidden" id="roleIds" name="roleIds" />
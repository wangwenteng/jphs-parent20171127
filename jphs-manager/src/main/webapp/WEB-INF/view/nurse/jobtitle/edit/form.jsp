<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<div class="form-group">
			<input type="hidden" id="id" name="id" value="${jobtitle.id}" />
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">职称名称：</label>
			<div class="controls col-md-6">
				<input type="text" value="${jobtitle.name}" id="name"
					name="name" placeholder="职称名称" class="form-control"   required>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">职称类型：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="jobtitleTypeId"
					name="jobtitleTypeId">
					<option value="">全部</option>
					<c:forEach items="${list}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${jobtitle.jobtitleTypeId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</fieldset>
</form>

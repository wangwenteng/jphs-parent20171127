<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<div class="form-group">
			<input type="hidden" id="id" name="id" value="${jobtitleType.id}" />
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">职称名称：</label>
			<div class="controls col-md-6">
				<input type="text" value="${jobtitleType.name}" id="name"
					name="name" placeholder="职称类型名称" class="form-control" maxlength="7"  required>
			</div>
		</div>
	</fieldset>
</form>

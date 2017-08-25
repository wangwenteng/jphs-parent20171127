<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
		<div class="form-group">
			<input type="hidden" id="id"name="id" value="${systemRole.id}">
		<label class="control-label col-md-3" for="input01">角色名称：</label>
			<div class="controls col-md-6">
				<input type="text" value="${systemRole.name}" maxlength="10" id="name" name="name" placeholder="请输入角色名称" class="form-control">
			</div>
		</div>
		<div class="form-group">

			<!-- 后置文本 -->
			<label class="control-label  col-md-3">角色描述：</label>
			<div class="controls  col-md-6  ">
				<div class="input-append input-group">
					<input  value="${systemRole.describe}" id="describe" name="describe" class="span2 form-control" placeholder="请输入角色描述" type="text">
				</div>
			</div>
		</div>

	</fieldset>
</form>

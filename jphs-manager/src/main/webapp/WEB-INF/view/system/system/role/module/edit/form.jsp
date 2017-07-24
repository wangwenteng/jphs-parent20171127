<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	var data = ${data };
</script>
<div class="form-group">
	<label class="control-label col-md-3">模块树11：</label>
	<div class="controls  col-md-6">
		<ul id="tt" class="easyui-tree edit_tree" checkbox="true"
			data-options="animate:true" >
		</ul>
	</div>
	<input type="hidden" id="moduleIds" name="moduleIds">
</div>
<input type="hidden" id="roleId" name="roleId" value="${roleId }" />

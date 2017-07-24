<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div>
	<input type="hidden" id="id" name="id" value="${systemModule.id}" />
	<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
		<div class="title_defalt">${systemModule.id == null?'系统权限表添加':'系统权限表编辑'}</div>
		<table id="systemModuleTable" class="tableStyle">
			<tr>
				<td align="right"width="100">名称：<label style="color: red;">*</label></td>
				<td>
					<input value="${systemModule.name}" 
						id="name"
						name="name"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[required,maxSize[50]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">链接地址：<label style="color: red;">*</label></td>
				<td>
					<input value="${systemModule.url}" 
						id="url"
						name="url"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[required,maxSize[200]]"></input>
				</td>
			</tr>
			<tr>
				<td align="right">上级菜单名称：<label style="color: red;">*</label></td>
				<td>
					<input value="${systemModule.parentId}" 
						id="parentId"
						name="parentId"
						type="text"
						class="inputText"
					 	data-validation-engine="validate[required,maxSize[50]]"></input>
				</td>
			</tr>
		</table>
	</div>
</div>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script type="text/javascript">
	var data = ${data };
</script>
<form class="form-horizontal">
	<fieldset>
		<div class="form-group">
			<input type="hidden" id="id" name="id" value="${platform.id}" />
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">平台名称：</label>
			<div class="controls col-md-6">
				<input type="text" placeholder="平台名称" id="name" name="name"
					placeholder="平台名称" value="${platform.name}" class="form-control" required="required">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">公司名称：</label>
			<div class="controls col-md-6">
				<input type="text" value="${platform.company}" id="company"
					name="company" placeholder="公司名称" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<!-- 下拉列表 -->
			<label class="control-label col-md-3">渠道方式：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="channel"
					name="channel">
					<option value="1"
						<c:if test="${platform.channel==1}">selected="selected"</c:if>>微信</option>
					<option value="2"
						<c:if test="${platform.channel==2}">selected="selected"</c:if>>公众号</option>
					<option value="3"
						<c:if test="${platform.channel==3}">selected="selected"</c:if>>app</option>
					<option value="4"
						<c:if test="${platform.channel==4}">selected="selected"</c:if>>网站</option>
				</select>
			</div>

		</div>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">联系人姓名：</label>
			<div class="controls col-md-6">
				<input type="text" value="${platform.contactsName}"
					id="contactsName" name="contactsName" placeholder="联系人姓名"
					class="form-control">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">联系人手机号：</label>
			<div class="controls col-md-6">
				<input type="text" value="${platform.contactsPhone}"
					id="contactsPhone" name="contactsPhone" placeholder="联系人手机号"
					class="form-control">
			</div>
		</div>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">公司地址：</label>
			<div class="controls col-md-6">
				<input type="text" value="${platform.companyAddress}"
					id="companyAddress" name="companyAddress" placeholder="公司地址"
					class="form-control">
			</div>
		</div>
		<div class="form-group">

			<!-- 文本区域 -->
			<label class="control-label col-md-3">备注：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea type="" id="remark" name="remark" class="form-control">${platform.remark}</textarea>
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="control-label col-md-3">提供服务：</label>
			<div class="controls  col-md-6">
				<ul id="tt" class="easyui-tree edit_tree" checkbox="true"
					data-options="animate:true"
					style="margin-top: 10px; height: 540px; overflow-y: auto;">
				</ul>
			</div>
			<input type="hidden" id="goodsIds" name="goodsIds">
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">关联站点</label>
			<div class="controls    col-md-6">
				<!-- 多选选项 -->
				<c:choose>
					<c:when test="${fn:length(site) >0}">
						<c:forEach items="${site}" var="e" varStatus="s">
							<label class="checkbox">
							<input type="checkbox" id="site" <c:if test="${e.checked}">checked="checked"</c:if>  value="${e.id }"> ${e.name }
							</label>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="20" align="center">没有可显示的记录。</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</div>
			<input type="hidden" id="sites" name="sites">
		</div>
	</fieldset>
</form>

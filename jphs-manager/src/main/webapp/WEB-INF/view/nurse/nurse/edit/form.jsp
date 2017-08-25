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
		<div id="legend" class="">
			<legend class="">${nurse.id == null?'添加护士':'编辑护士'}</legend>
		</div>
		<div class="form-group">
			<input type="hidden" id="id" name="id" value="${nurse.id}" /> <input
				type="hidden" id="user.id" name="user.id" value="${nurse.user.id}"
				required="required" />
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">护士手机号：</label>
			<div class="controls col-md-6">
				<input type="text" value="${nurse.user.phone}" id="phone"
					name="phone" maxlength="11"
					onkeypress='return /^\d$/.test(String.fromCharCode(event.keyCode||event.keycode||event.which))'
					oninput='this.value = this.value.replace(/\D+/g, "")'
					onpropertychange='if(!/\D+/.test(this.value)){return;};this.value=this.value.replace(/\D+/g, "")'
					onblur='this.value = this.value.replace(/\D+/g, "")'
					required="required" placeholder="护士手机号" class="form-control">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">真实姓名：</label>
			<div class="controls col-md-6">
				<input type="text" value="${nurse.user.name}" id="name" name="name"
					maxlength="10" placeholder="真实姓名" class="form-control"
					required="required">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">身份证号：</label>
			<div class="controls col-md-6">
				<input type="text" value="${nurse.sfz}" id="sfz" name="sfz"
					placeholder="身份证号"
					onkeypress='return /^\d$/.test(String.fromCharCode(event.keyCode||event.keycode||event.which))'
					oninput='this.value = this.value.replace(/\D+/g, "")'
					onpropertychange='if(!/\D+/.test(this.value)){return;};this.value=this.value.replace(/\D+/g, "")'
					onblur='this.value = this.value.replace(/\D+/g, "")' maxlength="18"
					class="form-control" required="required">
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">推荐人手机号：</label>
			<div class="controls col-md-6">
				<input type="text" id="friendPhone" name="friendPhone"
					placeholder="推荐人手机号" maxlength="11"
					onkeypress='return /^\d$/.test(String.fromCharCode(event.keyCode||event.keycode||event.which))'
					oninput='this.value = this.value.replace(/\D+/g, "")'
					onpropertychange='if(!/\D+/.test(this.value)){return;};this.value=this.value.replace(/\D+/g, "")'
					onblur='this.value = this.value.replace(/\D+/g, "")'
					class="form-control">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">参加工作时间：</label>
			<div class="controls col-md-6">
				<div style="width: 106%; margin-top: -9px; margin-bottom: -14px;"
					class="input-group date form_date col-md-3" data-date=""
					data-date-format="yyyy-mm-dd" data-link-field="workYears"
					data-link-format="yyyy-mm-dd">
					<input class="form-control" name="workYear" id="workYear"
						style="width: 100%;"
						value="<fmt:formatDate value="${nurse.workYears}"  type="both" pattern="yyyy-MM-dd" />"
						size="14" placeholder="请选择开始工作时间" type="text" value="" readonly>
					<span class="input-group-addon"><span
						class="glyphicon glyphicon-remove" title="清空"></span></span> <span
						class="input-group-addon"><span
						class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input type="hidden"
						name="workYears" id="workYears" value="${nurse.workYears}" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">在职医院：</label>
			<div class="controls col-md-6">
				<input value="${nurse.hospital}" id="hospital" name="hospital"
					type="text" placeholder="在职医院" class="form-control"
					required="required">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">所属科室：</label>
			<div class="controls col-md-6">
				<select class="form-control input-xlarge" id="departmentId"
					name="departmentId">
					<option>全部</option>
					<c:forEach items="${department}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${nurse.departmentId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3" for="input01">联系地址：</label>
			<div data-toggle="distpicker">
				<div class="form-group nurse_width"
					style="width: 24%; margin-left: 14px;">
					<label class="sr-only" for="province1">Province</label> <select
						class="form-control" id="province1" name="province1"
						style="width: 77%;"></select>
				</div>
				<div class="form-group nurse_width" style="width: 30%;">
					<label class="sr-only" for="city1">City</label> <select
						class="form-control" id="city1" name="city1" style="width: 70%"></select>
				</div>
				<div class="form-group nurse_width"
					style="width: 30%; margin-left: -38px;">
					<label class="sr-only" for="district1">District</label> <select
						class="form-control" id="district1" id="district1"
						style="width: 60%;"></select>
				</div>
			</div>
			<div class="controls col-md-6" style="margin-bottom: -20px;">
				<input type="text" id="adressName" name="adressName"
					value="${addressName}" placeholder="街道信息" class="form-control"
					required="required">
			</div>
			<input type="hidden" id="address" name="address">
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">服务区域：</label>
			<div class="controls  col-md-6">
				<ul id="tt" class="easyui-tree edit_tree" checkbox="true" data-options="animate:true"
					style="margin-top: 10px; height: 540px; overflow-y: auto;">
				</ul>
			</div>
			<input type="hidden" id="areas" name="areas">
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">性别：</label>
			<div class="controls  col-md-6">
				<!-- 单行单选项目 -->
				<label class="radio radio-inline"> <input type="radio"
					value="0" name="sex" id="sex"
					<c:if test="${nurse.user.sex==0}">checked="checked"</c:if>>
					男
				</label> <label class="radio radio-inline"> <input type="radio"
					value="1" name="user.sex" id="user.sex"
					<c:if test="${nurse.user.sex==1}">checked="checked"</c:if>>
					女
				</label>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">头像：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="head_portraits"
					name="head_portraits" src="${nurse.head_portrait}"> <input
					class="input-file" type="file" name="myfiles" id="head_portrait_s"
					onchange="ajaxFileUpload('head_portrait_s','head_portrait');" /> <input
					class="input-file" type="hidden" value="${nurse.head_portrait}"
					id="head_portrait" name="head_portrait" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">护士资格证：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-3">
				<img alt="" height="200" width="200" id="aptitude_positives"
					src="${nurse.aptitude_positive}"> <input class="input-file"
					type="file" name="myfiles" id="aptitude_positive_s"
					onchange="ajaxFileUpload('aptitude_positive_s','aptitude_positive');" />
				<input class="input-file" type="hidden"
					value="${nurse.aptitude_positive}" id="aptitude_positive"
					name="aptitude_positive" />
			</div>
			<div class="controls col-md-3" style="margin-left: 70px;">
				<img alt="" height="200" width="200" id="aptitude_negatives"
					src="${nurse.aptitude_negative}"> <input class="input-file"
					type="file" name="myfiles" id="aptitude_negative_s"
					onchange="ajaxFileUpload('aptitude_negative_s','aptitude_negative');" />
				<input class="input-file" type="hidden"
					value="${nurse.aptitude_negative}" id="aptitude_negative"
					name="aptitude_negative" />
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">身份证：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-3">
				<img alt="" height="200" width="200" id="id_positives"
					src="${nurse.id_positive}"> <input class="input-file"
					type="file" name="myfiles" id="id_positive_s"
					onchange="ajaxFileUpload('id_positive_s','id_positive');" /> <input
					class="input-file" type="hidden" value="${nurse.id_positive}"
					id="id_positive" name="id_positive" />
			</div>
			<div class="controls col-md-3" style="margin-left: 70px;">
				<img alt="" height="200" width="200" id="id_negatives"
					src="${nurse.id_negative}"> <input class="input-file"
					type="file" name="myfiles" id="id_negative_s"
					onchange="ajaxFileUpload('id_negative_s','id_negative');" /> <input
					class="input-file" type="hidden" value="${nurse.id_negative}"
					id="id_negative" name="id_negative" />
			</div>
		</div>
		<!-- <div class="form-group">
			<label class='control-label col-md-3'>头像</label>
			<div class='controls  col-md-8'>
				<div class="file-preview img_border">
					<div id="edit_storeimg"></div>
					<div class="clear" style="clear: both"></div>
				</div>
				<input id="file-0a" class="file" type="file" name="file" multiple
					data-min-file-count="1"> <input name="orgSuiteImgs"
					id="imgs" hidden="hidden" />
			</div>
		</div> -->
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">个人简介：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="brief" name="brief" rows="12" class="form-control">${fn:escapeXml(nurse.user.brief)}</textarea>
				</div>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本区域 -->
			<label class="control-label col-md-3">详情：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					<textarea id="details" name="details" rows="12"
						class="form-control">${fn:escapeXml(nurse.details)}</textarea>
				</div>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="control-label col-md-3">职称</label>
			<div id="type" class="clearfix">
				<c:forEach items="${list }" var="e" varStatus="s">
					<button  type="button"
						class="public-info public_btn public_btn_left"
						onclick="tr_product('${s.index}');">${e.name }</button>
				</c:forEach>

			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3"></label>
			<div class="clearfix">
				<div class="controls  col-md-8">
					<c:forEach items="${list }" var="e" varStatus="s">
						<c:if test="${s.index==0 }">
							<div  class="jobtitleType${s.index }" >
								<c:forEach items="${e.jobtitle }" var="f" varStatus="t">
									<label class="radio radio-inline"> <input type="radio"
										value="${f.id }" name="jobtitleType[${s.index }].jobtitle[${t.index }].id" id="jobtitleType${s.index }"
										<c:if test="${f.checked	}">checked="checked"</c:if>>
										${f.name }
									</label>
								</c:forEach>
							</div>
						</c:if>
						<div  class="jobtitleType${s.index }" style="display: none;">
							<c:if test="${s.index!=0 }">
								<c:forEach items="${e.jobtitle }" var="f" varStatus="t">
									<label class="radio radio-inline"> <input type="radio"
										value="${f.id }" name="jobtitleType[${s.index }].jobtitle[${t.index }].id" id="jobtitleType${s.index }"
										<c:if test="${f.checked	}">checked="checked"</c:if>>
										${f.name }
									</label>
								</c:forEach>
							</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div> --%>
	</fieldset>
</form>

<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal edit_form">
	<fieldset>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">所属商家：</label>
			<div class="controls col-md-6">
				<select class="marage_select" id="businessId" name="businessId">
					<c:forEach items="${businessList}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${commodity.businessId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">所属品类：</label>
			<div class="controls col-md-6">
				<select class="marage_select" id="commodityTypeId"
					name="commodityTypeId">
					<c:forEach items="${commodityTypeList}" var="e" varStatus="s">
						<option value="${e.id }"
							<c:if test="${commodity.commodityTypeId==e.id}">selected="selected"</c:if>>${e.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>


		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">标题：</label>
			<div class="controls col-md-6">
				<input value="${commodity.title}" id="title" name="title"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">副标题，简介：</label>
			<div class="controls col-md-6">
				<input value="${commodity.subTitle}" id="subTitle" name="subTitle"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[500]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">型号/规格：</label>
			<div class="controls col-md-6">
				<input value="${commodity.model}" id="model" name="model"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[255]]"></input>
			</div>
		</div>


		<%-- <div class="form-group">
			 
			<label class="control-label col-md-4" for="input01">内容：</label>
			<div class="controls col-md-6">
				<textArea id="content" name="content" rows="12"
					data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(commodity.content)}</textArea>
			</div>
		</div> --%>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">正品认证（0否，1是）：</label>
			<div class="controls col-md-6">
				<input value="${commodity.quality}" id="quality" name="quality"
					type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">隐私配送（0否，1是）：</label>
			<div class="controls col-md-6">
				<input value="${commodity.privacy}" id="privacy" name="privacy"
					type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">药检认证（0否，1是）：</label>
			<div class="controls col-md-6">
				<input value="${commodity.security}" id="security" name="security"
					type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
<%-- 
		<div class="form-group">
			 
			<label class="control-label col-md-4" for="input01">参数：</label>
			<div class="controls col-md-6">
				<textArea id="parameter" name="parameter" rows="12"
					data-validation-engine="validate[maxSize[65535]]">${fn:escapeXml(commodity.parameter)}</textArea>
			</div>
		</div> --%>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">是否支持使用优惠券（0不支持，1支持）：</label>
			<div class="controls col-md-6">
				<input value="${commodity.supportVoucher}" id="supportVoucher"
					name="supportVoucher" type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>

		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">限购数量：</label>
			<div class="controls col-md-6">
				<input value="${commodity.limitNumber}" id="limitNumber"
					name="limitNumber" type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">保护期(天）：</label>
			<div class="controls col-md-6">
				<input value="${commodity.protectDay}" id="protectDay"
					name="protectDay" type="text" class="inputText"
					data-validation-engine="validate[custom[integer]]"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-4">商品PC端图片：</label>
			<!-- 文件上传 -->
			<div class="controls col-md-6">
				<img alt="" height="200" width="200" id="pcurls"
					src="${pc_image.url}" /><span>尺寸 360*350 <br>*&nbsp;图片格式必须为.png格式
				</span> <input class="input-file" type="file" name="myfiles" id="pcurl_s"
					onchange="ajaxFileUpload('pcurl_s','url');" /> <input
					class="input-file" type="hidden" id="url" name="url" value="${url}" />
			</div>
		</div>

		
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-4" for="input01">备注：</label>
			<div class="controls col-md-6">
				<input value="${commodity.remark}" id="remark" name="remark"
					type="text" class="inputText"
					data-validation-engine="validate[maxSize[500]]"></input>
			</div>
		</div>
		<div class="public_editor">
			<h5>商品详情：</h5>
			<p>
				<textarea name="content" id="myEditor" class="form-control"
					style="width: 805px; height: 500px;"></textarea>
			</p>
		</div>
		
		
		
		<div class="norm_service" <%-- style="display: ${goods.gradeType == 0 || goods.gradeType == null ?'block':'none'};" --%> >
			<div class="price_gradeAdd">
				<div class="marage_right_content"  style="margin-top:0;padding-top:0">
					<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">
						<tbody id="addtrprice0">
							<tr class="headClass">
								<td width="100">序号</td>
								<td width="60">商品SKU</td>
								<!-- <td width="60px" >销售价格</td> -->
								<td width="60" >成本价</td>
								<td width="60" >原价</td>
								<td width="60" >销售价</td>
								<td width="60" >分销收益</td>
								<td width="60" >库存</td>
								<td width="60" >状态</td>
								<td width="60" >操作</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_add" onclick="addTd(0);">+</div>
					</div>
				</div>
			</div>
	</fieldset>
</form>
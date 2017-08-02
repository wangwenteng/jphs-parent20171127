<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6">

		<p>
			<img alt="" height="200" width="200" id="head_portraits"
				src="${nurse.head_portrait}">
		</p>
		<p>
			<img alt="" width="120" id="id_positives" src="${nurse.id_positive}">
			<img alt="" width="120" id="id_negatives" src="${nurse.id_negative}">
		</p>
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p>
			<span>姓名：</span>${nurse.user.name}</p>
		<p>
			<span>性别：</span>
			<c:if test="${nurse.user.sex==0}">男</c:if>
			<c:if test="${nurse.user.sex==1}">女</c:if>
		</p>
		<p>
			<span>年龄：</span>24
		</p>
		<p>
			<span>手机号：</span>${nurse.user.phone}</p>
		<p>
			<span>身份证号：</span>${nurse.sfz}</p>
		<p title="${nurse.address}">
			<span>联系地址：</span>
			<c:choose>
				<c:when test="${fn:length(nurse.address) > 19}">
					<c:out value="${fn:substring(nurse.address, 0, 19)}......" />
				</c:when>
				<c:otherwise>
					<c:out value="${e.companyAddress}" />
				</c:otherwise>
			</c:choose>
		</p>
		<p>
			<span>推荐人：</span>${nurse.recommendName}
		</p>
		<p>
			<span>注册时间：</span>
			<fmt:formatDate value="${nurse.createTime}" pattern="yyyy-mm-dd" />
		</p>
		<p>
			<span>个人简介：</span>${fn:escapeXml(nurse.brief)}</p>
		<p>
			<span>备注：</span>${fn:escapeXml(nurse.details)}</p>

	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">工作信息</p>
	<div class="col-md-6">
		<p>
			<span>工作时间：</span>
			<fmt:formatDate value="${nurse.workYears}" type="both"
				pattern="yyyy-mm-dd" />
		</p>
		<p>
			<span>在职医院：</span>${nurse.hospital}</p>
		<p>
			<span>科室：</span>
			<c:forEach items="${department}" var="e" varStatus="s">
				<c:if test="${nurse.departmentId==e.id}">${e.name }</c:if>
			</c:forEach>
		</p>
		<p>
			<img alt="" width="120" id="aptitude_positives"
				src="${nurse.aptitude_positive}"> <img alt="" width="120"
				id="aptitude_negatives" src="${nurse.aptitude_negative}">

		</p>
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p>
			<span>护士分类：</span>
			护士
		</p>
		<p>
			<span>职称：</span>副主任护师
		<p>
			<span>服务区域：</span>
		<table style="width: 300px;">
			<c:forEach items="${nurse.location}" var="e" varStatus="s">
				<tr>
					<c:set value="${ fn:split(e.content, '-') }" var="str1" />
					<c:forEach items="${ str1 }" var="s">
						<td>${s }</td>
					</c:forEach>

				</tr>
			</c:forEach>
		</table>
	</div>
</div>
<div class="from-group">
	<table id="dateTable" cellpadding="0" style="width: 720px;"
		cellspacing="0" class="text-center">
		<thead>
			<tr>
				<th width="30"></th>
				<th>审核结果</th>
				<th>审核时间</th>
				<th>审核人</th>
				<th>审核备注</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(audit) >0}">
					<c:forEach items="${audit}" var="e" varStatus="s">
						<tr class="bg_list_body">
							<td width="30">${s.index+1}</td>
							<td><c:if test="${e.audit==0 }">未通过</c:if> <c:if
									test="${e.audit==1 }">已认证</c:if></td>
							<td><fmt:formatDate value="${e.auditTime}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${e.auditUserName}" /></td>
							<td><c:out value="${e.remark}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="20" align="center">没有可显示审核记录。</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">审核信息</h4>
			</div>
			<div class="modal-body">
				<form id="auditForm" method="post" class="form-horizontal" action="/audit/insert.jhtml">
					<input type="hidden" id="creatorId" name="creatorId" value="${nurse.user.id}" /> 
					<input type="hidden" id="creatorName" name="creatorName" value="${nurse.user.name}" />
					<div class="form-group">
						<label class="control-label col-md-3">审核意见</label>
						<div class="controls  col-md-6">
							<!-- 单行单选项目 -->
							<label class="radio radio-inline"> <input type="radio"
								value="1" name="audit" checked="checked"> 通过
							</label> <label class="radio radio-inline"> <input type="radio"
								value="0" name="audit"> 不通过
							</label>
						</div>
					</div>
					<div class="form-group">
						<!-- 文本区域 -->
						<label class="control-label col-md-3">审核结果</label>
						<div class="controls col-md-6">
							<div class="textarea">
								<textarea type="" id="remark" name="remark" class="form-control"></textarea>
							</div>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="submit" id="save" class="btn btn-primary">提交</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>

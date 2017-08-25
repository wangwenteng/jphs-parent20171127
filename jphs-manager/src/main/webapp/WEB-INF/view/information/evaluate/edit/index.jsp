<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />信息管理<i class="public1-horn-45"></i>资讯管理 <i class="public1-horn-45"></i>评论管理 <i class="public1-horn-45"></i>${informationEvaluate.id == null?'审核评论':'审核评论'}
</div>
<form class="form-horizontal add_body" id="productForm" method="post" data-role="validate-form" action="/informationEvaluate/insert.jhtml">
	<div class="marage_right_content">
		<div class="clearfix">
			<input type="button" class="public-info public_btn public_btn_left" name="button1" id="button1" value="返回" onclick="history.go(-1)" />
			<input type="button" class="public-info public_btn public_btn_left" name="button2" id="button2" value="审核" data-toggle="modal" data-target="#myModal"/>
			<c:if test="${informationEvaluate.essence == -1 }">
				<input type="button" class="public-info public_btn public_btn_left" name="button2" id="button2" value="加精" onclick="essenceById('${informationEvaluate.id}','${informationEvaluate.essence }')"/>
			</c:if>
			<c:if test="${informationEvaluate.essence == 0 }">
				<input type="button" class="public-info public_btn public_btn_left" name="button2" id="button2" value="取消加精" onclick="essenceById('${informationEvaluate.id}','${informationEvaluate.essence }')"/>
			</c:if>
		</div>
		<hr class="mt-5" />
		<jsp:include page="form.jsp"></jsp:include>
	</div>
</form>

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
				<form id="auditForm" method="post" class="form-horizontal" action="/information/evaluate/insert.jhtml">
					<input type="hidden" id="auditarr" name="auditarr" value="${informationEvaluate.id}" /> 
					<div class="form-group">
						<label class="control-label col-md-3">审核结果</label>
						<div class="controls  col-md-6">
							<!-- 单行单选项目 -->
							<label class="radio radio-inline">
								<input type="radio" value="0" name="audit" checked="checked"> 通过
							</label> <label class="radio radio-inline">
								<input type="radio" value="1" name="audit"> 不通过
							</label>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" id="save" class="btn btn-primary">提交</button>
			</div>
		</div>
	</div>
</div>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<form class="form-horizontal">
	<fieldset>
	
	<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">审核状态：</label>
			<div class="controls col-md-6">
				<c:if test="${informationEvaluate.status == 1 }">
					已审核-未通过
				</c:if>
				<c:if test="${informationEvaluate.status == 0 }">
					已审核-通过
				</c:if>
				<c:if test="${informationEvaluate.status == -1 }">
					待审核
				</c:if>
			</div>
		</div>
	
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">评论人：</label>
			<div class="controls col-md-6">
				${informationEvaluate.creatorName }
			</div>
		</div>
		<div class="form-group">
			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">评论时间：</label>
			<div class="controls col-md-6">
				<fmt:formatDate value="${informationEvaluate.createTime}" pattern="yy-MM-dd HH:mm:ss" />
			</div>
		</div>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">头像：</label>
			<div class="controls col-md-6">
				<img alt="" style="width: 100px;height: 100px;" src="${informationEvaluate.headPicture}">
			</div>
		</div>
		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">咨询名称：</label>
			<div class="controls col-md-6">
				${information.title}
			</div>
		</div>

		<div class="form-group">

			<!-- 文本区域 -->
			<label class="control-label col-md-3">评论内容：</label>
			<div class="controls col-md-6">
				<div class="textarea">
					${informationEvaluate.content }
				</div>
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">创建设备：</label>
			<div class="controls col-md-6">
				${informationEvaluate.device }
			</div>
		</div>

		<div class="form-group">

			<!-- 文本输入 -->
			<label class="control-label col-md-3" for="input01">精华：</label>
			<div class="controls col-md-6">
				<c:if test="${informationEvaluate.essence ==0 }">
					精华
				</c:if>
				<c:if test="${informationEvaluate.essence ==-1 }">
					非精华
				</c:if>
			</div>
		</div>

	</fieldset>
</form>
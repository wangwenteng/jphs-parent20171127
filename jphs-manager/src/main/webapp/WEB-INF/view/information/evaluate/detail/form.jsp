<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<%-- <fieldset>
	<div class="form-group">

		<!-- 文本输入 -->
		<label class="control-label col-md-3" for="input01">评论人：</label>
		<div class="controls col-md-6">
			${informationEvaluate.creatorName }</div>
	</div>
	<div class="form-group">
		<!-- 文本输入 -->
		<label class="control-label col-md-3" for="input01">评论时间：</label>
		<div class="controls col-md-6">
			<fmt:formatDate value="${informationEvaluate.createTime}"
				pattern="yy-MM-dd HH:mm:ss" />
		</div>
	</div>
	<div class="form-group">

		<!-- 文本输入 -->
		<label class="control-label col-md-3" for="input01">头像：</label>
		<div class="controls col-md-6">
			<img alt="" style="width: 100px; height: 100px;"
				src="${informationEvaluate.headPicture}">
		</div>
	</div>
	<div class="form-group">

		<!-- 文本输入 -->
		<label class="control-label col-md-3" for="input01">咨询名称：</label>
		<div class="controls col-md-6">${information.title}</div>
	</div>

	<div class="form-group">

		<!-- 文本区域 -->
		<label class="control-label col-md-3">评论内容：</label>
		<div class="controls col-md-6">
			<div class="textarea">${informationEvaluate.content }</div>
		</div>
	</div>

	<div class="form-group">

		<!-- 文本输入 -->
		<label class="control-label col-md-3" for="input01">创建设备：</label>
		<div class="controls col-md-6">${informationEvaluate.device }</div>
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

</fieldset> --%>


<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" >
		<p>
			<span>评论人：</span>${informationEvaluate.creatorName}</p>
		<p>
			<span>咨询名称：</span> 
			${information.title}
		</p>
		<p>
			<span>创建设备：${informationEvaluate.device}</span>
		</p>
		<p><span>是否精华：</span> 
			<c:if test="${informationEvaluate.essence ==0 }">
					精华
				</c:if>
			<c:if test="${informationEvaluate.essence ==-1 }">
					非精华
				</c:if>
		</p>
		<p>
			<span>创建时间：</span><fmt:formatDate value="${informationEvaluate.createTime}" pattern="yy-MM-dd HH:mm:ss" /></p>

	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<span>头像：</span>
		<p>
			<img alt="" height="200" width="200" src="${informationEvaluate.headPicture}">
		</p>
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi"><span>评论内容：</span></p>
	<p class="">${informationEvaluate.content }</p>
</div>
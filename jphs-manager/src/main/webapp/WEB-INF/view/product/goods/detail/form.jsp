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
			<span>服务名称：</span>${goods.title}</p>
		<p>
			<span>服务简介：</span>${goods.subTitle}
		</p>
		<p>
			<span>服务品类：</span>${goods.product.title}

		</p>
		<p>
			<span>是否需要服务工具：</span>
			<!-- 单行单选项目 -->
			<c:if test="${goods.tool == 1}">
				需要
				</c:if>
			<c:if test="${goods.tool == 0}">
				 不需要
				</c:if>
		</p>
		<p>
			<span>是否需要上保险：</span>
			<c:if test="${goods.insurance == 1}">
					需要
				</c:if>
			<c:if test="${goods.insurance == 0}">
				 不需要
				</c:if>
		</p>
		<p>
			<span>接单方式：</span>
			<c:if test="${goods.type == 1}">
					内部
				</c:if>
			<c:if test="${goods.type == 0}">
					公开
				</c:if>

		</p>
		<p>
			<span>状态：</span>
			<c:if test="${goods.status == 0}">
					使用中
				</c:if>
			<c:if test="${goods.status == -1}">
				 	已停用
				</c:if>

		</p>
		<p>
			<span>排序：</span>${goods.sort}
		</p>
		<p>
			<span>创建人：</span>${goods.creatorName}
		</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${goods.createTime}"
								pattern="yy-MM-dd HH:mm" />
		</p>

	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p>
			<span>移动端图标：</span> <img alt="" height="200" width="200" id="pcurls"
				src="${wap_image.url}">
		</p>
		<p>
			<span>电脑端图标：</span> <img alt="" height="200" width="200"
				id="moveurls" src="${pc_image.url}">

		</p>
	</div>
	<p class="details_box_xinxi">服务套餐</p>
	<jsp:include page="setmeal.jsp"></jsp:include>

	<p class="details_box_xinxi">服务详情</p>
	${goods.content}

</div>

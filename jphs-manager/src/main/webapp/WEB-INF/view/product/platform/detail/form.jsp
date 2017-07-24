<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6" >
		<p>
			<span>平台名称：</span>${platform.name}</p>
		<p>
			<span>公司名称：</span> ${platform.company}
		</p>
		<p>
			<span>渠道：</span>
			<c:if test="${platform.channel==1}">微信</c:if>
			<c:if test="${platform.channel==2}">公众号</c:if>
			<c:if test="${platform.channel==3}">APP</c:if>
			<c:if test="${platform.channel==4}">网站</c:if>
		</p>
		<p>
			<span>联系人姓名：</span>${platform.contactsName}</p>
		<p>
			<span>联系人手机号：</span>${platform.contactsPhone}</p>

	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p>
			<span>公司地址：</span>${platform.companyAddress}</p>
		<p>
			<span>创建人：</span>${platform.creatorName}</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${platform.createTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</p>
		<p>
			<span>授权码：</span>${platform.authCode}</p>
		<p>
			<span>备注：</span>${platform.remark}</p>

	</div>
</div>
<div class="detail_fuwu">
	<p class="details_box_xinxi">提供服务</p>
	<c:choose>
		<c:when test="${fn:length(list) >0}">
			<c:forEach items="${list}" var="e" varStatus="s">
				<div class="details_fuwu_box">
					<p>${e.text}：</p>
					<ul class="clearfix">
						<c:choose>
							<c:when test="${fn:length(e.children) >0}">
								<c:forEach items="${e.children}" var="f" varStatus="t">
									<c:if test="${f.checked}"><li>${f.text}</li></c:if>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<li>没有绑定该品类的服务。</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p>没有添加相关商品</p>
		</c:otherwise>
	</c:choose>
</div>
<div class="detail_fuwu">
	<p class="details_box_xinxi">关联站点：</p>
	<div class="details_fuwu">
		<c:choose>
			<c:when test="${fn:length(site) >0}">
				<c:forEach items="${site}" var="e" varStatus="s">
					<c:if test="${e.checked}"><span>${e.name}</span></c:if>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<span>没有绑定相关的站点。</span>
			</c:otherwise>
		</c:choose>
	</div>
</div>
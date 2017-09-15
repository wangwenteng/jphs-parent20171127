<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">基本信息</p>
	<div class="col-md-6">
	
		<p><img alt="" height="200" width="200" id="head_portraits" src="${nurse.head_portrait}"></p>
		<p>
			<img alt="" width="120" id="id_positives"
				src="${nurse.id_positive}">	
			<img alt="" width="120" id="id_negatives"
				src="${nurse.id_negative}">
		</p>		
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p><span>姓名：</span>${nurse.user.name}</p>
		<p><span>性别：</span>
		<c:if test="${nurse.user.sex==0}">男</c:if>
		<c:if test="${nurse.user.sex==1}">女</c:if>
		</p>
		<p><span>年龄：</span><c:set var="idcard" value="${fn:substring(nurse.sfz,6,10) }" />
							<jsp:useBean id="nowDate" class="java.util.Date" /> <fmt:formatDate
								var="nowStr" value="${nowDate}" pattern="yyyy" />
							<c:if test="${nowStr-idcard<=0 }">1</c:if> <c:if
								test="${nowStr-idcard>0 }">${nowStr-idcard}</c:if></p>
			<p><span>手机号：</span>${nurse.user.phone}</p>
		<p><span>身份证号：</span>${nurse.sfz}</p>
		<p><span>联系地址：</span>${nurse.user.address}</p>
		<p><span>推荐人：</span>${nurse.recommendName}</p>
		<p><span>注册时间：</span><fmt:formatDate value="${nurse.createTime}" pattern="yyyy-MM-dd"/></p>
		<p><span>个人简介：</span>${fn:escapeXml(nurse.user.brief)}</p>
		<p><span>备注：</span>${fn:escapeXml(nurse.details)}</p>
		
	</div>
</div>
<div class="details_box clearfix">
	<p class="details_box_xinxi">工作信息</p>
	<div class="col-md-6">
		<p><span>工作时间：</span><fmt:formatDate value="${nurse.workYears}" type="both"
			pattern="yyyy-MM-dd" /></p>
		<p><span>在职医院：</span>${nurse.hospital}</p>
		<p><span>科室：</span>
		<c:forEach items="${department}" var="e" varStatus="s">
			<c:if test="${nurse.departmentId==e.id}">${e.name }</c:if>
		</c:forEach></p>
		<p>
			<img alt="" width="120" id="aptitude_positives"
				src="${nurse.aptitude_positive}">			
			<img alt="" width="120" id="aptitude_negatives"
				src="${nurse.aptitude_negative}">
			
		</p>
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p><span>护士分类：</span>
			护士
		</p>
		<p><span>职称：</span>护士</p>
		<p><span>服务区域：</span>
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

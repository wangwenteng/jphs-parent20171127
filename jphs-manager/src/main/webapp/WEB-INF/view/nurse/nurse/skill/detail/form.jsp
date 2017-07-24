<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">技能信息</p>
	<div class="col-md-6" style="width: 460px;height: 400px;margin-left: -20px">
		<p>
			<c:choose>
				<c:when test="${fn:length(nurseSkill.nurseImage) >0}">
					<c:forEach items="${nurseSkill.nurseImage}" var="e" varStatus="s">
						<div class="controls col-md-3"
							style="float:left;width: 200px; <c:if test="${(s.index+1)%2!=1 }">margin-left: 10px;</c:if> ">
							<img alt="" height="200" width="200" id="image_url${s.index+1}s"
								src="${e.url}"> 
							<input class="input-file" type="hidden" value="${e.url}"
								id="image_url${s.index+1}" name="nurseImage[${s.index}].url" />
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>	
		</p>
		<p>
			<img alt="" width="120" id="id_positives"
				src="${nurse.id_positive}">	
			<img alt="" width="120" id="id_negatives"
				src="${nurse.id_negative}">
		</p>		
	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p><span>姓名：</span>${nurseSkill.creatorName}</p>
		<p><span>技能名称：</span>
			<c:forEach items="${skill}" var="e" varStatus="s">
				<c:if test="${nurseSkill.skillId==e.id}">${e.name }</c:if>
			</c:forEach>
		</p>
		
		<p><span>发布价格：</span>${nurseSkill.price}</p>
		<p><span>平台价格：</span>${nurseSkill.skill.amount}</p>
		<p><span>服务区域：</span>${nurseSkill.location.content}</p>
		<p><span>空闲时间：</span>${nurseSkill.freeCycle}--${nurseSkill.leisureTime}</p>
		<p><span>发布时间：</span><fmt:formatDate value="${nurseSkill.createTime}" pattern="yyyy-mm-dd"/></p>
	</div>
</div>

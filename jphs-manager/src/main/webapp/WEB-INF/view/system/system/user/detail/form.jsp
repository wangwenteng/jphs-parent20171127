<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="details_box clearfix">
	<p class="details_box_xinxi">用户信息</p>
	<div class="col-md-6" >
		<p>
			<span>用户姓名：</span>${systemUser.name}</p>
		<p>
			<span>邮箱：</span> ${systemUser.email}
		</p>
		<p>
			<span>手机号：</span>${systemUser.phone}</p>

	</div>
	<div class="col-md-6" style="border-left: 1px solid #e0e0e0;">
		<p>
			<span>创建人：</span>${systemUser.creatorName}</p>
		<p>
			<span>创建时间：</span>
			<fmt:formatDate value="${systemUser.createTime}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</p>
	</div>
</div>

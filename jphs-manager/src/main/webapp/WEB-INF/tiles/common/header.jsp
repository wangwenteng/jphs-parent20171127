<%@page trimDirectiveWhitespaces="true"%>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.jinpaihushi.com/jsp/core" prefix="jphs"%>
<header class="manager_header">
	<img src="/static/images/logo.png" class="public-logo" />
	<ul>
		<li class="li1"><a href="#">首页</a></li>
		<li class="li8"><a href="/user/index.jhtml">会员管理</a></li>
		<li class="li2"><a href="/nurse/index.jhtml">护士管理</a></li>
		<li class="li3"><a href="/order/index.jhtml?schedule=0">订单管理</a></li>
		<jphs:hasPermission url="/platform/index.jhtml">
			<li class="li4"><a href="/platform/index.jhtml">产品管理</a></li>
		</jphs:hasPermission>
		<li class="li7"><a href="/voucher/index.jhtml">活动管理</a></li>
		<li class="li10"><a href="/information/channel/index.jhtml">信息管理</a></li>
		<li class="li9"><a href="/system/module/index.jhtml">系统管理</a></li>
		<jphs:hasPermission url="/access/log/showDataByDay.jhtml">
			<li class="li5"><a href="/access/log/showDataByDay.jhtml">统计分析</a></li>
		</jphs:hasPermission>
<%-- 		<li class="li6"><span>${sessionScope.session_user.name }</span> <span><img
				src="/static/images/sanjiaox.png" /></span></li> --%>
		<li class="dropdown"><a href="#" class="dropdown-toggle"
			data-toggle="dropdown" role="button" aria-haspopup="true"
			aria-expanded="false"><span>${sessionScope.session_user.name }</span> <span><img
				src="/static/images/sanjiaox.png" /></span></a>
			<ul class="dropdown-menu">
				<li><a href="/loginOut.jhtml" onclick="logout()">退出</a></li>
			</ul></li>
	</ul>
</header>
<script>
	$(".manager_header ul li").click(function() {
		$(".manager_header ul li").removeClass("active");
		$(this).addClass("active");
	})
</script>

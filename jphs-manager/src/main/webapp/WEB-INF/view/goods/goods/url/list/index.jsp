<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<script>
	var umodule = new UModule("/goods/url");
</script>
<div style="margin: 10px 10px 10px 10px;">
	<div class="zw_dqwz">
		您目前所在的位置是：<b><c:out value="${system_name}"></c:out>&nbsp;&gt;&gt;&nbsp;xx管理&nbsp;&gt;&gt;&nbsp;<label
			id="nav_current">xx管理</label> </b>
	</div>
	<jsp:include page="search.jsp"></jsp:include>
	<div class="siteOperate">
		<div class="siteOperate_a">
			<div class="operate_btn_left">
				<afocus:privilege url="/goods/url/add.jhtml">
					<a href="/goods/url/add.jhtml" data-role="add-btn">添加</a>
				</afocus:privilege>
			</div>
		</div>
		<div id="siteBody">
			<jsp:include page="list.jsp"></jsp:include>
		</div>
	</div>
</div>
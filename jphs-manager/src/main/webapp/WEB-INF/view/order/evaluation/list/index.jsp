<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" />订单管理<i class="public1-horn-45"></i>服务订单<i class="public1-horn-45"></i>评价管理<i class="public1-horn-45"></i>评价列表 
</div>
<div class="marage_right_title">
	<jsp:include page="search.jsp"></jsp:include>
</div>

<div class="marage_right_content">
	<!-- 左对齐按钮 -->
	<!-- <button type="button" class="btn btn-info public_btn">导出</button>	 -->
	<!-- 右对齐按钮 -->
	<jsp:include page="list.jsp"></jsp:include>

</div>
<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" action="/voucher/addUser.jhtml" >
	<div class="clearfix">
		 <input id="id" name="id" value="${voucherRepertory.id}" >
		 <input type="text" class="form-control" name="name" id="name" value="${user.name }">
		 <input type="text" class="form-control" name="phone" id="phone" value="${user.phone }">
	</div>
</form>


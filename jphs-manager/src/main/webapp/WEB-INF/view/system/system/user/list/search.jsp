<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get"  action="/system/user/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>用户姓名：</td>
			<td width="200">
				<input type="text" placeholder="请输入用户姓名" name="name" id="name" value="${systemUser.name}" />
			</td>
			<td>公司邮箱：</td>
			<td width="200">
				<input type="text" placeholder="请输入用户邮箱" name="email" id="email" value="${systemUser.email}" />
			</td>
		</tr>
		<tr>
			<%-- <td>密码：</td>
			<td width="200">
				<input type="text" name="password" id="password" value="${systemUser.password}" />
			</td> --%>
			<td>手机号：</td>
			<td width="200">
				<input type="text"  placeholder="请输入用户手机号" name="phone" id="phone" value="${systemUser.phone}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


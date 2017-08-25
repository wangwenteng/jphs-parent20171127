<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get"  action="/advertising/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<%-- <td>id：</td>
			<td width="200">
				<input type="text" name="id" id="id" value="${advertising.id}"  placeholder="请输入轮播图id"/>
			</td> --%>
			<td>渠道：</td>
			<td width="200">
				<select class="form-control input-xlarge"  name="type" id="type" >
					<option value="">请选择</option>
					<option value="1"
						<c:if test="${advertising.type==1}">selected="selected"</c:if>> app护士端</option>
					<option value="2"
						<c:if test="${advertising.type==2}">selected="selected"</c:if>>app用户端</option>
					<option value="3"
						<c:if test="${advertising.type==3}">selected="selected"</c:if>>官网首页</option>
					<option value="4"
						<c:if test="${advertising.type==4}">selected="selected"</c:if>>app资讯</option>
				</select>
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


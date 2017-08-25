<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<form id="serach-form" method="get" action="/platform/index.jhtml">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>平台名称：</td>
				<td width="200"><input type="text" id="name" name="name"
					value="${platform.name}" placeholder="请输入搜索平台名称" /></td>
				<td>公司名称：</td>
				<td width="200"><input type="text" name="company" id="company"
					value="${platform.company}" placeholder="请输入搜索公司名称" /></td>
			</tr>
			<tr>
				
				<td>联系人姓名：</td>
				<td width="200"><input type="text" name="contactsName"
					id="contactsName" value="${platform.contactsName}"
					placeholder="请输入搜索联系人姓名" /></td>
				<td>渠道：</td>
				<td>
					<div class="controls col-md-6">
						<select class="marage_select" style="width: 100px; margin-left: -15px;"
							id="channel" name="channel">
							<option value="">全部</option>
							<option  value="1" <c:if test="${platform.channel==1 }">selected="selected"</c:if> >微信</option>
							<option value="2" <c:if test="${platform.channel==2 }">selected="selected"</c:if> >公众号</option>
							<option value="3" <c:if test="${platform.channel==3 }">selected="selected"</c:if> >app</option>
							<option value="4" <c:if test="${platform.channel==4 }">selected="selected"</c:if> >网站</option>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td>联系人手机号：</td>
				<td width="200"><input type="text" name="contactsPhone"
					id="contactsPhone" value="${platform.contactsPhone}"
					placeholder="请输入搜索联系人手机号" /></td>
				<%-- <td>公司地址：</td>
				<td width="200"><input type="text" name="companyAddress"
					id="companyAddress" value="${platform.companyAddress}"
					placeholder="请输入搜索公司地址" /></td> --%>
				<!--<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>-->
			</tr>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>

	</div>
</form>
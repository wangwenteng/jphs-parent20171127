<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%-- <form id="serach-form" method="get" action="/withdraw/cash/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>账户姓名：</td>
			<td width="200">
				<input type="text" name="accountName" id="accountName" value="${withdrawCash.accountName}" />
			</td>
			<td>支付宝账号：</td>
			<td width="200">
				<input type="text" name="alipayAccount" id="alipayAccount" value="${withdrawCash.alipayAccount}" />
			</td>
			<td>审核时间：</td>
			<td width="200">
				<input type="text" name="auditTime" id="auditTime" value="${withdrawCash.auditTime}" />
			</td>
			<td>备注 驳回时间：</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form> --%>
<form method="get" action="/withdraw/cash/index.jhtml" id="serach-form">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>账户姓名：</td>
				<td width="200"><input type="text" name="accountName"
					id="accountName" value="${withdrawCash.accountName}"
					placeholder="请输入搜索账户姓名" /></td>
				<td>账户姓名：</td>
				<td width="200"><input type="text" name="alipayAccount"
					id="alipayAccount" value="${withdrawCash.alipayAccount}"
					placeholder="请输入搜索支付宝账户" /></td>
			</tr>
			<tr>
				<%-- <td>账户姓名：</td>
				<td width="200"><input type="text" name="alipayAccount"
					id="alipayAccount" value="${withdrawCash.alipayAccount}"
					placeholder="请输入搜索支付宝账户" /></td> --%>
				<td>状态：</td>
				<td width="200"><div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" id="status" name="status">
							<option value="">全部</option>
							<option value="1" <c:if test="${withdrawCash.status==1}">selected="selected"</c:if>>已提现</option>
							<option value="0" <c:if test="${withdrawCash.status==0}">selected="selected"</c:if>>审核中</option>
							<option value="-1" <c:if test="${withdrawCash.status==-1}">selected="selected"</c:if>>驳回</option>
						</select>
					</div></td>
			</tr>
			<%-- <tr>
				<td>姓名：</td>
				<td width="200"><input type="text" name="user.name"
					id="user.name" value="${nurseJobtitle.name}" placeholder="请输入搜索联系人姓名" /></td>
				<td>所属科室：</td>
				<td>
					<div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" id="departmentId"
							name="departmentId">
							<option value="">全部</option>
							<c:forEach items="${department}" var="e" varStatus="s">
								<option value="${e.id }"
									<c:if test="${nurseJobtitle.departmentId==e.id}">selected="selected"</c:if>>${e.name }</option>
							</c:forEach>
						</select>
					</div>
				</td>
			</tr> --%>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>

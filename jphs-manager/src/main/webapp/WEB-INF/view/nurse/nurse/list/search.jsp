﻿<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/nurse/index.jhtml" style="margin-top: -31px; ">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>身份证号：</td>
				<td width="200"><input type="text" name="sfz" id="sfz"
					value="${nurse.sfz}" placeholder="请输入搜索身份证号" /></td>
				<td>参加工作时间：</td>
				<td width="200">
					<div class="form-group">
						<div
							style="width: 130%;margin-top: -23px;margin-bottom: -40px;"
							class="input-group date form_date col-md-3" data-date=""
							data-date-format="yyyy-mm-dd" data-link-field="workYears"
							data-link-format="yyyy-mm-dd">
							<input class="form-control"
								value="${nurse.workYear}"
								size="14" placeholder="请选择开始工作时间" type="text" value="" readonly>
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-remove"></span></span> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-calendar"></span></span> <input
								type="hidden" name="workYear" id="workYear" value="" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>所属科室：</td>
				<td>
					<div class="controls col-md-6"    style=" width: 70%;margin-left: -15px;" >
						<select class="marage_select" id="departmentId"
							name="departmentId">
							<option value="">全部</option>
							<c:forEach items="${department}" var="e" varStatus="s">
								<option value="${e.id }"
									<c:if test="${nurse.departmentId==e.id}">selected="selected"</c:if>>${e.name }</option>
							</c:forEach>
						</select>
					</div>
				</td>
				<td>姓名：</td>
				<td width="200"><input type="text" name="user.name"
					id="user.name" value="${nurse.user.name}"
					placeholder="请输入搜索联系人姓名" /></td>
				<!--<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>-->
			</tr>
			<tr>
				<td>手机号：</td>
				<td width="200"><input type="text" name="user.phone"
					id="user.phone" value="${nurse.user.phone}"
					placeholder="请输入搜索联系人手机号" /></td>
				<td>联系地址：</td>
				<td width="200"><input type="text" name="address"
					id="address" value="${nurse.address}"
					placeholder="请输入搜索公司地址" /></td>
				<!--<td><button class="input-group-addon btn btn-primary search_btn">搜索</button></td>-->
			</tr>
			<%-- <tr>
			<td>参加工作时间：</td>
			<td width="200">
				<input type="text" name="workYears" id="workYears" value="${nurse.workYears}" />
			</td>
			<td>个人简介：</td>
			<td width="200">
				<input type="text" name="brief" id="brief" value="${nurse.brief}" />
			</td>
			<td>详情：</td>
			<td width="200">
				<input type="text" name="details" id="details" value="${nurse.details}" />
			</td>
			<td>在职医院：</td>
			<td width="200">
				<input type="text" name="hospital" id="hospital" value="${nurse.hospital}" />
			</td>
			<td>所属科室：</td>
			<td width="200">
				<input type="text" name="departmentId" id="departmentId" value="${nurse.departmentId}" />
			</td>
			<td>封号标识：</td>
			<td width="200">
				<input type="text" name="active" id="active" value="${nurse.active}" />
			</td>
		</tr> --%>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>


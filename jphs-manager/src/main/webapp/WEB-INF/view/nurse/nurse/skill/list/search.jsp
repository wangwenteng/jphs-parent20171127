<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form id="serach-form" method="get" id="serach-form" action="/nurse/skill/index.jhtml">
	<div class="clearfix">
		<table class="text-right ">
			<tr>
				<td>护士姓名：</td>
				<td><input type="text" placeholder="请输入护士姓名"
					name="creatorName" id="creatorName"
					value="${nurseSkill.creatorName}" /></td>
				<td>技能名称：</td>
				<td><input type="text" placeholder="请输入技能名称" name="skill.name"
					id="skill.name" value="${nurseSkill.skill.name}" /></td>
			</tr>
			<tr>
				<td>状态：</td>
				<td>
					<div class="controls col-md-6"
						style="width: 70%; margin-left: -15px;">
						<select class="marage_select" name="status" id="status">
							<option value="">全 部</option>
							<option value="0"
								<c:if test="${nurseSkill.status==0 }">selected="selected"</c:if>>开启</option>
							<option value="-1"
								<c:if test="${nurseSkill.status==-1 }">selected="selected"</c:if>>关闭</option>
						</select>
					</div>
				</td>
				<%-- <td>技能名称：</td>
			<td width="200">
				<input type="text" placeholder="请输入技能名称" name="skill.name" id="skill.name" value="${nurseSkill.skill.name}" />
			</td> --%>
			</tr>
			<%-- <tr>
			<td>服务区域：</td>
			<td width="200">
				<input type="text" name="locationId" id="locationId" value="${nurseSkill.locationId}" />
			</td>
			<td>空闲时间：</td>
			<td width="200">
				<input type="text" name="leisureTime" id="leisureTime" value="${nurseSkill.leisureTime}" />
			</td>
		</tr> --%>
		</table>
		<div class="marage_search_btn">
			<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
			<button id="clear" class="search_btn">清空</button>
		</div>
	</div>
</form>


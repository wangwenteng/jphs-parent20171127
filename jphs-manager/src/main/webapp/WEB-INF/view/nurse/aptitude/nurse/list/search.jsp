<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<form method="get" action="/aptitude/nurse/index.jhtml">
<div class="clearfix">
	<table class="text-right ">
		<tr>
			<td>资质ID：</td>
			<td width="200">
				<input type="text" name="aptitudeId" id="aptitudeId" value="${aptitudeNurse.aptitudeId}" />
			</td>
			<td>护士ID：</td>
			<td width="200">
				<input type="text" name="nurseId" id="nurseId" value="${aptitudeNurse.nurseId}" />
			</td>
			<td>审核标识：</td>
			<td width="200">
				<input type="text" name="auditId" id="auditId" value="${aptitudeNurse.auditId}" />
			</td>
			<td>资质商品ID：</td>
			<td width="200">
				<input type="text" name="aptitudeGoodsIds" id="aptitudeGoodsIds" value="${aptitudeNurse.aptitudeGoodsIds}" />
			</td>
		</tr>
	</table>
	<div class="marage_search_btn">
		<button type="submit" class="search_btn" data-role="search-btn">搜索</button>
		<button id="clear" class="search_btn">清空</button>
	</div>
</div>
</form>


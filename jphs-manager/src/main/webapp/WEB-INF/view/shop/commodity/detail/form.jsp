<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div style="margin: 10px 0px 10px 0px;" class="event_start_from">
	<div class="title_defalt"></div>
	<table id="commodityTable" class="tableStyle">
		<tr>
			<td align="right"width="100">商家id：</td>
			<td>
				<c:out value="${commodity.businessId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">品类id：</td>
			<td>
				<c:out value="${commodity.commodityTypeId}"/>
			</td>
		</tr>
		<tr>
			<td align="right">标题：</td>
			<td>
				<c:out value="${commodity.title}"/>
			</td>
		</tr>
		<tr>
			<td align="right">副标题，简介：</td>
			<td>
				<c:out value="${commodity.subTitle}"/>
			</td>
		</tr>
		<tr>
			<td align="right">型号/规格：</td>
			<td>
				<c:out value="${commodity.model}"/>
			</td>
		</tr>
		<tr>
			<td align="right">内容：</td>
			<td>
				<c:out value="${commodity.content}"/>
			</td>
		</tr>
		<tr>
			<td align="right">正品认证（0否，1是）：</td>
			<td>
				<c:out value="${commodity.quality}"/>
			</td>
		</tr>
		<tr>
			<td align="right">隐私配送（0否，1是）：</td>
			<td>
				<c:out value="${commodity.privacy}"/>
			</td>
		</tr>
		<tr>
			<td align="right">药检认证（0否，1是）：</td>
			<td>
				<c:out value="${commodity.security}"/>
			</td>
		</tr>
		<tr>
			<td align="right">参数：</td>
			<td>
				<c:out value="${commodity.parameter}"/>
			</td>
		</tr>
		<tr>
			<td align="right">是否支持使用优惠券（0不支持，1支持）：</td>
			<td>
				<c:out value="${commodity.supportVoucher}"/>
			</td>
		</tr>
		<tr>
			<td align="right">限购数量：</td>
			<td>
				<c:out value="${commodity.limitNumber}"/>
			</td>
		</tr>
		<tr>
			<td align="right">保护期(天）：</td>
			<td>
				<c:out value="${commodity.protectDay}"/>
			</td>
		</tr>
		<tr>
			<td align="right">：</td>
			<td>
				<c:out value="${commodity.sort}"/>
			</td>
		</tr>
		<tr>
			<td align="right">备注：</td>
			<td>
				<c:out value="${commodity.remark}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品分享总量：</td>
			<td>
				<c:out value="${commodity.shareNumber}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品浏览总量：</td>
			<td>
				<c:out value="${commodity.browser}"/>
			</td>
		</tr>
		<tr>
			<td align="right">商品销售总量：</td>
			<td>
				<c:out value="${commodity.count}"/>
			</td>
		</tr>
	</table>
</div>
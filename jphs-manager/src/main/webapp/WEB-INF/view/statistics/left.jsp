<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div class="common_left">
	<div class="common_left_h">数据统计</div>
	<dl>
		<dt class="public_left_active">
			<span class="common_left_icon"> </span>访问量统计 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<dd class="dd1">
				<a href="showDataByDay.jhtml"><img
					src="/static/images/Group7.png" />日访问量</a>
			</dd>
			<dd class="dd2">
				<a href="showDataByWeek.jhtml"><img
					src="/static/images/Group7.png" />周访问量</a>
			</dd>
			<dd class="dd3">
				<a href="showDataByMonth.jhtml"><img
					src="/static/images/Group7.png" />月访问量</a>
			</dd>
			<dd class="dd4">
				<a href="showDataByYear.jhtml"><img
					src="/static/images/Group7.png" />年访问量</a>
			</dd>
		</div>
	</dl>
	<script>
		$(".common_left dt").click(function() {
			$(this).toggleClass("public_left_active");
			$(this).find("i").toggleClass("public-horn-45");
			$(this).find("span").toggleClass("common_icon_active");
			$(this).next(".public_left_list").slideToggle();

		})
		$(".public_left_list").find("dd").click(
				function() {
					$(".public_left_list").find("dd").removeClass(
							"public_left_active");
					$(this).toggleClass("public_left_active");
				})
	</script>
</div>
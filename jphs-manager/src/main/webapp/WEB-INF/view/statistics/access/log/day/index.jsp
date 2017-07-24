<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div id="data">
	<div class="common_right_title">
		<img src="/static/images/yousanjiaox.png" /> 数据统计 <i
			class="public1-horn-45"></i> 访问量统计 <i class="public1-horn-45"></i>
		日访问量
	</div>
	<script type="text/javascript">
		var platformName = ${platformName};
		var timeList = ${timeList};
		var totalPv = ${totalPv};
		var totalUv = ${totalUv};
		var pvtext=${days}+'日-pv';
		var uvtext=${days}+'日-uv';
		
	</script>
	<div class="form-group" style="background: #fff">
		<div class="input-group date form_date col-md-3" data-date=""
			data-date-format="yyyy-mm-dd" data-link-field="dtp_input1"
			data-link-format="yyyy-mm-dd">
			<input class="form-control" size="14" placeholder="请选择查询日期" type="text" value="" readonly>
			<span class="input-group-addon"><span
				class="glyphicon glyphicon-remove"></span></span> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-calendar"></span></span> <input type="hidden"
				id="dtp_input1" value="" />
		</div>
		<input type="button" style="margin-left: 243px; margin-top: -43px;" class="btn" id="search" value="搜索" />
	</div>
	<div class="marage_right_content">
		<!-- ECharts单文件引入 -->
		<div id="accessPvChart" class="chart_parent"></div>
		<!-- ECharts单文件引入 -->
		<div id="accessUvChart" class="chart_parent"></div>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$(".dd1").addClass("public_left_active");
		$(".li5").addClass("active");
	})
</script>

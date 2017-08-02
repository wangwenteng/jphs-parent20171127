<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<script type="text/javascript">
var platformName =${platformName };
var timeList =${timeList};
var totalPv=${totalPv };
var totalUv=${totalUv };
var pvtext='pv';
var uvtext='uv';
</script>
<div id="data">
<div class="common_right_title">
	<img src="/static/images/yousanjiaox.png" /> 数据统计 <i
		class="public1-horn-45"></i> 访问量统计 <i class="public1-horn-45"></i>
	周访问量
</div>
<div class="marage_right_title">

	<div style="display: none;">
		<table id="monitor" class="table table-striped">
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
	</div>
	<div class="form-group" style="background: #fff">
		<div class="input-group date form_date col-md-3" style="float: left;"
			id="startTime" data-date="" data-date-format="yyyy-mm-dd"
			data-link-field="startTime" data-link-format="yyyy-mm-dd">
			<input class="form-control" size="14" placeholder="请选择开始日期"
				type="text" value="" readonly> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-remove" title="清空"></span></span> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-calendar"title="选择日期" ></span></span> <input type="hidden"
				id="startTime" value="" />
		</div>
		&nbsp;&nbsp;
		<div class="input-group date form_date col-md-3"
			style="float: left; margin-left: 1px" id="endTime" data-date=""
			data-date-format="yyyy-mm-dd" data-link-field="endTime"
			data-link-format="yyyy-mm-dd">
			<input class="form-control" size="14" placeholder="请选择结束日期"
				type="text" value="" readonly> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-remove" title="清空"></span></span> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input type="hidden"
				id="endTime" value="" />
		</div>
		<button class="btn" id="search" >搜索</button>
		<button id="last-week" class="btn">上一周</button>
		<button id="next-week" class="btn">下一周</button>
	</div>
</div>

	<div class="marage_right_content">
		<!-- ECharts单文件引入 -->
		<div id="accessPvChart" class="chart_parent"></div>
		<!-- ECharts单文件引入 -->
		<div id="accessUvChart" class="chart_parent"></div>
	</div>
</div>
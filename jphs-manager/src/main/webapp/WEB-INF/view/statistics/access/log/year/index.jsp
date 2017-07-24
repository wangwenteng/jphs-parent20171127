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
		<img src="/static/images/yousanjiaox.png" /> 数据统计 <i class="public1-horn-45"></i>
		访问量统计 <i class="public1-horn-45"></i> 年访问量
	</div>
	<div class="form-group" style="background: #fff">
		<div class="input-group date form_datetime col-md-3" data-date=""
			data-date-format="yyyy" data-link-field="dtp_input1">
			<input class="form-control" size="16" id="year" type="text" value="" readonly>
			<span class="input-group-addon"><span
				class="glyphicon glyphicon-remove"></span></span> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-th"></span></span>
			<input type="hidden" id="dtp_input1" value="" /><br />
		</div>
		<input type="button" class="btn" id="search" value="搜索" />
		<button id="last-year" class="btn"  >上一年</button>
		<button id="next-year" class="btn"  >下一年</button>
	</div>
	<div class="marage_right_content">
		<!-- ECharts单文件引入 -->
		<div id="accessPvChart" class="chart_parent"></div>
		<!-- ECharts单文件引入 -->
		<div id="accessUvChart" class="chart_parent"></div>
	</div>
</div>
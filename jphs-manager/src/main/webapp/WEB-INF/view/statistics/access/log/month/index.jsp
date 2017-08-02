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
		访问量统计 <i class="public1-horn-45"></i> 月访问量
	</div>
<div class="marage_right_title">
	<div class="form-group" style="background: #fff">
		<div class="input-group date form_date col-md-3" style="float: left;" data-date=""
			data-date-format="yyyy-mm-dd" data-link-field="dtp_input1"
			data-link-format="yyyy-mm-dd">
			<input class="form-control" id="month" size="16" placeholder="请选择查询日期" type="text" value="" readonly>
			<span class="input-group-addon"><span
				class="glyphicon glyphicon-remove" title="清空"></span></span> <span
				class="input-group-addon"><span
				class="glyphicon glyphicon-calendar" title="选择日期"></span></span> <input type="hidden"
				id="dtp_input1" value="" />
		</div>
		<button class="btn"  id="search" style="margin-left: 10px" >搜索</button>
		<button id="last-month" class="btn"  >上一月</button>
		<button id="next-month" class="btn"  >下一月</button>
	</div>
</div>

	<div class="marage_right_content">
		<!-- ECharts单文件引入 -->
		<div id="accessPvChart" class="chart_parent"></div>
		<!-- ECharts单文件引入 -->
		<div id="accessUvChart" class="chart_parent"></div>
	</div>
</div>
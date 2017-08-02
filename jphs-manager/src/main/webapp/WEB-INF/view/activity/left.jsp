<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_left">
	<div class="common_left_h">活动管理</div>
	<dl>
		<dt class="public_left_active">
		<span class="common_left_icon"> </span>活动管理
		 <i class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<jphs:hasPermission url="/advertising/index.jhtml">
			<dd>
				<a href="/advertising/index.jhtml"><img src="/static/images/Group7.png" />轮播图管理</a>
			</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/advertising/index.jhtml">
			<dd>
				<a href="/voucher/index.jhtml"><img src="/static/images/Group7.png" />优惠券管理</a>
			</dd>
			</jphs:hasPermission>
		</div>
	</dl>
	
	<!-- <dl>
		<dt class="public_left_active">
		<span class="common_left_icon"> </span>咨询管理
		 <i class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<dd>
				<a href="/information/channel/index.jhtml"><img src="/static/images/Group7.png" />频道管理</a>
			</dd>
			<dd>
				<a href="/information/index.jhtml"><img src="/static/images/Group7.png" />新闻管理</a>
			</dd>
			<dd>
				<a href="/information/evaluate/index.jhtml"><img src="/static/images/Group7.png" />评论管理</a>
			</dd>
		</div>
	</dl> -->
</div>
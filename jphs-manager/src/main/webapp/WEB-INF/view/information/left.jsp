<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_left">
	<div class="common_left_h">信息管理</div>
	<dl>
		<dt class="public_left_active">
		<span class="common_left_icon"> </span>资讯管理
		 <i class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<jphs:hasPermission url="/information/channel/index.jhtml">
			<dd>
				<a href="/information/channel/index.jhtml"><img src="/static/images/Group7.png" />频道管理</a>
			</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/information/index.jhtml">
			<dd>
				<a href="/information/index.jhtml"><img src="/static/images/Group7.png" />资讯管理</a>
			</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/information/evaluate/index.jhtml">
			<dd>
				<a href="/information/evaluate/index.jhtml"><img src="/static/images/Group7.png" />评论管理</a>
			</dd>
			</jphs:hasPermission>
		</div>
	</dl>
</div>
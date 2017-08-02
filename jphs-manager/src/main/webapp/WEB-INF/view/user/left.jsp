<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_left">
	<div class="common_left_h">会员管理</div>
	<dl>
		<dt class="public_left_active">
			<span class="common_left_icon"> </span>会员管理 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<jphs:hasPermission url="/user/index.jhtml">
				<dd>
					<a href="/user/index.jhtml"><img
						src="/static/images/Group7.png" />会员管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/transaction/index.jhtml">
				<dd>
					<a href="/transaction/index.jhtml"><img
						src="/static/images/Group7.png" />收支记录</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/evaluation/index.jhtml">
				<dd>
					<a href="/evaluation/index.jhtml"><img
						src="/static/images/Group7.png" />评价记录</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/voucher/user/index.jhtml">
				<dd>
					<a href="/voucher/user/index.jhtml"><img
						src="/static/images/Group7.png" />会员优惠券</a>
				</dd>
			</jphs:hasPermission>
		</div>


	</dl>
</div>

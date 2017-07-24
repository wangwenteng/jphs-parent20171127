<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<div class="common_left">
	<div class="common_left_h">订单管理</div>
	<dl>
		<dt class="public_left_active">
			<span class="common_left_icon"> </span>订单管理 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<jphs:hasPermission url="/order/index.jhtml">
				<dd>
					<a href="/order/index.jhtml?schedule=0"><img
						src="/static/images/Group7.png" />待支付</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=1"><img
						src="/static/images/Group7.png" />待接单</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=2"><img
						src="/static/images/Group7.png" />已接单</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=3"><img
						src="/static/images/Group7.png" />执行中</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=4"><img
						src="/static/images/Group7.png" />待确定</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=5"><img
						src="/static/images/Group7.png" />已完成</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=6"><img
						src="/static/images/Group7.png" />已取消</a>
				</dd>
				<dd>
					<a href="/order/index.jhtml?schedule=7"><img
						src="/static/images/Group7.png" />申诉中</a>
				</dd>
				<dd>
					<a href="/order/evaluation/index.jhtml"><img
						src="/static/images/Group7.png" />评价管理</a>
				</dd>
			</jphs:hasPermission>
		</div>

		<dt class="public_left_active">
			<span class="common_left_icon"> </span>定制服务 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<dd>
				<a href="/custom/service/index.jhtml"><img src="/static/images/Group7.png" />定制服务</a>
			</dd>
		</div>
		<dt class="public_left_active">
			<span class="common_left_icon"> </span>商品订单 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<dd>
				<a href="#"><img src="/static/images/Group7.png" />订单管理</a>
			</dd>
		</div>
	</dl>
</div>
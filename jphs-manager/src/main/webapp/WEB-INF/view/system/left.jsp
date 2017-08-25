<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx"
	uri="http://tiles.apache.org/tags-tiles-extras"%>
<%@taglib prefix="jphs" uri="http://www.jinpaihushi.com/jsp/core"%>
<div class="common_left">
	<div class="common_left_h">系统管理</div>
	<dl>
		<dt class="public_left_active">
			<span class="common_left_icon"> </span>系统管理 <i
				class="public-horn-135 left-mg"></i>
		</dt>
		<div class="public_left_list">
			<jphs:hasPermission url="/system/user/index.jhtml">
				<dd>
					<a href="/system/user/index.jhtml"><img
						src="/static/images/Group7.png" />用户管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/system/role/index.jhtml">
				<dd>
					<a href="/system/role/index.jhtml"><img
						src="/static/images/Group7.png" />角色管理</a>
				</dd>
			</jphs:hasPermission>
			<jphs:hasPermission url="/system/module/moduleTree.jhtml">
				<dd>
					<a href="/system/module/moduleTree.jhtml"><img
						src="/static/images/Group7.png" />模块管理</a>
				</dd>
			</jphs:hasPermission>
		</div>


	</dl>
</div>
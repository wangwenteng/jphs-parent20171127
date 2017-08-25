<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="tilesx" uri="http://tiles.apache.org/tags-tiles-extras"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>后台管理系统</title>
		<link rel="stylesheet" href="../static/css/base.css">
		<link rel="stylesheet" href="../static/css/mystyle.css" />
		<link rel="stylesheet" href="../static/css/min.css">
	</head>

	<body>

		<header class="manager_header">
			<img src="../static/images/logo.png" class="public-logo" />
			<ul>
				<li>首页</li>
				<li>护士管理</li>
				<li>订单管理</li>
				<li class="active">产品管理</li>
				<li>活动管理</li>
				<li>系统管理</li>
				<li>会员管理</li>
				<li>康复管理</li>
				<li>问题反馈</li>
				<li>
					<span>admin</span>
					<span><img src="../static/images/sanjiaox.png"/></span>
				</li>
			</ul>
		</header>
		<section class="manager_container">
			<div class="common_left">
				<div class="common_left_h">产品管理</div>
				<dl>
					<dt class="public_left_active">
						
						<span class="common_left_icon">
						</span>
						品类管理
						<i class="public-horn-135"></i>
					</dt>
					<div class="public_left_list">
						<dd>
							<img src="../static/images/Group 7.png" /> 站点配置
						</dd>
						<dd>
							<img src="../static/images/Group 7.png" /> 价格管理
						</dd>
					</div>
					<dt class="public_left_active">
						<span class="common_left_icon common_left_icon1">
						</span>
						服务管理
						<i class="public-horn-135"></i>
					</dt>
					<div class="public_left_list">
						<dd>
							<img src="../static/images/Group 7.png" /> 站点配置
						</dd>
						<dd>
							<img src="../static/images/Group 7.png" /> 价格管理
						</dd>
					</div>
					<dt class="public_left_active">
						<span class="common_left_icon common_left_icon2">
						</span>	

						站点管理
						<i class="public-horn-135"></i>
					</dt>
					<div class="public_left_list">
						<dd>
							<img src="../static/images/Group 7.png" /> 站点配置
						</dd>
						<dd>
							<img src="../static/images/Group 7.png" /> 价格管理
						</dd>
					</div>
				</dl>
			</div>
			<div class="common_right">
				<div class="common_right_title">
					<img src="../static/images/yousanjiaox.png" /> 产品管理
					<i class="public1-horn-45"></i> 站点管理
					<i class="public1-horn-45"></i> 站点配置
				</div>
				<div class="marage_right_title">
					<input type="text" placeholder="请输入搜索品类" />
					<button>搜索</button>
				</div>
				<div class="marage_right_content">
					<button class="public_btn m-10">添加品类</button>
					<table cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th>编号</th>
								<th>品类名称</th>
								<th>图标</th>
								<th>图片</th>
								<th>排序</th>
								<th>是否启用</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>母婴护理</td>
								<td>
									<img src="../static/images/morentupai.png" />
								</td>
								<td><img src="../static/images/morentupai.png" /></td>
								<td>1</td>
								<td>
									<span></span>
									<span class="mui-switch mui-switch-blue mui-switch-mini">
											<i class="mui-switch-handle"></i>
									</span>
								</td>
								<td>
									<img src="../static/images/bianji.png" />
								</td>
							</tr>
							<tr>
								<td>1</td>
								<td>母婴护理</td>
								<td>
									<img src="../static/images/morentupai.png" />
								</td>
								<td><img src="../static/images/morentupai.png" /></td>
								<td>1</td>
								<td class="mui-table-view-cell">
									<span></span>
									<span class="mui-switch mui-switch-blue mui-switch-mini">
											<i class="mui-switch-handle"></i>
									</span>
								</td>
								<td>
									<img src="../static/images/bianji.png" />
								</td>
							</tr>
							<tr>
								<td>1</td>
								<td>母婴护理</td>
								<td>
									<img src="../static/images/morentupai.png" />
								</td>
								<td><img src="../static/images/morentupai.png" /></td>
								<td>1</td>
								<td class="mui-table-view-cell">
									<span></span>
									<span class="mui-switch mui-switch-blue mui-switch-mini">
											<i class="mui-switch-handle"></i>
									</span>
								</td>
								<td>
									<img src="../static/images/bianji.png" />
								</td>
							</tr>
							<tr>
								<td>1</td>
								<td>母婴护理</td>
								<td>
									<img src="../static/images/morentupai.png" />
								</td>
								<td><img src="../static/images/morentupai.png" /></td>
								<td>1</td>
								<td class="mui-table-view-cell">
									<span></span>
									<span class="mui-switch mui-switch-blue mui-switch-mini">
											<i class="mui-switch-handle"></i>
									</span>
								</td>
								<td>
									<img src="../static/images/bianji.png" />
								</td>
							</tr>
							<tr>
								<td>1</td>
								<td>母婴护理</td>
								<td>
									<img src="../static/images/morentupai.png" />
								</td>
								<td><img src="../static/images/morentupai.png" /></td>
								<td>1</td>
								<td class="mui-table-view-cell">
									<span></span>
									<span class="mui-switch mui-switch-blue mui-switch-mini">
											<i class="mui-switch-handle"></i>
									</span>
								</td>
								<td>
									<img src="../static/images/bianji.png" />
								</td>
							</tr>
							<tr>
								<td>1</td>
								<td>母婴护理</td>
								<td>
									<img src="../static/images/morentupai.png" />
								</td>
								<td><img src="../static/images/morentupai.png" /></td>
								<td>1</td>
								<td class="mui-table-view-cell">
									<span></span>
									<span class="mui-switch mui-switch-blue mui-switch-mini">
											<i class="mui-switch-handle"></i>
									</span>
								</td>
								<td>
									<img src="../static/images/bianji.png" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="clearfix"></div>
			</div>
		</section>
		<script src="../static/js/mui.min.js"></script>
		<script src="../static/js/jquery.js"></script>
		<script>
			$(".manager_header ul li").click(function() {
				$(".manager_header ul li").removeClass("active");
				$(this).addClass("active");
			})

			$(".common_left dt").click(function() {
				$(this).toggleClass("public_left_active");
				$(this).find("i").toggleClass("public-horn-45");
				$(this).find("span").toggleClass("common_icon_active");
				$(this).next(".public_left_list").slideToggle();

			})
			$(".public_left_list").find("dd").click(function() {
				$(".public_left_list").find("dd").removeClass("public_left_active");
				$(this).toggleClass("public_left_active");
			})
		</script>
	</body>

</html>
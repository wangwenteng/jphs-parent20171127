$(function() {
	var userId= $("#id").val();
	$("#systemUserForm").validate({
		rules : {
			phone : {
				required : true,
				minlength : 11,
				// 自定义方法：校验手机号在数据库中是否存在
				// checkPhoneExist : true,
				isMobile : true,
				isTrue : userId
			},
			email : {
				required : true,
				email : true,
				accept : "@goldnurse.com",
				isTrue : userId
			},
			name : {
				required : true,
				rangelength : [ 2, 4 ],
				isTrue : userId
			},
			password : {
				required : true,
				rangelength : [ 6, 16 ]
			},
			confirm_password : {
				required : true,
				rangelength : [ 6, 16 ],
				equalTo : "#password"
			}
		},
		messages : {
			phone : {
				required : "请输入手机号",
				minlength : "确认手机不能小于11个字符",
				isMobile : "请正确填写您的手机号码",
				isTrue : "该手机号已被占用"
			},
			email : {
				required : "<font color=red>请输入一个Email地址</fond>",
				email : "请输入一个有效的Email地址",
				accept : "请输入公司邮箱",
				isTrue : "该邮箱已经被占用"
			},
			name : {

				required : "请输入姓名",
				rangelength : "请输入最二到四位姓名",
				isTrue :"该名称已被占用"
			},
			password : {
				required : "请输入密码",
				rangelength : "请输入6-16位的密码"
			},
			confirm_password : {
				required : "请输入确认密码",
				rangelength : "请输入6-16位的密码",
				equalTo : "两次输入密码不一致"
			}

		},
		submitHandler : function(form) {
			alert("操作成功！");
			form.submit();
		}
	});
	var id = $('#id').val();
	var name = $('#name').val();
	$('#resetPassword').click(function(){
		window.location.href = "/system/user/toEditPassword.jhtml?id=" + id+"&type=1&name="+name;
	})
	$('#modifyPassword').click(function(){
		window.location.href = "/system/user/toEditPassword.jhtml?id=" + id+"&type=2&name="+name;
	})
});
$(function() {
	$("#systemUserForm").validate({
		rules : {
			phone : {
				required : true,
				minlength : 11,
				// 自定义方法：校验手机号在数据库中是否存在
				// checkPhoneExist : true,
				isMobile : true
			},
			email : {
				required : true,
				email : true,
				accept : "@goldnurse.com"
			},
			name : {
				required : true,
				rangelength : [ 2, 4 ]
			},
			password : {
				required : true,
				rangelength : [ 8, 16 ]
			},
			confirm_password : {
				required : true,
				rangelength : [ 8, 16 ],
				equalTo : "#password"
			}
		},
		messages : {
			phone : {
				required : "请输入手机号",
				minlength : "确认手机不能小于11个字符",
				isMobile : "请正确填写您的手机号码"
			},
			email : {
				required : "<font color=red>请输入一个Email地址</fond>",
				email : "请输入一个有效的Email地址",
				accept : "请输入公司邮箱"
			},
			name : {

				required : "请输入姓名",
				rangelength : "请输入最二到四位姓名"
			},
			password : {
				required : "请输入密码",
				rangelength : "请输入8-16位的密码"
			},
			confirm_password : {
				required : "请输入确认密码",
				rangelength : "请输入8-16位的密码",
				equalTo : "两次输入密码不一致"
			}

		},
		submitHandler : function(form) {
			alert("操作成功！");
			form.submit();
		}
	});
});
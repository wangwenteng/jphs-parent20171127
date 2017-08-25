$(function() {
	$("#loginForm").validate({
		rules : {
			userName : {
				required : true,
			},
			password : {
				required : true,
			}
		},
		messages : {
			userName : "请输入手机号或者公司邮箱",
			password : "请输入密码"
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});
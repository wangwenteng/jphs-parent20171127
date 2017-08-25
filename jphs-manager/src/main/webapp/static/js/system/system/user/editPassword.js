$(function() {
	var userId= $("#id").val();
	$("#systemUserEdit").validate({
		rules : {
			
			old_password : {
				required : true,
				rangelength : [ 6, 16 ],
				checkPassword:userId,
			},
			password : {
				required : true,
				rangelength : [ 6, 16 ],
			},
			confirm_password : {
				required : true,
				rangelength : [ 6, 16 ],
				equalTo : "#password"
			}
		},
		messages : {
			old_password : {

				required : "请输入原密码",
				rangelength : "请输入8-16位的密码",
				checkPassword:"原密码有误"
			},
			password : {
				required : "请输入新密码",
				rangelength : "请输入8-16位的密码",
			},
			confirm_password : {
				required : "请确认新密码",
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
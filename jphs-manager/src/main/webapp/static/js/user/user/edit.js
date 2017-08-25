$(function() {
	
	$("#platformForm").validate({
		rules : {
			name : {
				required : true
			},
			password : {
				required : true
			} 
		},
		messages : {
			name : {
				required : "请输入昵称"
			},
			password : {
				required : "请输入密码"
			} 
		}
		
	})
	
});
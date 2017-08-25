$(function() {
	var id = $('#id').val();
	$("#jobtitleTypeForm").validate({
		rules : {
			name : {
				required : true,
				rangelength : [ 2, 7 ],
				jobtitleTypeIsTrue : id
			}
		},
		messages : {
			name : {
				required : "请输入职称名称",
				rangelength : "请输入二到七位",
				jobtitleTypeIsTrue:"该名称已经被占用"
			}
		},
		submitHandler : function(form) {
			form.submit();
			alert("添加成功");
		}
	});
});
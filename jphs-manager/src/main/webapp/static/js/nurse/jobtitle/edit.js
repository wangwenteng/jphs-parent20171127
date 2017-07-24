$(function() {
	$("#jobtitleForm").validate({
		rules:{
			name :{
				required:true,
				rangelength: [2,7]
				//isChinese:true
			},
			jobtitleTypeId:{
				required:true
			}
		},
		messages:{
			name:{
				required:"请输入职称名称",
				rangelength:"请输入二到七位"
				//isChinese:"请输入汉字"
			},
			jobtitleTypeId:"请选择职称类型"
		},
		submitHandler : function(form) {
			alert("添加成功");
			form.submit();
		}
	});
});
$(function() {
	var id =$('#id').val();
	$("#systemRoleForm").validate({
		rules : {
			name : {
				required : true,
				minlength : 2,
				roleName:id,
			},
			describe : {
				required : true,
			}
		},
		messages : {
			name : {
				required : "请输入角色名称",
				minlength : "请输入最少两位",
				roleName:"角色名称已被占用"
				
			},
			describe : {
				required : "请输入角色描述"
			}

		},
		submitHandler : function(form) {
			alert("操作成功！");
			form.submit();
		}
	});
});
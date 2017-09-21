$(function() {
	$('#save').click(function() {
		var roleIds = '';
		var checkedId = $('input[name="roleId"]:checked');
		if (checkedId.length > 0) {
			for (var i = 0; i < checkedId.length; i++) {
				if ((i + 1) == checkedId.length) {
					roleIds += checkedId[i].value;
				} else {
					roleIds += checkedId[i].value + ",";
				}
			}
			$('#roleIds').val(roleIds);
			document.nurseForm.submit();
			alert("分配成功！！");
		}else{
			alert("请选择角色！！！");
			return false;
		}
	});
});
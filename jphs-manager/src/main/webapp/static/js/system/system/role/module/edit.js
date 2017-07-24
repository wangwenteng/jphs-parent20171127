$(function() {
	$('#tt').tree({
		data : data.treeData
	});

	$('#save').click(function() {
		var moduleIds = '';
		var nodes = $('#tt').tree('getChecked');
		for (var i = 0; i < nodes.length; i++) {
			if ((i + 1) == nodes.length) {
				moduleIds += nodes[i].id;
			} else {

				moduleIds += nodes[i].id + ',';
			}
		}
		$('#moduleIds').val(moduleIds);
		alert('分配成功');
		document.systemRoleModuleForm.submit();
	});
});
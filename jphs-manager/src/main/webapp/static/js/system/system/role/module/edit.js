$(function() {
	$('#tt').tree({
		data : data.treeData
	});

	$('#save').click(function() {
		var moduleIds = '';
		var nodes = $('#tt').tree('getChecked');
		if(nodes.length>0){
			for (var i = 0; i < nodes.length; i++) {
				if ((i + 1) == nodes.length) {
					moduleIds += nodes[i].id;
				} else {

					moduleIds += nodes[i].id + ',';
				}
			}
			$('#moduleIds').val(moduleIds);
			document.systemRoleModuleForm.submit();
			alert('分配成功');
		}else{
			alert("请选择相关模块！！！");
			return false;
		}
	});
});
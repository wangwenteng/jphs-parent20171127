$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/system/module/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

	$('#tt').tree({
		data : data.treeData
	});
	$('#save').click(function() {
		var goods = '';
		var nodes = $('#tt').tree('getChecked');
		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].parentId != 0) {
				goods += nodes[i].id + ',';
			}
		}
		$('#goodsIds').val(goods);
		var sites = '';
		var siteIds = $('input:checkbox:checked');
		for (var i = 0; i < siteIds.length; i++) {
			sites += siteIds[i].value + ',';
		}
		$('#sites').val(sites);
		alert($('#goodsIds').val());
		alert($('#sites').val());
		document.platformForm.submit();
	});
});

function redirectUpdatePage(id) {
	window.location.href = "/system/module/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/system/module/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/system/module/delete.jhtml?id=" + id;
}

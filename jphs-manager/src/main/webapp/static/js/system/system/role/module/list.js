$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/system/role/module/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/system/role/module/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/system/role/module/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/system/role/module/delete.jhtml?id=" + id;
}

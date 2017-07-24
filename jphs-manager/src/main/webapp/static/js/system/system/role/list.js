$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/system/role/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/system/role/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/system/role/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/system/role/delete.jhtml?id=" + id;
}
function getModule(id) {
	window.location.href = "/system/role/getRoleModule.jhtml?roleId=" + id;
}

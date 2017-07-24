$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/department/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/department/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/department/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/department/delete.jhtml?id=" + id;
}

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/nurse/online/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#departmentId").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/nurse/online/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/nurse/online/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/nurse/online/delete.jhtml?id=" + id;
}

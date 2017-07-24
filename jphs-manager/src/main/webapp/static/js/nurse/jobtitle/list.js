$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jobtitle/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#jobtitleTypeId").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jobtitle/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jobtitle/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jobtitle/delete.jhtml?id=" + id;
}

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/workyears/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/workyears/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/workyears/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/workyears/delete.jhtml?id=" + id;
}

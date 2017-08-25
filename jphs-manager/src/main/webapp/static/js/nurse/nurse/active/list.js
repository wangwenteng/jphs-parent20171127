$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/nurse/active/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/nurse/active/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/nurse/active/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/nurse/active/delete.jhtml?id=" + id;
}

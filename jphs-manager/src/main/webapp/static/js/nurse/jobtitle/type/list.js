$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/jobtitle/type/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/jobtitle/type/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/jobtitle/type/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/jobtitle/type/delete.jhtml?id=" + id;
}

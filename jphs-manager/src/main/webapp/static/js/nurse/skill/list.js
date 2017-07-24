$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/skill/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});
	
});

function redirectUpdatePage(id) {
	window.location.href = "/skill/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/skill/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/skill/delete.jhtml?id=" + id;
}

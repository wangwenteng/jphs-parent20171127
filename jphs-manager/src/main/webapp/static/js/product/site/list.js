$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/site/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/site/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/site/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm('确实要删除吗？')){
	window.location.href = "/site/delete.jhtml?id=" + id;
	}
}

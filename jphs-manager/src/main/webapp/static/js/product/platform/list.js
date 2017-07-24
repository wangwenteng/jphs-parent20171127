$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/platform/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$('#channel').val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/platform/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/platform/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm('确实要删除吗？')){
		window.location.href = "/platform/delete.jhtml?id=" + id;
	}
}

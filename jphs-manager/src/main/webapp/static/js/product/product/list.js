$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/product/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/product/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/product/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm('确实要删除吗？')){
		window.location.href = "/product/delete.jhtml?id=" + id;
	}
}
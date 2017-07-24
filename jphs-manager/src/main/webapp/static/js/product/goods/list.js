$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/goods/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#productId").get(0).selectedIndex=0;
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/goods/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/goods/detail.jhtml?id=" + id;
}
function deleteById(id) {
	if(confirm('确实要删除吗？')){
	window.location.href = "/goods/delete.jhtml?id=" + id;
	}
}
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
function deleteById(id,status) {
	if(status == -1){
		if(confirm('确认要删除吗？')){
			window.location.href = "/product/delete.jhtml?id=" + id+"&status="+status;
		}
	}else if(status == 1){
		if(confirm('确认要启用吗？')){
			window.location.href = "/product/delete.jhtml?id=" + id+"&status="+status;
		}
	}else{
		if(confirm('确认要停用吗？')){
			window.location.href = "/product/delete.jhtml?id=" + id+"&status="+status;
		}
	}
	
}
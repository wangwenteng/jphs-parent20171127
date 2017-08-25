$(document).ready(function() {
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss' 
	});
})

$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/voucher/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#status").empty();
		$("#type").empty();
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/voucher/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/voucher/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/voucher/delete.jhtml?id=" + id;
}
function change(id){
	document.getElementById("goodsId").innerHTML=""; 
	if(id != ""){
		$.ajax({
			type: "get",
			url: "/voucher/getGoodsList.jhtml",
			data: {id:id},
			dataType: "json",
			success: function(data) {
				var goodsList = data.goodsList;
				$("#goodsId").append("<option value=''>请选择</option>");
				 for(var i = 0;i<goodsList.length;i++ ){
				 $("#goodsId").append("<option value='"+goodsList[i].id+"' >"+goodsList[i].title+"</option>");
				 };
			}
		});
	}else{
		$("#goodsId").append("<option value=''>请选择</option>");
	}

}
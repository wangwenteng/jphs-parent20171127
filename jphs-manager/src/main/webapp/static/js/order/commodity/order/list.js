$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/commodity/order/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$("#schedule").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/commodity/order/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/commodity/order/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/commodity/order/delete.jhtml?id=" + id;
}

function fh(id){
	$("#commodityOrderId").val(id);
	$("#no").val("");
}
function addLogistics(){
	
	var no = $("#no").val();
	var logisticsId = $("#logisticsId").val();
	if(no != "" && logisticsId != ""){
		if(confirm("确定添加吗？")){
			$.ajax({
			 	type: "get",
				url: "/commodity/order/addLogistics.jhtml",
				data:$('#logistics').serialize(),
				dataType: "json",
				success: function(data) { 
					if(data.result == 1){
						location.reload();
					}else{
						alert("添加失败");
					}
				}
		 }); 
		}
		
	}
}

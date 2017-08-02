$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/order/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		
		$("input[type='text']").val("");
		$("#device").empty();
	});
	
	$(".btn_add").click(function(){
        var tr = "<tr><td>new</td><td>new</td><td>new</td><td>new</td><td>new</td><td>new</td><td>new</td></tr>";
        $("tbody").append(tr);
    })

});

function redirectUpdatePage(id) {
	window.location.href = "/order/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/order/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/order/delete.jhtml?id=" + id;
}


function getExcel(){
	
	
	$('#serach-form').attr('action','/order/getExcel.jhtml');
	$('#serach-form').submit();
	
	$('#serach-form').attr('action','/order/index.jhtml');
}



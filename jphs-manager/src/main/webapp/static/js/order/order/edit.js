$(function() {
	var date = new Date();
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
		$('#departmentId').val("");
	});
	$('.form_date').datetimepicker({
	    language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 2,
		startDate:date,
		forceParse: 0
	});
	
});
/**
 * 修改接单人  派单
 * @param id
 * @param orderId
 * @param acceptUserId（如果有接单人就是修改接单人、没有就是派单）
 */
function editAcceptUserId(id,orderId,acceptUserId) {
	alert(1);
	window.location.href = "/order/editAcceptUserId.jhtml?id=" + id+"&orderId="+orderId+"&acceptUserId="+acceptUserId;
}
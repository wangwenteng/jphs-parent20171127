$(document).ready(function() {
	var date = new Date();
	$('.form_date').datetimepicker({
		 format: 'yyyy-mm-dd hh:ii:ss',
		 startDate:date
	});

		$("#timeModalData").load("/order/edit.jhtml",function(){});
		
		
});

function redirectUpdatePage(id) {
	window.location.href = "/order/redirectUpdate.jhtml?id=" + id;
}

function redirectUpdatePage(id) {
	window.location.href = "/order/redirectUpdate.jhtml?id=" + id;
}
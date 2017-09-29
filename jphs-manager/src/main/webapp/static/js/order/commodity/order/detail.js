function updateStatus(id,status){
	
	var coId = $("#coId").val();
	 
	if(status == -2){
		if(confirm("确定拒绝吗")){ 
			$.ajax({
				url : "/commodity/return/update.jhtml",
				type : "POST",
				data : {id:id,status:status},
				dataType : 'json',
				success : function(msg) { 
					location.reload();
				} 
			}) 
		 }
	}else if(status == 2){
		if(confirm("确定退款吗")){
			$.ajax({
				url : "/commodity/return/update.jhtml",
				type : "POST",
				data : {id:id,status:status,coId:coId},
				dataType : 'json',
				success : function(msg) { 
					location.reload();
				} 
			}) 
		}
	} 
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
					location.reload();
				}
		 }); 
		}
		
	}
}
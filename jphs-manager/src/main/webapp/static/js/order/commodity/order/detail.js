function updateStatus(id,status){
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
				data : {id:id,status:status},
				dataType : 'json',
				success : function(msg) { 
					location.reload();
				} 
			}) 
		}
	} 
}
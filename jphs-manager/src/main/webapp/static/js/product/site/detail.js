window.onload=function(){
	/*$('.tr_product').click(function(){
		//		norm_service	显示
		//		grade_service	隐藏
		$(".grade_service").hide();
		$(".norm_service").show();
	})*/
}

function tr_product(id){
	for(var a=0;a<20;a++){
		$(".price"+a).hide();
	}
	$(".price"+id).show();
}

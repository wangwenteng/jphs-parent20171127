
window.onload=function(){
// 实例化编辑器

	var um = UM.getEditor('myEditor');
	um.addListener('blur', function() {
		$('#focush2').html('编辑器失去焦点了');
	});
	um.addListener('focus', function() {
		$('#focush2').html('');
	});
	
	$(".btn_addsss").click(function(){
		var trlength = Number($('.price_gradeAdd').length);
		/*alert(trlength);*/
		var a='<div class="price_gradeAdd"><span class="price_add_title">'
				+'		<input type="hidden" id="cpList'+trlength+'" name="cpList['+trlength+'].id" value="'+trlength+'" />'
				+'等级名称：<input style="width: 200px;" type="text" id="gradeName'+trlength+'" name="cpList['+trlength+'].gradeName" /></span>'
				+'	<div class="marage_right_content" style="margin-top: 0; padding-top: 0">'
				+'<table id="dateTable" cellpadding="0" cellspacing="0" class="text-center">'
				+'<tbody id="addtrprice'+trlength+'"> <tr class="headClass">'
				+'		<td width="100px">序号</td>'
				+'		<td width="60px">商品SKU 	</td>'
				+'		<td width="60px">成本价</td>'
				+'		<td width="60px">原价</td>'
				+'		<td width="60px">销售价</td>'
				+'		<td width="60px">分销收益</td>'
				+'		<td width="60px">库存</td>'
				+'		<td width="60px">状态</td>'
				+'		<td width="60px">操作</td>'
				+'	</tr> </tbody> </table>'
				+'<div class="btn_add" onclick="addTd('+trlength+');">+</div></div></div>';
		
	    $(".norm_service").append(a);
	})
	
	
}


function addTd(id){
	
	var number = '';
	
	for(var a=1;a<61;a++){
		number+= '<option>'+a+'</option>'
	}
	// <input type="hidden" id="grade" name="grade" value="1" />
	/*var grade;
	if(id=='chuji'){
		grade = 1;
	}
	if(id=='zhongji'){
		grade = 2;
	}
	if(id=='gaoji'){
		grade = 3;
	}
	if(id=='biaozhun'){
		grade = 0;
	}*/
/*
 * var trlength = Number($('#addClassprice tr').length) -
 * Number($('.headClass').length);
 */
	var trlength = Number($('.addClassprice').length);
	var tr = '<tr class="addClassprice" id="delete'+trlength+'"><td><input style="width:100%" class="form-control" id="title'+trlength+'" name="cpList['+id+'].title" /></td>'
			+'		<td><input style="width:100%" class="form-control" id="name'+id+''+trlength+'" name="cpList['+id+'].name" value="0" /></td>'
			+'		<td><input style="width:100%" class="form-control" id="number'+id+''+trlength+'" name="cpList['+id+'].number"  value="1"/></td>'
			+'		<td><input style="width:100%" class="form-control" id="costPrice'+id+''+trlength+'" name="cpList['+id+'].costPrice" value="2" /></td>'
			+'		<td><input style="width:100%" class="form-control" id="oldPrice'+id+''+trlength+'" name="cpList['+id+'].oldPrice" value="3"/> </td>'
			+'		<td><input style="width:100%" class="form-control" id="price'+id+''+trlength+'" name="cpList['+id+'].price" value="4"/> </td>'
			+'		<td><input style="width:100%" class="form-control" id="profit'+id+''+trlength+'" name="cpList['+id+'].profit" value="5"/>  </td><td>'
			+'<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setJobtitle(\''+id+''+trlength+'\');" data-target="#myModal" />'
			+'<input type="hidden" id="aptitudeIdArr'+id+''+trlength+'" name="priceGrade['+id+'].price['+trlength+'].aptitudeIdArr" value=""/>'
			+'</td>'
			+'<td>'
			+'	<img style="width: 20px;height: 20px;" src="/static/images/shanchu.png" id="delete'+id+''+trlength+'" onclick="deleteTr('+trlength+');">'
			+'</td>'
			+'	<input type="hidden" id="grade'+trlength+'" name="priceGrade['+id+'].price['+trlength+'].grade" value="'+id+'" />'
			+'	</tr>';
	$("#addtrprice"+id).append(tr);
	/*<img style="width: 20px;height: 20px;" src="https://jinpai.b0.upaiyun.com/jinpaihushi/JP20170802114112-25444.png" data-toggle="modal" onclick="setJobtitle('${priceOne.id }');" data-target="#myModal" />
	*//*<input type="button" style="width:100%" class="form-control" value="删除" id="delete'+id+''+trlength+'"  onclick="deleteTr('+trlength+');"  />
	*<button type="button" class="public-info public_btn public_btn_center" data-toggle="modal" onclick="setJobtitle(\''+id+''+trlength+'\');" data-target="#myModal" >修改</button>
*/}


function deleteTr(id){
	if(confirm('确实要删除吗？')){
		 $("#delete"+id).hide();
		 $('#status'+id).val('-1');
	}
}
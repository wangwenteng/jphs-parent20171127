function search(){
	
	var name = $("#name").val();
	var phone = $("#phone").val();
	document.getElementById('userTable').innerHTML = '<th>编号</th>'
		+'<th>昵称</th>'
		+'<th>电话</th>'
		+'<th>选择</th>';
	 $.ajax({
		 	type: "get",
			url: "/voucher/getUserList.jhtml",
			data: {
					name :name,
					phone:phone
			},
			dataType: "json",
			success: function(data) {
				var userList = data.userList;
				for(var i = 0;i<userList.length;i++){
					$('#userTable').append("<tr><td>"+[i+1]+"</td>"
							+ "<td>"+userList[i].name+"</td>" 
							+ "<td>"+userList[i].phone+"</td>" 
							+ "<td><a onclick='choice("+userList[i].id+")'>选择此人</a></td></tr>");
				}
			}
	 });
}


function  showDiv(voucherRepertoryId){
	
	var id = $("#voucherId").val();
	window.location.href = "/voucher/addUser.jhtml?id=" + voucherRepertoryId + "&voucherId="+id;
	
}
function choice(creatorId){
	
	var voucherRepertoryId = $("#voucherRepertoryId").val();
	var voucherId = $("#voucherId").val();
	if(confirm("确定给此人添加优惠券吗？")){
	 $.ajax({
		 	type: "get",
			url: "/voucher/addVoucherUser.jhtml",
			data: {
				creatorId :creatorId,
				voucherId : voucherId,
				voucherRepertoryId : voucherRepertoryId,
			},
			dataType: "json",
			success: function(data) { 
				window.location.href = "/voucher/detail.jhtml?id=" + data.result;
			}
	 }); 
	}
	
		
}
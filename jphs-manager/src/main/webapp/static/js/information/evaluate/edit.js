$(function() {
	// 提交审核
	$("#save").on("click", function() {
		$("#auditForm").submit();
	});
});

function essenceById(id,essence){
	window.location.href = "/information/evaluate/insert.jhtml?id=" + id+"&audit=1&essence="+essence;
}
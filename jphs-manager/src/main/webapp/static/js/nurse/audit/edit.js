$(function() {
	$('#save').click(function() {
		if ($('#audit').val() == 1) {
			if (confirm('您确定要对该护士通过审核吗？')) {
				$("#auditForm").submit();
			}
		} else {
			if ($('#remark').val() == "") {
				alert('请输入不通过理由');
			} else {

				$("#auditForm").submit();
			}
		}
	})
});
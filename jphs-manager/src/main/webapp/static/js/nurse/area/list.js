$(function() {
	$("#redirectAddPage").on("click", function() {
		window.location.href = "/area/redirectAddPage.jhtml";
	});
	// 清除
	$("#clear").on("click", function() {
		$("input[type='text']").val("");
	});

});

function redirectUpdatePage(id) {
	window.location.href = "/area/redirectUpdate.jhtml?id=" + id;
}
function redirectDetailPage(id) {
	window.location.href = "/area/detail.jhtml?id=" + id;
}
function deleteById(id) {
	window.location.href = "/area/delete.jhtml?id=" + id;
}

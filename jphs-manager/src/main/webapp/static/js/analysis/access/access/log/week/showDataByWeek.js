$(document).ready(function() {
	$('#startTime').datetimepicker({
	    language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	$('#endTime').datetimepicker({
		language:  'zh-CN',
		weekStart: 1,
		todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});
	var cells = document.getElementById('monitor').getElementsByTagName('td');
	var clen = cells.length;
	var currentFirstDate;
	var formatDate = function(date) {
		var year = date.getFullYear();
		var month = (date.getMonth() + 1);
		var day = date.getDate();
		if (month < 10) {
			month = '0' + month;
		}
		if (day < 10) {
			day = '0' + day;
		}
		return year + '-' + month + '-' + day;
	};
	var addDate = function(date, n) {
		date.setDate(date.getDate() + n);
		return date;
	};
	var setDate = function(date) {
		var week = date.getDay() - 1;
		date = addDate(date, week * -1);
		currentFirstDate = new Date(date);
		for (var i = 0; i < clen; i++) {
			cells[i].innerHTML = formatDate(i == 0 ? date : addDate(date, 1));
		}
	};
	document.getElementById('last-week').onclick = function() {
		setDate(addDate(currentFirstDate, -7));
	};
	document.getElementById('next-week').onclick = function() {
		setDate(addDate(currentFirstDate, 7));
	};
	setDate(new Date(timeList[0]));
	$("#last-week").click(function() {
		var weekDays =cells[0].innerHTML+"T"+cells[6].innerHTML;
		$('#data').load("/access/log/showDataByWeek.jhtml?weekDays="+ weekDays, "");
	});
	$("#next-week").click(function() {
		var weekDays =cells[0].innerHTML+"T"+cells[6].innerHTML;
		$('#data').load("/access/log/showDataByWeek.jhtml?weekDays="+ weekDays, "");
	});
	$("#search").click(function() {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		if(startTime==""){
			alert("请选择开始日期");
		}
		else if(endTime==""){
			alert("请选择结束日期");
		}
		if(startTime!=""&&endTime!=""){
			weekDays=startTime+'T'+endTime;
			$('#data').load("/access/log/showDataByWeek.jhtml?weekDays="+ weekDays, "");
		}
	});
});

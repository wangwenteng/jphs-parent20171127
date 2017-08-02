$(document).ready(function() {
	$(".form_date").datetimepicker({  
		/*format: 'yyyy-mm',  
        weekStart: 1,  
        autoclose: true,  
        startView: 3,  
        minView: 3,  
        forceParse: false,  
        language: 'zh-CN'  */
		language:  'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 3,
		minView: 3,
		forceParse: false
    });
	var startDay =timeList[0];
	/**
     * 获取上一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */
    function getPreMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        month = parseInt(month) - 1;
        if (month == 0) {
            year = parseInt(year) - 1;
            month = 12;
        }
        if (month < 10) {
            month = '0' + month;
        }
        return year + '-' + month;
    }
    
    /**
     * 获取下一个月
     *
     * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
     */        
    function getNextMonth(date) {
        var arr = date.split('-');
        var year = arr[0]; //获取当前日期的年份
        var month = arr[1]; //获取当前日期的月份
        var month = parseInt(month) + 1;
        if (month == 13) {
            year = parseInt(year) + 1;
            month = 1;
        }
        if (month < 10) {
            month = '0' + month;
        }
        return year + '-' + month;
    }
	$("#last-month").click(function() {
		var month=getPreMonth(startDay);
		$('#data').load("/access/log/showDataByMonth.jhtml?month="+ month, "");
	});
	$("#next-month").click(function() {
		var month=getNextMonth(startDay);
		$('#data').load("/access/log/showDataByMonth.jhtml?month="+ month, "");
	});
	
	$("#search").click(function() {
		var month = $("#month").val();
		if(month==""){
			alert("请选择查询日期");
		}else{
			alert(month);
			$('#data').load("/access/log/showDataByMonth.jhtml?month="+ month, "");
		}
	});
});

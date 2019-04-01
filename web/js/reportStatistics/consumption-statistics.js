var tempData = {
    chart: {
        type: 'column'
    },
    title: {
        text: '维修保养消费本月统计'
    },
    yAxis: {
        min: 0,
        title: {
            text: '金额 (￥)'
        }
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.y}</b><br/>',
        valueSuffix: ' ￥',
        shared: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    credits: {
        enabled: false
    },
    series: []
};

var type = '';

function isGraphics(){
    $("#isGraphics").bootstrapSwitch({
        onText: '柱状图',
        offText: '折线图',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',
        onSwitchChange: function (event, state) {
            if (state == false) {
                tempData = {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '维修保养消费本月统计'
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '金额 (￥)'
                        }
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.y}</b><br/>',
                        valueSuffix: ' ￥',
                        shared: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0.2,
                            borderWidth: 0
                        }
                    },
                    credits: {
                        enabled: false
                    },
                    series: []
                };
                switchsValidator();
            } else if (state == true) {
                tempData = {
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: '维修保养消费本月统计'
                    },
                    yAxis: {
                        title: {
                            text: '金额 (￥)'
                        }
                    },

                    plotOptions: {
                        line: {
                            dataLabels: {
                                enabled: true          // 开启数据标签
                            },
                            enableMouseTracking: false // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                        }
                    },
                    credits: {
                        enabled: false
                    },
                    series: []
                };
                switchsValidator();
            }
        }
    });
}


$(function () {
    destoryValidator("checkWin","checkForm");
    initDateTime("datatimepicker");
    initTab();
    isGraphics();
    getColumnarChart("columnar", "/bill/query_default", tempData,"default","维修保养消费本月统计");
});



function search(count){
    if(count == 1){
        type = 'year'
        var start = $("#start1").val();
        var end = $("#end1").val();
        validator(start,end,type,"维修保养消费年统计");
    }else if(count == 2){
        type = 'quarter'
        var start = $("#start2").val();
        var end = $("#end2").val();
        validator(start,end,type,'维修保养消费季度统计');
    }else if(count == 3){
        type = 'month'
        var start = $("#start3").val();
        var end = $("#end3").val();
        validator(start,end,type,'维修保养消费月统计');
    }else if(count == 4){
        type = 'week'
        var start = $("#start4").val();
        var end = $("#end4").val();
        validator(start,end,type,'维修保养消费周统计');
    }else if(count == 5){
        type = 'day'
        var start = $("#start5").val();
        var end = $("#end5").val();
        validator(start,end,type,'维修保养消费日统计');
    }
}



function validator( start, end, type,text){
    if(start != '' && end != ''){
        getColumnarChart("columnar", "/bill/query_condition?start=" + start +"&end=" + end + "&type=" + type, tempData,type,text);
    }else{
        getColumnarChart("columnar", "/bill/query_default", tempData,"default","维修保养消费本月统计");
    }

}

function switchsValidator(){
    if(type == 'year'){
        type = 'year'
        var start = $("#start1").val();
        var end = $("#end1").val();
        validator(start,end,type,"维修保养消费年统计");
    }else if(type == 'quarter'){
        type = 'quarter';
        var start = $("#start2").val();
        var end = $("#end2").val();
        validator(start,end,type,'维修保养消费季度统计');
    }else if(type == 'month') {
        type = 'month';
        var start = $("#start3").val();
        var end = $("#end3").val();
        validator(start,end,type,'收维修保养消费月统计');
    }else if(type == 'week'){
        type = 'week';
        var start = $("#start4").val();
        var end = $("#end4").val();
        validator(start,end,type,'维修保养消费周统计');
    } else if(type == 'day') {
        type = 'day';
        var start = $("#start5").val();
        var end = $("#end5").val();
        validator(start,end,type,'维修保养消费日统计');
    }else{
        getColumnarChart("columnar", "/bill/query_default", tempData,"default","维修保养消费本月统计");
    }
}


function initDateTime(clazz) {
    $('.' + clazz).datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        initialDate: new Date(),
        autoclose: true,
        todayHighlight: true,
        minView: "month",//选择日期后，不会再跳转去选择时分秒
        todayBtn: true//显示今日按钮
    })
}

function initTab(){
    $("#myTab a").click(function(e){
        $(this).tab("show");
        $(".datatimepicker").val("");
    });
}


var tempData = {
    chart: {
        type: 'line'
    },
    title: {
        text: '配件使用本月统计'
    },
    yAxis: {
        title: {
            text: '条数 (条)'
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
var type = '';

function isGraphics(){
    $("#isGraphics").bootstrapSwitch({
        onText: '折线图',
        offText: '柱状图',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',
        onSwitchChange: function (event, state) {
            if (state == true) {
                tempData = {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '配件使用本月统计'
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: '条数 (条)'
                        }
                    },
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.y}</b><br/>',
                        valueSuffix: ' 条',
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
            } else if (state == false) {
                tempData = {
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: '配件使用本月统计'
                    },
                    yAxis: {
                        title: {
                            text: '条数 (条)'
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

var quantity = '领料数量'
function isQuantity() {
    $("#isQuantity").bootstrapSwitch({
        onText: '领料数量',
        offText: '退料数量',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',
        onSwitchChange: function (event, state) {
            if(state == true){
                quantity = "退料数量"
                switchsValidator();
            } else if(state == false){
                quantity = "领料数量"
                switchsValidator();
            }
        }

    });
}


var companyId='';
$(function () {
    initDateTime("datatimepicker");
    initTab();
    isGraphics();
    isQuantity();
});

function showCompany(){
    validatorCompany();
    $("#checkWin").modal('show');
}
function adminQuery(){
    $("#checkWin").modal('show');
    validatorCompany();
    initSelect2("company", "请选择公司", "/company/company_all", "565");
    destoryValidator("checkWin","checkForm");
}

function companyQuery(){
    getColumnarChart("columnar", "/materialUse/query_default?quantity=" + quantity, tempData,"default","配件使用本月统计");
}

function search(count){
    if(count == 1){
        type = 'year'
        var start = $("#start1").val();
        var end = $("#end1").val();
        validator(start,end,type,"配件使用年统计");
    }else if(count == 2){
        type = 'quarter'
        var start = $("#start2").val();
        var end = $("#end2").val();
        validator(start,end,type,'配件使用季度统计');
    }else if(count == 3){
        type = 'month'
        var start = $("#start3").val();
        var end = $("#end3").val();
        validator(start,end,type,'配件使用月统计');
    }else if(count == 4){
        type = 'week'
        var start = $("#start4").val();
        var end = $("#end4").val();
        validator(start,end,type,'配件使用周统计');
    }else if(count == 5){
        type = 'day'
        var start = $("#start5").val();
        var end = $("#end5").val();
        validator(start,end,type,'配件使用日统计');
    }
}





function validator( start, end, type,text){
    if($("#span").text()== 'admin'){
        if($("#spans").text() != ''){
            if(start != '' && end != ''){
                getLineBasicChart("columnar", "/materialUse/query_condition?start=" + start +"&end=" + end + "&type=" + type + "&companyId="+companyId + "&quantity=" + quantity, tempData,type,text);
            }else{
                getLineBasicChart("columnar", "/materialUse/query_default?companyId="+companyId + "&quantity="+quantity, tempData,"default","配件使用本月统计");
            }
        }else{
            showCompany();
        }
    }else if($("#span").text()== 'company'){
        if(start != '' && end != ''){
            getColumnarChart("columnar", "/materialUse/query_condition?start=" + start +"&end=" + end + "&type=" + type+ "&quantity=" + quantity, tempData,type,text);
        }else{
            getColumnarChart("columnar", "/materialUse/query_default?quantity=" + quantity , tempData,"default","配件使用本月统计");
        }
    }
}


function switchsValidator(){
    if(type == 'year'){
        type = 'year'
        var start = $("#start1").val();
        var end = $("#end1").val();
        validator(start,end,type,"配件使用年统计");
    }else if(type == 'quarter'){
        type = 'quarter';
        var start = $("#start2").val();
        var end = $("#end2").val();
        validator(start,end,type,'配件使用季度统计');
    }else if(type == 'month') {
        type = 'month';
        var start = $("#start3").val();
        var end = $("#end3").val();
        validator(start,end,type,'配件使用月统计');
    }else if(type == 'week'){
        type = 'week';
        var start = $("#start4").val();
        var end = $("#end4").val();
        validator(start,end,type,'配件使用周统计');
    } else if(type == 'day') {
        type = 'day';
        var start = $("#start5").val();
        var end = $("#end5").val();
        validator(start,end,type,'配件使用日统计');
    }else{
        getLineBasicChart("columnar", "/materialUse/query_default?companyId="+companyId + "&quantity="+quantity, tempData,"default","配件使用本月统计");
    }
}

function checkCompany(company) {
    companyId = company.value;
}

function validatorCompany(){
    $("#companyButton").removeAttr("disabled");
    $('#checkForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            companyId: {
                message: '验证失败',
                validators: {
                    notEmpty: {
                        message: '请选择公司'
                    }
                }

            }
        }
    })

        .on('success.form.bv', function (e) {
            $("#checkWin").modal('hide');
            var companyName = $("#company").find("option:selected").text();
            $('#spans').html("当前公司:"+companyName);
            $('#checkForm').data('bootstrapValidator').resetForm(true);
            getLineBasicChart("columnar", "/materialUse/query_default?companyId="+companyId + "&quantity="+quantity, tempData,"default","配件使用本月统计");
        })
}

function check(){
    $("#checkForm").data('bootstrapValidator').validate();
    if ($("#checkForm").data('bootstrapValidator').isValid()) {
        $("#companyButton").attr("disabled","disabled");
    } else {
        $("#companyButton").removeAttr("disabled");
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
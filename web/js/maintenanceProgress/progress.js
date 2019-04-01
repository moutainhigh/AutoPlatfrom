$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/progress/progress_pager_Y");
});

function searchStatus_Y() {
    initTable("cusTable", "/progress/progress_pager_Y");
}

function searchStatus_N() {
    initTable("cusTable", "/progress/progress_pager_N");
}

function searchStatus_All() {
    initTable("cusTable", "/progress/progress_pager");
}

function recordOk() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "请选择一条进度信息", "error");
        return false;
    } else {
        var record = selectRow[0]
        if (record.recordStatus != "N") {
        if (record.speedStatus == "维修保养中" && record.recordStatus != "N") {
            $.get('/record/achieve_record?recordId=' + record.recordId, function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                    swal("成功提示", data.message, "success");
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, 'json');
        } else if (record.speedStatus == "已登记") {
            swal('错误提示', "请先指派员工", "error");
        } else if (record.speedStatus == "已提醒" || record.speedStatus == "未提醒" || record.speedStatus == "已完成") {
            swal('错误提示', "请不要重复完成", "error");
        }
    } else {
            swal('错误提示', "该记录不可用！", "error");
        }
    }

}

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showProgressWin btn btn-primary btn-sm">查看进度</button>'
    ].join('');

}
window.operateEvents = {
    'click .showProgressWin': function (e, value, row, index) {
        var progress = row;
            if (progress.speedStatus == '维修保养中'){
                $(".conduct").css("color", "#1a7bb9");
                $("#text_1").css("color", "#1a7bb9");
                $("#text_2").css("color", "red");
                $("#text_3").css("color", "gray");
                $("#text_4").css("color", "gray");
                $("#text_5").css("color", "gray");
                $("#stopAnimation_2 div").css("background-color", "#009688");
                $("#stopAnimation_3 div").css("background-color", "#009688");
                $("#stopAnimation_4 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#stopAnimation_5 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#animation_1").hide();
                $("#stopAnimation_1").show();
                $("#stopAnimation_3").hide();
                $("#animation_2").show();
                $("#stopAnimation_4").show();
                $("#animation_3").hide();
                $("#stopAnimation_5").show();
                $("#animation_4").hide();
                $("#des_1").text("开始维修保养中");
            } else if(progress.speedStatus == '未提醒'){
                $(".conduct").css("color", "#1a7bb9");
                $("#text_1").css("color", "#1a7bb9");
                $("#text_2").css("color", "#1a7bb9");
                $("#text_3").css("color", "red");
                $("#text_4").css("color", "gray");
                $("#text_5").css("color", "gray");
                $("#stopAnimation_2 div").css("background-color", "#009688");
                $("#stopAnimation_3 div").css("background-color", "#009688");
                $("#stopAnimation_5 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#animation_1").hide();
                $("#stopAnimation_1").show();
                $("#stopAnimation_3").show();
                $("#animation_2").hide();
                $("#animation_4").hide();
                $("#animation_3").show();
                $("#stopAnimation_5").show();
                $("#stopAnimation_4").hide();
                $("#des_1").text("通知提车中");
            } else if (progress.speedStatus == '已提醒'){
                $(".conduct").css("color", "#1a7bb9");
                $("#text_1").css("color", "#1a7bb9");
                $("#text_2").css("color", "#1a7bb9");
                $("#text_3").css("color", "#1a7bb9");
                $("#text_4").css("color", "red");
                $("#text_5").css("color", "gray");
                $("#stopAnimation_2 div").css("background-color", "#009688");
                $("#stopAnimation_3 div").css("background-color", "#009688");
                $("#stopAnimation_4 div").css("background-color", "#009688");
                $("#animation_1").hide();
                $("#stopAnimation_1").show();
                $("#stopAnimation_3").show();
                $("#animation_2").hide();
                $("#animation_3").hide();
                $("#stopAnimation_4").show();
                $("#stopAnimation_5").hide();
                $("#animation_4").show();
                $("#des_1").text("结算金额中");
            } else if (progress.speedStatus == '已完成') {
                $(".conduct").css("color", "#1a7bb9");
                $("#text_1").css("color", "#1a7bb9");
                $("#text_2").css("color", "#1a7bb9");
                $("#text_3").css("color", "#1a7bb9");
                $("#text_4").css("color", "#1a7bb9");
                $("#text_5").css("color", "#1a7bb9");
                $("#stopAnimation_2 div").css("background-color", "#009688");
                $("#stopAnimation_3 div").css("background-color", "#009688");
                $("#stopAnimation_4 div").css("background-color", "#009688");
                $("#stopAnimation_5 div").css("background-color", "#009688");
                $("#animation_1").hide();
                $("#stopAnimation_1").show();
                $("#stopAnimation_3").show();
                $("#animation_2").hide();
                $("#animation_3").hide();
                $("#stopAnimation_4").show();
                $("#stopAnimation_5").hide();
                $("#animation_4").show();
                $("#animation_4").hide();
                $("#stopAnimation_5").show();
                $("#des_1").text("完成车辆维修保养");
            } else {
                $(".conduct").css("color", "red");
                $("#text_1").css("color", "gray");
                $("#text_2").css("color", "gray");
                $("#text_3").css("color", "gray");
                $("#text_4").css("color", "gray");
                $("#text_5").css("color", "gray");
                $("#stopAnimation_2 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#stopAnimation_3 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#stopAnimation_4 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#stopAnimation_5 div").css("background-color", "rgba(158, 158, 158, 0.54)");
                $("#stopAnimation_1").hide();
                $("#animation_1").show();
                $("#stopAnimation_3").show();
                $("#animation_2").hide();
                $("#animation_3").hide();
                $("#stopAnimation_4").show();
                $("#stopAnimation_5").show();
                $("#animation_4").hide();
                $("#des_1").text("维修保养登记中");
            }
        $("#searchDetailWin").modal('show');
    }
}

/**
 * Created by Xiao-Qiang on 2017/5/15.
 */

var contextPath = '';
var materialUseId = ''
$(document).ready(function () {
    var speedStatus = "维修保养中,未提醒,已提醒,已完成"
    initTable("cusTable", contextPath + "/record/pager_speedAndPickingStatus?speedStatus=" + speedStatus);
    initDateTimePickerNotValitor("form_datetime");
    $("#search").bind("click", initTable);
});

function showMUWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "请选择一条数据查看领料明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
        if (record.pickingStatus == "通过") {
            $("#showMuWinBtn").show();
        } else {
            $("#showMuWinBtn").hide();
        }
        initTableSetToolbar("cusTable1", contextPath + "/materialUse/query_pager?recordId=" + recordId, "toolbar1");
        $("#searchMUWin").modal('show');
        destoryValidator("editWin", "editForm");
    }
}

function countPrice(value, row, index) {
    return "" + (row.accPrice * row.accCount);
}

/*function achieve() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "请选择一条数据", "error");
        return false;
    } else {
        var record = selectRow[0];
        if (record.speedStatus == "已登记" || record.speedStatus == "维修保养中") {
            $.get(contextPath + '/record/achieve_record?recordId=' + record.recordId, function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                    swal("成功提示", data.message, "success");
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, 'json');
        } else {
            swal('错误提示', "请不要重复确认!", "error");
            return false;
        }
    }
}*/

function operateFormatter(value, row, index) {
    if (row.pickingStatus == '未审核') {
        return [
            '<button type="button" class="updatePS_1 btn btn-success  btn-sm">通过</button>',
            ' <button type="button" class="updatePS_2 btn btn-danger btn-sm" style="margin-right:15px;">回绝</button>'
        ].join('');
    } else if (row.pickingStatus == '未申请') {
        return ['暂无'].join('');
    } else {
        return ['已完成'].join('');
    }
}

window.operateEvents = {
    'click .updatePS_1': function (e, value, row, index) {
        var status = '通过';
        $.get(contextPath + "/materialUse/updatePickingStatusById?id=" + row.recordId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                    swal("成功提示", data.message, "success");
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                } else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            closeOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            }
                        });
                }
            }, "json");
    },
    'click .updatePS_2': function (e, value, row, index) {
        var status = '未通过';
        $.get(contextPath + "/materialUse/updatePickingStatusById?id=" + row.recordId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                } else if (data.result == "notLogin") {
                    swal({
                            title: "登入失败",
                            text: data.message,
                            type: "warning",
                            showCancelButton: false,
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "确认",
                            closeOnConfirm: true
                        },
                        function (isConfirm) {
                            if (isConfirm) {
                                top.location.href = "/login/show_login";
                            }
                        });
                }
            }, "json");
    }
}

function returnMaterial() {
    var materialUseInfo = $("#cusTable1").bootstrapTable('getSelections')[0];
    var returnCount = $("#returnCount").val();
    var mrStr = new Array();
    mrStr[0] = materialUseInfo.recordId;
    mrStr[1] = materialUseInfo.accId;
    mrStr[2] = returnCount;
    $.get(contextPath + '/materialReturn/add_return?mrStr=' + mrStr, function (data) {
        if (data.result == "success") {
            swal(data.message, "", "success");
            closeEditWin();
            $('#cusTable1').bootstrapTable('refresh');
        } else {
            swal(data.message, "", "error");
        }
    }, 'json');
}

function showReturnMaterial() {
    var row = $("#cusTable1").bootstrapTable('getSelections');
    var materialUseInfo = row[0];
    var record = $("#cusTable").bootstrapTable('getSelections')[0];
    if (row.length != 1) {
        swal('编辑失败', "请选择一条数据", "error");
        return false;
    } else {
        $.get(contextPath + '/materialReturn/is_recordExist?recordId=' + record.recordId + '&accId=' + materialUseInfo.accId, function (data) {
            if (data == "1") {
                if (record.speedStatus == "未提醒" || record.speedStatus == "已提醒" || record.speedStatus == "已完成") {
                    var recordId = record.recordId;
                    materialUseId = materialUseInfo.materialUseId;
                    validator("editForm");
                    $("#editForm").fill(materialUseInfo);
                    $("#editWin").modal('show');
                } else {
                    swal('退料失败', "该维修未完成不能退料!", "error");
                    return false;
                }
            } else {
                swal('退料失败', "请不要重复退料!", "error");
                return false;
            }
        }, 'json');
    }
}

function closeEditWin() {
    $("#editWin").modal('hide');
}

/*function isRM(value, row, index) {
    $.get(contextPath + '/materialUse/is_recordExist?recordId=' + row.recordId + '&accId=' + row.accId,
        function (data) {
            if (data == "1") {
                return "否";
            } else {
               return "是";
            }
        }, 'json');
}*/

/** 表单验证 */
function validator(formId) {
    $("#editButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            accCount: {
                message: '退料数量验证失败',
                validators: {
                    notEmpty: {
                        message: '退料数量不能为空'
                    }, regexp: {
                        regexp: /^\+?[1-9]\d*$/,
                        message: '只能输入大于零的数量!'
                    },
                    threshold: 11,
                    remote: {
                        url: contextPath + '/materialUse/queryIs_CountThan?materialUseId=' + materialUseId,
                        message: '退料数量大于物料清单数量',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            }
        }
    })/*
        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit(contextPath + "/materialReturn/add_materialReturn", formId, "editWin");
                $('#cusTable1').bootstrapTable('refresh');
            }
        })*/

}


/**
 * Created by Administrator on 2017-04-30.
 */
var speedStatus;
$(document).ready(function () {
    //调用函数，初始化表格
    speedStatus = "未提醒";
    initTable("cusTable", "/record/pager_speedStatus?speedStatus=" + speedStatus);

    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");
    initDateTimePicker("datetimepicker", "chargeTime", "addForm");

    $(".remindMethod").select2({
        // enable tagging
        tags: false,
        width: '565px',
        language: 'zh-CN',
        minimumResultsForSearch: -1,
        placeholder: "提醒方式",
        multiple: true
    });

    destoryValidator("addWin", "addForm");
    destoryValidator("remindWin", "remindForm");
});

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var carPlate = $("#searchCarPlate").val();
    var maintainOrFix = $("#searchMaintainOrFix").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/record/condition_pager?userName=" + userName + "&carPlate=" + carPlate + "&maintainOrFix=" + maintainOrFix + "&companyId=" + companyId + "&speedStatus=" + speedStatus);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchUserName").val('');
    $("#searchCarPlate").val('');
    $("#searchMaintainOrFix").val('all');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}

/** 给datetimepicker添加默认值 */
function getDate() {

    $("#addChargeTime").val(new Date());
}

/** 查看未提醒 */
function notRemind() {
    speedStatus = "未提醒";
    initTable("cusTable", "/record/pager_speedStatus?speedStatus=" + speedStatus);
}

/** 查看已提醒 */
function alreadyRemind() {
    speedStatus = "已提醒";
    initTable("cusTable", "/record/pager_speedStatus?speedStatus=" + speedStatus);
}

/** 查看已完成 */
function alreadyComplete() {
    speedStatus = "已完成";
    initTable("cusTable", "/record/pager_speedStatus?speedStatus=" + speedStatus);
}

/** 查看全部 */
function allRemind() {
    speedStatus = "已提醒,未提醒,已完成";
    initTable("cusTable", "/record/pager_speedStatus?speedStatus=" + speedStatus);
}

/** 显示结算提车的win，添加收费单据的win */
function showAddWin() {
    validator("addForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('结算失败', "只能选择一条维修保养记录", "error");
        return false;
    } else {
        var record = selectRow[0];
        var count = 0;
        $.get("/detail/pager?recordId=" + record.recordId + "&pageNumber=1&pageSize=20",
            function(data) {
                var len = data.rows.length;
                if (len > 0) {
                    for (var i = 0; i < len; i++) {
                        count += data.rows[i].price;
                    }
                    $("#addWin").modal('show');
                    $("#addForm").fill(record);
                    $("#addChargeBillMoney").val(count);
                    $("#addActualPayment").val(count);
                } else {
                    swal('结算失败', "该车主没有做维修保养项目", "error");
                    return false;
                }

            }, "json");


    }
}

/** 返回是否是注册车主 */
function formatterUser(value, row, index) {
    if (row.checkin.userId != null && row.checkin.userId != "") {
        return "是";
    } else {
        return "<span style='color: red;'>否</span>";
    }

}

var userFlag = true; // 用来标识车主是否是已注册车主，只有已注册车主才能发送邮箱提醒
/** 显示提车提醒的win */
function showRemindWin() {
    validator("remindForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length < 1) {
        swal('提醒失败', "至少要选择一条记录", "error");
        return false;
    } else {
        userFlag = true;
        var len = selectRow.length;
        var ids = "";
        var flag = true;
        var recordIds = "";
        var carPlate = "";
        var userName = "";
        for (var i = 0; i < len; i++) {
            var record = selectRow[i];
            if (selectRow[0].speedStatus != selectRow[i].speedStatus) {
                flag = false;
            } else {
                if (record.speedStatus == "已提醒" || record.speedStatus == "已完成") {
                    flag = false;
                } else {
                    if (record.checkin.userId == null || record.checkin.userId == "") { // 有非注册用户
                        userFlag = false;
                    }
                    if (ids == "") {
                        ids = record.checkin.userId;
                    } else {
                        ids += "," + record.checkin.userId;
                    }
                    if (recordIds == "") {
                        recordIds = record.recordId;
                        carPlate = record.checkin.carPlate;
                        userName = record.checkin.userName;
                    } else {
                        recordIds += "," + record.recordId;
                        carPlate += "," + record.checkin.carPlate;
                        userName += "、" + record.checkin.userName;
                    }
                }
            }


        }
        if (flag) {
            swal({
                    title: "确认操作?",
                    text: "您确定想车主：" + userName + ",发送提车提醒吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确认",
                    cancelButtonText: "取消",
                    closeOnConfirm: true,
                    closeOnCancel: true
                },
                function (isConfirm) {
                    if (isConfirm) {
                        $("#remindUserId").val(ids);
                        $("#remindRecordId").val(recordIds);
                        $("#remindCarPlate").val(carPlate);
                        $("#remindTitle").val("维修保养提车提醒");
                        $('#remindMethod').val("message");
                        $("#remindWin").modal('show');
                    } else {
                    }
                });
        } else {
            swal('提醒失败', "只能选择未提醒的记录", "error");
        }

    }
}

/** 全部提醒 */
function showAllRemindWin() {
    validator("remindForm");
    var selectRow = $("#cusTable").bootstrapTable('getData');
    userFlag = true;
    var len = selectRow.length;
    var ids = "";
    var recordIds = "";
    var carPlate = "";
    var count = 0;
    var userName = "";
    for (var i = 0; i < len; i++) {
        var record = selectRow[i];

        if (record.speedStatus == "已提醒" || record.speedStatus == "已完成") {
            continue;
        } else {
            if (record.checkin.userId == null || record.checkin.userId == "") { // 有非注册用户
                userFlag = false;
            }
            if (ids == "") {
                ids = record.checkin.userId;
            } else {
                ids += "," + record.checkin.userId;
            }
            if (userName == "") {
                recordIds = record.recordId;
                carPlate = record.checkin.carPlate;
                userName = record.checkin.userName;
            } else {
                recordIds += "," + record.recordId;
                carPlate += "," + record.checkin.carPlate;
                userName += "、" + record.checkin.userName;
            }
            count++;
        }
    }
    if (count <= 0) {
        swal('提醒失败', "没有未提醒的记录", "error");
    } else {
        swal({
                title: "确认操作?",
                text: "您确定想车主：" + userName + ",发送提车提醒吗？",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: true,
                closeOnCancel: true
            },
            function (isConfirm) {
                if (isConfirm) {
                    $("#remindUserId").val(ids);
                    $("#remindRecordId").val(recordIds);
                    $("#remindCarPlate").val(carPlate);
                    $("#remindTitle").val("维修保养提车提醒");
                    $('#remindMethod').val("message");
                    $("#remindWin").modal('show');
                } else {
                }
            });

    }

}

/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#remindButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            chargeBillMoney: {
                validators: {
                    notEmpty: {
                        message: '总金额不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 5,
                        message: '总金额的长度不能超过5位'
                    }
                }
            },
            actualPayment: {
                validators: {
                    notEmpty: {
                        message: '车主实际付款不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 5,
                        message: '实际付款金额不能超过5位'
                    }
                }
            },
            chargeTime: {
                validators: {
                    notEmpty: {
                        message: '付款时间不能为空'
                    }

                }
            },
            chargeBillDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            },
            remindTitle: {
                validators: {
                    notEmpty: {
                        message: '提醒标题不能为空'
                    }

                }
            },
            remindMethod: {
                validators: {
                    notEmpty: {
                        message: '至少选择一种提醒方式'
                    },
                    callback: {
                        message: "未注册的车主用户不能发送邮箱提醒哦",
                        callback: function (value, validator) {
                            if (value != null && value != "") {
                                var len = value.length;
                                if (userFlag) {
                                    return true;
                                } else {
                                    if (len == 1) {
                                        if (value == "email") {
                                            return false;
                                        } else {
                                            return true;
                                        }
                                    } else {
                                        return false;
                                    }
                                }
                            } else {
                                return false;
                            }

                        }
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/bill/add", formId, "addWin");
                $("input[type=reset]").trigger("click");
            } else if (formId == "remindForm") {
                formSubmit("/record/send_remind", formId, "remindWin");
            }


        })
}

var contextPath = '';
var speedStatus;
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/record/pager?status=ALL");

    initSelect2("maintain_fix", "请选择维修保养项目", "/maintainFix/maintain_all", "540");

    destoryValidator("editWin", "editForm");
    destoryValidator("detailWin", "detailForm");
    destoryValidator("editDetailWin", "editDetailForm");

    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    $("#maintainFixWin").on("hide.bs.modal", function () {
        $("#carPlate").html('');
        $("#userName").html('');
        $("#userPhone").html('');
        $("#maintainCarMileage").html('');
        $("#startTime").html('');
        $("#count").html('');
        $("#fixCount").html('');

        for (var i = 0; i < 10; i++) {
            $("#maintainName" + i).html('');
            $("#maintainMoney" + i).html('');
            $("#maintainPrice" + i).html('');

            $("#fixName" + i).html('');
            $("#fixAcc" + i).html('');
            $("#fixCarMileage" + i).html('');
            $("#fixPrice" + i).html('');
            $("#fixTime" + i).html('');
        }
    });

});

/** 格式化操作栏的按钮 */
function operateFormatter(value, row, index) {
    if (row.recordStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>'
        ].join('');
    }

}

/** 格式化选择保养项目操作栏 */
function formatterChoiceMaintain(value, row, index) {
    return [
        '<button type="button" class="choiceMaintain btn btn-primary btn-sm">选择</button>'
    ].join('');
}

/** 格式化选择维修项目操作栏 */
function formatterChoiceFix(value, row, index) {
    return [
        '<button type="button" class="choiceFix btn btn-primary btn-sm">选择</button>'
    ].join('');
}

/** 点击事件监听 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get(contextPath + "/record/update_status?id=" + row.recordId + "&status=" + row.recordStatus,
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
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get(contextPath + "/record/update_status?id=" + row.recordId + "&status=" + row.recordStatus,
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
    },
    'click .showEditWin': function (e, value, row, index) {

        var record = row;
        $("#editForm").fill(record);
        $("#editWin").modal('show');
    },
    'click .choiceMaintain': function (e, value, row, index) {
        choiceMaintainOrFixCommon("maintainWin", row);
    },
    'click .choiceFix': function (e, value, row, index) {
        choiceMaintainOrFixCommon("fixWin", row);
    }
}

function choiceMaintainOrFixCommon(winId, row) {
    var maintain = row;
    var recordId = $("#detailRecordId").val();
    $.get("/detail/query_detail?recordId=" + recordId + "&maintainId=" + maintain.maintainId,
        function (data) {
            if (data.result == "success") { // 没有记录
                $.get("/detail/query_acc?maintainIds=" + maintain.maintainId,
                    function (data) {
                        if (data.result == "success") { // 有配件，可以添加
                            $("#detailMaintainId").val(maintain.maintainId);
                            $("#detailMaintainName").val(maintain.maintainName);
                            maintainMoney = maintain.maintainMoney;
                            $("#" + winId).modal('hide');
                        } else if (data.result == "fail") { // 没有配件，添加失败
                            swal("错误提示", data.message, "error");
                        } else if (data.result == "notLogin") { // Session失效
                            swal({
                                    title: "登入失败",
                                    text: data.message,
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
                                        top.location.href = "/login/show_login";
                                    } else {
                                    }
                                });
                        }
                    }, "json");
            } else if (data.result == "fail") { // 有记录
                swal("错误提示", data.message, "error");
            } else if (data.result == "notLogin") { // Session失效
                swal({
                        title: "登入失败",
                        text: data.message,
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
                            top.location.href = "/login/show_login";
                        } else {
                        }
                    });
            }
        }, "json");
}

/** 显示编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var record = selectRow[0];
        $("#editForm").fill(record);
        $("#editWin").modal('show');
    }
}

var maintainMoney; // 用于验证输入的减价是否合理
/** 表单验证 */
function validator(formId) {
    $("#editButton").removeAttr("disabled");
    $("#detailButton").removeAttr("disabled");
    $("#editDetailButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            recordDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            },
            maintainDiscount: {
                validators: {
                    notEmpty: {
                        message: '折扣或者减价不能为空'
                    },
                    numeric: {
                        message: '折扣或者减价只能是数字'
                    },
                    regexp: {
                        regexp: /^[\.0-9]+$/,
                        message: '折扣或者减价不能小于等于0'
                    },
                    callback: {
                        message: "减价不能高于维修或保养的原价",
                        callback: function (value, validator) {
                            if (value < 0) {
                                return false;
                            } else {
                                if (maintainMoney == null || maintainMoney == "" || maintainMoney == undefined || maintainMoney == 0) {
                                    return false;
                                } else {
                                    if (value <= maintainMoney) {
                                        return true;
                                    } else {
                                        return false;
                                    }
                                }

                            }

                        }
                    }

                }
            },
            maintainId: {
                validators: {
                    notEmpty: {
                        message: '维修保养项目不能为空'
                    }

                }
            },
            maintainName: {
                validators: {
                    notEmpty: {
                        message: '请选择维修保养项目'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {


            if (formId == "editForm") {
                formSubmit("/record/edit", formId, "editWin");
            } else if (formId == "detailForm") {
                formSubmit("/detail/add", formId, "detailWin");

            } else if (formId == "editDetailForm") {
                formSubmit("/detail/edit", formId, "editDetailWin");
                $('#detailTable').bootstrapTable('refresh');
                $("#searchDetailWin").modal('show');
            }

        })

}

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

/** 显示生成维修保养明细的窗口 */
function showAddDetailWin() {
    validator("detailForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据生成维修保养明细", "error");
        return false;
    } else {
        maintainMoney = 0;
        var record = selectRow[0];
        $("input[type=reset]").trigger("click");
        $("#detailForm").fill(record);
        $("#detailWin").modal('show');
    }
}

/** 显示查看保养明细详情 */
function showDetailWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据查看维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        if (record.signStatus == "Y") {
            $("#signDiv").show();
            $("#editBtn").hide();
            $("#signBtn").hide();
            $("#detailBtn").css("margin-left", "230px");
        } else {
            $("#signDiv").hide();
            $("#editBtn").show();
            $("#signBtn").show();
        }
        var recordId = record.recordId;
        initTableSetToolbar("detailTable", "/detail/pager?recordId=" + recordId, "toolbar1");
        $("#searchDetailWin").modal('show');
    }
}

/** 显示修改保养明细详情的窗口 */
function showEditDetailWin() {
    validator("editDetailForm");
    var selectRow = $("#detailTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "只能选择一条数据修改", "error");
        return false;
    } else {
        var detail = selectRow[0];
        $("#editDetailForm").fill(detail);
        $('#editDetailMaintain').html('<option value="' + detail.maintain.maintainId + '">' + detail.maintain.maintainName + '</option>').trigger("change");
        $("#searchDetailWin").modal('hide');
        $("#editDetailWin").modal('show');
    }
}

/** 关闭修改维修保养明细的窗口 */
function closeEditDetailWin() {
    $("#editDetailWin").modal('hide');
    $("#searchDetailWin").modal('show');
}

/** 格式化原价和现价 */
function formatterMoney(value, row, index) {
    return "￥" + value;
}

/** 格式化打折或减价 */
function formatterDiscount(value, row, index) {
    if (parseFloat(value) < 1) {
        return value + "折";
    } else if (parseFloat(value) == 0) {
        return "无减价或折扣";
    } else {
        return "￥" + value;
    }
}

/** 选择维修保养项目 */
function choiseMaintain() {
    var maintainOrFix = $("#maintainOrFix").val();
    if (maintainOrFix == "维修") {
        initTableNotTollbar("fixTable", "/maintainFix/queryByPager");
        $("#fixWin").modal('show');
    } else if (maintainOrFix == "保养") {
        initTableNotTollbar("maintainTable", "/maintainFix/queryByMaintenanceItemPager");
        $("#maintainWin").modal('show');
    }
}

/** 确定选择的保养项目 */
function determineMaintain() {
    determineMaintainOrFix("maintainTable", "maintainWin", "只能选择一条保养项目");
}

/** 确定选择的维修项目 */
function determineFix() {
    determineMaintainOrFix("fixTable", "fixWin", "只能选择一条维修项目");
}

/** 选择维修保养项目的公共方法 */
function determineMaintainOrFix(tableId, winId, message) {
    var selectRow = $("#" + tableId).bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', message, "error");
        return false;
    } else {
        var maintain = selectRow[0];
        var recordId = $("#detailRecordId").val();
        $.get("/detail/query_detail?recordId=" + recordId + "&maintainId=" + maintain.maintainId,
            function (data) {
                if (data.result == "success") { // 没有记录
                    $.get("/detail/query_acc?maintainIds=" + maintain.maintainId,
                        function (data) {
                            if (data.result == "success") { // 有配件，可以添加
                                $("#detailMaintainId").val(maintain.maintainId);
                                $("#detailMaintainName").val(maintain.maintainName);
                                maintainMoney = maintain.maintainMoney;
                                $("#" + winId).modal('hide');
                            } else if (data.result == "fail") { // 没有配件，添加失败
                                swal("错误提示", data.message, "error");
                            } else if (data.result == "notLogin") { // Session失效
                                swal({
                                        title: "登入失败",
                                        text: data.message,
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
                                            top.location.href = "/login/show_login";
                                        } else {
                                        }
                                    });
                            }
                        }, "json");
                } else if (data.result == "fail") { // 有记录
                    swal("错误提示", data.message, "error");
                } else if (data.result == "notLogin") { // Session失效
                    swal({
                            title: "登入失败",
                            text: data.message,
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
                                top.location.href = "/login/show_login";
                            } else {
                            }
                        });
                }
            }, "json");

    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var carPlate = $("#searchCarPlate").val();
    var maintainOrFix = $("#searchMaintainOrFix").val();
    var companyId = $("#searchCompanyId").val();
    speedStatus = "已登记";
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

/** 生成明细清单 */
function generateDetail() {
    var tableData = $('#detailTable').bootstrapTable('getData');
    var len = tableData.length;
    if (len > 0) {
        var detail1 = tableData[0];
        var maintainOrFix = detail1.record.checkin.maintainOrFix;
        var count = 0;
        var fixCount = 0;
        if (maintainOrFix == "保养") {
            for (var i = 0; i < len; i++) {
                var detail = tableData[i];
                $("#maintainName" + i).html(detail.maintain.maintainName);
                $("#maintainMoney" + i).html('￥' + detail.maintain.maintainMoney);
                $("#maintainPrice" + i).html('￥' + detail.price);
                count += detail.price;
            }
            $("#count").html("￥" + count);
        } else if (maintainOrFix == "维修") {
            for (var i = 0; i < len; i++) {
                var detail = tableData[i];
                $("#fixName" + i).html(detail.maintain.maintainName);
                $("#fixAcc" + i).html("配件名");
                $("#fixCarMileage" + i).html(detail.record.checkin.carMileage);
                $("#fixPrice" + i).html("￥" + detail.price);
                $("#fixTime" + i).html(formatterDate1(detail.record.startTime));
                fixCount += detail.price;
            }
        }


        var carPlate = detail1.record.checkin.plate.plateName + "-" + detail1.record.checkin.carPlate;
        var userName = detail1.record.checkin.userName;
        var userPhone = detail1.record.checkin.userPhone;
        var maintainCarMileage = detail1.record.checkin.carMileage;
        var startTime = detail1.record.startTime;
        $("#carPlate").html(carPlate);
        $("#userName").html(userName);
        $("#userPhone").html(userPhone);
        $("#maintainCarMileage").html(maintainCarMileage);
        $("#startTime").html(formatterDate1(startTime));
        $("#fixCount").html("￥" + fixCount);

        $("#maintainFixWin").modal("show");
    } else {
        swal("生成明细清单失败", "该用户还没有明细清单", "error");
    }

}

/** 打印维修保养清单 */
function printMaintainAndFix() {

    /*var newWin = window.open('about:blank', '', '');
    var titleHTML = document.getElementById('printDiv').innerHTML;// 拿打印div所有元素
    newWin.document.write("<html><head><title></title><link rel='stylesheet' type ='text/css' href='/css/my-table.css'></head><body>" + titleHTML + "</body></html>");
    newWin.document.location.reload();
    newWin.print();
    newWin.close();*/
    $("#printDiv").jqprint();
    $("#maintainFixWin").modal("hide");
}

/** 用户已签字 */
function userConfirm() {
    swal({
            title: "确认操作?",
            text: "请确认用户已经签字!",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
            cancelButtonText: "取消",
            closeOnConfirm: false,
            closeOnCancel: false
        },
        function (isConfirm) {
            if (isConfirm) {
                var tableData = $('#detailTable').bootstrapTable('getData');
                if (tableData.length > 0) {
                    var maintainIds = "";
                    var recordId = tableData[0].record.recordId;
                    for (var i = 0, len = tableData.length; i < len; i++) {
                        if (maintainIds == "" || maintainIds == null) {
                            maintainIds = tableData[i].maintain.maintainId;
                        } else {
                            maintainIds += "," + tableData[i].maintain.maintainId;
                        }
                    }
                    $.get("/detail/confirm?recordId=" + recordId + "&maintainIds=" + maintainIds, function (data) {
                        if (data.result == "success") {
                            swal("确认成功", data.message, "success");
                            $('#cusTable').bootstrapTable('refresh');
                            $("#signDiv").show();
                            $("#editBtn").hide();
                            $("#signBtn").hide();
                            $("#detailBtn").css("margin-left", "230px");
                        } else if (data.result == "fail") {
                            swal("确认失败", "出现了一个错误", "error");
                        } else if (data.result == "notLogin") {
                            swal({
                                    title: "登入失败",
                                    text: data.message,
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
                                        top.location.href = "/login/show_login";
                                    } else {
                                    }
                                });
                        }
                    }, "json");
                } else {
                    swal("确认失败", "该用户还没有明细清单", "error");
                }


            } else {
                swal("取消操作", "您已经取消操作", "error");
            }
        });
}





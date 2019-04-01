var contextPath = '';
var speedStatus;
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/record/record_queryPager?status=ALL");
});

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
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
            $("#signBtn").hide();
        } else {
            $("#signDiv").hide();
            $("#signBtn").show();
        }
        var recordId = record.recordId;
        initTableSetToolbar("detailTable", "/detail/pager?recordId=" + recordId, "toolbar1");
        $("#searchDetailWin").modal('show');
    }
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
                            $("#signBtn").htdi();
                            $("#signDiv").show();
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

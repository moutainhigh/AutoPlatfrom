/**
 * Created by Xiao-Qiang on 2017/4/26.
 */
var contextPath = '';
$(document).ready(function () {
    var speedStatus = "维修保养中,未提醒,已提醒,已完成"
    initTable("cusTable", contextPath + "/record/pager_picking?speedStatus=" + speedStatus);
    initDateTimePickerNotValitor("form_datetime");
    $("#search").bind("click", initTable);
});

function showEditWin() {
    validator("editForm");
    var selectRow1 = $("#cusTable1").bootstrapTable('getSelections');
    if (selectRow1.length != 1) {
        swal('编辑失败', "请选择一条数据进行编辑", "error");
        return false;
    } else {
        var record = $("#cusTable").bootstrapTable('getSelections')[0];
        if (record.pickingStatus == "未申请") {
            var materialListInfo = selectRow1[0];
            $("#editForm").fill(materialListInfo);
            $("#editWin").modal('show');
        } else {
            swal('编辑失败', "只能编辑未申请的清单", "error");
        }
    }
}

function closeEditWin() {
    $("#editWin").modal('hide');
}

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
            materialCount: {
                message: '物料数量验证失败',
                validators: {
                    notEmpty: {
                        message: '物料数量不能为空'
                    }, regexp: {
                        regexp: /^\+?[1-9]\d*$/,
                        message: '只能输入大于零的数量!'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit(contextPath + "/materialList/update_count", formId, "editWin");
                $('#cusTable1').bootstrapTable('refresh');
            }
        })

}

function operateFormatter(value, row, index) {
    if (row.materialStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">冻结</button>',
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">激活</button>',
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/materialList/update_status?id=" + row.materialId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable1').bootstrapTable('refresh');
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
        var status = 'Y';
        $.get(contextPath + "/materialList/update_status?id=" + row.materialId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable1').bootstrapTable('refresh');
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

function bySelectSearch() {
    var userName = $("#searchUserName").val();
    var startTime = $("#createTimeStart").val();
    var endTime = $("#createTimeEnd").val();
    initTableSetToolbar("cusTable1", contextPath + "/materialList/select_query?userName=" + userName + "&startTime=" + startTime + "&endTime=" + endTime, 'toolbar1');
}

function queryStatus(status) {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    var record = selectRow[0];
    var recordId = record.recordId;
    initTableSetToolbar('cusTable1', contextPath + '/materialList/queryByStatus_materialList?status=' + status + '&recordId=' + recordId, 'toolbar1');
}

function queryAll() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    var record = selectRow[0];
    var recordId = record.recordId;
    initTableSetToolbar("cusTable1", contextPath + "/materialList/query_pager?recordId=" + recordId, "toolbar1");
}

function countPrice(value, row, index) {
    return "" + (row.accPrice * row.materialCount);
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#createTimeStart").val('');
    $("#createTimeEnd").val('');
    $("#searchUserName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function showMaterialWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "请选择一条数据查看维修保养明细", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
        initTableSetToolbar("cusTable1", contextPath + "/materialList/query_pager?recordId=" + recordId, "toolbar1");
        $("#searchMaterialWin").modal('show');
        destoryValidator('editWin', 'editForm');
    }
}

/** 显示是否回访 */
function formatterTrack(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "<span style='color: red'>否</span>";
    }
}

function showGetMaterial() {
    var record = $("#cusTable").bootstrapTable('getSelections')[0];
    var recordId = record.recordId;
    $.get(contextPath + '/materialList/query_isUse?recordId=' + recordId,
        function (data) {
            if (data == "1") {
                var materialLists = $("#cusTable1").bootstrapTable('getData');
                var accIds = new Array();
                var accCounts = new Array();
                if (materialLists.length > 0) {
                    if (record.pickingStatus == "未申请") {
                        $.each(materialLists, function (index, item) {
                            accIds[index] = materialLists[index].accId;
                            accCounts[index] = materialLists[index].materialCount;
                        });
                        $.get(contextPath + "/materialUse/add_material?recordId=" + recordId + "&accIds=" + accIds + "&accCounts=" + accCounts,
                            function (data) {
                                if (data.result == "success") {
                                    swal("成功提示", data.message, "success");
                                    $("#searchMaterialWin").modal('hide');
                                    $('#cusTable').bootstrapTable('refresh');
                                } else if (data.result == "fail") {
                                    swal("错误提示", data.message, "error");
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
                    } else {
                        swal('申请失败', "当前记录已经申请过了", "error");
                    }
                } else {
                    swal('申请失败', "当前记录没有物料", "error");
                }
            } else {
                swal('申请失败', "请先指定员工！", "error");
            }
        }, 'json');
}
function isUse(value, row, index) {
    var record = $("#cusTable").bootstrapTable('getSelections')[0];
    if (record.pickingStatus == '未申请') {
        return '未申请';
    }
    return '已申请';
}

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/peopleManage/workInfo_Status_Y");
    initSelect2("work_user", "请选择员工", "/peopleManage/self_user", 565);
    destoryValidator("editWin","editForm");
});


function operateFormatter(value, row, index) {
    if (row.workStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">冻结</button>',
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">激活</button>',
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var workStatus = 'N';
        $.get("/peopleManage/workInfo_status?id=" + row.workId + "&status=" + workStatus,
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
        var workStatus = 'Y';
        $.get("/peopleManage/workInfo_status?id=" + row.workId + "&status=" + workStatus,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
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

/** 查看不可用 */
function showWork_N() {
    initTable("cusTable", "/peopleManage/workInfo_Status_N");
}

/** 查看可用 */
function showWork_Y() {
    initTable("cusTable", "/peopleManage/workInfo_Status_Y");
}

/** 查看全部工单 */
function showWork() {
    initTable("cusTable", "/peopleManage/workInfo_pager");
}


/**编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length > 0) {
        var work = selectRow[0];
        if (work.workStatus != "N"){
            initDateTimePickerNotValitor("form_datetime");
            validator("editForm");
            $("#editForm").fill(work);
            $("#workEndTime").val(formatterDate(work.maintainRecord.endTime));
            $("#editWin").modal("show");
        } else {
            swal('指派失败', "该工单为不可用", "error");
        }
    } else {
        swal('指派失败', "只能选择一条工单进行指派", "error");
    }
}


/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },

    })
        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                var editUser = $("#editUser").val();
                var workEndTime = $("#workEndTime").val();
                if (editUser != null && editUser != "" && workEndTime != null && workEndTime != ""){
                    formSubmit("/peopleManage/workInfo_update", formId, "editWin");
                } else {
                    swal('错误提示', "请确认数据无误再操作", "error");
                }
            }
        })

}

/** 修改提交按钮 */
function editWork() {
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
    } else {
        $("#editButton").removeAttr("disabled");
    }
}
/**
 * Created by xiao-qiang 2017/4/18.
 */
var contextPath = '';
var editModuleName = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", contextPath + "/module/query_pager");
    destoryValidator('addWin', 'addForm');
    destoryValidator('editWin', 'editForm');
});

function showAddWin() {
    validator("addForm");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length < 1) {
        swal('编辑失败', "必须选择一条数据进行编辑", "error");
        return false;
    } else if (selectRow.length > 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var module = selectRow[0];
        editModuleName = module.moduleName;
        validator("editForm");
        $("#editForm").fill(module);
        $("#editWin").modal('show');
    }
}

function operateFormatter(value, row, index) {
    if (row.moduleStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">冻结</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">激活</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/module/update_status?id=" + row.moduleId + "&status=" + status,
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
        var status = 'Y';
        $.get(contextPath + "/module/update_status?id=" + row.moduleId + "&status=" + status,
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
            },  "json");
    },
    'click .showEditWin': function (e, value, row, index) {
        var module = row;
        editModuleName = module.moduleName;
        $("#editForm").fill(module);
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function queryAll() {
    initTable(contextPath + "cusTable", "/module/query_pager");
}

function queryStatus(status) {
    initTable('cusTable', contextPath + '/module/queryByStatus_module?status=' + status);
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
        fields: {
            moduleName: {
                message: '模块名称验证失败',
                validators: {
                    notEmpty: {
                        message: '模块名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: '模块名称长度必须在2到30位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_moduleName?editName=' + editModuleName,
                        message: '该名称已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit(contextPath + "/module/add_module", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit(contextPath + "/module/update_module", formId, "editWin");
                editModuleName = "";
            }
        })

}
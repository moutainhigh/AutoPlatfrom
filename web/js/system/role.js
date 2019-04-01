/**
 * Created by xiao-qiang 2017/4/18.
 */
var contextPath = '';
var editRoleDes = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", contextPath + "/role/query_pager");
    destoryValidator('addWin', 'addForm');
    destoryValidator('editWin', 'editForm');
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);

});

function operateFormatter(value, row, index) {
    if (row.roleStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm">冻结</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm">激活</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/role/update_status?id=" + row.roleId + "&status=" + status,
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
        $.get(contextPath + "/role/update_status?id=" + row.roleId + "&status=" + status,
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
        var role = row;
        editRoleDes = role.roleDes;
        $("#editForm").fill(role);
        validator("editForm");
        $("#editWin").modal('show');
    }
}

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
        var role = selectRow[0];
        editRoleDes = role.roleDes;
        validator("editForm");
        $("#editForm").fill(role);
        $("#editWin").modal('show');
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
        fields: {
            roleName: {
                message: '英文名称验证失败',
                validators: {
                    notEmpty: {
                        message: '英文名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '英文名称长度必须在2到20位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_roleName',
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    },
                    regexp: {
                        regexp: /^[A-Za-z ]*$/,
                        message: '只能是英文'
                    }
                }
            },
            roleDes: {
                message: '中文名称验证失败',
                validators: {
                    notEmpty: {
                        message: '中文名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '名称长度必须在2到10位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_roleDes?editDes=' + editRoleDes,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    },
                    regexp: {
                        regexp: /^[^\d\w\s]*$/,
                        message: '只能是中文'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit(contextPath + "/role/add_role", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit(contextPath + "/role/update_role", formId, "editWin");
                editRoleDes = "";
            }
        })

}

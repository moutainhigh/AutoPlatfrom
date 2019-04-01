/**
 * Created by GOD on 2017/4/17.
 */
var contextPath="";
var editTypeName = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/accessoriesType/pager");

    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
    initSelect2("accType_company", "请选择公司", "/company/company_all", "565");
    initSelect2("company", "请选择公司", "/company/company_all", "150");

    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");
});

/**编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var accessoriesType = selectRow[0];
        editTypeName = accessoriesType.accTypeName;
        validator("editForm");
        $("#editForm").fill(accessoriesType);
        $("#editWin").modal('show');
    }
}

/**提交添加数据 */
function showAddWin() {
    validator("addForm");
    $('#addCompany').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

function operateFormatter(value, row, index) {
    if (row.accTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-danger" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateInfo btn btn-default  btn-sm btn-primary" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-success" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateInfo btn btn-default  btn-sm btn-primary" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/accessoriesType/update_status?id=" + row.accTypeId + "&status=" + status,
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
        $.get(contextPath + "/accessoriesType/update_status?id=" + row.accTypeId + "&status=" + status,
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
    'click .showUpdateInfo': function (e, value, row, index) {
        var accessoriesType = row;
        editTypeName = accessoriesType.accTypeName;
        $("#editForm").fill(accessoriesType);
        $('#editCompany').html('<option value="' + accessoriesType.company.companyId + '">' + accessoriesType.company.companyName + '</option>').trigger("change");
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function queryAll() {
    initTable(contextPath + "cusTable", "/accessoriesType/pager");
}

function queryStatus(status) {
    initTable('cusTable', contextPath + '/accessoriesType/queryByStatus_AccType?status=' + status);
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
            accTypeName: {
                message: '配件分类名称验证失败',
                validators: {
                    notEmpty: {
                        message: '配件分类名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 8,
                        message: '配件分类名称长度必须在2到8位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_accTypeName?editTypeName=' + editTypeName,
                        message: '该分类名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            companyId: {
                validators: {
                    notEmpty: {
                        message: '公司不能为空'
                    }

                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/accessoriesType/add", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/accessoriesType/update", formId, "editWin");
                editTypeName = "";
            }


        })

}

/** 根据条件搜索 */
function searchAccType() {
    var accTypeName = $("#searchAccTypeName").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/accessoriesType/queryByCondition?accTypeName=" + accTypeName + "&companyId=" + companyId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchAccTypeName").val('');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}
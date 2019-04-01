
var editSupplyTypeName = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/supplyType/queryByPager?status=ALL");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");
    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");

});

/** 编辑数据 */
function showEditWin() {

    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var supplyType = selectRow[0];
        editSupplyTypeName = supplyType.supplyTypeName;
        validator("editForm");
        $("#editForm").fill(supplyType);
        $("#editWin").modal('show');
    }
}

function showAddWin() {
    validator("addForm");
    $("input[type=reset]").trigger("click");
    $("#addButton").removeAttr("disabled");
    $("#addWin").modal('show');
}


function operateFormatter(value, row, index) {
    if (row.supplyTypeStatus == 'Y') {
        return [
            '<button type="button" class="updateInActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateSupplyType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateSupplyType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/supplyType/updateStatus?id=" + row.supplyTypeId + "&status=Y",
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
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
            },"json");
    },
    'click .updateInactive': function (e, value, row, index) {
        $.get("/supplyType/updateStatus?id=" + row.supplyTypeId + "&status=N",
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                }else if(data.result == "fail"){
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
            },"json");
    },
    'click .showUpdateSupplyType1': function (e, value, row, index) {
        var supplyType = row;
        editSupplyTypeName = supplyType.supplyTypeName;
        validator("editForm");
        $("#editForm").fill(supplyType);
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
            supplyTypeName: {
                message: '验证失败',
                validators: {
                    notEmpty: {
                        message: '供应商分类名称不能为空'
                    },
                    remote: {
                        url: '/vilidate/queryIsExist_supplyTypeName?editSupplyTypeName=' + editSupplyTypeName,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '供应商分类长度必须在2到20位之间'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/supplyType/add", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/supplyType/edit", formId, "editWin");
                editSupplyTypeName = "";
            }

        })

}

/** 根据条件搜索 */
function searchSupplyType() {
    var supplyTypeName = $("#searchSupplyTypeName").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/supplyType/conditionPager?supplyTypeName=" + supplyTypeName + "&companyId=" + companyId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchSupplyTypeName").val('');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}


/**
 * Created by GOD on 2017/4/18.
 */
var contextPath="";
var editName = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/accessories/pager");

    initDateTimePicker("datetimepicker", "accBuyedTime");
    initDateTimePicker("datetimepicker1", "accUsedTime");
    $("#search").bind("click", initTable);
    initSelect2("acc_supply", "请选择供应商", "/supply/queryAll", "565");
    initSelect2("acc_company", "请选择公司", "/company/company_all", "565");
    initSelect2("acc_accessoriesType", "请选择配件类别", "/accessoriesType/accessoriesType_All", "565");

    initSelect2("company", "请选择公司", "/company/company_all", "150");
    initSelect2("accType", "请选择配件分类", "/accessoriesType/accessoriesType_All", "150");

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
        var accessories = selectRow[0];
        editName = accessories.accName;
        validator("editForm");
        $("#editForm").fill(accessories);
        $('#editCompany').html('<option value="' + accessories.company.companyId + '">' + accessories.company.companyName + '</option>').trigger("change");
        $('#editSupply').html('<option value="' + accessories.supply.supplyId + '">' + accessories.supply.supplyName + '</option>').trigger("change");
        $('#editAccessoriesType').html('<option value="' + accessories.accessoriesType.accTypeId + '">' + accessories.accessoriesType.accTypeName + '</option>').trigger("change");
        $('#editBuyedTime').val(formatterDate(accessories.accBuyedTime));
        $('#editUsedTime').val(formatterDate(accessories.accUsedTime));
        $("#editWin").modal('show');
    }
}


/**提交添加数据 */
function showAddWin() {
    validator("addForm");
    $('#addCompany').html('').trigger("change");
    $('#addSupply').html('').trigger("change");
    $('#addAccessoriesType').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}


function operateFormatter(value, row, index) {
    if (row.accStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-danger" style="margin-right:15px;" >冻结</button>'

        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-success" style="margin-right:15px;" >激活</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/accessories/update_status?id=" + row.accId + "&status=" + status,
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
        $.get(contextPath + "/accessories/update_status?id=" + row.accId + "&status=" + status,
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

function queryAll() {
    initTable(contextPath + "cusTable", "/accessories/pager");
}

function queryStatus(status) {
    initTable('cusTable', contextPath + '/accessories/queryByStatus_Acc?status=' + status);
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
            accName: {
                message: '配件名称验证失败',
                validators: {
                    notEmpty: {
                        message: '配件名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 8,
                        message: '配件名称长度必须在1到8位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_accName?editName=' + editName,
                        message: '该分类名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            accTotal: {
                validators :{
                    notEmpty: {
                        message: '配件数量不能为空'
                    }
                }
            },
            accPrice: {
                validators :{
                    notEmpty: {
                        message: '配件价格不能为空'
                    }
                }
            },
            accDes: {
                validators: {
                    notEmpty: {
                        message: '配件分类描述不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '配件分类描述长度必须在2到15位之间'
                    }
                }
            },
            accCommodityCode: {
                validators :{
                    notEmpty: {
                        message: '商品条码不能为空'
                    }
                }
            },
            accUnit: {
                validators :{
                    notEmpty: {
                        message: '计量单位不能为空'
                    }
                }
            },
            accIdle: {
                validators :{
                    notEmpty: {
                        message: '可用数量不能为空'
                    }
                }
            },
            accSalePrice: {
                validators :{
                    notEmpty: {
                        message: '配件售价不能为空'
                    }
                }
            },
            accUsedTime: {
                validators :{
                    notEmpty: {
                        message: '最近一次领料时间不能为空'
                    }
                }
            },
            accBuyedTime: {
                validators :{
                    notEmpty: {
                        message: '最近一次购买时间不能为空'
                    }
                }
            },
            supplyId: {
                validators :{
                    notEmpty: {
                        message: '供应商不能为空'
                    }
                }
            },
            accTypeId: {
                validators :{
                    notEmpty: {
                        message: '配件分类不能为空'
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
                formSubmit("/accessories/add", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/accessories/update", formId, "editWin");
                editName = "";
            }


        })

}

/** 根据条件搜索 */
function searchAcc() {
    var accName = $("#searchAccName").val();
    var accCommodityCode = $("#searchAccCommodityCode").val();
    var companyId = $("#searchCompanyId").val();
    var accTypeId = $("#searchAccTypeId").val();
    initTable("cusTable", "/accessories/queryByCondition?accName=" + accName + "&accCommodityCode=" + accCommodityCode + "&companyId=" + companyId + "&accTypeId" +accTypeId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchAccName").val('');
    $("#searchAccCommodityCode").val('');
    $('#searchCompanyId').html('').trigger("change");
    $('#searchAccTypeId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}



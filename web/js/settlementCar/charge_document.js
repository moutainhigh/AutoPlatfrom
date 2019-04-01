$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/bill/pager?status=ALL");

    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    destoryValidator("editWin", "editForm");

});

/** 格式化操作栏的按钮 */
function operateFormatter(value, row, index) {
    if (row.chargeBillStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
/** 点击事件监听 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/bill/update_status?id=" + row.chargeBillId + "&status=" + row.chargeBillStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                } else if(data.result == "fail"){
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
        $.get("/bill/update_status?id=" + row.chargeBillId + "&status=" + row.chargeBillStatus,
            function(data){
                if(data.result == "success"){
                    $('#cusTable').bootstrapTable('refresh');
                } else if(data.result == "fail"){
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
    'click .showEditWin': function (e, value, row, index) {

        var chargeBill = row;
        $("#editForm").fill(chargeBill);
        $("#editWin").modal('show');
    }
}

/** 根据条件搜索 */
function searchCondition() {
    var userName = $("#searchUserName").val();
    var userPhone = $("#searchUserPhone").val();
    var paymentMethod = $("#searchPaymentMethod").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/bill/condition_pager?userName=" + userName + "&userPhone=" + userPhone + "&paymentMethod=" + paymentMethod + "&companyId=" + companyId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchUserName").val('');
    $("#searchUserPhone").val('');
    $("#searchPaymentMethod").val('all');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}

/** 显示修改收费单据的信息 */
function showEditWin() {
    validator("editForm");
    initDateTimePicker("datetimepicker", "chargeTime", "editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('修改失败', "只能选择一条收费单据", "error");
        return false;
    } else {
        var chargeBill = selectRow[0];
        $("#editForm").fill(chargeBill);
        $("#editChargeTime").val(formatterDate(chargeBill.chargeTime))
        $("#editWin").modal('show');
    }
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
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit("/bill/edit", formId, "editWin");

            }


        })
}

/** 显示打印收费单据的win */
function showChargeBillWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('打印失败', "只能选择一条收费单据进行打印", "error");
        return false;
    } else {
        var chargeBill = selectRow[0];
        $("#code").html(getCodeNumber());
        $("#chargeTime").html(formatterDate2(chargeBill.chargeTime));
        $("#companyName").html(chargeBill.record.company.companyName);
        $("#paymentMethod").html(chargeBill.paymentMethod);
        $("#actualPaymentMax").html(upDigit(chargeBill.actualPayment));
        $("#actualPayment").html(chargeBill.actualPayment);
        $("#chargeBillDes").html(chargeBill.chargeBillDes);
        $("#chargeBillWin").modal("show");
    }

}

/** 打印收费单据 */
function printChargeBill() {
    /**
    var newWin=window.open('about:blank', '', '');
    var titleHTML = document.getElementById('printDiv').innerHTML;// 拿打印div所有元素
    newWin.document.write("<html><head><title></title><link rel='stylesheet' type ='text/css' href='/css/bootstrap.min.css'></head><body>" + titleHTML + "</body></html>");
    newWin.document.location.reload();
    newWin.print();
    newWin.close();
     */
    $("#printDiv").jqprint();
    $("#chargeBillWin").modal("hide");
}

/** 获取7位数验证码 */
function getCodeNumber() {
    var str = Math.floor((Math.random() * 9 + 1) * 1000000);
    return str;
}



var isUser = false;
var lCount = "";

var alCount = ""; // 配件总数量

var userId = "";
var msg = "只能是数字";

var aTotal = "";
var aIdle = "";

$.ajaxSetup({
    async: true
});

$(document).ready(function () {

    initTable("saleTable", "/accessoriesSale/pager");

    initBsSwitchSale("isUser", switchChange);
    initBsSwitchSale("eIsUser", switchChange);

    disableSwitch("userWin", "isUser");

    destoryValidator("addWin", "addForm");
    destoryValidator("accTypeWin", "accForm");
    destoryValidator("editWin", "editForm");

});

function initBsSwitchSale(id, onSwitchChange) {
    initBsSwitch.call(this, id, onSwitchChange);
}

function switchChange(event, state) {
    onSwitchChange.call(this, event, state);
}

function showSearchFormSale() {
    initDateTimePickerNotValitor("form_datetime");
    showSHForm.call(this);
}


override :switchChange = function (event, state) {
    if (state == true) {
        isUser = true;
        showUserWin();
    } else if (state == false) {
        isUser = false;
    }
}

function disableAddFormInput() {
    $("#accTypeName").prop("disabled", true);
    $("#accName").prop("disabled", true);
    $("#aAccUnit").prop("disabled", true);
    $("#accBuyPrice").prop("disabled", true);
}

function disableFormInput(formId) {
    if (formId != 'addForm') {
        disableAddFormInput();
    } else {
        disableEditInput();
    }
}

function enableFormInput(formId) {
    $("#accTypeName").prop("disabled", false);
    $("#accName").prop("disabled", false);
    $("#aAccUnit").prop("disabled", false);
    $("#accBuyPrice").prop("disabled", false);
}

function enableInput() {
    $("input[name='accSaleCount']").prop("disabled", false);
    $("input[name='accSaleTotal']").prop("disabled", false);
    $("input[name='accSaleMoney']").prop("disabled", false);
}

function disableEditFormInput() {
    $("#eAccTypeName").prop("disabled", true);
    $("#eAccName").prop("disabled", true);
    $("#eAccUnit").prop("disabled", true);
}

function enableEditInput() {
    $("#eAccTypeName").prop("disabled", false);
    $("#eAccName").prop("disabled", false);
    $("#eAccUnit").prop("disabled", false);
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('ions');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var accessoriesSale = selectRow[0];
        $("#editForm").fill(accessoriesSale);

        $("#SaleTime").val(formatterDate(accessoriesSale.accSaleTime));
        $("#editWin").modal('show');
    }
}

function updateAccSaleInfo(formId) {
    var discount = $("#saleDiscount").val();
    if (discount != '' && discount != null) {
        updateAccessoriesSaleInfo(formId);
    } else {
        $("#saleDiscount").val(0);
        updateAccessoriesSaleInfo(formId);
    }
}

/**更新数据 */
function updateAccessoriesSaleInfo(formId) {
    var lastCount = $("#eLastCount").val();
    if (lastCount != null && lastCount != '') {
        $.get("/accessoriesSale/update?lastCount=" + lastCount,
            $("#editForm").serialize(),
            function (data) {
                if (data.result == "success") {
                    $('#editWin').modal('hide');
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                    allSales();
                    $(formId).data('bootstrapValidator').resetForm(true);
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }
}

function addAccSaleInfo(formId) {
    var discount = $("#accSaleDiscount").val();
    if (discount != '' && discount != null) {
        addAccessoriesSaleInfo(formId);
    } else {
        $("#accSaleDiscount").val(0);
        addAccessoriesSaleInfo(formId);
    }
}

function addAccessoriesSaleInfo(formId) {
    var lCount = $("#aLastCount").val();
    if (lCount != null && lCount != '') {
        $.post("/accessoriesSale/addSale?lastCount=" + lCount,
            $("#addForm").serialize(),
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    clearTempData();
                    swal(data.message, "", "success");
                    $('#saleTable').bootstrapTable('refresh');
                    $("input[type=reset]").trigger("click");
                    $(formId).data('bootstrapValidator').resetForm(true);
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            }, "json");
    }
}

function fmtOperate(value, row, index) {
    if (row.accSaleStatus == 'Y') {
        return ['<button type="button" class="removeSale btn btn-danger  btn-sm" style="margin-right:15px;">冻结</button>',
            '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('')
    } else {
        return ['<button type="button" class="enableSale btn btn-success  btn-sm" style="margin-right:15px;">激活</button>',
            '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('')
    }
}

function fmtOption() {
    return ['<button type="button" class="stData btn btn-primary btn-sm" style="margin-right:15px;" >选择</button>'].join('')
}

function fmtOpt() {
    return ['<button type="button" class="stUser btn btn-primary btn-sm" style="margin-right:15px;" >选择</button>'].join('')
}

window.operateEvents = {
    'click .removeSale': function (e, value, row, index) {
        $.get("/accessoriesSale/remove?id=" + row.accSaleId,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#saleTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            });
    },
    'click .showEditWin': function (e, value, row, index) {
        var accessoriesSale = row;
        console.log(accessoriesSale);
        $("#editForm").fill(accessoriesSale);



        aTotal = accessoriesSale.accessories.accTotal;
        aIdle = aTotal - accessoriesSale.accSaleCount;

        $("#eLastCount").val(aIdle);
        $("#saleDiscount").val(reSetDiscount(accessoriesSale.accSaleDiscount));
        $("#saleTime").val(formatterDate(accessoriesSale.accSaledTime));

        disableEditFormInput();
        showAccEditWin();

        autoEditCalculationCount1("saleCount", "salePrice", "saleDiscount", "saleTotal", "saleMoney", "eLastCount", accessoriesSale.accessories.accTotal, accessoriesSale.accessories.accIdle, accessoriesSale.accSaleCount);

    },
    'click .enableSale': function (e, value, row, index) {
        $.get("/accessoriesSale/enable?id=" + row.accSaleId,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#saleTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            });
    },
    'click .stData': function (e, value, row, index) {
        var acc = row;
        $("#addForm").fill(acc);
        $("#aLastCount").val(acc.accIdle);
        $("#accWin").modal("hide");
        enableInput();
        autoEditCalculationCount("aSaleCount", "accSalePrice", "accSaleDiscount", "accSaleTotal", "accSaleMoney", "aLastCount", row.accTotal, row.accIdle);

    },
    'click .stUser': function (e, value, row, index) {
        var user = row;
        $("#addForm").fill(user);
        enableSwitch("userWin", "isUser");
        $("#userWin").modal("hide");
    }
}

function showAccessories() {
    initTableNotTollbar("accTable", "/accessories/pager");
    validator("accForm");
    $("#accWin").modal("show");
}


function addAcc() {
    var selectRow = $("#accTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('添加失败', "请至少选择一条数据后关闭本窗口", "error");
    } else {
        var acc = selectRow[0];
        console.log(acc);
        lCount = acc.accIdle;
        $("#addForm").fill(acc);
        $("#aLastCount").val(acc.accIdle);
        $("#accWin").modal("hide");
        autoEditCalculationCount("aSaleCount", "accSalePrice", "accSaleDiscount", "accSaleTotal", "accSaleMoney", "aLastCount", acc.accTotal, acc.accIdle);
        enableInput();
    }
}

function addUser() {
    var selectRow = $("#userTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('添加失败', "请至少选择一条数据后关闭本窗口", "error");
    } else {
        var user = selectRow[0];
        userId = user.userId;
        $("#addForm").fill(user);
        enableSwitch("userWin", "isUser");
        $("#userWin").modal("hide");
    }
}

function isReAdd(id) {
    $.get('/accessoriesSale/isReAdd?userId=' + id, function (data) {
        if (data.result == 'success') {
            return true;
        } else {
            return false;
        }
    })
}

function fmtCheckState(value) {
    if (value == 'Y') {
        return "已审核";
    } else {
        return "审核中";
    }
}

function fmtSaleState(value) {
    if (value == 'Y') {
        return "可用";
    } else {
        return "不可用";
    }
}

function fmtDiscount(value) {
    if (value != 0) {
        return value;
    } else {
        return "无折扣";
    }
}

function delteleSale() {
    var selectRows = $("#cusTable").bootstrapTable('getSelections');
    var accSaleIdArr = [];
    for (var i = 0; i < selectRows.length; i++) {
        accSaleIdArr[i] = selectRows[i].accSaleId;
    }
    if (selectRows.length > 0) {
        $.each(selectRows, function (index, value, array) {
            if (value.accSaleCheck == "Y") {
                $.get("/accessoriesSale/batchDelete?accSaleArr=" + accSaleIdArr, function (data) {
                    if (data.result == "success") {
                        swal(data.result, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    } else if (data.result == "fail") {
                        swal(data.result, "", "error");
                    }
                });
            } else if (value.accSaleCheck == "N") {
                swal('删除失败', "必须选择审核通过的数据", "error");
            }
        });

    } else {
        swal('删除失败', "请至少选择一条数据删除", "error");
    }
}

function onlySale() {
    initTable("saleTable", "/accessoriesSale/onlySale");
}

function allSales() {
    initTable("saleTable", "/accessoriesSale/pager");
}

function byAccNameSearch() {
    var accName = $("#sAccName").val();
    var saleTimeStart = $("#SaleTimeStart").val();
    var saleTimeEnd = $("#SaleTimeEnd").val();
    var uName = $("#usrName").val();
    initTable("saleTable", "/accessoriesSale/search?accName=" + accName + "&SaleTimeStart=" + saleTimeStart + '&userName=' + uName + "&SaleTimeEnd=" + saleTimeEnd);
}

function getUserName() {
    var ne = "";
    $("#userName").bind('input', function (name, value) {
        ne = this.value;
    })
    return ne;
}

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
            'accessories.accName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 15,
                        message: '不能超过15个字符'
                    }
                }
            },
            userName: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 15,
                        message: '不能超过15个字符'
                    },
                    threshold: 2,
                    // remote: {
                    //     url: '/accessoriesSale/isReAdd?userId=' + userId,
                    //     type: 'get',
                    //     delay: 2000
                    // },
                }
            },
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^(13[0-9]|15[0-35-9]|18[0-9]|17[06-8]|14[57])\d{8}$/,
                        message: '请输入正确的手机号'
                    },
                    stringLength: {
                        min: 0,
                        max: 12,
                        message: '字数不可以超过12个字符'
                    }
                }

            },
            accUnit: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 3,
                        message: '字数不可以超过3个字符'
                    },
                }
            }
            ,
            'accessories.accessoriesType.accTypeName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                    ,
                    stringLength: {
                        min: 0,
                        max: 8,
                        message: '字数不可以超过8个字符'
                    }
                }
                ,

            }
            ,
            accSaleCount: {
                validators: {
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: msg
                    }
                }
            }
            ,
            accSalePrice: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    regexp: {
                        regexp: /^([1-9][0-9]*)+(.[0-9]{1,2})?$/,
                        message: '只接受小数点后两位'
                    }
                }
            }
            ,
            accSaleDiscount: {
                validators: {
                    regexp: {
                        regexp: /^\d+(\.\d+)?$/,
                        message: '折扣只能是数字'
                    }
                }
            }
            ,
            'accessories.company.companyName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                    ,
                }
                ,
                stringLength: {
                    min: 0,
                    max: 8,
                    message: '字数不可以超过8个字符'
                }
            }
            ,
            accSaledTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                addAccSaleInfo("#addForm")
            } else if (formId == "editForm") {
                updateAccSaleInfo("#editForm");
            }
        })
}

function clearTempData() {
    $('#aAccName').html('').trigger("change");
    $('#aAccUnit').html('').trigger("change");
    $('#accSaleCount').html('').trigger("change");
    $('#accSalePrice').html('').trigger("change");
    $('#accSaleDiscount').html('').trigger("change");
    $('#accSaleTime').html('').trigger("change");
    $('#accSaleTotal').html('').trigger("change");
    $('#accSaleMoney').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}

function showAccAddWin() {
    initDateTimePicker("accSaleTime", "accSaledTime", "addForm");
    disableAddFormInput();
    $("#aLastCount").attr("placeholder", "无法读取库存数量");
    // clearTempData();
    validator("addForm");
    $("#addWin").modal("show");
}

function showAccEditWin() {
    initDateTimePicker("accSaleTime", "accSaledTime", "editForm");
    initDateTimePickerNotValitor("form_datetime");
    // clearTempData();
    validator("editForm");
    $("#editWin").modal("show");
}

/**
 *
 * @param saleCount 销售数量
 * @param salePrice 销售价格
 * @param saleDiscount 销售折扣
 * @param saleTotal 销售总价
 * @param saleMoney 销售最终价
 * @param dLastCount 显示库存数量
 * @param alCount 库存总数量
 */
function autoEditCalculationCount(saleCount, salePrice, saleDiscount, saleTotal, saleMoney, dLastCount, alCount, accIdle) {
    var aBuyPrice = $("#accBuyPrice").val();
    var aCount = alCount;
    var sCount = "";
    var sPrice = ""
    var sDiscount = "";
    var rs = "";
    var urs = "";
    var acCount = "";
    $("#" + saleCount + ",#" + salePrice + ",#" + saleDiscount).bind("input onfocus", function () {
        acCount = $("#aLastCount").val();
        sCount = $("#" + saleCount).val();
        sPrice = $("#" + salePrice).val();
        sDiscount = $("#" + saleDiscount).val();

        if (sCount != null && sCount != "") {
            acCount = accIdle - sCount;
            $("#" + dLastCount).val(acCount);

            if (sPrice != null && sPrice != '' && sDiscount != null && sDiscount != '') {
                urs = sCount * sPrice;
                rs = (sCount * sPrice) * sDiscount;
                $("#" + saleMoney).val(rs);
                $("#" + saleTotal).val(urs);
            } else {
                urs = sCount * sPrice;
                $("#" + saleMoney).val(urs);
                $("#" + saleTotal).val(urs);
            }
            if (sCount < 0 || sCount > accIdle) {
                $("#" + dLastCount).val("超过库存数量");
                $("#addButton").attr("disabled", "disabled");
            } else $("#addButton").removeAttr("disabled");
            if ("#" + dLastCount) {

            }
        } else {
            $("#" + dLastCount).val(accIdle);
        }
    })
}


function autoEditCalculationCount1(saleCount, salePrice, saleDiscount, saleTotal, saleMoney, dLastCount, alCount, accIdle, aSaleCount) {
    var aBuyPrice = $("#accBuyPrice").val();
    var tAcc = aSaleCount + accIdle;
    var aCount = alCount;
    var sCount = "";
    var sPrice = ""
    var sDiscount = "";
    var rs = "";
    var urs = "";
    var acCount = "";
    $("#" + saleCount + ",#" + salePrice + ",#" + saleDiscount).bind("input onfocus", function () {
        acCount = $("#aLastCount").val();
        sCount = $("#" + saleCount).val();
        sPrice = $("#" + salePrice).val();
        sDiscount = $("#" + saleDiscount).val();
        if (sCount != null && sCount != "") {
            acCount = tAcc - sCount;
            $("#" + dLastCount).val(acCount);

            if (sPrice != null && sPrice != '' && sDiscount != null && sDiscount != '') {
                urs = sCount * sPrice;
                rs = (sCount * sPrice) * sDiscount;
                $("#" + saleMoney).val(rs);
                $("#" + saleTotal).val(urs);
            } else {
                urs = sCount * sPrice;
                $("#" + saleMoney).val(urs);
                $("#" + saleTotal).val(urs);
            }
            if (sCount < 0 || sCount > accIdle) {
                $("#" + dLastCount).val("超过库存数量");
                $("#editButton").attr("disabled", "disabled");
            } else $("#editButton").removeAttr("disabled");

            if ("#" + dLastCount) {

            }
        } else {
            $("#" + dLastCount).val(tAcc);
            $("#" + saleTotal).val(0);
            $("#" + saleMoney).val(0);

        }
    })
}

function showUserWin() {
    initTableNotTollbar("userTable", "/customer/customerInfo_pager");
    $("#userWin").modal('show');

}
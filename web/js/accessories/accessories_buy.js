var isAcc = false;
var count = 0;
var accName = "";
var isSelect = false;

$(document).ready(function () {
    initTable("cusTable", "/accessoriesBuy/pager");

    initSelect2("supply", "请选择供应商", "/supply/queryAll", 568);
    initSelect2("accTypeA", "请选择配件类别", "/accessoriesType/accessoriesType_All", 568);

    initBsSwitchBuy("isAcc", switchChange);

    disableSwitch("accWin", "isAcc");

    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");

});

function initBsSwitchBuy(id, onSwitchChange) {
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
    console.log(state);
    if (state != false) {
        disableInput();
        showAccessories();
    } else {
        enableInput();
    }
}

function disableInput() {
    $("input[name='accessories.accName']").prop("disabled", true);
    $("input[name='accUnit']").prop("disabled", true);
    $("input[name='accessories.accessoriesType.accTypeName']").prop("disabled", true);
    $("input[name='accessories.accCommodityCode']").prop("disabled", true);
}

function enableInput() {
    $("input[name='accessories.accName']").prop("disabled", false);
    $("input[name='accUnit']").prop("disabled", false);
    $("input[name='accessories.accessoriesType.accTypeName']").prop("disabled", false);
    $("input[name='accessories.accCommodityCode']").prop("disabled", false);
}


/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('ions');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var accessoriesBuy = selectRow[0];
        $("#editForm").fill(accessoriesBuy);
        $("#buyTime").val(formatterDate(accessoriesBuy.accBuyTime));
        $("#editWin").modal('show');
    }
}

function updateAccBuyInfo(formId) {
    var discount = $("#eAccBuyDiscount").val();
    if (discount != '' && discount != null) {
        updateAccessoriesBuyInfo(formId);
    } else {
        $("#eAccBuyDiscount").val(0);
        updateAccessoriesBuyInfo(formId);
    }
}

/**更新数据 */
function updateAccessoriesBuyInfo(formId) {
    $.post("/accessoriesBuy/update",
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $('#editWin').modal('hide');
                swal(data.message, "", "success");
                $('#cusTable').bootstrapTable('refresh');
                allBuys();
                $("#" + formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        }, "json");
}

function addAccessoriesBuyInfo(formId) {
    var discount = $("#accBuyDiscount").val();
    if (discount != '' && discount != null) {
        addAccBuyInfo(formId);
    } else {
        $("#accBuyDiscount").val(0);
        addAccBuyInfo(formId);
    }
}

function passChecks() {
    var ids = [];
    var rows = $("#cusTable").bootstrapTable('getSelections');
    if (rows.length <= 0) {
        swal('选择失败', "请选择一条或一条以上的数据", "error");
        return false;
    } else {
        var datas = rows;
        for (var i = 0; i < datas.length; i++) {
            if (datas[i].accBuyCheck != 'N') {
                swal("操作失败", "此采购已通过审核无需再次通过", "error");
                return false;
            } else continue;
        }
        swal({
            title: "您确定要通过审核吗？",
            text: '确定了将不可更改',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: true,
            cancelButtonText: "取消",
            confirmButtonText: "是的，我通过",
            confirmButtonColor: "#ec6c62"
        }, function () {
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].accBuyId);
            }
            $.get('/accessoriesBuy/passChecks?ids=' + ids, function (data) {
                if (data.result == "success") {
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            })
        })
    }
}

function addAccBuyInfo(formId) {
    $.post("/accessoriesBuy/isAccAdd?state=" + isAcc,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                // disableSwitch("addWin","isAcc");
                swal(data.message, "", "success");
                clearTempData();
                $('#cusTable').bootstrapTable('refresh');
                $("input[type=reset]").trigger("click");
            } else if (data.result == "fail") {
                enableSwitch("addWin", "isAcc");
                destoryValidator(formId, "addForm");
                validator(formId);
                swal(data.message, "", "error");
            }
        }, "json");
}

function fmtOperate(value, row, index) {
    if (row.accBuyStatus == 'Y') {
        return ['<button type="button" class="removeBuy btn btn-danger  btn-sm" style="margin-right:15px;">冻结</button>',
            '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('')
    } else {
        return ['<button type="button" class="enableBuy btn btn-success  btn-sm" style="margin-right:15px;">激活</button>',
            '<button type="button" class="showEditWin btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('')
    }
}

function fmtIsFinish(value, row, index) {
    if (row.accIsBuy == 'N') {
        return ['<button type="button" class="btn btn-warning isFinish btn-sm">完成此采购</button>'
        ].join('');
    } else {
        return ['<button type="button" class="btn btn-success btn-sm" disabled>已完成</button>'
        ].join('');
    }
}


function fmtPassCheck(value, row, index) {
    if (row.accBuyCheck != 'Y') {
        return ['<button type="button" class="passCheck btn btn-warning btn-sm">通过此审核</button>'
        ].join('');
    } else {
        return ['<button type="button" class="btn btn-success btn-sm" disabled>已通过</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .removeBuy': function (e, value, row, index) {
        $.get("/accessoriesBuy/remove?id=" + row.accBuyId, function (data) {
            if (data.result == "success") {
                $('#addWin').modal('hide');
                $('#cusTable').bootstrapTable('refresh');
            } else if (data.result == "fail") {
                swal(data.message, "", "error");
            }
        });
    },
    'click .showEditWin': function (e, value, row, index) {
        var accessoriesBuy = row;
        $("#editForm").fill(accessoriesBuy);
        $("#eAccBuyDiscount").val(reSetDiscount(accessoriesBuy.accBuyDiscount));
        $("#eAccDes").val(accessoriesBuy.accessories.accDes);
        $('#supplyType').html('<option value="' + accessoriesBuy.accessories.supply.supplyId + '">' + accessoriesBuy.accessories.supply.supplyName + '</option>').trigger("change");
        $('#eAccType').html('<option value="' + accessoriesBuy.accessories.accessoriesType.accTypeId + '">' + accessoriesBuy.accessories.accessoriesType.accTypeName + '</option>').trigger("change");
        $("#buyTime").val(formatterDate(accessoriesBuy.accBuyTime));
        showAccEditWin();
        autoCalculation1("eAccBuyCount", "eAccBuyPrice", "eAccBuyDiscount", "eAccBuyTotal", "eAccBuyMoney");
    },
    'click .enableBuy': function (e, value, row, index) {
        $.get("/accessoriesBuy/enable?id=" + row.accBuyId,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }
            });
    },
    'click .isFinish': function (e, value, row, index) {
        if (row.accBuyCheck != 'Y') {
            swal("操作失败", "审核未通过!", "error");
        } else {
            swal({
                title: "您确定完成了该采购吗？",
                text: '确定了将不可更改',
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: true,
                cancelButtonText: "取消",
                confirmButtonText: "是的，我完成了",
                confirmButtonColor: "#ec6c62"
            }, function () {
                $.get("/accessoriesBuy/finish?id=" + row.accBuyId + "&accId=" + row.accessories.accId,
                    function (data) {
                        if (data.result == "success") {
                            $('#cusTable').bootstrapTable('refresh');
                        } else if (data.result == "fail") {
                            swal(data.message, "", "error");
                        }
                    });
            })
        }
    },

    'click .passCheck': function (e, value, row, index) {
        swal({
            title: "您确定通过此审核吗？",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: true,
            cancelButtonText: "取消",
            confirmButtonText: "是的，通过",
            confirmButtonColor: "#ec6c62"
        }, function () {
            $.get("/accessoriesBuy/isPassCheck?accBuyId=" + row.accBuyId + "&status=" + row.accBuyCheck,
                function (data) {
                    if (data.result == "success") {
                        $('#cusTable').bootstrapTable('refresh');
                    } else if (data.result == "fail") {
                        swal(data.message, "", "error");
                    }
                });
        })
    }
}

function fmtAccOperate(value, row, index) {
    return [
        '<button type="button" class="slData btn btn-primary  btn-sm" style="margin-right:15px;" >选择</button>'
    ].join('');
}

function afterEdit(buyCount, buyPrice, buyTotal, buyMoney) {
    var rs = '';
    var urs = '';
    urs = buyCount * buyPrice;
    $("#" + buyTotal).val(urs);
    $("#" + buyMoney).val(urs);
}

window.operateAccEvents = {
    'click .slData': function (e, value, row, index) {
        var acc = row;
        console.log(acc);
        $("#accBuyTime").val(formatterDate(acc.accUsedTime));
        $('#supply').html('<option value="' + acc.supply.supplyId + '">' + acc.supply.supplyName + '</option>').trigger("change");
        $('#accType').html('<option value="' + acc.accessoriesType.accTypeId + '">' + acc.accessoriesType.accTypeName + '</option>').trigger("change");
        $("#addForm").fill(acc);
        enableSwitch("accWin", "isAcc");
        $("#accWin").modal("hide");
        afterEdit(acc.accIdle, acc.accPrice, "accBuyTotal", "accBuyMoney");
    }
}

function showAccessories() {
    initTableNotTollbar("accTable", "/accessories/pager");
    $("#accWin").modal("show");
}


function addAccBuy() {
    var selectRow = $("#accTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        disableSwitch("accWin", "isAcc");
        swal('添加失败', "请至少选择一条数据后关闭本窗口", "error");
    } else {
        var acc = selectRow[0];
        $("#accDes").val(acc.accDes);
        $("#accBuyTime").val(formatterDate(acc.accUsedTime));
        $("#addForm").fill(acc);
        isSelect = true;

        $('#supply').html('<option value="' + acc.supply.supplyId + '">' + acc.supply.supplyName + '</option>').trigger("change");
        $('#accType').html('<option value="' + acc.accessoriesType.accTypeId + '">' + acc.accessoriesType.accTypeName + '</option>').trigger("change");

        enableSwitch("accWin", "isAcc");
        $("#accWin").modal("hide");

        accAddAutoCalculation(acc.accDiscount, acc.accIdle, acc.accPrice, "accBuyTotal", "accBuyMoney");
        autoCalculation1("accBuyCount", "accBuyPrice", "accBuyDiscount", "accBuyTotal", "accBuyMoney");
    }
    // disableSwitch("addWin", "isAcc");
}

function accAddAutoCalculation(accDiscount, accIdle, accPrice, accBuyTotal, accBuyMoney) {
    var rs = "";
    var udrs = "";
    if (accDiscount < 1) {
        udrs = accPrice * accIdle;
        rs = (accPrice * accIdle) * accDiscount;
        $("#" + accBuyTotal).val(udrs);
        $("#" + accBuyMoney).val(rs);
    } else {
        udrs = accPrice * accIdle;
        $("#" + accBuyTotal).val(udrs);
        $("#" + accBuyMoney).val(udrs);
    }
}

function fmtCheckState(value) {
    if (value != 'N') {
        return "已审核";
    } else {
        return "审核中";
    }
}

function fmtDiscount(value) {
    if (value != 0) {
        return value;
    } else {
        return "无折扣"
    }
}

function fmtBuyState(value) {
    if (value == 'Y') {
        return "已采购";
    } else {

        return "未采购";
    }
}

/**
 * 批量冻结状态
 */
function delteleBuy() {
    var selectRows = $("#cusTable").bootstrapTable('getSelections');
    var accBuyIdArr = [];
    for (var i = 0; i < selectRows.length; i++) {
        accBuyIdArr[i] = selectRows[i].accBuyId;
    }
    if (selectRows.length > 0) {
        $.each(selectRows, function (index, value, array) {
            $.get("/accessoriesBuy/batchDelete?accBuyArr=" + accBuyIdArr,
                function (data) {
                    if (data.result == "success") {
                        swal(data.result, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                    } else if (data.result == "fail") {
                        swal(data.result, "", "error");
                    }
                });
        });
    } else {
        swal('操作失败', "请至少选择一条数据冻结", "error");
    }
}

function onlyCheck() {
    initTable("cusTable", "/accessoriesBuy/onlyCheck");
}

function onlyStatus() {
    initTable("cusTable", "/accessoriesBuy/onlyStatus");
}

function onlyBuy() {
    initTable("cusTable", "/accessoriesBuy/onlyBuy");
}

function allBuys() {
    initTable("cusTable", "/accessoriesBuy/pager");
}

function byAccNameSearch() {
    initDateTimePicker("form_datetime", "buyTimeStart", "formSearch");
    initDateTimePicker("form_datetime", "buyTimeEnd", "formSearch");
    var accName = $("#sAccName").val();
    var buyTimeStart = $("#buyTimeStart").val();
    var buyTimeEnd = $("#buyTimeEnd").val();
    initTable("cusTable", "/accessoriesBuy/byAccNameSearch?accName=" + accName + "&buyTimeStart=" + buyTimeStart + "&buyTimeEnd=" + buyTimeEnd);
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

            'accessories.accDes': {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '不可以超过500个字符'
                    },
                }
            },

            'accessories.accTypeId': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                }
            },

            'accessories.accCommodityCode': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                    stringLength: {
                        min: 0,
                        max: 13,
                        message: '不可以超过13个字符'
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    }
                }
            },

            'accessories.supplyId': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },

            accDes: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '字数不可以超过500个字符'
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
            },
            'accessories.accessoriesType.accTypeName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                },

            },
            accBuyCount: {
                validators: {
                    notEmpty: {
                        message: '不可以为空',
                    },
                    regexp: {
                        regexp: /^[0-9]+$/,
                        message: '只能是数字'
                    }
                }
            },
            accBuyPrice: {
                validators: {
                    notEmpty: {
                        message: '不可以为空'
                    },
                    regexp: {
                        regexp: /^([1-9][0-9]*)+(.[0-9]{1,2})?$/,
                        message: '只接受小数点后两位'
                    }
                }
            },
            accBuyDiscount: {
                validators: {
                    regexp: {
                        regexp: /^([0-9](\.[0-9]+)?|10)$/,
                        message: '请输入正确的折扣'
                    }
                }
            },
            'accessories.company.companyName': {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    },
                },
                stringLength: {
                    min: 0,
                    max: 8,
                    message: '字数不可以超过8个字符'
                }
            },
            accBuyTime: {
                validators: {
                    notEmpty: {
                        message: '不能为空'
                    }
                }
            },
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                addAccessoriesBuyInfo(formId);
            } else if (formId == "editForm") {
                updateAccBuyInfo(formId);
            }
        })
}

function clearTempData() {
    enableInput();
    $('#aAccName').html('').trigger("change");
    $('#aAccUnit').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#accBuyCount').html('').trigger("change");
    $('#accBuyPrice').html('').trigger("change");
    $('#accBuyDiscount').html('').trigger("change");
    $('#aCompanyName').html('').trigger("change");
    $('#accBuyTime').html('').trigger("change");
    $('#accBuyTotal').html('').trigger("change");
    $('#accBuyMoney').html('').trigger("change");
    $('#supply').html('').trigger("change");
    $('#accType').html('').trigger("change");

    $("input[type=reset]").trigger("click");
}

function showAccAddWin() {
    checkName("accName");
    showAccessoriesAddWin(isSelect);
}

function showAccessoriesAddWin() {
    $("#dck").css("display", "none");
    initDateTimePicker("form_datetime", "accBuyTime", "addForm");
    // clearTempData();
    validator("addForm");
    $("#addWin").modal('show');
    // dataCheck("accName", "addForm");
    autoCalculation1("accBuyCount", "accBuyPrice", "accBuyDiscount", "accBuyTotal", "accBuyMoney");
}

function checkName(nameId) {
    $("#" + nameId).bind('onfocus input', function () {
        $.get('/accessoriesBuy/checkData?name=' + this.value, function (data) {
            if (data.result == "success") {
                $("#dck").css("display", "none");
                $("#addButton").removeAttr("disabled");
            } else if (data.result == "fail") {
                $("#dck").val(1);
                $("#dck").text("此配件已存在");
                $("#dck").css("display", "block");
                // $("#" + nameId).css("border-color", "#a94442");
                // $("#aName").css("color", "#a94442");
                // $("#accName").next().removeClass("glyphicon-ok").addClass("glyphicon-remove").css("color", "#a94442");
                $("#addButton").attr("disabled", "disabled");
            }
        })
    })
}

function showAccEditWin() {
    initDateTimePicker("form_datetime", "accBuyTime", "editForm");
    // clearTempData();
    validator("editForm");
    $("#editWin").modal('show');
}

/**
 *
 * @param buyCount 购买数量
 * @param buyPrice 购买单价
 * @param buyDiscount 购买折扣
 * @param buyTotal 购买总价
 * @param buyMoney 购买最终价
 */
function autoCalculation1(buyCount, buyPrice, buyDiscount, buyTotal, buyMoney) {
    var bCount = "";
    var bPrice = "";
    var bDiscount = "";
    var bTotal = "";
    var bMoney = "";
    var udrs = ""; //未打折扣总价
    var rs = ""; // 打完折扣总价
    $("#" + buyCount + ",#" + buyPrice + ",#" + buyDiscount).bind("input", function () {
        bCount = $("#" + buyCount).val();
        bPrice = $("#" + buyPrice).val();
        bDiscount = $("#" + buyDiscount).val();
        if (bCount != null && bCount != "") {
            if (bPrice != null && bPrice != "" && bDiscount != null && bDiscount != "") {
                rs = (bCount * bPrice) * bDiscount;
                var urs = bCount * bPrice;
                $("#" + buyTotal).val(urs);
                $("#" + buyMoney).val(rs);
            } else {
                udrs = bCount * bPrice;
                $("#" + buyTotal).val(udrs);
                $("#" + buyMoney).val(udrs);
            }
        } else {
            $("#" + buyTotal).val(0);
            $("#" + buyMoney).val(0);
        }
    })
}
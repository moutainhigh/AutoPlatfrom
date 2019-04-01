/**
 * Created by Administrator on 2017-04-17.
 */
var appointment;
var userInfo;

var editPhone = "";

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/checkin/checkin_pager?status=ALL");

    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "540");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "540");

    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "540");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");

    $("#isApp").bootstrapSwitch({
        onText: '是',
        offText: '否',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',

        onSwitchChange: function (event, state) {
            if (state == true) {
                //调用函数，初始化表格
                initTableNotTollbar("appTable", "/appointment/query_pager?status=Y");

                $("#appWin").modal('show');
            } else if (state == false) {
            }
        }
    });

    $("#choiceUser").bootstrapSwitch({
        onText: '是',
        offText: '否',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',

        onSwitchChange: function (event, state) {
            if (state == true) {
                //调用函数，初始化表格
                initTableNotTollbar("userTable", "/customer/customerInfo_pager");

                $("#userWin").modal('show');
            } else if (state == false) {
            }
        }
    });


});

/** 监听是否的预约监听事件 */
function isAppChoice() {
    if ($('#isApp').bootstrapSwitch('state')) {
        if (appointment != null && appointment != "" && appointment != undefined) {
            setData(appointment, "appointment");
        }
    } else {
        if (appointment != null && appointment != "" && appointment != undefined) {
            clearAddForm();
        }

    }
}

/** 监听是否从系统中选择监听事件 */
function isUserChoice() {
    if ($('#choiceUser').bootstrapSwitch('state')) {
        if (userInfo != null && userInfo != "" && userInfo != undefined) {
            setData(userInfo, "userInfo");
        }
    } else {
        if (userInfo != null && userInfo != "" && userInfo != undefined) {
            clearAddForm();
        }

    }
}

/** 添加选择品牌 */
function checkBrand(combo) {
    $('#addCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {
        $("#carModelDiv").show();
        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "540");
    } else {
        $("#carModelDiv").hide();
    }
}

/** 修改窗口选择车型 */
function editCheckBrand(combo) {
    $('#editCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {

        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "540");
    } else {

    }
}
/** 是否需要洗车 */
function carWash(value, row, index) {
    if (value == "Y") {
        return "是";
    } else {
        return "否";
    }
}

/** 显示添加数据的窗口 */
function showAddWin() {

    appointment = "";
    validator("addForm");
    initDateTimePicker("datetimepicker", "arriveTime", "addForm");
    $("#addWin").modal('show');
}

/** 给datetimepicker添加默认值 */
function getDate() {
    if (appointment != null && appointment != "" && appointment != undefined) {

    } else {
        $("#addDatetimepicker").val(new Date());
    }
}

/** 关闭预约 */
function closeAppWin() {
    $('#isApp').bootstrapSwitch('state', false);
    $("#appWin").modal('hide');
}

/** 关闭选择车主信息 */
function closeUserWin() {
    $('#choiceUser').bootstrapSwitch('state', false);
    $("#userWin").modal('hide');
}

/** 选择预约记录 */
function checkApp() {
    var selectRow = $("#appTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        appointment = selectRow[0];
        setData(appointment, "appointment");
        $('#isApp').bootstrapSwitch('state', true);
        $("#appWin").modal('hide');
    }
}

/** 选择车主信息 */
function choiceUser() {
    var selectRow = $("#userTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一个车主信息", "error");
        return false;
    } else {
        var user = selectRow[0];
        if (user.userName != null && user.userName != "" && user.userPhone != null && user.userPhone != "") {
            userInfo = user;
            setData(userInfo, "userInfo");
            $('#choiceUser').bootstrapSwitch('state', true);
            $("#userWin").modal('hide');
        } else {
            swal('选择失败', "请选完善该车主的信息,至少要填写车主姓名和手机号", "error");
            return false;
        }
    }
}

/** 给添加的form表单设置值 */
function setData(appointment, flag) {

    if (flag == "appointment") {
        $("#userDiv").hide();
        $("#addUserName").val(appointment.userName);
        $("#addUserPhone").val(appointment.userPhone);
        $("#addUserId").val(appointment.userId);
        $("#addCarPlateNumber").val(appointment.carPlate);
        $("#addAppointmentId").val(appointment.appointmentId);
        $("#addDatetimepicker").val(formatterDate(appointment.arriveTime));
        $('#addCarBrand').html('<option value="' + appointment.brand.brandId + '">' + appointment.brand.brandName + '</option>').trigger("change");
        $('#addCarColor').html('<option value="' + appointment.color.colorId + '">' + appointment.color.colorName + '</option>').trigger("change");
        $('#addCarModel').html('<option value="' + appointment.model.modelId + '">' + appointment.model.modelName + '</option>').trigger("change");
        $('#addCarPlate').html('<option value="' + appointment.plate.plateId + '">' + appointment.plate.plateName + '</option>').trigger("change");
        $("#addMaintainOrFix").val(appointment.maintainOrFix);

        $("#addCarPlateNumber").attr("readonly","readonly");
    } else if (flag == "userInfo") {
        $("#appDiv").hide();
        $("#addUserName").val(appointment.userName);
        $("#addUserPhone").val(appointment.userPhone);
        $("#addUserId").val(appointment.userId);
    }
    $("#addUserName").attr("readonly","readonly");
    $("#addUserPhone").attr("readonly","readonly");
}

/** 清除添加的form表单信息 */
function clearAddForm() {
    editPhone = "";
    $("#appDiv").show();
    $("#userDiv").show();
    $('#addCarBrand').html('').trigger("change");
    $('#addCarColor').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#addCarPlate').html('').trigger("change");
    $("#addAppointmentId").val('');
    $("#addUserId").val('');
    $("input[type=reset]").trigger("click");

    $("#addUserName").removeAttr("readonly");
    $("#addUserPhone").removeAttr("readonly");
    $("#addCarPlateNumber").removeAttr("readonly");
}

/** 编辑数据 */
function showEditWin() {
    validator("editForm");
    initDateTimePicker("datetimepicker", "arriveTime", "editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {

        var checkin = selectRow[0];
        editPhone = checkin.userPhone;
        $("#editForm").fill(checkin);
        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + checkin.brandId, "540");
        $('#editCarBrand').html('<option value="' + checkin.brand.brandId + '">' + checkin.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + checkin.color.colorId + '">' + checkin.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + checkin.model.modelId + '">' + checkin.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + checkin.plate.plateId + '">' + checkin.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(checkin.arriveTime));
        $("#editWin").modal('show');
    }
}

/** 返回按钮 */
function operateFormatter(value, row, index) {
    if (row.checkinStatus == 'Y') {
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

/** 格式化预约信息操作栏 */
function formatterChoiceApp(value, row, index) {
    return [
        '<button type="button" class="choiceApp btn btn-primary btn-sm" style="margin-left:15px;" >选择</button>'
    ].join('');
}

/** 格式化车主信息操作栏 */
function formatterChoiceUser(value, row, index) {
    return [
        '<button type="button" class="choiceUser btn btn-primary btn-sm" style="margin-left:15px;" >选择</button>'
    ].join('');
}

/** 更改状态 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/checkin/update_status?checkinId=" + row.checkinId + "&status=" + row.checkinStatus,
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
        $.get("/checkin/update_status?checkinId=" + row.checkinId + "&status=" + row.checkinStatus,
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
        var checkin = row;
        $("#editForm").fill(checkin);
        $('#editCarBrand').html('<option value="' + checkin.brand.brandId + '">' + checkin.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + checkin.color.colorId + '">' + checkin.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + checkin.model.modelId + '">' + checkin.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + checkin.plate.plateId + '">' + checkin.plate.plateName + '</option>').trigger("change");
        $('#editDatetimepicker').val(formatterDate(checkin.arriveTime));
        validator("editForm");
        $("#editWin").modal('show');
    },
    'click .choiceUser': function (e, value, row, index) {
        var user = row;
        if (user.userName != null && user.userName != "" && user.userPhone != null && user.userPhone != "") {
            userInfo = user;
            setData(userInfo, "userInfo");
            $('#choiceUser').bootstrapSwitch('state', true);
            $("#userWin").modal('hide');
        } else {
            swal('选择失败', "请选完善该车主的信息,至少要填写车主姓名和手机号", "error");
            return false;
        }
    },
    'click .choiceApp': function (e, value, row, index) {
        appointment = row;
        setData(appointment, "appointment");
        $('#isApp').bootstrapSwitch('state', true);
        $("#appWin").modal('hide');
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
            userName: {
                message: '车主验证失败',
                validators: {
                    notEmpty: {
                        message: '车主姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '车主长度必须在2到4位之间'
                    }
                }
            },
            userPhone: {
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号只能是11位'
                    }
                }
            },
            brandId: {
                validators: {
                    notEmpty: {
                        message: '品牌不能为空'
                    }

                }
            },
            colorId: {
                validators: {
                    notEmpty: {
                        message: '颜色不能为空'
                    }

                }
            },
            modelId: {
                validators: {
                    notEmpty: {
                        message: '车型不能为空'
                    }

                }
            },
            plateId: {
                validators: {
                    notEmpty: {
                        message: '车牌不能为空'
                    }

                }
            },
            carPlate: {
                validators: {
                    notEmpty: {
                        message: '车牌号码不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码只能是5位'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: '车牌不能是中文'
                    }
                }
            },
            oilCount: {
                validators: {
                    notEmpty: {
                        message: '汽车油量不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 3,
                        message: '汽车油量不能超过3位数'
                    },
                    regexp: {
                        regexp: /^[\.0-9]+$/,
                        message: '油量只能是数字'
                    }

                }
            },
            carMileage: {
                validators: {
                    notEmpty: {
                        message: '汽车行驶里程不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '汽车行驶里程不能超过6位数'
                    },
                    regexp: {
                        regexp: /^[\.0-9]+$/,
                        message: '行驶里程只能是数字'
                    }

                }
            },
            arriveTime: {
                validators: {
                    notEmpty: {
                        message: '到店时间不能为空'
                    }

                }
            },
            carThings: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            },
            intactDegrees: {
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '描述不能超过500个字'
                    }

                }
            },
            userRequests: {
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
            if (formId == "addForm") {
                var isApp = $('#isApp').bootstrapSwitch('state');
                $.post("/checkin/add?isApp=" + isApp,
                    $("#" + formId).serialize(),
                    function (data) {
                        if (data.result == "success") {
                            $('#addWin').modal('hide');
                            $('#cusTable').bootstrapTable('refresh');
                            $('#' + formId).data('bootstrapValidator').resetForm(true);
                            swal({
                                    title: "添加成功",
                                    text: data.message + ",是否添加维修保养明细？",
                                    type: "success",
                                    showCancelButton: true,
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "是",
                                    cancelButtonText: "否",
                                    closeOnConfirm: true,
                                    closeOnCancel: true
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        showMaintain();
                                    } else {

                                    }
                                });
                        } else if (data.result == "fail") {
                            swal("错误提示", data.message, "error");
                        } else if (data.result == "notLogin") {
                            swal({
                                    title: "登入失败",
                                    text: data.message,
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "确认",
                                    cancelButtonText: "取消",
                                    closeOnConfirm: true,
                                    closeOnCancel: true
                                },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        top.location.href = "/login/show_login";
                                    } else {
                                    }
                                });
                        }
                    }, "json");
                $('#choiceUser').bootstrapSwitch('state', false);
                $('#isApp').bootstrapSwitch('state', false);
                clearAddForm();
            } else if (formId == "editForm") {
                formSubmit("/checkin/edit", formId, "editWin");
                clearAddForm();
            }


        })
}

/** 根据条件搜索 */
function searchCheckin() {
    var userName = $("#searchUserName").val();
    var userPhone = $("#searchUserPhone").val();
    var carPlate = $("#searchCarPlate").val();
    var maintainOrFix = $("#searchMaintainOrFix").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/checkin/condition_pager?userName=" + userName + "&userPhone=" + userPhone + "&carPlate=" + carPlate + "&maintainOrFix=" + maintainOrFix + "&companyId=" + companyId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchUserName").val('');
    $("#searchUserPhone").val('');
    $("#searchCarPlate").val('');
    $("#searchMaintainOrFix").val('all');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}

/** 子窗口调用父窗口的js方法 */
function showMaintain() {
    parent.showMaintainPage();
}




var userInfo;

var editPhone;
$(document).ready(function () {
    //调用函数，初始化表格
    validator("appointmentForm");
    initSelect2("car_company", "请选择汽修公司", "/company/company_all", "505");
    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "505");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "505");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "505");
    initDateTimePicker("datetimepicker", "arriveTime","appointmentForm");

});

/** 判断是否是手机号 */
function isPhone(str) {
    var reg = /^1[3|4|5|7|8][0-9]{9}$/;
    return reg.test(str);
}

/** 判断是否是姓名 */
function isName(nam) {
    var reg = /^[\u4e00-\u9fa5 ]{2,15}$/;
    return reg.test(nam);
}

/** 判断是否是0-9的5位车牌 */
function isCarNum(num) {
    var reg = /^[0-9]{5}$/;
    return reg.test(num);
}

/** 检查车牌号 */
function checkCarNumber(num) {
    if (isCarNum(num.value)) {
        $("#errorCarNumber").html('');
    } else {
        $("#errorCarNumber").html('请输入5位车牌号');
    }
}

/** 检查手机号 */
function checkPhone(phone) {
    if (isPhone(phone.value)) {
        $("#errorPhone").html('');
    } else {
        $("#errorPhone").html('请输入正确的手机号');
        //$("#getButton").attr("disabled","disabled");
    }
}

/** 检查手机号 */
function checkPhone(phone) {
    if (isPhone(phone.value)) {
        $("#errorPhone").html('');
    } else {
        $("#errorPhone").html('请输入正确的手机号');
        //$("#getButton").attr("disabled","disabled");
    }
}

/** 检查姓名 */
function checkName(name) {
    if (isName(name.value)) {
        $("#errorName").html('');
    } else {
        $("#errorName").html('请输入中文名');
    }
}



/** 添加选择品牌 */
function checkBrand(combo) {
    $('#addCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {
        $("#carModelDiv").show();
        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "505");
    } else {
        $("#carModelDiv").hide();
    }
}

/** 修改窗口选择车型 */
function editCheckBrand(combo) {
    $('#editCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {

        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "505");
    } else {

    }
}


/** 清除添加的form表单信息 */
function clearAddForm() {
    $('#name').html('').trigger("change");
    $('#phone').html('').trigger("change");
    $('#addCompany').html('').trigger("change");
    $('#addCarBrand').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#addCarColor').html('').trigger("change");
    $('#addCarPlate').html('').trigger("change");
    $('#CarNumber').html('').trigger("change");
    $('#timePicker').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}



/** 添加提交按钮 */
function addAppiontment() {
    $("#appointmentForm").data('bootstrapValidator').validate();
    if ($("#appointmentForm").data('bootstrapValidator').isValid()) {
        $("#addBtn").attr("disabled","disabled");
    } else {
        $("#addBtn").removeAttr("disabled");
    }
}

/** 表单验证 */
function validator(formId) {
    $("#addBtn").removeAttr("disabled");
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
                    regexp: {
                        regexp: /^1(3|4|5|7|8)\d{9}$/,
                        message: '手机号格式错误'
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
            company: {
                validators: {
                    notEmpty: {
                        message: '公司不能为空'
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
            companyId: {
                validators: {
                    notEmpty: {
                        message: '公司不能为空'
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
            arriveTime: {
                validators: {
                    notEmpty: {
                        message: '到店时间不能为空'
                    }

                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "appointmentForm") {
                formSubmitAppointment("/customerClientWeb/addCustomer", formId);
                clearAddForm();
            }
        })
}

/** form表单提交 */
function formSubmitAppointment(url, formId) {
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                swal("成功提示", data.message, "success");
                $('#' + formId).data('bootstrapValidator').resetForm(true);
            } else if (data.result == "fail") {
                swal("错误提示", data.message, "error");
                $("#addBtn").removeAttr("disabled");
            }
        }, "json");
}





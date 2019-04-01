var userInfo;

var editPhone;
/** 显示添加数据的窗口 */
$(document).ready(function () {
    $("#addWin").modal('show');
    validator("addForm");
    initDateTimePicker("datetimepicker", "arriveTime", "addForm");
    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "565");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "565");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "565");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "565");
});

/** 添加选择品牌 */
function checkBrand(combo) {
    $('#addCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {
        $("#carModelDiv").show();
        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "565");
    } else {
        $("#carModelDiv").hide();
    }
}

/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
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
            if (formId == "addForm") {
                $.post("/appointment/addCustomer",
                    $("#" + formId).serialize(),
                    function (data) {
                        if (data.result == "success") {
                            $('#addWin').modal('hide');
                            swal("成功提示", data.message, "success");
                            $('#cusTable').bootstrapTable('refresh');
                            $('#' + formId).data('bootstrapValidator').resetForm(true);
                        } else if (data.result == "fail") {
                            swal("错误提示", data.message, "error");
                            $("#addButton").removeAttr("disabled");
                            $("#editButton").removeAttr("disabled");
                            $("#detailButton").removeAttr("disabled");
                            $("#remindButton").removeAttr("disabled");
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
        })
}

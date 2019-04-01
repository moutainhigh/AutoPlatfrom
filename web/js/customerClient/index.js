var userInfo;
var carUserName;
var carUserPhone;
var editPhone;
$(document).ready(function () {
    //调用函数，初始化表格


    initSelect2("car_brand", "请选择品牌", "/carBrand/car_brand_all", "565");
    initSelect2("car_color", "请选择颜色", "/carColor/car_color_all", "565");
    initSelect2("car_plate", "请选择车牌", "/carPlate/car_plate_all", "565");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "150");

    destoryValidator("addWin", "addForm");


});

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
        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "565");
    } else {
        $("#carModelDiv").hide();
    }
}

/** 修改窗口选择车型 */
function editCheckBrand(combo) {
    $('#editCarModel').html('').trigger("change");
    var brandId = combo.value;
    if (brandId != null && brandId != undefined && brandId != "") {

        initSelect2("car_model", "请选择车型", "/carModel/car_model_all?brandId=" + brandId, "565");
    } else {

    }
}


/** 清除添加的form表单信息 */
function clearAddForm() {
    $("#userDiv").show();
    $('#addCarBrand').html('').trigger("change");
    $('#addCarColor').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#addCarPlate').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}


/** 显示添加数据的窗口 */
function showAddWin(companyId, userName, userPhone) {
    userInfo = "";
    if (userName != null && userName != "" && userPhone != null && userPhone != "") {
        validator("addForm");
        initDateTimePicker("datetimepicker", "arriveTime", "addForm");
        $("#addWin").modal('show');
        $("#companyId").val(companyId);
    } else {
        swal({
                title: "预约失败",
                text: "您的个人资料不完整,是否完善个人资料？",
                type: "error",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "是",
                cancelButtonText: "否",
                closeOnConfirm: true,
                closeOnCancel: true
            },
            function (isConfirm) {
                if (isConfirm) {
                    showData();
                } else {
                    window.parent.closeThis();
                }
            });
    }
}


/** 子窗口调用父窗口的js方法 */
function showData() {
    parent.showDataPage();
}

/** 关闭选择 */
function closeAppWin() {

    $("#appWin").modal('hide');
}

/** 给添加的form表单设置值 */
function setData(customer) {
    $("#appDiv").hide();
    $("#addUserName").val(customer.userName);
    $("#addUserPhone").val(customer.userPhone);
    $("#addUserId").val(customer.userId);

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
                formSubmit("/appointment/addCustomer", formId, "addWin");
                clearAddForm();
            }
        })
}


/** 子窗口调用父窗口的js方法 */
function showMaintain() {
    parent.showMaintainPage();
}

var currPage = 1;

function selectPage(obj) {
    $("#companyDiv").html("");
    $.get("queryCompany_pager?index=" + obj.text,
        function (data) {
            var htmlFor = "";
            $.each(data, function (index, item) {
                var companyStr = "'" + item.companyId + "'";
                htmlFor += '<div class="col-lg-4 col-md-4 col-sm-6">';
                htmlFor += '<a onclick="showAddWin(' + companyStr + carUserName + carUserPhone + ');" class="fh5co-project-item image-popup model">';
                htmlFor += '<figure><div class="overlay"><i class="ti-plus"></i></div>';
                htmlFor += '<img src="' + item.companyImg + '" alt="Image" class="img-responsive"></figure>';
                htmlFor += '<div class="fh5co-text"><h2>' + item.companyName + '</h2><span>' + item.companyTel + '</span>';
                htmlFor += '<p>' + item.companyAddress + '</p><button class="btn-success col-lg-5  pull-right">预约</button></div></a></div>';
            });
            $("#companyDiv").html(htmlFor);
        }, "json");
    currPage = parseInt(obj.text);
}

function nextPage(pageSize) {
    var idx = currPage + 1;
    if (currPage == pageSize) {
        idx = currPage;
    } else {
        currPage = currPage + 1;
    }
    $("#companyDiv").html("");
    $.get("queryCompany_pager?index=" + idx,
        function (data) {
            var htmlFor = "";
            $.each(data, function (index, item) {
                var companyStr = "'" + item.companyId + "'";
                htmlFor += '<div class="col-lg-4 col-md-4 col-sm-6">';
                htmlFor += '<a onclick="showAddWin(' + companyStr + carUserName + carUserPhone + ');" class="fh5co-project-item image-popup model">';
                htmlFor += '<figure><div class="overlay"><i class="ti-plus"></i></div>';
                htmlFor += '<img src="' + item.companyImg + '" alt="Image" class="img-responsive"></figure>';
                htmlFor += '<div class="fh5co-text"><h2>' + item.companyName + '</h2><span>' + item.companyTel + '</span>';
                htmlFor += '<p>' + item.companyAddress + '</p><button class="btn-success col-lg-5  pull-right">预约</button></div></a></div>';
            });
            $("#companyDiv").html(htmlFor);
        }, "json");
}

function lastPage() {
    var idx = (currPage - 1);
    if (currPage == 1) {
        idx = currPage;
    } else {
        currPage = currPage - 1;
    }
    $("#companyDiv").html("");
    $.get("queryCompany_pager?index=" + idx,
        function (data) {
            var htmlFor = "";
            $.each(data, function (index, item) {
                var companyStr = "'" + item.companyId + "'";
                htmlFor += '<div class="col-lg-4 col-md-4 col-sm-6">';
                htmlFor += '<a onclick="showAddWin(' + companyStr + carUserName + carUserPhone + ');" class="fh5co-project-item image-popup model">';
                htmlFor += '<figure><div class="overlay"><i class="ti-plus"></i></div>';
                htmlFor += '<img src="' + item.companyImg + '" alt="Image" class="img-responsive"></figure>';
                htmlFor += '<div class="fh5co-text"><h2>' + item.companyName + '</h2><span>' + item.companyTel + '</span>';
                htmlFor += '<p>' + item.companyAddress + '</p><button class="btn-success col-lg-5  pull-right">预约</button></div></a></div>';
            });
            $("#companyDiv").html(htmlFor);
        }, "json");
}

function intNamePhone(userName, userPhone) {
    carUserName = ", '" + userName + "'";
    carUserPhone = ", '" + userPhone + "'";
}
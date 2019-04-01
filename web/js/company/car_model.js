var editModelName = ""
/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var product = selectRow[0];
        $("#editForm").fill(product);
        $('.car_brand').html('<option value="' + product.brand.brandId+ '">' + product.brand.brandName + '</option>').trigger("change");
        editModelName = product.modelName;
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function showAddWin(){
    validator("addForm");
    $('#car_brand').html('').trigger("change");
    $("#addWin").modal('show');
    $("input[type=reset]").trigger("click");
}

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carModel/queryByPager");
    initSelect2("car_brand", "请选择汽车品牌", "/carBrand/car_brand_all", "550");
    initSelect2("brand", "请选择汽车品牌", "/carBrand/car_brand_all", "200");
    destoryValidator("addWin","addForm");
    destoryValidator("editWin","editForm");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});


/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchModelName").val('');
    $("#brandId").html('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function searchModel(){
    var ModelName = $("#searchModelName").val();
    var brandId = $("#brandId").val();
    var brandName=$("#brandName").val();
    initTable("cusTable","/carModel/searchPager?modelName="+ModelName+"&brandId="+brandId+"&brandName"+brandName);
}


function modelAll(){
    initTable("cusTable","/carModel/queryByPager");
}

/**查询可用*/
function statusUsableness(){
    var status = 'Y';
    initTable("cusTable","/carModel/queryByModelPager?status="+status);
}

/**查询不可用*/
function statusAvailable(){
    var status = 'N';
    initTable("cusTable","/carModel/queryByModelPager?status="+status);
}

function operating(value, row, index) {
    if (row.modelStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm btn-primary" >编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default  btn-sm btn-primary" >编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var modelStatus = 'N';
        $.get("/carModel/modelStatusModify?id=" + row.modelId + "&status=" + row.modelStatus,
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
        var modelStatus = 'Y';
        $.get("/carModel/modelStatusModify?id=" + row.modelId + "&status=" + row.modelStatus,
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
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var incomingType = row;
        editModelName = incomingType.modelName;
        validator("editForm");
        $("#editForm").fill(incomingType);
        $('.car_brand').html('<option value="' + incomingType.brand.brandId+ '">' + incomingType.brand.brandName + '</option>').trigger("change");
        $("#editWin").modal('show');
    }
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
            brandName: {
                message: '品牌失败',
                validators: {
                    notEmpty: {
                        message: '汽车品牌不能为空'
                    }
                }
            },
            modelName: {
                message: '车型名称失败',
                validators: {
                    notEmpty: {
                        message: '车型名称不能为空'
                    },threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_ModelName?editModelName=' + editModelName,
                        message: '该车型名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            modelDes: {
                message: '车型描述失败',
                validators: {
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '车型描述用字符长度必须在1~500字之间'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/carModel/insertCarModel", formId, "addWin");
                editModelName = ""
            } else if (formId == "editForm") {
                formSubmit("/carModel/uploadCarModel", formId, "editWin");
                editModelName = ""
            }
        })

}


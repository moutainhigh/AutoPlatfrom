/**
 * Created by Administrator on 2017-05-17.
 */

var editName = "";
var editTel = "";
var editWebsite = "";
var editPhone = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/intention/intention_pager?status=ALL");
});

/** 返回按钮 */
function operateFormatter(value, row, index) {
    if (row.intentionStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger btn-sm" style="margin-right:15px;" >冻结</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-success btn-sm" style="margin-right:15px;" >激活</button>'
        ].join('');
    }
}

/** 更改状态 */
window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        $.get("/intention/update_status?id=" + row.intentionId + "&status=" + row.intentionStatus,
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
        $.get("/intention/update_status?id=" + row.intentionId + "&status=" + row.intentionStatus,
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

/** 根据条件搜索 */
function searchCheckin() {
    var name = $("#searchName").val();
    var phone = $("#searchPhone").val();
    var email = $("#searchEmail").val();
    initTable("cusTable", "/intention/condition_pager?name=" + name + "&phone=" + phone + "&email=" + email);

}

function showAddWin(){
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条记录进行操作", "error");
        return false;
    } else {
        var intention = selectRow[0];
        editName = intention.companyName;
        editTel  = intention.companyTel;
        editWebsite = intention.companyWebsite;
        initDateTimePicker("form_datetime", "companyOpenDate", "addForm");
        validator("addForm");
        $("#addForm").fill(intention);
        $("#addWin").modal('show');
    }
}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchName").val('');
    $("#searchPhone").val('');
    $("#searchEmail").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

//图片上传预览    IE是用了滤镜。
function previewImage(file) {
    var MAXWIDTH = 120;
    var MAXHEIGHT = 60;
    var div = document.getElementById('preview');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead onclick=$("#previewImg").click()>';
        var img = document.getElementById('imghead');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
//                 img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top + 'px';
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
    }
}

function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    return param;
}

/** 选择公司位置 */
function showMap() {
    $("#mapWin").modal("show");
}

/** 确定 */
function determine() {
    var temp = $("#result_").val().split(",");
    $("#addLatitude").val(temp[0]);
    $("#editLatitude").val(temp[0]);
    $("#addLongitude").val(temp[1]);
    $("#editLongitude").val(temp[1]);
    $("#mapWin").modal("hide");
}


function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            companyName: {
                message: '公司名称失败',
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    },
                    stringLength: {
                        min: 4,
                        max: 20,
                        message: '公司名称长度必须在4到20位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_companyName?editName=' + editName,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            companyDes: {
                message: '公司描述失败',
                validators: {
                    stringLength: {
                        min: 0,
                        max: 500,
                        message: '公司描述长度不能操作500位数'
                    }
                }
            },
            companyAddress: {
                message: '公司地址失败',
                validators: {
                    stringLength: {
                        min: 2,
                        max: 200,
                        message: '公司地址长度必须在10到200位之间'
                    }
                }
            },
            companyTel: {
                message: '公司联系方式失败',
                validators: {
                    notEmpty: {
                        message: '公司联系方式不能为空'
                    }, stringLength: {
                        min: 13,
                        max: 13,
                        message: '公司号码长度必须是13至13位,(例如:097-999977776)'
                    },regexp:{
                        regexp: /^\d{3,4}-?\d{7,9}$/,
                        message: '请输入正确公司官电话(例如：097-999977776)'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_companyTel?editCompanyTel=' + editTel,
                        message: '该公司号码已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            companyPricipal: {
                message: '公司负责人失败',
                validators: {
                    notEmpty: {
                        message: '公司负责人不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 4,
                        message: '公司负责人长度必须在2到4位之间'
                    }
                }
            },
            companyWebsite: {
                message: '公司官网URL失败',
                validators: {
                    regexp: {
                        regexp: /^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?$/,
                        message: '请输入正确公司官网网址（http://开头）'
                    },
                    notEmpty: {
                        message: '公司官网URL不能为空'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_companyWebsite?editWebsite=' + editWebsite,
                        message: '该URL已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            companySize: {
                message: '公司规模失败',
                validators: {
                    notEmpty: {
                        message: '公司规模不能为空'
                    }
                }
            },
            companyPricipalPhone:{
                message: '负责人手机号码失败',
                validators: {
                    notEmpty: {
                        message: '负责人手机号码不能为空'
                    },
                    regexp: {
                        regexp: /^1(3|4|5|7|8)\d{9}$/,
                        message: '手机号格式错误'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_userPhone?editPhone=' + editPhone,
                        message: '该URL已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                $('#addForm').ajaxSubmit({
                    url: '/company/InsertCompany',
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == "success") {
                            $('#addWin').modal('hide');
                            editName = "";
                            editTel  = "";
                            editWebsite = "";
                            editPhone = "";
                            swal(data.message, "", "success");
                            $('#cusTable').bootstrapTable('refresh');
                            $('#addForm').data('bootstrapValidator').resetForm(true);
                        } else if (data.result == "fail") {
                            $('#addWin').modal('hide');
                            swal(data.message, "", "error");
                            $('#addForm').data('bootstrapValidator').resetForm(true);
                        }
                    }
                })
            }
        })
}
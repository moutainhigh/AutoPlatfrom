/**
 * Created by xiao-kang on 2017/4/17.
 */
var sessionUserId;

var contextPath = '';
var editEmail = "";
var editPhone = "";
var editIdentity = "";
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", contextPath + "/admin/query_pager");
    /*initSelect2("adminCAndSO", "选择管理员类型", contextPath + "/role/query_cAdminAndSOAdmin", "540");*/
    /*initSelect2("admin_company", "请选择公司", contextPath + "/company/company_all", "540");*/
    $("#search").bind("click", initTable);
    destoryValidator("addWin", "addForm");
    destoryValidator("editWin", "editForm");
});

function getSessionUserId(val) {
    sessionUserId = val;
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length == 0) {
        swal('编辑失败', "必须选择一条数据进行编辑", "error");
        return false;
    } else if (selectRow.length > 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
    } else {
        $("#editForm").clearForm();
        var user = selectRow[0];
        var selectGender = document.getElementById("editGender");
        selectGender.value = user.userGender;
        initEditParam(user);
    }
}

function showAddWin() {
    validator("addForm");
    /*$('#adminTypeSelect').html('').trigger("change");
    $('#addCompany').html('').trigger("change");*/
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

function thisStatus(value, row, index) {
    if (value == 'Y') {
        return "可用";
    } else {
        return "不可用";
    }
}

function operateFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">冻结</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">激活</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        if (sessionUserId != row.userId) {
            $.get(contextPath + "/admin/update_status?id=" + row.userId + "&status=" + status,
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
        } else {
            swal("无法冻结自己!", "", "error");
        }
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/admin/update_status?id=" + row.userId + "&status=" + status,
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
        $("#editForm").clearForm();
        var user = row;
        var selectGender = document.getElementById("editGender");
        selectGender.value = user.userGender;
        initEditParam(user);
    }
}

function queryAll() {
    initTable("cusTable", contextPath + "/admin/query_pager");
}

function queryCompany() {
    initTable("cusTable", contextPath + "/admin/company_pager");
}

function initEditParam(user) {
    editEmail = user.userEmail;
    editPhone = user.userPhone;
    editIdentity = user.userIdentity;
    $("#role").val(user.role.roleDes);
    $("#editBirthday").val(birthday(user.userBirthday));
    $("#createTime").val(formatterDate(user.userCreatedTime));
    $("#loginTime").val(formatterDate(user.userLoginedTime));
    $("#editForm").fill(user);
    $("#icon").attr("src",user.userIcon);
    validator("editForm");
    $("#editWin").modal('show');
    fmtDate();
}

function querySystem() {
    initTable("cusTable", contextPath + "/admin/system_pager");
}

/*function adminSelect(selectId) {
    var roleId = $("#" + selectId).val();
    var roleName = $("#" + selectId).find("option:selected").text();
    if (roleName == "董事长") {
        $(".admin_company").prop("disabled", false);
    } else {
        $(".admin_company").prop("disabled", true);
        $("#addCompany").empty();
    }
}*/

/**生成默认密码*/
function defaultPwd() {
    var password = "123456";
    $("#pwd").val(password);
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
                message: '名称验证失败',
                validators: {
                    notEmpty: {
                        message: '名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '名称长度必须在2到10位之间'
                    }
                }
            },
            userPwd: {
                message: '密码验证失败',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度必须在6到16位之间'
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
                    },
                    threshold: 11,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_userPhone?editPhone=' + editPhone,
                        message: '该手机号已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            userEmail: {
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp: {
                        regexp: /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/,
                        message: '邮箱格式错误'
                    },
                    threshold: 6,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_userEmail?editEmail=' + editEmail,
                        message: '该邮箱已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            userIdentity: {
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    regexp: {
                        regexp: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                        message: '身份证格式错误'
                    },
                    threshold: 18,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_userIdentity?editIdentity=' + editIdentity,
                        message: '该身份证已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            userAddress: {
                message: '地址验证失败',
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 15,
                        message: '地址长度必须在2到15位之间'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit(contextPath + "/admin/add_admin", formId, "addWin");

            } else if (formId == "editForm") {
                /*formSubmit(contextPath + "/admin/update_admin", formId, "editWin");*/
                editIdentity = "";
                editEmail = "";
                editPhone = "";
                $('#editForm').ajaxSubmit({
                    url: contextPath + '/admin/update_admin',
                    type: 'post',
                    dataType: 'json',
                    success: function (data) {
                        if (data.result == "success") {
                            $('#editWin').modal('hide');
                            swal(data.message, "", "success");
                            $('#cusTable').bootstrapTable('refresh');
                            $('#editForm').data('bootstrapValidator').resetForm(true);
                        } else if (data.result == "fail") {
                            $('#editForm').modal('hide');
                            swal(data.message, "内容不匹配", "error");
                            $('#editForm').data('bootstrapValidator').resetForm(true);
                        }
                    }
                })
            }
        })

}

function getBirthday(obj, index) {
    var identity = obj.value;
    if (identity.length == 18) {
        var year = identity.substring(6, 10);
        var month = identity.substring(10, 12);
        var date = identity.substring(12, 14);
        var birthday = year + "-" + month + "-" + date;
        if (index == 1) {
            $("#birthday").val(birthday);
            getGender(obj);
        } else {
            $("#editBirthday").val(birthday);
        }
    }
}

function getGender(obj) {
    var genderStr = (obj.value.substring(16, 17));
    if (gender % 2 == 0) {
        gender = "F";
    } else {
        gender = "M"
    }
        $("#gender").val(gender);

}

function gender(value, row, index) {
    if (value == "M") {
        return "男";
    } else if (value == "F") {
        return "女";
    } else {
        return "未选择";
    }
}

/**
 * 时间格式化，传递进来的时间,返回yyyy-MM-dd HH:mm
 * @param value
 * @returns
 */
function birthday(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    }
    else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    }
}

function fmtDate(){
    var curLength = $("#chang").val().length;
    var shu = 200-curLength;
    $("#textShu").text(shu);

    var textareaObj=document.getElementById("chang");
    var remainObj=document.getElementById("textShu");
    var num=0;
    if(/msie/i.test()){
        textareaObj.onpropertychange=function(){
            num=200-this.value.length;
            remainObj.innerHTML=num;
        }
    }else{
        textareaObj.oninput=function(){
            num=200-this.value.length;
            remainObj.innerHTML=num;
        }
    }

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchPhone").val('');
    $("#searchEmail").val('');
    $("#searchName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function bySelectSearch() {
    var userName = $("#searchName").val();
    var userPhone = $("#searchPhone").val();
    var userEmail = $("#searchEmail").val();
    initTable("cusTable", contextPath + "/admin/select_query?userName=" + userName + "&userPhone=" + userPhone + "&userEmail=" + userEmail);
}

function previewImage(file)
{
    var MAXWIDTH  = 250;
    var MAXHEIGHT = 250;
    var div = document.getElementById('preview');
    if (file.files && file.files[0])
    {
        div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
        var img = document.getElementById('imghead');
        img.onload = function(){
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width  =  rect.width;
            img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top+'px';
        }
        var reader = new FileReader();
        reader.onload = function(evt){img.src = evt.target.result;}
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
        div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
    }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight ){
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if( rateWidth > rateHeight ){
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else{
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    return param;
}

function roleName(value, row, index) {
    if (value == "董事长") {
        return row.company.companyName + value;
    }
    return value;
}
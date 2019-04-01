$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/customer/customerInfo_pager");
    destoryValidator("addWin", "addForm");
    destoryValidator("editWin","editForm");
    destoryValidator("myModal","editModal");

});

var editEmail = "";
var editPhone = "";
var editIdentity = "";


function gender(value, row, index) {
    if (row.userGender == 'M') {
        return '男'
    }else if (row.userGender == 'F'){
        return '女'
    }else if (row.userGender == 'N'){
        return '未知'
    }
}


$(function () {$("[data-toggle='tooltip']").tooltip();});
function fmtDate(){
    var curLength = $("#chang").val().length;
    var shu = 150-curLength;
    $("#textShu").text(shu);

    var textareaObj=document.getElementById("chang");
    var remainObj=document.getElementById("textShu");
    var num=0;
    if(/msie/i.test()){
        textareaObj.onpropertychange=function(){
            num=150-this.value.length;
            remainObj.innerHTML=num;
        }
    }else{
        textareaObj.oninput=function(){
            num=150-this.value.length;
            remainObj.innerHTML=num;
        }
    }

}


function oldAndNew() {
    var da1 = $("#form_datetime").val();
    var da = formatterDate(new Date());
    var year = da.substring(0,4);
    var year1 = da1.substring(0,4);
    var count = year - year1;
    if (count >= 1){
        $("#client").val("老客户");
    } else {
        $("#client").val("新客户");
    }
}


function customerAge(row) {
    var card = row.userIdentity;
    if(card != null && card != '') {
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();

        var age = myDate.getFullYear() - card.substring(6, 10) - 1;
        if (card.substring(10, 12) < month || card.substring(10, 12) == month && card.substring(12, 14) <= day) {
            age++;
        }
        var birthday = card.substring(6, 10) + "-" + card.substring(10, 12) + "-" + card.substring(12, 14);
        $("#birthday").val(birthday);
        $("#age").val(age);
    }else{
        $("#birthday").val("");
        $("#age").val("");
    }
}

function operateFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">冻结</button>',
            '<button style="margin-left: 10px" type="button" class="showUpdateInfo btn btn-primary">查看详情</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">激活</button>',
            '<button style="margin-left: 10px" type="button"  class="showUpdateInfo btn btn-primary">查看详情</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var userStatus = 'N';
        $.get("/customer/customerInfo_status?id=" + row.userId + "&status=" + userStatus,
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
        var userStatus = 'Y';
        $.get("/customer/customerInfo_status?id=" + row.userId + "&status=" + userStatus,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
                }else if (data.result == "notLogin") {
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
    'click .showUpdateInfo': function (e, value, row, index) {
        var user = row;
        var selectGender = document.getElementById("gender");
        selectGender.value = user.userGender;
        editEmail = user.userEmail;
        editPhone = user.userPhone;
        editIdentity = user.userIdentity;
        var loginedTime = document.getElementById("form_loginedTime");
        $("#ffile").val(user.userIcon);
        loginedTime.value = user.userLoginedTime;
        $("#icon").attr("src", user.userIcon);
        $("#form_loginedTime").val(formatterDate(user.userLoginedTime));
        $("#role").val(user.role.roleDes);
        $("#form_datetime").val(formatterDate(user.userCreatedTime));
        $("#editModal").fill(user);
        validator("editModal");
        $("#myModal").modal('show');
        if(user.userStatus == 'Y'){
            $("#status").val("可用");
        }else{
            $("#status").val("不可用");
        }
        var change = user.userStatus;
        if (change == 'N'){
            return 'Y';
        }if (user.userNickname == null || user.userNickname == ""){
            $("#nickname").val("");
        }if (user.userEmail == null || user.userEmail == ""){
            $("#editEmail").val("");
        }if (user.userIdentity == null || user.userIdentity == ""){
            $("#editIdentity").val("");
        }if (user.wechatOpenId == null || user.wechatOpenId == ""){
            $("#wechatOpen").val("");
        }if (user.qqOpenId == null || user.qqOpenId == ""){
            $("#qqOpen").val("");
        }if (user.weiboOpenId == null || user.weiboOpenId == ""){
            $("#weiboOpen").val("");
        }if (user.userAddress == null || user.userAddress == ""){
            $("#address").val("");
        }if (user.userDes == null || user.userDes == ""){
            $("#chang").val("");
        }
        customerAge(row);
        oldAndNew();
        fmtDate();
        formatterDate();
    }
}

/** 修改提交 */
function editModal() {
    $("#editModal").data('bootstrapValidator').validate();
    if ($("#editModal").data('bootstrapValidator').isValid()) {
        $("#editModalButton").attr("disabled","disabled");
    } else {
        $("#editModalButton").removeAttr("disabled");
    }
}





/** 查看不可用 */
function showStatusWin_N() {
    initTable("cusTable", "/customer/customerInfo_pagerStatus");
}

/** 查看可用 */
function showStatusWin_Y() {
    initTable("cusTable", "/customer/customerInfo_pager");
}

/** 查看全部车主 */
function showStatusWin() {
    initTable("cusTable", "/customer/customerInfo");
}



/** 条件查询全部车主 */
function selectCustomer() {
    var userPhone = $("#userPhone").val();
    var userName = $("#userName").val();
    initTable("cusTable", "/customer/selectCustomerInfo?userPhone="+userPhone + "&userName="+userName);
}


function closeCustomer() {
    $("#formCustomer").hide();
}

function selectCustomerWin() {
    $("#formCustomer").show();
    $("#customerDiv").show();
}

function defaultPwd() {
    var password = "123456";
    $("#pwd").val(password);
}

/**提交添加数据 */
function showAddWin() {
    validator("addForm");
    $('#addCompany').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#pwd").val("");
    $("#addWin").modal('show');
}

/** 表单验证 */
function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $("#editModalButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
        },
        fields: {
            userNickname: {
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    },
                    stringLength: {
                        message: '昵称长度为4-8位'
                    }
                }
            },
            userPwd: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 16,
                        message: '密码长度为6-16位'
                    }
                }
            },
            userName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    regexp: {
                        regexp: /^[\u4e00-\u9fa5]{2,4}$/,
                        message: '姓名格式错误'
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
                        url: '/customer/customerPhone_verification?editPhone='+editPhone,
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
                        url: '/customer/customerEmail_verification?editEmail='+editEmail,
                        message: '该微信已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            userIdentity: {
                validators: {
                    regexp: {
                        regexp: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
                        message: '身份证格式错误'
                    },
                    threshold: 18,
                    remote: {
                        url: '/customer/customerIdentity_verification?editIdentity='+editIdentity,
                        message: '该身份证已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            wechatOpenId: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\d_.]{5,}$/,
                        message: '微信号格式错误'
                    }
                }
            },
            qqOpenId: {
                validators: {
                    regexp: {
                        regexp: /[1-9][0-9]{5,}/,
                        message: 'QQ号格式错误'
                    }
                }
            },
            weiboOpenId: {
                validators: {
                    regexp: {
                        regexp: /[a-zA-z0-9_\d_.]{5,}/,
                        message: '微博号格式错误'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/customer/customerInfo_insert", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/customer/customerInfo_update", formId, "editWin");
            } else if(formId == 'editModal') {
                    $('#editModal').ajaxSubmit({
                        url: '/customer/customerInfo_update',
                        type: 'post',
                        dataType: 'json',
                        success: function (data) {
                            if (data.result == "success") {
                                $('#myModal').modal('hide');
                                swal(data.message, "", "success");
                                $('#cusTable').bootstrapTable('refresh');
                                $('#editModal').data('bootstrapValidator').resetForm(true);
                            } else if (data.result == "fail") {
                                $('#myModal').modal('hide');
                                swal(data.message, "修改信息有误", "error");
                                $('#editModal').data('bootstrapValidator').resetForm(true);
                            }
                        }
                    })
            }


        })
}

function outFormData(formData) {
    var form = formData;
    var tagElements = form.getElementsByTagName('input');
    for (var j = 0; j < tagElements.length; j++){
        alert(tagElements[j].value);

    }
    alert($("#editModal").serialize());
}

//图片上传预览    IE是用了滤镜。
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


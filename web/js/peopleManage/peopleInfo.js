$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/peopleManage/peopleInfo_pager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
    initSelect2("user_role", "请选择角色", "/peopleManage/role_all", 565);//不包括人事部管理员
    initSelect2("user_roleAll", "请选择角色", "/peopleManage/companyRole_all", 565);//包括人事部管理员
    initSelect2("user_roleName", "请选择角色", "/peopleManage/queryRole_all", 150);
    initSelect2("user_company", "请选择公司", "/peopleManage/user_company", 150);
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


function salary(value, row, index) {
    if (row.userSalary == 0) {
        return '无'
    } else {
        return row.userSalary;
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

function operateUpdateFormatter(value, row, index) {
    return [
        '<button type="button" class="showUpdateInfo btn btn-primary">查看详情</button>'
    ].join('');
}


function operateFormatter(value, row, index) {
    if (row.userStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">冻结</button>',
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">激活</button>',
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var userStatus = 'N';
        $.get("/peopleManage/peopleInfo_status?id=" + row.userId + "&status=" + userStatus,
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
        $.get("/peopleManage/peopleInfo_status?id=" + row.userId + "&status=" + userStatus,
            function (data) {
                if (data.result == "success") {
                    $('#addWin').modal('hide');
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
}
window.operateUpdateEvents = {
    'click .showUpdateInfo': function (e, value, row, index) {
        var user = row;
        var selectGender = document.getElementById("gender");
        selectGender.value = user.userGender;
        editEmail = user.userEmail;
        editPhone = user.userPhone;
        editIdentity = user.userIdentity;
        $("#role").val(row.role.roleDes);
        $("#form_datetime").val(formatterDate(user.userCreatedTime));
        $("#editModal").fill(user);
        $("#icon").attr("src", user.userIcon);
        var company = document.getElementById("editModalCompany");
        company.value = user.company.companyName;
        var loginedTime = document.getElementById("form_loginedTime");
        loginedTime.value = user.userLoginedTime;
        $("#form_loginedTime").val(formatterDate(user.userLoginedTime));
        $('#editModalCompany').html('<option value="' + user.company.companyId + '">' + user.company.companyName + '</option>').trigger("change");
        validator("editModal");
        $("#myModal").modal('show');
        if (user.userStatus == 'Y') {
            $("#status").val("可用");
        } else {
            $("#status").val("不可用");
        }
        var change = user.userStatus;
        if (change == 'N') {
            return 'Y';
        }
        customerAge(row);
        fmtDate();
        formatterDate();
    }
}

/** 修改提交 */
function editModal_1() {
    $("#editModal").data('bootstrapValidator').validate();
    if ($("#editModal").data('bootstrapValidator').isValid()) {
        $("#editModalButton").attr("disabled","disabled");
    } else {
        $("#editModalButton").removeAttr("disabled");
    }
}




/** 查看不可用 */
function showStatus_N() {
    initTable("cusTable", "/peopleManage/peopleInfo_pagerStatus");
}

/** 查看可用 */
function showStatus_Y() {
    initTable("cusTable", "/peopleManage/peopleInfo_pager");
}

/** 查看全部员工 */
function showStatus() {
    initTable("cusTable", "/peopleManage/peopleInfoAll");
}



/** 条件查询全部员工 */
function selectPeople() {
    var userPhone = $("#userPhone").val();
    var userName = $("#userName").val();
    var userEmail = $("#userEmail").val();
    var roleName = $("#roleName").val();
    var companyName = $("#companyName").val();
    initTable("cusTable", "/peopleManage/selectPeopleInfo?userPhone="+userPhone + "&userName="+userName + "&userEmail="+userEmail + "&roleName="+roleName + "&companyName="+companyName);
}


function closePeople() {
    $("#formPeople").hide();
}

function selectPeopleWin() {
    $("#formPeople").show();
    $("#peopleDiv").show();
}


/** 修改提交按钮 */
function editInfo() {
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
    } else {
        $("#editButton").removeAttr("disabled");
    }
}



/**编辑数据 */
function showEdit() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('分配失败', "只能选择一条信息进行角色分配", "error");
        return false;
    } else {
        var user = selectRow[0];
        if(user.userStatus != "N"){
            $("#editForm").fill(user);
            $('#editRole').html('<option value="' + user.role.roleId + '">' + user.role.roleDes + '</option>').trigger("change");
            $('#editRoleAll').html('<option value="' + user.role.roleId + '">' + user.role.roleDes + '</option>').trigger("change");
            $("#editWin").modal('show');
        } else {
            swal('分配失败', "该员工是不可用状态", "error");
            return false;
        }
    }
}

function defaultPwd() {
    var password = "123456";
    $("#pwd").val(password);
}

/**提交添加数据 */
function showAdd() {
    validator("addForm");
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
            userAddress: {
                validators: {
                    notEmpty: {
                        message: '居住地不能为空'
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
                        url: '/peopleManage/peoplePhone_verification?editPhone='+editPhone,
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
                        url: '/peopleManage/peopleEmail_verification?editEmail='+editEmail,
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
                        url: '/peopleManage/peopleIdentity_verification?editIdentity='+editIdentity,
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
                    regexp: /[1-9][0-9]{4,}/,
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
            },
            userSalary: {
                validators: {
                    regexp: {
                        regexp: /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/,
                            message: '工资格式错误'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/peopleManage/peopleInfo_insert", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/peopleManage/peopleRole_update", formId, "editWin");
            } else if(formId == 'editModal') {
                $('#editModal').ajaxSubmit({
                    url: '/peopleManage/peopleInfo_update',
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


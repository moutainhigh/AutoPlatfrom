/**
 * Created by Xiao-Qiang on 2017/5/5.
 */
var contextPath = '';

function redirectIndex(needRedirect) {
    if (needRedirect == "redirect") {
        $.messager.alert("提示", "登录信息无效，请重新登录", "info", function () {
            top.location.href = "/login/logout";
        });
    }
}

function login() {
    $.post(contextPath + "/login/login",
        $("#login_form").serialize(),
        function (data) {
            if (data.result == "success") {
                if (data.message == "adminHome") {
                    window.location.href = contextPath + "/adminHome";
                } else if (data.message == "customerHome") {
                    window.location.href = contextPath + "/customerHome";
                }
            } else {
                $("#errMsg").html(data.message);
            }
        }
    );
}

function showCodeWin() {
    $("#codeWin").modal('show');
}

var count = 3;
function cCount(val) {
    $("#successMsg").html(val + count + "s后自动登入");
    if (count == 0) {
        count = 3;
    } else {
        count--;
    }
    setTimeout(function () {
        if (count >= 0) {
            cCount(val);
        }
    }, 1000)
}
/** 注册逻辑 */
function register() {
    $.post(contextPath + "/login/register",
        $("#register_form").serialize(),
        function (data) {
            if (data.result == "success") {
                if (data.message == "注册成功，请到邮箱中进行验证") {
                    $("#errMsg1").html("");
                    swal({
                            title: "注册成功",
                            text: data.message + ",是否打开邮箱？",
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
                                var type = getEmailType(userEmail);
                                window.open("http://www.mail." + type + ".com");
                            } else {

                            }
                        });
                } else {
                    cCount(data.message);
                    setTimeout(function () {
                        window.location.href = contextPath + "/customerHome";
                    }, 3000);
                }
            } else {
                $("#errMsg1").html(data.message);
            }
        }
    );
}

/** 获取邮箱类型 */
function getEmailType(val) {
    var value = val.split(".");
    return value[0].split("@")[1];
}
var userPhone;
var userEmail;
/** 验证输入的账号 */
function variNumber(number) {
    if (isEmail(number)) {
        userEmail = number;
        $("#errMsg1").html("");
        $("#codeDiv").hide();
    } else if (isPhone(number)) {
        userPhone = number;
        $("#errMsg1").html("");
        $("#codeDiv").show();
    } else {
        $("#errMsg1").html("请输入正确的手机号或邮箱");
    }
}

/** 判断是否是邮箱 */
function isEmail(str) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.([a-zA-Z0-9_-])+)+$/;
    return reg.test(str);
}

/** 判断是否是手机号 */
function isPhone(str) {
    var reg = /^1[3|4|5|7|8][0-9]{9}$/;
    return reg.test(str);
}

/** 获取验证码 */
var countdown = 60;
function getCode(val) {
    sendCode(countdown);
    if (countdown == 0) {
        val.removeAttribute("disabled");
        val.value = "获取验证码";
        countdown = 60;
    } else {
        val.setAttribute("disabled", true);
        val.value = "重新发送(" + countdown + ")";
        countdown--;
    }
    setTimeout(function () {
        if (val.value != "获取验证码") {
            getCode(val);
        } else {
            $("#successMsg").html('');
        }
    }, 1000)
}

/** 发送短信 */
function sendCode(val) {
    if (val == 60) {
        if (userPhone != null && userPhone != "" && userPhone != undefined) {
            $.get("/login/sendCode?userPhone=" + userPhone, function (data) {
                if (data.result == "success") {
                    $("#successMsg").html(data.message);
                }
            }, "json");
        }
    }
}

var codeNumber;
/** 获取验证码 */
var countdown1 = 60;
function getCode1(val) {
    var phone = $("#phone").val();
    if(phone != '' ){
        sendCode1(countdown1);
        if (countdown1 == 0) {
            val.removeAttribute("disabled");
            val.value = "获取验证码";
            countdown1 = 60;
        } else {
            val.setAttribute("disabled", true);
            val.value = "重新发送(" + countdown1 + ")";
            countdown1--;
        }
        setTimeout(function () {
            if (val.value != "获取验证码") {
                getCode1(val);
            }
        }, 1000)
    }else{
        $("#error").html("请输人手机号");
    }

}

/** 发送查询进度的验证短信 */
function sendCode1(val) {
    if (val == 60) {
        var userPhone = $("#phone").val();
        if (userPhone != null && userPhone != "" && userPhone != undefined) {
            $.get("/customerClientWeb/sendCode?userPhone=" + userPhone, function (data) {
                if (data.result == "success") {
                    $("#successMsg").html(data.message);
                    getCodeCar();
                }
            }, "json");
        }
    }
}

var carCode = '';
function getCodeCar(){
    $.get("/customerClientWeb/code",
        function(data){
            carCode = data;
        })
}

/** 检查手机号 */
function checkPhone(phone) {
    if(phone != '') {
        if (isPhone(phone.value)) {
            $("#error").html('');
            $("#getButton").removeAttr("disabled");
        } else {
            $("#error").html('请输入正确的手机号');
            $("#getButton").attr("disabled", "disabled");
        }
    }
}

/** 查询 */
function customerCar() {
    var phone = $("#phone").val();
    var code = $("#code").val();
    var base = new Base64();
    var codeData = base.decode(""+carCode);
    if (code == codeData) {
        $("#error").html('');
        $("#successMsg").html('');
        $("#phone").val('');
        $("#code").val('');
        countdown1 = 60;
        $("#getButton").val("获取验证码");
        $("#getButton").removeAttribute("disabled");
        var url = '/customerClientWeb/userPage?phone=' + phone;
        document.getElementById("iframeTable").src = url;
    } else {
        $("#error").html('您输入的验证码有误，请重新输入');
    }
}

/*显示找回密码窗口*/
function showPwdWin(){
    $("#editPwd").modal("show");
    $("input[type=reset]").trigger("click");
    $("#error").html("");
    $("#error1").html("");
    $('#successMsg2').html("");
    $('#pwdDiv').hide();
    countdown2 = 60;
    $("#codeButton").val("获取验证码");
    $("#codeButton").attr("disabled","disabled");

}

/*显示动态登录窗口*/
function showLoginWin(){
    $("#loginWin").modal("show");
    $("input[type=reset]").trigger("click");
    $("#error").html("");
    countdown2 = 60;
    $("#codeButton").val("获取验证码");
    $("#codeButton").attr("disabled","disabled");

}

function checkPhone(number){
    if (number != null && number != "") {
        $.get("/pwd/checkPhone?number=" + number,
            function (data) {
                if (data == "true") {
                    userPhone = number;
                    $("#loginError").html("");
                    $("#codeButton1").removeAttr("disabled");
                } else if (data == "false") {
                    $("#codeButton1").attr("disabled", "disabled");
                    $("#loginError").html("不存在该用户");
                }
            })
    }
}


function checkEmail(number){
    if (number != null && number != "") {
        $.get("/pwd/checkPhone?number=" + number,
            function (data) {
                if (data == "true") {
                    userPhone = number;
                    $("#error").html("");
                    $("#pwdDiv").show();
                    $("#codeButton").removeAttr("disabled");
                } else if (data == "false") {
                    $("#error").html("不存在该用户");
                }
            })
    }
}
/** 验证找回密码输入的账号 */
function variNumber1(number) {
    if(number != '') {
        if (isEmail(number)) {
            checkEmail(number);
        } else if (isPhone(number)) {
            checkEmail(number);
        } else {
            $("#error").html("请输入正确的手机号或邮箱");
        }
    }
}

/** 验证手机号登录 */
function variNumber2(number) {
    if(number != '') {
        if (isPhone(number)) {
            checkPhone(number);
        } else {
            $("#codeButton1").attr("disabled", "disabled");
            $("#loginError").html("请输入正确的手机号");
        }
    }
}

/*获取验证码*/
var countdown2 = 60;
function getCode2(val) {
    var phone = $("#phone").val();
    sendCode2(countdown2);
    if (countdown2 == 0) {
    val.removeAttribute("disabled");
    val.value = "获取验证码";
    countdown2 = 60;
    } else {
    val.setAttribute("disabled", true);
    val.value = "重新发送(" + countdown2 + ")";
    countdown2--;
    }
    setTimeout(function () {
    if (val.value != "获取验证码") {
        getCode2(val);
    }
    }, 1000)
}

/*获取验证码*/
var countdown3 = 60;
function getCode3(val) {
    var phone = $("#phone1").val();
    sendCode3(countdown3);
    if (countdown3 == 0) {
        val.removeAttribute("disabled");
        val.value = "获取验证码";
        countdown3 = 60;
    } else {
        val.setAttribute("disabled", true);
        val.value = "重新发送(" + countdown3 + ")";
        countdown3--;
    }
    setTimeout(function () {
        if (val.value != "获取验证码") {
            getCode2(val);
        }
    }, 1000)
}

/** 发送找回密码的验证短信 */
function sendCode2(val) {
    if (val == 60) {
        var userPhone = $("#phone").val();
        if (userPhone != null && userPhone != "" && userPhone != undefined) {
            $.get("/pwd/sendCode?number=" + userPhone , function (data) {
                if (data.result == "success") {
                    $("#successMsg2").html(data.message);
                    getCodePwd();
                }
            }, "json");

        }
    }
}
/** 发送动态登录的验证短信 */
function sendCode3(val) {
    if (val == 60) {
        var userPhone = $("#phone1").val();
        if (userPhone != null && userPhone != "" && userPhone != undefined) {
            $.get("/pwd/sendCode1?number=" + userPhone , function (data) {
                if (data.result == "success") {
                    getCodePwd1();
                    $("#loginSuccess").html(data.message);
                }
            }, "json");

        }
    }
}
/*获取到找回密码的验证码*/
var pwdCode = '';
function getCodePwd(){
    $.get("/pwd/code",
        function(data){
            pwdCode = data;
    })
}

/*获取到动态登入的验证码*/
var pwdCode1 = '';
function getCodePwd1(){
    $.get("/pwd/code1",
        function(data){
            pwdCode1 = data;
        })
}
/*找回密码验证用户输入的验证码*/
function variCode(val){
    var base = new Base64();
    var code = base.decode(""+pwdCode);
    if(val != '') {
        if (val == code) {
            $("#error").html("");
            $("#pwd").removeAttr("disabled");
            $("#pwd1").removeAttr("disabled");
        } else {
            $("#pwd").attr("disabled", "disabled");
            $("#pwd1").attr("disabled", "disabled");
            $("#error").html('您输入的验证码有误，请重新输入');
        }
    }else{

    }
}
/*动态登入验证用户输入的验证码*/
function variCode1(val){
    var base = new Base64();
    var code = base.decode(""+pwdCode1);
    if(val != '') {
        if (val == code) {
            $("#loginError1").html("");
            return true;
        } else {
            $("#loginError1").html('您输入的验证码有误，请重新输入');
        }
    }else{
        $("#loginError1").html('请输入手机验证码');
    }
    return false;
}

function editPwd(){
    var pwd = $("#pwd").val();
    var pwd1 = $("#pwd1").val();
    var number = $("#phone").val();
    if(pwd != '' && pwd1 != '') {
        if(pwd >= 6 && pwd1 >= 6 && pwd >= 16 && pwd1 >= 16) {
            if (pwd == pwd1) {
                $("#error1").html("");
                $.get("/pwd/editPwd?number=" + number + "&pwd=" + pwd,
                    function (data) {
                        if (data.result == "success") {
                            swal("修改密码成功", "", "success");
                            $("#editPwd").modal("hide");
                        }
                    }, "json");
            } else {
                $("#error1").html('您输入的两次密码不一样，请重新输入');
            }
        }else{
            $("#error1").html('密码不能小于6位,大于16位');
        }
    }else{
        $("#error1").html('请输人密码');
    }
}




/** 清除提示信息 */
function clearSuccess(val) {
    if (val.value != "") {
        $("#successMsg").html("");
    }
}

$('#password1').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行查询
        login();
    }
});

function login1() {
    var phone = $("#phone1").val();
    var code = $("#loginCode").val();
    if (variCode1(code)) {
        $.get(contextPath + "/pwd/login?phone=" + phone,
            function (data) {
                if (data.result == "success") {
                    if (data.message == "adminHome") {
                        window.location.href = contextPath + "/adminHome";
                    } else if (data.message == "customerHome") {
                        window.location.href = contextPath + "/customerHome";
                    }
                } else {
                    $("#loginError1").html(data.message);
                }
            }
        );
    }

}


/** 获取6位数验证码 */
function getCodeNumber() {
    var str = Math.floor((Math.random() * 9 + 1) * 100000);
    return str;
}
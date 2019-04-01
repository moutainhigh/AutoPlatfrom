function editPwd() {
    var oldPwd = $("#oldPwd").val();
    var pwd = $("#pwd").val();
    var rePwd = $("#rePwd").val();
    if (pwd == rePwd && rePwd.length >= 6 && rePwd != null && rePwd != "" && pwd != null && pwd != "") {
        $.get("/pwd/selfPwd?oldPwd="+oldPwd + "&rePwd="+rePwd, function (data) {
            if (data.result == "success") {
                swal({
                        title: "修改成功",
                        text: data.message,
                        type: "success",
                        showCancelButton: false,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确认",
                        closeOnConfirm: true
                    },
                    function (isConfirm) {
                        if (isConfirm) {
                            window.parent.closeThis();
                        }
                    });
            } else if (data.result == "fail") {
                swal("修改失败", data.message, "error");
            }
        }, 'json');
    } else {
        swal('错误提示', "两次密码输入有误", "error");
        return false;
    }
}


$(".arrowImg2").hide();

function arrow2() {
    $(".arrowImg1").show();
    $(".arrowImg2").hide();
    $("#oldPwd").prop('type', 'password');
}

function arrow1() {
    $(".arrowImg1").hide();
    $(".arrowImg2").show();
    $("#oldPwd").prop('type', 'text');
}

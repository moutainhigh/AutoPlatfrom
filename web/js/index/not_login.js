/**
 * Created by Administrator on 2017-05-09.
 */

function notLogin() {

    swal({
            title: "登入失败",
            text: "登入信息已失效，请重新登入",
            type: "warning",
            showCancelButton: false,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确认",
            closeOnConfirm: true
        },
        function (isConfirm) {
            if (isConfirm) {
                top.location.href = "/index";
            }
        });
}
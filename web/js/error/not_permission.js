/**
 * Created by Administrator on 2017-05-09.
 */

function notPermission() {

    swal({
            title: "没有权限访问",
            text: "抱歉，您没有权限访问哦，请联系管理员",
            type: "warning",
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
}
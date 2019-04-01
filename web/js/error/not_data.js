/**
 * Created by Administrator on 2017-05-09.
 */

function notData() {

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

/** 子窗口调用父窗口的js方法 */
function showData() {
    parent.showDataPage();
}
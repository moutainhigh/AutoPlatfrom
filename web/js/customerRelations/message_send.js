


var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/MessageSend/query_pager");
    destoryValidator("editWin","editForm");

});


/**添加车主*/
function showCustomer(){
    initTableNotTollbar("customerTable", "/record/pager_message");
    $("#customerWin").modal('show');
}

function addCustomer(){
    var selectRow = $("#customerTable").bootstrapTable('getSelections');
    if(selectRow.length < 1){
        swal('操作错误', "至少选择一个车主", "error");
    }else{
        addMessageId(selectRow);
        $("#customerWin").modal('hide');
    }
}

var userId = new Array();
/**插入Id*/
function addMessageId(obj){
    if(obj.length > 0){
        for(var i = 0; i < obj.length; i++){
            userId[i] =obj[i].checkin.userId;
        }
        
        $.get("/MessageSend/addMessageId?userId="+userId,
            function(data){
                swal({
                        title: "添加成功",
                        text: '是否需要前往发送短信内容',
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "确认",
                        cancelButtonText: "取消",
                        closeOnConfirm: true,
                        closeOnCancel: true
                    },function (isConfirm) {
                    if (isConfirm) {
                        showEditWin();
                    } else {
                        $('#cusTable').bootstrapTable('refresh');
                    }
                });

            })
    }else {
        swal('添加失败', "至少选择一行数据", "error");
    }
}

var idList = new Array();
var idStr = new Array();
var sendMsg = '';

/** 发送内容 */
function showEditWin() {
    idList = queryAllId();
    $("#editWin").modal('show');
    validator("editForm");
}
function sendSuccess(){
    idStr = queryAllId();
    swal({
        title: "",
        text: '是否发送短信',
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        closeOnConfirm: true,
        closeOnCancel: true
    },function (isConfirm) {
        if (isConfirm) {
            $.get("/MessageSend/update_success?idList="+idStr,
                function(data){
                    swal(data.message, "", "success");
                    $('#cusTable').bootstrapTable('refresh');
                })
        } else {
        }
    });

}
function  queryAllId() {
    var queryList = new Array();
    $.get("/MessageSend/queryAllId",
        function(data){
            $.each(data, function (index, item) {
                queryList[index] = data[index].messageId;
            })
        })
    return queryList;
}






function validator(formId) {
    $("#editButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            sendMsg:{
                validators:{
                    notEmpty:{
                        message: '短信内容不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '短信内容不能超过500个字符'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {

            } else if (formId == "editForm") {
                sendMsg = $("#sendMsg").val();
                $.get("/MessageSend/update_messageSend?idList="+idList+"&sendMsg="+sendMsg,
                    function(data){
                        if(data.result == 'success'){
                            $('#editWin').modal('hide');
                            swal(data.message, "", "success");
                            $('#cusTable').bootstrapTable('refresh');
                        }
                    })
            }


        })

}

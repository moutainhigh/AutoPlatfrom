


var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/trackVisit/query_pager");
    destoryValidator("editWin","editForm");
    destoryValidator("addWin","addForm");
});



function operateFormatter(value, row, index) {
    if (row.maintainRecord.trackStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >已回访</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >未回访</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}


/** 添加数据 */
function showAddWin() {
    validator("addForm");
    $("#addWin").modal('show');
}

/** 编辑数据 */
function showEditWin() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
    } else {
        var vistit = selectRow[0];
        $("#editForm").fill(vistit);
        $("#editWin").modal('show');
    }
}


window.openCustomer ={
    'click .addCustomer': function (e, value, row, index) {
        $(".customerId").val(row.checkin.userId);
        $(".visit_user").val(row.checkin.userName);
        showAddWin();
        $("#customerWin").modal('hide');
    }
}



window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             $.get(contextPath + "/complaint/update_status?id=" + row.inTypeId + "&status=" + status,
                 function(data){
                     if(data.result == "success"){
                         $('#addWin').modal('hide');
                         swal(data.message, "", "success");
                         $('#cusTable').bootstrapTable('refresh');
                     }else if(data.result == "fail"){
                         swal(data.message, "", "error");
                     }
                 },"json");
         },
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              $.get(contextPath + "/complaint/update_status?id=" + row.inTypeId + "&status=" + status,
                  function(data){
                      if(data.result == "success"){
                          $('#addWin').modal('hide');
                          swal(data.message, "", "success");
                          $('#cusTable').bootstrapTable('refresh');
                      }else if(data.result == "fail"){
                          swal(data.message, "", "error");
                      }
                  },"json");
          },
          'click .showUpdateIncomingType1': function (e, value, row, index) {
              var incomingType = row;
              $("#updateForm").fill(incomingType);
              $("#editWin").modal('show');
         }
}

function statusFormatter(value, row, index) {
    if (row.inTypeStatus == 'Y') {
        return [
            '可用'
        ].join('');
    }else if(row.inTypeStatus == 'N'){
        return [
            '不可用'
        ].join('');
    }

}

/**添加回访记录*/
function showCustomer(){
    initTableNotTollbar("customerTable", "/record/pager_track");
    $("#customerWin").modal('show');
}

function customerFormatter(value, row, index) {
    return [
        '<button type="button" class="addCustomer btn btn-primary  btn-sm" style="margin-right:15px;" >添加回访</button>'
    ].join('');
}


function addCustomer(){
    var selectRow = $("#customerTable").bootstrapTable('getSelections');
    if (selectRow.length > 1) {
        swal('操作错误', "只能选择一个车主", "error");
        return false;
    } else if(selectRow.length < 1){
        swal('操作错误', "至少选择一个车主", "error");
    }else  if(selectRow.length == 1){
        var customer = selectRow[0];
        $(".customerId").val(customer.checkin.userId);
        $(".visit_user").val(customer.checkin.userName);
        showAddWin();
        $("#customerWin").modal('hide');

    }
}


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
            serviceEvaluate: {
                validators: {
                    notEmpty: {
                        message: '回访评分不能为空'
                    }
                }

            },
            trackContent: {
                validators: {
                    notEmpty: {
                        message: '回访问题不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 500,
                        message: '回访问题长度必须在2到500位之间'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/trackVisit/add_track", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/salary/update_salary", formId, "editWin");

            }


        })

}





















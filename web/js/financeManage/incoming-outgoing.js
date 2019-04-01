/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/incomingOutgoing/query_pager");
    destoryValidator("editWin","editForm");
    initSelect2("outType", "请选择支出类型", "/outgoingType/outType_all", "565");
    initSelect2("inType", "请选择收入类型", "/incomingType/inType_all", "565");
    isType();
});

function isType(){
    $("#isType").bootstrapSwitch({
        onText: '收入',
        offText: '支出',
        onColor: 'success',
        offColor: 'danger',
        size: 'normal',
        onSwitchChange: function (event, state) {
            if (state == false) {
               $("#outDiv").css("display",'block');
                $("#inDiv").css("display",'none');
            } else if (state == true) {
                $('#inDiv').css("display",'block');
                $("#outDiv").css("display",'none');

            }
        }
    });
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var incomingOutgoing = selectRow[0];
        validator("editForm");
        $("#editForm").fill(incomingOutgoing);
        $("#addButton1").removeAttr("disabled");
        $("#editWin").modal('show');
    }
}

function showAddWin(){
    validator("addForm")
    $("#addWin").modal('show');
}

function rowStyle(row, index) {
    if (row.incomingType == null) {
        return {
            css: {"background-color": "#E8E8E8"}
        }
    }else if(row.outgoingType == null){
        return {
            css: {"background": "#E0FFFF"}
        }
    }
}

function operateFormatter(value, row, index) {
    if (row.inOutStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
        ].join('');
    }else{
        return [
            '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
            '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }

}
window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             $.get(contextPath + "/incomingOutgoing/update_status?id=" + row.inOutId + "&status=" + status,
                 function(data){
                     if(data.result == "success"){
                         $('#cusTable').bootstrapTable('refresh');
                     }else if(data.result == "fail"){
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
                 },"json");
         },
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              $.get(contextPath + "/incomingOutgoing/update_status?id=" + row.inOutId + "&status=" + status,
                  function(data){
                      if(data.result == "success"){
                          $('#cusTable').bootstrapTable('refresh');
                      }else if(data.result == "fail"){
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
                  },"json");
          },
          'click .showUpdateIncomingType1': function (e, value, row, index) {
              var incomingType = row;
              validator("editForm");
              $("#editForm").fill(incomingType);
              $("#addButton1").removeAttr("disabled");
              $("#editWin").modal('show');
         }
}

function queryByInOutType(type){
    if(type == 1){
        initTable("cusTable", "/incomingOutgoing/query_inOutType?type=2");
        $('#cusTable').bootstrapTable('hideColumn', 'incomingType.inTypeName');
    }else if(type == 2){
        initTable("cusTable", "/incomingOutgoing/query_inOutType?type=1");
        $('#cusTable').bootstrapTable('hideColumn', 'outgoingType.outTypeName');
    }else if(type == 3){
        initTable("cusTable", "/incomingOutgoing/query_pager");
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
            inOutMoney: {
                message: '收支金额失败',
                validators: {
                    notEmpty: {
                        message: '收支金额不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '收支金额只能是数字'
                    }
                }
            },
            outTypeId: {
                validators:{
                    notEmpty:{
                        message: '支出类型不能为空'
                    }
                }
            },
            inTypeId:{
                validators:{
                    notEmpty:{
                        message:'收入类型不能为空'
                    }
                }
            }

        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/incomingOutgoing/add_inOut", formId, "addWin");
            } else if (formId == "editForm") {
                formSubmit("/incomingOutgoing/update_inOut", formId, "editWin");
            }
        })

}



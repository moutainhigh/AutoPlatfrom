/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/incomingType/query_pager");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "220");
    destoryValidator("editWin","editForm");
    destoryValidator("addWin","addForm");
});

var MAINTENANCE_IN = '维修保养收入';
var ACC_IN = '配件收入'
var editTypeName = "";
/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var incomingType = selectRow[0];
        if(incomingType.inTypeName != MAINTENANCE_IN && incomingType.inTypeName != ACC_IN  && incomingType.company.companyId != null){
            editTypeName = incomingType.inTypeName;
            validator("editForm");
            $("#editForm").fill(incomingType);
            $('#editCompany').html('<option value="' + incomingType.company.companyId + '">' + incomingType.company.companyName + '</option>').trigger("change");
            $("#editWin").modal('show');
        }else{
            swal('编辑失败', "你不能修改名称为"+incomingType.inTypeName+"的记录", "warning");
        }
    }
}


function showAddWin(){
   validator("addForm");
    $('#addCompany').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

function operateFormatter(value, row, index) {
    if(row.inTypeName != MAINTENANCE_IN && row.inTypeName != ACC_IN) {
        if (row.inTypeStatus == 'Y') {
            return [
                '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
                '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
            ].join('');
        } else {
            return [
                '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
                '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
            ].join('');
        }
    }else{
        return ['<span style="color: lightcoral;">暂无</span>'].join('');
    }

}
window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             if( row.company.companyId != null) {
                 $.get(contextPath + "/incomingType/update_status?id=" + row.inTypeId + "&status=" + status,
                     function (data) {
                         if (data.result == "success") {
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
             }else{
                 swal('编辑失败', "", "warning");
             }
         },
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              if( row.company.companyId != null) {
                  $.get(contextPath + "/incomingType/update_status?id=" + row.inTypeId + "&status=" + status,
                      function (data) {
                          if (data.result == "success") {
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
              } else{
                  swal('编辑失败', "", "warning");
              }
          },
          'click .showUpdateIncomingType1': function (e, value, row, index) {
              var incomingType = row;
              if((incomingType.inTypeName != MAINTENANCE_IN || incomingType.inTypeName != ACC_IN)&& incomingType.company.companyId != null) {
                  editTypeName = incomingType.inTypeName;
                  validator("editForm");
                  $("#editForm").fill(incomingType);
                  $('#editCompany').html('<option value="' + incomingType.company.companyId + '">' + incomingType.company.companyName + '</option>').trigger("change");
                  $("#editWin").modal('show');
              }else{
                  swal('编辑失败', "", "warning");
              }
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
            inTypeName: {
                message: '收入类型名称失败',
                validators: {
                    notEmpty: {
                        message: '收入类型名称不能为空'
                    },
                    remote: {
                        url: '/vilidate/queryIsExist_inTypeName?editTypeName=' + editTypeName,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '收入类型名称长度必须在2到20位之间'
                    }

                }
            },
            companyId: {
                validators: {
                    notEmpty: {
                        message: '所属公司不能为空'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            var addName = $("#name").val();
            var editName = $("#name1").val();
            if (formId == "addForm") {
                if(addName != MAINTENANCE_IN && addName != ACC_IN) {
                    formSubmit("/incomingType/add_incomingType", formId, "addWin");
                }else{
                    swal('添加失败', "你不能添加名称为"+addName, "warning");
                    $('#addWin').modal('hide');
                    $('#' + formId).data('bootstrapValidator').resetForm(true);
                }
            } else if (formId == "editForm") {
                if(editName != MAINTENANCE_IN && editName != ACC_IN) {
                    formSubmit("/incomingType/update_incomingType", formId, "editWin");
                    editTypeName = '';
                }else{
                    swal('更新失败', "你不能添加名称为"+editName, "warning");
                    $('#addWin').modal('hide');
                    $('#' + formId).data('bootstrapValidator').resetForm(true);
                }
            }
        })

}

/** 根据条件搜索 */
function search() {
    var inTypeName = $("#inTypeName").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/incomingType/query_condition?inTypeName=" + inTypeName + "&companyId=" + companyId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#inTypeName").val('');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}

/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/outgoingType/query_pager");
    initSelect2("company", "请选择汽修公司", "/company/company_all", "220");
    destoryValidator("editWin","editForm");
    destoryValidator("addWin","addForm");
});

var SALARY_OUT = '工资支出';
var ACC_OUT = '配件支出';
var editTypeName = "";
/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var outgoingType = selectRow[0];
        if(outgoingType.outTypeName != SALARY_OUT && outgoingType.outTypeName != ACC_OUT && outgoingType.company.companyId != null ){
            editTypeName = outgoingType.outTypeName;
            validator("editForm");
            $("#editForm").fill(outgoingType);
            $('#editCompany').html('<option value="' + outgoingType.company.companyId + '">' + outgoingType.company.companyName + '</option>').trigger("change");
            $("#editWin").modal('show');
        }else{
            swal('编辑失败', "你不能修改名称为"+outgoingType.outTypeName+"记录", "warning");
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
    if(row.outTypeName != ACC_OUT && row.outTypeName != SALARY_OUT) {
        if (row.outTypeStatus == 'Y') {
            return [
                '<button type="button" class="updateActive btn btn-danger  btn-sm" style="margin-right:15px;" >冻结</button>',
                '<button type="button" class="showUpdateoutgoingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
            ].join('');
        } else {
            return [
                '<button type="button" class="updateInactive btn btn-success  btn-sm" style="margin-right:15px;" >激活</button>',
                '<button type="button" class="showUpdateoutgoingType1 btn btn-primary  btn-sm" style="margin-right:15px;">编辑</button>'
            ].join('');
        }
    }else{
        return ['<span style="color: lightcoral;">暂无</span>'].join('');
    }

}
window.operateEvents = {
         'click .updateActive': function (e, value, row, index) {
             var status = 'N';
             if( row.company.companyId != null ) {
                 $.get(contextPath + "/outgoingType/update_status?id=" + row.outTypeId + "&status=" + status,
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
          'click .updateInactive': function (e, value, row, index) {
              var status = 'Y';
              if( row.company.companyId != null ) {
                  $.get(contextPath + "/outgoingType/update_status?id=" + row.outTypeId + "&status=" + status,
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
          'click .showUpdateoutgoingType1': function (e, value, row, index) {
              var outgoingType = row;
              if(outgoingType.company.companyId != null ) {
                  editTypeName = outgoingType.outTypeName;
                  validator("editForm");
                  $("#editForm").fill(outgoingType);
                  $('#editCompany').html('<option value="' + outgoingType.company.companyId + '">' + outgoingType.company.companyName + '</option>').trigger("change");
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
            outTypeName: {
                message: '支出类型名称失败',
                validators: {
                    notEmpty: {
                        message: '支出类型名称不能为空'
                    },
                    remote: {
                        url: '/vilidate/queryIsExist_outTypeName?editTypeName=' + editTypeName,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '支出类型名称长度必须在2到20位之间'
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
                if(addName != SALARY_OUT && addName != ACC_OUT){
                    formSubmit("/outgoingType/add_outgoingType", formId, "addWin");
                }else{
                    swal('添加失败', "你不能添加名称为"+addName, "warning");
                    $('#addWin').modal('hide');
                    $('#' + formId).data('bootstrapValidator').resetForm(true);
                }

            } else if (formId == "editForm") {
                if(editName != SALARY_OUT && editName != ACC_OUT){
                    formSubmit("/outgoingType/update_outgoingType", formId, "editWin");
                    editTypeName = '';
                }else{
                    swal('编辑失败', "你不能修改名称为"+editName, "warning");
                    $('#editWin').modal('hide');
                    $('#' + formId).data('bootstrapValidator').resetForm(true);
                }
            }
        })

}

/** 根据条件搜索 */
function search() {
    var inTypeName = $("#inTypeName").val();
    var companyId = $("#searchCompanyId").val();
    initTable("cusTable", "/outgoingType/query_condition?inTypeName=" + inTypeName + "&companyId=" + companyId);

}

/** 关闭搜索的form */
function closeSearchForm() {
    $("#inTypeName").val('');
    $('#searchCompanyId').html('').trigger("change");
    $("#searchDiv").hide();
    $("#showButton").show();
}

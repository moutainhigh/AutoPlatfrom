/**
 * Created by xiao-kang on 2017/4/17.
 */
var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/salary/query_pager");



    destoryValidator("editWin","editForm");
    destoryValidator("addWin","addForm");
    destoryValidator("importSalaryWin","importSalaryForm");
});

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var salary = selectRow[0];
        initDateTimePicker("datatimepicker","salaryTime","editForm");
        validator("editForm");
        $("#editForm").fill(salary);
        $("#editTime").val(formatterDate(salary.salaryTime));
        $("#editWin").modal('show');
    }
}

function getDate() {
    $("#datatimepicker").val(new Date());
}

function showAddWin() {
    initDateTimePicker("datatimepicker","salaryTime","addForm");
    validator("addForm");
    $(".userId").val("");
    $(".userName").val("");
    $("#addWin").modal('show');
}

function showUserWin(){
    initTableNotTollbar("userTable", "/peopleManage/query_user");
    $("#userWin").modal('show');

}

function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showUpdateIncomingType1 btn btn-primary  btn-sm" style="margin-right:15px;" >编辑</button>'
    ].join('');
}
window.operateEvents = {
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var salary = row;
        validator("editForm");
        initDateTimePicker("datatimepicker","salaryTime","editForm");
        $("#editForm").fill(salary);
        $("#editTime").val(formatterDate(salary.salaryTime));
        $("#editWin").modal('show');
    }
}

function userFormatter(value, row, index) {
    return [
        '<button type="button" class="addUserName btn btn-primary  btn-sm" style="margin-right:15px;" >选择</button>'
    ].join('');
}

window.userEvents = {
    'click .addUserName': function (e, value, row, index) {
        var user = row;
        $(".userId").val(user.userId);
        $(".userName").val(user.userName);
        $("#userWin").modal('hide');
    }
}

function addUserName(){
    var selectRow = $("#userTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一个员工", "error");
        return false;
    } else {
        var user = selectRow[0];
        $(".userId").val(user.userId);
        $(".userName").val(user.userName);
        $("#userWin").modal('hide');
    }
}

/** 根据条件搜索 */
function searchSalary() {
    var userName = $("#userName").val();
    var salaryRange = $("#salaryRange").val();
    if(userName != '' || salaryRange != ''){
        initTable("cusTable", "/salary/query_search?userName=" + userName + "&salaryRange=" + salaryRange);
    }else{
        initTable("cusTable", "/salary/query_pager");
    }
}
/** 关闭搜索的form */
function closeSearchForm() {
    $("#userName").val('');
    $("#salaryRange").val('0');
    $("#searchDiv").hide();
    $("#showButton").show();
}

// 显示导入窗口
function showImport(){
    fileValidator();
    $("#importSalaryWin").modal("show");
}
// 验证添加工资和更新工资
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
            userName: {
                message: '验证失败',
                validators: {
                    notEmpty: {
                        message: '必须选择员工'
                    }
                }
            },
            prizeSalary: {
                validators: {
                    notEmpty: {
                        message: '奖金不能为空'
                    }
                }
            },
            minusSalary: {
                validators: {
                    notEmpty: {
                        message: '罚金不能为空'
                    }
                }
            },
            salaryTime: {
                validators: {
                    notEmpty:{
                        message: '发放时间不能为空'
                    }
                }

            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/salary/add_salary", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/salary/update_salary", formId, "editWin");

            }


        })

}

// 导入按钮导入成功之后禁用
function importSalary() {
    $("#importSalaryForm").data('bootstrapValidator').validate();
    if ($("#importSalaryForm").data('bootstrapValidator').isValid()) {
        $("#importButton").attr("disabled","disabled");
    } else {
        $("#importButton").removeAttr("disabled");
    }
}

// 验证文件格式
function fileValidator() {
    $("#importButton").removeAttr("disabled");
    $('#importSalaryForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            fileSalary: {
                message: '验证失败',
                validators: {
                    notEmpty: {
                        message: '请选择文件'
                    },
                    regexp: {
                        regexp: /\.(?:xls|xlsx)$/,
                        message: '文件格式错误,请导入excel文件'
                    }
                }

            }
        }
    })

        .on('success.form.bv', function (e) {
            $('#importSalaryForm').ajaxSubmit({
                url:'/salary/readExcel',
                type:'post',
                dataType: 'json',
                success: function (data) {
                    if (data.result == "success") {
                        $('#importSalaryWin').modal('hide');
                        swal(data.message, "", "success");
                        $('#cusTable').bootstrapTable('refresh');
                        $('#importSalaryForm').data('bootstrapValidator').resetForm(true);
                    } else if(data.result == "fail"){
                        $('#importSalaryWin').modal('hide');
                        swal(data.message, "内容不匹配", "error");
                        $('#importSalaryForm').data('bootstrapValidator').resetForm(true);

                    } else if (data.result == "notLogin") {
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
                }
            })
        })

}

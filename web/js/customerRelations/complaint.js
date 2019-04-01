


var contextPath = '';

$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable", "/complaint/query_pager");
    destoryValidator("addWin","addForm");
    destoryValidator("editWin","editForm");
    destoryValidator("adminWin","adminForm");
    initSelect2("company", "请选择公司", "/company/company_all", "565");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
});


/** 回复按钮 */
function addReply() {
    $("#adminForm").data('bootstrapValidator').validate();
    if ($("#adminForm").data('bootstrapValidator').isValid()) {
        $("#addAdminButton").attr("disabled","disabled");
    } else {
        $("#addAdminButton").removeAttr("disabled");
    }
}

/** 添加投诉 */
function showAddWin() {
    validator("addForm");
    $("#addWin").modal('show');
}

/** 添加回复 */
function showAdminWin() {
    validator("adminForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "请选择一条投诉记录进行回复", "error");
        return false;
    } else {
        var Reply = selectRow[0];
        $("#adminForm").fill(Reply);
        $("#adminWin").modal('show');
    }
}

/** 编辑投诉内容 */
function showEditContent() {
    validator("editForm");
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "请选择一条投诉记录进行编辑", "error");
        return false;
    } else {
        var complaint = selectRow[0];
        $("#editForm").fill(complaint);
        $('#editCompany').html('<option value="' + complaint.company.companyId + '">' + complaint.company.companyName + '</option>').trigger("change");
        $("#editWin").modal('show');
    }
}



/*表格获取行操作*/
function operateFormatter(value, row, index) {
    return [
        '<button type="button" class="showomplaintReply btn btn-primary  btn-sm" style="margin-right:15px;" >回复</button>'
    ].join('');
}
window.operateEvents = {
    'click .showomplaintReply': function (e, value, row, index) {
        validator("adminForm");
        var Reply = row;

            $("#adminForm").fill(Reply);
            $("#adminWin").modal('show');

    }
}


function validator(formId) {
    $("#addButton").removeAttr("disabled");
    $("#editButton").removeAttr("disabled");
    $("#addAdminButton").removeAttr("disabled");
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            complaintReply:{
                validators:{
                    notEmpty:{
                        message: '回复内容不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 500,
                        message: '回复内容长度必须在2到500位之间'
                    }
                }
            },
            companyId:{
                validators:{
                    notEmpty:{
                        message: '投诉公司不能为空'
                    }
                }
            },
            complaintContent:{
                validators:{
                    notEmpty:{
                        message: '投诉内容不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 500,
                        message: '投诉内容长度必须在2到500位之间'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/complaint/add_customer", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit("/complaint/edit_complaintContent", formId, "editWin");

            } else if (formId == "adminForm") {
                formSubmit("/complaint/add_admin", formId, "adminWin");

            }

        })

}



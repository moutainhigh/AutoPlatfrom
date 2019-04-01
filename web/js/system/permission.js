/**
 * Created by xiao-qiang 2017/4/19.
 */
var contextPath = '';
var ypAll = new Array();
var npAll = new Array();
var roleId2;
var roleObj2;
var moduleId2;
var moduleObj2;
var editPName = "";
var editZhName = "";

function showAduqPermission() {
    $("#allotPermission").hide();
    initTable("cusTable", contextPath + "/permission/query_pager");
    initSelect2("module_all", "选择模块查询", contextPath + "/module/query_all", "150");
    initSelect2("module_all_2", "选择所属模块", contextPath + "/module/query_all", "540");
    $("#search").bind("click", initTable);
    destoryValidator('addWin', 'addForm');
    destoryValidator('editWin', 'editForm');
    $("#aduqPermission").show();
}

function showAllotPermission() {
    $("#aduqPermission").hide();
    $("#allotPermission").show();
}

function selectRole(roleObj, roleId) {
    roleObj2 = roleObj;
    roleId2 = roleId;
    $("#roleType").val($(roleObj2).text());
    if (moduleId2 != null && moduleId2 != "") {
        queryByRoleIdOrModuleId(roleId2, moduleId2);
    }
    $("#selectModule").show();
}

function selectModule(moduleObj, moduleId) {
    moduleObj2 = moduleObj;
    moduleId2 = moduleId;
    $("#moduleType").val($(moduleObj2).text());
    if (roleId2 != null && roleId2 != "") {
        queryByRoleIdOrModuleId(roleId2, moduleId2);
    }
}

function queryByRoleIdOrModuleId(roleId, moduleId) {
    $.get(contextPath + "/permission/roleIdOrModuleId_permission?roleId=" + roleId + "&moduleId=" + moduleId,
        function (data) {
            if (data.length > 0) {
                drawPermission(data);
                $("#permissionDes").show();
            } else {
                $("#permissionY").html("");
                $("#permissionN").html("");
                $("#permissionDes").hide();
            }
        }, "json");
}

function drawPermission(data) {

    var yStr = "";
    var nStr = "";
    var count1 = 0;
    var count2 = 0;
    $.each(data, function (index, item) {
        var str = "'" + data[index].permissionId + "'";
        var info =  "'" + data[index].permissionDes + "'";
        var infoType = '';
        if (data[index].status == 1) {
            infoType = "'Y'";
            npAll[count1] = data[index].permissionId;
            yStr += '<span onclick="delById(' + str + ',this);" onmouseout="hidePermissionInfo(' + infoType + ')" onmouseover="showPermissionInfo(' + info + ', '+ infoType +');" class="label label-success">' + data[index].permissionName + '&nbsp;&nbsp;&nbsp;<i class="fa fa-minus-circle"></i></span>';
            count1++;
        } else if (data[index].status == 0) {
            infoType = "'N'";
            ypAll[count2] = data[index].permissionId;
            nStr += '<span onclick="addById(' + str + ',this);" onmouseout="hidePermissionInfo(' + infoType + ')" onmouseover="showPermissionInfo(' + info + ', '+ infoType +');" class="label label-warning">' + data[index].permissionName + '&nbsp;&nbsp;&nbsp;<i class="fa fa-plus-circle"></i></span>';
            count2++;
        }
    });
    yStr += "<p style='clear:both'></p>";
    nStr += "<p style='clear:both'></p>";
    /*ypAll = ypAll.substring(0, ypAll.length - 1);
     npAll = npAll.substring(0, npAll.length - 1);*/
    $("#permissionY").html(yStr);
    $("#permissionN").html(nStr);
    $("#btnDiv").show();
}

function showPermissionInfo(info, infoType) {
    if (infoType == 'Y') {
        document.getElementById("permissionY").setAttribute("title", info)
        document.getElementById("permissionY").title = info;
    } else if (infoType == 'N') {
        document.getElementById("permissionN").setAttribute("title", info)
        document.getElementById("permissionN").title = info;
    }
}

function hidePermissionInfo(infoType) {
    if (infoType == 'Y') {
        document.getElementById("permissionY").setAttribute("title", "")
        document.getElementById("permissionY").title = "";
    } else if (infoType == 'N') {
        document.getElementById("permissionN").setAttribute("title", "")
        document.getElementById("permissionN").title = "";
    }
}

function addById(permissionId, obj) {
    var str = "" + permissionId;
    $.get(contextPath + "/permission/addByRole_permission?permissionIds=" + str + "&roleId=" + roleId2,
        function (data) {
            if (data.result == "fail") {
                swal("错误提示", data.message, "error");
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
        }, "json");
    selectRole(roleObj2, roleId2);
    selectModule(moduleObj2, moduleId2);
    npAll = new Array();
    ypAll = new Array();
}

function delById(permissionId, obj) {
    var str = "" + permissionId;
    $.get(contextPath + "/permission/delByRole_permission?permissionIds=" + str + "&roleId=" + roleId2,
        function (data) {
            if (data.result == "fail") {
                swal("错误提示", data.message, "error");
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
        }, "json");
    selectRole(roleObj2, roleId2);
    selectModule(moduleObj2, moduleId2);
    npAll = new Array();
    ypAll = new Array();
}

function addAll() {
    if (ypAll.length > 0) {
        $.get(contextPath + "/permission/addByRole_permission?permissionIds=" + ypAll + "&roleId=" + roleId2,
            function (data) {
                if (data.result == "success") {
                    swal("成功提示", data.message, "success");
                } else if (data.result == "fail") {
                    swal("错误提示", data.message, "error");
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
            }, "json");
        selectRole(roleObj2, roleId2);
        selectModule(moduleObj2, moduleId2);
        npAll = new Array();
        ypAll = new Array();
    }
}

function delAll() {
    if (npAll.length > 0) {
        $.get(contextPath + "/permission/delByRole_permission?permissionIds=" + npAll + "&roleId=" + roleId2,
            function (data) {
                if (data.result == "success") {
                    swal("成功提示", data.message, "success");
                } else if (data.result == "fail") {
                    swal("错误提示", data.message, "error");
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
            }, "json");
        selectRole(roleObj2, roleId2);
        selectModule(moduleObj2, moduleId2);
        npAll = new Array();
        ypAll = new Array();
    }
}

function queryAll() {
    initTable("cusTable", contextPath + "/permission/query_pager");
}

function moduleSelect(combox) {
    initTable("cusTable", contextPath + "/permission/module_pager?moduleId=" + combox.value);
}

function queryByStatus(status) {
    initTable("cusTable", contextPath + "/permission/status_pager?status=" + status);
}

function operateFormatter(value, row, index) {
    if (row.permissionStatus == 'Y') {
        return [
            '<button type="button" class="updateActive btn btn-success  btn-sm">冻结</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateInactive btn btn-danger  btn-sm">激活</button>',
            ' <button type="button" class="showEditWin btn btn-primary btn-sm" style="margin-right:15px;">编辑</button>'
        ].join('');
    }
}

window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var status = 'N';
        $.get(contextPath + "/permission/update_status?id=" + row.permissionId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
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
            }, "json");
    },
    'click .updateInactive': function (e, value, row, index) {
        var status = 'Y';
        $.get(contextPath + "/permission/update_status?id=" + row.permissionId + "&status=" + status,
            function (data) {
                if (data.result == "success") {
                    $('#cusTable').bootstrapTable('refresh');
                } else if (data.result == "fail") {
                    swal(data.message, "", "error");
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
            }, "json");
    },
    'click .showEditWin': function (e, value, row, index) {
        var permission = row;
        editPName = permission.permissionName;
        editZhName = permission.permissionZHName;
        $("#editForm").fill(permission);
        $('#moduleSelect3').html('<option value="' + permission.module.moduleId + '">' + permission.module.moduleName + '</option>').trigger("change");
        validator("editForm");
        $("#editWin").modal('show');
    }
}

function showAddWin() {
    validator("addForm");
    $('#moduleSelect2').html('').trigger("change");
    $("input[type=reset]").trigger("click");
    $("#addWin").modal('show');
}

function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length < 1) {
        swal('编辑失败', "必须选择一条数据进行编辑", "error");
        return false;
    } else if (selectRow.length > 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
        return false;
    } else {
        var permission = selectRow[0];
        editPName = permission.permissionName;
        editZhName = permission.permissionZHName;
        $("#editForm").fill(permission);
        $('#moduleSelect3').html('<option value="' + permission.module.moduleId + '">' + permission.module.moduleName + '</option>').trigger("change");
        $("#editWin").modal('show');
        validator("editForm");
    }
}

/** 表单验证 */
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
            moduleId: {
                message: '所属模块验证失败',
                validators: {
                    notEmpty: {
                        message: '所属模块不能为空'
                    }
                }
            },
            permissionName: {
                message: '权限名称验证失败',
                validators: {
                    notEmpty: {
                        message: '权限名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 40,
                        message: '权限名称长度必须在2到40位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_PN?editPName=' + editPName,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            }
            ,
            permissionZHName: {
                message: '权限中文名称验证失败',
                validators: {
                    notEmpty: {
                        message: '权限中文名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: '权限中文名称长度必须在2到30位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: contextPath + '/vilidate/queryIsExist_PZHN?editZhName=' + editZhName,
                        message: '该名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit(contextPath + "/permission/add_permission", formId, "addWin");

            } else if (formId == "editForm") {
                formSubmit(contextPath + "/permission/update_permission", formId, "editWin");
                editPName = "";
                editZhName = "";
            }
        })

}
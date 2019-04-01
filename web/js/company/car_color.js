
var editColorName = "";
function showAddWin(){
    $("#span").css("background-color","");
    $("#addWin").modal('show');
    validator("addForm");
    $("input[type=reset]").trigger("click");
}
$(document).ready(function () {
    //调用函数，初始化表格
    initTable("cusTable","/carColor/queryByPager");
    //当点击查询按钮的时候执行
    $("#search").bind("click", initTable);
    destoryValidator("addWin","addForm");
    destoryValidator("editWin","editForm");
});

/** 关闭搜索的form */
function closeSearchForm() {
    $("#searchColorName").val('');
    $("#searchDiv").hide();
    $("#showButton").show();
}

function searchColor(){
    var colorName = $("#searchColorName").val();
    initTable("cusTable","/carColor/searchByPager?colorName="+colorName);
}

function colorAll(){
    initTable("cusTable","/carColor/queryByPager");
}

/**查询可用*/
function statusUsableness(){
    var status = 'Y';
    initTable("cusTable","/carColor/queryByStatusPager?status="+status);
}

/**查询不可用*/
function statusAvailable(){
    var status = 'N';
    initTable("cusTable","/carColor/queryByStatusPager?status="+status);
}

/** 编辑数据 */
function showEditWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('编辑失败', "只能选择一条数据进行编辑", "error");
    }else{
        var product = selectRow[0];
        $("#spans").css("background",product.colorHex);
        editColorName = product.colorName;
        validator("editForm");
        $("#editForm").fill(product);
        $("#editWin").modal('show');
    }

}

/**
 * 十六进制颜色转换为RGB颜色
 * @param color 要转换的十六进制颜色
 * @return RGB颜色
 */
function colorHexToRGB(color){
    color=color.toUpperCase();
    var regexpHex=/^#[0-9a-fA-F]{3,6}$/;//Hex
    if(regexpHex.test(color)){
        var hexArray=new Array();
        var count=1;
        for(var i=1;i<=3;i++){
            if(color.length-2*i>3-i){
                hexArray.push(Number("0x"+color.substring(count,count+2)));
                count+=2;
            }else{
                hexArray.push(Number("0x"+color.charAt(count)+color.charAt(count)));
                count+=1;
            }
        }
        return hexArray.join(",");
    }else{
        return color;
    }
}

function colorFormatter(value, row, index) {
    return "<span style='display: inline-block; width: 25px; height: 25px; background-color: " + value + ";'></span>";
}

function operating(value, row, index) {
    if (row.colorStatus == 'Y') {
        return [
            '<button type="button" class="updateInactive btn btn-default  btn-sm btn-danger" >冻结</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    } else {
        return [
            '<button type="button" class="updateActive btn btn-default  btn-sm btn-success" >激活</button>&nbsp;&nbsp;',
            '<button type="button" class="showUpdateIncomingType1 btn btn-default btn-sm btn-primary ">编辑</button>'
        ].join('');
    }
}



window.operateEvents = {
    'click .updateActive': function (e, value, row, index) {
        var Status = 'N';
        $.get("/carColor/colorStatusModify?id=" + row.colorId + "&status=" + Status,
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
        var Status = 'Y';
        $.get("/carColor/colorStatusModify?id=" + row.colorId + "&status=" + Status,
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
            }, "json")
    },
    'click .showUpdateIncomingType1': function (e, value, row, index) {
        var incomingType = row;
        editColorName = incomingType.colorName;
        validator("editForm");
        $("#editForm").fill(incomingType);
        $("#spans").css("background",incomingType.colorHex);
        $("#editWin").modal('show');
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
            colorName: {
                message: '颜色名称失败',
                validators: {
                    notEmpty: {
                        message: '颜色名称不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '颜色名称长度必须在2到4位之间'
                    },
                    threshold: 6,
                    remote: {
                        url: '/vilidate/queryIsExist_ColorName?editColorName=' + editColorName,
                        message: '该颜色名称已存在',
                        delay: 2000,
                        type: 'GET'
                    }
                }
            },
            colorDes: {
                message: '颜色描述失败',
                validators: {
                    notEmpty: {
                        message: '颜色描述不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '颜色描述长度必须在1到500位之间'
                    }
                }
            },
            colorHex: {
                message: '颜色Hex失败',
                validators: {
                    notEmpty: {
                        message: '颜色Hex不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '颜色Hex长度必须在1到500位之间'
                    }

                }
            },
            colorRGB: {
                message: '颜色RGB失败',
                validators: {
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '颜色RGB长度必须在1到500位之间'
                    }
                }

            }


        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/carColor/insertCarColor", formId, "addWin");
                editColorName = ""
            } else if (formId == "editForm") {
                formSubmit("/carColor/uploadCarColor", formId, "editWin");
                editColorName =""
            }
        })

}


$('#colorpalette').colorPalette()
    .on('selectColor', function(e) {
        $('#selected-color').val(e.color);
        $('#selected-colorRGB').val(colorHexToRGB(e.color));
        $("#span").css("background-color", e.color);
    });

$('#colorpalette1').colorPalette()
    .on('selectColor', function(e) {
        $('#selected-color1').val(e.color);
        $('#selected-colorRGB1').val(colorHexToRGB(e.color));
        $("#spans").css("background-color", e.color);
    });

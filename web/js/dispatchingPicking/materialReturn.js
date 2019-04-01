/**
 * Created by Xiao-Qiang on 2017/5/15.
 */

var contextPath = '';
$(document).ready(function () {
    var speedStatus = "未提醒,已提醒,已完成"
    initTable("cusTable", contextPath + "/record/pager_speedAndPickingStatus?speedStatus=" + speedStatus);
    initDateTimePickerNotValitor("form_datetime");
    $("#search").bind("click", initTable);
});

function showMRWin() {
    var selectRow = $("#cusTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('错误提示', "请选择一条数据", "error");
        return false;
    } else {
        var record = selectRow[0];
        var recordId = record.recordId;
        initTableSetToolbar("cusTable1", contextPath + "/materialReturn/query_pager?recordId=" + recordId, "toolbar1");
        /*var mrs = $("#cusTable1").bootstrapTable('getData');
        alert(mrs.length);
        if (mrs.length < 1) {
            $("#searchMRWin").html("<h1>当前记录暂无退料信息!</h1>");
        }*/
        $("#searchMRWin").modal('show');
    }
}

function countPrice(value, row, index) {
    return "" + (row.accPrice * row.accCount);
}
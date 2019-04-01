<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>工单查询</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/maintenanceProgress/jquery.step.css" rel="stylesheet" type="text/css">


</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="maintainRecord.checkin.maintainOrFix" >
                维修或保养
            </th>
            <th data-field="maintainRecord.checkin.userName" >
                车主姓名
            </th>
            <th data-field="workCreatedTime" data-formatter="formatterDate" >
                创建时间
            </th>
            <th data-field="workAssignTime"  data-formatter="formatterDate">
                指派时间
            </th>
            <th data-field="user.userName">
                指派员工
            </th>
            <shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
            <th data-field="company.companyName">
                汽修公司
            </th>
            </shiro:hasAnyRoles>
            <th data-field="workStatus" data-formatter="status">
                当前状态
            </th>
            <shiro:hasAnyRoles name="companyAdmin">
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="companyAdmin, companyArtificer, companyReceive">
            <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 指派员工
            </button></a>
            </shiro:hasAnyRoles>
            <a><button onclick="showWork_N();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看不可用
            </button></a>
            <a><button onclick="showWork_Y();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看可用
            </button></a>
            <a><button onclick="showWork();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看全部工单
            </button></a>
        </div>
        </tbody>
    </table>
</div>



<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">指派员工</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="work.workId" name="workId" />
                            <input type="hidden" attr="work.maintainRecord.recordId" name="recordId" />
                            <div class="form-group">
                                <label class="control-label">员工指派：</label>
                                <select id="editUser" class="js-example-tags form-control work_user" name="userId"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">预计维修或保养结束时间：</label>
                                <input type="text" name="endTime" readonly
                                       class="form_datetime form-control"
                                       id="workEndTime">
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <span id="error1" style="color: red;"></span>
                                <br/>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="editWork()" id="editButton" class="btn btn-primary" value="确定"/>
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>



<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/peopleManage/work.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/maintenanceProgress/jquery.step.js"></script>

</body>
</html>

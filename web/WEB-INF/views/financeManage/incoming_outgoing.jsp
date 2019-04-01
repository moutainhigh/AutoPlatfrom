<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>收支管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css">
</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="450"
           data-row-style="rowStyle"
           >
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="incomingType.inTypeName"  data-formatter="isNullName" >
                收入类型
            </th>
            <th data-field="outgoingType.outTypeName" data-formatter="isNullName">
                支出类型
            </th>
            <th data-field="inOutMoney" >
                收支金额
            </th>
            <th data-field="user.userName">
                创建人
            </th>
            <th data-field="inOutCreatedTime" data-formatter="formatterDate">
                创建时间
            </th>
        <shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
            <th data-field="company.companyName">
                所属公司
            </th>
        </shiro:hasAnyRoles>
            <th data-field="inOutStatus" data-formatter="status">
                当前状态
            </th>
            <shiro:hasAnyRoles name="companyAccounting, companyAdmin">
                <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>

        <tbody>

        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="companyAccounting, companyAdmin">
                <a><button onclick="showAddWin()" type="button" id="add" class="btn btn-default" >
                    <i class="glyphicon glyphicon-plus"></i> 添加
                </button></a>
                <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                </button></a>
            </shiro:hasAnyRoles>

            <a><button onclick="queryByInOutType(1);" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看支出
            </button></a>

            <a><button onclick="queryByInOutType(2);" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看收入
            </button></a>

            <a><button onclick="queryByInOutType(3);" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看收支
            </button></a>
            <a>
                <button onclick="searchStatus('/incomingOutgoing/query_status?status=Y');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/incomingOutgoing/query_status?status=N');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>

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
                        <h3 class="m-t-none m-b">修改收支记录</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="incomingOutgoing.inOutId" name="inOutId" />
                            <div class="form-group">
                                <label class="control-label">收支记录金额：</label>
                                <input type="number"   attr="incomingOutgoing.inOutMoney" name="inOutMoney"  class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">

                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" class="btn btn-primary" value="修改" onclick="edit()">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加收支记录</h3>
                        <form role="form" id="addForm" >

                            <div class="form-group" style="width: auto; display: inherit;">
                                <label class="control-label">选择类型：</label>
                                <input type="checkbox" id="isType" name="isType">
                            </div>
                            <div class="form-group" id="outDiv">
                                <label class="control-label">支出类型：</label>
                                <select id="outType" class="js-example-tags form-control outType" name="outTypeId"></select>
                            </div>
                            <div class="form-group" id ="inDiv" style="display: none;">
                                <label class="control-label">收入类型：</label>
                                <select id="inType" class="js-example-tags form-control inType" name="inTypeId"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">收支记录金额：</label>
                                <input type="number"   name="inOutMoney"  class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary" value="添加" onclick="add()">
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
<script src="<%=path%>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/financeManage/incoming-outgoing.js"></script>
<script src="<%=path%>/js/main.js"></script>

</body>
</html>

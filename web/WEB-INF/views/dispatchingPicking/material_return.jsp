<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>退料管理</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"/>
    <link href="<%=path %>/css/main.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="520">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="checkin.userName">
                车主姓名
            </th>
            <th data-field="checkin.carPlate">
                登记车牌号
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="speedStatus">
                当前进度
            </th>
            <th data-field="pickingStatus">
                领料状态
            </th>
        </tr>
        </thead>

        <tbody>
        <div id="toolbar" class="btn-group">
            <a>
                <button onclick="showMRWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>
                    查询退料明细
                </button>
            </a>
        </div>
        </tbody>

    </table>
</div>

<div id="searchMRWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">查看当前记录的退料明细</h3>
                        <table class="table table-hover" id="cusTable1"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="520">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="userName">
                                    车主名
                                </th>
                                <th data-field="userRequests">
                                    车主要求
                                </th>
                                <th data-field="maintainName">
                                    项目名
                                </th>
                                <th data-field="accName">
                                    配件名
                                </th>
                                <th data-field="accCount">
                                    物料数量
                                </th>
                                <th data-field="jisuan" data-formatter="countPrice">
                                    基本费用
                                </th>
                                <th data-field="mrReturnDate" data-formatter="formatterDate">
                                    创建时间
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <div id="toolbar1" class="btn-group">

                            </div>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="<%=path %>/js/dispatchingPicking/materialReturn.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>领料管理</title>
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
            <shiro:hasAnyRoles name="companyAdmin, companyRepertory">
                <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>

        <tbody>
        <div id="toolbar" class="btn-group">
            <a>
                <button onclick="showMUWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>
                    查询领料明细
                </button>
            </a>
            <%--<a>
                <button onclick="achieve();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-ok"></i>
                    确认完成
                </button>
            </a>--%>
        </div>
        </tbody>

    </table>
</div>

<div id="searchMUWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">查看当前记录的领料明细</h3>
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
                                <th data-field="muUseDate" data-formatter="formatterDate">
                                    创建时间
                                </th>
                                <%--<th data-field="isReturn" data-formatter="isRM">
                                    是否退料
                                </th>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <div id="toolbar1" class="btn-group">
                                <a id="showMuWinBtn">
                                    <button onclick="showReturnMaterial()" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-plus"></i> 申请退料
                                    </button>
                                </a>
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
    <div id="editWin" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12 b-r">
                            <h3 class="m-t-none m-b">修改物料数量</h3>
                            <form role="form" id="editForm">
                                <input type="hidden" attr="materialUseInfo.accId" name="accId"/>
                                <div class="form-group">
                                    <label>退料数量：</label>
                                    <input attr="materialUseInfo.accCount" id="returnCount" type="number" name="accCount"
                                           class="form-control"/>
                                </div>

                                <div class="modal-footer" style="overflow:hidden;">
                                    <button onclick="closeEditWin();" type="button" class="btn btn-default">
                                        关闭
                                    </button>
                                    <input type="button" onclick="returnMaterial()" id="editButton" class="btn btn-primary"
                                           value="退料">
                                    </input>
                                </div>
                            </form>
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

<script src="<%=path %>/js/dispatchingPicking/materialUse.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>跟踪回访管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="600">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="checkin.userName">
                车主名:
            </th>
            <th data-field="checkin.userPhone">
                车主电话:
            </th>
            <th data-field="trackContent" >
                回访问题:
            </th>
            <th data-field="serviceEvaluate" >
                服务评分:
            </th>
            <th data-field="admin.userName">
                回访人:
            </th>
            <th data-field="trackCreatedTime" data-formatter="formatterDate">
                回访时间:
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <%--<a><button onclick="showAddWin();" type="button" id="add" class="btn btn-default" >
                <i class="glyphicon glyphicon-plus"></i> 添加
            </button></a>--%>
<shiro:hasAnyRoles name="companyAdmin,companyReceive">
            <a><button onclick="showCustomer();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 添加回访
            </button></a>
    </shiro:hasAnyRoles>
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
                        <h3 class="m-t-none m-b">跟踪回访</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="vistit.checkin.userId" name="complaintId" />
                            <div class="form-group">
                                <label>回访车主：</label>
                                <input type="text" readonly="readonly" attr="vistit.checkin.userName" name="userName"
                                       class="form-control nowDatrtime"/>
                            </div>
                            <div class="form-group">
                                <label>回访问题：</label>
                                <textarea class="form-control" name="trackContent" id="trackContent"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label">回访评分：</label>
                                <input type="text" id="serviceEvaluate" name="serviceEvaluate" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <span id="error1" style="color: red;"></span>
                                <br/>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton1" class="btn btn-primary" value="确定" onclick="updateIncomingType()">
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
                        <h3 class="m-t-none m-b" style="text-align: center;">添加回访记录</h3>
                        <form role="form" id="addForm" >
                        <input type="hidden" id="addCustomerId" name="userId" class="form-control customerId"/>
                            <div class="form-group">
                                <label class="control-label">回访车主名：</label>
                                <input  type="text" id="addCustomer" class="form-control visit_user"/>
                            </div>
                            <div class="form-group">
                                <label>回访问题：</label>
                                <textarea class="form-control" name="trackContent" id="trackContent1"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label">回访评分：</label>
                                <input type="text" id="serviceEvaluate1" name="serviceEvaluate" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <span id="error" style="color: #ff0000;"></span>
                                <br/>
                                <input type="button" id="addButton" class="btn btn-primary" onclick="add()" value="添加">
                                </input>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="reset" name="reset" style="display: none;" />
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="customerWin" class="modal fade" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 85%;height: 60%">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加回访</h3>
                        <table class="table table-hover" id="customerTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="440">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="checkin.userName">
                                    车主名
                                </th>
                                <th data-field="checkin.userPhone">
                                    车主手机号
                                </th>
                                <th data-field="checkin.userRequests">
                                    车主维保要求
                                </th>
                                <th data-field="operate" data-formatter="customerFormatter" data-events="openCustomer">
                                    操作
                                </th>
                            </tr>
                            </thead>
                        </table>
                        <div class="modal-footer" >
                            <input type="button"  style="margin-top: 15px;" onclick="addCustomer()" class="btn btn-primary" value="确定添加">
                            </input>
                            <button type="button" style="margin-top: 15px;" class="btn btn-default"
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
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/customerRelations/track_visit.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:32
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
    <title>短信发送</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="user.userName">
                用户名:
            </th>
            <th data-field="user.userPhone" >
                用户手机号:
            </th>
            <th data-field="sendMsg" >
                短信发送内容:
            </th>
            <th data-field="sendTime" data-formatter="formatterDate">
                短信发送时间:
            </th>
            <th data-field="company.companyName">
                所属公司
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
<shiro:hasAnyRoles name="companyAdmin,companyReceive">
            <a><button onclick="showCustomer()" type="button" id="add" class="btn btn-default" >
                <i class="glyphicon glyphicon-plus"></i> 添加用户
            </button></a>
            <a><button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 发送内容
            </button></a>
            <a><button onclick="sendSuccess();" type="button" id="All" class="btn btn-default">
                <i class="glyphicon glyphicon-pencil"></i> 一键短信发送
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
                        <h3 class="m-t-none m-b">发送短信内容</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="messageSend.messageId" name="messageId" />
                            <div class="form-group">
                                <label class="control-label">短信内容：</label>
                                <textarea class="form-control" type="textarea" attr="messageSend.sendMsg" id="sendMsg" name="sendMsg"
                                          maxlength="400" rows="4">独在异乡为异客，每逢佳节“粽”思亲。遥知兄弟登高处，万水千山“粽”是情。 端午节，要吃“粽”，祝您“粽”横四海，“粽”是走运!端午节快乐! 端午节平台有惊喜，详情电话咨询，客服小二</textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" onclick="edit()" class="btn btn-primary"
                                       value="确定">
                                </input>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </div>
</div>
</div>



<div id="customerWin" class="modal fade">
    <div class="modal-dialog" style="width: 85%;height: 60%">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加用户</h3>
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
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/customerRelations/message_send.js"></script>
</body>
</html>

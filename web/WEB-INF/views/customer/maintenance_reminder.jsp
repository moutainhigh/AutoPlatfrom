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
    <title>维修保养提醒</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
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
                用户名:
            </th>
            <th data-field="lastMaintainTime" data-formatter="formatterDate">
                上次保养时间
            </th>
            <th data-field="checkin.carMileage">
                上次保养行驶里程
            </th>
            <th data-field="remindMsg">
                提醒内容
            </th>
            <th data-field="remindTime" data-formatter="formatterDate">
                提醒时间
            </th>
            <th data-field="remindType" >
                提醒方式
            </th>
            <th data-field="remindCreatedTime" data-formatter="formatterDate">
                提醒记录创建时间
            </th>
            <shiro:hasAnyRoles name="companyAdmin,companyReceive">
                <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>

        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchUserName" name="userName" class="form-control" placeholder="请输入车主姓名">
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control" id="searchRemindType" name="searchRemindType">
                        <option value="all">提醒方式</option>
                        <option value="短信提醒">短信提醒</option>
                        <option value="邮箱提醒">邮箱提醒</option>

                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="button" onclick="searchCondition()" class="btn btn-primary">
                        查询
                    </button>
                    <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                        重置
                    </button>
                </div>
            </div>
        </form>

    </table>
</div>



<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b"style="text-align: center">修改维修保养提醒</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="reminder.remindId" name="remindId" />
                            <div class="form-group">
                                <label class="control-label">保养提醒时间：</label>
                                <input type="text" readonly="readonly" attr="reminder.remindTime" id="editRemindTime" name="remindTime" class="form-control nowDatrtime"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">保养提醒方式：</label>
                                <select class="js-example-tags form-control" name="remindType">
                                    <option value="短信提醒" selected="selected">短信提醒</option>
                                    <option value="邮箱提醒">邮箱提醒</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">保养提醒内容：</label>
                                <textarea class="form-control" attr="reminder.remindMsg" type="textarea" name="remindMsg" maxlength="400"
                                          rows="3"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" onclick="edit()" class="btn btn-primary" value="修改">
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
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
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
<script src="<%=path %>/js/customerRelations/message_reminder.js"></script>
</body>
</html>

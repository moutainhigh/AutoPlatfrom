<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:22
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
    <title>查看已完成的维修保养记录</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">

    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
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
            <th data-field="user"  data-formatter="formatterUser">
                是否注册用户
            </th>
            <th data-field="checkin.carMileage">
                汽车行驶里程
            </th>
            <th data-field="startTime" data-formatter="formatterDate">
                维修保养开始时间
            </th>
            <th data-field="endTime" data-formatter="formatterDate">
                预估结束时间
            </th>
            <th data-field="actualEndTime" data-formatter="formatterDate">
                实际结束时间
            </th>
            <th data-field="recordCreatedTime" data-formatter="formatterDate">
                创建记录时间
            </th>
            <th data-field="pickupTime" data-formatter="formatterDate">
                提车时间
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="speedStatus">
                当前进度
            </th>
            <th data-field="trackStatus" data-formatter="formatterTrack">
                是否回访
            </th>
            <th data-field="recordDes">
                记录描述
            </th>
            <th data-field="company.companyName">
                汽修公司
            </th>
            <th data-field="recordStatus" data-formatter="status">
                记录状态
            </th>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchUserName" name="userName" class="form-control" placeholder="请输入车主姓名">
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchCarPlate" name="carPlate" class="form-control" placeholder="请输入车牌号码">
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control" id="searchMaintainOrFix" name="maintainOrFix">
                        <option value="all">维修&nbsp;&&nbsp;保养</option>
                        <option value="维修">维修</option>
                        <option value="保养">保养</option>
                    </select>
                </div>
                <shiro:hasAnyRoles name="systemOrdinaryAdmin, systemSuperAdmin">
                    <div class="col-sm-2">
                        <select class="js-example-tags form-control company" id="searchCompanyId" name="comanyId">
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div class="col-sm-2">
                    <button type="button" onclick="searchCondition()" class="btn btn-primary">
                        查询
                    </button>
                    <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                        关闭
                    </button>

                </div>
            </div>
        </form>
        <tbody>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="companyAdmin, companyReceive">
                <a>
                    <button onclick="showRemindWin()" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-envelope"></i> 提车提醒
                    </button>
                </a>

                <a>
                    <button onclick="showAllRemindWin()" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-envelope"></i> 全部提醒
                    </button>
                </a>

                <a>
                    <button onclick="showAddWin()" id="settlementButton" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-ok"></i> 结算提车
                    </button>
                </a>
            </shiro:hasAnyRoles>
            <a>
                <button onclick="alreadyRemind()" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 已提醒
                </button>
            </a>

            <a>
                <button onclick="notRemind()" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 未提醒
                </button>
            </a>

            <a>
                <button onclick="alreadyComplete()" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 已完成
                </button>
            </a>

            <a>
                <button onclick="allRemind()" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 全部
                </button>
            </a>

            <a>
                <button onclick="showSearchForm()" id="showButton" type="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-search"></i> 条件查询
                </button>
            </a>

        </div>
        </tbody>

    </table>
</div>

<div id="addWin" class="modal fade" style="overflow:scroll" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">结算提车</h3>
                        <form role="form" id="addForm">
                            <input type="hidden" id="addRecordId" attr="record.recordId" name="recordId"
                                   class="form-control"/>
                            <input type="hidden" id="addCheckinId" attr="record.checkin.checkinId"
                                   name="record.checkinId" class="form-control"/>
                            <input type="hidden" attr="record.checkin.userId" name="userId" class="form-control"/>
                            <input type="hidden" attr="record.checkin.carMileage" name="carMileage"
                                   class="form-control"/>
                            <input type="hidden" attr="record.checkin.checkinId" name="checkinId"
                                   class="form-control"/>
                            <input type="text" attr="record.checkin.maintainOrFix" name="maintainOrFix"
                                   class="form-control"/>
                            <div class="form-group">
                                <label class="control-label">车主姓名：</label>
                                <input readonly type="text" attr="record.checkin.userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">收费总金额（元）：</label>
                                <input id="addChargeBillMoney" readonly type="text" name="chargeBillMoney" maxlength="5"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">付款方式：</label>
                                <select class="js-example-tags form-control" id="addPaymentMethod" name="paymentMethod">
                                    <option value="现金">现金</option>
                                    <option value="支付宝">支付宝</option>
                                    <option value="微信">微信</option>
                                    <option value="刷卡">刷卡</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">实付款（元）：</label>
                                <input id="addActualPayment" type="text" name="actualPayment" maxlength="5"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">收费时间：</label>
                                <input id="addChargeTime" readonly onclick="getDate()" type="text" name="chargeTime"
                                       class="form-control datetimepicker"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">收费描述：</label>
                                <textarea class="form-control" name="chargeBillDes"
                                          rows="3"></textarea>
                            </div>


                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" onclick="add()" class="btn btn-primary" value="结算">
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

<div id="remindWin" class="modal fade" style="overflow:scroll" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">提车提醒</h3>
                        <form role="form" id="remindForm">
                            <input type="hidden" id="remindUserId" name="userId" class="form-control"/>
                            <input type="hidden" id="remindRecordId" name="recordId" class="form-control"/>
                            <input type="hidden" id="remindCarPlate" name="carPlate" class="form-control"/>
                            <div class="form-group">
                                <label class="control-label">提醒方式：</label>
                                <select id="remindMethod" class="js-example-tags form-control remindMethod" name="remindMethod">
                                    <option value="message">短信</option>
                                    <option value="email">邮箱</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">提醒标题：</label>
                                <input type="text" id="remindTitle" name="remindTitle" class="form-control"/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="remindButton"
                                       onclick="buttonStatus('remindForm', 'remindButton')" class="btn btn-primary"
                                       value="发送提醒">
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
<script src="<%=path %>/js/settlementCar/car_reminder.js"></script>
</body>
</html>

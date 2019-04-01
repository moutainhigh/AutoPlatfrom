<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>我的维修保养进度</title>

    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/maintenanceProgress/jquery.step.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/maintenanceProgress/style.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/style.min862f.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="checkin.userName">
                车主姓名
            </th>
            <th data-field="checkin.carPlate">
                登记车牌号
            </th>
            <th data-field="startTime" data-formatter="formatterDate">
                维修保养开始时间
            </th>
            <th data-field="actualEndTime" data-formatter="formatterDate">
                完成时间
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="company.companyName">
                汽修公司
            </th>
            <th data-field="recordStatus" data-formatter="status">
                记录状态
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
<%--            <a>
                <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                </button>
            </a>--%>
            <a>
                <button onclick="searchStatus('/record/pager?status=Y');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/record/pager?status=N');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
        </div>
        </tbody>

    </table>
</div>

<%--<div id="editWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改维修保养记录</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="record.recordId" name="recordId" class="form-control"/>
                            <input type="hidden" attr="record.checkin.checkinId" name="checkinId" class="form-control"/>
                            <input type="hidden" attr="record.trackStatus" name="trackStatus" class="form-control"/>
                            <div class="form-group">
                                <label>开始时间：</label>
                                <input id="editStartTime" readonly type="text" name="startTime"
                                       class="form-control datetimepicker"/>
                            </div>

                            <div class="form-group">
                                <label>预估结束时间：</label>
                                <input id="editEndTime" readonly type="text" name="endTime"
                                       class="form-control datetimepicker"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">记录描述：</label>
                                <textarea class="form-control" attr="record.recordDes" type="textarea" name="recordDes"
                                          rows="3"></textarea>
                            </div>


                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="buttonStatus('editForm', 'editButton')" id="editButton" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="searchDetailWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-body" style="padding: 0px;">
                <div class="row">
                    <div class="col-sm-12">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">维修保养进度</h3>
                        <div class="step-header">
                            <ul>
                                <li>
                                    <img src="<%=path%>/img/order.png">
                                    <p class="app">维修保养登记</p>
                                </li>
                                <div class="sk-spinner sk-spinner-three-bounce" style="float: left;margin: 40px auto;">
                                    <div class="sk-bounce1"></div>
                                    <div class="sk-bounce2"></div>
                                    <div class="sk-bounce3"></div>
                                </div>
                                <li>
                                    <img src="<%=path%>/img/anouncement.png">
                                    <p class="des">指派员工</p>
                                </li>
                                <div class="load" style="float: left;margin: 40px auto;">
                                    <div class="load_1"></div>
                                    <div class="load_2"></div>
                                    <div class="load_3"></div>
                                </div>
                                <li>
                                    <img src="<%=path%>/img/car_repair.png">
                                    <p class="des">开始维修保养</p>
                                </li>
                                <div class="load" style="float: left;margin: 40px auto;">
                                    <div class="load_1"></div>
                                    <div class="load_2"></div>
                                    <div class="load_3"></div>
                                </div>
                                <li>
                                    <img src="<%=path%>/img/phone_icon_by_cemagraphics.png">
                                    <p class="des">通知提车</p>
                                </li>
                                <div class="load" style="float: left;margin: 40px auto;">
                                    <div class="load_1"></div>
                                    <div class="load_2"></div>
                                    <div class="load_3"></div>
                                </div>
                                <li>
                                    <img src="<%=path%>/img/cash_register.png">
                                    <p class="des">结算金额</p>
                                </li>
                                <div class="load" style="float: left;margin: 40px auto;">
                                    <div class="load_1"></div>
                                    <div class="load_2"></div>
                                    <div class="load_3"></div>
                                </div>
                                <li>
                                    <img src="<%=path%>/img/Ok.png">
                                    <p class="des">完成车辆维修保养</p>
                                </li>
                            </ul>
                        </div>
                        <div style="height: 25%;border-top: 1px solid #e5e5e5;"></div>
                        &lt;%&ndash;<div class="tree">&ndash;%&gt;
                        &lt;%&ndash;<ul>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span class="badge badge-important">车主：${requestScope.maintainRecord}</span>&ndash;%&gt;
                        &lt;%&ndash;<ul>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span class="badge badge-success"><i class="icon-minus-sign"></i>2017-4-26&nbsp;&nbsp;周三</span>&ndash;%&gt;
                        &lt;%&ndash;<ul>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;</ul>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span class="badge badge-success"><i class="icon-minus-sign"></i>2017-4-27&nbsp;&nbsp;周三</span>&ndash;%&gt;
                        &lt;%&ndash;<ul>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;<li>&ndash;%&gt;
                        &lt;%&ndash;<span>拆除了一个轮胎</span>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;</ul>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;</ul>&ndash;%&gt;
                        &lt;%&ndash;</li>&ndash;%&gt;
                        &lt;%&ndash;</ul>&ndash;%&gt;
                        &lt;%&ndash;</div>&ndash;%&gt;
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="editDetailWin" style="overflow:scroll" class="modal fade" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改维修保养记录</h3>
                        <form role="form" id="editDetailForm">
                            <input type="hidden" attr="detail.detailId" name="detailId" class="form-control"/>
                            <input type="hidden" attr="detail.record.recordId" name="recordId" class="form-control"/>
                            <div class="form-group">
                                <label>车主姓名：</label>
                                <input readonly type="text" attr="detail.record.checkin.userName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>车主电话：</label>
                                <input readonly type="text" attr="detail.record.checkin.userPhone" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">维修保养项目：</label>
                                <select id="editDetailMaintain" class="js-example-tags form-control maintain_fix" name="maintainId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">折扣&nbsp;|&nbsp;减价：</label>
                                <input type="text" maxlength="4" attr="detail.maintainDiscount" name="maintainDiscount" class="form-control"/>
                                <span style="font-size: 12px; color: green;">小于1大于0是折扣，大于等于1则是减价</span>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" onclick="closeEditDetailWin()">关闭
                                </button>
                                <input type="button" onclick="buttonStatus('editDetailForm', 'editDetailButton')" id="editDetailButton" class="btn btn-primary" value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>--%>


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
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
<script src="<%=path %>/js/maintenanceProgress/jquery.step.js"></script>
<script src="<%=path %>/js/maintenanceProgress/progress.js"></script>

</body>
</html>

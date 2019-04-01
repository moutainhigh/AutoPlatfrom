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
    <title>车辆维修保养进度管理</title>


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
                维修保养完成时间
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <shiro:hasAnyRoles name="carOwner">
            <th data-field="company.companyName">
                所属公司
            </th>
            </shiro:hasAnyRoles>
            <th data-field="speedStatus">
                进度状态
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
            <a>
                <button onclick="searchStatus_Y();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用进度
                </button>
            </a>
            <a>
                <button onclick="searchStatus_N();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用进度
                </button>
            </a>
            <a>
                <button onclick="searchStatus_All();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看全部进度
                </button>
            </a>
            <shiro:hasAnyRoles name="companyAdmin, companyArtificer">
            <a>
                <button onclick="recordOk();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-ok"></i> 维修或保养完成
                </button>
            </a>
            </shiro:hasAnyRoles>
        </div>
        </tbody>
    </table>
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
                                    <li id="textConduct">
                                        <img src="<%=path%>/img/order.png">
                                        <p class="conduct">维修保养登记</p>
                                    </li>
                                        <div id="stopAnimation_1" class="load_color" style="float: left;margin: 40px auto;">
                                            <div class="load_1"></div>
                                            <div class="load_2"></div>
                                            <div class="load_3"></div>
                                        </div>
                                        <div id="animation_1" class="sk-spinner sk-spinner-three-bounce" style="float: left;margin: 40px auto;">
                                            <div class="sk-bounce1"></div>
                                            <div class="sk-bounce2"></div>
                                            <div class="sk-bounce3"></div>
                                        </div>
                                    <li>
                                        <img src="<%=path%>/img/anouncement.png">
                                        <p id="text_1" class="des">指派员工</p>
                                    </li>
                                    <div id="stopAnimation_2" class="load" style="float: left;margin: 40px auto;">
                                        <div class="load_1"></div>
                                        <div class="load_2"></div>
                                        <div class="load_3"></div>
                                    </div>
                                    <li>
                                        <img src="<%=path%>/img/car_repair.png">
                                        <p id="text_2" class="des">开始维修保养</p>
                                    </li>
                                    <div id="stopAnimation_3" class="load" style="float: left;margin: 40px auto;">
                                        <div class="load_1"></div>
                                        <div class="load_2"></div>
                                        <div class="load_3"></div>
                                    </div>
                                    <div id="animation_2" class="sk-spinner sk-spinner-three-bounce" style="float: left;margin: 40px auto;">
                                        <div class="sk-bounce1"></div>
                                        <div class="sk-bounce2"></div>
                                        <div class="sk-bounce3"></div>
                                    </div>
                                    <li>
                                        <img src="<%=path%>/img/phone_icon_by_cemagraphics.png">
                                        <p id="text_3" class="des">通知提车</p>
                                    </li>
                                    <div id="stopAnimation_4" class="load" style="float: left;margin: 40px auto;">
                                        <div class="load_1"></div>
                                        <div class="load_2"></div>
                                        <div class="load_3"></div>
                                    </div>
                                    <div id="animation_3" class="sk-spinner sk-spinner-three-bounce" style="float: left;margin: 40px auto;">
                                        <div class="sk-bounce1"></div>
                                        <div class="sk-bounce2"></div>
                                        <div class="sk-bounce3"></div>
                                    </div>
                                    <li>
                                        <img src="<%=path%>/img/cash_register.png">
                                        <p id="text_4" class="des">结算金额</p>
                                    </li>
                                    <div id="stopAnimation_5" class="load" style="float: left;margin: 40px auto;">
                                        <div class="load_1"></div>
                                        <div class="load_2"></div>
                                        <div class="load_3"></div>
                                    </div>
                                    <div id="animation_4" class="sk-spinner sk-spinner-three-bounce" style="float: left;margin: 40px auto;">
                                        <div class="sk-bounce1"></div>
                                        <div class="sk-bounce2"></div>
                                        <div class="sk-bounce3"></div>
                                    </div>
                                    <li>
                                        <img src="<%=path%>/img/Ok.png">
                                        <p id="text_5" class="des">完成车辆维修保养</p>
                                    </li>
                                </ul>
                        </div>
                        <div style="height: 23%;border-top: 1px solid #e5e5e5; text-align: center;color: #00bcd4;">
                            <h1 id="des_1">维修保养登记中</h1>
                        </div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
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
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
<script src="<%=path %>/js/maintenanceProgress/progress.js"></script>

</body>
</html>

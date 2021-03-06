<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>接待登记管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css">

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
            <th data-field="userName">
                车主姓名
            </th>
            <th data-field="userPhone">
                车主电话
            </th>
            <th data-field="brand.brandName">
                汽车品牌
            </th>
            <th data-field="color.colorName">
                汽车颜色
            </th>
            <th data-field="model.modelName">
                汽车车型
            </th>
            <th data-field="plate.plateName">
                汽车车牌
            </th>
            <th data-field="carPlate">
                车牌号码
            </th>
            <th data-field="oilCount">
                汽车油量（L）
            </th>
            <th data-field="carMileage">
                汽车行驶里程（KM）
            </th>
            <th data-field="carWash" data-formatter="carWash">
                是否洗车
            </th>
            <th data-field="arriveTime" data-formatter="formatterDate">
                到店时间
            </th>
            <th data-field="carThings">
                车上物品描述
            </th>
            <th data-field="intactDegrees">
                汽车完好度描述
            </th>
            <th data-field="userRequests">
                用户要求描述
            </th>
            <th data-field="maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="checkinCreatedTime" data-formatter="formatterDate">
                登记时间
            </th>
            <th data-field="company.companyName">
                汽修公司
            </th>
            <th data-field="checkinStatus" data-formatter="status">
                记录状态
            </th>
            <shiro:hasAnyRoles name="companyAdmin, companyReceive">
                <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchUserName" name="userName" class="form-control" placeholder="请输入车主姓名">
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchUserPhone" name="userPhone" class="form-control" placeholder="请输入车主电话">
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
                    <button type="button" onclick="searchCheckin()" class="btn btn-primary">
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
                    <button onclick="showAddWin();" type="button" id="add" class="btn btn-default">
                        <i class="glyphicon glyphicon-plus"></i> 添加
                    </button>
                </a>
                <a>
                    <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                        <i class="glyphicon glyphicon-pencil"></i> 修改
                    </button>
                </a>
            </shiro:hasAnyRoles>
            <a>
                <button onclick="searchStatus('/checkin/checkin_pager?status=Y');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/checkin/checkin_pager?status=N');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/checkin/checkin_pager?status=ALL');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看全部
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


<div id="editWin" style="overflow:scroll" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改登记</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="checkin.checkinId" name="checkinId" class="form-control"/>
                            <input type="hidden" attr="checkin.userId" name="userId" class="form-control"/>
                            <input type="hidden" attr="checkin.appointmentId" name="appointmentId"
                                   class="form-control"/>
                            <div class="form-group">
                                <label class="control-label">车主姓名：</label>
                                <input type="text" readonly attr="checkin.userName" maxlength="4" name="userName"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">车主电话：</label>
                                <input type="text" readonly attr="checkin.userPhone" maxlength="11" name="userPhone"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车品牌：</label>
                                <select id="editCarBrand" class="js-example-tags form-control car_brand"
                                        onchange="editCheckBrand(this)" name="brandId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车车型：</label>
                                <select id="editCarModel" class="js-example-tags form-control car_model" name="modelId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车颜色：</label>
                                <select id="editCarColor" class="js-example-tags form-control car_color" name="colorId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label>汽车车牌：</label>
                                <select id="editCarPlate" class="js-example-tags form-control car_plate" name="plateId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">车牌号码：</label>
                                <input type="text" attr="checkin.carPlate" maxlength="5" name="carPlate"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">到店时间：</label>
                                <input id="editDatetimepicker" readonly type="text" name="arriveTime"
                                       class="form-control datetimepicker"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">是否需要洗车：</label>
                                <select class="js-example-tags form-control" attr="checkin.carWash" type="select-one"
                                        name="carWash">
                                    <option value="N">否</option>
                                    <option value="Y">是</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车油量（L）：</label>
                                <input type="text" attr="checkin.oilCount" maxlength="3" name="oilCount"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车行驶里程（km）：</label>
                                <input type="text" attr="checkin.carMileage" maxlength="6" name="carMileage"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">车上物品描述：</label>
                                <textarea class="form-control" attr="checkin.carThings" type="textarea" name="carThings"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车完好度描述：</label>
                                <textarea class="form-control" attr="checkin.intactDegrees" type="textarea"
                                          name="intactDegrees"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">用户要求描述：</label>
                                <textarea class="form-control" attr="checkin.userRequests" type="textarea"
                                          name="userRequests"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">保养&nbsp;|&nbsp;维修：</label>
                                <select id="editMaintainOrFix" attr="checkin.maintainOrFix" type="select-one"
                                        class="js-example-tags form-control" name="maintainOrFix">
                                    <option value="保养">保养</option>
                                    <option value="维修">维修</option>
                                </select>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" onclick="edit()" id="editButton" class="btn btn-primary"
                                       value="修改">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="addWin" class="modal fade" style="overflow:scroll" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加记录</h3>
                        <div class="form-group" id="appDiv">
                            <label>是否预约：</label>
                            <input type="checkbox" id="isApp" name="isApp" onchange="isAppChoice()">

                        </div>
                        <div class="form-group" id="userDiv">
                            <label>是否注册车主：</label>
                            <input type="checkbox" id="choiceUser" name="user" onchange="isUserChoice()">
                        </div>
                        <form role="form" id="addForm">
                            <input type="hidden" id="addUserId" name="userId" class="form-control"/>
                            <input type="hidden" id="addAppointmentId" name="appointmentId" class="form-control"/>

                            <div class="form-group">
                                <label class="control-label">车主姓名：</label>
                                <input type="text" id="addUserName" maxlength="4" name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">车主电话：</label>
                                <input type="text" id="addUserPhone" maxlength="11" name="userPhone"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车品牌：</label>
                                <select id="addCarBrand" class="js-example-tags form-control car_brand"
                                        onchange="checkBrand(this)" name="brandId">
                                </select>
                            </div>

                            <div class="form-group" id="carModelDiv" style="display: none;">
                                <label class="control-label">汽车车型：</label>
                                <select id="addCarModel" class="js-example-tags form-control car_model" name="modelId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车颜色：</label>
                                <select id="addCarColor" class="js-example-tags form-control car_color" name="colorId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车车牌：</label>
                                <select id="addCarPlate" class="js-example-tags form-control car_plate" name="plateId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">车牌号码：</label>
                                <input id="addCarPlateNumber" type="text" name="carPlate" maxlength="5"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">到店时间：</label>
                                <input id="addDatetimepicker" readonly onclick="getDate()" type="text" name="arriveTime"
                                       class="form-control datetimepicker"/>

                            </div>
                            <div class="form-group">
                                <label class="control-label">是否需要洗车：</label>
                                <select class="js-example-tags form-control" name="carWash">
                                    <option value="N">否</option>
                                    <option value="Y">是</option>
                                </select>

                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车油量（L）：</label>
                                <input type="text" name="oilCount" maxlength="3" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车行驶里程（km）：</label>
                                <input type="text" name="carMileage" maxlength="6" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">车上物品描述：</label>
                                <textarea class="form-control" name="carThings"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车完好度描述：</label>
                                <textarea class="form-control" name="intactDegrees"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">用户要求描述：</label>
                                <textarea class="form-control" name="userRequests"
                                          rows="3"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">保养&nbsp;|&nbsp;维修：</label>
                                <select id="addMaintainOrFix" class="js-example-tags form-control" name="maintainOrFix">
                                    <option value="保养">保养</option>
                                    <option value="维修">维修</option>
                                </select>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" onclick="add()" class="btn btn-primary" value="添加">
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

<div id="appWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" onclick="closeAppWin()"></span>
                        <h3 class="m-t-none m-b">选择预约记录</h3>
                        <table class="table table-hover" id="appTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="tater" data-checkbox="true"></th>
                                <th data-field="userName">
                                    车主姓名
                                </th>
                                <th data-field="userPhone">
                                    车主电话
                                </th>
                                <th data-field="brand.brandName">
                                    汽车品牌
                                </th>
                                <th data-field="color.colorName">
                                    汽车颜色
                                </th>
                                <th data-field="model.modelName">
                                    汽车车型
                                </th>
                                <th data-field="plate.plateName">
                                    汽车车牌
                                </th>
                                <th data-field="carPlate">
                                    车牌号码
                                </th>
                                <th data-field="arriveTime" data-formatter="formatterDate">
                                    预计到店时间
                                </th>
                                <th data-field="maintainOrFix">
                                    维修&nbsp;|&nbsp;保养
                                </th>
                                <th data-field="appCreatedTime" data-formatter="formatterDate">
                                    预约创建时间
                                </th>
                                <th data-field="company.companyName">
                                    汽修公司
                                </th>
                                <th data-field="appoitmentStatus" data-formatter="status">
                                    预约状态
                                </th>
                                <th data-field="speedStatus">
                                    当前进度
                                </th>
                                <th data-field="caozuo" data-formatter="formatterChoiceApp" data-events="operateEvents">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 50px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default" onclick="closeAppWin()">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="checkApp()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="userWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" onclick="closeUserWin()"></span>
                        <h3 class="m-t-none m-b">选择车主信息</h3>
                        <table class="table table-hover" id="userTable"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="500">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="userNickname">
                                    昵称
                                </th>
                                <th data-field="userName">
                                    姓名
                                </th>
                                <th data-field="userEmail">
                                    邮箱
                                </th>
                                <th data-field="userGender" data-formatter="gender">
                                    性别
                                </th>
                                <th data-field="userPhone">
                                    手机号
                                </th>
                                <th data-field="userStatus" data-formatter="status">
                                    当前状态
                                </th>
                                <th data-field="caozuo" data-formatter="formatterChoiceUser"
                                    data-events="operateEvents">
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div style="height: 50px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default" onclick="closeUserWin()">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="choiceUser()" value="确定">
                            </input>
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
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/maintenanceReception/reception_register.js"></script>

</body>
</html>

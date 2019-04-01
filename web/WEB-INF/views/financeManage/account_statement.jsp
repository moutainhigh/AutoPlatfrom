
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>对账单管理</title>

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
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="record.checkin.userName">
                车主姓名
            </th>
            <th data-field="record.checkin.userPhone">
                车主手机号
            </th>
            <th data-field="chargeBillMoney">
                收费总金额
            </th>
            <th data-field="actualPayment">
                实付金额
            </th>
            <th data-field="paymentMethod">
                付款方式
            </th>
            <th data-field="chargeTime" data-formatter="formatterDate">
                收费时间
            </th>
            <th data-field="chargeCreatedTime" data-formatter="formatterDate">
                收费单据创建时间
            </th>
            <th data-field="chargeBillDes">
                收费单据描述
            </th>
        <shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
            <th data-field="record.company.companyName">
                所属公司
            </th>
        </shiro:hasAnyRoles>
            <th data-field="chargeBillStatus" data-formatter="status">
                单据状态
            </th>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchUserName" name="userName" class="form-control" placeholder="请输入车主姓名">
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchUserPhone" name="userPhone" class="form-control" placeholder="请输入车主手机号">
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control" id="searchPaymentMethod">
                        <option value="all">选择付款方式</option>
                        <option value="现金">现金</option>
                        <option value="支付宝">支付宝</option>
                        <option value="微信">微信</option>
                        <option value="刷卡">刷卡</option>
                    </select>
                </div>
                <div class="col-sm-2">
                </div>
                <div class="col-sm-2">
                </div>
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
            <a>
                <button onclick="showChargeBillWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-print"></i> 打印收费单据
                </button>
            </a>

            <a>
                <button onclick="searchStatus('/bill/pager?status=Y');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/bill/pager?status=N');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/bill/pager?status=ALL');" type="button" class="btn btn-default">
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


<div id="chargeBillWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row" style="position: relative;">
                    <div id="signDiv" style="background: url('/img/materialsFlag1.png')-25px -25px no-repeat;position: absolute;z-index:999;width: 250px;height: 250px;background-size:250px;display: block;"></div>
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">

                        </h3>
                        <%@ include file="../common/print.jsp" %>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                            <button type="button" class="btn btn-info" onclick="printChargeBill()">
                                <i class="glyphicon glyphicon-print"></i> 打印
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
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=path %>/js/jquery.jqprint-0.3.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/settlementCar/charge_document.js"></script>
</body>
</html>

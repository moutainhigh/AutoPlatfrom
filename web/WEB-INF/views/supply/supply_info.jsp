<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>供应商信息管理</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
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
            <th data-field="id" data-checkbox="true"></th>
            <th  data-field="supplyName" data-sortable="true">
                名称
            </th>
            <th data-field="supplyTel" >
                电话
            </th>
            <th data-field="supplyPricipal" >
                负责人
            </th>
            <th data-field="supplyAddress" data-width="200">
                地址
            </th>
            <th data-field="supplyBank" >
                开户银行
            </th>
            <th data-field="supplyBankAccount" >
                开户人
            </th>
            <th data-field="supplyBankNo" >
                开户卡号
            </th>
            <th data-field="supplyAlipay" >
                支付宝
            </th>
            <th data-field="supplyWechat" >
                微信
            </th>
            <th data-field="supplyType.supplyTypeName">
                供应商分类
            </th>
            <th data-field="company.companyName">
                所属公司
            </th>
            <th data-field="supplyCreatedTime" data-formatter="formatterDate" >
                创建时间
            </th>

            <th data-field="supplyStatus" data-formatter="status" >
                状态
            </th>
            <shiro:hasRole name="companyAdmin">
                <th data-field="operation" data-formatter="operateFormatter" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasRole>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group col-sm-12" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchSupplyName" name="supplyName" class="form-control" placeholder="供应商名称" >
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchSupplyPricipal" name="supplyPricipal" class="form-control" placeholder="供应商负责人" >
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control supplyType" id="searchSupplyTypeId" name="supplyTypeId">
                    </select>
                </div>
                <shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
                    <div class="col-sm-2">
                        <select class="js-example-tags form-control company" id="searchCompanyId" name="comanyId">
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div class="col-sm-2">
                    <button type="button" onclick="searchSupply()" class="btn btn-primary">
                        查询
                    </button>
                    <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                        关闭
                    </button>
                    <input type="reset" name="reset" style="display: none;"/>
                </div>
            </div>
        </form>
        <tbody>
        <div id="toolbar" class="btn-group">
        <shiro:hasRole name="companyAdmin">
                <a>
                    <button onclick="showAddWin();" type="button" id="add" class="btn btn-default" >
                        <i class="glyphicon glyphicon-plus"></i> 添加
                    </button>
                </a>
                <a>
                    <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                        <i class="glyphicon glyphicon-pencil"></i> 修改
                    </button>
                </a>
            </shiro:hasRole>
            <a>
                <button onclick="searchStatus('/supply/queryByPager?status=Y');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/supply/queryByPager?status=N');" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/supply/queryByPager?status=ALL');" type="button" class="btn btn-default">
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



<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改供应商</h3>
                        <form role="form" id="editForm" >
                            <input type="hidden" attr="supply.supplyId" name="supplyId" id = "supplyId"/>
                            <div class="form-group">
                                <label class="control-label">供应商分类：</label>
                                <select id="editSupplyType" class="js-example-tags form-control supply_type" name="supplyTypeId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label>供应商名称：</label>
                                <input type="text" attr="supply.supplyName"  name="supplyName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>负责人姓名：</label>
                                <input type="text" attr="supply.supplyPricipal" name="supplyPricipal" class="form-control"/>
                            </div>
                            <div class="form-group">
                               <label>手机号：</label>
                                <input type="text"  attr="supply.supplyTel" name="supplyTel" maxlength="11" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>地址：</label>
                                <input id="editAddress" attr="supply.supplyAddress" type="text" class="col-sm-6" name="supplyAddress"/>
                            </div>
                            <div class="form-group">
                                <label>开户银行全称：</label>
                                <select class="js-example-tags form-control" attr="supply.supplyBank" type="select-one"
                                        name="supplyBank">
                                    <option value="中国工商银行">中国工商银行</option>
                                    <option value="中国农业银行">中国农业银行</option>
                                    <option value="中国银行">中国银行</option>
                                    <option value="中国建设银行">中国建设银行</option>
                                    <option value="交通银行">交通银行</option>
                                    <option value="中国农业银行">中国农业银行</option>
                                    <option value="招商银行">招商银行</option>
                                    <option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>开户人：</label>
                                <input type="text" attr="supply.supplyBankAccount" name="supplyBankAccount" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>开户卡号：</label>
                                <input type="text" attr="supply.supplyBankNo" name="supplyBankNo" maxlength="19" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>支付宝：</label>
                                <input type="text" attr="supply.supplyAlipay"  name="supplyAlipay" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>微信：</label>
                                <input type="text" attr="supply.supplyWechat" name="supplyWechat" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">


                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" class="btn btn-primary" value="修改" onclick="edit()">
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
                        <h3 class="m-t-none m-b">添加供应商</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">供应商分类：</label>
                                <select id="addSupplyType" class="js-example-tags form-control supply_type" name="supplyTypeId">
                                </select>
                            </div>
                            <div class="form-group">
                                <label>供应商名称：</label>
                                <input type="text"  name="supplyName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>负责人姓名：</label>
                                <input type="text" name="supplyPricipal" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>手机号：</label>
                                <input type="text"  name="supplyTel" maxlength="11" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>地址：</label>
                                <input data-toggle="city-picker" class="address" name="supplyAddress"/>
                            </div>
                            <div class="form-group">
                                <label>开户银行全称：</label>
                                <select class="js-example-tags form-control" type="select-one" name="supplyBank">
                                    <option value="">请选择</option>
                                    <option value="中国工商银行">中国工商银行</option>
                                    <option value="中国农业银行">中国农业银行</option>
                                    <option value="中国银行">中国银行</option>
                                    <option value="中国建设银行">中国建设银行</option>
                                    <option value="交通银行">交通银行</option>
                                    <option value="中国农业银行">中国农业银行</option>
                                    <option value="招商银行">招商银行</option>
                                    <option value="中国邮政储蓄银行">中国邮政储蓄银行</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>开户人：</label>
                                <input type="text" name="supplyBankAccount" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>开户卡号：</label>
                                <input type="text" name="supplyBankNo" maxlength="19" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>支付宝：</label>
                                <input type="text"  name="supplyAlipay" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>微信：</label>
                                <input type="text"  name="supplyWechat" class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="add()" value="添加">
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

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/supply/supply_info.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script type="text/javascript">

</script>
</body>
</html>

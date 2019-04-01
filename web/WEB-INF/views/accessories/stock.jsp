<%--
  Created by IntelliJ IDEA.
  User: XiaoQiao
  Date: 2017/4/11
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>库存管理</title>

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
            <th data-field="id" data-checkbox="true"></th>
            <th data-field="accName" data-sortable="true">
                名称
            </th>
            <th data-field="accTotal">
                数量
            </th>
            <th data-field="accPrice">
                价格
            </th>
            <th data-field="accDes">
                描述
            </th>
            <th data-field="accCommodityCode">
                商品条码
            </th>
            <th data-field="accUnit">
                计量单位
            </th>
            <th data-field="accIdle">
                可用数量
            </th>
            <th data-field="accSalePrice">
                售价
            </th>
            <th data-field="accUsedTime" data-formatter="formatterDate">
                最近一次领料时间
            </th>
            <th data-field="accBuyedTime" data-formatter="formatterDate">
                最近一次购买时间
            </th>
            <th data-field="supply.supplyName">
                配件供应商
            </th>
            <th data-field="accCreatedTime" data-formatter="formatterDate">
                创建时间
            </th>
            <th data-field="accessoriesType.accTypeName">
                所属分类
            </th>
            <th data-field="company.companyName">
                所属公司
            </th>
            <th data-field="accStatus" data-formatter="status">
                状态
            </th>
            <shiro:hasAnyRoles name="companyAdmin, companyRepertory">
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
        <form id="formSearch" class="form-horizontal">
            <div class="form-group col-sm-12" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchAccName" name="accName" class="form-control" placeholder="请输入配件名称">
                </div>
                <div class="col-sm-2">
                    <input type="text" id="searchAccCommodityCode" name="accCommodityCode" class="form-control"
                           placeholder="请输入商品条码">
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control company" id="searchCompanyId" name="companyId">
                    </select>
                </div>
                <div class="col-sm-2">
                    <select class="js-example-tags form-control accType" id="searchAccTypeId" name="accTypeId">
                    </select>
                </div>
                <div class="col-sm-2">
                    <button type="button" onclick="searchAcc()" class="btn btn-primary">
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
            <!--
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
            -->
            <a>
                <button onclick="queryStatus('Y')" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查可用模块
                </button>
            </a>
            <a>
                <button onclick="queryStatus('N')" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查不可用模块
                </button>
            </a>
            <a>
                <button onclick="queryAll()" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查询全部
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

<!-- 添加弹窗 -->
<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加配件</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">名称：</label>
                                <input type="text" name="accName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">数量：</label>
                                <input type="text" name="accTotal" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">价格：</label>
                                <input type="text" name="accPrice"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">描述：</label>
                                <textarea name="accDes" cols="20" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label">商品条码：</label>
                                <input type="text" name="accCommodityCode"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">计量单位：</label>
                                <input type="text" name="accUnit"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">可用数量：</label>
                                <input type="text" name="accIdle"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">售价：</label>
                                <input type="text" name="accSalePrice"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">最近一次领料时间：</label>
                                <input size="16" type="text" name="accUsedTime" readonly
                                       class="form_datetime form-control datetimepicker" id="accUsedTime">
                            </div>
                            <div class="form-group">
                                <label class="control-label">最近一次购买时间：</label>
                                <input type="text" name="accBuyedTime" readonly id="accBuyedTime"
                                       class="form_datetime form-control datetimepicker"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">供应商：</label>
                                <select id="addSupply" class="js-example-tags form-control acc_supply"
                                        name="supplyId"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">所属分类：</label>
                                <select id="addAccessoriesType" class="js-example-tags form-control acc_accessoriesType"
                                        name="accTypeId"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">所属公司：</label>
                                <select id="addCompany" class="js-example-tags form-control acc_company"
                                        name="companyId"></select>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary" onclick="add()" value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 修改弹窗 -->
<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改供应商</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="accessories.accId" name="accId" id="accId"/>
                            <div class="form-group">
                                <label class="control-label">名称：</label>
                                <input type="text" attr="accessories.accName" name="accName" class="form-control"/>
                                <label class="control-label">数量：</label>
                                <input type="text" attr="accessories.accTotal" name="accTotal" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">价格：</label>
                                <input type="text" attr="accessories.accPrice" name="accPrice" class="form-control"/>
                                <label class="control-label">描述：</label>
                                <textarea attr="accessories.accDes" name="accDes" type="textarea" cols="20" rows="5"
                                          class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label class="control-label">商品条码：</label>
                                <input type="text" attr="accessories.accCommodityCode" name="accCommodityCode"
                                       class="form-control"/>
                                <label class="control-label">计量单位：</label>
                                <input type="text" attr="accessories.accUnit" name="accUnit" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">可用数量：</label>
                                <input type="text" attr="accessories.accIdle" name="accIdle" class="form-control"/>
                                <label class="control-label">售价：</label>
                                <input type="text" attr="accessories.accSalePrice" name="accSalePrice"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">最近一次领料时间：</label>
                                <input id="editUsedTime" attr="accessories.accUsedTime" name="accUsedTime"
                                       class="form_datetime form-control datetimepicker1"/>

                                <label class="control-label">最近一次购买：</label>
                                <input id="editBuyedTime" attr="accessories.accBuyedTime" name="accBuyedTime"
                                       class="form_datetime form-control datetimepicker"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">供应商：</label>
                                <select id="editSupply" class="js-example-tags form-control acc_supply"
                                        name="supplyId"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">配件分类：</label>
                                <select id="editAccessoriesType"
                                        class="js-example-tags form-control acc_accessoriesType"
                                        name="accTypeId"></select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">所属公司：</label>
                                <select id="editCompany" class="js-example-tags form-control acc_company"
                                        name="companyId"></select>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" class="btn btn-primary" value="修改"
                                       onclick="edit()">
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
<script src="<%=path %>/js/accessories/accessories.js"></script>
</body>
</html>
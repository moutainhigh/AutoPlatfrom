<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>
<%@ taglib prefix="s" uri="http://shiro.apache.org/tags" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>配件采购管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <style>th {
        text-align: center;
    }

    td {
        text-align: center;
    }</style>
</head>
<body>
<div class="container">
    <s:hasAnyRoles name="companyAdmin, companyRepertory, companyBuyer, systemSuperAdmin, systemOrdinaryAdmin">
        <form id="formSearch" class="form-horizontal">
            <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                <div class="col-sm-2">
                    <input size="16" type="text" readonly
                           class="form_datetime form-control " id="buyTimeStart" placeholder="请选择开始时间">
                    <span class="add-on"><i class="icon-remove"></i></span>
                </div>
                <div class="col-sm-2">
                    <input size="16" type="text" readonly
                           class="form_datetime form-control " id="buyTimeEnd" placeholder="请选择结束时间">
                </div>

                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="sAccName" class="form-control" placeholder="请输入配件名">
                </div>

                <div class="col-sm-2">
                    <button type="button" onclick="byAccNameSearch()" class="btn btn-primary">
                        查询
                    </button>
                    <button type="button" onclick="closeSHForm()" class="btn btn-default">
                        关闭
                    </button>
                </div>
            </div>
        </form>
    </s:hasAnyRoles>

    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="accessories.supply.supplyName">供应商</th>
            <th data-field="accessories.accessoriesType.accTypeName">配件类别</th>
            <th data-field="accessories.accName">配件名称</th>
            <th data-field="accessories.accCommodityCode">配件条码</th>
            <th data-field="accUnit">单位</th>
            <th data-field="accessories.company.companyName">配件所属公司</th>
            <th data-field="accBuyCount">采购数量</th>
            <th data-field="accBuyPrice">采购单价</th>
            <th data-field="accBuyDiscount" data-formatter="fmtDiscount">采购折扣</th>
            <th data-field="accBuyTime" data-formatter="formatterDate">采购时间</th>
            <th data-field="accBuyTotal">采购总价</th>
            <th data-field="accBuyMoney">采购最终价</th>
            <th data-field="accBuyCheck" data-formatter="fmtCheckState">审核状态</th>
            <th data-field="accIsBuy" data-formatter="fmtBuyState">采购状态</th>
            <th data-field="accBuyStatus" data-formatter="status">状态</th>
            <s:hasAnyRoles name="companyAdmin, companyBuyer">
                <th data-formatter="fmtOperate" data-events="operateEvents">操作</th>
                <th data-formatter="fmtIsFinish" data-events="operateEvents">采购操作</th>
                <s:hasAnyRoles name="companyAdmin">
                    <th data-formatter="fmtPassCheck" data-events="operateEvents">审核操作</th>
                </s:hasAnyRoles>
            </s:hasAnyRoles>
        </tr>
        </thead>
        <tbody>

        <div id="toolbar" class="btn-group" style="margin: 10px 0px 10px 0px;">
            <s:hasAnyRoles name="companyAdmin, companyBuyer">
                <a data-toggle="modal">
                    <button type="button" onclick="showAccAddWin()" id="add" class="btn btn-default">
                        <i class="glyphicon glyphicon-plus"></i> 添加
                    </button>
                </a>
            </s:hasAnyRoles>
            <s:hasAnyRoles name="companyAdmin, companyBuyer, systemSuperAdmin, companyRepertory, systemOrdinaryAdmin">
                <a>
                    <button onclick="onlyCheck();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-ok"></i> 只看已审核
                    </button>
                </a>

                <a>
                    <button onclick="onlyStatus();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-shopping-cart"></i> 只看可用
                    </button>
                </a>

                <a>
                    <button onclick="onlyBuy();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-shopping-cart"></i> 只看以采购
                    </button>
                </a>
                <a>
                    <button onclick="showSearchFormSale();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-filter"></i>条件查询
                    </button>
                </a>
                <a>
                    <button onclick="allBuys();" type="button" class="btn btn-default">查看所有</button>
                </a>
                <s:hasAnyRoles
                        name="companyAdmin, companyRepertory">
                    <a>
                        <button onclick="passChecks();" type="button" class="btn btn-default">审核通过</button>
                    </a>
                </s:hasAnyRoles>
            </s:hasAnyRoles>

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
                        <h3 class="m-t-none m-b">修改采购信息</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="accessoriesBuy.accBuyId" name="accBuyId"/>
                            <input type="hidden" attr="accessoriesBuy.accessories.accId" name="accessories.accId"/>
                            <input type="hidden" attr="accessoriesBuy.accessories.accessoriesType.accTypeId"
                                   name="accessories.accessoriesType.accTypeId"/>
                            <input type="hidden" attr="accessoriesBuy.accessories.supply.supplyId"
                                   name="accessories.supply.supplyId"/>

                            <div class="form-group">
                                <label class="control-label">配件供应商：</label>
                                <select id="supplyType" class="js-example-tags form-control supply"
                                        name="accessories.supplyId"
                                        attr="accessoriesBuy.accessories.supply.supplyName"></select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">配件类别：</label>
                                <select id="eAccType" class="js-example-tags form-control accTypeA"
                                        name="accessories.accTypeId" attr="acc.accessoriesType.accTypeName"></select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">配件名称：</label>
                                <input type="text" name="accessories.accName" attr="accessoriesBuy.accessories.accName"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">计量单位：</label>
                                <input type="text" name="accUnit" attr="accessoriesBuy.accUnit" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">配件条码：</label>
                                <input type="text" name="accessories.accCommodityCode"
                                       attr="accessoriesBuy.accessories.accCommodityCode" maxlength="13"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">配件描述：</label>
                                <textarea name="accessories.accDes" class="form-control" rows="3"
                                          style="resize: none;" attr="accessoriesBuy.accessories.accDes"
                                          placeholder="描述信息可以不填"
                                          id="eAccDes"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">数量：</label>
                                <input type="text" name="accBuyCount" id="eAccBuyCount"
                                       attr="accessoriesBuy.accBuyCount"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">单价：</label>
                                <input type="text" name="accBuyPrice" id="eAccBuyPrice"
                                       attr="accessoriesBuy.accBuyPrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">折扣：</label>
                                <input type="text" name="accBuyDiscount" id="eAccBuyDiscount"
                                       attr="accessoriesBuy.accBuyDiscount" value="1"
                                       class="form-control" placeholder="没折扣可以不填" title="如：7.8折是0.78、5折是0.5"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">采购时间：</label>
                                <input size="16" type="text" name="accBuyTime" readonly
                                       class="form_datetime form-control " attr="accessoriesBuy.accBuyTime"
                                       id="buyTime">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" id="eAccBuyTotal"
                                       attr="accessoriesBuy.accBuyTotal"
                                       id="eAccBuyTotal"
                                       class="form-control" readonly/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" attr="accessoriesBuy.accBuyMoney"
                                       id="eAccBuyMoney"
                                       class="form-control" readonly/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" id="editButton" value="修改"
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

<div id="addWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-keyboard="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加配件采购信息</h3>
                        <div class="form-group" style="width: auto; display: inherit;">
                            <label>库存中是否有剩余配件：</label>
                            <input type="checkbox" id="isAcc" name="isAcc">
                        </div>
                        <form role="form" id="addForm">
                            <input type="hidden" attr="acc.accId" name="accId"/>
                            <input type="hidden" attr="acc.accId" name="accessories.accId"/>

                            <div class="form-group">
                                <label class="control-label">配件供应商：</label>
                                <select id="supply" class="js-example-tags form-control supply"
                                        name="accessories.supplyId" style="width: 100%;"></select>
                            </div>

                            <div class="form-group">
                                <label class="control-label">类别：</label>
                                <select id="accType" class="js-example-tags form-control accTypeA"
                                        name="accessories.accTypeId" attr="acc.accessoriesType.accTypeName"
                                        style="width: 100%;"></select>
                            </div>

                            <div class="form-group">
                                <label class="control-label" id="aName">配件名称：</label>
                                <input type="text" name="accessories.accName" class="form-control " id="accName"
                                       attr="acc.accName"/>
                                <small class="help-block" id="dck" style="color: #a94442; display: none"></small>
                            </div>

                            <div class="form-group">
                                <label class="control-label">配件描述：</label>
                                <textarea name="accessories.accDes" class="form-control" rows="3" id="accDes"
                                          style="resize: none;" attr="acc.accDes" placeholder="描述信息可以不填"></textarea>
                            </div>

                            <div class="form-group">
                                <label class="control-label">配件条码：</label>
                                <input type="text" name="accessories.accCommodityCode" class="form-control"
                                       maxlength="13"
                                       id="accCommodityCode"
                                       attr="acc.accCommodityCode"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">单位：</label>
                                <input type="text" name="accUnit" id="aAccUnit" attr="acc.accUnit"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">数量：</label>
                                <input type="text" name="accBuyCount" id="accBuyCount" attr="acc.accIdle"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">单价：</label>
                                <input type="text" name="accBuyPrice" id="accBuyPrice" attr="acc.accPrice"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">折扣：</label>
                                <input type="text" name="accBuyDiscount" id="accBuyDiscount"
                                       title="如：7.8折是0.78、5折是0.5" placeholder="没有折扣可以不填"
                                       class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">采购时间：</label>
                                <input size="16" type="text" id="accBuyTime" name="accBuyTime" readonly
                                       class="form_datetime form-control ">
                            </div>

                            <div class="form-group">
                                <label>总价：</label>
                                <input type="text" name="accBuyTotal" id="accBuyTotal" class="form-control" readonly/>
                            </div>

                            <div class="form-group">
                                <label>最终价：</label>
                                <input type="text" name="accBuyMoney" id="accBuyMoney" class="form-control" readonly/>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary"
                                       onclick="add()"
                                       value="添加"/>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="accWin" class="modal fade " aria-hidden="true" style="overflow:scroll"
     data-keyboard="true">
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">选择配件</h3>
                        <form role="form" id="accForm">
                            <table class="table table-hover" id="accTable"
                                   data-pagination="true"
                                   data-show-refresh="true"
                                   data-show-toggle="true"
                                   data-showColumns="true">
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
                                    <th data-formatter="fmtAccOperate" data-events="operateAccEvents">
                                        操作
                                    </th>
                                </tr>
                                </thead>
                            </table>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="添加" id="addAccBtn"
                                       onclick="addAccBuy()">
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
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>

<script src="<%=path %>/js/accessories/accessories_main.js"></script>
<script src="<%=path %>/js/accessories/accessories_buy.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>

</body>
</html>

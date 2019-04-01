<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>

<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>维修项目管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
    <form id="formSearch" class="form-horizontal">
        <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
            <div class="col-sm-2" style="margin-left: -15px;">
                <input type="text" id="searchRepairName" class="form-control" style="width:150px;" placeholder="请输维修项目名称" >
            </div>
            <div class="col-sm-2">
                <button type="button" onclick="search();" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="520"
    >
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="maintainName" >
                维修项目名称
            </th>
            <th data-field="maintainHour" >
                维修所需工时
            </th>
            <th data-field="maintainMoney" >
                维修基础费用
            </th>
            <th data-field="maintainManHourFee" >
                维修工时费
            </th>
            <th data-field="maintainDes" >
                维修描述
            </th>
            <th data-field="company.companyName">
                公司名称
            </th>
            <th data-field="maintainStatus" data-formatter="status">
                维修项目状态
            </th>
            <shiro:hasAnyRoles name="companyAdmin,companyRepertory">
                <th data-field="coi" data-formatter="operating" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="companyAdmin,companyRepertory,systemSuperAdmin,systemOrdinaryAdmin,companyArtificer,companyReceive">
            <shiro:hasAnyRoles name="companyAdmin,companyRepertory">
                <a>
                    <button type="button" id="add" onclick="showAddWin();" class="btn btn-default" >
                    <i class="glyphicon glyphicon-plus"></i> 添加
                    </button>
                 </a>
                <a>
                    <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                    </button>
                </a>
                <a><button type="button" onclick="showAddacc();" class="btn btn-default" >
                    <i class="glyphicon glyphicon-plus"></i> 添加基础配件
                </button></a>
            </shiro:hasAnyRoles>
                <a>
                    <button type="button" onclick="showAcc();" class="btn btn-default">
                        <i class="glyphicon glyphicon-search"></i> 查看基础配件
                    </button>
                </a>
                <a>
                    <button onclick="statusUsableness();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-search"></i>可用项目
                    </button>
                </a>
                <a>
                    <button onclick="statusAvailable();" type="button" class="btn btn-default">
                        <i class="glyphicon glyphicon-search"></i>不可用项目
                    </button>
                </a>
                <a>
                    <button onclick="All()" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>全部
                    </button>
                </a>
                <a>
                    <button onclick="showSearchForm()" id="showButton" type="button" class="btn btn-primary">
                        <i class="glyphicon glyphicon-search"></i> 搜索
                    </button>
                </a>
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
                        <h3 class="m-t-none m-b">修改维修项目</h3>
                        <form role="form" id="editForm">
                            <input name="maintainId" attr="maintain.maintainId" type="hidden">
                            <input name="maintainOrFix" attr="maintain.maintainOrFix" type="hidden">
                            <input name="companyId" attr="maintain.companyId" type="hidden">
                            <div class="form-group">
                                <label>维修项目名称：</label>
                                <input type="text"   name="maintainName" attr="maintain.maintainName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修所需工时（小时）：</label>
                                <input type="number"  name="maintainHour" attr="maintain.maintainHour"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修基础费用：</label>
                                <input type="number"  name="maintainMoney" attr="maintain.maintainMoney"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修工时费用：</label>
                                <input type="number"  name="maintainManHourFee" attr="maintain.maintainManHourFee"
                                       class="form-control"/>
                            </div>
                            <%--<div class="form-group" >--%>
                                <%--<label>公司名称</label>--%>
                                <%--<select class="js-example-tags form-control company" id="companys" name="companyId" value="company.companyId" style="width:150px;">--%>
                                <%--</select>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label>维修项目描述:</label>
                                <textarea  name="maintainDes" type="textarea" cols="20" rows="5" class="form-control" attr="maintain.maintainDes"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="edit();">
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
                        <h3 class="m-t-none m-b">添加维修项目</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>维修项目名称：</label>
                                <input type="text"   name="maintainName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修所需工时（小时）：</label>
                                <input type="number"  name="maintainHour"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>项目基础费用：</label>
                                <input type="number"  name="maintainMoney"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>维修工时费用：</label>
                                <input type="number"  name="maintainManHourFee"
                                       class="form-control"/>
                            </div>
                            <%--<div class="form-group" >--%>
                                <%--<label>公司名称</label>--%>
                                <%--<select class="js-example-tags form-control company" id="" name="companyId">--%>
                                <%--</select>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <label>维修项目描述:</label>
                                <textarea  name="maintainDes" type="textarea" cols="20" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="add()" value="添加">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="AccessoriesWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加保养项目基础配件</h3>
                        <div class="modal-footer" style="overflow:hidden;">
                            <div class="form-group">
                                <label>请选择配件分类</label>
                                <select id="addAccessoriesType" onchange="queryByTypeId(this);" class="js-example-tags form-control acc_accessoriesType" name="accTypeId"></select>
                            </div>
                            <table class="table table-hover" id="cusTable2"
                                   data-pagination="true"
                                   data-show-refresh="true"
                                   data-show-toggle="true"
                                   data-showColumns="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="accName">
                                        配件名称
                                    </th>
                                    <th data-field="accIdle" >
                                        配件库可用数
                                    </th>
                                    <th data-field="accSalePrice" >
                                        配件单价
                                    </th>
                                    <th data-field="test" data-formatter="operationWin" >
                                        操作
                                    </th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="accWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                <input type="text" name="accCount" id="count" class="form-control"  placeholder="请输入项目所需配件数量"/>
                <div class="modal-footer" style="overflow:hidden;">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <input type="button" class="btn btn-primary" value="确认选择配件" onclick="Addacc();"/>
                    <input type="reset" name="reset" style="display: none;" />
                </div>
            </div>
        </div>
    </div>
</div>



<div id="AccWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">维修项目基础配件</h3>
                        <div class="modal-footer" style="overflow:hidden;">
                            <table class="table table-hover" id="cusTable3"
                                   data-pagination="true"
                                   data-show-refresh="true"
                                   data-show-toggle="true"
                                   data-showColumns="true">
                                <thead>
                                <tr>
                                    <th data-field="state" data-checkbox="true"></th>
                                    <th data-field="accessories.accName">
                                        配件名称
                                    </th>
                                    <th data-field="accCount" >
                                        配件数量
                                    </th>
                                    <th data-field="accessories.accSalePrice" >
                                        配件单价
                                    </th>
                                </tr>
                                </thead>
                            </table>
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
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
<script src="<%=path %>/js/company/maintainItem.js"></script>
<script src="<%=path %>/js/main.js"></script>
</body>
</html>

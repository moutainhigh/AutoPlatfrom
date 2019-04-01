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
    <title>供应商分类管理</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
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
            <th data-field="supplyTypeName" data-sortable="true">
                分类名称
            </th>
            <th data-field="supplyTypeDes">
                分类描述
            </th>
            <th data-field="company.companyName">
                公司
            </th>
            <th data-field="supplyTypeStatus" data-formatter="status">
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
            <div class="form-group col-sm-12" id="searchDiv" style="margin-top:15px; display: none; padding-left: 0px;">
                <div class="col-sm-2" style="margin-left: -15px;">
                    <input type="text" id="searchSupplyTypeName" name="supplyTypeName" class="form-control"
                           placeholder="供应商分类名称">
                </div>
                <shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
                    <div class="col-sm-2">
                        <select class="js-example-tags form-control company" id="searchCompanyId" name="comanyId">
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div class="col-sm-2">
                    <button type="button" onclick="searchSupplyType()" class="btn btn-primary">
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
                    <button onclick="showAddWin();" type="button" id="add" class="btn btn-default">
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
                <button onclick="searchStatus('/supplyType/queryByPager?status=Y');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/supplyType/queryByPager?status=N');" type="button"
                        class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i> 查看不可用记录
                </button>
            </a>
            <a>
                <button onclick="searchStatus('/supplyType/queryByPager?status=ALL');" type="button"
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


<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改供应商分类</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" attr="supplyType.supplyTypeId" name="supplyTypeId" id="supplyTypeId"/>
                            <div class="form-group">
                                <label>供应商分类名称：</label>
                                <input type="text" attr="supplyType.supplyTypeName" name="supplyTypeName"
                                       id="supplyTypeName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>供应商分类描述：</label>
                                <textarea class="form-control" attr="supplyType.supplyTypeDes" type="textarea"
                                          name="supplyTypeDes"
                                          rows="3"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <span id="error1" style="color: red;"></span>
                                <br/>
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="editButton" onclick="edit()" class="btn btn-primary"
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

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加供应商分类</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>供应商分类名称：</label>
                                <input type="text" name="supplyTypeName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label>供应商分类描述：</label>
                                <textarea class="form-control" type="textarea" name="supplyTypeDes"
                                          rows="3"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    关闭
                                </button>
                                <input type="button" class="btn btn-primary" id="addButton" onclick="add()" value="添加">
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
<script src="<%=path %>/js/supply/supply_type.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script type="text/javascript">

</script>
</body>
</html>

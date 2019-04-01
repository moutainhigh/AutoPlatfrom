<%--
  Created by IntelliJ IDEA.
  User: xiao-qiang
  Date: 2017/4/17
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>权限管理</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/font-awesome.min93e3.css" rel="stylesheet">
    <link href="<%=path %>/css/main.css" rel="stylesheet">

    <style>
        .ibox-content span {
            margin-right: 0.5em;
            font-size: 1em;
            margin-top: 0.5em;
            float: left;
            cursor: pointer;
        }

        #permissionY span {
            background-color: #388436;

        }

        #permissionN span {
            background-color: #a99e23;
        }
    </style>
</head>
<body class="gray-bg">
<div class="container">
    <div class="wrapper wrapper-content">
        <div class="wrapper wrapper-content">
            <div class="ibox-title">
                <button onclick="showAduqPermission();" type="button" class="btn btn-primary" data-toggle="button">
                    管理权限
                </button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <button onclick="showAllotPermission();" type="button" class="btn btn-primary" data-toggle="button">
                    分配权限
                </button>
            </div>
        </div>

        <div class="wrapper wrapper-content" id="aduqPermission" style="display: none">
            <table class="table table-hover" id="cusTable"
                   data-pagination="true"
                   data-show-refresh="true"
                   data-show-toggle="true"
                   data-showColumns="true"
                   data-height="520">
                <thead>
                <tr>
                    <th data-field="state" data-checkbox="true"></th>
                    <th data-field="permissionName">
                        权限名称
                    </th>
                    <th data-field="permissionZHName">
                        权限中文名称
                    </th>
                    <th data-field="permissionDes">
                        权限描述
                    </th>
                    <th data-field="module.moduleName">
                        所属模块
                    </th>
                    <th data-field="permissionStatus" data-formatter="status">
                        状态
                    </th>
                    <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <div id="toolbar" class="btn-group">
                    <a>
                        <button onclick="showAddWin()" type="button" id="add" class="btn btn-default">
                            <i class="glyphicon glyphicon-plus"></i> 添加
                        </button>
                    </a>
                    <a>
                        <button onclick="showEditWin()" type="button" id="edit" class="btn btn-default">
                            <i class="glyphicon glyphicon-pencil"></i>
                            修改
                        </button>
                    </a>
                    <a>
                        <button onclick="queryAll()" type="button" class="btn btn-default">
                            <i class="glyphicon glyphicon-search"></i>
                            查询全部
                        </button>
                    </a>
                    <a>
                        <button onclick="queryByStatus('Y')" type="button" class="btn btn-default">
                            <i class="glyphicon glyphicon-search"></i>
                            查可用
                        </button>
                    </a>
                    <a>
                        <button onclick="queryByStatus('N')" type="button" class="btn btn-default">
                            <i class="glyphicon glyphicon-search"></i>
                            查不可用
                        </button>
                    </a>
                    <select id="moduleSelect" onchange="moduleSelect(this);"
                            class="js-example-tags form-control module_all"
                            name="moduleId">
                    </select>
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
                                <h3 class="m-t-none m-b">修改权限信息</h3>
                                <form role="form" id="editForm">
                                    <input type="hidden" attr="permission.permissionId" name="permissionId"/>
                                    <div class="form-group">
                                        <label class="control-label">所属模块：</label>
                                        <select id="moduleSelect3" class="js-example-tags form-control module_all_2"
                                                name="moduleId">
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">权限名称：</label>
                                        <input attr="permission.permissionName" type="text" name="permissionName"
                                               class="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">权限中文名称：</label>
                                        <input attr="permission.permissionZHName" type="text" name="permissionZHName"
                                               class="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <label>权限描述：</label>
                                        <textarea type="textarea" attr="permission.permissionDes" name="permissionDes"
                                                  class="form-control"></textarea>
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

        <div id="addWin" class="modal fade" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-sm-12 b-r">
                                <h3 class="m-t-none m-b">添加权限信息</h3>
                                <form role="form" id="addForm">
                                    <div class="form-group">
                                        <label class="control-label">所属模块：</label>
                                        <select id="moduleSelect2" class="js-example-tags form-control module_all_2"
                                                name="moduleId">
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">权限名称：</label>
                                        <input type="text" name="permissionName" class="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label">权限中文名称：</label>
                                        <input type="text" name="permissionZHName" class="form-control"/>
                                    </div>

                                    <div class="form-group">
                                        <label>权限描述：</label>
                                        <textarea name="permissionDes" class="form-control"></textarea>
                                    </div>

                                    <div class="modal-footer" style="overflow:hidden;">
                                        <button type="button" class="btn btn-default"
                                                data-dismiss="modal">关闭
                                        </button>
                                        <input type="button" id="addButton" onclick="add()" class="btn btn-primary"
                                               value="添加">
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
    </div>

    <div class="wrapper wrapper-content" id="allotPermission" style="display: none">
        <p>&nbsp;</p>
        <div class="input-group">
            <div class="input-group-btn">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">角色类型
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${roles}" var="r">
                        <li onclick="selectRole(this, '${r.roleId}')"><a href="javascript:;">${r.roleDes}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <input id="roleType" type="text" disabled="disabled" placeholder="请选择一个角色" class="form-control">
        </div>

        </br>

        <div class="input-group" id="selectModule" style="display: none">
            <div class="input-group-btn">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">模块类型
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <c:forEach items="${modules}" var="m">
                        <li onclick="selectModule(this, '${m.moduleId}')"><a href="javascript:;">${m.moduleName}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <input id="moduleType" type="text" disabled="disabled" placeholder="请选择一个模块" class="form-control">
        </div>

        <div class="ibox-content" id="permissionDes" style="display: none">

            <div class="ibox-title">
                <h4>已有的权限</h4>
            </div>
            <div id="permissionY" class="ibox-content" style="height: 170px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
            </div>

            <div class="ibox-title">
                <h4>未有的权限</h4>
            </div>
            <div id="permissionN" class="ibox-content" style="height: 170px; OVERFLOW-Y: auto; OVERFLOW-X:hidden;">
            </div>
            <div id="btnDiv" style="display: none;">
                <button onclick="addAll();" type="button" class="btn btn-primary" data-toggle="button">
                    添加所有
                </button>
                <button onclick="delAll();" type="button" class="btn btn-primary" data-toggle="button">
                    删除所有
                </button>
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
<script src="<%=path %>/js/system/permission.js"></script>
<script src="<%=path %>/js/main.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>物料清单</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"/>
    <link href="<%=path %>/css/main.css" rel="stylesheet"/>
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
            <th data-field="checkin.userName">
                车主姓名
            </th>
            <th data-field="checkin.carPlate">
                登记车牌号
            </th>
            <th data-field="checkin.maintainOrFix">
                保养&nbsp;|&nbsp;维修
            </th>
            <th data-field="speedStatus">
                当前进度
            </th>
            <th data-field="pickingStatus">
                领料状态
            </th>
        </tr>
        </thead>

        <tbody>
        <div id="toolbar" class="btn-group">
            <a>
                <button onclick="showMaterialWin();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>
                    查询物料清单
                </button>
            </a>
        </div>
        </tbody>

    </table>
</div>

<div id="searchMaterialWin" class="modal fade" aria-hidden="true" style="overflow:scroll">
    <div class="modal-dialog" style="width: 1000px;">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">查看当前记录的物料清单</h3>
                        <table class="table table-hover" id="cusTable1"
                               data-pagination="true"
                               data-show-refresh="true"
                               data-show-toggle="true"
                               data-showColumns="true"
                               data-height="520">
                            <thead>
                            <tr>
                                <th data-field="state" data-checkbox="true"></th>
                                <th data-field="userName">
                                    车主名
                                </th>
                                <th data-field="userRequests">
                                    车主要求
                                </th>
                                <th data-field="maintainName">
                                    项目名
                                </th>
                                <th data-field="accName">
                                    配件名
                                </th>
                                <th data-field="materialCount">
                                    物料数量
                                </th>
                                <th data-field="jisuan" data-formatter="countPrice">
                                    基本费用
                                </th>
                                <th data-field="materialCreatedTime" data-formatter="formatterDate">
                                    创建时间
                                </th>
                                <th data-field="materialStatus" data-formatter="status">
                                    状态
                                </th>
                                <th data-field="isAdd" data-formatter="isUse">
                                    是否申请
                                </th>
                                <shiro:hasAnyRoles name="companyAdmin, companyRepertory">
                                <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                                    操作
                                </th>
                                </shiro:hasAnyRoles>
                            </tr>
                            </thead>
                            <div class="container">
                                <form id="formSearch" class="form-horizontal">
                                    <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
                                        <div class="col-sm-2">
                                            <input size="16" type="text" readonly
                                                   class="form_datetime form-control " id="createTimeStart"
                                                   placeholder="请选择开始时间">
                                            <span class="add-on"><i class="icon-remove"></i></span>
                                        </div>
                                        <div class="col-sm-2">
                                            <input size="16" type="text" readonly
                                                   class="form_datetime form-control " id="createTimeEnd"
                                                   placeholder="请选择结束时间">
                                        </div>

                                        <div class="col-sm-2" style="margin-left: -15px;">
                                            <input type="text" id="searchUserName" class="form-control"
                                                   placeholder="请输入车主名">
                                        </div>

                                        <div class="col-sm-2">
                                            <button type="button" onclick="bySelectSearch()" class="btn btn-primary">
                                                查询
                                            </button>
                                            <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                                                关闭
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <br/>
                            <tbody>
                            <div id="toolbar1" class="btn-group">
                                <a>
                                    <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                                        <i class="glyphicon glyphicon-pencil"></i> 修改
                                    </button>
                                </a>
                                <a>
                                    <button onclick="showGetMaterial()" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-plus"></i> 申请领料
                                    </button>
                                </a>
                                <a>
                                    <button onclick="queryAll()" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-search"></i>
                                        查询全部
                                    </button>
                                </a>
                                <a>
                                    <button onclick="queryStatus('Y')" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-search"></i>
                                        查可用清单
                                    </button>
                                </a>
                                <a>
                                    <button onclick="queryStatus('N')" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-search"></i>
                                        查不可用清单
                                    </button>
                                </a>
                                <a>
                                    <button id="showButton" onclick="showSearchForm();" type="button"
                                            class="btn btn-default">
                                        <i class="glyphicon glyphicon-search"></i>
                                        条件查询
                                    </button>
                                </a>
                            </div>
                            </tbody>

                        </table>
                        <div style="height: 40px;"></div>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default"
                                    data-dismiss="modal">关闭
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div id="editWin" class="modal fade" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-12 b-r">
                            <h3 class="m-t-none m-b">修改物料数量</h3>
                            <form role="form" id="editForm">
                                <input type="hidden" attr="materialListInfo.materialId" name="materialId"/>
                                <div class="form-group">
                                    <label>物料数量：</label>
                                    <input attr="materialListInfo.materialCount" type="number" name="materialCount"
                                           class="form-control"/>
                                </div>

                                <div class="modal-footer" style="overflow:hidden;">
                                    <button onclick="closeEditWin();" type="button" class="btn btn-default">
                                        关闭
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
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="<%=path %>/js/dispatchingPicking/materialList.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/main.js"></script>

</body>
</html>

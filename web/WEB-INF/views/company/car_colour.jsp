<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>汽车颜色管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/example.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-colorpalette.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="container">
    <form id="formSearch" class="form-horizontal">
        <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
            <div class="col-sm-2" style="margin-left: -15px;">
                <input type="text" id="searchColorName" name="colorName" class="form-control" placeholder="请输入颜色名称" >
            </div>
            <div class="col-sm-2">
                <button type="button" onclick="searchColor();" class="btn btn-primary">
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
            <th data-field="colorName" data-sortable="true">
                汽车颜色名称
            </th>
            <th data-field="colorHex" data-formatter="colorFormatter">
                汽车颜色
            </th>

            <th data-field="colorDes">
                汽车颜色描述
            </th>
            <th data-field="colorStatus" data-formatter="status">
                汽车颜色状态
            </th>
            <shiro:hasAnyRoles name="systemSuperAdmin,systemOrdinaryAdmin">
                <th data-field="co" data-formatter="operating" data-events="operateEvents">
                    操作
                </th>
            </shiro:hasAnyRoles>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
        <shiro:hasAnyRoles name="systemSuperAdmin,systemOrdinaryAdmin">
            <a data-toggle="modal">
                <button type="button" id="add" onclick="showAddWin();" class="btn btn-default">
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
                <button onclick="statusUsableness();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>查看可用颜色
                </button>
            </a>
            <a>
                <button onclick="statusAvailable();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>查看不可用颜色
                </button>
            </a>
            <a>
                <button onclick="colorAll();" type="button" class="btn btn-default">
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


<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加汽车颜色</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">汽车颜色名称：</label>
                                <br />
                                <input type="text" name="colorName" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车颜色：</label>
                                <span id="span" style='display: inline-block; width: 25px; height: 25px;'></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色RGB：</label>
                                <input id="selected-colorRGB" readonly class="form-control" name="colorRGB">
                            </div>
                            <div class="form-group btn-group">
                                <label class="control-label">汽车颜色Hex：</label>
                                <input id="selected-color" readonly class="form-control" name="colorHex">
                                <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown">点击选择颜色</a>
                                <ul class="dropdown-menu">
                                    <li><div id="colorpalette"></div></li>
                                </ul>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="add();" value="添加">
                                </input>
                                </input>
                                <input type="reset" name="reset" style="display: none;" />
                            </div>
                        </form>
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
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">修改汽车颜色</h3>
                        <form role="form" id="editForm">
                            <input type="hidden" name="colorId" class="form-control" attr="carColor.colorId"/>
                            <div class="form-group">
                                <label class="control-label">汽车颜色名称：</label>
                                <br />
                                <input type="text" name="colorName" class="form-control" attr="carColor.colorName"/>
                            </div>

                            <div class="form-group">
                                <label class="control-label">汽车颜色：</label>
                                <span id="spans" style='display: inline-block; width: 25px; height: 25px;  background-color:transparent;'></span>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色RGB：</label>
                                <input id="selected-colorRGB1" readonly class="form-control" name="colorRGB" attr="carColor.colorRGB" type="text">
                            </div>
                            <div class="form-group btn-group">
                                <label class="control-label">汽车颜色Hex：</label>
                                <input id="selected-color1" readonly class="form-control" name="colorHex" attr="carColor.colorHex" type="text">
                                <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown">点击选择颜色</a>
                                <ul class="dropdown-menu">
                                    <li><div id="colorpalette1"></div></li>
                                </ul>
                            </div>
                            <div class="form-group">
                                <label class="control-label">汽车颜色描述：</label>
                                <textarea name="colorDes" cols="20" rows="5" class="form-control" attr="carColor.colorDes" type="textarea"></textarea>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" onclick="edit();" value="修改">
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
<script src="<%=path%>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-colorpalette.js"></script>
<script src="<%=path%>/js/main.js"></script>
<script src="<%=path%>/js/company/car_color.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:47
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


    <title>库存统计</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
          type="text/css">
</head>
<shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
<body onload="adminQuery()">
<span id="span" hidden>admin</span>
</shiro:hasAnyRoles>
<shiro:hasAnyRoles name="companyAdmin,companyBuyer,companySales,companyRepertory">
<body onload="companyQuery()">
<span id="span" hidden>company</span>
</shiro:hasAnyRoles>
<div class="container" style="margin-top:15px;">
    <ul class="nav nav-pills" id="myTab">
        <li  class="active"><a  data-toggle="tab" data-target="#year"   >按年查询</a></li>
        <li><a  data-toggle="tab" data-target="#quarter">按季度查询</a></li>
        <li><a  data-toggle="tab" data-target="#month">按月查询</a></li>
        <li><a  data-toggle="tab"data-target="#week">按周查询</a></li>
        <li><a  data-toggle="tab"data-target="#day">按日查询</a></li>
        <shiro:hasAnyRoles name="systemSuperAdmin, systemOrdinaryAdmin">
            <li><a  data-toggle="tab" onclick="showCompany()">选择公司</a></li>
            <li class="disabled" ><a id="spans" href ="javascript:return false;"></a></li>

        </shiro:hasAnyRoles>
        <div class="form-group" style="width: auto; display: inherit;">
            <input type="checkbox" id="isGraphics" name="isGraphics">
            <input type="checkbox" id="isQuantity">
        </div>

    </ul>
    <div class="tab-content" style="margin-top:10px;">
        <div class="tab-pane  fade in active" id="year">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group">
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择开始时间" id="start1"  name="startTime"
                               class="form-control datatimepicker"/>
                    </div>
                    <div class="col-md-1">
                        <hr style="color:black"/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择结束时间" id="end1" name="endTime"
                               class="form-control datatimepicker"/>
                    </div>

                    <div class="col-sm-3">
                        <button type="button" onclick="search(1)" class="btn btn-primary">
                            查询
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade " id="quarter">
            <form  class="form-horizontal">
                <div class="form-group"  >
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择开始时间" id="start2" name="startTime"
                               class="form-control datatimepicker"/>
                    </div>
                    <div class="col-md-1">
                        <hr style="color:black"/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择结束时间" id="end2" name="endTime"
                               class="form-control datatimepicker"/>
                    </div>

                    <div class="col-sm-3">
                        <button type="button" onclick="search(2)" class="btn btn-primary">
                            查询
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="month">
            <form  class="form-horizontal">
                <div class="form-group" >
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择开始时间" id="start3" name="startTime"
                               class="form-control datatimepicker"/>
                    </div>
                    <div class="col-md-1">
                        <hr style="color:black"/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择结束时间" id="end3" name="endTime"
                               class="form-control datatimepicker"/>
                    </div>

                    <div class="col-sm-3">
                        <button type="button" onclick="search(3)" class="btn btn-primary">
                            查询
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade " id="week">
            <form class="form-horizontal">
                <div class="form-group"  >
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择开始时间" id="start4" name="startTime"
                               class="form-control datatimepicker"/>
                    </div>
                    <div class="col-md-1">
                        <hr style="color:black"/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择结束时间" id="end4"  name="endTime"
                               class="form-control datatimepicker"/>
                    </div>

                    <div class="col-sm-3">
                        <button type="button" onclick="search(4)" class="btn btn-primary">
                            查询
                        </button>

                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade " id="day">
            <form class="form-horizontal">
                <div class="form-group" >
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择开始时间 " id="start5" name="startTime"
                               class="form-control datatimepicker"/>
                    </div>
                    <div class="col-md-1">
                        <hr style="color:black"/>
                    </div>
                    <div class="col-sm-4">
                        <input type="text" readonly placeholder="请选择结束时间" id="end5"  name="endTime"
                               class="form-control datatimepicker"/>
                    </div>

                    <div class="col-sm-3">
                        <button type="button" onclick="search(5)" class="btn btn-primary">
                            查询
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<div id="checkWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择公司</h3>
                        <form role="form" id="checkForm" >
                            <div class="form-group">
                                <label class="control-label">请选择公司：</label>
                                <select id="company" class="js-example-tags form-control company" name="companyId" onchange="checkCompany(this)"></select>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="companyButton" onclick="check()" class="btn btn-primary" value="确认" >
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="columnar" style="min-width:400px;height:400px"></div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<script src="<%=path %>/js/highcharts.js"></script>
<script src="<%=path %>/js/grid-light.js"></script>
<script src="<%=path %>/js/exporting.js"></script>
<script src="<%=path %>/js/highcharts-zh_CN.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/reportStatistics/my-charts.js"></script>
<script src="<%=path %>/js/reportStatistics/stock-statistics.js"></script>
<script src="<%=path %>/js/main.js"></script>
</body>
</html>

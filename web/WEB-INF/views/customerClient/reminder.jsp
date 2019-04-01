<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的已完成的维修保养</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 20px;">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title panel-primary">
                    <h5>我的已完成的维修保养</h5>

                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="remTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>
                            <th data-toggle="true">车主姓名</th>
                            <th>登记车牌号</th>
                            <th>汽车行驶里程</th>
                            <th>维修保养开始时间</th>
                            <th>预估结束时间</th>
                            <th>实际结束时间</th>
                            <th data-hide="all">创建记录时间</th>
                            <th data-hide="all">提车时间</th>
                            <th data-hide="all">保养&nbsp;|&nbsp;维修</th>
                            <th data-hide="all">当前进度</th>
                            <th data-hide="all">是否回访</th>
                            <th data-hide="all">记录描述</th>
                            <th data-hide="all">汽修公司</th>
                            <th data-hide="all">记录状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.rems}" var="rem">
                            <tr>
                                <td>${rem.checkin.userName}</td>
                                <td>${rem.checkin.carPlate}</td>
                                <td>${rem.checkin.carMileage}</td>
                                <td>
                                    <fmt:formatDate value="${rem.startTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${rem.endTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${rem.actualEndTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${rem.recordCreatedTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${rem.pickupTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>${rem.checkin.maintainOrFix}</td>
                                <td>${rem.speedStatus}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${rem.trackStatus == 'Y'}">
                                            是
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">否</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${rem.recordDes}</td>
                                <td>${rem.company.companyName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${rem.recordStatus == 'Y'}">
                                            可用
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">不可用</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                        <tfoot>
                        </tfoot>
                    </table>

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
<script src="<%=path %>/js/plugins/footable/footable.all.min.js"></script>
<script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/company/home.js"></script>
<script src="<%=path%>/js/customerClient/reminder.js"></script>
</body>
</html>
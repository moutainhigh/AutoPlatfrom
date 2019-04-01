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
    <title>我的预约</title>

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
                    <h5>我的预约</h5>

                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="appTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>
                            <th data-toggle="true">车主姓名</th>
                            <th>车主电话</th>
                            <th>汽车品牌</th>
                            <th>汽车颜色</th>
                            <th>汽车车型</th>
                            <th>汽车车牌</th>
                            <th>车牌号码</th>
                            <th data-hide="all">预计到店时间</th>
                            <th data-hide="all">预约状态</th>
                            <th data-hide="all">当前进度</th>
                            <th data-hide="all">维修|保养</th>
                            <th data-hide="all">预约创建时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.apps}" var="app">
                            <tr>
                                <td>${app.userName}</td>
                                <td>${app.userPhone}</td>
                                <td>${app.brand.brandName}</td>
                                <td>${app.color.colorName}</td>
                                <td>${app.model.modelName}</td>
                                <td>${app.plate.plateName}</td>
                                <td>${app.carPlate}</td>
                                <td>
                                    <fmt:formatDate value="${app.arriveTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${app.appoitmentStatus == 'Y'}">
                                            可用
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">不可用</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${app.speedStatus}</td>
                                <td>${app.maintainOrFix}</td>
                                <td>
                                    <fmt:formatDate value="${app.appCreatedTime}" pattern="yyyy-MM-dd HH:mm"/>
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
<script src="<%=path%>/js/customerClient/appointment.js"></script>
</body>
</html>
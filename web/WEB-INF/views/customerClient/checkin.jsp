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
    <title>我的接待</title>

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
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title panel-success">
                    <h5>我的接待记录</h5>

                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="checkinTable" class="footable table table-stripped toggle-arrow-tiny"
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
                            <th data-hide="all">汽车油量（L）</th>
                            <th data-hide="all">行驶里程（KM）</th>
                            <th data-hide="all">是否洗车</th>
                            <th data-hide="all">到店时间</th>
                            <th data-hide="all">车上物品描述</th>
                            <th data-hide="all">汽车完好度描述</th>
                            <th data-hide="all">用户要求描述</th>
                            <th data-hide="all">维修|保养</th>
                            <th data-hide="all">记录状态</th>
                            <th data-hide="all">登记时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.chs}" var="checkin">
                            <tr>
                                <td>${checkin.userName}</td>
                                <td>${checkin.userPhone}</td>
                                <td>${checkin.brand.brandName}</td>
                                <td>${checkin.color.colorName}</td>
                                <td>${checkin.model.modelName}</td>
                                <td>${checkin.plate.plateName}</td>
                                <td>${checkin.carPlate}</td>
                                <td>${checkin.oilCount}</td>
                                <td>${checkin.carMileage}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${checkin.carWash == 'Y'}">
                                            是
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">否</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value="${checkin.arriveTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>${checkin.carThings}</td>
                                <td>${checkin.intactDegrees}</td>
                                <td>${checkin.userRequests}</td>
                                <td>${checkin.maintainOrFix}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${checkin.checkinStatus == 'Y'}">
                                            可用
                                        </c:when>
                                        <c:otherwise>
                                            <span style="color: red;">不可用</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <fmt:formatDate value="${checkin.checkinCreatedTime}"
                                                    pattern="yyyy-MM-dd HH:mm"/>
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
<script src="<%=path%>/js/customerClient/checkin.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/18
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>iframeTable</title>
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/plugins/footable/footable.core.css" rel="stylesheet">

    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<body>

<div class="ibox-content">
    <table id="userTable" class="footable table table-stripped toggle-arrow-tiny"
           data-page-size="8">
        <thead>
        <tr>
            <th data-toggle="true">车主姓名</th>
            <th>预计结束时间</th>
            <th>实际结束时间</th>
            <th>车主提车时间</th>
            <th>维修保养进度</th>
            <th>维修保养总费用</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="rd">
            <tr>
                <td>${rd.checkin.userName}</td>
                <td>
                    <fmt:formatDate value="${rd.endTime}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td>
                    <fmt:formatDate value="${rd.actualEndTime}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td>
                    <fmt:formatDate value="${rd.pickupTime}" pattern="yyyy-MM-dd HH:mm"/>
                </td>
                <td>${rd.speedStatus}</td>
                <td>${rd.chargeBill.chargeBillMoney}</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        </tfoot>
    </table>

</div>


<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/plugins/footable/footable.all.min.js"></script>
<script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
<script>
    $(document).ready(function () {
        $("#userTable").footable();
    });
</script>
</body>
</html>

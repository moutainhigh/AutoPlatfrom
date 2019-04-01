
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>工资管理</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container">
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="500">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="user.userName">
                名称
            </th>
            <th data-field="prizeSalary">
                奖金
            </th>
            <th data-field="minusSalary">
                罚金
            </th>
            <th data-field="totalSalary">
                总工资
            </th>
            <th data-field="salaryTime" data-formatter="formatterDate">
                发放时间
            </th>
            <th data-field="salaryDes">
                工资描述
            </th>
        </tr>
        </thead>
    </table>
</div>

<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/peopleManage/peopleSalary.js"></script>
<script src="<%=path%>/js/main.js"></script>
</body>
</html>

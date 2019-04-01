<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<head>
    <title>重新登入</title>

    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">

</head>
<body onload="notLogin()">

<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/index/not_login.js"></script>
</body>
</html>

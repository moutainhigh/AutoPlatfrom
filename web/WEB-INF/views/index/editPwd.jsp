<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人资料</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/code.css" rel="stylesheet" type="text/css">


</head>
<body>

<div id="main">
    <form method="get">
        <div class="row pass">
            <input type="password" id="oldPwd" placeholder="请输入旧密码"/>
            <img class="arrowImg1" onclick="arrow1();" src="<%=path%>/img/alien_2.png">
            <img class="arrowImg2" onclick="arrow2();" src="<%=path%>/img/alien_17.png">
        </div>

        <div class="row pass">
            <input type="password" id="pwd" title="新密码必须是六位或六位以上" placeholder="请输入新密码" onfocus="this.type='password'" autocomplete = 'new-password'/>
        </div>

        <div class="row pass">
            <input type="password" id="rePwd" placeholder="请输入确认密码 " disabled="true"  onfocus="this.type='password'"autocomplete = 'new-password'/>
        </div>
        <div class="arrowCap"></div>
        <div class="arrow"></div>
        <p class="meterText">密码强度</p>
        <input type="submit" onclick="editPwd();" value="确定" />
    </form>
</div>




<%@ include file="../common/rightMenu.jsp" %>
<script src="<%=path %>/js/contextmenu.js"></script>
<script src="<%=path %>/js/jquery.min.js"></script>
<script src="<%=path %>/js/bootstrap.min.js"></script>
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
<script src="<%=path %>/js/index/editPwd.js"></script>
<script src="<%=path %>/js/index/script.js"></script>
<script src="<%=path %>/js/index/jquery.complexify.js"></script>

</body>
</html>

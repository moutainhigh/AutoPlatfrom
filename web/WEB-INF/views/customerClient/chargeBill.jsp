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
    <title>我的收费单据</title>

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
                    <h5>我的收费单据</h5>

                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">

                    <table id="billTable" class="footable table table-stripped toggle-arrow-tiny"
                           data-page-size="8">
                        <thead>
                        <tr>
                            <th data-toggle="true">车主姓名</th>
                            <th>车主手机号</th>
                            <th>收费总金额</th>
                            <th>实付金额</th>
                            <th>付款方式</th>
                            <th>收费时间</th>
                            <th>收费单据创建时间</th>
                            <th data-hide="all">收费单据描述</th>
                            <th data-hide="all">所属公司</th>
                            <th data-hide="all">收费状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.bills}" var="bill">
                            <tr>
                                <td>${bill.record.checkin.userName}</td>
                                <td>${bill.record.checkin.userPhone}</td>
                                <td>${bill.chargeBillMoney}</td>
                                <td>${bill.actualPayment}</td>
                                <td>${bill.paymentMethod}</td>
                                <td>
                                    <fmt:formatDate value="${bill.chargeTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>
                                    <fmt:formatDate value="${bill.chargeCreatedTime}" pattern="yyyy-MM-dd HH:mm"/>
                                </td>
                                <td>${bill.chargeBillDes}</td>
                                <td>${bill.record.company.companyName}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${bill.chargeBillStatus == 'Y'}">
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
<script src="<%=path%>/js/customerClient/ChargeBilll.js"></script>
</body>
</html>
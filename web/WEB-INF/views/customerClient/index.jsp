<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/5/17
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>车主后台主页</title>
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.css" rel="stylesheet">

</head>
<body onload="intNamePhone('${sessionScope.user.userName}', '${sessionScope.user.userPhone}');">

<div class="container" style="margin-top: 20px;">
    <h3>推荐汽修公司</h3>
    <div class="row" id="companyDiv">
        <c:forEach items="${companys}" var="c">
            <div class="col-lg-4 col-md-4 col-sm-6">
                <a onclick="showAddWin('${c.companyId}', '${sessionScope.user.userName}', '${sessionScope.user.userPhone}');"
                   class="fh5co-project-item image-popup model">
                    <figure>
                        <div class="overlay"><i class="ti-plus"></i></div>
                        <img src="<%=path%>${c.companyImg}" alt="Image" class="img-responsive">
                    </figure>
                    <div class="fh5co-text">
                        <h2>${c.companyName}</h2>
                        <span>${c.companyTel}</span>
                        <p>${c.companyAddress}</p>
                        <button class="btn-success col-lg-5  pull-right">预约</button>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
    <c:if test="${pageTotal.size() >= 2}">
        <ul style="margin: auto" class="pagination pagination-lg">
            <li><a href="javascript:;" onclick="lastPage();">&laquo;</a></li>
            <c:forEach items="${pageTotal}" var="pt">
                <li><a href="javascript:;" onclick="selectPage(this);" id="pb${pt}">${pt}</a></li>
            </c:forEach>
            <li><a href="javascript:;" onclick="nextPage(${pageTotal.size()});">&raquo;</a></li>
        </ul>
    </c:if>
</div>

<div id="addWin" class="modal fade" style="overflow:scroll" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">预约</h3>
                        <form role="form" id="addForm">
                            <input type="hidden" id="companyId" name="companyId" class="form-control"/>
                            <div class="form-group">
                                <label>车主姓名：</label>
                                <input type="text" id="addUserName" name="userName" readonly
                                       value="${sessionScope.user.userName}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>车主电话：</label>
                                <input type="text" id="addUserPhone" name="userPhone" readonly
                                       value="${sessionScope.user.userPhone}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>汽车品牌：</label>
                                <select id="addCarBrand" class="js-example-tags form-control car_brand"
                                        onchange="checkBrand(this)" name="brandId">
                                </select>
                            </div>

                            <div class="form-group" id="carModelDiv" style="display: none;">
                                <label>汽车车型：</label>
                                <select id="addCarModel" class="js-example-tags form-control car_model" name="modelId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label>汽车颜色：</label>
                                <select id="addCarColor" class="js-example-tags form-control car_color" name="colorId">
                                </select>
                            </div>

                            <div class="form-group">
                                <label>汽车车牌：</label>
                                <select id="addCarPlate" class="js-example-tags form-control car_plate" name="plateId">
                                </select>
                            </div>


                            <div class="form-group">
                                <label>车牌号码：</label>
                                <input type="text" maxlength="5" name="carPlate" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>预估到店时间：</label>
                                <input id="addDatetimepicker" readonly type="text" name="arriveTime"
                                       class="form-control datetimepicker"/>
                            </div>

                            <div class="form-group">
                                <label>保养&nbsp;|&nbsp;维修：</label>
                                <select id="addMaintainOrFix" class="js-example-tags form-control" name="maintainOrFix">
                                    <option value="保养">保养</option>
                                    <option value="维修">维修</option>
                                </select>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary" onclick="add()" value="添加">
                                </input>
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
<script src="<%=path %>/js/bootstrapValidator.js"></script>
<script src="<%=path %>/js/content.min.js?v=1.0.0"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path%>/js/customerClient/index.js"></script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: GOD
  Date: 2017/5/24
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String path = request.getContextPath();
%>
<html>
    <head>
        <title>我要预约</title>
        <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
        <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
        <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
        <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
        <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
        <link href="<%=path %>/js/accessories/bootstrap-switch/css/bootstrap3/bootstrap-switch.min.css" rel="stylesheet"
              type="text/css">

        <%@ include file="../common/rightMenu.jsp" %>
        <script src="<%=path %>/js/contextmenu.js"></script>
        <script src="<%=path %>/js/jquery.min.js"></script>
        <script src="<%=path %>/js/bootstrapValidator.js"></script>
        <script src="<%=path %>/js/bootstrap.min.js"></script>
        <script src="<%=path %>/js/bootstrap-table.js"></script>
        <script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
        <script src="<%=path %>/js/sweet-alert.min.js"></script>
        <script src="<%=path %>/js/jquery.formFill.js"></script>
        <script src="<%=path %>/js/select2.full.min.js"></script>
        <script src="<%=path %>/js/zh-CN.js"></script>
        <script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
        <script src="<%=path %>/js/locales/bootstrap-datetimepicker.fr.js"></script>
        <script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
        <script src="<%=path %>/js/accessories/bootstrap-switch/js/bootstrap-switch.min.js"></script>
        <script src="<%=path %>/js/main.js"></script>
        <script src="<%=path%>/js/customerClient/app.js"></script>
    </head>
<body>

<div id="addWin" class="modal fade" style="overflow:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">我要预约</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label>车主姓名：</label>
                                <input type="text" id="addUserName" name="userName" value="${sessionScope.user.userName}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>车主电话：</label>
                                <input type="text" id="addUserPhone" name="userPhone" value="${sessionScope.user.userPhone}" class="form-control"/>
                            </div>

                            <div class="form-group">
                                <label>汽车品牌：</label>
                                <select id="addCarBrand" class="js-example-tags form-control car_brand" onchange="checkBrand(this)" name="brandId">
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
                                <label>汽车公司：</label>
                                <select id="addCompany" class="js-example-tags form-control company" name="companyId">
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

</body>
</html>

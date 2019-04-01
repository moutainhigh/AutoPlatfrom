<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>个人资料</title>

    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/message.css" rel="stylesheet" type="text/css">


</head>
<body>
<div class="wrapper" id="mySelf">
    <div class="modal-content">
        <div class="col-sm-12 b-r">
            <div class="ibox">
                <div class="ibox-content">
                    <form role="form" id="editSelf" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="userId" value="${user.userId}" />
                        <input type="hidden" name="uIcon" value="${user.userIcon}" />
                        <input type="hidden" name="address" value="${user.userAddress}" />
                        <div class="max_div">
                            <br/>
                            <div class="shell">
                                <div class="avatar">
                                    <div id="preview">
                                        <img id="icon" src="<%=path%>${user.userIcon}" onclick="$('#previewImg').click();" name="file" style="border-radius: 50%;"/>
                                    </div>
                                    <input type="file" name="file" onchange="previewImage(this)" style="display: none;" id="previewImg">
                                </div>
                                <br/>
                                <div class="table_info">
                                    <div class="row">
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">邮箱：</label>
                                            <input class="form-control" style="display: initial;" id="editEmail" name="userEmail" type="text" value="${user.userEmail}" />
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">昵称：</label>
                                            <input class="form-control" style="display: initial;" type="text" value="${user.userNickname}"  name="userNickname"/>
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">手机号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editPhone" value="${user.userPhone}"  name="userPhone"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">性别：</label>
                                            <select style="display: initial;" class="form-control"  name="userGender" id="gender">
                                                <c:if test="${user.userGender == 'N'}">
                                                    <option value="N">未知</option>
                                                    <option value="M">男</option>
                                                    <option value="F">女</option>
                                                </c:if>
                                                <c:if test="${user.userGender == 'M'}">
                                                    <option value="M">男</option>
                                                    <option value="N">未知</option>
                                                    <option value="F">女</option>
                                                </c:if>
                                                <c:if test="${user.userGender == 'F'}">
                                                    <option value="F">女</option>
                                                    <option value="N">未知</option>
                                                    <option value="M">男</option>
                                                </c:if>
                                            </select>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">年龄：</label>
                                            <input class="form-control" style="display: initial;" disabled="disabled" placeholder="根据身份证获取年龄" id="age" type="text" name="userAge"/>
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">微博号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="weiboOpen" value="${user.weiboOpenId}"  name="weiboOpenId"/>
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">微信号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="wechatOpen" value="${user.wechatOpenId}"  name="wechatOpenId"/>
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">QQ号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="qqOpen" value="${user.qqOpenId}"  name="qqOpenId"/>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">身份证：</label>
                                            <input class="form-control" onblur="setAgeAndBrithday()" style="display: initial;" type="text" id="editIdentity" value="${user.userIdentity}"  name="userIdentity"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">生日：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" placeholder="根据身份证获取生日" id="birthday" value='<fmt:formatDate value="${user.userBirthday}" pattern="yyyy-MM-dd" />'  name="userBirthday"/>
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">真实姓名：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="userName" value="${user.userName}"  name="userName"/>
                                        </div>
                                        <div class="col-md-3 form-group">
                                            <label class="control-label">居住地址：</label>
                                            <div style="position: relative;">
                                                <input data-toggle="city-picker" style="display: initial;" value="${user.userAddress}" name="userAddress">
                                            </div>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-3">
                                            <label class="control-label">创建时间：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;"value="${user.userCreatedTime}" type="text" id="form_datetime" name="userCreatedTime"/>
                                        </div>
                                        <div class="col-md-3">
                                            <label class="control-label">最近登录：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" value="${user.userLoginedTime}" type="text" id="form_loginedTime" name="userLoginedTime"/>
                                        </div>
                                        <shiro:hasAnyRoles name="companyRepertory, companyReceive, companyArtificer, companySales, companyHumanManager, companyAccounting, companyBuyer, companyEmp">
                                        <div class="col-md-3">
                                            <label class="control-label">基本工资：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" value="${user.userSalary}" name="userSalary"/>
                                        </div>
                                        </shiro:hasAnyRoles>
                                    </div>
                                    <br/>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="control-label">个人描述：</label>
                                            <textarea class="form-control" style="display: initial;" id="chang"  type="textarea" maxlength="150" placeholder="限制字数为150" name="userDes">${user.userDes}</textarea>
                                            <em class="zi_em" id="textShu">150</em>
                                        </div>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </div>
                        <button type="button" id="editSelfButton" class="btn btn-danger btn-block btn-flat" onclick="selfMessage();">提交</button>
                    </form>
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
<script src="<%=path %>/js/index/message.js"></script>

</body>
</html>

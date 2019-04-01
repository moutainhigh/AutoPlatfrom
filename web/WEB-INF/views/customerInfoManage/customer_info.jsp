<%--
  Created by IntelliJ IDEA.
  User: xiao-kang
  Date: 2017/4/13
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>车主基本信息管理</title>


    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/people_info.css" rel="stylesheet" type="text/css">

</head>
<body>

<div class="container">

    <form id="formCustomer" class="form-horizontal">
        <div class="form-group" id="customerDiv" style="margin-top:15px; display: none;">
            <div class="col-sm-2">
                <input type="text" id="userPhone" class="form-control" placeholder="请输入手机号">
            </div>

            <div class="col-sm-2">
                <input type="text" id="userName" class="form-control" placeholder="请输入姓名">
            </div>
            <div class="col-sm-2">
                <button type="button" onclick="selectCustomer()" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeCustomer()" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>

    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="userNickname" >
                昵称
            </th>
            <th data-field="userName" >
                姓名
            </th>
            <th data-field="userEmail" >
                邮箱
            </th>
            <th data-field="userGender" data-formatter="gender" >
                性别
            </th>
            <th data-field="userPhone" >
                手机号
            </th>
            <th data-field="userStatus" data-formatter="status">
                当前状态
            </th>
            <th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a><button onclick="showAddWin();" type="button" id="add" class="btn btn-default" >
                <i class="glyphicon glyphicon-plus"></i> 添加
            </button></a>
            <a><button onclick="showStatusWin_N();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看不可用
            </button></a>
            <a><button onclick="showStatusWin_Y();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看可用
            </button></a>
            <a><button onclick="showStatusWin();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 查看全部车主
            </button></a>
            <a><button onclick="selectCustomerWin();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i> 条件查询
            </button></a>
        </div>
        </tbody>
    </table>
</div>


<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                        <h3 class="m-t-none m-b">添加车主</h3>
                        <form role="form" id="addForm">
                            <div class="form-group">
                                <label class="control-label">姓名：</label>
                                <input type="text"  name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">昵称：</label>
                                <input type="text" name="userNickname"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">邮箱：</label>
                                <input type="text" id="userEmail" name="userEmail"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <p><label class="control-label">密码：</label></p>
                                <input type="password" id="pwd" name="userPwd"
                                       class="form-control" style="width: 75%; display: initial;"/>
                                <button type="button" onclick="defaultPwd()"style="float: right; margin-right: 5px" class="btn btn-success" data-toggle="tooltip" data-placement="top" title="默认密码为123456">使用默认密码</button>
                            </div>
                            <div class="form-group">
                                <label class="control-label">性别：</label>
                                <select class="form-control" name="userGender">
                                    <option value="N" selected = "selected">未知</option>
                                    <option value="M">男</option>
                                    <option value="F">女</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="control-label">手机号：</label>
                                <input type="text" maxlength="11" name="userPhone"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">居住地址：</label>
                                <div style="position: relative;">
                                    <input data-toggle="city-picker" id="ress" class="address" name="userAddress">
                                </div>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" class="btn btn-primary" onclick="add()" value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="myModal" class="modal fade" aria-hidden="true">
    <div class="modal-dialog" style="width: 92%;margin-top: 25px">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h4 class="m-t-none m-b">车主个人信息</h4>
                        <div class="form_info">
                            <form role="form" method="post" id="editModal" class="form_form" onkeydown="if(event.keyCode==13){return false;}" enctype="multipart/form-data">
                                <input type="hidden" name="userId" attr="user.userId" />
                                <input type="hidden" name="uIcon" attr="user.userIcon"/>
                                <div class="form_img">
                                    <div id="preview">
                                        <img id="icon" attr="user.userIcon" name="file" style="border-radius: 50%;"/>
                                    </div>
                                    <input type="file" name="file" onchange="previewImage(this)" style="display: none;" id="previewImg">
                                </div>
                                <div class="info">
                                    <div class="row">
                                        <div class="col-md-4 form-group">
                                            <label class="control-label">邮箱：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editEmail" attr="user.userEmail" name="userEmail"/>
                                        </div>
                                        <div class="col-md-4 form-group">
                                            <label class="control-label">昵称：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="nickname" attr="user.userNickname" name="userNickname"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">性别：</label>
                                            <select style="display: initial;" class="form-control" name="userGender" attr="user.userGender" id="gender">
                                                <option value="N" selected = "selected">未知</option>
                                                <option value="M">男</option>
                                                <option value="F">女</option>
                                            </select>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label">年龄：</label>
                                            <input class="form-control" style="display: initial;" disabled="disabled" id="age" type="text" attr="user.userAge" name="useAge"/>
                                        </div>
                                        <div class="col-md-4 form-group">
                                            <label class="control-label" style="margin-left: -14px">手机号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editPhone" attr="user.userPhone" name="userPhone"/>
                                        </div>
                                        <div class="col-md-4 form-group">
                                            <label class="control-label" style="margin-left: -14px">身份证：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="editIdentity" attr="user.userIdentity" name="userIdentity"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4 form-group">
                                            <label class="control-label" style="margin-left: -14px">微信号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="wechatOpen" attr="user.wechatOpenId" name="wechatOpenId"/>
                                        </div>
                                        <div class="col-md-4 form-group">
                                            <label class="control-label" style="margin-left: -8px">QQ号：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="qqOpen" attr="user.qqOpenId" name="qqOpenId"/>
                                        </div>
                                        <div class="col-md-4 form-group">
                                            <label class="control-label">微博：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="weiboOpen" attr="user.weiboOpenId" name="weiboOpenId"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label">生日：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="birthday" attr="user.userBirthday" name="userBirthday"/>
                                        </div>
                                        <div class="col-md-4 form-group">
                                            <label class="control-label" style="margin-left: -28px">真实姓名：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userName" name="userName"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">新老用户：</label>
                                            <input class="form-control" style="display: initial;" id="client" disabled="disabled" type="text"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label"  style="margin-left: -28px">创建时间：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="form_datetime" name="userCreatedTime"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">最近登录：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="form_loginedTime" name="userLoginedTime"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">所属角色：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="role"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">居住地址：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="address" attr="user.userAddress" name="userAddress"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">当前状态：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="status" disabled="disabled" name="userStatus"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="control-label" style="margin-left: -28px">个人描述：</label>
                                            <textarea class="form-control" style="display: initial;" id="chang" type="textarea" maxlength="150" placeholder="限制字数为150" attr="user.userDes" name="userDes"></textarea>
                                            <em class="zi_em" id="textShu">150</em>
                                        </div>
                                    </div>
                                </div>
                                <button class="form_save" data-dismiss="modal" aria-hidden="true">关闭</button>
                                <button class="form_save" type="button" id="editModalButton" onclick="editModal()">保存</button>
                            </form>
                            <button class="button_form" id="button" onclick="$('#previewImg').click();">更换头像</button>
                        </div>
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
<script src="<%=path %>/js/bootstrap-table.js"></script>
<script src="<%=path %>/js/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path %>/js/sweet-alert.min.js"></script>
<script src="<%=path %>/js/jquery.formFill.js"></script>
<script src="<%=path %>/js/customerManage/customerInfo.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=path %>/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
</body>
</html>

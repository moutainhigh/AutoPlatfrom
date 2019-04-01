<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-04-14
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>管理员管理</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrap-table.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/bootstrapValidator.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/select2.min.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/city-picker.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/people_info.css" rel="stylesheet" type="text/css">
    <link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css">
    <style>
        .form_form .form_save{
        position: relative;
        top: 9px;
        float: right;
        margin-right: 32px;
        width: 70px;
        height: 25px;
        font-family: "微软雅黑";
        color: #d4d4d4;
        background-color: #909090;
        border: 0px;
        outline: none;
        border-radius:5px;
        }

        .button_form{
            width: 265px;
            height: 50px;
            margin-left: 12px;
            margin-top: 0px;
            font-family: "微软雅黑";
            color: #d4d4d4;
            background-color: #909090;
            font-size: 20px;
            border: 0px;
            outline: none;
            border-radius:5px;
        }
    </style>
</head>
<body onload="getSessionUserId('${sessionScope.user.userId}')">

<div class="container">
    <form id="formSearch" class="form-horizontal">
        <div class="form-group" id="searchDiv" style="margin-top:15px; display: none;">
            <div class="col-sm-2" style="margin-left: -15px;">
                <input type="text" id="searchName" class="form-control" placeholder="姓名：">
            </div>
            <div class="col-sm-2" style="margin-left: -15px;">
                <input type="text" id="searchEmail" class="form-control" placeholder="邮箱：">
            </div>

            <div class="col-sm-2" style="margin-left: -15px;">
                <input type="text" id="searchPhone" class="form-control" placeholder="手机号：">
            </div>
            <div class="col-sm-2">
                <button type="button" onclick="bySelectSearch()" class="btn btn-primary">
                    查询
                </button>
                <button type="button" onclick="closeSearchForm()" class="btn btn-default">
                    关闭
                </button>
            </div>
        </div>
    </form>
    <table class="table table-hover" id="cusTable"
           data-pagination="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-showColumns="true"
           data-height="520">
        <thead>
        <tr>
            <th data-field="state" data-checkbox="true"></th>
            <th data-field="userName">
                姓名
            </th>
            <th data-field="role.roleDes" data-formatter="roleName">
                角色
            </th>
            <th data-field="userEmail">
                邮箱
            </th>
            <th data-field="userPhone">
                手机
            </th>
            <th data-field="userNickname">
                昵称
            </th>
            <th data-field="userIdentity">
                身份证
            </th>
            <th data-field="userGender" data-formatter="gender">
                性别
            </th>
            <th data-field="userBirthday" data-formatter="birthday">
                生日
            </th>
            <th data-field="userAddress">
                地址
            </th>
            <th data-field="qqOpenId">
                QQ
            </th>
            <th data-field="weiboOpenId">
                微博
            </th>
            <th data-field="wechatOpenId">
                微信
            </th>
            <th data-field="userDes">
                描述
            </th>
            <th data-field="company.companyName">
                公司
            </th>
            <th data-field="userCreatedTime" data-formatter="formatterDate">
                创建时间
            </th>
            <th data-field="userLoginedTime" data-formatter="formatterDate">
                最近登陆时间
            </th>
            <th data-field="userStatus" data-formatter="status">
                状态
            </th>
            <th data-field="caozuo" data-formatter="operateFormatter" data-events="operateEvents">
                操作
            </th>
        </tr>
        </thead>
        <tbody>
        <div id="toolbar" class="btn-group">
            <a>
                <button onclick="showAddWin()" type="button" id="add" class="btn btn-default">
                    <i class="glyphicon glyphicon-plus"></i> 添加
                </button>
            </a>
            <a>
                <button onclick="showEditWin();" type="button" id="edit" class="btn btn-default">
                    <i class="glyphicon glyphicon-pencil"></i> 修改
                </button>
            </a>
            <a>
                <button onclick="queryAll();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>
                    查询全部
                </button>
            </a>
            <a>
                <button onclick="querySystem();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>
                    查询管理员
                </button>
            </a>
            <a>
                <button onclick="queryCompany();" type="button" class="btn btn-default">
                    <i class="glyphicon glyphicon-search"></i>
                    查询董事长
                </button>
            </a>
            <a><button id="showButton" onclick="showSearchForm();" type="button" class="btn btn-default">
                <i class="glyphicon glyphicon-search"></i>
                条件查询
            </button></a>
        </div>
        </tbody>

    </table>
</div>


<%--<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">修改模块信息</h3>
                        <form role="form" id="updateForm">
                            <input type="hidden" attr="module.moduleId" name="moduleId"/>
                            <div class="form-group">
                                <label>模块名称：</label>
                                <input type="text" attr="module.moduleName" name="moduleName" class="form-control"/>

                                <label>模块描述：</label>
                                <textarea attr="module.moduleDes" type="textarea" name="moduleDes"
                                          class="form-control"></textarea>
                            </div>

                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" class="btn btn-primary" value="修改" onclick="updateModule()">
                                </input>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>--%>

<div id="addWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">添加管理员</h3>
                        <form role="form" id="addForm">
                            <input type="hidden" id="birthday" name="userBirthday"/>
                            <input type="hidden" id="gender" name="userGender"/>
                            <%--<div class="form-group">
                                <select id="adminTypeSelect" onchange="adminSelect('adminTypeSelect');"
                                        class="js-example-tags form-control adminCAndSO"
                                        name="adminTypeId">
                                </select>
                            </div>--%>
                            <%--<div class="form-group">
                                <select id="addCompany" disabled="disabled"
                                        class="js-example-tags form-control admin_company"
                                        name="companyId"></select>
                            </div>--%>
                            <div class="form-group">
                                <label class="control-label">姓名：</label>
                                <input type="text" name="userName" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">邮箱：</label>
                                <input type="email" name="userEmail"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <p><label class="control-label">密码：</label></p>
                                <input type="password" id="pwd" name="userPwd"
                                       class="form-control" style="width: 75%; display: initial;"/>
                                <button type="button" onclick="defaultPwd()" style="float: right"
                                        class="btn btn-success" data-toggle="tooltip" data-placement="top"
                                        title="默认密码为123456">使用默认密码
                                </button>
                            </div>
                            <div class="form-group">
                                <label class="control-label">手机号：</label>
                                <input type="text" name="userPhone"
                                       class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label class="control-label">居住地址：</label>
                                <div style="position: relative;">
                                    <input data-toggle="city-picker" class="address" name="userAddress">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label">身份证：</label>
                                <input type="text" name="userIdentity" onblur="getBirthday(this, 1)"
                                       class="form-control"/>
                            </div>
                            <div class="modal-footer" style="overflow:hidden;">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">关闭
                                </button>
                                <input type="button" id="addButton" onclick="add()" class="btn btn-primary" value="添加">
                                </input>
                                <input type="reset" name="reset" style="display: none;"/>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<div id="editWin" class="modal fade" aria-hidden="true">
    <div class="modal-dialog" style="width: 92%;margin-top: 25px">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h4 class="m-t-none m-b">修改管理员</h4>
                        <div class="form_info">
                            <form role="form" method="post" id="editForm" class="form_form" onkeydown="if(event.keyCode==13){return false;}" enctype="multipart/form-data">
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
                                        <div class="col-md-4">
                                            <label class="control-label">邮箱：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userEmail" name="userEmail"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">昵称：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userNickname" name="userNickname"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">性别：</label>
                                            <select style="display: initial;" class="form-control" name="userGender" attr="user.userGender" id="editGender">
                                                <option value="" selected = "selected">未选择</option>
                                                <option value="M">男</option>
                                                <option value="F">女</option>
                                            </select>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -14px">手机号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userPhone" name="userPhone"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -14px">身份证：</label>
                                            <input class="form-control" style="display: initial;" type="text" id="identity" onblur="getBirthday(this, 2)" attr="user.userIdentity" name="userIdentity"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">所属职位：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="role"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -14px">微信号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.wechatOpenId" name="wechatOpenId"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -8px">QQ号：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.qqOpenId" name="qqOpenId"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">微博：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.weiboOpenId" name="weiboOpenId"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label">生日：</label>
                                            <input class="form-control" style="display: initial;" type="text" disabled="disabled" id="editBirthday" name="userBirthday"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label">姓名：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userName" name="userName"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">居住地址：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userAddress" name="userAddress"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label class="control-label"  style="margin-left: -28px">入职时间：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="createTime" name="userCreatedTime"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">最近登录：</label>
                                            <input class="form-control" disabled="disabled" style="display: initial;" type="text" id="loginTime" name="userLoginedTime"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label class="control-label" style="margin-left: -28px">基本工资：</label>
                                            <input class="form-control" style="display: initial;" type="text" attr="user.userSalary" name="userSalary"/>
                                        </div>
                                        <br />
                                        <br />
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <label class="control-label" style="margin-left: -28px">个人描述：</label>
                                            <textarea class="form-control" style="display: initial;" id="chang" type="textarea" maxlength="200" placeholder="限制字数为200" attr="user.userDes" name="userDes"></textarea>
                                            <em class="zi_em" id="textShu">200</em>
                                        </div>
                                    </div>
                                </div>
                                <button class="form_save" data-dismiss="modal" aria-hidden="true">关闭</button>
                                <button class="form_save" id="editButton" onclick="edit()">保存</button>
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
<script src="<%=path %>/js/system/admin.js"></script>
<script src="<%=path %>/js/select2.full.min.js"></script>
<script src="<%=path %>/js/zh-CN.js"></script>
<script src="<%=path %>/js/main.js"></script>
<script src="<%=path %>/js/city-picker.data.js"></script>
<script src="<%=path %>/js/city-picker.js"></script>
<script src="<%=path %>/js/jquery.form.min.js"></script>
</body>
</html>

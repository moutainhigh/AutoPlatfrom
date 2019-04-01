<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/1
  Time: 8:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <title>车主后台主页</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
    <link href="<%=path %>/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=path %>/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=path %>/css/animate.min.css" rel="stylesheet">
    <link href="<%=path %>/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span><img style="width: 70px;height: 70px" alt="image" class="img-circle"
                                   src="<%=path %>${sessionScope.user.userIcon}"/></span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong
                                       class="font-bold">${sessionScope.user.userName}</strong></span>
                                <span class="text-muted text-xs block">
									<shiro:hasRole name="carOwner">车主</shiro:hasRole>
                                    <b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a id="personalData" class="J_menuItem" href="<%=path %>/message/personal_message">个人资料</a>
                            </li>
                            <li><a class="J_menuItem" href="<%=path %>/pwd/personal_pwd">修改密码</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="<%=path %>/login/logout">安全退出</a>
                            </li>
                        </ul>
                    </div>
                    <div class="logo-element">H+
                    </div>
                </li>

                <li>
                    <a href="#"><i class="glyphicon glyphicon-phone-alt"></i> <span
                            class="nav-label">维修保养预约管理</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path %>/appointment/my_app">我的预约</a>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path %>/customerClient/app">我要预约</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa-hand-lizard-o"></i> <span class="nav-label">维修保养接待管理</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path %>/checkin/my_checkin">我的接待</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="glyphicon glyphicon-wrench"></i> <span class="nav-label">维修保养管理</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path%>/customerClient/record">我的维修保养记录</a>
                        </li>
                        <li><a class="J_menuItem" href="<%=path%>/progress/progress_page">我的维修保养进度</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="glyphicon glyphicon-shopping-cart"></i> <span
                            class="nav-label">结算提车</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path %>/record/my_reminder">我的已完成的维修保养</a>
                        </li>
                        <li><a class="J_menuItem" href="<%=path %>/bill/my_bill">我的收费单据管理</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="fa fa fa-bar-chart-o"></i> <span class="nav-label">报表统计</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path%>/reportStatistics/consumption_page">我的维修保养消费统计</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="#"><i class="glyphicon glyphicon-envelope"></i> <span class="nav-label">投诉</span><span
                            class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path%>/complaint/show_complaint">我的投诉</a>
                        </li>
                    </ul>
                </li>

                <%--<li>
                    <a href="#"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                        <span class="nav-label">系统管理</span><span
                                class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li><a class="J_menuItem" href="<%=path%>/role/info">个人资料</a>
                        </li>
                        <li><a class="J_menuItem" href="<%=path %>/admin/info">修改密码</a>
                        </li>
                    </ul>
                </li>--%>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                        class="fa fa-bars"></i> </a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li class="dropdown hidden-xs">
                        <a class="right-sidebar-toggle" aria-expanded="false">
                            <i class="fa fa-tasks"></i> 主题
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content" id="rightEvent">
                    <a href="javascript:;" class="active J_menuTab" data-id="<%=path %>/customer/home">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseThis"><a>关闭当前选项卡</a>
                    </li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="<%=path %>/login/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" id="J_iframe" name="iframe0" width="100%" height="100%"
                    src="<%=path %>/customer/home"
                    frameborder="0" data-id="<%=path %>/customer/home" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">
                &copy;版权所有 创意科技
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">

            <ul class="nav nav-tabs navs-3">

                <li class="active">
                    <a data-toggle="tab" href="#tab-1">
                        <i class="fa fa-gear"></i> 主题
                    </a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <div class="sidebar-title">
                        <h3><i class="fa fa-comments-o"></i> 主题设置</h3>
                        <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                    </div>
                    <div class="skin-setttings">
                        <div class="title">主题设置</div>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox"
                                           id="collapsemenu">
                                    <label class="onoffswitch-label" for="collapsemenu">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox"
                                           id="fixednavbar">
                                    <label class="onoffswitch-label" for="fixednavbar">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox"
                                           id="boxedlayout">
                                    <label class="onoffswitch-label" for="boxedlayout">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <a href="#" class="s-skin-0">
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                                    默认皮肤
                                </span>
                            </div>
                        </a>
                        <a href="#" class="s-skin-1">
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                                    蓝色主题
                                </span>
                            </div>
                        </a>
                        <a href="#" class="s-skin-3">
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                                    黄色/紫色主题
                                </span>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
<script src="<%=path %>/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="<%=path %>/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
<script src="<%=path %>/js/hplus.min.js?v=4.1.0"></script>
<script src="<%=path %>/js/contabs.min.js" type="text/javascript"></script>
<script src="<%=path %>/js/plugins/pace/pace.min.js"></script>

<script src="<%=path %>/js/index/home.js"></script>
</body>
</html>

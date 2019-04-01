<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/17
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>联系我们</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="author" content="" />
    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content="" />
    <meta name="twitter:image" content="" />
    <meta name="twitter:url" content="" />
    <meta name="twitter:card" content="" />

    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">

    <!-- Animate.css -->
    <link rel="stylesheet" href="<%=path%>/css/animate.css">
    <!-- Icomoon Icon Fonts-->
    <link rel="stylesheet" href="<%=path%>/css/icomoon.css">
    <!-- Themify Icons-->
    <link rel="stylesheet" href="<%=path%>/css/themify-icons.css">
    <!-- Bootstrap  -->
    <link rel="stylesheet" href="<%=path%>/css/bootstrap.css">

    <!-- Magnific Popup -->
    <link rel="stylesheet" href="<%=path%>/css/magnific-popup.css">

    <link href="<%=path %>/css/sweet-alert.css" rel="stylesheet" type="text/css">

    <!-- Owl Carousel  -->
    <link rel="stylesheet" href="<%=path%>/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=path%>/css/owl.theme.default.min.css">

    <!-- Theme style  -->
    <link rel="stylesheet" href="<%=path%>/css/style.css">

    <!-- Modernizr JS -->
    <script src="<%=path%>/js/modernizr-2.6.2.min.js"></script>
    <%--rightMenu--%>
    <script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
    <style type="text/css">
        body,div,ul,li,p,a,img{
            padding: 0;
            margin: 0;
        }
        /*右侧悬浮菜单*/
        .slide{
            width: 50px;
            height: 250px;
            position: fixed;
            top: 50%;
            margin-top: -126px;
            background: #999999;
            right: 0;
            border-radius: 5px 0 0 5px;
            z-index: 999;
        }
        .slide ul{
            list-style: none;
        }
        .slide .icon li{
            width: 49px;
            height: 50px;
            background: url(<%=path%>/img/icon.png) no-repeat;
        }
        .slide .icon .up{
            background-position:-330px -120px ;
        }
        .slide .icon li.qq{
            background-position:-385px -73px ;
        }
        .slide .icon li.tel{
            background-position:-385px -160px ;
        }
        .slide .icon li.wx{
            background-position:-385px -120px ;
        }
        .slide .icon li.down{
            background-position:-330px -160px ;
        }
        .slide .info{
            top: 50%;
            height: 147px;
            position: absolute;
            right: 100%;
            background: #999999;
            width: 0px;
            overflow: hidden;
            margin-top: -73.5px;
            transition:0.5s;
            border-radius:4px 0 0 4px ;
        }
        .slide .info.hover{
            width: 145px;

        }
        .slide .info li{
            width: 145px;
            color: #FFFFCC;
            text-align: center;
        }
        .slide .info li p{
            font-size: 1.1em;
            line-height: 2em;
            padding: 15px;
            text-align: left;
        }
        .slide .info li.qq p a{
            display: block;
            margin-top: 12px;
            width: 100px;
            height: 32px;
            line-height: 32px;
            color: #CCCCCC;
            font-size: 16px;
            text-align: center;
            text-decoration: none;
            border: 1px solid #CCCCCC;
            border-radius: 5px;
        }
        .slide .info li.qq p a:hover{
            color: #FFFFFF;
            border: none;
            background: #CCCCCC;
        }
        .slide .info li div.img{
            height: 100%;
            background: #DEFFF9;
            margin: 15px;
        }
        .slide .info li div.img img{
            width: 100%;
            height: 100%;
        }
        /*控制菜单的按钮*/
        .index_cy{
            width: 30px;
            height: 30px;
            background: url(<%=path%>/img/index_cy.png);
            position: fixed;
            right: 0;
            top: 50%;
            margin-top: 140px;
            background-position: 62px 0;
            cursor: pointer;
        }
        .index_cy2{
            width: 30px;
            height: 30px;
            background: url(<%=path%>/img/index_cy.png);
            position: fixed;
            right: 0;
            top: 50%;
            margin-top: 140px;
            background-position: 30px 0;
            cursor: pointer;
        }

        /*自适应 当屏小于1050时隐藏*/
        @media screen and (max-width: 300px) {
            .slide{
                display: none;
            }
            #btn{
                display: none;
            }

        }
    </style>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="<%=path%>/js/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div class="gtco-loader"></div>

<%@ include file="../index/rightMenu.jsp" %>

<div id="page">

    <div class="page-inner">
        <nav class="gtco-nav" role="navigation">
            <div class="gtco-container">

                <div class="row">
                    <div class="col-sm-4 col-xs-12">
                        <div id="gtco-logo"><a href="<%=path%>/index">首页 <em>.</em></a></div>
                    </div>
                    <div class="col-xs-8 text-right menu-1">
                        <ul>
                            <li><a href="<%=path%>/customerClientWeb/tour">功能简介</a></li>
                            <li><a href="<%=path%>/customerClientWeb/features">平台特性</a></li>
                            <li><a href="<%=path%>/customerClientWeb/pricing">产品收费</a></li>
                            <li class="has-dropdown">
                                <a href="#">车主入口</a>
                                <ul class="dropdown">
                                    <li><a href="<%=path%>/customerClientWeb/appointment">车主预约</a></li>
                                    <li><a href="<%=path%>/customerClientWeb/customerCar">进度跟踪</a></li>
                                </ul>
                            </li>
                            <li class="btn-cta"><a href="<%=path%>/customerClientWeb/contact"><span>入驻 我们</span></a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </nav>

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/contact.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">
                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">提交您的意向或投诉</span>
                                <h1>联系我们</h1>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </header>


        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="col-md-6 animate-box">
                            <h3>联系方式</h3>
                            <form method="post" id="intentionCompany">
                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" for="name">您的称呼</label>
                                        <input type="text" id="name" name="name" class="form-control" maxlength="4" placeholder="请输入您的称呼">
                                    </div>
                                    <div id="nameError" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" for="email">邮箱</label>
                                        <input type="email" id="email" name="email" class="form-control" placeholder="输入您的邮箱">
                                    </div>
                                    <div id="emailError" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" for="phone">填写手机号</label>
                                        <input type="text" id="phone" name="phone" class="form-control" maxlength="11" placeholder="输入您的手机号">
                                    </div>
                                    <div id="phoneError" style="color: red;padding-left: 20px;"></div>
                                </div>

                                <div class="row form-group">
                                    <div class="col-md-12">
                                        <label class="sr-only" for="message">信息</label>
                                        <textarea id="message" name="des" cols="30" rows="10" class="form-control" placeholder="填写您的意向信息或投诉信息,我们将以最快的方式联系您."></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="button" onclick="intentionCompany()" value="提交" class="btn btn-primary">
                                </div>

                            </form>
                        </div>
                        <div class="col-md-5 col-md-push-1 animate-box">

                            <div class="gtco-contact-info">
                                <h3>公司信息</h3>
                                <ul>
                                    <li class="address">地址: 中国江西省赣州市章贡区, <br> 银河大道阳明国际B2栋12楼</li>
                                    <li class="phone"><a href="tel://1234567920">+ 1235 2355 98</a></li>
                                    <li class="email"><a href="mailto:info@yoursite.com">Jango@google.com</a></li>
                                    <li class="url"><a href="http://www.jangomp.com">www.jangomp.com</a></li>
                                </ul>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../common/footer.jsp" %>
    </div>

</div>

<div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
</div>

<!-- jQuery -->
<script src="<%=path%>/js/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="<%=path%>/js/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="<%=path%>/js/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="<%=path%>/js/jquery.waypoints.min.js"></script>
<!-- Carousel -->
<script src="<%=path%>/js/owl.carousel.min.js"></script>
<!-- countTo -->
<script src="<%=path%>/js/jquery.countTo.js"></script>
<!-- Magnific Popup -->
<script src="<%=path%>/js/jquery.magnific-popup.min.js"></script>
<script src="<%=path%>/js/magnific-popup-options.js"></script>
<%--rightMenu--%>
<div id="btn" class="index_cy"></div>
<script type="text/javascript">
    $(function(){

        $('.slide .icon li').not('.up,.down').mouseenter(function(){
            $('.slide .info').addClass('hover');
            $('.slide .info li').hide();
            $('.slide .info li.'+$(this).attr('class')).show();//.slide .info li.qq
        });
        $('.slide').mouseleave(function(){
            $('.slide .info').removeClass('hover');
        });

        $('#btn').click(function(){
            $('.slide').toggle();
            if($(this).hasClass('index_cy')){
                $(this).removeClass('index_cy');
                $(this).addClass('index_cy2');
            }else{
                $(this).removeClass('index_cy2');
                $(this).addClass('index_cy');
            }

        });

    });
</script>
<!-- Main -->
<script src="<%=path%>/js/webmain.js"></script>

<%--login js--%>
<script src="<%=path%>/js/index/login.js"></script>

<script src="<%=path %>/js/sweet-alert.min.js"></script>

<script>
    /** 添加意向公司 */
    function intentionCompany() {
        if (verificationForm()) {
            var url = "/intention/add";
            $.post(url, $("#intentionCompany").serialize(),
                    function (data) {
                        if (data.result == "success") {
                            swal("提交成功", data.message, "success");
                        } else {
                            swal("提交失败", data.message, "error");
                        }
                        $("#name").val('');
                        $("#email").val('');
                        $("#phone").val('');
                        $("#message").val('');
                    }, "json");
        }
    }

    /** 验证form表单 */
    function verificationForm() {
        var name = $("#name").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        if (name != null && name != "") {
            if (isName(name)) {
                $("#nameError").html('');
                if (email != null && email != "" && phone != null && phone != "") {
                    if (isEmail(email)) {
                        $("#emailError").html("");
                        if (isPhone(phone)) {
                            $("#phoneError").html("");
                            return true;
                        } else {
                            $("#emailError").html("");
                            $("#phoneError").html("请输入正确的手机号");
                        }
                    } else {
                        $("#phoneError").html("");
                        $("#emailError").html("请输入正确的邮箱");
                    }
                } else {
                    if (email != null && email != "") { // 输入的是邮箱
                        if (isEmail(email)) {
                            $("#emailError").html("");
                            return true;
                        }
                        $("#phoneError").html("");
                        $("#emailError").html("请输入正确的邮箱");
                    } else if (phone != null && phone != "") { // 输入的是手机号
                        if (isPhone(phone)) {
                            $("#phoneError").html("");
                            return true;
                        } else {
                            $("#emailError").html("");
                            $("#phoneError").html("请输入正确的手机号");
                        }
                    } else {
                        $("#emailError").html("");
                        $("#phoneError").html("手机号或邮箱至少输入一个");
                    }
                }
            } else {
                $("#nameError").html("请输入正确的称呼");
            }
        } else {
            $("#nameError").html("请输入您的称呼");
        }
        return false;
    }

    /** 验证用户名是否合法 */
    function isName(str) {
        var reg = /^[^a-zA-Z0-9]+$/;
        return reg.test(str);
    }


</script>

</body>
</html>



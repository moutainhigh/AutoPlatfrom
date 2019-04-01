<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/17
  Time: 20:01
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
    <title>产品收费</title>
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

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/price.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">
                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">平台收费</span>
                                <h1>为每户入驻的商家</h1>
                                <h1>提供定制的产品</h1>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </header>



        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <h2>入驻套餐</h2>
                        <p>根据需求定制属于你自己最实惠的套餐</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <div class="price-box">
                            <h2 class="pricing-plan">试用</h2>
                            <div class="price"><sup class="currency">￥</sup>15<small>/月</small></div>
                            <p>推荐小型门店商家试用了解</p>
                            <hr>
                            <ul class="pricing-info">
                                <li>数据存储 1GB</li>
                                <li>短信提醒 500条</li>
                                <li>动态员工支配</li>
                                <li>使用天数 30天</li>
                            </ul>
                            <a href="<%=path%>/customerClientWeb/contact" class="btn btn-default btn-sm">立即试用</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="price-box">
                            <h2 class="pricing-plan">入驻</h2>
                            <div class="price"><sup class="currency">￥</sup>300<small>/年</small></div>
                            <p>推荐中型商家入驻</p>
                            <hr>
                            <ul class="pricing-info">
                                <li>云数据存储 5GB</li>
                                <li>短信提醒 5000条</li>
                                <li>动态员工支配</li>
                                <li>工作日提供技术支持</li>
                                <li>使用天数 365天</li>
                                <li>免费版本更新</li>
                            </ul>
                            <a href="<%=path%>/customerClientWeb/contact" class="btn btn-default btn-sm">立即入驻</a>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="price-box popular">
                            <div class="popular-text">Popular</div>
                            <h2 class="pricing-plan">永久VIP</h2>
                            <div class="price"><sup class="currency">￥</sup>998<small>/~</small></div>
                            <p>推荐大型商家入驻</p>
                            <hr>
                            <ul class="pricing-info">
                                <li>云数据存储 5GB</li>
                                <li>用户数据永久备份</li>
                                <li>短信提醒 5000条</li>
                                <li>动态员工支配</li>
                                <li>24小时提供技术支持</li>
                                <li>使用天数 永久</li>
                                <li>免费版本更新</li>
                            </ul>
                            <a href="<%=path%>/customerClientWeb/contact" class="btn btn-primary btn-sm">立即抢购</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="gtco-section">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box">
                        <h2>常见问题</h2>
                        <p></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <ul class="fh5co-faq-list">
                            <li class="animate-box">
                                <h2>客户数据保密吗?</h2>
                                <p>入驻平台后我们会为所有用户信息保密并且签署相关合同是不存在泄露隐私问题的.</p>
                            </li>
                            <li class="animate-box">
                                <h2>入驻后不会使用怎么办?</h2>
                                <p>入驻后的商家我们会发一份详细的使用文档并且会安排相关的客服人员教您使用的.</p>
                            </li>
                            <li class="animate-box">
                                <h2>如果平台系统出现问题怎么办?</h2>
                                <p>平台使用过程中如果出现问题可以反馈给我们客服,客服会第一时间反馈给我们的程序员为您提供解决方案.</p>
                            </li>
                            <li class="animate-box">
                                <h2>手机号可以重复注册吗?</h2>
                                <p>我们平台的手机号是一人一号制度,手机号注册过的不能够再注册您可以通过手机号找回密码.</p>
                            </li>
                            <li class="animate-box">
                                <h2>我能够调取客户信息吗?</h2>
                                <p>我们有权利为顾客保密并且只提供基础信息给商家,做到顾客的资料不泄露.</p>
                            </li>
                            <li class="animate-box">
                                <h2>入驻以后是否有保障?</h2>
                                <p>入驻的商家我们提供一切援助,并且对所有商家顾客负责,如果有相应问题第一时间通知商家让所有用户放心使用.</p>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../common/subscribeMe.jsp" %>
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

</body>
</html>



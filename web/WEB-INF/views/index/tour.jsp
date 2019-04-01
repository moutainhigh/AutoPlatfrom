<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/17
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>平台特性</title>
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

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/tour.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">


                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">安心 放心 省心</span>
                                <h1>让您的爱车维保变得</h1>
                                <h1>简单&nbsp;&nbsp;高效</h1>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        </header>



        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 text-left gtco-heading">
                        <h2>PC电脑端</h2>
                        <p>完美的自适应高效便捷办公</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5">
                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>清晰的界面</h3>
                                <p>简洁明了的界面显示给顾客完美的体验和操作,人人都能轻松掌控.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>报表统计查询</h3>
                                <p>满足汽车服务门店的所有盈利需求！服务开单、财务管理化繁为简，削减成本
                                    客户管理 、数据分析、微信客户管理精准营销，提高效率</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>客户管理</h3>
                                <p>管家式会员管理，专治客户流失
                                    线上功能操作，订单和服务记录查询，积分礼品制，打造会员优越感
                                    会员资料库录入，便于大数据统计和精准营销，让客户产生好感和依赖</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="icon-check"></i>
						</span>
                            <div class="feature-copy">
                                <h3>服务开单</h3>
                                <p>智能化服务跟进，高效满足需求
                                    24小时在线预约功能，随时跟进用户的到店情况
                                    提供线上购买套餐卡、账户充值和线下开单2种开单选择
                                    支持多项收款和挂账、结账功能，支持导出，财务智能化</p>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-7 macbook-wrap animate-box" data-animate-effect="fadeInRight">
                        <img src="<%=path%>/images/macbook.png" alt="Free HTML5 Bootstrap Template">
                    </div>
                </div>
            </div>
        </div>

        <div class="gtco-section border-bottom">
            <div class="gtco-container">

                <div class="row">
                    <div class="col-md-8 text-left gtco-heading">
                        <h2>手机端</h2>
                        <p>手机端提供客户查询爱车保养情况,对所有流程一清二楚</p>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <img src="<%=path%>/images/iphone.png" class="img-responsive" alt="Free HTML5 Bootstrap Template">
                    </div>
                    <div class="col-md-6 mt-sm">
                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-layers-alt"></i>
						</span>
                            <div class="feature-copy">
                                <h3>跟踪流程</h3>
                                <p>从预约到提车所有流程都能够在手机端查询数据便捷商务</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-key"></i>
						</span>
                            <div class="feature-copy">
                                <h3>私人用户</h3>
                                <p>用户数据平台给予保密,让你不再担心信息泄露不再被骚扰.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-image"></i>
						</span>
                            <div class="feature-copy">
                                <h3>个性化</h3>
                                <p>用户个性化能够上传自己的头像对自己个人信息全面编辑.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-heart"></i>
						</span>
                            <div class="feature-copy">
                                <h3>投诉信息</h3>
                                <p>用户投诉反馈给商家提高维保质量,让消费者权益最大化不被侵害.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-infinite"></i>
						</span>
                            <div class="feature-copy">
                                <h3>报表查询</h3>
                                <p>用户可打印自己的账单查询自己的消费情况不再给坑.</p>
                            </div>
                        </div>

                        <div class="feature-left animate-box" data-animate-effect="fadeInLeft">
						<span class="icon">
							<i class="ti-credit-card"></i>
						</span>
                            <div class="feature-copy">
                                <h3>一键支付</h3>
                                <p>用户能够通过微信支付宝一键支付,让提车不再繁琐.</p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <div class="gtco-section border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading">
                        <h2>入驻商家&nbsp;案例</h2>
                        <p>入驻平台使得车主维保更加方便快捷</p>
                    </div>
                </div>
                <%@ include file="../common/companyImages.jsp" %>
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



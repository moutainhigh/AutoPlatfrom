<%--
  Created by IntelliJ IDEA.
  User: iJangoGuo
  Date: 2017/5/17
  Time: 20:31
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
    <title>产品特性</title>
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

        <header id="gtco-header" class="gtco-cover gtco-cover-sm" role="banner" style="background-image: url(<%=path%>/images/features.jpg)">
            <div class="overlay"></div>
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-12 col-md-offset-0 text-left">


                        <div class="row row-mt-15em">

                            <div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
                                <span class="intro-text-small">功能特性</span>
                                <h1>简约&nbsp;&nbsp;实用&nbsp;&nbsp;人性化</h1>
                            </div>

                        </div>


                    </div>
                </div>
            </div>
        </header>


        <div id="gtco-features" class="border-bottom">
            <div class="gtco-container">
                <div class="row">
                    <div class="col-md-8 col-md-offset-2 text-center gtco-heading animate-box">
                        <h2>一场全新的汽修革命</h2>
                        <p>顾客能够从预约到汽车维保，从状态查询到结算提车</p>
                        <p>每一个环节都能够时刻关注爱车的维保情况</p>
                        <p>商家能够顾客信息到维保项目添加,从派工领料到财务统计</p>
                        <p>并且每一个流程清晰准确无误。</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-vector"></i>
						</span>
                            <h3>流程控制</h3>
                            <p>完美的维保汽修流程，每个角色具有相对应功能权限，每个功能互不干扰，并且入驻商家具有动态的角色权限管理。</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-tablet"></i>
						</span>
                            <h3>操作性能</h3>
                            <p>汽修维保平台适用于一切PC端，完美的客户体验让车主和入驻商家随时随地都能够查询到想要的信息。 </p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-settings"></i>
						</span>
                            <h3>数据动态</h3>
                            <p>入驻商家能够动态更新所有数据，从汽车维保项目添加到客户结算提车，一切都能够让商家有更好的体验。</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-email"></i>
						</span>
                            <h3>维修提醒</h3>
                            <p>车主在维修完后平台都能够在下次维修保养时间定时发送短信提醒保养，让车主不错过任何一次保养爱车</p>
                        </div>
                    </div>

                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-cloud-down"></i>
						</span>
                            <h3>安全云数据</h3>
                            <p>所有的信息都安全的存储在云数据中心，不用再担心任何数据丢失问题，让车主，商家更快捷更放心</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-shopping-cart"></i>
						</span>
                            <h3>轻松结算</h3>
                            <p>车主能够通过手机端轻松查看爱车的维保进度，账单明细，消费统计全都一目了然并且在页面一键轻松结算</p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-stats-up"></i>
						</span>
                            <h3>报表统计</h3>
                            <p>平台提供人性化的报表统计，让商家精准了解一切数据报表，顾客也能够查看自己的消费统计 </p>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="feature-center animate-box" data-animate-effect="fadeIn">
						<span class="icon">
							<i class="ti-printer"></i>
						</span>
                            <h3>导入导出</h3>
                            <p>入驻的商家能够准确的导出所有员工工资、车主的账单详情、报表统计并且打印存档 </p>
                        </div>
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


<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Docker OS</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description"/>
    <meta content="Coderthemes" name="author"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>

    <!-- App favicon -->
    <link rel="shortcut icon" href="/static/images/favicon.ico">

    <!-- C3 charts css -->
    <link href="/static/plugins/c3/c3.min.css" rel="stylesheet" type="text/css"/>

    <!-- App css -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/core.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/components.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/icons.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/pages.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/menu.css" rel="stylesheet" type="text/css"/>
    <link href="/static/css/responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/static/plugins/datatables/jquery.dataTables.min.css"></link>
    <link href="/static/css/bootstrap-select.min.css" rel="stylesheet">
    <!-- jQuery  -->
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/metisMenu.min.js"></script>
    <script src="/static/js/waves.js"></script>
    <script src="/static/js/jquery.slimscroll.js"></script>
    <script src="/static/js/bootstrap-select.js"></script>
    <script src="/static/js/modernizr.min.js"></script>
    <script src="/static/js/public.js"></script>
    <script src="/static/js/layer/layer.min.js"></script>
    <link href="/static/css/animate.css" rel="stylesheet">
    <script src="/static/js/jquery.core.js"></script>
    <script src="/static/js/jquery.app.js"></script>


    <!-- DataTables -->
    <script src="/static/plugins/jquery-datatables-editable/jquery.dataTables.js"></script>
    <script src="/static/plugins/datatables/dataTables.bootstrap.js"></script>
    <link href="/static/plugins/datatables/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    <script src="/static/js/multiselect.min.js"></script>
    <link rel="stylesheet" href="/static/plugins/switchery/switchery.min.css">
    <script src="/static/plugins/switchery/switchery.min.js"></script>
    <style>

        #div1{
            position:fixed;
            z-index: 9999999;
            height:80px;
            width:200px;
            right:10px;
            top:10px;
        }

        .host_th {
            width: 20%;
            padding: 5px;
            text-align: center;
        }

        .no-top {
            top: 0px !important;
        }

        .top10 {
            top: 10px !important;
        }

        .width12 {
            width: 12% !important;
        }

        .width21 {
            width: 21%;
        }

        .top-390 {
            margin-top: -390px;
        }

        .widget-two-icon-server {

            position: absolute;
            left: 30px;
            font-size: 16px !important;
            top: 0px;
            height: 40px;
            width: 40px;
            text-align: center;
            line-height: 34px;
            border-radius: 50%;
    </style>
    <script>
        function success(messages, time) {
            if(!time){
                time = 3000
            }
            $("#div1").removeClass("fadeInUp")
            $("#notifications_html").html(messages)
            $("#div1").show()
            $("#div1").addClass("fadeInUp")
            setTimeout(function () {
                $("#div1").hide();
            },time)
        }
    </script>
</head>


<body>

<!-- Begin page -->
<div id="wrapper">

    <!-- Top Bar Start -->
    <div class="topbar">

        <!-- LOGO -->
        <div class="topbar-left">
            <!--<a href="index.html" class="logo"><span>Code<span>Fox</span></span><i class="mdi mdi-layers"></i></a>-->
            <!-- Image logo -->
            <span class="logo" style="color: #a0a0a0 !important;text-transform: inherit;    margin-left: -30px;"> Docker OS</span>
        </div>

        <!-- Button mobile view to collapse sidebar menu -->
        <div class="navbar navbar-default" role="navigation" style="background-color: #ffffff;">
            <div class="container">

                <!-- Navbar-left -->
                <ul class="nav navbar-nav navbar-left nav-menu-left">
                    <li>
                        <button type="button" class="button-menu-mobile open-left waves-effect">
                            <i class="dripicons-menu"></i>
                        </button>
                    </li>
                    <li class="dropdown hidden-xs mega-menu">
                    </li>
                </ul>
                <ul class="nav navbar-nav navbar-left">
                    <li style="margin-top: 20px;">
                        <a href="#" class="">
                            <span id="page_title"></span>
                        </a>
                    </li>
                </ul>
                <!-- Right(Notification) -->
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="#" class="right-menu-item dropdown-toggle" data-toggle="dropdown">
                            <i class="mdi mdi-bell-plus  widget-two-icon no-top"
                               style="color:#64c5b1;font-size:30px;"></i>
                            <span class="badge badge-pink"><span id="messages-number">4</span></span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right dropdown-lg user-list notify-list">
                            <li class="list-group notification-list m-b-0">
                                <div class="slimscroll">
                                    <!-- list item-->
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="media-left p-r-10">
                                                <em class="fa fa-diamond bg-primary"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading text-primary">A new order has been placed A new
                                                    order has been placed</h5>
                                                <p class="m-0">
                                                    There are new settings available
                                                </p>
                                            </div>
                                        </div>
                                    </a>

                                    <!-- list item-->
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="media-left p-r-10">
                                                <em class="fa fa-cog bg-warning"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading text-warning">设置</h5>
                                                <p class="m-0">
                                                    There are new settings available
                                                </p>
                                            </div>
                                        </div>
                                    </a>

                                    <!-- list item-->
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="media-left p-r-10">
                                                <em class="fa fa-bell-o bg-custom"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading text-custom">Updates</h5>
                                                <p class="m-0">
                                                    There are <span class="text-primary font-600">2</span> new updates
                                                    available
                                                </p>
                                            </div>
                                        </div>
                                    </a>

                                    <!-- list item-->
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="media-left p-r-10">
                                                <em class="fa fa-user-plus bg-danger"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading text-danger">New user registered</h5>
                                                <p class="m-0">
                                                    You have 10 unread messages
                                                </p>
                                            </div>
                                        </div>
                                    </a>

                                    <!-- list item-->
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="media-left p-r-10">
                                                <em class="fa fa-diamond bg-primary"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading text-primary">A new order has been placed A new
                                                    order has been placed</h5>
                                                <p class="m-0">
                                                    There are new settings available
                                                </p>
                                            </div>
                                        </div>
                                    </a>

                                    <!-- list item-->
                                    <a href="javascript:void(0);" class="list-group-item">
                                        <div class="media">
                                            <div class="media-left p-r-10">
                                                <em class="fa fa-cog bg-warning"></em>
                                            </div>
                                            <div class="media-body">
                                                <h5 class="media-heading text-warning">New settings</h5>
                                                <p class="m-0">
                                                    There are new settings available
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </li>
                            <!-- end notification list -->
                        </ul>
                    </li>

                    <li class="dropdown user-box">

                        <a href="" class="dropdown-toggle waves-effect user-link" data-toggle="dropdown"
                           aria-expanded="true">
                            <img src="/static/images/users/avatar-1.jpg" alt="user-img" class="img-circle user-img">
                            <span id="login-user"></span>
                        </a>

                        <ul class="dropdown-menu dropdown-menu-right arrow-dropdown-menu arrow-menu-right user-list notify-list">
                            <li><a href="javascript:void(0)"><span class="badge badge-info pull-right">4</span>设置</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="/logout.do">退出登录</a></li>
                        </ul>
                    </li>

                </ul> <!-- end navbar-right -->

            </div><!-- end container -->
        </div><!-- end navbar -->
    </div>
    <!-- Top Bar End -->


    <!-- ========== Left Sidebar Start ========== -->
    <div class="left side-menu">
        <div class="slimscroll-menu" id="remove-scroll">

            <!--- Sidemenu -->
            <div id="sidebar-menu">
                <!-- Left Menu Start -->
                <ul class="metisMenu nav text-dark" id="side-menu">
                    <li class="menu-title"></li>
                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fi-air-play"></i><span
                                class="badge badge-success pull-right">2</span> <span> 系统概览 </span></a>
                    </li>

                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fa fa-server"></i>
                            <span> 环境管理 </span> <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/ent/list', '/环境管理/环境配置管理');">环境配置管理</a>
                            </li>
                            <li><a href="javascript:void(0)" onclick="make_page('/groups/list', '/环境管理/主机组管理');">主机组管理</a>
                            </li>
                            <li><a href="javascript:void(0)" onclick="make_page('/server/list', '/环境管理/主机列表');">主机列表</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fa  fa-cubes"></i> <span> 容器管理 </span>
                            <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/container/list', '/容器管理/容器列表');">容器列表</a>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fi-briefcase"></i>
                            <span> 应用管理 </span> <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/ci/env/list', '/应用管理/我的应用');">我的应用</a>
                            <li><a href="ui-panels.html">应用仓库</a></li>
                            <li><a href="javascript:void(0)" onclick="make_page('/app/list', '/应用管理/应用栈列表');">应用列表</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fa  fa-sitemap"></i><span> 网络管理 </span>
                            <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/network/subnet', '/网络管理/虚拟子网');">虚拟子网</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fi-box"></i><span> 镜像管理 </span>
                            <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/images/templates/list', '/镜像管理/模板管理');">模板管理</a>
                            </li>
                            <li><a href="javascript:void(0)" onclick="make_page('/registry/server/list', '/镜像管理/仓库配置');">仓库配置</a>
                            </li>
                            <li><a href="javascript:void(0)" onclick="make_page('/images/list', '/镜像管理/镜像列表');">镜像列表</a>
                            </li>
                            <li><a href="email-read.html">自定义镜像</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fa fa-linode"></i><span> 持续集成 </span>
                            <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/ci/env/flow/list', '/持续集成/流程配置');">流程配置</a>
                            <li><a href="javascript:void(0)" onclick="make_page('/ci/images/list', '/持续集成/镜像编译');">镜像编译</a>
                            <li><a href="javascript:void(0)" onclick="make_page('/ci/flow/list', '/持续集成/流水线');">流水线</a>                        </ul>
                    </li>
                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fa  fa-sliders"></i><span> 资源报表 </span>
                            <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/report/used', '/资源报表/使用报表');">使用报表</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i class="fa fa-history"></i><span> 系统日志 </span>
                            <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="javascript:void(0)" onclick="make_page('/log/operation/list', '/系统日志/操作日志');">操作日志</a>
                            </li>
                            <li><a href="javascript:void(0)" onclick="make_page('/log/container/list', '/系统日志/容器日志');">容器日志</a>
                            </li>
                        </ul>
                    </li>

                    <li>
                        <a href="javascript: void(0);" aria-expanded="true"><i
                                class="fa fa-gear"></i><span> 系统设置 </span> <span class="menu-arrow"></span></a>
                        <ul class="nav-second-level nav" aria-expanded="true">
                            <li><a href="chart-flot.html">用户信息</a></li>
                            <li><a href="chart-morris.html">用户管理</a></li>
                        </ul>
                    </li>
                </ul>

            </div>
            <!-- Sidebar -->
            <div class="clearfix"></div>

        </div>
        <!-- Sidebar -left -->

    </div>
    <!-- Left Sidebar End -->


    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->
    <div class="content-page">
        <div id="html_content">
        </div>
        <footer class="footer text-right">
            Docker OS
        </footer>
    </div>
    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->
    <div id="div1" style="display: none" class="animated fadeInUp">
        <div class="alert alert-icon alert-white alert-success alert-dismissible fade in" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">×</span>
            </button>
            <i class="mdi mdi-check-all" style="margin-top:15px;"></i>
            <div id="notifications_html"></div>
        </div>
    </div>
</div>
<!-- END wrapper -->
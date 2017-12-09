<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>云管理平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <!-- App favicon -->
    <link rel="shortcut icon" href="/static/images/favicon.ico">

    <!-- App css -->
    <link href="/static/css/bootstrap-select.min.css" rel="stylesheet" />
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/core.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/components.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/pages.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/menu.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/responsive.css" rel="stylesheet" type="text/css" />

    <script src="/static/js/modernizr.min.js"></script>
    <script src="/static/js/public.js"></script>
</head>


<body class="">

<!-- HOME -->
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">

                <div class="wrapper-page">
                    <div class="account-pages">
                        <div class="account-box">
                            <div class="account-logo-box">
                                <h5 class="text-uppercase font-bold m-b-5 m-t-50">登录</h5>
                                <p class="m-b-0">请使用OA账户登录</p>
                            </div>
                            <div class="account-content">
                                <form class="form-horizontal" action="#">
                                    <form id="login-form">
                                    <div class="form-group m-b-20">
                                        <div class="col-xs-12">
                                            <label for="emailaddress">用户名</label>
                                            <input class="form-control" type="text" name="userName"  id="emailaddress" required="" placeholder="zhaoyun">
                                        </div>
                                    </div>

                                    <div class="form-group m-b-20">
                                        <div class="col-xs-12">
                                            <a href="page-recoverpw.html" class="text-muted pull-right"><small>Forgot your password?</small></a>
                                            <label for="password">密码</label>
                                            <input class="form-control" type="password" required="" name="password"  id="password" placeholder="Enter your password">
                                        </div>
                                    </div>

                                    <div class="form-group m-b-20">
                                        <div class="col-xs-12">

                                            <div class="checkbox checkbox-success">
                                                <input id="remember" type="checkbox" checked="">
                                                <label for="remember">
                                                    Remember me
                                                </label>
                                            </div>

                                        </div>
                                    </div>

                                        <div class="form-group text-center m-t-10">
                                            <div class="col-xs-12">
                                                <button onclick="login()" class="btn btn-md btn-block btn-primary waves-effect waves-light" type="button">登录</button>
                                            </div>
                                        </div>
                                    </form>

                            </div>
                        </div>
                    </div>
                    <!-- end card-box-->
                </div>
                <!-- end wrapper -->

            </div>
        </div>
    </div>
</section>
<!-- END HOME -->



<script>
    var resizefunc = [];
</script>

<!-- jQuery  -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/metisMenu.min.js"></script>
<script src="/static/js/waves.js"></script>
<script src="/static/js/jquery.slimscroll.js"></script>
<script src="/static/js/bootstrap-select.min.js" type="text/javascript"></script>

<!-- App js -->
<script src="/static/js/jquery.core.js"></script>
<script src="/static/js/jquery.app.js"></script>

</body>
</html>
<script>
    function login() {
            url = "/login.do";
            data =  get_form_data();
            result = post(data, url);
            if (result.status == "ok") {
                window.location.href = "/index.do"
            } else {
                layer.tips(result.message, "#login-btn", {
                    tips: [1, '#3595CC'],
                    time: 4000
                });
            }
    }

        $(window).keydown(function (event) {
            if (event.keyCode) {
                $("#btn-login").click();
            }
        });
</script>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
    <title>小程序后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />

    <!-- App favicon -->
    <link rel="shortcut icon" href="<c:url value='/resource/assets/images/favicon.ico'/>">

    <!-- App css -->
    <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
    <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />

    <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>
    </head>


    <body class="bg-accpunt-pages">

        <!-- HOME -->
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-sm-12">

                        <div class="wrapper-page">

                            <div class="account-pages">
                                <div class="account-box">
                                    <div class="account-logo-box">
                                        <h2 class="text-uppercase text-center">
                                            <a href="index.html" class="text-success">
                                                <span><img src="<c:url value='/resource/assets/images/logo_dark.png'/>" alt="" height="18"></span>
                                            </a>
                                        </h2>
                                        <h6 class="text-uppercase text-center font-bold mt-4">注册用户</h6>
                                    </div>
                                    <div class="account-content">
                                        <form class="form-horizontal" action="<c:url value='/login'/>">

                                            <div class="form-group row m-b-20">
                                                <div class="col-12">
                                                    <label for="username">用户名</label>
                                                    <input class="form-control" type="email" id="username" required="" placeholder="请输入用户名">
                                                </div>
                                            </div>

                                            <div class="form-group row m-b-20">
                                                <div class="col-12">
                                                    <label for="emailaddress">邮箱</label>
                                                    <input class="form-control" type="email" id="emailaddress" required="" placeholder="john@deo.com">
                                                </div>
                                            </div>

                                            <div class="form-group row m-b-20">
                                                <div class="col-12">
                                                    <label for="password">密码</label>
                                                    <input class="form-control" type="password" required="" id="password" placeholder="请输入密码">
                                                </div>
                                            </div>

                                            <div class="form-group row m-b-20">
                                                <div class="col-12">

                                                    <div class="checkbox checkbox-success">
                                                        <input id="remember" type="checkbox" checked="">
                                                        <label for="remember">
                                                            	我接受 <a href="#">条款和条件</a>
                                                        </label>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="form-group row text-center m-t-10">
                                                <div class="col-12">
                                                    <button class="btn btn-block btn-gradient waves-effect waves-light" type="submit">免费注册(仅是模板,未实现功能)</button>
                                                </div>
                                            </div>

                                        </form>

                                        <div class="row m-t-50">
                                            <div class="col-sm-12 text-center">
                                                <p class="text-muted">您已经有账户了?  <a href="<c:url value='/login'/>" class="text-dark m-l-5"><b>登录</b></a></p>
                                            </div>
                                        </div>

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


        <!-- jQuery  -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/popper.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>

        <!-- App js -->
        <script src="assets/js/jquery.core.js"></script>
        <script src="assets/js/jquery.app.js"></script>

    </body>
</html>
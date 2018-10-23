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
                                        <h6 class="text-uppercase text-center font-bold mt-4">登录认证</h6>
                                        <c:if test="${fn:length(msg) > 0}">
	                                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
	                               				 ${msg}
	                            			</div>
	                            		</c:if>
                                    </div>
                                    <div class="account-content">
                                        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/authenticate">
											<input type="hidden" id="token" name="token" value="${token }">
                                            <div class="form-group m-b-20 row">
                                                <div class="col-12">
                                                    <label for="userCustom.email">邮箱</label>
                                                    <input class="form-control" type="email" value="${user.email}" name="userCustom.email" id="userCustom.email" required="" placeholder="邮箱">
                                                </div>
                                            </div>

                                            <div class="form-group row m-b-20">
                                                <div class="col-12">
                                                    <a href="#" class="text-muted pull-right"><small>忘记密码?</small></a>
                                                    <label for="userCustom.pwd">密码</label>
                                                    <input class="form-control" type="password" value="${user.pwd}" name="userCustom.pwd" required="" id="userCustom.pwd" placeholder="密码">
                                                </div>
                                            </div>

                                            <div class="form-group row m-b-20">
                                                <div class="col-12">

                                                    <div class="checkbox checkbox-success">
                                                        <input id="remember" name="remember" type="checkbox" checked="">
                                                        <label for="remember">
                                                            	记住我
                                                        </label>
                                                    </div>

                                                </div>
                                            </div>

                                            <div class="form-group row text-center m-t-10">
                                                <div class="col-12">
                                                    <button class="btn btn-block btn-gradient waves-effect waves-light" type="submit">登录</button>
                                                </div>
                                            </div>

                                        </form>

                                        <div class="row m-t-50">
                                            <div class="col-sm-12 text-center">
                                                <p class="text-muted">没有账户? <a href="${pageContext.request.contextPath}/register" class="text-dark m-l-5"><b>注册</b></a></p>
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
        <script src="<c:url value='/resource/assets/js/jquery.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/popper.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/bootstrap.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/waves.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.slimscroll.js'/>"></script>

        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>

    </body>
</html>

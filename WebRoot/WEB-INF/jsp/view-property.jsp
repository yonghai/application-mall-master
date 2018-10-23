<%@ page language="java" pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>后台管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="<c:url value='/resource/assets/images/favicon.ico'/>">

		<!-- Plugins css-->
        <link href="<c:url value='/resource/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css'/>" rel="stylesheet" />
        
        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />

        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>

        <div class="wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-6" style="margin: auto;">

                        <div class="card-box">
                            <h4 class="header-title m-t-0">查看属性</h4>

                            <div id="formSubmitAction" role="form">
                                <div class="form-group">
                                    <label for="name">属性名称</label>
                                    <input type="text" name="name" parsley-trigger="change" required
                                           placeholder="输入属性名称" value="${result.result.name }" class="form-control" id="name">
                                </div>
                                <div class="form-group">
                                	<label for="select" class="col-form-label">属性集合</label>
                                    <div class="m-b-0">
		                                <select id="select" multiple data-role="tagsinput">
		                                	<c:forEach items="${result.result.propertyItemCustoms}" var="item">
			                                    <option value="${item.name}">${item.name}</option>
			                                    <option value="${item.name}">${item.name}</option>
			                                    <option value="${item.name}">${item.name}</option>
			                                    <option value="${item.name}">${item.name}</option>
		                                    </c:forEach>
		                                </select>
		                            </div>
                                </div>

                            </div>
                        </div> <!-- end card-box -->
                    </div>
                </div>
                
                
                
                
                

            </div> <!-- end container -->
        </div>
        <!-- end wrapper -->



		<!-- jQuery  -->
        <script src="<c:url value='/resource/assets/js/jquery.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/popper.min.js'/>"></script><!--轻量级tooltips提示插件 -->
        <script src="<c:url value='/resource/assets/js/bootstrap.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/waves.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.slimscroll.js'/>"></script>

        
        <!-- plugin Js -->
        <script src="<c:url value='/resource/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js'/>"></script>
        
        <!-- Init Js file -->
        <script type="text/javascript" src="<c:url value='/resource/assets/pages/jquery.form-advanced.init.js'/>"></script>
        
        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
    </body>
</html>
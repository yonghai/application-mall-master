<%@ page language="java" pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Abstack - Responsive Web App Kit</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

       	<!-- App favicon -->
        <link rel="shortcut icon" href="<c:url value='/resource/assets/images/favicon.ico'/>">

        <!-- X editable -->
        <link href="<c:url value='/resource/plugins/bootstrap-xeditable/css/bootstrap-editable.css'/>" rel="stylesheet" />

        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />
        
        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>

        
            <div class="container-fluid">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <h4 class="page-title">订单详情</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->

                <div class="row">
                    <div class="col-12">
                        <div class="card-box">

                            <table class="table table-bordered table-striped">
                                <tbody>
                                <tr>
                                    <td width="35%">订单号</td>
                                    <td width="65%">${result.data.orderInfo.orderNumber}</td>
                                </tr>
                                <tr>
                                    <td>订单时间</td>
                                    <td>${result.data.orderInfo.dateAdd}</td>
                                </tr>
                                <tr>
                                    <td>收货人信息</td>
                                    <td>${result.data.orderInfo.linkMan}${result.data.logistics.mobile}(${result.data.orderInfo.address})</td>
                                </tr>
                                <tr>
                                    <td>商品价格</td>
                                    <td>￥${result.data.orderInfo.amount}</td>
                                </tr>
                                <tr>
                                    <td>运费价格</td>
                                    <td>￥${result.data.orderInfo.amountLogistics}</td>
                                </tr>
                                <tr>
                                    <td>折扣</td>
                                    <td>￥${result.data.orderInfo.discount}</td>
                                </tr>
                                <tr>
                                    <td>实付价格</td>
                                    <td>￥${result.data.orderInfo.amountReal}</td>
                                </tr>
                                <c:if test="${empty result.data.orderInfo.trackingNumber}">
                                	<td>物流</td>
                                    <td>无需物流</td>
                                </c:if>
                                <c:if test="${not empty result.data.orderInfo.trackingNumber}">
                                	<td>物流</td>
                                    <td>${result.data.logistics.trackingNumber}</td>
                                </c:if>
                                <c:forEach items="${result.data.goods}" var="item">
                                	<tr>
	                                    <td>商品名</td>
	                                    <td>${item.goodsName }-${item.property }(X${item.number })￥${item.amount }</td>
	                                </tr>
                                </c:forEach>
									<tr>
	                                    <td>备注</td>
	                                    <td>￥${result.data.orderInfo.remark}</td>
	                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div><!-- end col -->
                </div>
                <!-- end row -->

            </div> <!-- end container -->

        <!-- Footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        2017 - 2018 Â© Abstack. - Coderthemes.com
                    </div>
                </div>
            </div>
        </footer>
        <!-- End Footer -->


        <!-- jQuery  -->
        <script src="<c:url value='/resource/assets/js/jquery.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/popper.min.js'/>"></script><!--轻量级tooltips提示插件 -->
        <script src="<c:url value='/resource/assets/js/bootstrap.min.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/waves.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.slimscroll.js'/>"></script>

        <!-- Xeditable -->
        <script src="<c:url value='/resource/plugins/moment/moment.js'/>"></script>

       	<!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>

    </body>
</html>
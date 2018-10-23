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
        <link rel="shortcut icon" href="resource/assets/images/favicon.ico">

        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />

        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>

        <!-- Navigation Bar-->
        <header id="topnav">
            <!-- begin topbar-main -->
            <div class="topbar-main">
                <div class="container-fluid">

                    <!-- Logo container-->
                    <div class="logo">
                        <!-- Image Logo -->
                        <a href="<c:url value='/index'/>" class="logo">
                            <img src="<c:url value='/resource/assets/images/logo_sm.png'/>" alt="" height="26" class="logo-small">
                            <img src="<c:url value='/resource/assets/images/logo.png'/>" alt="" height="16" class="logo-large">
                        </a>

                    </div>
                    <!-- End Logo container-->


                    <div class="menu-extras topbar-custom">

                        <ul class="list-unstyled topbar-right-menu float-right mb-0">
                            <li class="menu-item">
                                <!-- Mobile menu toggle-->
                                <a class="navbar-toggle nav-link">
                                    <div class="lines">
                                        <span></span>
                                        <span></span>
                                        <span></span>
                                    </div>
                                </a>
                                <!-- End mobile menu toggle-->
                            </li>
                            
                            <li class="dropdown notification-list">
                                <a class="nav-link dropdown-toggle arrow-none waves-light waves-effect" data-toggle="dropdown" href="#" role="button"
                                   aria-haspopup="false" aria-expanded="false">
                                    <i class="fi-bell noti-icon"></i>
                                    <span class="badge badge-danger badge-pill noti-icon-badge">4</span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right dropdown-lg">
                                    <!-- item-->
                                    <div class="dropdown-item noti-title">
                                        <h6 class="m-0"><span class="float-right"><a href="" class="text-dark"><small>清除所有</small></a> </span>通知</h6>
                                    </div>
                                    <div class="slimscroll" style="max-height: 190px;">
                                        <!-- item-->
                                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                                            <div class="notify-icon bg-success"><i class="mdi mdi-comment-account-outline"></i></div>
                                            <p class="notify-details">Caleb Flakelar commented on Admin<small class="text-muted">1 min ago</small></p>
                                        </a>

                                        <!-- item-->
                                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                                            <div class="notify-icon bg-info"><i class="mdi mdi-account-plus"></i></div>
                                            <p class="notify-details">New user registered.<small class="text-muted">5 hours ago</small></p>
                                        </a>

                                        <!-- item-->
                                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                                            <div class="notify-icon bg-danger"><i class="mdi mdi-heart"></i></div>
                                            <p class="notify-details">Carlos Crouch liked <b>Admin</b><small class="text-muted">3 days ago</small></p>
                                        </a>

                                        <!-- item-->
                                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                                            <div class="notify-icon bg-warning"><i class="mdi mdi-comment-account-outline"></i></div>
                                            <p class="notify-details">Caleb Flakelar commented on Admin<small class="text-muted">4 days ago</small></p>
                                        </a>

                                        <!-- item-->
                                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                                            <div class="notify-icon bg-purple"><i class="mdi mdi-account-plus"></i></div>
                                            <p class="notify-details">New user registered.<small class="text-muted">7 days ago</small></p>
                                        </a>

                                        <!-- item-->
                                        <a href="javascript:void(0);" class="dropdown-item notify-item">
                                            <div class="notify-icon bg-custom"><i class="mdi mdi-heart"></i></div>
                                            <p class="notify-details">Carlos Crouch liked <b>Admin</b><small class="text-muted">13 days ago</small></p>
                                        </a>
                                    </div>

                                    <!-- All-->
                                    <a href="javascript:void(0);" class="dropdown-item text-center text-primary notify-item notify-all">
                                        查看所有 <i class="fi-arrow-right"></i>
                                    </a>

                                </div>
                            </li>

                            <li class="dropdown notification-list">
                                <a class="nav-link dropdown-toggle waves-effect waves-light nav-user" data-toggle="dropdown" href="#" role="button"
                                   aria-haspopup="false" aria-expanded="false">
                                    <img src="<c:url value='/resource/assets/images/users/avatar-1.jpg'/>" alt="user" class="rounded-circle"> <span class="ml-1 pro-user-name">Samuel <i class="mdi mdi-chevron-down"></i> </span>
                                </a>
                                <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
                                    <!-- item-->
                                    <div class="dropdown-item noti-title">
                                        <h6 class="text-overflow m-0">Welcome !</h6>
                                    </div>

                                    <!-- item-->
                                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                                        <i class="fi-head"></i> <span>My Account</span>
                                    </a>

                                    <!-- item-->
                                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                                        <i class="fi-cog"></i> <span>Settings</span>
                                    </a>

                                    <!-- item-->
                                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                                        <i class="fi-help"></i> <span>Support</span>
                                    </a>

                                    <!-- item-->
                                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                                        <i class="fi-lock"></i> <span>Lock Screen</span>
                                    </a>

                                    <!-- item-->
                                    <a href="${pageContext.request.contextPath}/logout" class="dropdown-item notify-item">
                                        <i class="fi-power"></i> <span>Logout</span>
                                    </a>

                                </div>
                            </li>
                        </ul>
                    </div>
                    <!-- end menu-extras -->

                    <div class="clearfix"></div>

                </div> <!-- end container -->
            </div>
            <!-- end topbar-main -->

            <div class="navbar-custom">
                <div class="container-fluid">
                    <div id="navigation">
                        <!-- Navigation Menu-->
                        <ul class="navigation-menu">

                            <li class="has-submenu">
                                <a href="<c:url value='/index'/>"><i class="fi-air-play"></i>Dashboard</a>
                            </li>
                            
                            <li class="has-submenu">
                                <a href="#"><i class="fi-briefcase"></i>基本设置</a>
                                <ul class="submenu">
                                    <li><a href="<c:url value='/photos'/>">图片空间</a></li>
                                    <li class="has-submenu">
                                        <a href="#">轮播图</a>
                                        <ul class="submenu">
                                        	<li><a href="<c:url value='/swiper/add'/>">新增轮播图</a></li>
                                        	<li><a href="<c:url value='/swiper/list'/>">轮播图管理</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-submenu">
                                        <a href="#">公告</a>
                                        <ul class="submenu">
                                        	<li><a href="<c:url value='/notice/add'/>">新增公告</a></li>
                                        	<li><a href="<c:url value='/notice/list'/>">公告管理</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#"><i class="fi-box"></i>产品管理</a>
                                <ul class="submenu">
                                	<li class="has-submenu">
                                        <a href="#">类别管理</a>
                                        <ul class="submenu">
                                            <li><a href="<c:url value='/product/category/add'/>">新增类别</a></li>
                                    		<li><a href="<c:url value='/product/category/list'/>">类别列表</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-submenu">
                                        <a href="#">商品属性</a>
                                        <ul class="submenu">
                                            <li><a href="<c:url value='/property/add'/>">新增属性</a></li>
                                            <li><a href="<c:url value='/property/list'/>">属性列表</a></li>
                                        </ul>
                                    </li>
                                    <li class="has-submenu">
                                        <a href="#">产品管理</a>
                                        <ul class="submenu">
                                            <li><a href="<c:url value='/product/add'/>">新增产品</a></li>
                                    		<li><a href="<c:url value='/product/list'/>">产品列表</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#"><i class="fi-mail"></i>订单及用户</a>
                                <ul class="submenu">
                                   	<li><a href="<c:url value='/order/list'/>">订单列表</a></li>
                                    <li><a href="<c:url value='/user/list'/>">用户列表</a></li>
                                    <li><a href="<c:url value='/reputation/list'/>">评价列表</a></li>
                                </ul>
                            </li>

                            <li class="has-submenu">
                                <a href="#"><i class="fi-mail"></i>活动管理</a>
                                <ul class="submenu">
                                   	<li><a href="<c:url value='/coupons/new'/>">发行优惠券</a></li>
                                   	<li><a href="<c:url value='/coupons/list'/>">优惠券列表</a></li>
                                </ul>
                            </li>


                        </ul>
                        <!-- End navigation menu -->
                    </div> <!-- end #navigation -->
                </div> <!-- end container -->
            </div> <!-- end navbar-custom -->
        </header>
        <!-- End Navigation Bar-->


        <div class="wrapper">
            <div class="container-fluid">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Abstack</a></li>
                                    <li class="breadcrumb-item"><a href="#">公告</a></li>
                                    <li class="breadcrumb-item active">新增公告</li>
                                </ol>
                            </div>
                            <h4 class="page-title">新增公告</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
				
				<div class="row">
                    <div class="col-12">
                        <div class="card-box">
							<form id="formSubmitAction" role="form">
								<input type="hidden" id="token" name="token" value="${token }">
								<div class="form-group">
                                    <label for="notice_name">公告<span class="text-danger">*</span></label>
                                    <input type="text" name="notice_name" parsley-trigger="change" required
                                           placeholder="输入公告标题" class="form-control" id="notice_name">
                                </div>
                                
                                <div class="form-group">
                                    <label class="header-title m-t-0">公告内容,可选项.CTRL+SHIFT+F全屏</label>
                                    <textarea id="elm1" name="area"></textarea>
                                </div>
                                
                                <button type="submit" class="btn btn-primary">提交</button>
                            </form>
							
                        
						</div>
                    </div>
                </div>
                <!--  end row -->
				
				
            </div> <!-- end container -->
        </div>
        <!-- end wrapper -->


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

		<script type="text/javascript" src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
		
		<!--Wysiwig js-->
        <script src="<c:url value='/resource/plugins/tinymce/tinymce.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/tinymce/langs/zh_CN.js'/>"></script>
        
        <!-- Parsley js -->
        <script type="text/javascript" src="<c:url value='/resource/plugins/parsleyjs/parsley.min.zh.js'/>"></script>
        
        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function () {
                if($("#elm1").length > 0){
                    tinymce.init({
                        selector: "textarea#elm1",
                        theme: "modern",
                        height:300,
                        convert_urls :false,
                        relative_urls :false,
                        remove_script_host :false,
                        plugins: [
                            "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
                            "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
                            "save table contextmenu directionality emoticons template paste textcolor"
                        ],
                        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | l      ink image | print preview media fullpage | forecolor backcolor emoticons"
                        
                    });
                   
                }
                $('formSubmitAction').parsley();
            });
            
            $('#formSubmitAction').submit(function() {
				var name = $('#notice_name').val().trim();//标题
				var content = tinymce.activeEditor.getContent().trim();
				var token = $('#token').val().trim();
				$.ajax({
					url: '<c:url value='/notice/save'/>',
					type : 'post',
					data: {"noticeCustom.title":name,"noticeCustom.content":content,"token":token},
       				dataType: "json",
       				beforeSend:function(){  
	                    _index = layer.load(2);
	                },
					success : function(data) {
						console.log(data);
						layer.close(_index);
					    if(data.ok){
						    // 此处可对 data 作相关处理
						    alert(data.result);
							location.replace(location.href);
							return false;
					    }else{
					    	alert('提交失败！'+data.result);
					    	return false;			
					    }
					    
					},
					error:function(jqXHR, textStatus, errorThrown){
						layer.close(_index);
						layer.open({
						  title: '信息'
						  ,content: '提交失败!'
						});
						return false;
			        }
				});
				return false;
			});
		</script>
    </body>
</html>
<%@ page language="java" pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>后台管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<!-- App favicon -->
        <link rel="shortcut icon" href="<c:url value='/resource/assets/images/favicon.ico'/>">

        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />

		<!-- 相册 css -->
		<link rel="stylesheet" href="<c:url value='/resource/custom/css/xc.css'/>">
		<link rel="stylesheet" href="<c:url value='/resource/plugins/lightbox2/2.8.1/css/lightbox.css'/>">
        
        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>
		<style>
			.clearfix:after {
				clear: both;
				content: "\200B";
				display: block;
				height: 0;
			}
			.clearfix {
				*zoom: 1;
			}
		</style>
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
fsgsgdsfg
		<!-- The Content --> 
        <div class="wrapper">
            <div class="container-fluid">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="page-title-box">
                            <div class="btn-group pull-right">
                                <ol class="breadcrumb hide-phone p-0 m-0">
                                    <li class="breadcrumb-item"><a href="#">Abstack</a></li>
                                    <li class="breadcrumb-item"><a href="#">基本设置</a></li>
                                    <li class="breadcrumb-item active">图片空间</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
               
				<div class="xc-btn-bar">
					<a id='addImg' href="javascript:;" class="bluebtn sp-add xc-add"><i style="background: url(<c:url value='/resource/assets/images/ico_fabu_2.png'/>) no-repeat;" class="ico-add"></i>
						添加图片
					</a>
				</div>
				
				<div class="xc-btn-bar">
					<h3>相册名称：站点默认的图片资源集合</h3>
				</div>
				
				<c:if test="${result.ok==true}">
					
					<div class="xc-all-box clearfix">
						<c:forEach items="${result.result}" var="item">
							<div class="subxc-list">
								<div class="img">
									<a href="${item.photoAddress}" data-lightbox="gallery" data-title="${item.photoName}">
										<img src="${item.smallPhoto}">
									</a>
								</div>
								<p>${item.username}</p>
								<div class="xc-opr clearfix">
									<a href="javascript:void(0)" data-value="${item.photoAddress}" id="xc-fm-a" class="xc-fm-a">复制链接</a>
									<a href="javascript:void(0)" onclick="delImg('${item.id}','${item.photoName}','${item.photoAddress}',this)" id="xc-xiugai-a" class="xc-xiugai-a">删除</a>
								</div>
							</div>
						</c:forEach>
						
						<div id="preview"></div>
					</div>
					
				</c:if>
				<c:if test="${result.ok==false}">
					返回数据错误 ${result.result}
				</c:if>
				
				
				
				

                
				

            </div> 
        </div>
        <!-- end wrapper -->
		<!-- end Content -->
        <!-- Footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-12 text-center">
                        2017 - 2018 © Abstack. - Coderthemes.com
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

        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
        
        <!-- 遮罩图层 -->
        <script type="text/javascript" src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resource/plugins/lightbox2/2.8.1/js/lightbox.min.js'/>"></script>
		<!-- 剪切板 -->
		<script type="text/javascript" src="<c:url value='/resource/plugins/clipboard/jquery.zclip.min.js'/>"></script>
        <script type="text/javascript">
        	function onSuccess(data){
        		var html = '<div class="subxc-list"><div class="img"><a href="'+data.photoAddress+'" data-lightbox="gallery" data-title="'+data.photoName+'">'
							+'<img src="'+data.smallPhoto+'"></a></div><p>'+data.username+'</p><div class="xc-opr clearfix">'
							+'<a href="javascript:void(0)" data-value="'+data.photoAddress+'" id="xc-fm-a" class="xc-fm-a">复制链接</a><a href="javascript:void(0)" '
							+'onclick="delImg(\''+data.id+'\',\''+data.photoName+'\',\''+data.photoAddress+'\',this)" id="xc-xiugai-a" class="xc-xiugai-a">删除</a></div></div>';
        	
        		$("#preview").append(html);
        		
        		copyToBoard();
			}
			/*上传图片*/
	        $("#addImg").click(function(){
			  	var index = layer.open({
					type: 2,
					title: '图片上传',
					content: '<c:url value='/toUploadImages'/>',
					area: ['800px', '450px'], //宽高
					cancel: function(e){
				  	},
				  	end:function(e){
						//location.reload();
					}
				});
			});
			
			/**删除图片**/
			function delImg(id,name,photoAddress,obj){
			  	$.ajax({//token:$('#token').val().trim(
					url: '<c:url value='/photo/delete'/>',
					type : 'post',
					data: {"photoCustom.id":id,"photoCustom.photoAddress":photoAddress},
		         	dataType: "json",
					success : function(data) {
						if(data.ok){
							$(obj).parent().parent('.subxc-list').remove()
							layer.msg('已删除!',{icon:1,time:3000});
						}else{
							layer.msg('操作失败!'+data.result,{icon: 5,time:1000});
					    }
					},
					error:function(jqXHR, textStatus, errorThrown){
						layer.msg('操作失败!',{icon: 5,time:1000});
			        }
				});
			}
			
			/*复制到剪切板,移动设备不兼容**/
			function copyToBoard(){
				$(".xc-fm-a").zclip({
					path: "<c:url value='/resource/plugins/clipboard/ZeroClipboard.swf'/>",
					copy: function(){
						var url = $(this).attr('data-value');
						return url;
					},
					afterCopy : function() {
						console.log();
						layer.open({
						  	title: '链接已复制到剪切板',
					  		content: $(this).attr('data-value')
						});
					}
				});
			}
			
			
			 $(function() {
			    /*复制*/
				copyToBoard();
			
			 });
        	
        	
        	
        	
        </script>
    </body>
</html>
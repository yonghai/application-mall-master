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

        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />
		
		<!-- Plugins css-->
        <link rel="stylesheet" href="<c:url value='/resource/plugins/bootstrap-touchspin/css/jquery.bootstrap-touchspin.min.css'/>">
		
		<!-- fileupload css -->
        <link rel="stylesheet" href="<c:url value='/resource/plugins/lightbox2/2.8.1/css/lightbox.css'/>">
        <link rel="stylesheet" href="<c:url value='/resource/plugins/layui/css/layui.css'/>">
      
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
                                    <li class="breadcrumb-item"><a href="#">商品管理</a></li>
                                    <li class="breadcrumb-item active">新增商品</li>
                                </ol>
                            </div>
                            <h4 class="page-title"></h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
                
                <div class="row">
                    <div class="col-sm-12" style="margin: auto;">

                        <div class="card-box" style="margin-bottom: 100px;">
                            <h4 class="header-title m-t-0">新增商品</h4>

                            <!-- Form row -->
                           	<form id="formSubmitAction" role="form">
                           		<input type="hidden" id="token" name="token" value="${token }">
                           		<div class="form-group">
                                    <label for="name">商品名称<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control" maxlength="50" name="defaultconfig" id="defaultconfig" 
                                    		placeholder="输入商品名称" required parsley-trigger="change"/>
                                           
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-12">
                                        <button type="button" class="layui-btn header-title m-t-0" id="test1">上传封面</button>
										<div class="layui-upload-list">
										  	<a href="javascript:void(0);"  data-title="" id="preview-img">
										  		<img style="display:block;height:100%;height:150px;" src="<c:url value='/resource/assets/images/img_xc_bg.jpg'/>" class="layui-upload-img" id="demo1">
										  	</a>
										    <p id="demoText"></p>
										</div>
                                    </div>
                                </div>
                                <label class="col-form-label">展示图</label>
                                <div class="form-group form-row">
									<div class="layui-upload-list" id="tag1">
									  	<img style="display:block;height:100%;height:150px;" src="<c:url value='/resource/assets/images/img_xc_bg.jpg'/>" class="layui-upload-img" id="demo11">
									    <p id="demoText11"></p>
									</div>
									<div class="layui-upload-list" id="tag2" style="margin-left: 10px;">
									  	<img style="display:block;height:100%;height:150px;" src="<c:url value='/resource/assets/images/img_xc_bg.jpg'/>" class="layui-upload-img" id="demo22">
									    <p id="demoText22"></p>
									</div>
									<div class="layui-upload-list" id="tag3" style="margin-left: 10px;">
									  	<img style="display:block;height:100%;height:150px;" src="<c:url value='/resource/assets/images/img_xc_bg.jpg'/>" class="layui-upload-img" id="demo33">
									    <p id="demoText33"></p>
									</div>
		                         </div>
                                <div class="form-row">
	                                <div class="form-group col-md-6">
                                    	<label for="cid" class="col-form-label">所属商品种类<span class="text-danger">*</span></label>
                                        <select id="cid" class="form-control">
                                        </select>

                                    </div>
                                    
                                    <div class="form-group col-md-6">
	                                    <label for="stores" class="col-form-label">库存<span class="text-danger">*</span></label>
	                                    <input id="stores" type="text" value="100" name="demo0" data-bts-min="0" data-bts-max="999" data-bts-init-val="" data-bts-step="1" data-bts-decimal="0" data-bts-step-interval="100" data-bts-force-step-divisibility="round" data-bts-step-interval-delay="500" data-bts-prefix="" data-bts-postfix="" data-bts-prefix-extra-class="" data-bts-postfix-extra-class="" data-bts-booster="true" data-bts-boostat="10" data-bts-max-boosted-step="false" data-bts-mousewheel="true" data-bts-button-down-class="btn btn-secondary" data-bts-button-up-class="btn btn-secondary"/>
	                                </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="minPrice" class="col-form-label">活动价<span class="text-danger">*</span></label>
                                        <input id="minPrice" type="text" value="0" name="demo1_1" data-bts-min="0" data-bts-max="999" data-bts-init-val="" data-bts-step="1" data-bts-decimal="0" data-bts-step-interval="100" data-bts-force-step-divisibility="round" data-bts-step-interval-delay="500" data-bts-prefix="" data-bts-postfix="" data-bts-prefix-extra-class="" data-bts-postfix-extra-class="" data-bts-booster="true" data-bts-boostat="10" data-bts-max-boosted-step="false" data-bts-mousewheel="true" data-bts-button-down-class="btn btn-secondary" data-bts-button-up-class="btn btn-secondary"/>
	                                </div>
                                    <div class="form-group col-md-6">
                                        <label for="originalPrice" class="col-form-label">正常价<span class="text-danger">*</span></label>
                                        <input id="originalPrice" type="text" value="0" name="demo1_1" data-bts-min="0" data-bts-max="999" data-bts-init-val="" data-bts-step="1" data-bts-decimal="0" data-bts-step-interval="100" data-bts-force-step-divisibility="round" data-bts-step-interval-delay="500" data-bts-prefix="" data-bts-postfix="" data-bts-prefix-extra-class="" data-bts-postfix-extra-class="" data-bts-booster="true" data-bts-boostat="10" data-bts-max-boosted-step="false" data-bts-mousewheel="true" data-bts-button-down-class="btn btn-secondary" data-bts-button-up-class="btn btn-secondary"/>
	                                </div>
                                </div>
                                
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                    	<label for="commission" class="col-form-label">消费返还积分<span class="text-danger">*</span></label>
	                                    <input id="commission" type="text" value="0" name="demo0" data-bts-min="0" data-bts-max="999" data-bts-init-val="" data-bts-step="1" data-bts-decimal="0" data-bts-step-interval="100" data-bts-force-step-divisibility="round" data-bts-step-interval-delay="500" data-bts-prefix="" data-bts-postfix="" data-bts-prefix-extra-class="" data-bts-postfix-extra-class="" data-bts-booster="true" data-bts-boostat="10" data-bts-max-boosted-step="false" data-bts-mousewheel="true" data-bts-button-down-class="btn btn-secondary" data-bts-button-up-class="btn btn-secondary"/>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="commissionType" class="col-form-label">积分类型</label>
                                        <select id="commissionType" class="form-control">
                                         	<option>默认类型</option>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="property" class="col-form-label">属性</label>
                                        <select id="property" class="form-control">
                                        </select>
                                    </div>
                                   	<div class="form-group col-md-6">
                                    	<label for="orderBy" class="col-form-label">排序值<span class="text-danger">*</span></label>
	                                    <input id="orderBy" type="text" value="0" name="demo0" data-bts-min="0" data-bts-max="999" data-bts-init-val="" data-bts-step="1" data-bts-decimal="0" data-bts-step-interval="100" data-bts-force-step-divisibility="round" data-bts-step-interval-delay="500" data-bts-prefix="" data-bts-postfix="" data-bts-prefix-extra-class="" data-bts-postfix-extra-class="" data-bts-booster="true" data-bts-boostat="10" data-bts-max-boosted-step="false" data-bts-mousewheel="true" data-bts-button-down-class="btn btn-secondary" data-bts-button-up-class="btn btn-secondary"/>
                                    </div>
                                </div>
                                
		                         <div class="form-group" style="margin-top:10px">
		                             <label class="header-title m-t-0">商品详情(可选项).CTRL+SHIFT+F全屏</label>
		                             <textarea id="elm1" name="area"></textarea>
		                         </div>
                         
                                <button type="submit" class="btn btn-primary">提交</button>
                            </form>
                			<!-- end row -->

                

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
		
		<!-- fileupload js -->
        <script type="text/javascript" src="<c:url value='/resource/plugins/lightbox2/2.8.1/js/lightbox.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resource/plugins/layui/layui.js'/>"></script>
		
        <!-- Parsley js -->
        <script type="text/javascript" src="<c:url value='/resource/plugins/parsleyjs/parsley.min.zh.js'/>"></script>
        
        <!--Wysiwig js-->
        <script src="<c:url value='/resource/plugins/tinymce/tinymce.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/tinymce/langs/zh_CN.js'/>"></script>
        
        <!-- plugin Js -->
        <script type="text/javascript" src="<c:url value='/resource/plugins/bootstrap-touchspin/js/jquery.bootstrap-touchspin.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/bootstrap-maxlength/bootstrap-maxlength.js'/>" type="text/javascript"></script>
        
        
        
        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
		<script type="text/javascript">
			$(document).ready(function () {
                if($("#elm1").length > 0){
                    tinymce.init({
                        selector: "textarea#elm1",
                        theme: "modern",
                        height:800,
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
			// 添加商品类别
			function option_add(obj,value,text) {  
			    obj.append("<option value='"+value+"'>"+text+"</option>");  
			}
			function category_add_arr(arr) {
				var selObj = $("#cid");  
				var index = -1;
				var filterarray = $.grep(arr,function(value){
					option_add(selObj,value.id,value.name);
					index++;
				});
				if(index<0){
					option_add(selObj,'','空');
					alert('缺少商品分类,请先添加类别再添加商品.');
				}
			}
			function property_add_arr(arr) {
				var selObj = $("#property");
				option_add(selObj,'','空');
				var index = -1;
				var filterarray = $.grep(arr,function(value){
					option_add(selObj,value.id,value.name);
					index++;
				});
				if(index<0){
					alert('缺少商品分类,请先添加类别再添加商品.');
				}
			}
			$(document).ready(function(){
				var _index = null;
				$.ajax({
					url: '<c:url value='/product/listPropertyAndCategory'/>',
					type : 'post',
           			dataType: "json",
           			beforeSend:function(){  
	                    _index = layer.load(2);
	                },
					success : function(data) {
					    if(data.ok){
					    	category_add_arr(data.result[0]);
					    	property_add_arr(data.result[1]);
						    
					    }else{
					    	alert('查询失败!请联系管理员');
					    }
					    layer.close(_index);
					},
					error:function(jqXHR, textStatus, errorThrown){
						layer.close(_index);
						alert("网络请求失败!请联系管理员");
			        }
				});
				
				
				
			}); 
			
			var tempFile='';
			var tempPic1='';
			var tempPic2='';
			var tempPic3='';
			$("input[name='demo1_1']").TouchSpin({
			    min: 0,
			    max: 99999,
			    step: 0.01,
			    decimals: 2,
			    boostat: 5,
			    prefix: '￥',
			    maxboostedstep: 10,
			    buttondown_class: 'btn btn-gradient',
			    buttonup_class: 'btn btn-gradient'
			});
			layui.use('upload', function(){
			  upload = layui.upload;
			  
			  //普通图片上传
			  var uploadInst = upload.render({
			    elem: '#test1'
			    ,url: '<c:url value='/api/upload/image'/>'
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo1').attr('src', result); //图片链接（base64）
			        $('#preview-img').attr('href', result);
			        $('#preview-img').attr('data-lightbox', 'gallery');
			      });
			    }
			    ,done: function(res){
			      //如果上传失败
			      if(res.code == 0){
			      	tempFile = '';
			        return layer.msg('上传失败');
			      }else{
			      	//上传成功
			      	tempFile = res.msg.url;
			      	return layer.msg('上传成功');
			      }
			      
			    }
			    ,error: function(){
			      tempFile = '';
			      //演示失败状态，并实现重传
			      var demoText = $('#demoText');
			      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
			      demoText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			  });
			  
			  
			  upload.render({
			    elem: '#tag1'
			    ,url: '<c:url value='/api/upload/image'/>'
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo11').attr('src', result); //图片链接（base64）
			      });
			    }
			    ,done: function(res){
			      //如果上传失败
			      if(res.code == 0){
			      	tempPic1 = '';
			        return layer.msg('上传失败');
			      }else{
			      	//上传成功
			      	tempPic1 = res.msg.url;
			      	return layer.msg('上传成功');
			      }
			      
			    }
			    ,error: function(){
			      tempPic1 = '';
			      //演示失败状态，并实现重传
			      var demoText = $('#demoText11');
			      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
			      demoText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			  });
			
			
				upload.render({
			    elem: '#tag2'
			    ,url: '<c:url value='/api/upload/image'/>'
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo22').attr('src', result); //图片链接（base64）
			      });
			    }
			    ,done: function(res){
			      //如果上传失败
			      if(res.code == 0){
			      	tempPic2 = '';
			        return layer.msg('上传失败');
			      }else{
			      	//上传成功
			      	tempPic2 = res.msg.url;
			      	return layer.msg('上传成功');
			      }
			      
			    }
			    ,error: function(){
			      tempPic2 = '';
			      //演示失败状态，并实现重传
			      var demoText = $('#demoText22');
			      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
			      demoText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			  });
			  
			  
			  upload.render({
			    elem: '#tag3'
			    ,url: '<c:url value='/api/upload/image'/>'
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo33').attr('src', result); //图片链接（base64）
			      });
			    }
			    ,done: function(res){
			      //如果上传失败
			      if(res.code == 0){
			      	tempPic3 = '';
			        return layer.msg('上传失败');
			      }else{
			      	//上传成功
			      	tempPic3 = res.msg.url;
			      	return layer.msg('上传成功');
			      }
			      
			    }
			    ,error: function(){
			      tempPic3 = '';
			      //演示失败状态，并实现重传
			      var demoText = $('#demoText33');
			      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
			      demoText.find('.demo-reload').on('click', function(){
			        uploadInst.upload();
			      });
			    }
			  });
			  
			  
			  
			});
			
			
			$('#formSubmitAction').submit(function() {
				var name = $('#defaultconfig').val().trim();
				var cid = $('#cid').val().trim();
				var stores = $('#stores').val().trim();
				var minPrice = $('#minPrice').val().trim();
				var originalPrice = $('#originalPrice').val().trim();
				var commission = $('#commission').val().trim();
				var property = $('#property').val().trim();
				var orderBy = $('#orderBy').val().trim();
				var content = tinymce.activeEditor.getContent().trim();
				var token = $('#token').val().trim();
				layer.confirm('是否提交？', {
				  btn: ['是','否'] //按钮
				}, function(){
					if(tempFile.length<1){
						layer.open({
						  title: '信息'
						  ,content: '请先上传图片!'
						});	
						return false;	 
					}else{
						$.ajax({
							url: '<c:url value='/product/save'/>',
							type : 'post',
							data: {
							"productCustom.name":name,
							"productCustom.cid":cid,
							"productCustom.pic":tempFile,
							"productCustom.stores":stores,
							"productCustom.minPrice":minPrice,
							"productCustom.originalPrice":originalPrice,
							"productCustom.commission":commission,
							"productCustom.propertyIds":property,
							"productCustom.orderBy":orderBy,
							"content":content,
							"token":token,
							"pic1":tempPic1,"pic2":tempPic2,"pic3":tempPic3},
			          		dataType: "json",
			          		beforeSend:function(){  
			                    _index = layer.load(2);
			                },
							success : function(data) {
								layer.close(_index);
							    if(data.ok){
								    // 此处可对 data 作相关处理
								    layer.msg(data.result, {icon: 1});
							    }else{
							    	layer.msg('提交失败！'+data.result, {icon: 1});
							    }
							    return false;
							},
							error:function(jqXHR, textStatus, errorThrown){
								layer.close(_index);
								layer.msg('提交失败!', {icon: 1});
								return false;
					        }
						});
					}
					 
				}, function(){
				});
				return false;
			});
		</script>
		<!-- Init Js file -->
        <script type="text/javascript" src="<c:url value='/resource/assets/pages/jquery.form-advanced.init.js'/>"></script>
    </body>
</html>
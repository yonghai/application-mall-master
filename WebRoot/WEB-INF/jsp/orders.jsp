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

		<!-- DataTables -->
        <link href="<c:url value='/resource/plugins/datatables/dataTables.bootstrap4.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/plugins/datatables/buttons.bootstrap4.min.css'/>" rel="stylesheet" type="text/css" />
        <!-- Responsive datatable examples -->
        <link href="<c:url value='/resource/plugins/datatables/responsive.bootstrap4.min.css'/>" rel="stylesheet" type="text/css" />
		
        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />

		<!-- 图片预览 -->
		<link rel="stylesheet" href="<c:url value='/resource/plugins/lightbox2/2.8.1/css/lightbox.css'/>">
        
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
                                    <li class="breadcrumb-item"><a href="#">订单及用户</a></li>
                                    <li class="breadcrumb-item active">订单列表</li>
                                </ol>
                            </div>
                            <h4 class="page-title">订单列表</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title end breadcrumb -->
				
				<div class="row">
                    <div class="col-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b>订单列表</b></h4>
                            <p class="text-muted font-14 m-b-30">
                            	
                            </p>

                            <table id="datatable-buttons" class="table table-striped table-bordered table-centered" cellspacing="0" width="100%">
                                <thead class="text-center">
                                <tr>
                                    <th style="vertical-align: middle;">用户名</th>
                                    <th style="vertical-align: middle;">用户ID</th>
                                    <th style="vertical-align: middle;">订单号</th>
                                    <th style="vertical-align: middle;">价格(￥)</th>
                                    <th style="vertical-align: middle;">备注</th>
                                    <th style="vertical-align: middle;">运单号</th>
                                    <th style="vertical-align: middle;">联系人</th>
                                    <th style="vertical-align: middle;">手机号</th>
                                    <th style="vertical-align: middle;">地址</th>
                                    <th style="vertical-align: middle;">操作</th>
                                </tr>
                                </thead>

								<div class="form-row">
					                <div class="form-group col-md-4">
					                    <label for="orderid" class="col-form-label">订单号</label>
					                    <input type="text" class="form-control" id="orderid" name="orderid">
					                </div>
					                <div class="form-group col-md-3">
					                    <label for="orderType" class="col-form-label">订单类型</label>
					                    <select name="orderType" id="orderType" class="form-control">
					                    	<option value ="">所有</option>
					                    	<option value ="-1">待付款</option>
					                    	<option value ="0">待发货</option>
					                    	<option value ="1">待收货</option>
					                    	<option value ="2">待评价</option>
					                    	<option value ="3">已完成</option>
					                    </select>
					                </div>
					                <div class="form-group col-md-3">
					                    <label for="userid" class="col-form-label">用户ID</label>
					                    <input type="text" class="form-control" id="userid" name="userid">
					                </div>
					                <div class="form-group col-md-1">
					                 	<label class="col-form-label">点击搜索</label>
					                    <button name="search" id="search" class="form-control btn btn-primary waves-light waves-effect w-md" type="submit">查询</button>
					                </div>
					            </div>
					            
                                <tbody class="text-center">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
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

		<!-- Required datatable js -->
        <script src="<c:url value='/resource/plugins/datatables/jquery.dataTables.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/datatables/dataTables.bootstrap4.min.js'/>	"></script>
        <!-- Buttons examples -->
        <script src="<c:url value='/resource/plugins/datatables/dataTables.buttons.min.js'/>	"></script>
        <script src="<c:url value='/resource/plugins/datatables/buttons.bootstrap4.min.js'/>	"></script>
        <script src="<c:url value='/resource/plugins/datatables/jszip.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/datatables/pdfmake.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/datatables/vfs_fonts.js'/>"></script>
        <script src="<c:url value='/resource/plugins/datatables/buttons.html5.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/datatables/buttons.print.min.js'/>"></script>
        <!-- Responsive examples -->
        <script src="<c:url value='/resource/plugins/datatables/dataTables.responsive.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/datatables/responsive.bootstrap4.min.js'/> "></script>
       	
       	<!-- 图片预览 -->
       	<script type="text/javascript" src="<c:url value='/resource/plugins/lightbox2/2.8.1/js/lightbox.min.js'/>"></script>
		<!-- 遮罩图层 -->
        <script type="text/javascript" src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
        
        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
		
		<script type="text/javascript">
			var table;
			$(document).ready(function() {
                //Buttons examples
                table = $('#datatable-buttons').DataTable({
                    lengthChange: false,
                    buttons: ['copy', 'excel', 'pdf','csv','print'],
	  				"bServerSide" : true,
	  				"bSort" : false,// 排序
	  				"bFilter" : false,// 搜索栏 
	  				"bProcessing" : true,
	  				"stateSave": true,
	  				"oLanguage": {
		                "sLengthMenu": "每页显示 _MENU_ 条记录",
		                "sZeroRecords": "没有检索到数据",
		                "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
		                "sInfoEmpty": "没有数据",
		                "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
		                "sSearch": "查找账户",
		                "oPaginate": {
		                    "sFirst": "首页",
		                    "sPrevious": "前一页",
		                    "sNext": "后一页",
		                    "sLast": "尾页"
		                }
		            },
                    ajax: function (data, callback, settings) {
		                //封装请求参数
		                var param={};
		                //param.token = $('#token').val().trim();
		               	param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
	                	param.start = data.start;//开始的记录序号
	                	param.page = (data.start / data.length)+1;//当前页码
	                	
	                	var orderid = $('#orderid').val().trim();
	                	var orderType = $('#orderType').val().trim();
	                	var userid = $('#userid').val().trim();
	                	var data = {"orderCustom.limit":param.limit,
	                	"orderCustom.start":param.start,
	                	"orderCustom.orderNumber":orderid,
	                	"orderCustom.status":orderType,
	                	"orderCustom.appid":userid,
	                	"orderCustom.page":param.page};
		                //ajax请求数据
		                $.ajax({
		                    type: "POST",
		                    url: "<c:url value="/order/listPage"/>",
		                    cache: false,  //禁用缓存
		                    data: data, //传入组装的参数
		                    dataType: "json",
		                    success: function (result) {
		                        //console.log(result.result);
		                        //封装返回数据
	                            var returnData = {};
	                            if(result.ok){
		                            returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
		                            returnData.recordsTotal = result.result.recordsTotal;//返回数据全部记录
		                            returnData.recordsFiltered = result.result.recordsFiltered;//后台不实现过滤功能，每次查询均视作全部结果
		                            returnData.data = result.result.data;//返回的数据列表
	                            }else{
	                            	alert("返回列表数据失败,请重新登录或联系管理员:"+result.result);
	                            }
	                            
	                            //console.log(returnData);
	                            //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
	                            //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
	                            callback(returnData);
		                    }
		                });
		            },
		            //用户名 订单号 价格(￥) 备注 运单号 联系人 手机号 地址
		            columns: [
						{ "data": "user.nickName" },
						{ "data": "user.appid" },
		                { "data": "orderNumber" },
		                { "data": "amountReal" },
		                { "data": "remark" },
		                { "data": "trackingNumber" },
		                { "data": "linkMan" },
		                { "data": "mobile" },
		                { "data": "address" },
						{ 
		                	"data": "id",
		                	"className": "td-manage",  
		                	"render": function ( data, type, full, meta ) {  
		                		var _html = '';
		                		if(eval(0) == eval(full.status)){
		                			_html='<a onClick="fahuo(this,\''+full.orderNumber+'\')" href="javascript:;" title="发货"><i class="dripicons-direction"></i></a> ';
		                		}
			                    _html = _html + '<a class="ml-2" onClick="edit(\'查看订单\',\'<c:url value="/order/detail2"/>\',\''+full.orderNumber+'\')" href="javascript:;" title="查看订单"><i class="dripicons-preview"></i></a>';
			                    _html = _html + '<a class="ml-2" onClick="del(this,\''+full.orderNumber+'\')" href="javascript:;" title="删除"><i class="dripicons-trash"></i></a>';
			                    return _html;  
			                } 
						}
		            ]
                    
                });
				new $.fn.dataTable.Buttons( table, {
				    buttons: ['copy', 'excel', 'csv','print'],
				} );
                table.buttons().container()
                        .appendTo('#datatable-buttons_wrapper .col-md-6:eq(0)');
            } );
            

			/*编辑*/
			function edit(title,url,orderNumber){
				url = url+'?orderCustom.orderNumber='+orderNumber;
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*删除*/
			function del(obj,orderNumber){
				layer.confirm('确认要删除吗？记得给买家退款哦~',function(index){
					$.ajax({
						url: '<c:url value='/order/delete'/>',
						type : 'post',
						data: {"orderCustom.orderNumber":orderNumber},
			         	dataType: "json",
						success : function(data) {
							if(data.ok){
								layer.msg(data.result,{icon: 5,time:1000});
								window.location.reload();
							}else{
								layer.msg('操作失败!'+data.result,{icon: 5,time:1000});
						    }
						},
						error:function(jqXHR, textStatus, errorThrown){
							layer.msg('操作失败!',{icon: 5,time:1000});
				        }
					});	
				});
			}
			
			/*发货*/
			function fahuo(obj,orderNumber){
				layer.confirm('选择发货方式', {
				  btn: ['手动发货','免物流发货'] //按钮
				}, function(){
					layer.prompt({title: '输入任何口令，并确认', formType: 3}, function(text, index){
						$.ajax({
							url: '<c:url value='/order/fahuo'/>',
							type : 'post',
							data: {"orderCustom.orderNumber":orderNumber,"orderCustom.trackingNumber":text},
				         	dataType: "json",
							success : function(data) {
								if(data.ok){
									layer.msg(data.result,{icon: 5,time:1000});
									window.location.reload();
								}else{
									layer.msg('操作失败!'+data.result,{icon: 5,time:1000});
							    }
							},
							error:function(jqXHR, textStatus, errorThrown){
								layer.msg('操作失败!',{icon: 5,time:1000});
					        }
						});	
					});
				  
				}, function(){
				  layer.confirm('免物流发货', {
				    time: 20000, //20s后自动关闭
				    btn: ['确认', '取消']
				  }, function(){
					  $.ajax({
							url: '<c:url value='/order/fahuo'/>',
							type : 'post',
							data: {"orderCustom.orderNumber":orderNumber},
				         	dataType: "json",
							success : function(data) {
								if(data.ok){
									layer.msg(data.result,{icon: 5,time:1000});
									window.location.reload();
								}else{
									layer.msg('操作失败!'+data.result,{icon: 5,time:1000});
							    }
							},
							error:function(jqXHR, textStatus, errorThrown){
								layer.msg('操作失败!',{icon: 5,time:1000});
					        }
						});	
					});
				});
			}
            
            $("#search").click(function() {
            	//table.fnClearTable();//清空数据.fnClearTable();//清空数据
        		//table.fnDestroy(); //还原初始化了的datatable
        		table.draw(false);
        		
		    });
            
		</script>
		
		
    </body>
</html>
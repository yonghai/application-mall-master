<%@ page language="java" pageEncoding="UTF-8"%>
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

        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>
        
        <div>
				<div class="row">
                    <div class="col-12">
                        <div class="card-box table-responsive">
                            <h4 class="m-t-0 header-title"><b>优惠券去向列表</b></h4>
                            <p class="text-muted font-14 m-b-30">
                            	在这里可以查询到发行的优惠券到谁的账号里
                            </p>

                            <table id="datatable-buttons" class="table table-striped table-bordered table-centered" cellspacing="0" width="100%">
                                <thead class="text-center">
                                <tr>
                                    <th style="vertical-align: middle;">优惠券名称</th>
                                    <th style="vertical-align: middle;">优惠金额</th>
                                    <th style="vertical-align: middle;">满X元使用</th>
                                    <th style="vertical-align: middle;">有效期</th>
                                    <th style="vertical-align: middle;">用户名</th>
                                    <th style="vertical-align: middle;">用户头像</th>
                                </tr>
                                </thead>


                                <tbody class="text-center">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- end row -->
				
				
				
				
        </div>
        <!-- end wrapper -->



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
	  				"bServerSide" : true,
	  				"bSort" : false,// 排序
	  				"bFilter" : false,// 搜索栏 
	  				"bProcessing" : true,  
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
		                //ajax请求数据
		                $.ajax({
		                    type: "POST",
		                    url: "<c:url value="/coupons/fetchList"/>",
		                    cache: false,  //禁用缓存${flag }
		                    data: {"couponsCustom.flag":'${flag}',"couponsCustom.limit":param.limit,"couponsCustom.start":param.start,"couponsCustom.page":param.page}, //传入组装的参数
		                    dataType: "json",
		                    success: function (result) {
		                        //console.log(result);
		                        //封装返回数据
	                            var returnData = {};
	                            if(result.code === 1){
		                            returnData.draw = result.data.draw;//这里直接自行返回了draw计数器,应该由后台返回
		                            returnData.recordsTotal = result.data.recordsTotal;//返回数据全部记录
		                            returnData.recordsFiltered = result.data.recordsFiltered;//后台不实现过滤功能，每次查询均视作全部结果
		                            returnData.data = result.data.data;//返回的数据列表
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
		            //优惠券名称 优惠金额 满X元使用 有效期 操作
		            columns: [
		                { "data": "name" },
		                { "data": "money" },
		                { "data": "moneyHreshold" },
		                { "data": "dateEnd" },
		                { "data": "wxUserCustom.nickName" },
		                { 
		                	"data": "wxUserCustom.avatarUrl",
		                	"className": "my_class",  
		                	"render": function ( data, type, full, meta ) {  
			                    return '<div><img height="50px" class="picture-thumb" src="'+data+'"></div>';  
			                } 
						},
		            ]
                    
                });
            } );
            
		</script>
		
		
    </body>
</html>
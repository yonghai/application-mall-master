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

        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>

        
        <div class="wrapper">
            <div class="container-fluid">
				<c:if test="${result.ok==true}">
	                <div class="row">
	                    <div class="col-sm-6" style="margin: auto;">
	
	                        <div class="card-box">
	                            <h4 class="header-title m-t-0">新增类别</h4>
	                            <p class="text-muted font-14 m-b-20">
	                                新增一个类别，在该类别下添加商品。
	                            </p>
	
	                            <form id="formSubmitAction" role="form">
	                            	<input type="hidden" id="token" name="token" value="${token }">
	                                <div class="form-group">
	                                    <label for="cname">分类名称<span class="text-danger">*</span></label>
	                                    <input type="text" name="cname" parsley-trigger="change" required
	                                           placeholder="输入分类名称" value="${result.result.name}" class="form-control" id="cname">
	                                </div>
	                                <div class="form-group">
	                                    <label for="orderBy">排序值<span class="text-danger">*</span></label>
	                                    <input type="text" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="2" name="orderBy" parsley-trigger="change" required
	                                           placeholder="输入0~99数字" value="${result.result.orderBy}" class="form-control" id="orderBy"/>
	                                </div>
	
									<button type="submit" class="btn btn-primary text-right m-b-0">提交</button>
	
	                            </form>
	                        </div> <!-- end card-box -->
	                    </div>
	                </div>
                
                </c:if>
	           <c:if test="${result.ok==false}">
					<div>${result.result},请重新登录</div>
				</c:if>
                
                
                

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
        
        <!-- Parsley js -->
        <script type="text/javascript" src="<c:url value='/resource/plugins/parsleyjs/parsley.min.zh.js'/>"></script>
        
        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
		<script type="text/javascript">
			$('#formSubmitAction').submit(function() {
				var cname = $('#cname').val().trim();//标题
				var orderBy = $('#orderBy').val().trim();
				var token = $('#token').val().trim();
				$.ajax({
					url: '<c:url value='/product/category/update'/>',
					type : 'post',
					data: {"productCategoryCustom.id":'${result.result.id}',"productCategoryCustom.name":cname,"productCategoryCustom.orderBy":orderBy,"token":token},
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
							var index = parent.layer.getFrameIndex(window.name);
							parent.layer.close(index);
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
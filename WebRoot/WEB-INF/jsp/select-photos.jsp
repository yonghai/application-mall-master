<%@ page language="java" pageEncoding="UTF-8" errorPage="/horizontal/page-500.html"%>
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
        <link rel="shortcut icon" href="<c:url value='/horizontal/assets/images/favicon.ico'/>">

        <!-- App css -->
        <link href="<c:url value='/horizontal/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/horizontal/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/horizontal/assets/css/style.css'/>" rel="stylesheet" type="text/css" />
        
		<!-- 相册 css -->
		<link rel="stylesheet" href="<c:url value='/custom/css/xc.css'/>">
		
		<script src="<c:url value='/horizontal/assets/js/modernizr.min.js'/>"></script>
		
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
            <div class="container-fluid">
				<c:if test="${result.ok==true}">
					
					<div class="xc-all-box clearfix">
						<c:forEach items="${result.result.dataList}" var="item">
							<div style="width: 200px; height: 250px;" class="subxc-list">
								<div class="img">
									<a href="${item.photoAddress}" data-lightbox="gallery" data-title="${item.photoName}">
										<img src="${item.smallPhoto}">
									</a>
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


        <!-- jQuery  -->
        <script src="<c:url value='/horizontal/assets/js/jquery.min.js'/>"></script>
        <script src="<c:url value='/horizontal/assets/js/bootstrap.min.js'/>"></script>
        <script src="<c:url value='/horizontal/assets/js/waves.js'/>"></script>
        <script src="<c:url value='/horizontal/assets/js/jquery.slimscroll.js'/>"></script>

        <!-- App js -->
        <script src="<c:url value='/horizontal/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/horizontal/assets/js/jquery.app.js'/>"></script>
        
        <!-- 遮罩图层 -->
        <script type="text/javascript" src="<c:url value='/custom/layer/2.4/layer.js'/>"></script>
        <script type="text/javascript">
        	function onSuccess(data){
        		var html = '<div class="subxc-list"><div class="img"><a href="'+data.photoAddress+'" data-lightbox="gallery" data-title="'+data.photoName+'">'
							+'<img src="'+data.smallPhoto+'"></a></div><p>'+data.username+'</p><div class="xc-opr clearfix">'
							+'<a href="javascript:void(0)" data-value="'+data.photoAddress+'" id="xc-fm-a" class="xc-fm-a">复制链接</a><a href="javascript:void(0)" '
							+'onclick="delImg(\''+data.id+'\',\''+data.photoName+'\',\''+data.photoType+'\',this)" id="xc-xiugai-a" class="xc-xiugai-a">删除</a></div></div>';
        	
        		$("#preview").append(html);
        		
        		copyToBoard();
			}
			/*上传图片*/
	        $("#addImg").click(function(){
			  	var index = layer.open({
					type: 2,
					title: '图片上传',
					content: '<c:url value='/horizontal/jsp/upload.jsp?method=upload_photos'/>',
					area: ['800px', '450px'], //宽高
					cancel: function(e){
				  	},
				  	end:function(e){
						//location.reload();
					}
				});
			});
			
			/**删除图片**/
			function delImg(id,name,photoType,obj){
			  	$.ajax({//token:$('#token').val().trim(
					url: '<c:url value='/photo'/>',
					type : 'post',
					data: {id:id, method:'deletePhoto',name:name,photoType:photoType},
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
        	
        	
        </script>
    </body>
</html>
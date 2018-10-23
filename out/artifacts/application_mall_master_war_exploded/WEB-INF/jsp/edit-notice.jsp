<%@ page language="java"  pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <title>后台管理系统</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="/resource/assets/images/favicon.ico">

        <!-- App css -->
        <link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/icons.css'/>" rel="stylesheet" type="text/css" />
        <link href="<c:url value='/resource/assets/css/style.css'/>" rel="stylesheet" type="text/css" />

        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>
	<div class="container-fluid">
		<c:if test="${result.ok==true}">
			<div class="row">
				<div class="col-12">
					<div class="card-box">
						<form id="formSubmitAction" role="form">
							<input type="hidden" id="token" name="token" value="${token }">
							<div class="form-group">
								<label for="notice_name">
									公告
									<span class="text-danger">*</span>
								</label>
								<input type="text" name="notice_name" parsley-trigger="change"
									required placeholder="输入公告标题" class="form-control"
									id="notice_name" value="${result.result.title}">
							</div>
	
							<div class="form-group">
								<label class="header-title m-t-0">
									公告内容,可选项.CTRL+SHIFT+F全屏
								</label>
								<textarea id="elm1" name="area">${result.result.content}</textarea>
							</div>
	
							<button type="submit" class="btn btn-primary">
								提交
							</button>
						</form>
	
	
					</div>
				</div>
			</div>
		<!--  end row -->
		</c:if>
           <c:if test="${result.ok==false}">
			<div>${result.result},请重新登录</div>
		</c:if>
		
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

		<script type="text/javascript" src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
		
		<!--Wysiwig js-->
        <script src="<c:url value='/resource/plugins/tinymce/tinymce.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/tinymce/langs/zh_CN.js'/>"></script>
        
        <!-- md5 -->
		<script type="text/javascript" src="<c:url value='/resource/custom/js/md5.js'/>"></script>
		
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
                $('form').parsley();
            });
			
			$('#formSubmitAction').submit(function() {
				var name = $('#notice_name').val().trim();//标题
				var content = tinymce.activeEditor.getContent().trim();
				var token = $('#token').val();
				var id = '${result.result.id}';
				$.ajax({
					url: '<c:url value='/notice/update'/>',
					type : 'post',
					data: {"noticeCustom.id":id,"noticeCustom.title":name,"noticeCustom.content":content,"token":token},
      				dataType: "json",
      				beforeSend:function(){  
	                    _index = layer.load(2);
	                },
					success : function(data) {
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
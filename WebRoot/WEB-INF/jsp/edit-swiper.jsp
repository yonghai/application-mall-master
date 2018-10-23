<%@ page language="java" pageEncoding="UTF-8" errorPage="/horizontal/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>后台管理系统</title>
        <meta name="renderer" content="webkit">
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

		<!-- fileupload css -->
        <link rel="stylesheet" href="<c:url value='/resource/plugins/lightbox2/2.8.1/css/lightbox.css'/>">
        <link rel="stylesheet" href="<c:url value='/resource/plugins/layui/css/layui.css'/>">
  
        
        <script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

    </head>

    <body>
           


        <div>
            <div class="container-fluid">
				<c:if test="${result.ok==true}">
	                <!-- Page-Title -->
	                <div class="row">
	                    <div class="col-sm-12">
	                        <div class="page-title-box">
	                            <div class="btn-group pull-right">
	                                <ol class="breadcrumb hide-phone p-0 m-0">
	                                    <li class="breadcrumb-item"><a href="#">Abstack</a></li>
	                                    <li class="breadcrumb-item"><a href="#">基本设置</a></li>
	                                    <li class="breadcrumb-item active">编辑轮播图</li>
	                                </ol>
	                            </div>
	                            <h4 class="page-title">编辑轮播图</h4>
	                        </div>
	                    </div>
	                </div>
	                <!-- end page title end breadcrumb -->
					
					<div class="row">
	                    <div class="col-12">
	                        <div class="card-box">
	                            <h4 class="header-title m-t-0">编辑轮播图</h4>
	                            <p class="text-muted font-14 m-b-10">
	                                	上传图片最为轮播图,内容编辑可选,如果填写html富文本,则该轮播图是带链接的,否则小程序上点击轮播图无效果,视为无链接.
	                            </p>
								
								<form id="formSubmitAction" role="form">
									<input type="hidden" id="token" name="token" value="${token }">
									<div class="form-group">
										<div class="layui-upload">
										  <button type="button" class="layui-btn" id="test1">替换图片</button>
										  <div class="layui-upload-list">
										  	<a href="${result.result.url}" data-lightbox="gallery" data-title="" id="preview-img">
										  		<img style="display:block;height:100%;height:150px;" src="${result.result.thumb}" class="layui-upload-img" id="demo1">
										  	</a>
										    <p id="demoText"></p>
										  </div>
										</div>  
	                                </div>
	                                
	                                <div class="form-group">
	                                    <label class="header-title m-t-0">随便写点啥CTRL+SHIFT+F全屏</label>
	                                    <textarea id="elm1" name="area">${result.result.content}</textarea>
	                                </div>
	                                
	                                <button type="submit" class="btn btn-primary">更改</button>
	                            </form>
								
	                        
							</div>
	                    </div>
	                </div>
	                <!--  end row -->
				
				
				</c:if>
	            <c:if test="${result.ok==false}">
					<div>${result.result},请重新登录或联系管理员</div>
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
		
		<!-- fileupload js -->
        <script type="text/javascript" src="<c:url value='/resource/plugins/lightbox2/2.8.1/js/lightbox.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/resource/plugins/layui/layui.js'/>"></script>
		
		<!--Wysiwig js-->
        <script src="<c:url value='/resource/plugins/tinymce/tinymce.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/tinymce/langs/zh_CN.js'/>"></script>
        
        <!-- App js -->
        <script src="<c:url value='/resource/assets/js/jquery.core.js'/>"></script>
        <script src="<c:url value='/resource/assets/js/jquery.app.js'/>"></script>
		
		<!-- md5 -->
		<script type="text/javascript" src="<c:url value='/resource/custom/js/md5.js'/>"></script>
		
		<script type="text/javascript">
			var tempFile='';
			var content_md5 = hex_md5('${result.result.content}');
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
			  
			});
			
			
			$('#formSubmitAction').submit(function() {
				var content = tinymce.activeEditor.getContent().trim();
				var content_md5_new = hex_md5(content);
				var token = $('#token').val();
				var id = '${result.result.id}';
				var url = '${result.result.url}';
				var modified = true;
				if($.trim(content_md5) == $.trim(content_md5_new)){
					modified=false;
				}
				//上传图片||内容修改
				if((tempFile.length>0) || ($.trim(content_md5) != $.trim(content_md5_new))){
					$.ajax({
						url: '<c:url value='/swiper/update'/>',
						type : 'post',
						data: {"swiperCustom.url":url,"swiperCustom.id":id,"fileName":tempFile,"swiperCustom.content":content,"token":token,modified:modified},
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
				}else{
					layer.open({
					  title: '信息'
					  ,content: '没有修改内容!'
					});
				}
				return false;
			});
		</script>
    </body>
</html>
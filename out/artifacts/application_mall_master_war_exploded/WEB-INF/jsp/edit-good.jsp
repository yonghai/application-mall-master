<%@ page language="java" pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>后台管理系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />

		<!-- App favicon -->
		<link rel="shortcut icon"
			href="<c:url value='/resource/assets/images/favicon.ico'/>">

		<!-- App css -->
		<link href="<c:url value='/resource/assets/css/bootstrap.min.css'/>"
			rel="stylesheet" type="text/css" />
		<link href="<c:url value='/resource/assets/css/icons.css'/>"
			rel="stylesheet" type="text/css" />
		<link href="<c:url value='/resource/assets/css/style.css'/>"
			rel="stylesheet" type="text/css" />

		<!-- Plugins css-->
		<link rel="stylesheet"
			href="<c:url value='/resource/plugins/bootstrap-touchspin/css/jquery.bootstrap-touchspin.min.css'/>">

		<!-- fileupload css -->
		<link rel="stylesheet"
			href="<c:url value='/resource/plugins/lightbox2/2.8.1/css/lightbox.css'/>">
		<link rel="stylesheet"
			href="<c:url value='/resource/plugins/layui/css/layui.css'/>">

		<script src="<c:url value='/resource/assets/js/modernizr.min.js'/>"></script>

	</head>

	<body>

		<div class="container-fluid" style="margin-top: 10px;">
			<c:if test="${result.ok==true}">
			<div class="row">
				<div class="col-sm-12">

					<div class="card-box" style="margin-bottom: 100px;">
						<h4 class="header-title m-t-0">
							新增商品
						</h4>
						
							<!-- Form row -->
							<form id="formSubmitAction" role="form">
								<input type="hidden" id="token" name="token" value="${token }">
								<div class="form-group">
									<label for="name">
										商品名称
										<span class="text-danger">*</span>
									</label>
									<input type="text" class="form-control" maxlength="50"
										name="defaultconfig" id="defaultconfig" placeholder="输入商品名称"
										required parsley-trigger="change" value="${result.result.basicInfo.name}" />
	
								</div>
								<div class="form-row">
									<div class="form-group col-md-12">
										<button type="button" class="layui-btn header-title m-t-0"
											id="test1">
											上传封面
										</button>
										<div class="layui-upload-list">
	
											<a href="${result.result.basicInfo.pic}" data-title="" id="preview-img" data-lightbox="gallery">
												<img style="display: block; height: 100%; height: 150px;"
													src="${result.result.basicInfo.pic}"
													class="layui-upload-img" id="demo1"> </a>
											<p id="demoText"></p>
										</div>
									</div>
								</div>
								<label class="col-form-label">展示图</label>
                                <div class="form-group form-row">
									<div class="layui-upload-list" id="tag1">
									  	<img style="display:block;height:100%;height:150px;" src="${result.result.pics[0]}" onerror="this.src='<c:url value='/resource/assets/images/img_xc_bg.jpg'/>'" class="layui-upload-img" id="demo11">
									    <p id="demoText11"></p>
									</div>
									<div class="layui-upload-list" id="tag2" style="margin-left: 10px;">
									  	<img style="display:block;height:100%;height:150px;" src="${result.result.pics[1]}" onerror="this.src='<c:url value='/resource/assets/images/img_xc_bg.jpg'/>'" class="layui-upload-img" id="demo22">
									    <p id="demoText22"></p>
									</div>
									<div class="layui-upload-list" id="tag3" style="margin-left: 10px;">
									  	<img style="display:block;height:100%;height:150px;" src="${result.result.pics[2]}" onerror="this.src='<c:url value='/resource/assets/images/img_xc_bg.jpg'/>'" class="layui-upload-img" id="demo33">
									    <p id="demoText33"></p>
									</div>
		                         </div>
								<div class="form-row">
									<div class="form-group col-md-6">
										<label for="cid" class="col-form-label">
											所属商品种类
											<span class="text-danger">*</span>
										</label>
										<select id="cid" class="form-control">
											<c:forEach items="${result.result.categorys}" var="item">
												<c:choose>
												   <c:when test="${result.result.basicInfo.cid==item.id}">  
												        <option value="${item.id}" selected = "selected">
															${item.name}
														</option>
												   </c:when>
												   <c:otherwise>
												   		<option value="${item.id}">
															${item.name}
														</option>
												   </c:otherwise>
												</c:choose>
												
											</c:forEach>
										</select>
	
									</div>
	
									<div class="form-group col-md-6">
										<label for="stores" class="col-form-label">
											库存
											<span class="text-danger">*</span>
										</label>
										<input id="stores" type="text" value="${result.result.basicInfo.stores}" name="demo0"
											data-bts-min="0" data-bts-max="999" data-bts-init-val=""
											data-bts-step="1" data-bts-decimal="0"
											data-bts-step-interval="100"
											data-bts-force-step-divisibility="round"
											data-bts-step-interval-delay="500" data-bts-prefix=""
											data-bts-postfix="" data-bts-prefix-extra-class=""
											data-bts-postfix-extra-class="" data-bts-booster="true"
											data-bts-boostat="10" data-bts-max-boosted-step="false"
											data-bts-mousewheel="true"
											data-bts-button-down-class="btn btn-secondary"
											data-bts-button-up-class="btn btn-secondary"/>
									</div>
								</div>
								<div class="form-row">
									<div class="form-group col-md-6">
										<label for="minPrice" class="col-form-label">
											活动价
											<span class="text-danger">*</span>
										</label>
										<input id="minPrice" type="text" value="${result.result.basicInfo.minPrice}" name="demo1_1"
											data-bts-min="0" data-bts-max="999" data-bts-init-val=""
											data-bts-step="1" data-bts-decimal="0"
											data-bts-step-interval="100"
											data-bts-force-step-divisibility="round"
											data-bts-step-interval-delay="500" data-bts-prefix=""
											data-bts-postfix="" data-bts-prefix-extra-class=""
											data-bts-postfix-extra-class="" data-bts-booster="true"
											data-bts-boostat="10" data-bts-max-boosted-step="false"
											data-bts-mousewheel="true"
											data-bts-button-down-class="btn btn-secondary"
											data-bts-button-up-class="btn btn-secondary"/>
									</div>
									<div class="form-group col-md-6">
										<label for="originalPrice" class="col-form-label">
											正常价
											<span class="text-danger">*</span>
										</label>
										<input id="originalPrice" type="text" value="${result.result.basicInfo.originalPrice}" name="demo1_1"
											data-bts-min="0" data-bts-max="999" data-bts-init-val=""
											data-bts-step="1" data-bts-decimal="0"
											data-bts-step-interval="100"
											data-bts-force-step-divisibility="round"
											data-bts-step-interval-delay="500" data-bts-prefix=""
											data-bts-postfix="" data-bts-prefix-extra-class=""
											data-bts-postfix-extra-class="" data-bts-booster="true"
											data-bts-boostat="10" data-bts-max-boosted-step="false"
											data-bts-mousewheel="true"
											data-bts-button-down-class="btn btn-secondary"
											data-bts-button-up-class="btn btn-secondary"/>
									</div>
								</div>
	
								<div class="form-row">
									<div class="form-group col-md-6">
										<label for="commission" class="col-form-label">
											消费返还积分
											<span class="text-danger">*</span>
										</label>
										<input id="commission" type="text" value="${result.result.basicInfo.commission}" name="demo0"
											data-bts-min="0" data-bts-max="999" data-bts-init-val=""
											data-bts-step="1" data-bts-decimal="0"
											data-bts-step-interval="100"
											data-bts-force-step-divisibility="round"
											data-bts-step-interval-delay="500" data-bts-prefix=""
											data-bts-postfix="" data-bts-prefix-extra-class=""
											data-bts-postfix-extra-class="" data-bts-booster="true"
											data-bts-boostat="10" data-bts-max-boosted-step="false"
											data-bts-mousewheel="true"
											data-bts-button-down-class="btn btn-secondary"
											data-bts-button-up-class="btn btn-secondary"/>
									</div>
									<div class="form-group col-md-6">
										<label for="commissionType" class="col-form-label">
											积分类型
										</label>
										<select id="commissionType" class="form-control">
											<option>
												默认类型
											</option>
										</select>
									</div>
								</div>
	
								<div class="form-row">
									<div class="form-group col-md-6">
										<label for="property" class="col-form-label">
											属性
										</label>
										<select id="property" class="form-control">
											<option value="">
												空
											</option>
											<c:forEach items="${result.result.properties}" var="item">
												<c:choose>
												   <c:when test="${result.result.basicInfo.propertyIds==item.id}">  
												        <option value="${item.id}" selected = "selected">
															${item.name}
														</option>
												   </c:when>
												   <c:otherwise>
												   		<option value="${item.id}">
															${item.name}
														</option>
												   </c:otherwise>
												</c:choose>
												
											</c:forEach>
										</select>
									</div>
									<div class="form-group col-md-6">
										<label for="orderBy" class="col-form-label">
											排序值
											<span class="text-danger">*</span>
										</label>
										<input id="orderBy" type="text" value="${result.result.basicInfo.orderBy}" name="demo0"
											data-bts-min="0" data-bts-max="999" data-bts-init-val=""
											data-bts-step="1" data-bts-decimal="0"
											data-bts-step-interval="100"
											data-bts-force-step-divisibility="round"
											data-bts-step-interval-delay="500" data-bts-prefix=""
											data-bts-postfix="" data-bts-prefix-extra-class=""
											data-bts-postfix-extra-class="" data-bts-booster="true"
											data-bts-boostat="10" data-bts-max-boosted-step="false"
											data-bts-mousewheel="true"
											data-bts-button-down-class="btn btn-secondary"
											data-bts-button-up-class="btn btn-secondary" />
									</div>
									<div class="form-group col-md-12" style="margin-top:10px">
			                             <label class="header-title m-t-0">商品详情(可选项).CTRL+SHIFT+F全屏</label>
			                             <textarea id="elm1" name="area">${result.result.content}</textarea>
			                         </div>
								</div>
	
								<button type="submit" class="btn btn-primary">
									提交
								</button>
							</form>
							<!-- end row -->
						
					</div>
				</div>
			</div>
			</c:if>
	        <c:if test="${result.ok==false}">
				<div>${result.result},请重新登录</div>
			</c:if>
		</div>
		<!-- end container -->
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
		<script src="<c:url value='/resource/assets/js/popper.min.js'/>"></script>
		<!--轻量级tooltips提示插件 -->
		<script
			src="<c:url value='/resource/assets/js/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/resource/assets/js/waves.js'/>"></script>
		<script
			src="<c:url value='/resource/assets/js/jquery.slimscroll.js'/>"></script>

		<!-- fileupload js -->
		<script type="text/javascript"
			src="<c:url value='/resource/plugins/lightbox2/2.8.1/js/lightbox.min.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/resource/custom/layer/2.4/layer.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/resource/plugins/layui/layui.js'/>"></script>

		<!-- Parsley js -->
		<script type="text/javascript"
			src="<c:url value='/resource/plugins/parsleyjs/parsley.min.zh.js'/>"></script>

		<!--Wysiwig js-->
        <script src="<c:url value='/resource/plugins/tinymce/tinymce.min.js'/>"></script>
        <script src="<c:url value='/resource/plugins/tinymce/langs/zh_CN.js'/>"></script>
        
		<!-- plugin Js -->
		<script type="text/javascript"
			src="<c:url value='/resource/plugins/bootstrap-touchspin/js/jquery.bootstrap-touchspin.min.js'/>"></script>
		<script
			src="<c:url value='/resource/plugins/bootstrap-maxlength/bootstrap-maxlength.js'/>"
			type="text/javascript"></script>



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
                        setup: function (ed) {    
						    // tab键
						    ed.on('keydown', function (evt) {
						        if (evt.keyCode == 9) {
						            if (evt.shiftKey) {
						                ed.execCommand('Outdent');
						            } else {
						                ed.execCommand('Indent');
						            }
						
						            evt.preventDefault();
						            evt.stopPropagation();
						        }
						    });
						},
                        toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | l      ink image | print preview media fullpage | forecolor backcolor emoticons"
                        
                    });
                   
                }
                $('formSubmitAction').parsley();
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
				var id = '${result.result.basicInfo.id}';
				var pic = '${result.result.basicInfo.pic}';
				var pic1 = '${result.result.pics[0]}';
				var pic2 = '${result.result.pics[1]}';
				var pic3 = '${result.result.pics[2]}';
				var token = $('#token').val().trim();
				layer.confirm('是否提交？', {
				  btn: ['是','否'] //按钮
				}, function(){
					$.ajax({
						url: '<c:url value='/product/update'/>',
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
							"productCustom.id":id,
							"token":token,
							"oldPic1":pic1,"oldPic2":pic2,"oldPic3":pic3,"oldPic":pic,
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
							    var index = parent.layer.getFrameIndex(window.name);
								parent.layer.close(index);
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
					 
				}, function(){
				});
				return false;
			});
			
			
		</script>
					<!-- Init Js file -->
					<script type="text/javascript"
						src="<c:url value='/resource/assets/pages/jquery.form-advanced.init.js'/>"></script>
	</body>
</html>
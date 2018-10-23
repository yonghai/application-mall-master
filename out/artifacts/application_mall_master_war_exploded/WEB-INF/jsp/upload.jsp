<%@ page language="java" pageEncoding="UTF-8" errorPage="/jsp/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>图片上传</title>

		<script src="<c:url value='/resource/custom/upload/jquery.min.js'/>"></script>
		<script src="<c:url value='/resource/custom/upload/jQuery.upload.min.js'/>"></script>
		<link rel="stylesheet" href="<c:url value='/resource/custom/upload/upload.css'/>">
		
	</head>
	<body>
		<h2 align="center">
			 图片上传
		</h2>
		<div class="case" align="center">
			<div class="upload" data-size="5120" data-num="10" action='<c:url value='/image/upload'/>' id='case'></div>
		</div>
	</body>
	<script>
		$(function(){
			$("#case").upload(function(o,data){
				parent.onSuccess(data);
			});
		})
	</script>
</html>
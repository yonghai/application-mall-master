<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" errorPage="/horizontal/page-500.html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>HTML5多文件预览上传-zyUpload</title>
		<!-- 引用控制层插件样式 -->
		<link rel="stylesheet" href="<c:url value='/custom/uploadd/control/css/zyUpload.css'/>" type="text/css">
		
		<script type="text/javascript" src="<c:url value='/custom/uploadd/control/js/jquery.min.js?v=2.1.4'/>"></script>
		<!-- 引用核心层插件 -->
		<script type="text/javascript" src="<c:url value='/custom/uploadd/core/zyFile.js'/>"></script>
		<!-- 引用控制层插件 -->
		<script type="text/javascript" src="<c:url value='/custom/uploadd/control/js/zyUpload.js'/>"></script>
		<!-- 引用初始化JS -->
		<script type="text/javascript" src="<c:url value='/custom/uploadd/control/js/jqueryrotate.js'/>"></script>

		<script type="text/javascript" src="<c:url value='/custom/uploadd/index.js'/>"></script>
	</head>
	
	<body>
		<h1 style="text-align:center;">图片上传</h1>
	    <div id="demo" class="demo"></div>   
	</body>
		        
	<script type="text/javascript">
		/**/
		//引用初始化JS
		$(function(){
		    // 初始化插件
		    $("#demo").zyUpload({
		        width            :   "650px",                 // 宽度
		        height           :   "400px",                 // 宽度
		        itemWidth        :   "120px",                 // 文件项的宽度
		        itemHeight       :   "100px",                 // 文件项的高度
		        url              :   "<c:url value='/api/upload?method=${param.method}&filePath=${param.filePath}'/>",  // 上传文件的路径
		        multiple         :   true,                    // 是否可以多个文件上传
		        dragDrop         :   true,                    // 是否可以拖动上传文件
		        del              :   true,                    // 是否可以删除文件
		        finishDel        :   false,                   // 是否在上传文件完成后删除预览
		        /* 外部获得的回调接口 */
		        onSelect: function(files, allFiles){                    // 选择文件的回调方法
		            //console.info("当前选择了以下文件：");
		            //console.info(files);
		            //console.info("之前没上传的文件：");
		            //console.info(allFiles);
		        },
		        onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法
		            //console.info("当前删除了此文件：");
		            //console.info(file);
		            //console.info("当前剩余的文件：");
		            //console.info(surplusFiles);
		        },
		        onSuccess: function(file){                    // 文件上传成功的回调方法
		            //console.info("此文件上传成功：");
		            //console.info(file);
		        },
		        onFailure: function(file){                    // 文件上传失败的回调方法
		            //console.info("此文件上传失败：");
		            //console.info(file);
		        },
		        onComplete: function(responseInfo){           // 上传完成的回调方法
		            //console.info("文件上传完成");
		            //console.info(responseInfo);
		        }
		    });
		});

		
	</script>
</html>
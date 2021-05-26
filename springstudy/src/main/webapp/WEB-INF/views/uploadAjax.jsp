<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}" scope= "application" ></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.uploadResult {
		width: 100%;
		background-color:  gray;
	}
	.uploadResult ul{
		display: flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li{
		list-style: none;
		padding: 10px;
	}
	
	.uploadResult ul li img{
		width: 100px;
	}
	
	.bigPictureWrapper {
		position: absolute;
		display: none;
		justify-content: center;
		align-content: center;
		top: 0%;
		width: 100%;
		height: 100%;
		background-color:  gray;
		z-index: 100;
	}
	
	.bigPicture {
		position: relative;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.bigPicture img{
		width: 600px;
	}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	function showImage(fileCallPath) {
		$(".bigPictureWrapper").css("display", "flex").show();
		
		$(".bigPicture")
		.html("<img src='${ctx}/display?fileName=" + fileCallPath + "'>")
		.animate({width:'100%', height:'100%'}, 1000);  /*1초간 확대 */
	}
	
	$(document).ready(function() {
		/*거부하겟다..   */ 
		var regex = new RegExp("(.*?\.(exe|sh|zip|alz)$)");
		/* 최대 5메가  */
		var maxSize = 5242880;
		
		function checkExtension(fileName, fileSize){
			if (fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}
			
			if (regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드 할 수 없습니다.");
				return false;
			}
			
			return true;
		}
		/*복사해놓기?   */
		var cloneObj = $(".uploadDiv").clone();
		
		
		$("#uploadBtn").on("click", function(e) {
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			for (var i = 0; i < files.length; i++) {
				if (!checkExtension(files[i].name, files[i].size)) {
					return false;
				}
				
				formData.append("uploadFile", files[i]);
			}
			/* 비동기방식 */
			$.ajax({
				url : "${ctx}/uploadAjaxAction",
				processData : false,
				contentType : false,
				data : formData,
				type : "post" ,
				dataType : "json",
				success : function(result) {
					console.log(result);
					showUploadedFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			});
		});
		
		var uploadResult = $(".uploadResult ul");
		
		function showUploadedFile(uploadResultArr){
			/* 문자열 출력하는 변수*/
			var str = "";
			/*반복문   */
			$(uploadResultArr).each(function(i, obj) {
				if (!obj.image) {
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/" + obj.uuid + "_" + obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g), "/");
					/*파일로 넘어왔을때 */
					str +="<li><div><a href='${ctx}/download?fileName=" + fileCallPath + "'>" +
						  "<img src ='${ctx}/resource/img/attach.png'>" + obj.fileName + "</a>" +
						  "<span data-file=\'" + fileCallPath + "\' data-type='file'>x</span>" +
						  "</div></li>";
				}else {
					var fileCallPath = encodeURIComponent(obj.uploadPath + "/s_" + obj.uuid + "_" + obj.fileName);
					var originPath = obj.uploadPath + "\\" + obj.uuid + "_" + obj.fileName;
					originPath = originPath.replace(new RegExp(/\\/g), "/");
					
					str += "<li><a href=\"javascript:showImage(\'" + originPath + "\')\">" +
						   "<img src='${ctx}/display?fileName=" + fileCallPath + "'></a>" +
						   "<span data-file=\'" + fileCallPath + "\' data-type='image'>x</span>" +
						   "</li>";
				}
			});
			/*동적으로 돔객체를 생성*/	
			uploadResult.append(str);
		}
		/* 빅픽쳐를 클릭했을때  */
		$(".bigPictureWrapper").on("click", function (e) {
			$(".bigPicture").animate({width:"0%", height:"0%"}, 1000);
			/*스크립트도 람다식이 가능하다ㅓ?   */
			setTimeout(() => {
				$(this).hide();
			}, 1000);
		});
		/*x가 클릭이벤트가 발생한다면   */
		$(".uploadResult").on("click", "span", function (e) {
			var targetFile = $(this).data("file");
			var type = $(this).data("type");
			
			$.ajax({
				url : "${ctx}/deleteFile",
				data : {fileName : targetFile, type : type}
				dataType : "text",
				type: "POST",
				success : function (result) {
					alert(result);
				}
			});
		});
	});
</script>
</head>
<body>
	<h1>Upload With Ajax</h1>
	<div class="bigPictureWrapper">
		<div class="bigPicture">
		</div>
	</div>
	<div class="uploadDiv">
		<input type="file" name="uploadFile" multiple="multiple">

	</div>
	<!--첨부파일했을떄 썸네일  -->
	<div class="uploadResult">
		<ul>
		
		</ul>
	</div>
	<!--id:스크립트에서 이벤트감지 -->
	<button id="uploadBtn">Upload</button>
</body>
</html>
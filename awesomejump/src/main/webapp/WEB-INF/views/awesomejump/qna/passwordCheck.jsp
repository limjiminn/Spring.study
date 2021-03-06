<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<s:csrfMetaTags/>
<title>Insert title here</title>
<link rel="icon" href="/resources/awesomejump/img/favicon.png">
<link rel="stylesheet" href="/resources/awesomejump/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/awesomejump/css/animate.css">
<link rel="stylesheet" href="/resources/awesomejump/css/owl.carousel.min.css">
<link rel="stylesheet" href="/resources/awesomejump/css/all.css">
<link rel="stylesheet" href="/resources/awesomejump/css/flaticon.css">
<link rel="stylesheet" href="/resources/awesomejump/css/themify-icons.css">
<link rel="stylesheet" href="/resources/awesomejump/css/magnific-popup.css">
<link rel="stylesheet" href="/resources/awesomejump/css/slick.css">
<link rel="stylesheet" href="/resources/awesomejump/css/style.css">
  
  <script defer src="/resources/awesomejump/js/jquery-1.12.1.min.js"></script>
  <script defer src="/resources/awesomejump/js/popper.min.js"></script>
  <script defer src="/resources/awesomejump/js/bootstrap.min.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.magnific-popup.js"></script>
  <script defer src="/resources/awesomejump/js/swiper.min.js"></script>
  <script defer src="/resources/awesomejump/js/masonry.pkgd.js"></script>
  <script defer src="/resources/awesomejump/js/owl.carousel.min.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.nice-select.min.js"></script>
  <script defer src="/resources/awesomejump/js/slick.min.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.counterup.min.js"></script>
  <script defer src="/resources/awesomejump/js/waypoints.min.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.ajaxchimp.min.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.form.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.validate.min.js"></script>
  <script defer src="/resources/awesomejump/js/mail-script.js"></script>
  <script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
  <!-- custom js -->
  <script defer src="/resources/awesomejump/js/custom.js"></script>
  
<script type="text/javascript">
/* 	function passwordCheck() {
		if (document.actionForm.password.value == ${qDto.qna_pw}) {
			location.href = "/awesomejump/qna/read?qna_no=" + qna_no;
		} else {
			alert ("?????? ????????? ?????????.");
		}
	} */
	var CSRFheader = $("meta[name='_csrf_header']").attr('content');
	var CSRFtoken = $("meta[name='_csrf']").attr('content');
	
	$(document).ready(function() {
	   	$("#submit").on("click", function() {
	   		
	   		var params = jQuery("#pwck").serialize();
	   		if($("#password").val() == "") {
	   			alert("??????????????? ????????? ?????????.");
	   			$("password").focus();
	   			return false;
	   		}
	   		
			$.ajax({
				url : "/awesomejump/qna/read",
				type : "POST",
				contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
				beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader, CSRFtoken),
				async: false,
				data : params,
				dataType: 'json',
				success : function(data) {
					if(data == 0) {
						alert("??????????????? ???????????? ????????????.");
						return;
					} else {
						location.href = "/awesomejump/qna/read?qna_no=${qna_no}"
					} 
				}
			});
		 
		});
	
	});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/templates/awesomejump/header.jsp"/>
<br><br><br><br><br><br>
<form id="pwck" name="actionForm" >
	<s:csrfInput/>
	<input type="hidden" name="qna_no" value="${qna_no}" >
	<div style="text-align: center;">
		<h4 class="page-header">?????? ?????????</h4>
	</div>
	<br>
	<div style="text-align: center;">
		??? ?????? ????????? ?????????.<br>
		??????????????? ????????? ?????????.<br><br>
		<input type="password" id="password" name="qna_pw" ><br>
		<button type="button" class="btn btn-defalut" id="submit" >??????</button>
	</div>
</form>
<jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp" />
</body>
</html>
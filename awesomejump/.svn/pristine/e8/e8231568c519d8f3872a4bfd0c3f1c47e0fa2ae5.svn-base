<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<!doctype html>
<html lang="ko">

  <head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <s:csrfMetaTags/>
  
  <title>AwesomeJump - login</title>
   <script type="text/javascript">
  $(document).ready(function(){
	 
	if(document.frm.email.value == "undefined"){
		alert("개인정보 미동의시 로그인이 불가능 합니다");
		location.href="/login";
	}
	
	var nickName = document.frm.nickName.value;
	
	var CSRFheader = $("meta[name='_csrf_header']").attr('content');
	var CSRFtoken = $("meta[name='_csrf']").attr('content');
	
	 $.ajax({
			type : "POST",
			url : "/nickName_check",
			dataType : "json",
			data : "nickName=" + nickName,
			beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken), 
			success : function(data) {
				
				if (data.message == "1") {
					$("#message2").html("이미 등록된 닉네임 입니다.").css("color", "red");
					$("#renickName").val("-1");
				
				}else{
					$("#message2").html("사용 가능 닉네임 입니다.").css("color", "blue");
					$("#renickName").val("1");
				}
			},
			fail : function() {
				alert("시스템에러");
			}
		}); 
  });

</script>

<script type="text/javascript">
  function nickNameCheck(){
	  var nickName = document.frm.nickName.value;
	  
	  var CSRFheader = $("meta[name='_csrf_header']").attr('content');
	  var CSRFtoken = $("meta[name='_csrf']").attr('content');
	  
	   $.ajax({
			type : "POST",
			url : "/nickName_check",
			dataType : "json",
			data : "nickName=" + nickName,
			beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken), 
			success : function(data) {
				
				if (data.message == "1") {
					$("#message2").html("이미 등록된 닉네임 입니다.").css("color", "red");
					$("#renickName").val("-1");
				
				}else{
					$("#message2").html("사용 가능 닉네임 입니다.").css("color", "blue");
					$("#renickName").val("1");
				}
			},
			fail : function() {
				alert("시스템에러");
			}
		}); 
}
  
</script>

  <link rel="icon" href="/resources/awesomejump/img/favicon.png">
  <link rel="stylesheet" href="/resources/awesomejump/css/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/animate.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/owl.carousel.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/all.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/flaticon.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/themify-icons.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/magnific-popup.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/slick.css">
  <!-- style CSS -->
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
  <script defer src="/resources/awesomejump/js/contact.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.ajaxchimp.min.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.form.js"></script>
  <script defer src="/resources/awesomejump/js/jquery.validate.min.js"></script>
  <script defer src="/resources/awesomejump/js/mail-script.js"></script>
  <script defer src="/resources/awesomejump/js/stellar.js"></script>
  <script defer src="/resources/awesomejump/js/price_rangs.js"></script>
  <!-- custom js -->
  <script defer src="/resources/awesomejump/js/custom.js"></script>
</head>

<body>
  
  <jsp:include page="/WEB-INF/templates/awesomejump/header.jsp"/>
  <!-- breadcrumb start-->
  <section class="breadcrumb breadcrumb_bg">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8">
          <div class="breadcrumb_iner">
            <div class="breadcrumb_iner_item">
              <h2>Tracking Order</h2>
              <p>
                Home <span>-</span> Tracking Order
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- breadcrumb start-->

  <!--================login_part Area =================-->
  <section class="login_part padding_top">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-lg-6 col-md-6">
          <div class="login_part_text text-center">
            <div class="login_part_text_iner">
              <h2>New to our Shop?</h2>
              <p>There are advances being made in science and technology everyday, and a good example of this is the</p>
              <a href="#" class="btn_3">Create an Account</a>
            </div>
          </div>
        </div>
        <div class="col-lg-6 col-md-6">
          <div class="login_part_form">
            <div class="login_part_form_iner">
              <h3>
                Welcome Back ! <br> Please Sign in now
              </h3>
              <div class="container">
			  	<h1>추가정보입력</h1>
			  	<form name="frm" method="post">
				    <input type="text" id="email" value="${email}" disabled="disabled">
				    <span id="message1"></span>
				    <input type="hidden" name="reemail" id="reemail"><br>
				    <input type="text" id="nickName" value="${nickName}" onchange="nickNameCheck();">
				    <span id="message2"></span>
				    <input type="hidden" name="renickName" id="renickName"><br>
				    <input type="submit" value="추가정보 입력 완료">
	   			 <s:csrfInput/>
    			</form>
  				</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!--================login_part end =================-->

  <jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp"/>

</body>
</html>
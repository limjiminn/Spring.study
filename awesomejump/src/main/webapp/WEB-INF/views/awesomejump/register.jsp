<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang="ko">

  <head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <s:csrfMetaTags/>
  
  <title>AwesomeJump - join</title>
  
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
  
  <script type="text/javascript">
  function go_save() {
		if (document.frm.email1.value == "") {
			alert("이메일을 입력해주세요");
			document.frm.id.focus();
		} else if (document.frm.reemail.value == "-1") {
			alert("이메일을 다시 입력해주세요")
			document.frm.reemail.focus();
		} else if (document.frm.email2.value == "") {
			alert("이메일을 형식을 선택해주세요");
		} else if (document.frm.password.value == "") {
			alert("비밀번호를 입력해주세요");
			document.frm.pwd.focus();
		} else if (document.frm.password.value != document.frm.confirmPassword.value) {
			alert("비밀번호가 일치하지 않습니다.");
			document.frm.pwd.focus();
		} else if (document.frm.nickName.value == "") {
			alert("닉네임을 입력해주세요");
			document.frm.name.focus();
		} else if (document.frm.renickName.value == "-1") {
			alert("닉네임을 다시 입력해주세요")
			document.frm.renickName.focus();	
		}  else {
			var email1 = document.frm.email1.value;
			var email2 = document.frm.email2.value;
			$('input[name=mem_email]').attr('value',email1+'@'+email2);
			document.frm.action = "/register";
			document.frm.submit();
		}
	}
  	
  function emailCheck(){
	  var email1 = document.frm.email1.value;
	  var email2 = document.frm.email2.value;
	  
	  var email = email1+"@"+email2;
	  //alert(email);
	  
	  var CSRFheader = $("meta[name='_csrf_header']").attr('content');
	  var CSRFtoken = $("meta[name='_csrf']").attr('content');
	  
	   $.ajax({
			type : "POST",
			url : "/email_check",
			dataType : "json",
			data : "email=" + email,
			beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken), 
			success : function(data) {
				
				if (data.message == "1") {
					$("#message").html("이미 등록된 이메일 입니다.").css("color", "red");
					$("#reemail").val("-1");
				
				}else{
					$("#message").html("사용 가능 이메일 입니다.").css("color", "blue");
					$("#reemail").val("1");
				}
			},
			fail : function() {
				alert("시스템에러");
			}
		}); 
  }
  
  function passwordCheck(){
	  var pwd1 = document.frm.password.value;
	  var pwd2 = document.frm.confirmPassword.value;
	  
	  if(pwd1 != pwd2){
		  $("#message2").html("비밀번호가 일치하지 않습니다.").css("color", "red");
	  }else{
		  $("#message2").html("비밀번호가 일치합니다.").css("color", "blue");
	  }
  }
  
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
					$("#message3").html("이미 등록된 닉네임 입니다.").css("color", "red");
					$("#renickName").val("-1");
				
				}else{
					$("#message3").html("사용 가능 닉네임 입니다.").css("color", "blue");
					$("#renickName").val("1");
				}
			},
			fail : function() {
				alert("시스템에러");
			}
		}); 
  }
  </script>
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
                회원가입
              </h3>
              <form class="row contact_form" action="#" method="post" name="frm" novalidate="novalidate">
                <div class="col-md-12 form-group p_star">
					<input type="text" class="form-control" id="email1" placeholder="Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'">
					<input type="hidden" name="reemail" id="reemail"><br>
					<select id="email2" name="email2" onchange="emailCheck();">
						<option value="">선택</option>
						<option value="naver.com">naver.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option value="nate.com">nate.com</option>
						<option value="gmail.com">gmail.com</option>
						<option value="direct">직접입력</option>
					</select>               
				 </div>
				 <span id ="message"></span> 
				<input type="hidden" name=mem_email value="">
                <div class="col-md-12 form-group">
					<input type="password" class="form-control" id="password" name="mem_pw" placeholder="Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'">
    			</div>
        		<div class="col-md-12 form-group">
					<input type="password" class="form-control" id="confirmPassword" onchange="passwordCheck();" name="confirmPassword" placeholder="Confirm Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Confirm Password'">
         			<span id ="message2"></span> 
         		</div>
         		<div class="col-md-12 form-group">
					<input type="text" class="form-control" id="nickName" onchange="nickNameCheck();" name="mem_nickname" placeholder="Nick Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nick Name'">
					<span id ="message3"></span> 
					<input type="hidden" name="renickName" id="renickName"><br>
				</div>
                <div class="col-md-12 form-group">
                  <input type="button" value="회원가입완료" class="button button-register w-100" onclick="go_save();">
                  <a class="lost_pass" href="#">forget password?</a>
                </div>
                <s:csrfInput/>
              </form>
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
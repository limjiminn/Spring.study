<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <title>AwesomeJump - Register</title>
  <style type="text/css">
  .register_logo img{
  	width: 80px;
  	margin: 0 7px;
  	float: left;
  }
  
  .register_logo {
    width: fit-content;
    text-align: center;
    margin: auto;
  }
  
  </style>
  
  <link rel="icon" href="/resources/awesomejump/img/Fevicon.png" type="image/png">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/fontawesome/css/all.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/themify-icons/themify-icons.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/linericon/style.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/owl-carousel/owl.theme.default.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/owl-carousel/owl.carousel.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/nice-select/nice-select.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/nouislider/nouislider.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/style.css">
  
  <script src="/resources/awesomejump/vendors/jquery/jquery-3.2.1.min.js"></script>
  <script src="/resources/awesomejump/vendors/bootstrap/bootstrap.bundle.min.js"></script>
  <script src="/resources/awesomejump/vendors/skrollr.min.js"></script>
  <script src="/resources/awesomejump/vendors/owl-carousel/owl.carousel.min.js"></script>
  <script src="/resources/awesomejump/vendors/nice-select/jquery.nice-select.min.js"></script>
  <script src="/resources/awesomejump/vendors/jquery.ajaxchimp.min.js"></script>
  <script src="/resources/awesomejump/vendors/mail-script.js"></script>
  <script src="/resources/awesomejump/js/main.js"></script>
  
  
  <script type="text/javascript">
  function go_save() {
		if (document.frm.email.value == "") {
			alert("이메일을 입력해주세요");
			document.frm.id.focus();
		} else if (document.frm.email2.value == "") {
			alert("이메일을 형식을 선택해주세요");
		} else if (document.frm.password.value == "") {
			alert("비밀번호를 입력해주세요");
			document.frm.pwd.focus();
		} else if (document.frm.password.value != document.frm.confirmPassword.value) {
			alert("비밀번호가 일치하지 않습니다.");
			document.frm.pwd.focus();
		} else if (document.frm.nickname.value == "") {
			alert("닉네임을 입력해주세요");
			document.frm.name.focus();
		}  else {
			document.frm.action = "/register";
			document.frm.submit();
		}
	}
  	
  
  </script>
</head>
<body>

  <jsp:include page="/WEB-INF/templates/awesomejump/header.jsp"/>
  
  <!-- ================ start banner area ================= -->	
	<section class="blog-banner-area" id="category">
		<div class="container h-100">
			<div class="blog-banner">
				<div class="text-center">
					<h1>Register</h1>
					<nav aria-label="breadcrumb" class="banner-breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">Register</li>
            </ol>
          </nav>
				</div>
			</div>
    </div>
	</section>
	<!-- ================ end banner area ================= -->
  
  <!--================Login Box Area =================-->
	<section class="login_box_area section-margin">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="login_box_img">
						<div class="hover">
							<h4>Already have an account?</h4>
							<p>There are advances being made in science and technology everyday, and a good example of this is the</p>
							<a class="button button-account" href="login">Login Now</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="login_form_inner register_form_inner">
						<h3 style="margin-bottom: 30px;">회원가입</h3>
						<form class="row login_form" action="#/" id="register_form" name="frm">
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="email" name="email" placeholder="Email" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Email'">
							</div>	
							<div>
								<select name="email2">
									<option value="">선택</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="nate.com">nate.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="direct">직접입력</option>
								</select>
							</div>
							<div class="col-md-12 form-group">
								<input type="text" class="form-control" id="password" name="password" placeholder="Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Password'">
              				</div>
             				 <div class="col-md-12 form-group">
								<input type="text" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Confirm Password'">
             				 </div>
             				 <div class="col-md-12 form-group">
								<input type="text" class="form-control" id="nickname" name="nickname" placeholder="Nick Name" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Nick Name'">
							</div>
							<div class="col-md-12 form-group">
								<input type="button" value="회원가입완료" class="button button-register w-100" onclick="go_save();">
							</div>
						</form>
						<br>
						<h3 style="margin-bottom: 20px;">간편로그인</h3>
						<div class="register_logo">
							<img src="/resources/google.png">
							<img src="/resources/kakao.png">
							<img src="/resources/naver.png">
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--================End Login Box Area =================-->

  <jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp"/>

</body>
</html>
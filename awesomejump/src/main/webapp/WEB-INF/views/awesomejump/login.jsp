<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<!doctype html>
<html lang="ko">

  <head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <s:csrfMetaTags/>
  
  <title>AwesomeJump - login</title>
  
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
  <!-- 카카오 소셜 로그인 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script type="text/javascript">
	window.Kakao.init("b4dea7c9a2b1d6a43189e97efe991cd8");
	console.log(Kakao.isInitialized());
	
function kakaoLogin() {
	window.Kakao.Auth.login({
		scope:'profile_nickname, profile_image, account_email, gender, birthday, age_range',
		// 추가 동의 받을 동의 항목 id 목록
		success: function(authObj) {
			console.log(authObj);
			window.Kakao.API.request({
				url:'/v2/user/me',
				success: res => {
					const kakao_account = res.kakao_account;
					console.log(kakao_account);
					
				 	console.log(kakao_account.email);
					console.log(kakao_account.profile['nickname']);
					console.log(kakao_account.gender);
					console.log(kakao_account.birthday);
					console.log(kakao_account.age_range); 
					
					
					var email = kakao_account.email;
					var gender = kakao_account.gender;
					var age_range = kakao_account.age_range;
					var birthday = kakao_account.birthday;
					var nickName = kakao_account.profile['nickname'];
					
					//location.href="/loginAdd?email="+email+"&nickName="+nickName;

					var newForm = document.createElement('form');
					newForm.setAttribute('method','post');
					newForm.setAttribute('action','/loginAdd');
					
					var CSRFtoken = $("meta[name='_csrf']").attr('content');
					
					var input1 = document.createElement('input');
					var input2 = document.createElement('input');
					var input3 = document.createElement('input');
					var input4 = document.createElement('input');
					
					input1.setAttribute("type","hidden");
					input1.setAttribute("name","email");
					input1.setAttribute("value",email);
					input2.setAttribute("type","hidden");
					input2.setAttribute("name","nickName");
					input2.setAttribute("value",nickName);
					input3.setAttribute("type","hidden");
					input3.setAttribute("name","_csrf");
					input3.setAttribute("value",CSRFtoken);
					input4.setAttribute("type","hidden");
					input4.setAttribute("name","social");
					input4.setAttribute("value","kakao");
					
					newForm.appendChild(input1);
					newForm.appendChild(input2);
					newForm.appendChild(input3);
					newForm.appendChild(input4);
					
					document.body.appendChild(newForm);
					
					newForm.submit();
					
				},
				fail: function(error){
					alert(JSON.stringify(error));
				}
			});
		}
	})
}
</script>

<!-- 네이버 소셜 로그인 -->
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>

  
  <div id="naver_id_login" style="display: none;"></div>
  <script type="text/javascript">
  	var naver_id_login = new naver_id_login("CAdAeq_W_hK2H895SPXh", "https://sixsenseproject.shop/naver_callback");
  	var state = naver_id_login.getUniqState();
  	naver_id_login.setButton("white", 2,40);
  	naver_id_login.setDomain("https://sixsenseproject.shop");
  	naver_id_login.setState(state);
  	//naver_id_login.setPopup();
  	naver_id_login.init_naver_id_login();
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
                Welcome Back ! <br> Please Sign in now
              </h3>
              <form class="row contact_form" action="/login" method="POST" novalidate="novalidate">
                <div class="col-md-12 form-group p_star">
                  <input type="text" class="form-control" id="name" name="username" value="" placeholder="user@email.com">
                </div>
                <div class="col-md-12 form-group p_star">
                  <input type="password" class="form-control" id="password" name="password" value="" placeholder="Password">
                </div>
                <div class="col-md-12 form-group">
                  <div class="creat_account d-flex align-items-center">
                    <input type="checkbox" id="f-option" name="selector"> <label for="f-option">Remember me</label>
                  </div>
                  <button type="submit" value="submit" class="btn_3">log in</button>
                  <a class="lost_pass" href="#">forget password?</a>
                </div>
                 <s:csrfInput/>
              </form>
              
               <br>
				<h3 style="margin-bottom: 20px;">간편로그인</h3>
				<div class="register_logo">
					<a href="javascript:kakaoLogin();"><img src="/resources/kakao.png"></a>
					<img src="/resources/naver.png" onclick="document.getElementById('naver_id_login_anchor').click();">
					<a href="/googleLogin"><img src="/resources/google.png" ></a>
					<!-- <div class="g-signin2" data-onsuccess="onSignIn" ></div> -->
   					<!-- <button type="button" onclick="signOut();">Sign Out</button> -->
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
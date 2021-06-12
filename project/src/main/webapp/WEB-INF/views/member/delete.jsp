<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/includes/header.jsp" %>
<style type="text/css">

body {
	  margin: 0;
	  padding: 0;
	 /*  background-color: #17a2b8; */
	  height: 100vh;
	}
	#login .container #login-row #login-column #login-box {
	  margin-top: 20px;
	  max-width: 600px;
	  height: 350px;
	  border: 1px solid #9C9C9C;
	  border-bottom: 100px;
	  background-color: #EAEAEA;
	}
	#login .container #login-row #login-column #login-box #login-form {
	  padding: 30px;
	}
	#login .container #login-row #login-column #login-box #login-form #register-link {
	  margin-top: -85px;
	}
</style>
</head>
<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>회원탈퇴</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>마이페이지</li>
            <li>회원탈퇴</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
    
     <body>
    <div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                         <form id="login-form" name="delete" action="${ctx}/member/delete" method="post"  >
                            <h3 class="text-center text-info">회원 탈퇴</h3>
                            <h5 class="text-center text-info">비밀번호를 입력하시면 탈퇴승인됩니다.</h5>
                            <div class="form-group">
                                <label for="username" class="text-info"></label><br>
                                 <input type="hidden" class="form-control"  name="userid" id="userid" value="${member.userid}"  />
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">PW:</label><br>
                               <input type="password" class="form-control"   name="userpw" id="userpw" placeholder="비밀번호 입력"  />
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info"></label><br>
                                <input type="hidden" class="form-control"   name="username" id="username" value="${member.username}"  />
                            </div>
                            <div class="form-group">
                                <div class="text-center">
                                <button type="submit" id="submit" class="btn btn-info btn-md">탈퇴</button>
	              				 <button type="button" class="btn btn-info btn-md" onclick="location.href='${ctx}'">취소</button>
	              				 </div>
                            </div>
                           
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
    
 <%--    <section id="contact" class="contact">
      <div class="container">   

            <form id="login-form" name="delete" action="${ctx}/member/delete" method="post"  >
           
              <div class="form-group">
	                <input type="text"  name="userid" id="userid" value="${member.userid}"  />
	                <div class="validate"></div>
              </div>         
              <div class="form-group">
	                <input type="password"  name="userpw" id="userpw" placeholder="비밀번호"  />
	                <div class="validate"></div>
              </div>         
              <div class="form-group">
	                <input type="text"  name="username" id="username" value="${member.username}"  />
	                <div class="validate"></div>
              </div>         
	               <div class="text-center"><button type="submit" id="submit" class="btn btn-info btn-md">탈퇴</button></div>
	               <div class="cencle"><button type="button" class="btn btn-info btn-md">취소</button></div>
            </form>
			
          </div>
      
    </section><!-- End Contact Section --> --%>
</main>
<body>

</body>
</html>
<%@ include file="/resources/includes/footer.jsp" %>
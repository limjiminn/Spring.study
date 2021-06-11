<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#submit").on("click", function(){
			
		if($("#userpw").val()!=$("#userpw2").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#userpw").focus();
			return false;
		}
		});
	});
</script>
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
	  height: 600px;
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
<%@ include file="/resources/includes/header.jsp" %>
</head>
<main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>정보수정</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>마이페이지</li>
            <li>정보수정</li>
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
                        <form id="login-form" name="Update" action="${ctx}/member/update" method="post"  >
                            <h3 class="text-center text-info">회원수정</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">아이디</label><br>
                                <input type="text" class="form-control" name="userid" id="userid" value="${member.userid}" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">비밀번호</label><br>
                               <input type="password" class="form-control"  name="userpw" id="userpw" placeholder="비밀번호"  />
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">비밀번호 재입력</label><br>
                                 <input type="password" class="form-control" name="userpw2" id="userpw2" placeholder="비밀번호 재입력" />
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">이름</label><br>
                                <input type="text" class="form-control"  name="username" id="username" value="${member.username}" readonly="readonly"/>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">이메일</label><br>
                                 <input type="text" class="form-control"  name="email" id="email" value="${member.email}" />
                            </div>
                            <div class="text-center">
                                <button type="submit" id="submit" class="btn btn-info btn-md">저장</button>
                                <button class="btn btn-info btn-md" type="button" onclick="location.href='${ctx}'">취소</button>
                            </div>
                          
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
    
	<%-- <section id="contact" class="contact">
      <div class="container">   

            <form id="login-form" name="Update" action="${ctx}/member/update" method="post"  >
           
              <div class="form-group">
                <input type="text"  name="userid" id="userid" value="${member.userid}" readonly="readonly"/>
                <div class="validate"></div>
                <span id="message"></span>
              </div>
              <div class="form-group">
                <input type="password"  name="userpw" id="userpw" placeholder="비밀번호"  />
                <div class="validate"></div>
              </div>
              <div class="form-group">
                <input type="password" name="userpw2" id="userpw2" placeholder="비밀번호 재입력" />
                <div class="validate"></div>
              </div>
              <div class="form-group">
                <input type="text"  name="username" id="username" value="${member.userid}" readonly="readonly"/>
                <div class="validate"></div>
              </div>
              <div class="form-group">
                <input type="text"  name="email" id="email" value="${member.email}" />
                <div class="validate"></div>
              </div>
              
              
              <div class="text-center"><button type="submit" id="submit">저장</button></div>
              <div class="cencle"><button type="button">취소</button></div>
            </form>
			
          </div>
      
    </section><!-- End Contact Section --> --%>
</main>
</html>
<%@ include file="/resources/includes/footer.jsp" %>
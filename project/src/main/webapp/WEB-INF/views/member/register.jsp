<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
 <%@ include file="/resources/includes/header.jsp" %>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
   <script type="text/javascript">
		$(document).ready(function(){
			$("#cencle").on("click", function(){
				alert("확인");	
				location.href ="${ctx}/";					    
			});
			$("#submit").on("click", function(){
				if($("#userid").val()==""){
					alert("아이디를 입력해주세요.");
					$("#userid").focus();
					return false;
				}
				if($("#userpw").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#userpw").focus();
					return false;
				}
				if($("#userpw").val()!=$("#userpw2").val()){
					alert("비밀번호가 일치하지 않습니다.");
					$("#userpw").focus();
					return false;
				}
				if($("#username").val()==""){
					alert("성명을 입력해주세요.");
					$("#username").focus();
					return false;
				}
				if($("#email").val()==""){
					alert("이메일을 입력해주세요.");
					$("#email").focus();
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
	
</head>
 <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>회원가입</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>회원가입</li>
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
                        <form id="login-form" name="Register" action="${ctx}/member/register" method="post">
                            <h3 class="text-center text-info">회원가입</h3>
                            <div class="form-group">
                              <label for="username" class="text-info"></label><br>
                                <input type="text" name="userid" id="userid" class="form-control"placeholder="아이디">
                                <span id="message"></span>
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info"></label><br>
                                <input type="password" name="userpw" id="userpw" class="form-control" placeholder="비밀번호">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info"></label><br>
                                <input type="password" name="userpw2" id="userpw2" class="form-control" placeholder="비밀번호 확인">
                            </div>
                            <div class="alert alert-success" id="alert-success">비밀번호가 일치합니다.</div>
                            <div class="alert alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</div>
                            <div class="form-group">
                               <label for="password" class="text-info"></label><br>
                                <input type="text" name="username" id="username" class="form-control" placeholder="이름">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info"></label><br>
                                <input type="text" name="email" id="email" class="form-control" placeholder="이메일">
                            </div>
                          	<div class="form-group">
                                <input type="submit" id="submit" class="btn btn-info btn-md" value="확인">
                                <input type="button" id="cencle" class="btn btn-info btn-md" value="취소">                                
                            </div>
                            
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
    <!-- ======= Contact Section ======= -->
<%--     <section id="contact" class="contact">
      <div class="container">   

            <form name="Register" action="${ctx}/member/register" method="post"  >
           
              <div class="form-group">
                <input type="text"  name="userid" id="userid" placeholder="아이디"/>
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
                <input type="text"  name="username" id="username" placeholder="이름" />
                <div class="validate"></div>
              </div>
              <div class="form-group">
                <input type="text"  name="email" id="email" placeholder="이메일" />
                <div class="validate"></div>
              </div>
              
              
              <div class="text-center"><button type="submit" id="submit">회원가입</button></div>
              <div class="cencle"><button type="button">취소</button></div>
            </form>
			
          </div>
      
    </section><!-- End Contact Section --> --%>
    
    
<script >
	 $(document).ready(function(){
		$("#userid").blur(function() { 
	 		var userid =$("#userid").val();
		$.ajax({
				url: "${ctx}/member/idchk?userid="+userid,
				type: "POST",
				success:function(data){
					if (data == 1) {
						$("#message").html("사용 불가능한 아이디 입니다.").css("color", "red");
					}else {
						$("#message").html("사용 가능한 아이디 입니다.").css("color","green");
					}
				},
				fail: function() {
					alert("시스템 에러");
				}
			
			});
	 
 		});
	 });
	 </script>
	 <script>
	$(document).ready(function(){
		$(function() { 
			$("#alert-success").hide();
			$("#alert-danger").hide();
			$("#userpw2").blur(function() {
				var userpw =$("#userpw").val();
				var userpw2 =$("#userpw2").val();
				if(userpw !="" || userpw2 !=""){
					if(userpw == userpw2){
						$("#alert-success").show();
						$("#alert-danger").hide();
						
						$("#submit").removeAttr("disabled");
					}else{
						$("#alert-success").hide();
						$("#alert-danger").show();
						$("#submit").Attr("disabled","disabled");
					}
				}	
			});
			
				
			
		});
	}); 
	</script>

  </main><!-- End #main -->

</html>
 <%@ include file="/resources/includes/footer.jsp" %>
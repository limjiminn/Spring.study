<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<%@ include file="/resources/includes/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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
	  height: 320px;
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

<script type="text/javascript">
	/* $(document).ready(function(e){
		$("#login").click(function(){
			//입력값 체크
			if ($.trim($("#userid").val()) == "") {
				alert("아이디를 입력해 주세요");
				$("#userid").focus();
				return;
			}
		});
	}); 
	function login() {
		$.ajax({
			url:"member/login",
			type: "POST",
			dataType: "json", 
			data:{
				id:$("#userid").val(),
				pw:$("#userpw").val()
			},
			success: function(data){
				alert(data.Msg);
			},
			error: function(){
				alert("err");
			}
		});
		
	}*/
</script>
  <main id="main">

    <!-- ======= Breadcrumbs ======= -->
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>로그인</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>로그인</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->  
  </main><!-- End #main -->

 <body>
    <div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="${ctx}/member/loginPost" method="post">
                            <h3 class="text-center text-info">로그인</h3>
                            <div class="form-group">
                                <label for="username" class="text-info">ID:</label><br>
                                <input type="text" name="userid" id="userid" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">PW:</label><br>
                                <input type="text" name="userpw" id="userpw" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                                <input type="submit" name="submit" class="btn btn-info btn-md" value="확인">
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="${ctx}/member/register" class="text-info">회원가입</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>



 <%@ include file="/resources/includes/footer.jsp" %>

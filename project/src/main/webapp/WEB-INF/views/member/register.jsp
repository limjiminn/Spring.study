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
   <script type="text/javascript">
		$(document).ready(function(){
			
			// 취소
			$(".cencle").on("click", function(){
				alert("확인");	
				location.href ="${ctx}/home";					    
			})
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
				if($("#userpw").val()==$("#userpw2").val()){
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

    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact">
      <div class="container">   

            <form action="${ctx}/member/register" method="post"  >
           
              <div class="form-group">
                <input type="text"  name="userid" id="userid" placeholder="아이디"   />
                <div class="validate"></div>
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
      
    </section><!-- End Contact Section -->

  </main><!-- End #main -->

</html>
 <%@ include file="/resources/includes/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/resources/includes/header.jsp" %>
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
    <section id="contact" class="contact">
      <div class="container">   

            <form name="delete" action="${ctx}/member/delete" method="post"  >
           
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
	               <div class="text-center"><button type="submit" id="submit">탈퇴</button></div>
	               <div class="cencle"><button type="button">취소</button></div>
            </form>
			
          </div>
      
    </section><!-- End Contact Section -->
</main>
<body>

</body>
</html>
<%@ include file="/resources/includes/footer.jsp" %>
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
	<section id="contact" class="contact">
      <div class="container">   

            <form name="Update" action="${ctx}/member/update" method="post"  >
           
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
      
    </section><!-- End Contact Section -->
</main>
</html>
<%@ include file="/resources/includes/footer.jsp" %>
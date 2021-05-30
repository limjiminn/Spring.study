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

    <!-- ======= Contact Section ======= -->
    <section id="contact" class="contact">
      <div class="container">   
		<form action="${ctx}/member/login" method="post">
			<%-- <c:if test="${member == null}"> --%>
				<div class="col-lg-4">
					<label for="userid"></label>
					<input type="text" id="userid" name="userid">
				</div>
				<div class="col-lg-4">
					<label for="userpw"></label>
					<input type="password" id="userpw" name="userpw">
				</div>
				<div class="col-lg-4">
					<button type="submit">로그인</button>
					<button type="button"><a href="${ctx}/member/register">회원가입</a></button>
				</div>
			<%-- </c:if> --%>
			<%-- <c:if test="${member != null }">
				<div>
					<p>${member.username}님 환영합니다.</p>
					<button id="logoutBtn" type="button">로그아웃</button>
				</div>
			</c:if>
			<c:if test="${msg == false}">
				<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
			</c:if> --%>
		</form>	
      </div>  
    </section><!-- End Contact Section -->
          
  </main><!-- End #main -->




 <%@ include file="/resources/includes/footer.jsp" %>

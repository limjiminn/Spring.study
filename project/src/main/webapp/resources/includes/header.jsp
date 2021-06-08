<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>beer shop</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${ctx}/resources/assets/img/favicon.png" rel="icon">
  <link href="${ctx}/resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${ctx}/resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${ctx}/resources/assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: Sailor - v2.3.1
  * Template URL: https://bootstrapmade.com/sailor-free-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="fixed-top ">
    <div class="container d-flex align-items-center">

      <h1 class="logo"><a href="/shop">BEER</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <%-- <a href="index.html" class="logo"><img src="${ctx}/resources/assets/img/slide/로고.png" alt="" class="img-fluid"></a> --%>

      <nav class="nav-menu d-none d-lg-block">
        <ul>
          <li class="drop-down"><a href="#">About</a>
            <ul>
              <li><a href="about.html">About Us</a></li>
              <li><a href="team.html">Team</a></li>
              <li><a href="testimonials.html">Testimonials</a></li>

              <li class="drop-down"><a href="#">Deep Drop Down</a>
                <ul>
                  <li><a href="#">Deep Drop Down 1</a></li>
                  <li><a href="#">Deep Drop Down 2</a></li>
                  <li><a href="#">Deep Drop Down 3</a></li>
                  <li><a href="#">Deep Drop Down 4</a></li>
                  <li><a href="#">Deep Drop Down 5</a></li>
                </ul>
              </li>
            </ul>
          </li>
          
          <li><a href="${ctx}/product/productlist?pcategory=lager">라거</a></li>
          <li><a href="${ctx}/product/productlist?pcategory=ale">에일</a></li>
          <li><a href="${ctx}/product/productlist?pcategory=radler">라들러</a></li>
          <li><a href="${ctx}/product/productlist?pcategory=dark">흑맥주</a></li>
          <li><a href="${ctx}/board/list">공지사항</a></li>
		 
	 	   
	 		<c:if test="${empty sessionScope.member}">	
	 			<li><a href="${ctx}/member/login">로그인</a></li>
	 			<li><a href="${ctx}/member/register">회원가입</a></li>
	 		</c:if>
	 		<c:if test="${not empty sessionScope.member}">
	 			<li style="color: blue">${sessionScope.member.username}님 반갑습니다.</li>
	 			<li><a href="${ctx}/member/logout">로그아웃</a></li>
	 			<li><a href="${ctx}/member/mypage">마이페이지</a></li>
	 		</c:if>	 				 	
	 	   <li><a href="${ctx}/cart/list">장바구니</a></li>
        </ul>

      </nav><!-- .nav-menu --> 
      <div class="get-started-btn ml-auto">
      </div>
      <!-- 	<a href="index.html" class="get-started-btn ml-auto">Get Started</a> -->
    </div>
  </header><!-- End Header -->

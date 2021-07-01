<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <title>AwesomeJump - Blog</title>
  
  <link rel="icon" href="/resources/awesomejump/img/Fevicon.png" type="image/png">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/bootstrap/bootstrap.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/fontawesome/css/all.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/themify-icons/themify-icons.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/linericon/style.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/owl-carousel/owl.theme.default.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/vendors/owl-carousel/owl.carousel.min.css">
  <link rel="stylesheet" href="/resources/awesomejump/css/style.css">
  
  <script defer src="/resources/awesomejump/vendors/jquery/jquery-3.2.1.min.js"></script>
  <script defer src="/resources/awesomejump/vendors/bootstrap/bootstrap.bundle.min.js"></script>
  <script defer src="/resources/awesomejump/vendors/skrollr.min.js"></script>
  <script defer src="/resources/awesomejump/vendors/owl-carousel/owl.carousel.min.js"></script>
  <script defer src="/resources/awesomejump/vendors/nice-select/jquery.nice-select.min.js"></script>
  <script defer src="/resources/awesomejump/vendors/jquery.ajaxchimp.min.js"></script>
  <script defer src="/resources/awesomejump/vendors/mail-script.js"></script>
  <script defer src="/resources/awesomejump/js/main.js"></script>
</head>

<style>
  form {
    min-height: 1000px;
  }
</style>

<body>
  
  <jsp:include page="/WEB-INF/templates/awesomejump/header.jsp"/>
  
  <c:if test="${not empty result}">
    <p class="m-4 lead">Uploaded <c:out value="${result}"/></p>
    <img src="/upload/${result}">
  </c:if>
  
  <form action="/testUpload" method="POST" enctype="multipart/form-data">
    <input class="m-4" type="file" name="file">
    <input type="submit">
    <s:csrfInput/>
  </form>
  
  <jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp"/>
  
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
  <script defer src="https://code.jquery.com/jquery-3.5.1.slim.min.js" ></script>
  <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"></script>
  
  <title>Login</title>
  
  <style>
    .form-control {
      width: 320px;
    }
  </style>
  
</head>
<body>
  <h1>로그인 페이지 입니다.</h1>
  <form action="/login" method="post">
    아이디    : <input type="text" class="form-control" name="username">
    비밀번호  : <input type="password" class="form-control" name="password">
    <button class="btn btn-primary my-3">로그인</button>
    <s:csrfInput/>
  </form>
  <a href="/"><button class="btn btn-primary my-3">Home</button></a>
</body>
</html>

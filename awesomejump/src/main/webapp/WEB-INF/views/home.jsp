<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>Home</title>
</head>
<body>
  <h1>Hello world!</h1>

  <P>The time on the server is ${serverTime}.</P>
  <p>mybatis 테스트 : ${getTime}</p>
  
  <a href="/user"><button>user</button></a>
  <a href="/admin"><button>admin</button></a>
  <a href="/login"><button>login</button></a>
  <a href="/logout"><button>logout</button></a>
  
  <s:authorize access="hasRole('ROLE_USER')">
    <s:authentication var="principal" property="principal"/>
    <p class="lead">닉네임 : ${principal.mem_nickname}</p>
  </s:authorize>
  
</body>
</html>

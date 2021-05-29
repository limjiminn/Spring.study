<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
 <%@ include file="/resources/includes/header.jsp" %>
<form name="login" method="post" action="/member/login">
	<c:if test="${member == null}">
		<div>
			<label for="userid"></label>
			<input type="text" id="userid" name="userid">
		</div>
		<div>
			<label for="userpw"></label>
			<input type="password" id="userpw" name="userpw">
		</div>
		<div>
			<button type="submit">로그인</button>
			<button type="button">회원가입</button>
		</div>
	</c:if>
	<c:if test="${member != null }">
		<div>
			<p>${member.username}님 환영합니다.</p>
			<button id="logoutBtn" type="button">로그아웃</button>
		</div>
	</c:if>
	<c:if test="${msg == false}">
		<p style="color: red;">로그인 실패! 아이디와 비밀번호 확인해주세요.</p>
	</c:if>
</form>


 <%@ include file="/resources/includes/footer.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="/member/register" method="post">
		<input type="text" id="userid" name="userid" placeholder="아이디"><br>
		<input type="password" id="userpw" name="pwd" placeholder="비밀번호"><br>
		<input type="password" id="userid" name="pwdCheck" placeholder="비밀번호 재입력"><br>
		<input type="text" id="username" name="username" placeholder="이름"><br>
		<input type="text" id="email" name="email" placeholder="이메일"><br>
	</form>
</body>
</html>
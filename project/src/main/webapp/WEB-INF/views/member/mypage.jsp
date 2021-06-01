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
          <h2>마이페이지</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>마이페이지</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
	<ul>
		<li><a href="${ctx}/member/update">정보수정</a></li>
		<li><a href="${ctx}/member/delete">회원탈퇴</a></li>
	</ul>
 </main><!-- End #main -->
</html>
<%@ include file="/resources/includes/footer.jsp" %>
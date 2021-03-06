<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>AwesomeJump - myQna</title>

<link rel="stylesheet" href="/resources/awesomejump/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/awesomejump/css/animate.css">
<link rel="stylesheet" href="/resources/awesomejump/css/owl.carousel.min.css">
<link rel="stylesheet" href="/resources/awesomejump/css/magnific-popup.css">
<link rel="stylesheet" href="/resources/awesomejump/css/slick.css">
<!-- style CSS -->
<link rel="stylesheet" href="/resources/awesomejump/css/style.css">
<link rel="stylesheet" href="/resources/awesomejump/css/custom/myQna.css">

<script defer src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script defer src="/resources/awesomejump/js/popper.min.js"></script>
<script defer src="/resources/awesomejump/js/bootstrap.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.magnific-popup.js"></script>
<script defer src="/resources/awesomejump/js/owl.carousel.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.counterup.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.nice-select.min.js"></script>
<script defer src="/resources/awesomejump/js/slick.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.ajaxchimp.min.js"></script>
<!-- custom js -->
<script defer src="/resources/awesomejump/js/custom.js"></script>

<!-- Swiper -->
<link rel="stylesheet" href="/resources/awesomejump/css/swiper.min.css">
<script src="/resources/awesomejump/js/swiper.min.js"></script>
</head>

<body class="myQna">

  <!-- Header -->
  <jsp:include page="/WEB-INF/templates/awesomejump/header.jsp" />
  <!-- 회원 정보 페이지 메뉴바(MembersNavbar) -->
  <jsp:include page="/WEB-INF/templates/awesomejump/membersNavbar.jsp" />

  <section class="myQnaList">
    <div class="container">
      <div class="divTable offset-xl-2 col-xl-8 mt-5">
        <div class="divTableHead">
          <div class="divTableRow">
            <div class="divTableCell"><h4>문의 종류</h4></div>
            <div class="divTableCell"><h4>문의 제목</h4></div>
            <div class="divTableCell"><h4>등록일</h4></div>
            <div class="divTableCell"><h4>답변 상태</h4></div>
          </div>
        </div>
        <div class="divTableBody">
          <c:forEach var="myQna" items="${myQnaList}">
            <div class="divTableRow">
              <div class="divTableCell cell1"><span><c:out value="${myQna.qnaCategory}"/></span></div>
              <div class="divTableCell cell2"><span><c:out value="${myQna.qnaTitle}"/></span></div>
              <div class="cell3 mt-3">
                <div class="divTableCell mobileCell"><span><fmt:formatDate value="${myQna.qnaRegisterDate}" pattern="yyyy-MM-dd"/></span></div>
                <div class="divTableCell mobileCell"><span><c:out value="${myQna.qnaAnswerStatus eq 'n' ? '답변 대기' : '답변 완료'}"/></span></div>
              </div>
              <div class="divTableCell pcCell"><span><fmt:formatDate value="${myQna.qnaRegisterDate}" pattern="yyyy-MM-dd"/></span></div>
              <div class="divTableCell pcCell"><span><c:out value="${myQna.qnaAnswerStatus eq 'n' ? '답변 대기' : '답변 완료'}"/></span></div>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </section>
	
  <!-- Footer -->
  <jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp" />

</body>
</html>
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
 </main><!-- End #main -->
</html>
 <!-- ======= Pricing Section ======= -->
    <section id="pricing" class="pricing">
      <div class="container">

        <div class="row">

          <div class="col-lg-3 col-md-6">
            <div class="box">
              <ul>
                <li> </li>
                <li> </li>
                <li> </li>
              <a href="${ctx}/member/update" ><h5>회원 수정</h5></a>
                <li> </li>
                <li> </li>
                <li> </li>            
              </ul>
              
            </div>
          </div>
          <div class="col-lg-3 col-md-6 mt-4 mt-lg-0">
            <div class="box">
              <ul>
                <li> </li>
                <li> </li>
                <li> </li>
              <a href="${ctx}/member/delete"><h5>회원 탈퇴</h5></a>
                <li> </li>
                <li> </li>
                <li> </li>  
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section><!-- End Pricing Section -->
<%@ include file="/resources/includes/footer.jsp" %>
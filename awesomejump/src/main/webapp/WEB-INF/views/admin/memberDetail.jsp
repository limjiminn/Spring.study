<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
  <title>Product Page</title>
  
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">
  
  <script defer src="/resources/admin/vendor/jquery/jquery.min.js"></script>
  <script defer src="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script defer src="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
  <!-- Custom scripts -->
  <script defer src="/resources/admin/js/sb-admin-2.min.js"></script>
<style type="text/css">
	h1 {
		font-family: "NANUM";
		font-size: 70px;
		font-weight: bold;
		margin-bottom: 40px;
	}
	
	.mid {
		margin: 70px;
	}
	
	img{
	width: 300px;
    height: 300px;
    object-fit: cover;
    margin: 60px 120px 20px 120px;
	}
	
	p {
		font-family: "NANUM";
		font-size: 20px;
		font-weight: bold;
	
	}
	
	.menu {
		margin-top: 60px;
		
	}
	
	.menu input{
		width: 200px;
	}
</style>
</head>

<body id="page-top">
  <!-- Page Wrapper -->
  <div id="wrapper">

    <%-- <jsp:include page="/WEB-INF/templates/admin/sidebar.jsp"/> --%>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
      <!-- Main Content -->
      <div id="content">

        <jsp:include page="/WEB-INF/templates/admin/topbar.jsp"/>

        <!-- Begin Page Content -->
        <div class="container-fluid">
          <!-- Page Heading -->
		<div class="mid">
		<h1>?????? ????????????</h1>
			<div class="profile1" style="float: left; margin-left: 100px;">
				<c:choose>
	             <c:when test="${member.mem_profile_img eq 'profile.jpg'}">
	               <img class="rounded-circle ml-2" src="/resources/awesomejump/img/profile.jpg" width="64px" height="64px"/>
	             </c:when>
	             <c:otherwise>
	               <img class="rounded-circle ml-2" src="/upload${member.mem_profile_img}" width="64px" height="64px"/>
	             </c:otherwise>
	           </c:choose>
	           <p style="margin-left: 40px;font-size: 40px;">?????? ????????? ??????</p>
        	 </div>  
           <div class="profile2" style="float: left; margin: 60px;">
	          <p>?????? ?????? : ${member.mem_no}</p>
	          <p>?????? ????????? : ${member.mem_nickname}</p>
	          <p>?????? ????????? : ${member.mem_email}</p>
	          <p>????????? : <fmt:formatDate value="${member.mem_date}" pattern="yyyy-MM-dd HH:ss"/></p>
	          <p>?????? ????????? : <fmt:formatDate value="${member.mem_last_login}" pattern="yyyy-MM-dd HH:ss"/></p>
	          <p>?????? ????????? ?????? : ${member.mem_enabled_yn}</p>
	          <p>????????? ?????? ?????? : ${member.mem_email_yn}</p>
	          <p>?????? ?????? : 
	          <c:choose>
	          	<c:when test="${member.mem_register_path == 'DEFAULT'}">
	          		????????????
	          	</c:when>
	          	<c:when test="${member.mem_register_path == 'kakao'}">
	          		???????????? - ?????????
	          	</c:when>
	          	<c:when test="${member.mem_register_path == 'google'}">
	          		???????????? - ??????
	          	</c:when>
	          	<c:when test="${member.mem_register_path == 'naver'}">
	          		???????????? - ?????????
	          	</c:when>
	          </c:choose>
	          </p>
	          <p>?????? ????????? : ${member.mem_point}</p>
          </div>
          <div class="menu">
         	<input type="button" value="?????? ?????? ??????" class="btn btn-danger"> 
         	<input type="button" value="??????????????? ??????" class="btn btn-danger">
         	<input type="button" value="????????????" class="btn btn-success" onclick="history.go(-1);">
 
          </div>
        </div>
        <!-- /.container-fluid -->
        </div>
      </div>
      <!-- End of Main Content -->



      <jsp:include page="/WEB-INF/templates/admin/footer.jsp"/>

    </div>
    <!-- End of Content Wrapper -->
  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top"> 
    <i class="fas fa-angle-up"></i>
  </a>

  <jsp:include page="/WEB-INF/templates/admin/logoutModal.jsp"/>

</body>
</html>
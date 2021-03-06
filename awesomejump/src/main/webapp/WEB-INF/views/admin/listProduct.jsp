<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <s:csrfMetaTags/>
  
  
  <title>상품 목록</title>
  
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">
  <%-- <script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script> --%>
  <script type="text/javascript" src="/resources/admin/vendor/jquery/jquery.min.js"></script>
  <script defer src="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script defer src="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
  <!-- Custom scripts -->
  <script defer src="/resources/admin/js/sb-admin-2.min.js"></script>


	<style>
	  table {
	    width: 100%;
	    border-top: 1px solid #444444;
	    border-collapse: collapse;
	  }
	  th, td {
	    border-bottom: 1px solid #444444;
	    padding: 10px;
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

				<nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="/admin">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">상품 관리</li>
            </ol>
          </nav>
          <!-- Page Heading -->
          <div class="d-flex align-items-center">
            <h1 class="h3 mb-2 text-gray-800 ml-1 mt-4">상품 목록</h1>
          </div>
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th>상품코드</th>
											<th>상품명</th>
											<th>상품금액</th>
											<th>상품설명</th>
											<th style="margin-left: 10px; margin-right: 10px;">상품재고</th>
											<th style="margin-left: 10px; margin-right: 10px;">상품재질</th>
											<th style="margin-left: 10px; margin-right: 10px;">상품포인트</th>
											<th style="margin-left: 10px; margin-right: 10px;">1차분류</th>
											<th style="margin-left: 10px; margin-right: 10px;">2차분류</th>
											<th style="margin-left: 10px; margin-right: 10px;">3차분류</th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${list}" var="list">
										<tr>
											<td>${list.product_no }</td>
											<td>
											<a href="/admin/detailProduct?product_no=${list.product_no }">${list.product_name }</a>
											</td>
											<td><fmt:formatNumber value="${list.product_price }" type="number"/>원&nbsp;</td>
											<td>${list.product_content }&nbsp;</td>
											<td>${list.product_stock }&nbsp;</td>
											<td>${list.product_texture }&nbsp;</td>
											<td>${list.product_point }P&nbsp;</td>
											<td>${list.category_main }&nbsp;</td>
											<td>${list.category_mid }&nbsp;</td>
											<td>${list.category_sub }</td>
										</tr>
									</c:forEach>
                  </tbody>
                </table>
                <button class="btn btn-primary" type="button" onclick="history.go(-1)">뒤로가기</button>
              </div>

       <%--  <!-- Begin Page Content -->
        <div class="container-fluid">
	          <!-- Page Heading -->
	          <h1 class="h3 mb-4 text-gray-800" align="center">상품목록</h1>
						<button class="btn btn-primary" type="button" onclick="history.go(-1)">뒤로가기</button>
					<div>
						<table style="border: 1px;	">
							<thead align="center" >
								<tr>
									<th>상품코드</th>
									<th>상품명</th>
									<th>상품금액</th>
									<th>상품설명</th>
									<th style="margin-left: 10px; margin-right: 10px;">상품재고</th>
									<th style="margin-left: 10px; margin-right: 10px;">상품재질</th>
									<th style="margin-left: 10px; margin-right: 10px;">상품포인트</th>
									<th style="margin-left: 10px; margin-right: 10px;">1차분류</th>
									<th style="margin-left: 10px; margin-right: 10px;">2차분류</th>
									<th style="margin-left: 10px; margin-right: 10px;">3차분류</th>
								</tr>
							</thead>
							<tbody align="center">
								<c:forEach items="${list}" var="list">
									<tr>
										<td>${list.product_no }</td>
										<td>
										<a href="/admin/detailProduct?product_no=${list.product_no }">${list.product_name }</a>
										</td>
										<td><fmt:formatNumber value="${list.product_price }" type="number"/>원&nbsp;</td>
										<td>${list.product_content }&nbsp;</td>
										<td>${list.product_stock }&nbsp;</td>
										<td>${list.product_texture }&nbsp;</td>
										<td>${list.product_point }P&nbsp;</td>
										<td>${list.category_main }&nbsp;</td>
										<td>${list.category_mid }&nbsp;</td>
										<td>${list.category_sub }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<br>
						<br>
						<button class="btn btn-primary" type="button" onclick="history.go(-1)">뒤로가기</button>
		   		</div>
        </div>
        <!-- /.container-fluid -->
      </div>
      <!-- End of Main Content --> --%>


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
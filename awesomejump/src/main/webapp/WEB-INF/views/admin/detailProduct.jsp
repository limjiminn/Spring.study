<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <s:csrfMetaTags/>
  
  
  <title>상품 상세</title>
  
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

	<style type="text/css">
		#result_card img{
		max-width: 100%;
	    height: auto;
	    display: block;
	    padding: 5px;
	    margin-top: 10px;
	    margin: auto;	
	}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
			let product_no = '<c:out value="${detail.product_no}"/>';
			let uploadResult = $("#uploadResult");
			
			$.getJSON("/admin/getImageList", {product_no : product_no}, function(arr){
				
				if(arr.length === 0) {
					
					let str = "";
					str += "<div id='result_card'>";
					str += "<img src='/resources/admin/img/noImage.png'>";
					str += "</div>";
					
					uploadResult.html(str);
					
					return;
					
				}
				
				let str = "";
				let obj = arr;
				
				for(let i = 0; i < obj.length; i++){
					
					let fileCallPath = encodeURIComponent(obj[i].pro_image_path.replace(/\\/g, '/') + "/" + obj[i].pro_image_uuid + "_" + obj[i].pro_image_name);
					str += "<div id='result_card'";
					str += "data-path='" + obj[i].pro_image_path + "' data-uuid='" + obj[i].pro_image_uuid + "' data-filename='" + obj[i].pro_image_name + "'";
					str += ">";
					str += "<img src='/display?fileName=" + fileCallPath + "'>";
					str += "</div>";
					
					uploadResult.html(str);
				}
				
			});
			
			
		});
	</script>
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
          <h1 class="h3 mb-4 text-gray-800">상품 상세</h1>
      <!-- <form method="post" role="from" autocomplete="off"> -->
  			<s:csrfInput/>
  			
  			<table>
  				<tr>
  					<th><label>상품 이미지</label></th>
  					<td>
	  					<div id="uploadResult">
		  				
		  				</div>
	  				</td>
  				</tr>
  				<tr>
  					<th><label for="product_name">상품명</label></th>
  					<td><span>${detail.product_name }</span></td>
  				</tr>
  				<tr>
  					<th><label for="product_price">상품가격</label></th>
  					<td><span><fmt:formatNumber value="${detail.product_price }" pattern="###,###,###"/></span>원</td>
  				</tr>
  				<tr>
  					<th><label for="product_content">상품설명</label></th>
  					<td><span>${detail.product_content }</span></td>
  				</tr>
  				<tr>
  					<th><label for="product_stock">상품재고</label></th>
  					<td><span>${detail.product_stock }</span>개</td>
  				</tr>
  				<tr>
  					<th><label for="product_texture">상품재질</label></th>
  					<td><span>${detail.product_texture }</span></td>
  				</tr>
  				<tr>
	  					<th><label for="product_option_color">상품색상</label></th>
	  					<th><label for="product_option_size">상품크기</label></th>
  				</tr>
  					<c:forEach items="${option }" var="option">
  					<tr>
	  					<td><span>[${option.product_option_color }]</span></td>
	  					<td><span>[${option.product_option_size }]</span></td>
	  				</tr>
  					</c:forEach>
  				<tr>
  					<th><label for="product_point">포인트</label></th>
  					<td><span>${detail.product_point }</span>P</td>
  				</tr>
  				<tr>
  					<th><label>1차 분류</label></th>
  					<td><span class="category1">${detail.category_main }</span></td>
  				</tr>
  				<tr>
  					<th><label>2차 분류</label></th>
  					<td><span class="category1">${detail.category_mid }</span></td>
  				</tr>
  				<tr>
  					<th><label>3차 분류</label></th>
  					<td><span class="category1">${detail.category_sub }</span></td>
  				</tr>
  			</table>
		  	<br>
	  		<button type="button" onclick="location.href='/admin/modifyProduct?product_no=${detail.product_no}'" class="btn btn-primary">수정</button>
	  		<button onclick="history.go(-1)" class="btn btn-primary">뒤로가기</button>
	   	<!-- </form> -->
	   	
        </div>
        <!-- /.container-fluid -->
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<%@ include file="/resources/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
 <main id="main">

    <!-- ======= Breadcrumbs ======= -->
   
    <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">	
	        <div class="d-flex justify-content-between align-items-center">
	          <h2>상품 분류</h2>
	          <ol>
	            <li><a href="index.html">Home</a></li>
	            <li>상품 분류</li>
	          </ol>
	        </div>
      </div>
    </section><!-- End Breadcrumbs -->


    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">
        <div class="row portfolio-container">
        
			<c:forEach items="${list}" var="list">
          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <div class="portfolio-wrap">
            <img src="${ctx}/resources/assets/img/lager/${list.pimg}" class="img-fluid" >
              <div class="portfolio-info">
                <a href="${ctx}/product/productdetail?pid=${list.pid}"><h4>${list.pname}</h4></a>
                <h4>${list.pprice}</h4>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/lager/${list.pimg}" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="bx bx-plus"></i></a>
                  <a href="${ctx}/product/productdetail?pid=${list.pid}" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>
          	</c:forEach>

      
        </div>

      </div>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->
 
<%@ include file="/resources/includes/footer.jsp" %>
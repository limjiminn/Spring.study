<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />    
<%@ include file="/resources/includes/header.jsp" %>
 <main id="main">
	
	<section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>${detail.pcategory}</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>${detail.pcategory}</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
	
    <!-- ======= Portfolio Details Section ======= -->
    <section id="portfolio-details" class="portfolio-details">
      <div class="container">

        <div class="row">
          <div class="col-lg-8">
            <h2 class="portfolio-title">This is an example of portfolio detail</h2>
            <div class="owl-carousel portfolio-details-carousel">
              <img src="${ctx}/resources/assets/img/lager/${detail.pimg}" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-4 portfolio-info">
            <h2>${detail.pname}</h2>
			
            <ul>
              <li><strong>Category</strong>: ${detail.pcategory}</li>
              <li><strong>Country</strong>: ${detail.pcompany}</li>
              <li>
              	<strong>구입 수량 : </strong>
              	<input type="number" min="1" max="${detail.pstock}" value="1"/>
         	  </li>
            <p>${detail.pdescription}</p>
            <hr>
              <li><strong>Price</strong>: <h2>${detail.pprice}won</h2></li>
            
            </ul>
            <button><a href="##">구매하기</a></button>
            <button><a href="##">장바구니</a></button>
          </div>
        </div>

      </div>
    </section><!-- End Portfolio Details Section -->

  </main><!-- End #main -->
<%@ include file="/resources/includes/footer.jsp" %>
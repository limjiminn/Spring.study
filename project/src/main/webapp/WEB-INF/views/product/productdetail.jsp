<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />    
<%@ include file="/resources/includes/header.jsp" %>
<!-- <script>
	$(document).ready(function(){
		alert("확인");
		var amount=$("#pronum").val(" ");
		$("#insert").on('click',function(){
			location.href ='${ctx}/cart/insert?pid='+ ${detail.pid} +'&amount='+amount;
		});
	});
</script> -->
 <main id="main">	
	<section id="breadcrumbs" class="breadcrumbs">
      <div class="container">
        <div class="d-flex justify-content-between align-items-center">
          <h2>${detail.pcategory}</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>상품 분류</li>
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
            
            <div class="owl-carousel portfolio-details-carousel">
              <img src="${ctx}/resources/assets/img/lager/${detail.pimg}" class="img-fluid" alt="">
            </div>
          </div>

          <div class="col-lg-4 portfolio-info">
            <h2>${detail.pname}</h2>
			
            <ul>
              <li><strong>Category</strong>: ${detail.pcategory}</li>
              <li><strong>Country</strong>: ${detail.pcompany}</li>
          <%--     <li>
             	<strong>구입 수량 : </strong>
             	<input id=pronum name="pronum" type="number" min="1" max="${detail.pstock}" value="1"/>
            </li>            --%>
             <li>
              <strong>Price</strong>:
              	<h2><fmt:formatNumber value="${detail.pprice}" pattern="###,###,###"/></h2>
           </ul>
           <form action="${ctx}/cart/insert" name="form1" method="post">
           		<input type="hidden" name="pid" value="${detail.pid}">
           			<select name="amount">
           				<c:forEach begin="1" end="${detail.pstock}" var="i">
           					<option value="${i}">${i}</option>
           				</c:forEach>
           			</select>&nbsp;개 
	          <hr>
	          <p>${detail.pdescription}</p>
           			<input type="submit" value="장바구니">
           			
           			
            <%-- <button><a id="insert" href="${ctx}/cart/insert?pid=${detail.pid}&amount=${detail.pamount}" >장바구니</a></button> --%>
           </form> <a href="${ctx}/product/productlist?pcategory=${detail.pcategory}"> 목록으로 돌아가기 </a>	
          </div>
        </div>

      </div>
    </section><!-- End Portfolio Details Section -->
  </main><!-- End #main -->

<%@ include file="/resources/includes/footer.jsp" %>
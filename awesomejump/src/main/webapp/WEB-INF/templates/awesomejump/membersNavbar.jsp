<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}"/>
<section class="members-navbar">
  <ul class="nav nav-tabs justify-content-center" id="myTab" role="tablist">
    <li class="nav-item" role="memberInfo">
      <a class="nav-link" id="memberInfo-tab" data-toggle="tab" href="#memberInfo" role="tab" aria-controls="memberInfo" aria-selected="false">회원 정보</a>
    </li>
    <li class="nav-item" role="orderInfo">
      <a class="nav-link" id="orderInfo-tab" data-toggle="tab" href="#orderInfo" role="tab" aria-controls="orderInfo" aria-selected="false">주문 정보</a>
    </li>
    <li class="nav-item" role="myActivity">
      <a class="nav-link" id="myActivity-tab" data-toggle="tab" href="#myActivity" role="tab" aria-controls="myActivity" aria-selected="false">나의 활동</a>
    </li>
  </ul>
  <div class="tab-content" id="myTabContent">
    <div class="tab-pane active" id="memberInfo" role="tabpanel" aria-labelledby="memberInfo-tab">
      <!-- 회원 정보 -->
      <div class="swiper-container mySwiper">
        <div class="swiper-wrapper swiper-memberInfo">
          <div class="swiper-slide">
            <a class="<c:out value="${uri == '/members' ? 'text-primary' : ''}"/>" href="/members">회원 정보</a>
          </div>
          <div class="swiper-slide">
            <a class="<c:out value="${uri == '/recentView' ? 'text-primary' : ''}"/>" href="/recentView">최근 본 상품</a>
          </div>
          <div class="swiper-slide">
            <a class="<c:out value="${uri == '/interest' ? 'text-primary' : ''}"/>" href="/interest">관심 상품</a>
          </div>
          <div class="swiper-slide">
            <a class="<c:out value="${uri == '/coupon' ? 'text-primary' : ''}"/>" href="/coupon">쿠폰</a>
          </div>
          <div class="swiper-slide">
            <a class="<c:out value="${uri == '/point' ? 'text-primary' : ''}"/>" href="/point">포인트</a>
          </div>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="orderInfo" role="tabpanel" aria-labelledby="orderInfo-tab">
      <div class="tab-list">
        <div class="tab-items">
          <a class="<c:out value="${uri == '/orders' ? 'text-primary' : ''}"/>" href="/orders">주문 조회</a>
        </div>
        <div class="tab-items">
          <a class="<c:out value="${uri == '/address' ? 'text-primary' : ''}"/>" href="/address">배송지 관리</a>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="myActivity" role="tabpanel" aria-labelledby="myActivity-tab">
      <div class="tab-list">
        <div class="tab-items">
          <a class="<c:out value="${uri == '/myReview' ? 'text-primary' : ''}"/>" href="/myReview">나의 리뷰</a>
        </div>
        <div class="tab-items">
          <a class="<c:out value="${uri == '/myQna' ? 'text-primary' : ''}"/>" href="/myQna">나의 문의</a>
        </div>
      </div>      
    </div>
  </div>
</section>
<div style="height: 95px;"></div>

<script>
var swiper = new Swiper(".mySwiper", {
  slidesPerView : 'auto',
  freeMode : true
});
</script>
<style>
.members-navbar a {
	font-family: "NANUM";
	font-weight: bold;
	font-size: 20px;
}

</style>
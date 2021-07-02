<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<s:authorize access="hasRole('ROLE_USER')">
  <s:authentication var="member" property="principal"/>
</s:authorize>

<header class="main_menu home_menu">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-lg-12">
        <nav class="navbar navbar-expand-lg navbar-light">
          <a class="navbar-brand" href="/"> <img src="/resources/awesomejump/img/logo.png" alt="logo"></a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="menu_icon"><i class="fas fa-bars"></i></span>
          </button>
          <div class="collapse navbar-collapse main-menu-item" id="navbarSupportedContent">
            <ul class="navbar-nav">
            
            <s:authorize access="isAnonymous()">
              <li class="nav-item d-block d-sm-none text-center border-bottom">
                <a class="d-inline nav-link" href="/login">로그인</a>
                <a class="d-inline nav-link" href="/register">회원가입</a>
              </li>
            </s:authorize>
              
              <li class="nav-item"><a class="nav-link" href="/">Home</a></li>
              <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="blog" id="navbarDropdown_1"
                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Shop </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown_1">
                  <a class="dropdown-item" href="category"> shop category</a> <a class="dropdown-item"
                    href="single-product">product details</a>
                </div></li>
              <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="blog" id="navbarDropdown_3"
                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> pages</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
                  <a class="dropdown-item" href="login"> login</a> <a class="dropdown-item" href="tracking">tracking</a>
                  <a class="dropdown-item" href="checkout">product checkout</a> 
                  <a class="dropdown-item" href="cart">shopping cart</a>
                   <a class="dropdown-item" href="confirmation">confirmation</a> <a
                    class="dropdown-item" href="elements">elements</a>
                </div></li>
              <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="blog" id="navbarDropdown_2"
                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> blog</a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown_2">
                  <a class="dropdown-item" href="blog"> blog</a> <a class="dropdown-item" href="single-blog">Single
                    blog</a>
                </div></li>
              <li class="nav-item"><a class="nav-link" href="contact">Contact</a></li>
            </ul>
          </div>
          <div class="hearer_icon d-flex">
            <a class="d-none d-sm-block align-self-center" id="search_1" href="javascript:void(0)"><i class="ti-search"></i></a> 
          
            <s:authorize access="isAnonymous()">
              <span class="d-none d-sm-block">
                <a class="ml-4 font-weight-bold" href="/login">로그인</a>
                <span class="mx-2 font-weight-lighter" style="position: relative; bottom: 2px;">｜</span>
                <a class="font-weight-bold" href="/register">회원가입</a>
              </span>
            </s:authorize>
            
            <s:authorize access="hasRole('ROLE_USER')">
              <span class="d-none d-sm-block">
                <a class="align-self-center" href="" style="line-height: 38px !important;"><i class="ti-heart"></i></a>
                <a class="align-self-center mr-3" href="/cart"><i class="fas fa-cart-plus"></i></a>
              </span>
              
              <%-- 
              <div class="dropdown cart align-self-center">
                <a class="dropdown-toggle" href="" id="navbarDropdown3" role="button" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false"> <i class="fas fa-cart-plus"></i>
                  <!-- [style.css] .main_menu .cart i:after 장바구니 개수 css -->
                </a>
              </div>
              --%>
              
              <div class="btn-group">
                <c:choose>
                  <c:when test="${member.mem_profile_img eq 'profile.jpg'}">
                    <img class="rounded-circle ml-2" src="/resources/awesomejump/img/profile.jpg" width="32px" height="32px"/>
                  </c:when>
                  <c:otherwise>
                    <img class="rounded-circle ml-2" src="/upload/member/${member.mem_profile_img}" width="32px" height="32px"/>
                  </c:otherwise>
                </c:choose>
                <button type="button" class="btn dropdown-toggle dropdown-toggle-split" 
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
                <div class="dropdown-menu">
                  <a class="dropdown-item" href="/members">내 정보</a>
                  <div class="dropdown-divider"></div>
                  <a class="dropdown-item" href="/logout">로그아웃</a>
                </div>
              </div>
            </s:authorize>
          </div>
        </nav>
      </div>
    </div>
  </div>
  <div class="search_input" id="search_input_box">
    <div class="container ">
      <form class="d-flex justify-content-between search-inner">
        <input type="text" class="form-control" id="search_input" placeholder="Search Here">
        <button type="submit" class="btn"></button>
        <span class="ti-close" id="close_search" title="Close Search"></span>
      </form>
    </div>
  </div>
</header>
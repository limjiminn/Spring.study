<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />

<!DOCTYPE html>
<html lang="en">

<!-- <head> -->
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>beer shop</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="${ctx}/resources/assets/img/favicon.png" rel="icon">
  <link href="${ctx}/resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="${ctx}/resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/venobox/venobox.css" rel="stylesheet">
  <link href="${ctx}/resources/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="${ctx}/resources/assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: Sailor - v2.3.1
  * Template URL: https://bootstrapmade.com/sailor-free-bootstrap-theme/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>
<%@ include file="/resources/includes/header.jsp" %>
  
  <!-- ======= Hero Section ======= -->
  <section id="hero">
    <div id="heroCarousel" class="carousel slide carousel-fade" data-ride="carousel">

      <ol class="carousel-indicators" id="hero-carousel-indicators"></ol>

      <div class="carousel-inner" role="listbox">

        <!-- Slide 1 -->
        <div class="carousel-item active" style="background-image: url(${ctx}/resources/assets/img/slide/????????????.jpg)">
          <div class="carousel-container">
            <div class="container">
              <h2 class="animate__animated animate__fadeInDown">??????</h2>
              <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p>
              <a href="${ctx}/product/productlist?pcategory=lager" class="btn-get-started animate__animated animate__fadeInUp scrollto">Read More</a>
            </div>
          </div>
        </div>

        <!-- Slide 2 -->
        <div class="carousel-item" style="background-image: url(${ctx}/resources/assets/img/slide/????????????.jpg)">
          <div class="carousel-container">
            <div class="container">
              <h2 class="animate__animated animate__fadeInDown">??????</h2>
              <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p>
              <a href="${ctx}/product/productlist?pcategory=ale" class="btn-get-started animate__animated animate__fadeInUp scrollto">Read More</a>
            </div>
          </div>
        </div>

        <!-- Slide 3 -->
        <div class="carousel-item" style="background-image: url(${ctx}/resources/assets/img/slide/???????????????.jpg)">
          <div class="carousel-container">
            <div class="container">
              <h2 class="animate__animated animate__fadeInDown">?????????</h2>
              <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p>
              <a href="${ctx}/product/productlist?pcategory=radler" class="btn-get-started animate__animated animate__fadeInUp scrollto">Read More</a>
            </div>
          </div>
        </div>

        <!-- Slide 4 -->
        <div class="carousel-item" style="background-image: url(${ctx}/resources/assets/img/slide/??????.jpg)">
          <div class="carousel-container">
            <div class="container">
              <h2 class="animate__animated animate__fadeInDown">?????????</h2>
              <p class="animate__animated animate__fadeInUp">Ut velit est quam dolor ad a aliquid qui aliquid. Sequi ea ut et est quaerat sequi nihil ut aliquam. Occaecati alias dolorem mollitia ut. Similique ea voluptatem. Esse doloremque accusamus repellendus deleniti vel. Minus et tempore modi architecto.</p>
              <a href="${ctx}/product/productlist?pcategory=dark" class="btn-get-started animate__animated animate__fadeInUp scrollto">Read More</a>
            </div>
          </div>
        </div>
      </div>

      <a class="carousel-control-prev" href="#heroCarousel" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon icofont-simple-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>

      <a class="carousel-control-next" href="#heroCarousel" role="button" data-slide="next">
        <span class="carousel-control-next-icon icofont-simple-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>

    </div>
  </section><!-- End Hero -->

  <main id="main" >

    <!-- ======= Portfolio Section ======= -->
    <section id="portfolio" class="portfolio">
      <div class="container">

        <div class="section-title">
          <h2>beer</h2>
          <p>????????????</p>
        </div>

        <div class="row">
          <div class="col-lg-12 d-flex justify-content-center">
            <ul id="portfolio-flters">
              <li data-filter="*" class="filter-active">All</li>
              <li data-filter=".filter-app">??????</li>
              <li data-filter=".filter-card">??????</li>
              <li data-filter=".filter-web">?????????</li>
              <li data-filter=".filter-webs">?????????</li>
            </ul>
          </div>
        </div>

        <div class="row portfolio-container">

          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/?????? ?????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=lager0"><h4>?????? ?????????</h4></a>
                <p>App</p>
                <div class="portfolio-links">
                  <a href="assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/????????? ?????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=radler3"><h4>????????? ?????????</h4></a>
                <p>Web</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-2.jpg" data-gall="portfolioGallery" class="venobox" title="Webs 3"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/lager/??????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=lager12"><h4>??????</h4></a>
                <p>App</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-3.jpg" data-gall="portfolioGallery" class="venobox" title="App 2"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-card">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/?????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=ale10"><h4>?????????</h4></a>
                <p>Card</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-4.jpg" data-gall="portfolioGallery" class="venobox" title="Card 2"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/????????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=radler2"><h4>????????????</h4></a>
                <p>Web</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-5.jpg" data-gall="portfolioGallery" class="venobox" title="Web 2"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-app">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/?????????????????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=lager6"><h4>?????????????????????</h4></a>
                <p>App</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-6.jpg" data-gall="portfolioGallery" class="venobox" title="App 3"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-card">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/??????????????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=ale9"><h4>??????????????????</h4></a>
                <p>Card</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-7.jpg" data-gall="portfolioGallery" class="venobox" title="Card 1"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-card">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/?????? ?????? ??????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=ale4"><h4>?????? ?????? ??????</h4></a>
                <p>Card</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-8.jpg" data-gall="portfolioGallery" class="venobox" title="Card 3"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 portfolio-item filter-web">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/???????????? ?????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                <a href="${ctx}/product/productdetail?pid=radler4"><h4>???????????? ?????????</h4></a>
                <p>Web</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-9.jpg" data-gall="portfolioGallery" class="venobox" title="Web 3"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>
		  
		  <div class="col-lg-4 col-md-6 portfolio-item filter-webs">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/????????? ????????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=dark0"><h4>????????? ????????????</h4></a>
                <p>Web</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-5.jpg" data-gall="portfolioGallery" class="venobox" title="Web 2"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>
        
        
        <div class="col-lg-4 col-md-6 portfolio-item filter-webs">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/????????? ????????????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=dark1"><h4>????????? ????????????</h4></a>
                <p>Web</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-5.jpg" data-gall="portfolioGallery" class="venobox" title="Web 2"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>
        
        <div class="col-lg-4 col-md-6 portfolio-item filter-webs">
            <div class="portfolio-wrap">
              <img src="${ctx}/resources/assets/img/portfolio/????????? ??????.jpg" class="img-fluid" alt="">
              <div class="portfolio-info">
                 <a href="${ctx}/product/productdetail?pid=dark2"><h4>????????? ??????</h4></a>
                <p>Web</p>
                <div class="portfolio-links">
                  <a href="${ctx}/resources/assets/img/portfolio/portfolio-5.jpg" data-gall="portfolioGallery" class="venobox" title="Web 2"><i class="bx bx-plus"></i></a>
                  <a href="portfolio-details.html" data-gall="portfolioDetailsGallery" data-vbtype="iframe" class="venobox" title="Portfolio Details"><i class="bx bx-link"></i></a>
                </div>
              </div>
            </div>
          </div>
        
        </div>
      </div>
    </section><!-- End Portfolio Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <%@ include file="/resources/includes/footer.jsp" %>
 
</body>

</html>
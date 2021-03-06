<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="ko">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<s:csrfMetaTags/>

<title>AwesomeJump - feature</title>

<link rel="icon" href="/resources/awesomejump/img/favicon.png">
<link rel="stylesheet" href="/resources/awesomejump/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/awesomejump/css/animate.css">
<link rel="stylesheet" href="/resources/awesomejump/css/owl.carousel.min.css">
<link rel="stylesheet" href="/resources/awesomejump/css/all.css">
<link rel="stylesheet" href="/resources/awesomejump/css/flaticon.css">
<link rel="stylesheet" href="/resources/awesomejump/css/themify-icons.css">
<link rel="stylesheet" href="/resources/awesomejump/css/magnific-popup.css">
<link rel="stylesheet" href="/resources/awesomejump/css/slick.css">
<!-- style CSS -->
<link rel="stylesheet" href="/resources/awesomejump/css/style.css">
<link rel="stylesheet" href="/resources/awesomejump/css/custom/members.css">

<script defer src="/resources/awesomejump/js/jquery-1.12.1.min.js"></script>
<script defer src="/resources/awesomejump/js/popper.min.js"></script>
<script defer src="/resources/awesomejump/js/bootstrap.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.magnific-popup.js"></script>
<script defer src="/resources/awesomejump/js/masonry.pkgd.js"></script>
<script defer src="/resources/awesomejump/js/owl.carousel.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.nice-select.min.js"></script>
<script defer src="/resources/awesomejump/js/slick.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.counterup.min.js"></script>
<script defer src="/resources/awesomejump/js/waypoints.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.ajaxchimp.min.js"></script>
<script defer src="/resources/awesomejump/js/jquery.form.js"></script>
<script defer src="/resources/awesomejump/js/jquery.validate.min.js"></script>
<script defer src="/resources/awesomejump/js/mail-script.js"></script>
<!-- custom js -->
<script defer src="/resources/awesomejump/js/custom.js"></script>
<script defer src="/resources/awesomejump/js/custom/members.js"></script>

<!-- Swiper -->
<link rel="stylesheet" href="/resources/awesomejump/css/swiper.min.css">
<script src="/resources/awesomejump/js/swiper.min.js"></script>
<style type="text/css">
p {
font-size: 20px;
}
</style>

</head>

<body class="members">
  <s:authentication var="member" property="principal"/>

  <!-- Header -->
  <jsp:include page="/WEB-INF/templates/awesomejump/header.jsp" />
  <!-- ?????? ?????? ????????? ?????????(MembersNavbar) -->
  <jsp:include page="/WEB-INF/templates/awesomejump/membersNavbar.jsp"/>
 
  <!-- ?????? ?????? ?????? ?????? -->
  <c:if test="${modifyResult eq true}">
  <jsp:include page="/WEB-INF/templates/awesomejump/alertSuccess.jsp">
    <jsp:param value="?????? ????????? ??????????????? ?????????????????????." name="alert"/>
  </jsp:include>
  </c:if>
 
  <!-- ?????? ?????? ?????? ??? ?????? -->
  <section class="member-info">
    <div class="container">
      <div class="row">
        <div class="col-md-5 offset-md-2">
          <p>????????? : </p>
          <div class="d-flex col-md-9 p-0">
            <input class="form-control" type="text" name="nickname" value="${member.mem_nickname}">
            <button id="nickname-modify" class="btn members-button btn-modify">??????</button>
          </div>
          <div class="nickcheck-result text-danger"></div>
          <div class="my-4"><p>????????? : </p>${member.mem_email}</div>
          <div class="my-4"><p>????????? : </p>${member.mem_date}</div>
          <div class="my-4"><p>?????? ????????? : </p>${member.mem_last_login}</div>          
        </div>
        <div class="col-md-5">
          <form action="/members/profileimagemodify" method="POST" enctype="multipart/form-data" id="uploadImageForm">
            <input type="hidden" value="${member.mem_email}" id="mem_email">
            <div class="mt-3 d-flex align-items-center">
              <p>????????? ?????? : </p>
              <c:choose>
                <c:when test="${member.mem_profile_img eq 'profile.jpg'}">
                  <img class="rounded-circle ml-2" src="/resources/awesomejump/img/profile.jpg" width="64px" height="64px"/>
                </c:when>
                <c:otherwise>
                  <img class="rounded-circle ml-2" src="/upload${member.mem_profile_img}" width="64px" height="64px"/>
                </c:otherwise>
              </c:choose>
              <button type="button" class="ml-4 btn members-button btn-modify" id="uploadBtn">??????</button>
            </div>
            <input class="mt-3" type="file" name="uploadedImage">
            <s:csrfInput/>
          </form>
        <p class="mt-3">????????? ????????? 10MB ?????? <br>?????????(bmp, gif, jpg, jpeg, png)??? <br>????????? ???????????????.</p>
        </div>
      </div>
      <div class="offset-md-2 mt-3">
        <button class="btn members-button" data-toggle="modal" data-target="#passwordModifyModal">???????????? ??????</button>
        <button class="btn members-button" data-toggle="modal" data-target="#memberLeaveModal">?????? ??????</button>
      </div>
    </div>
  </section>
  
  <!-- Footer -->  
  <jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp" />
  
  <!-- ???????????? ?????? -->
  <div class="modal fade" id="passwordModifyModal" data-backdrop="static" data-keyboard="false" 
       tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="staticBackdropLabel">???????????? ??????</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-md-8 offset-md-2">
              <div class="form-group">
                <label for="modify-mem_pw" class="col-form-label">?????? ???????????? :</label>
                <input type="password" class="form-control" id="modify-mem_pw">
              </div>
              <div id="vm-mem_pw" class="min-height-30px">??????????????? 6?????? ?????? 20?????? ?????? ????????? ????????? ????????? ???????????? ?????????.</div>
              <form action="/members/changepw" method="POST" name="changepw">
                <div class="form-group">
                  <label for="modify-mem_new_pw" class="col-form-label">????????? ???????????? :</label>
                  <input type="password" class="form-control" id="modify-mem_new_pw" name="newPassword">
                </div>
                <div id="vm-mem_new_pw" class="text-danger min-height-30px"></div>
                <div class="form-group">
                  <label for="modify-mem_confirm_new_pw" class="col-form-label">????????? ???????????? ?????? :</label>
                  <input type="password" class="form-control" id="modify-mem_confirm_new_pw">
                </div>
                <div id="vm-mem_confirm_new_pw" class="text-danger vm-mem_onfirm_new_pw min-height-30px"></div>
                <s:csrfInput/>
              </form>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">??????</button>
          <button type="button" class="btn btn-primary" id="modifyPasswordBtn">??????</button>
        </div>
      </div>
    </div>
  </div>
  
  <!-- Modal -->
  <div class="modal fade" id="memberLeaveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">?????? ??????</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body my-3 lead text-center">
          ??????????????? ?????????????????????????
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">??????</button>
          <button type="button" class="btn btn-danger" id="memberLeaveBtn">??????</button>
        </div>
      </div>
    </div>
  </div>  
  
</body>
</html>
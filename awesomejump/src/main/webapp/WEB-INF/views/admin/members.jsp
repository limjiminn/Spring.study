<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
  <title>Members Page</title>
  
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <!-- Custom styles for this template-->
  <link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">
  <link href="/resources/admin/css/members.css" rel="stylesheet">
  
  <script defer src="/resources/admin/vendor/jquery/jquery.min.js"></script>
  <script defer src="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script defer src="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>
  <!-- Custom scripts -->
  <script defer src="/resources/admin/js/sb-admin-2.min.js"></script>
  <script defer src="/resources/admin/js/members.js"></script>
</head>

<body id="page-top">
  <!-- Page Wrapper -->
  <div id="wrapper">

    <%-- <jsp:include page="/WEB-INF/templates/admin/sidebar.jsp"/> --%>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
      <!-- Main Content -->
      <div id="content">

        <%-- <jsp:include page="/WEB-INF/templates/admin/topbar.jsp"/> --%>

        <!-- Begin Page Content -->
        <div class="container-fluid">
          <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
              <li class="breadcrumb-item"><a href="/admin">Home</a></li>
              <li class="breadcrumb-item active" aria-current="page">?????? ??????</li>
            </ol>
          </nav>
          <!-- Page Heading -->
          <div class="d-flex align-items-center">
            <h1 class="h3 mb-2 text-gray-800 ml-1 mt-4">?????? ??????</h1>
            <i class="fas fa-search fa-lg" style="position: relative; top: .5rem; left: .5rem; cursor: pointer;"
               data-toggle="modal" data-target="#searchModal"></i>
          </div>
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th>????????????</th>
                      <th><span>?????????</span></th>
                      <th><span>?????????</span></th>
                      <th><span>?????? ?????????</span></th>
                      <th><span>?????? ??????</span></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach var="member" items="${memberList}">
                      <tr>
                        <td>${member.mem_no}</td>
                        <td><a href="/admin/members/mem_detail?mem_no=${member.mem_no}">${member.mem_email}</a></td>
                        <td>${member.mem_nickname}</td>
                        <td><fmt:formatDate value="${member.mem_last_login}" pattern="yyyy-MM-dd"/></td>
                        <td>${member.mem_enabled_yn eq 1 ? '?????????' : '????????????'}</td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
              <!-- Pagination -->
              <c:if test="${pageParam.lastBlockNum != -1}">
                <nav aria-label="pagination">
                  <ul class="pagination">
                    <!-- << -->
                    <li class="page-item <c:out value="${pageParam.blockNum eq 0 ? 'disabled' : ''}"/>">
                      <a class="page-link" href="/admin/members?page=${pageParam.blockNum * pageParam.paginationCount}" tabindex="-1">
                        <i class="fas fa-angle-left"></i>
                      </a>
                    </li>
                    <!-- Search keywords -->
                    <c:set var="email" value="${pageParam.email eq null ? '' : '&email='}${pageParam.email eq null ? '' : pageParam.email}"/>
                    <c:set var="nickname" value="${pageParam.nickname eq null ? '' : '&nickname='}${pageParam.nickname eq null ? '' : pageParam.nickname}"/>
                    <c:set var="lastLoginBegin" value="${pageParam.lastLoginBegin eq null ? '' : '&lastLoginBegin='}${pageParam.lastLoginBegin eq null ? '' : pageParam.lastLoginBegin}"/>
                    <c:set var="lastLoginEnd" value="${pageParam.lastLoginEnd eq null ? '' : '&lastLoginEnd='}${pageParam.lastLoginEnd eq null ? '' : pageParam.lastLoginEnd}"/>
                    <c:set var="status" value="${pageParam.status eq null ? '' : '&status='}${pageParam.status eq null ? '' : pageParam.status}"/>
                    <!-- Paging -->
                    <c:forEach begin="1" end="${pageParam.paginationCount}" varStatus="varStatus">
                      <c:set var="page" value="${varStatus.index + pageParam.blockNum * pageParam.paginationCount}"/>
                      <c:if test="${pageParam.pageCount >= page}">
                        <li class="page-item <c:out value="${pageParam.currentPage eq page ? 'active' : ''}"/>">
                          <a class="page-link" href="/admin/members?page=${page}${email}${nickname}${lastLoginBegin}${lastLoginEnd}${status}">${page}</a>
                        </li>
                      </c:if>
                    </c:forEach>
                    <!-- >> -->
                    <li class="page-item <c:out value="${pageParam.blockNum eq pageParam.lastBlockNum ? 'disabled' : ''}"/>">
                      <a class="page-link" href="/admin/members?page=${(pageParam.blockNum + 1) * pageParam.paginationCount + 1}">
                        <i class="fas fa-angle-right"></i>
                      </a>
                    </li>
                  </ul>
                </nav>
              </c:if>
              <!-- ?????? ????????? ?????? ?????? -->
              <c:if test="${pageParam.lastBlockNum == -1}">
                <div class="text-center">
                  <div class="lead">?????? ????????? ???????????? ????????????.</div>
                </div>
              </c:if>
            </div>
          </div>
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

  <!-- ????????? -->
  <div class="modal fade p-0" id="searchModal" tabindex="-1" data-backdrop="static" data-keyboard="false" role="dialog"
    aria-labelledby="#searchModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <form action="/admin/members" method="GET" id="searchForm">
          <div class="modal-header">
            <h5 class="modal-title" id="searchModalLabel">?????? ??????</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group row">
              <label for="search_email" class="offset-lg-2 col-lg-2 col-form-label">?????????</label>
              <div class="col-lg-6">
                <input type="text" class="form-control" id="search_email" name="email">
              </div>
            </div>
            <div class="form-group row">
              <label for="search_nickname" class="offset-lg-2 col-lg-2 col-form-label">?????????</label>
              <div class="col-lg-6">
                <input type="text" class="form-control" id="search_nickname" name="nickname">
              </div>
            </div>
            <div class="form-group row">
              <label for="search_lastLoginBegin" class="offset-lg-2 col-lg-2 col-form-label">?????? ?????????</label>
            </div>
            <div class="row">
              <div class="col-lg-4 col-sm-6 offset-lg-2 mb-3">
                <input class="form-control" type="date" id="search_lastLoginBegin" placeholder="?????????" name="lastLoginBegin">
              </div>
              <div class="col-lg-4 col-sm-6">
                <input class="form-control" type="date" id="search_lastLoginEnd" placeholder="?????????" name="lastLoginEnd">
              </div>
            </div>
            <div class="row">
              <div class="col-lg-4 col-sm-6 offset-lg-2 my-3">
                ?????? ??????
              </div>
              <div class="col-lg-4 col-sm-6 mt-3">
                <label>????????? <input type="radio" name="status" value="1" style="font-size: 1.1rem;"></label>
                <label>???????????? <input type="radio" name="status" value="0" style="font-size: 1.1rem;"></label>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">??????</button>
            <button type="submit" class="btn btn-primary">??????</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script>console.log("${pageParam}")</script>

</body>
</html>
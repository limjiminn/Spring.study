<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/resources/includes/header.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}" scope= "application" ></c:set>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Register</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Read Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <%-- <form role="form" action="${ctx}/board/register" method="post"> --%>
        	
          <div class="form-group" >
            <label>bno</label> <input class="form-control" name='bno' value="${board.bno}" readonly="readonly">
          </div>
          
          <div class="form-group">
            <label>Title</label> <input class="form-control" name='title' value="${board.title}" readonly="readonly">
          </div>

          <div class="form-group">
            <label>Text area</label>
            <textarea class="form-control" rows="3" name='content'  readonly="readonly">${board.content}</textarea>
          </div>

          <div class="form-group">
            <label>Writer</label> <input class="form-control" name='writer' value="${board.writer}" readonly="readonly">
          </div>
          
          <button data-oper="modify" class="btn btn-default">
          	<a href="${ctx}/board/modify?bno=${board.bno}">Modify</a></button>
          	
          <button data-oper="list" class="btn btn-default">
          	<a href="${ctx}/board/list">List</a></button>
          
        <!-- </form> -->

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<%@include file="/resources/includes/footer.jsp"%>
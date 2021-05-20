<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/resources/includes/header.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}" scope= "application" ></c:set>

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Board Modify</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<script>
	$(document).ready(function(){
		var formObj = $("form");
		
		$("button").on("click", function(e){
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			/* === : 타입까지 비교 */
			if (operation === "remove") {
				formObj.attr("action", "${ctx}/board/remove");
			}else if(operation === "list"){
				
				formObj.attr("action","${ctx}/board/list").attr("method","get");
				var pageNumTag = $("input[name = 'pageNum']").clone();
				var amountTag = $("input[name = 'amount']").clone();

				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
			}
			
			formObj.submit();
		});
	});
</script>
<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Modify Page</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form" action="${ctx}/board/modify" method="post">
        	<!--수정하고 페이지 고정  -->
        	<input type="hidden"  name="pageNum" value="${cri.pageNum}">
          	<input type="hidden"  name="amount" value="${cri.amount}">
          	
          <div class="form-group" >
            <label>bno</label> <input class="form-control" name='bno' value="${board.bno}" readonly="readonly">
          </div>
          
          <div class="form-group">
            <label>Title</label> <input class="form-control" name='title' value="${board.title}" >
          </div>

          <div class="form-group">
            <label>Text area</label>
            <textarea class="form-control" rows="3" name='content'>${board.content}</textarea>
          </div>

          <div class="form-group">
            <label>Writer</label> <input class="form-control" name='writer' value="${board.writer}" readonly="readonly">
          </div>
          
          <button type="submit" data-oper="modify" class="btn btn-default">Modify</button>
          
          <button type="submit" data-oper="remove" class="btn btn-danger">Remove</button>
          
          <button type="submit" data-oper="list" class="btn btn-info">List</button>
          
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<%@include file="/resources/includes/footer.jsp"%>
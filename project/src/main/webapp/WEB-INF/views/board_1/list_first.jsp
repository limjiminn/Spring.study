<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/resources/includes/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath}" scope= "application" ></c:set>

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Tables</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Board List Page
                            <button id="regBtn" type="button" class="btn btn-xs pull-right">Register New Board</button>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table class="table table-striped table-bordered table-hover">
                            
                                <thead>
                                    <tr>
                                        <th>#번호</th>
                                        <th>제목</th>
                                        <th>작성자</th>
                                        <th>작성일</th>
                                        <th>조회수</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach items="${list}" var="board" >
                                		<tr>
                                			<th>${board.bno}</th>
                                			<th><a href="${ctx}/board/read?bno=${board.bno}">${board.title}</a></th>
                                			<th>${board.writer}</th>
                                			<th>${board.regdate}</th>
                                			<th><span class="badge bg-ref">${board.viewcnt}</th>
                                		</tr>
                                	</c:forEach>
                                </tbody>
                            </table>
                            	<!-- Modal 추가 : 활성화된 div를 선택하지 않고선 다시 원래 화면을 볼수 없도록 막기때문에 메세지를 보여주는데 효과적-->
                            	<!-- Modal은 날씬하지만 신축성있고 최소한의 기능성과 스마트함을 갖춘 대화창이다. -->
                            	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            		<div class="modal-dialog">
                            			<div class="modal-content">
                            				<div class="modal-header">
                            					<button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>
                            					<h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            				</div>
                            				<div class="modal-body">
                            					처리가 완료 되었습니다.
                            				</div>
                            				<div class="modal-footer">
                            					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            					<button type="button" class="btn btn-primary" data-dismiss="">Save Changes</button>
                            				</div>
                     
                            			</div>
                            		</div>
                            	</div>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
<script>
	$(document).ready(function(){
		var result = "${result}";
		
		checkModal(result); 
		/* window.history 객체를 조작 */
		//정상처리되는순간 history 없앤다.
		history.replaceState({}, null, null);
		
		function checkModal(result) {
			
			if (result == "" || history.state) {
				return;
			}
			
			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글" + parseInt(result) + "변이 등록되었습니다.");  
			}
			
			$("#myModal").modal("show");
		}
		
		$("#regBtn").on("click", function() {
			self.location = "${ctx}/board/register";
		});
	});
</script>            
<%@include file="/resources/includes/footer.jsp"%>




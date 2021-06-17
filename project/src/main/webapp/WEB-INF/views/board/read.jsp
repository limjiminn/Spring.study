<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%@include file="/resources/includes/header.jsp"%>
<c:set var="contextPath" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<style>
	.uploadResult {
		width:100%;
		background-color: gray;
	}
	
	.uploadResult ul{
		display:flex;
		flex-flow: row;
		justify-content: center;
		align-items: center;
	}
	
	.uploadResult ul li {
		list-style: none;
		padding: 10px;
		align-content: center;
		text-align: center;
	}
	
	.uploadResult ul li img{
		width: 100px;
	}
	
	.uploadResult ul li span {
		color:white;
	}
	
	.bigPictureWrapper {
		position: absolute;
		display: none;
		justify-content: center;
		align-items: center;
		top:0%;
		width:100%;
		height:100%;
		background-color: gray; 
		z-index: 100;
		background:rgba(255,255,255,0.5);
	}
	
	.bigPicture {
		position: relative;
		display:flex;
		justify-content: center;
		align-items: center;
	}
	
	.bigPicture img {
		width:600px;
	}
</style>
<section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>게시판</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>리뷰</li>
            <li>게시판</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
<div class="container">
  <div class="col-lg-12">
  
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="container">
  <div class="col-lg-12">
    <div class="panel panel-default">

     
      <!-- /.panel-heading -->
      <div class="panel-body">

         <div class="form-group">
           <label>번호</label> <input class="form-control" name="bno" value="${board.bno }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>제목</label> <input class="form-control" name="title" value="${board.title }" readonly="readonly">
         </div>

         <div class="form-group">
           <label>내용</label>
           <textarea class="form-control" rows="3" name="content" readonly="readonly">${board.content }</textarea>
         </div>

         <div class="form-group">
           <label>작성자</label> <input class="form-control" name="writer" value="${board.writer }" readonly="readonly">
         </div>
         <!-- ===========회원과 작성자가 일치한다면 ================================================= -->
         <c:if test="${member.username == board.writer }">
         	<button data-oper="modify" class="btn btn-default">수정</button>         
         </c:if>
		 <button data-oper="list" class="btn btn-info">목록</button>
		 
		 <form id="operForm" action="${contextPath}/boad/modify" method="get">
	  		 <input type="hidden" id="bno" name="bno" value="${board.bno}">
	  		 <input type="hidden" id="pageNum" name="pageNum" value="${cri.pageNum}">
	  		 <input type="hidden" id="amount" name="amount" value="${cri.amount}">
			 <input type="hidden" name="type" value="${cri.type}">
			 <input type="hidden" name="keyword" value="${cri.keyword}">
		 </form>
      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<!-- <div class='bigPictureWrapper'>
  <div class='bigPicture'>
  </div>
</div>

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Files</div>
      /.panel-heading
      <div class="panel-body">
        
        <div class="uploadResult"> 
          <ul>
          </ul>
        </div>
      </div>
       end panel-body
    </div>
     end panel-body
  </div>
  end panel
</div>
/.row -->

<div class='container'>
  <div class="col-lg-12">

    <!-- /.panel -->
    <div class="panel panel-default">
      <div class="panel-heading">
      <!-- ======================================================================== -->
        <i class="fa fa-comments fa-fw"></i> 댓글
        <c:if test="${not empty member}">
	        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글 작성</button>
	        <hr>
        </c:if>
      </div>      
      
      <!-- /.panel-heading -->
      <div class="panel-body">        
        <ul class="chat">
        </ul>
        <!-- ./ end ul -->
      </div>
      <!-- /.panel .chat-panel -->

	  <div class="panel-footer"></div>
	</div>
  </div>
  <!-- ./ end row -->
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">댓글 작성</h4>
            </div>
            <div class="modal-body">
            	<div class="form-group">
            		<label>내용</label>
            		<input class="form-control" name="replytext" value="New Reply!!!!" >
            	</div>
            	<div class="form-group">
            		<label>작성자</label>
            		<input class="form-control" name="replyer" value="replyer" readonly="readonly">
            	</div>
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" name="replyDate" value="2018-01-01 13:13">
				</div>
            </div>
			<div class="modal-footer">
				
	        		<button id="modalModifyBtn" type="button" class="btn btn-warning">수정</button>
	        		<button id="modalRemoveBtn" type="button" class="btn btn-danger">삭제</button>
				
        		<button id="modalRegisterBtn" type="button" class="btn btn-primary">게시</button>
        		<button id="modalCloseBtn" type="button" class="btn btn-default">취소</button>
      		</div>          
    	</div>
          <!-- /.modal-content -->
	</div>
<!-- /.modal-dialog -->
</div>
 <!-- /.modal -->

<script type="text/javascript" src="${contextPath}/resources/js/reply.js"></script>
<script type="text/javascript">
window.onload = function() {
	history.pushState(null, null, location.href);
    window.onpopstate = function() {
	
        history.go(1);
	};
}; 

$(document).ready(function() {
	var bnoValue = "${board.bno}";
	var replyUL = $(".chat");
	  
	showList(1);
	
	function showList(page) {
		console.log("show list " + page);
		   
		replyService.getList(
				{bno:bnoValue, contextPath:"${contextPath}", page: page || 1 }, 
				function(replyCnt, list) {
					console.log("replyCnt: "+ replyCnt );
					console.log("list: " + list);
					console.log(list);
					
					if(page == -1){
						pageNum = Math.ceil(replyCnt/10.0);
						showList(pageNum);
						return;
					}
					  
					var str="";
					 
					if(list == null || list.length == 0){
						return;
					}
					 
					for (var i = 0, len = list.length || 0; i < len; i++) {
						str +="<li class='left clearfix' data-rno='"+list[i].rno+"'>";
						str +="  <div><div class='header'><strong class='primary-font'>["
							+ list[i].rno+"] "+list[i].replyer+"</strong>";
						str +="    <small class='pull-right text-muted'>"
							+ replyService.displayTime(list[i].updatedate)+"</small></div>";
						str +="    <p>"+list[i].replytext+"</p></div></li>";
					}
					 
					replyUL.html(str);
					 
					showReplyPage(replyCnt);
		});//end function
	}//end showList
	
    var pageNum = 1;
    var replyPageFooter = $(".panel-footer");
    
    function showReplyPage(replyCnt) {
    	var endNum = Math.ceil(pageNum / 10.0) * 10;
    	var startNum = endNum - 9;
    	
    	var prev = startNum != 1;
    	var next = false;
    	
    	if(endNum * 10 >= replyCnt) {
    		endNum = Math.ceil(replyCnt/10.0);
    	}
    	
    	if(endNum * 10 < replyCnt) {
    		next = true;
    	}
    	
    	var str = "<ul class='pagination pull-right'>";
    	
    	if(prev) {
    		str+= "<li class='page-item'><a class='page-link' href='"+(startNum -1)+"'>Previous</a></li>";
    	}
    	
    	for(var i = startNum ; i <= endNum; i++) {
    		var active = pageNum == i? "active":"";
    		
    		str+= "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
    	}
    	
    	if(next) {
    		str+= "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li>";
    	}
    	
    	str += "</ul></div>";
    	
    	console.log(str);
    	
    	replyPageFooter.html(str);
    }
     
    replyPageFooter.on("click","li a", function(e) {
    	e.preventDefault();
    	console.log("page click");
    	
    	var targetPageNum = $(this).attr("href");
    	
    	console.log("targetPageNum: " + targetPageNum);
    	
    	pageNum = targetPageNum;
    	
    	showList(pageNum);
     });     
	
    var modal = $(".modal");
    var modalInputReplyText = modal.find("input[name='replytext']");
    
    var modalInputReplyer = modal.find("input[name='replyer']");
    var modalInputReplyDate = modal.find("input[name='replyDate']");
    
    var modalModifyBtn = $("#modalModifyBtn");
    var modalRemoveBtn = $("#modalRemoveBtn");
    var modalRegisterBtn = $("#modalRegisterBtn");
    
    var username = "<c:out value='${member.username}'/>";
    
    $("#modalCloseBtn").on("click", function(e){
    	modal.modal("hide");
    });
    
    
    $("#addReplyBtn").on("click", function(e){
    	modal.find("input[name='replytext']").val(""); 
    	modal.find("input[name='replyer']").val("${member.username}"); 
    	/* modal.find("input").val(""); */
    	modalInputReplyDate.closest("div").hide();
    	modal.find("button[id !='modalCloseBtn']").hide();
    	
    	modalRegisterBtn.show();
    	
    	$(".modal").modal("show");
    });
    
    // 새로운 댓글 처리
    modalRegisterBtn.on("click", function(e) {
    	var reply = {
    			replytext: modalInputReplyText.val(),
    			replyer:modalInputReplyer.val(),
    			contextPath:"${contextPath}",
    			bno:bnoValue
    		};
    	
        replyService.add(reply, function(result){
        	alert(result);
        	
        	/* modal.find("input").val(""); */
        	modal.find("input[name='replytext']").val(""); 
        	  modal.find("input[name='replyer']").val("${member.username}"); 
        	modal.modal("hide"); 
        	
        	showList(-1);
        });
	});
    
    $(".chat").on("click", "li", function(e){
    	var reply = {
    		rno : $(this).data("rno"),
    		contextPath:"${contextPath}",
    	};
	
    	replyService.get(reply, function(reply){
    		modalInputReplyText.val(reply.replytext);
    		modalInputReplyer.val(reply.replyer);
    		modalInputReplyDate.val(replyService.displayTime(reply.regdate)).attr("readonly","readonly");
    		modal.data("rno", reply.rno);
    		
    		
    		modal.find("button[id !='modalCloseBtn']").hide();
    		
    		if (reply.replyer == username) {
    		modalModifyBtn.show();
    		modalRemoveBtn.show();
    		
    		$(".modal").modal("show");
			} else{
				
    		 modalModifyBtn.hide();
    		modalRemoveBtn.hide(); 
    		
    		$(".modal").modal("show");
			}
    	});
    });
/*     $(".chat").on("click", "li", function(e){
    	var reply = {
    		rno : $(this).data("rno"),
    		contextPath:"${contextPath}",
    	};
	
    	replyService.get(reply, function(reply){
    		modalInputReplyText.val(reply.replytext);
    		modalInputReplyer.val(reply.replyer);
    		modalInputReplyDate.val(replyService.displayTime(reply.regdate)).attr("readonly","readonly");
    		modal.data("rno", reply.rno);
    		
    		 alert(modalInputReplyText.val(reply.replytext)); 
    		
    		modal.find("button[id !='modalCloseBtn']").hide();
    		
    		 if (modalInputReplyer.val(reply.replyer)  username) {
    		modalModifyBtn.hide();
    		modalRemoveBtn.hide();			
			} 
    		 modalModifyBtn.show();
    		modalRemoveBtn.show(); 
    		
    		$(".modal").modal("show");
    	});
    }); 
    */
    
    modalModifyBtn.on("click", function(e){
    	var reply = {
    			rno:modal.data("rno"), 
    			replytext: modalInputReplyText.val(),
    			contextPath:"${contextPath}"
    		};
    	
    	replyService.update(reply, function(result) {
    		alert(result);
    		
     	    modal.modal("hide");
     	    showList(pageNum);
     	});
    });

    modalRemoveBtn.on("click", function (e) {
    	var reply = {
    			rno : modal.data("rno"), 
    			contextPath:"${contextPath}"
    		};
    	
    	replyService.remove(reply, function(result) {
    		alert(result);
    		
    		modal.modal("hide");
    		showList(pageNum);
    	});
    });
});
</script>
<script type="text/javascript">
$(document).ready(function() {
	var operForm = $("#operForm"); 
		$("button[data-oper='modify']").on("click", function(e){
	  	operForm.attr("action","${contextPath}/board/modify").submit();
	});
	
	$("button[data-oper='list']").on("click", function(e){
		operForm.find("#bno").remove();
		operForm.attr("action","${contextPath}/board/list")
		operForm.submit();
	});
});
</script>

<script>
$(document).ready(function() {
	  (function() {
	  	var bno = "${board.bno}";
	    
	    $.getJSON("${contextPath}/board/getAttachList", {bno: bno}, function(arr) {
	        
	       console.log(arr);
	       
	       var str = "";
	       
	       $(arr).each(function(i, attach){
	       
	         if(attach.filetype){
	           var fileCallPath =  encodeURIComponent(attach.uploadPath+ "/s_" + attach.uuid +"_" + attach.fileName);
	           
	           str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.filetype + "' ><div>";
	           str += "<img src='${contextPath}/display?fileName=" + fileCallPath+"'>";
	           str += "</div>";
	           str +"</li>";
	         } else {
	           str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.filetype+"' ><div>";
	           str += "<span> "+ attach.fileName + "</span><br/>";
	           str += "<img src='${contextPath}/resources/img/attach.png'></a>";
	           str += "</div>";
	           str +"</li>";
	         }
	       });
	       
	       $(".uploadResult ul").html(str);
	     });//end getjson
	  })();//end function
	  
	  $(".uploadResult").on("click","li", function(e){
	      
	    console.log("view image");
	    
	    var liObj = $(this);
	    
	    var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));

	    if(liObj.data("type")){
	      showImage(path.replace(new RegExp(/\\/g),"/"));
	    } else {
	      self.location ="${contextPath}/download?fileName=" + path;
	    }
	    
	    
	  });
	  
	  function showImage(fileCallPath){
	    $(".bigPictureWrapper").css("display","flex").show();
	    
	    $(".bigPicture")
	    .html("<img src='${contextPath}/display?fileName="+fileCallPath+"' >")
	    .animate({width:"100%", height: "100%"}, 1000);
	    
	  }

	  $(".bigPictureWrapper").on("click", function(e){
	    $(".bigPicture").animate({width:"0%", height: "0%"}, 1000);
	    setTimeout(function(){
	      $(".bigPictureWrapper").hide();
	    }, 1000);
	  });
	});

</script>
<%@include file="/resources/includes/footer.jsp"%>
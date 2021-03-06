<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>

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

  <script src="/resources/awesomejump/js/jquery-1.12.1.min.js"></script>
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

  <!-- Swiper -->
  <link rel="stylesheet" href="/resources/awesomejump/css/swiper.min.css">
  <script src="/resources/awesomejump/js/swiper.min.js"></script>
 
  <script type="text/javascript">
	  function allCheck(){
		  var chk = $("#allCheck").prop("checked");
		  if(chk) {
		   $(".chBox").prop("checked", true);
		  } else {
		   $(".chBox").prop("checked", false);
		  }
	  }

	  function deleteinter(pno) {
		  var CSRFheader = $("meta[name='_csrf_header']").attr('content');
		  var CSRFtoken = $("meta[name='_csrf']").attr('content');
		  
		  $.ajax({
				type : "POST",
				url : "/deleteInter",
				dataType : "json",
				data : "pno=" + pno +"&mem_no=" + ${mem_no},
				beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken), 
				success : function(data) {
					
					if (data.message == "1") {
						alert("????????? ??????????????? ?????????????????????.");
						 location.reload();
					}else{
						alert("???????????? ??????????????????");
						
					}
				},
				fail : function() {
					alert("???????????????");
				}
			}); 
	}
	  
	  function selectDelete() {
			var checkArr = [];
			
			  var CSRFheader = $("meta[name='_csrf_header']").attr('content');
			  var CSRFtoken = $("meta[name='_csrf']").attr('content');
			  
			$("input[class='chBox']:checked").each(function(){
				checkArr.push($(this).attr("data-cartNum"));
			});
			
			 $.ajax({
					type : "POST",
					url : "/selDeleteInter",
					dataType : "json",
					asyns: false,
					data : {
						"pno" : checkArr,
						"mem_no" : ${mem_no}
					},
					beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken), 
					success : function(data) {
						if (data.message == "1") {
							alert("????????? ??????????????? ?????????????????????.");
							 location.reload();
						}else{
							alert("???????????? ??????????????????.");
							
						}
					},
					fail : function() {
						alert("???????????????");
					}
				}); 
		}
  </script>
  <script type="text/javascript">
  	$(document).ready(function(){
  		 var actionForm = $("#actionForm");
  		$(".paginate_button a").on("click", function(e) {
  			e.preventDefault(); // <a>????????? ???????????? ????????? ????????? ????????? ??????
  			
  			console.log("click");

  			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
  			actionForm.submit();
  		});
  	});
  </script>
  <style>
    body {
      min-height: 1000px;
    }  
    li a{
    	padding: 8px;
    }
    
    .selectDelete_btn {
        border: none;
   	 	border-radius: 20px;
    	padding: 10px 50px;
    }
    .inter_btn {
   		border: none;
   	 	border-radius: 20px;
    	padding: 5px 60px;
    	margin: 5px;
    	font-family: "NANUM";
	font-size: 15px;
    }
    
    .numCss {
    	border-bottom: 1px solid;
    }
    p{
	font-family: "NANUM";
	font-size: 15px;
	font-weight: bold;
	}
  </style>

</head>
<body>
  
  <jsp:include page="/WEB-INF/templates/awesomejump/header.jsp"/>
  
  <jsp:include page="/WEB-INF/templates/awesomejump/membersNavbar.jsp"/>

  <div class="container">
    <div>
    </div>
    <section class="cart_area padding_top" style="padding-top: 80px;">
    <div class="container">
      <div class="cart_inner" >
        <div class="table-responsive" style="overflow: hidden;">
          <table class="table">
            <thead>
              <tr>
              	<th style="width: 10%;"><input type="checkbox" name="allCheck" id="allCheck" class="allCheck" onclick="allCheck();"></th> 
                <th scope="col" style="width: 25%;">????????????</th>
                <th scope="col" style="width: 30%;">?????????</th>
                <th scope="col" style="width: 15%;">?????????</th>
                <th scope="col" style="width: 15%;">??????</th>
                <th scope="col" style="width: 15%;">??????????????????<th>
              </tr>
            </thead>
            <c:forEach items="${interest}" var="inter" >
	            <tbody>
	              <tr>
	              	<td>
	              		<input type="checkbox" name="chBox" id="chBox" class="chBox" data-cartNum="${inter.product_no}"/>
	              	</td>
	              	<td>
	                  <div>
	                      <img src="/upload${inter.product_thumb_img}" alt="" style="width: 150px; border-radius: 50px;"/>
	                  </div>
	                </td>
	                <td>
	                  <div class="media-body">
	                      <p>${inter.product_name}</p>
	                  </div>
	                </td>
	                <td>
	                  <h5>${inter.product_point}p</h5>
	                </td>
	                <td>
	                  <h5>${inter.product_price}???</h5>
	                </td>
	                <td>
	               		<input type="button" value="??????" class="inter_btn" onclick="location.href='/memDetailProduct?product_no=${inter.product_no}'"><br>
	                	<input type="button" value="??????" class="inter_btn"  onclick="deleteinter(${inter.product_no});"> 
	                </td>
	              </tr>
            </c:forEach>
	        </tbody>
          </table>
          <button type="button" class="selectDelete_btn" onclick="selectDelete();" style="font-family: 'NANUM';font-size: 15px;">????????????</button>
          <div class='pull-right' style="font-size: 25px;">
					<ul class="pagination" style="justify-content: center;">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous">
								<a href="${pageMaker.startPage -1}">&laquo;</a>
							</li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}"
							end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? 'numCss':''} ">
								<a href="${num}" >${num}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a
								href="${pageMaker.endPage +1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
				
			<form id='actionForm' action="/interest" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
			</form>   
        
        </div>
      </div>
     </div> 
  </section>
  </div>

  <jsp:include page="/WEB-INF/templates/awesomejump/footer.jsp"/>

</body>
</html>
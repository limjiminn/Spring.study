// Bootstrap tooltip init
$('[data-toggle="tooltip"]').tooltip()

// 배송 상태에 따른 글자색 변경
$(".detailDiv5").each(function() {
	var status = $(this).text();
	if 		(status == '주문 접수')   $(this).css("color", "#354A21");
	else if (status == '배송 진행중') $(this).css("color", "#3A5311");
	else if (status == '배송 완료')   $(this).css("color", "#028A0F");
});

// 배송 상태에 따른 버튼 css 변경 및 툴팁 표시 지정
$(".detailDiv6").each(function() {
	var status = $(this).siblings(".detailDiv5").text();
	var writeReviewBtn = $(this).children("button");
	if 		(status == '주문 접수')   writeReviewBtn.css("color", "gray");
	else if (status == '배송 진행중') writeReviewBtn.css("color", "gray");
	else if (status == '배송 완료')   {
		writeReviewBtn.css("color", "#555")
	    			  .css("font-weight", "bold")
	    			  .removeAttr("data-original-title");
	}
	
	if (writeReviewBtn.text() == '후기 완료') {
		writeReviewBtn.css("color", "#028A0F")
					  .attr("data-original-title", "상품 후기를 작성하셨습니다.")
					  .attr("data-toggle", "tooltip").attr("data-placement", "top");
	}
});

// 리뷰 작성 모달 팝업
$(".detailDiv6").on("click", ".writeReviewBtn", function() {
	var status = $(this).parent().siblings(".detailDiv5").text();
	var orderDetailReviewYn = $(this).data("orderDetailReviewYn");
	
	if (status != '배송 완료') 		return false;
	if (orderDetailReviewYn == 'y') return false;
	
	var orderDetailNo =      $(this).data("orderDetailNo");
	var productNo =          $(this).data("productNo");
	var productName = 		 $(this).data("productName");
	var productOptionColor = $(this).data("productOptionColor");
	var productOptionSize =  $(this).data("productOptionSize");
	var writeReviewDiv = 
	`
	<div class="modal fade" data-backdrop="static" id="writeReviewModal" tabindex="-1" aria-labelledby="writeReviewModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="writeReviewModalLabel">상품 리뷰 작성</h5>
	        <button type="button" class="close" onclick="destroyModal('#writeReviewModal')";>
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="d-flex align-items-center col-lg-8 offset-lg-2 p-0">
	      	  <p class="lead mr-2" style="min-width: 70px;">상품명 : </p>
	      	  <p>${productName} [${productOptionColor} / ${productOptionSize}]</p>
	      	</div>
	      	<div class="mt-2 col-lg-8 offset-lg-2 p-0">
	      	  <p class="lead">
	      	  	상세 내용 
	      	  	<span class="rateit float-right" data-rateit-mode="font" data-rateit-resetable="false" 
	      	  		  data-rateit-ispreset="true" data-rateit-value="5"
	      	  		  style="font-size:25px; top: 6px;">
	      	    </span>
	      	  </p>
              <textarea class="form-control reviewContent" placeholder="상품에 대한 의견을 적어 주세요."></textarea>
              <p class="lead mt-2">상품 이미지 업로드</p>
              <small class="mb-2" style="position: relative; bottom: 15px;">
              	상품 후기 사진은 10MB 이하 이미지(bmp, gif, jpg, jpeg, png)만 업로드 가능합니다.
              </small>
              <div class="row">
	            <input type="file" multiple name="uploadedImage" id="uploadedImage" class="ml-3" style="width: 200px;">
	            <button class="btn btn-primary uploadImageBtn" style="height: 27px; width: 70px; line-height: 0.8; font-size: 0.9rem;">업로드</button>
              </div>
	          <div class="imagePreviewWrapper d-flex flex-wrap my-3"></div>
            </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" onclick="destroyModal('#writeReviewModal');">취소</button>
	        <button type="button" class="btn btn-primary reviewSubmitBtn" 
	        		data-product-no="${productNo}"
	        		data-order-detail-no="${orderDetailNo}">
	          완료
	       	</button>
	      </div>
	    </div>
	  </div>
	</div>
	`;
	
	$(".orders").append(writeReviewDiv);
	$("#writeReviewModal").modal("toggle");
	setTimeout(() => $(".rateit").rateit({ step: 1 }), 250);
	
});

// 리뷰 이미지 업로드
$(document).on("click", ".uploadImageBtn", function() {
	
	var inputImage = $("input[name='uploadedImage']");
	var images = inputImage[0].files;
	var imageData = new FormData();
	
	Array.from(images).forEach(function(image) {
		if (!checkExtension(image.name, image.size)) return false;
		
		imageData.append('uploadedImage', image);
	})

	$.ajax({
		url: '/orders/review/upload',
		type: 'POST',
		processData: false,
		contentType: false,
		beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader, CSRFtoken),
		dataType: 'json',
		data: imageData,
		success: (data) => {
			$("input[name='uploadedImage']").val('');
			
			data.forEach(function(image) {
				var uploadedImagePreview = 
				`
				<div class="mx-2">
				  <img src="/upload/${image.imagePath}/s_${image.imageUuid}_${image.imageName}">
				  <span class="uploadedImage position-relative"
				        data-image-path="${image.imagePath}"
				        data-image-name="${image.imageName}"
				        data-image-type="${image.imageType}"
				        data-image-uuid="${image.imageUuid}">
				    <i class="fas fa-times position-absolute removeUploadedImage" 
				       style="color: #c30101; font-size: 1.1rem;"></i>
				  </span>
				</div>
				`;
				
				$(".imagePreviewWrapper").append(uploadedImagePreview);
			});
		} 
	});
});

// 리뷰 이미지 삭제
$(document).on("click", ".removeUploadedImage", function() {
	var imagePath = $(this).closest("span").data("imagePath");
	var imageName = $(this).closest("span").data("imageName");
	var imageType = $(this).closest("span").data("imageType");
	var imageUuid = $(this).closest("span").data("imageUuid");
	
	$.ajax({
		url: '/orders/review/remove',
		type: 'POST',
		contentType: 'application/json',
		beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader, CSRFtoken),
		data: JSON.stringify({
			imagePath: imagePath,
			imageName: imageName,
			imageType: imageType,
			imageUuid: imageUuid
		}),
		success: () => $(this).closest("div").remove()
	});
});

// 리뷰 작성 결과 전송
$(document).on("click", ".reviewSubmitBtn", function() {

	var orderDetailNo = $(this).data("orderDetailNo");
	var productNo =     $(this).data("productNo");
	var reviewContent = $(".reviewContent").val();
	var reviewGrade =   $(".rateit").rateit('value');
	var imageArray = [];
	
	$(".uploadedImage").each(function() {
		var imagePath = $(this).data("imagePath");
		var imageName = $(this).data("imageName");
		var imageType = $(this).data("imageType");
		var imageUuid = $(this).data("imageUuid");
		
		var image = {
			imagePath: imagePath,
			imageName: imageName,
			imageType: imageType,
			imageUuid: imageUuid
		};
		imageArray.push(image);
	});
	
	var reviewForm = $("<form></form>")
	reviewForm.attr("method", "POST")
			  .attr("action", "/orders/review/write");

	reviewForm.append($("<input/>", { type: "hidden", name: "_csrf",           value: CSRFtoken }))
			  .append($("<input/>", { type: "hidden", name: "order_detail_no", value: orderDetailNo }))
			  .append($("<input/>", { type: "hidden", name: "product_no",      value: productNo }))
			  .append($("<input/>", { type: "hidden", name: "review_content",  value: reviewContent }))
			  .append($("<input/>", { type: "hidden", name: "review_grade",    value: reviewGrade }))
			  .append($("<input/>", { type: "hidden", name: "imageArray",      value: JSON.stringify(imageArray) }));
	reviewForm.appendTo("body").submit();
});


// 모바일 화면에서의 주문 상세정보 아이콘 클릭시 기능 연결
$(".orderInfo").on("click", ".mobileOrderDetailBtn", function() {
	var orderDetailBtn = $(this).closest(".card").find(".orderDetailBtn");
	orderDetailBtn.trigger("click");
});

// 주문 상세 표시 모달
$(".orderInfo").on("click", ".orderDetailBtn", function(event) {
	event.stopPropagation();
	
	var orderInfoUid = 		   $(this).data("orderInfoUid");
	var orderInfoName = 	   $(this).data("orderInfoName");
	var orderInfoDate = 	   $(this).data("orderInfoDate");
	var orderInfoPrice = 	   $(this).data("orderInfoPrice");
	var orderInfoApproval =    $(this).data("orderInfoApproval");
	var orderInfoReceiveName = $(this).data("orderInfoReceiveName");
	var orderInfoPhone =       $(this).data("orderInfoPhone");
	var orderInfoPostcode =    $(this).data("orderInfoPostcode");
	var orderInfoAddress =     $(this).data("orderInfoAddress");
	var orderInfoRequest =     $(this).data("orderInfoRequest");
	
	var orderDetailDiv = 
	`
	  <div class="modal fade" data-backdrop="static" id="orderDetailModal" tabindex="-1" aria-labelledby="orderDetailModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <h5 class="modal-title" id="orderDetailModalLabel">상세 정보</h5>
	          <button type="button" class="close" onclick="destroyModal('#orderDetailModal')";>
	            <span aria-hidden="true">&times;</span>
	          </button>
	        </div>
	        <div class="modal-body">
	          <dl>
	          	<dt>주문 번호</dt>
	          	<dd>${orderInfoUid}</dd>
	          	<dt>주문 항목</dt>
	          	<dd>${orderInfoName}</dd>
	          </dl>
	          <dl class="row">
	          	<dt class="col-6 col-md-5">주문 날짜</dt>
	          	<dd class="col-6 col-md-7">${orderInfoDate}</dd>
	          	<dt class="col-6 col-md-5">결제 금액</dt>
	          	<dd class="col-6 col-md-7">${orderInfoPrice}</dd>
	          	<dt class="col-6 col-md-5">카드 승인 번호</dt>
	          	<dd class="col-6 col-md-7">${orderInfoApproval}</dd>
	          	<dt class="col-6 col-md-5">수령자 이름</dt>
	          	<dd class="col-6 col-md-7">${orderInfoReceiveName}</dd>
	          	<dt class="col-6 col-md-5">수령인 전화번호</dt>
	          	<dd class="col-6 col-md-7">${orderInfoPhone}</dd>
	          	<dt class="col-6 col-md-5">배송지 우편번호</dt>
	          	<dd class="col-6 col-md-7">${orderInfoPostcode}</dd>
	          </dl>
	          <dl>
	          	<dt>배송지 주소</dt>
	          	<dd>${orderInfoAddress}</dd>
	          	<dt>주문 요청사항</dt>
	          	<dd>${orderInfoRequest}</dd>
	          </dl>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-primary" onclick="destroyModal('#orderDetailModal');">확인</button>
	        </div>
	      </div>
	    </div>
	  </div>
	`;
	
	$(".orders").append(orderDetailDiv);
	$("#orderDetailModal").modal("toggle");
});

// 모달이 닫히면 제거
function destroyModal(modal) {
	$(modal).modal("toggle");
	setTimeout(() => $(modal).remove(), 500);
}

// 업로드 이미지 파일 정규식
var checkImage = RegExp(/\.(bmp|gif|jpg|jpeg|png)$/);
var maxSize = 10 * 1024 * 1024;

// 업로드 이미지 파일 용량 및 확장자 확인
function checkExtension(fileName, fileSize) {

	if (!checkImage.test(fileName)) {
		alert("이미지 파일만 등록할 수 있습니다.");
		return false;
	}
	if (fileSize >= maxSize) {
		alert("이미지 사이즈는 10MB를 넘을 수 없습니다.");
		return false;
	}

	return true;
}
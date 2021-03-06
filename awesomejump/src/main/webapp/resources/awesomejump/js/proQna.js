/**
 * 
 */
 console.log("Product Qna Module.......");
 
 var proQnaService = (function() {
	var CSRFheader = $("meta[name='_csrf_header']").attr('content');
	var CSRFtoken = $("meta[name='_csrf']").attr('content');
	
	function add(qna, callback, error) {
		console.log("add Qna...........");
		
		$.ajax({
			type : 'post',
			url : '/awesomejump/new',
			data : JSON.stringify(qna),
			beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken), 
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		})
	}
	
	function getList(param, callback, error) {
		
		var product_no = param.product_no;
		var page = param.page || 1;
		
		$.getJSON("/awesomejump/pages/" + product_no + "/" + page + ".json",
			function(data) {
				if (callback) {
					callback(data);
				}
			}).fail(function(xhr, status, err) {
				if (error) {
					error();
				}
			});
	}
	
	function remove(pro_qna_no, callback, error) {
		$.ajax({
			type : 'delete',
			url : '/awesomejump/' + pro_qna_no,
			beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken),
			success : function (deleteResult, status, xhr) {
				if (callback) {
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		});
	}
	
	function update(pro_qna, callback, error) {
		console.log("pro_qna_no : " + pro_qna.pro_qna_no);
		
		$.ajax({
			type : 'put',
			url : '/awesomejump/' + pro_qna.pro_qna_no,
			beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader,CSRFtoken),
			data : JSON.stringify(pro_qna),
			contentType : "application/json; charset=utf-8",
			success : function (result, status, xhr) {
				if (callback) {
					callback(result);
				}
			},
			error : function(request, xhr, status, er) {
				if (error) {
					error(er);
				}
					alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
	
	function get(pro_qna_no, callback, error) {
		$.get("/awesomejump/" + pro_qna_no + ".json", function(result) {
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr,status,err) {
			if(error) {
				error();
			}
		});
	}
	
	function displayTime(timeValue) {
		var today = new Date();
		var gap = today.getDate() - timeValue;
		
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000 * 60 * 60 * 24)) {
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [(hh > 9 ? '' : '0') + hh, ':', (mi > 9 ? '' : '0') + mi, ':', (ss > 9 ? '' : '0') + ss].join('');
		} else {
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth() + 1;
			var dd = dateObj.getDate();
			
			return [yy, '/', (mm > 9 ? '' : '0') + mm, '/', (dd > 9 ? '' : '0') + dd].join('');
		}
	}
	;
	
	//???????????? ????????? ??????
/*	var passwordCheckResult = false;
	$.ajax({
		url: "/awesomejump/qna/passwordCheck",
		type : "POST",
		async: false,
		data : JSON.stringify({
			qna_pw: currentPassword,
			qna_no: qna_no
		}),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		beforeSend: (xhr) => xhr.setRequestHeader(CSRFheader, CSRFtoken),
		success: (data) => {
			if (data.checkResult === 0) {
				$("#input_qna_pw").focus();
				$("#vm-qna_pw").text("??????????????? ???????????? ????????????.")
							   .attr("class", "text-danger").shake();	
			}
			if (data.checkResult === "") {
				$("#input_qna_pw").focus();
				$("#vm-qna_pw").text("??????????????? ????????? ?????????.")
							   .attr("class", "text-danger").shake();
			}
			if (data.checkResult === 1) {
				passwordCheckResult = true;
			}
		}
	});
	
	if (passwordCheckResult === false) { return false; }
	
	if (currentPassword == newPassword) {
		$("#vm-mem_new_pw").text("?????? ??????????????? ???????????????.").shake();
		$("#modify-mem_confirm_new_pw").val("");
		$("#modify-mem_new_pw").val("").focus();
		return false;
	}
	
	$("form[name='readQna']").submit();*/
	
	return {
		add:add,
		getList : getList,
		remove : remove,
		update : update,
		get : get,
		displayTime : displayTime
		};
})();
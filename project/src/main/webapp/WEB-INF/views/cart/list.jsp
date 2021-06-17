<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<%@ include file="/resources/includes/header.jsp" %>
<style>
	.media-object{
		width: 130px;
	}
</style>
<body onload="init();">
<script language="JavaScript">

var sell_price;
var amount;

function init () {
	sell_price = document.form.sell_price.value;
	amount = document.form.amount.value;
	document.form.sum.value = sell_price;
	change();
}

function add () {
	hm = document.form.amount;
	sum = document.form.sum;
	hm.value ++ ;

	sum.value = parseInt(hm.value) * sell_price;
}

function del () {
	hm = document.form.amount;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			sum.value = parseInt(hm.value) * sell_price;
		}
}

function change () {
	hm = document.form.amount;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
	sum.value = parseInt(hm.value) * sell_price;
}  
</script>

 <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>장바구니</h2>
          <ol>
            <li><a>Home</a></li>
            <li>장바구니</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th class="text-center">상품명</th>
                        <th class="text-center">개수</th>
                        <th class="text-center">가격</th>
                        <th class="text-center">총 가격</th>
                        <th> </th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                	<c:when test="${map.count == 0}">
                		<tr>
	                        <td class="col-sm-8 col-md-6">
                				<span style="text-align:right;">장바구니가 비었습니다.</span>
	                        </td>
	                    </tr>
                	</c:when>
                	<c:otherwise>
 		<form name="form" method="get">             			
                		<c:forEach items="${map.list}" var="map">
	                    <tr>
	                        <td class="col-sm-8 col-md-6">
	                        <div class="media">
	                            <a class="thumbnail pull-left" href="#"> <img class=media-object src="${ctx}/resources/assets/img/lager/${map.pimg}"> </a>
	                            <div class="media-body">
	                                <h4 class="media-heading"><a href="${ctx}/product/productdetail?pid=${map.pid}">${map.pname}</a></h4>
	                            </div>
	                        </div>
	                        </td>
	                        <%-- <td class="amount" style="text-align: center">
	                        <input type="email" class="form-control" id="exampleInputEmail1" value="${map.amount}">
	                       	 <input type="number" name="amount"  style="width:50px;" value="${map.amount}">
	                        </td>
	                        <td class="price"><strong>${map.pprice}</strong></td>
	                        <td class="total text-center">
	                        	<strong>${map.pprice* map.amount}</strong></td>
	                        <td class="col-sm-1 col-md-1">--%>
                                              
						<td>
							<input type=hidden name="sell_price" value="1000">
							<input type="text" name="amount" value="${map.amount}" size="3" onchange="change();"><br>
							<input type="button" value=" + " onclick="add();">
							<input type="button" value=" - " onclick="del();">
						</td>
						 <td class="price"><strong>${map.pprice}</strong></td>
						<td>
							<input type="text"  name="sum" size="11" readonly value="${map.money}" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px; width:50px;">원
						</td>
							<td>
	                        <button type="button" class="btn btn-danger" onclick="location.href='${ctx}/cart/delete?cartid=${map.cartid}'">
	                          삭제
	                        </button>
	                        </td> 
	                    </tr>
                		</c:forEach>
	   </form>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td><h3>Total</h3></td>
                        <td class="text-right"><h3><strong>${map.sumMoney}</strong></h3></td>
                    </tr>
                    <tr>
                        <td>   </td>
                        <td>   </td>
                        <td>   </td>
                        <td>	</td>
                    </tr>
                	</c:otherwise>
                			
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body> 
<%@ include file="/resources/includes/footer.jsp" %>
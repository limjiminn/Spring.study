<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath == '/' ? '' : pageContext.request.contextPath }" scope="application" />
<%@ include file="/resources/includes/header.jsp" %>
<<style>
	.media-object{
		width: 130px;
	}
</style>
<!DOCTYPE html>
 <section id="breadcrumbs" class="breadcrumbs">
      <div class="container">

        <div class="d-flex justify-content-between align-items-center">
          <h2>장바구니</h2>
          <ol>
            <li><a href="index.html">Home</a></li>
            <li>장바구니</li>
          </ol>
        </div>

      </div>
    </section><!-- End Breadcrumbs -->
<<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>개수</th>
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
                		<c:forEach items="${map.list}" var="map">
                			
	                    <tr>
	                        <td class="col-sm-8 col-md-6">
	                        <div class="media">
	                            <a class="thumbnail pull-left" href="#"> <img class=media-object src="${ctx}/resources/assets/img/lager/${map.pimg}"> </a>
	                            <div class="media-body">
	                                <h4 class="media-heading"><a href="${ctx}/product/productdetail?pid=${map.pid}">${map.pname}</a></h4>
	                            </div>
	                        </div></td>
	                        <td class="col-sm-1 col-md-1" style="text-align: center">
	                        <%-- <input type="email" class="form-control" id="exampleInputEmail1" value="${map.amount}"> --%>
	                        <input type="number" name="amount"  style="width:50px;" value="<fmt:formatNumber value="${map.amount}"  pattern="#,###,###" />">
	                        
	                        </td>
	                        <td class="col-sm-1 col-md-1 text-center"><strong>${map.pprice}</strong></td>
	                        <td class="col-sm-1 col-md-1 text-center"><strong>${map.pprice* map.amount}</strong></td>
	                        <td class="col-sm-1 col-md-1">
	                        <button type="button" class="btn btn-danger" onclick="location.href='${ctx}/cart/delete?cartid=${map.cartid}'">
	                          삭제
	                        </button></td>
	                    </tr>
                		</c:forEach>
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
                        <td>
                      <!--   <button type="button" class="btn btn-default">
                            쇼핑<span class="glyphicon glyphicon-shopping-cart"></span>
                        </button></td>
                        <td>
                        <button type="button" class="btn btn-success">
                            구매<span class="glyphicon glyphicon-play"></span>
                        </button></td> -->
                    </tr>
                	</c:otherwise>
                			
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%@ include file="/resources/includes/footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒拉--歷史訂單</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous" />
<script
	src="${pageContext.request.contextPath}/js/_04_order/historyOrder.js"></script>
<style>
.orderItems {
	background: rgba(80, 189, 138, 0.3);
}
</style>
</head>
<body>
	<div class="w-75 my-5 mx-auto">

		<ul class="nav nav-tabs nav-justified ">
			<li class="nav-item nav-link" id="all">全部</li>
			<li class="nav-item nav-link" id="waitShip">待出貨</li>
			<li class="nav-item nav-link" id="alreadyShip">已出貨</li>
			<li class="nav-item nav-link" id="finish">完成</li>
		</ul>

		<div class="container-fluid"
			style="border: 1px solid rgba(0, 0, 0, 0.575);">

			<div class="row border-bottom">
				<div class="col-1 d-flex justify-content-center align-items-center text-center py-2"></div>
				<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">訂單編號</div>
				<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">訂購日期</div>
				<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">出貨日期</div>
				<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">到貨日期</div>
				<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">訂單狀態</div>
				<div class="col-1 d-flex justify-content-center align-items-center text-center py-2"></div>
			</div>

			<c:forEach var="entry" items="${order_list}">
				<div class="row orderRow ${entry.status}">
					<div class="col-1 d-flex justify-content-center align-items-center text-center py-2"></div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">${entry.orderNo}</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">
						<fmt:formatDate value="${entry.orderDate}" pattern="yyyy-MM-dd" />
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">
						<fmt:formatDate value="${entry.shippingDate}" pattern="yyyy-MM-dd" />
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">
						<fmt:formatDate value="${entry.arriveDate}" pattern="yyyy-MM-dd" />
					</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">${entry.status}</div>
					<div class="col-1 d-flex justify-content-center align-items-center text-center py-2"></div>
				</div>

				<div class="row orderItems" style="display: none;">
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2"></div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">商品名稱</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">規格</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">單價</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">數量</div>
					<div class="col-2 d-flex justify-content-center align-items-center text-center py-2">總價</div>

					<c:forEach var="detailMap" items="${orderItem_map}">
						<c:if test="${detailMap.key==entry.orderNo}">
							<c:forEach var="detailEntry" items="${detailMap.value}">
								<div class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light py-2">
									<img
										src="${pageContext.request.contextPath}/init/getProductImage?id=${detailEntry.productId}"
										alt="" style="max-width: 100px;" />
								</div>
								<div
									class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light py-2">
									${detailEntry.productName}</div>
								<div
									class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light py-2">
									${detailEntry.formatContent1} ${detailEntry.formatContent2}</div>
								<div
									class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light py-2">
									$ ${detailEntry.unitPrice}</div>
								<div
									class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light py-2">
									${detailEntry.quantity}</div>
								<div
									class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light py-2">
									$ ${detailEntry.unitPrice*detailEntry.quantity}</div>
							</c:forEach>

						</c:if>
					</c:forEach>
					<div
						class="col-12 d-flex justify-content-end align-items-center border-top border-light py-2">
						<span style="font-size: 20px;">總金額: $ ${entry.totalPrice}</span>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_04_order/style.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/_04_order/productInfo.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="bg-light">

	<div class="top_area">

		<div class="logo">
			<a href="<c:url value='' />"> <img src="img/logo_transparent.png"
				style="width: 100px; height: 100px;" alt="">
			</a>
		</div>
		<div class="top-space"></div>

		<div>
			<a class="animateCart animated flash text-danger"
				href="<c:url value='' />"> <i class="fas fa-shopping-cart"></i>
			</a>
		</div>
		<div>
			<a class="nav-link dropdown-toggle text-dark"
				href="<c:url value='#' />" id="navbarDropdown" role="button"
				data-toggle="dropdown" aria-haspopup="true"> 登入 </a>

			<div class="dropdown-menu" aria-labelledby="navbarDropdown">
				<a class="dropdown-item" href="<c:url value='#' />">Another
					action</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="<c:url value='#' />">Something
					else here</a>
			</div>
		</div>
	</div>

	<ul id="a-style">
		<div class="test">
			<li class="dropdown"><i class="fas fa-bullhorn"></i> <a
				href="<c:url value='#' />">論壇</a>
				<div class="dropdown-content">
					<a href="<c:url value='#' />">惡魔版</a> <a href="<c:url value='#' />">天使版</a>
				</div></li>
			<li class="dropdown "><i class="fas fa-shopping-bag"></i> <a
				href="#news">商城</a>
				<div class="dropdown-content">
					<a href="<c:url value='#' />">購物區</a> <a href="<c:url value='#' />">購物車</a>
					<a href="<c:url value='#' />">歷史訂單</a>
				</div></li>
			<li class="dropdown"><i class="fas fa-user-friends"></i> <a
				href="<c:url value='#' />">關於我們</a>
				<div class="dropdown-content">
					<a href="<c:url value='#' />">創建理念</a> <a
						href="<c:url value='#' />">團隊介紹</a> <a href="<c:url value='#' />">關於我們</a>
				</div></li>
		</div>
	</ul>

	<div class="center-content">
		<!-- 		<div class="side_menu col-2"> -->

		<div class="side_menu col-2">


			<div>
				<h3>天使</h3>
			</div>
			<div>
				<a href="<c:url value='#' />"> 書籍</a>
			</div>
			<div>
				<a href="<c:url value='#' />">紓壓小物</a>
			</div>
			<div>
				<h3>惡魔</h3>
			</div>
			<div>
				<a href="<c:url value='#' />">書籍</a>
			</div>
			<div>
				<a href="<c:url value='#' />">紓壓小物</a>
			</div>
		</div>


		<div class="productBox col-10">
			<form action="" name="buyForm" method="Post">
				<div class="topProductBox">

					<div class="productImg col-5">
						<img
							src="${pageContext.request.contextPath}/init/getProductImage?id=${product.productId}"
							class="img-thumbnail">
					</div>
					<div class="itemSelect col-lg-6 col-sm-3 m-2">
						<div class="mt-2">
							商品名稱: ${product.productName} <span style="float: right;"><i
								class="fas fa-external-link-alt ml-5"></i></span>
						</div>
						<div class="mt-2">商品價格: ${product.price}</div>
						<div class="mt-3"></div>

						<div class="btn-group-vertical btn-group-toggle mb-5"
							data-toggle="buttons">${title1}
							<c:forEach var="entry" items="${content1}">
								<div
									class="btn btn-outline-secondary text-monospace mt-2 active">
									<input type="radio" name="content1" value="${entry}">${entry}
									
									<c:set var="content1" value="${param.content1}" scope="application"/>
									
									
								</div>
							</c:forEach>
						</div>

						<div class="btn-group-vertical btn-group-toggle"
							data-toggle="buttons">${title2}
							<c:forEach var="entry2" items="${content2}">
								<div
									class="btn btn-outline-secondary text-monospace mt-2 active">
									<input type="radio" name="content2" value="${entry2}">${entry2}
									
									<c:set var="content2" value="${param.content2}" scope="application"/>
								</div>
							</c:forEach>
						</div>

						<div class="mt-2">
							數量<input type="number" name="qty" value="1" min="1" max="9"
								class="ml-6 mt-2">
						</div>
						<div class="btn-group-sm ml-3 mb-3" role="group"
							aria-label="Basic example">
							<input type="button" class="butNow btn btn-dark mt-3"
								role="button" value="立即購買 " id="buyNow"
								onclick="buyForm.action='<c:url value="/order/checkOrder" />';buyForm.submit();">
							<input type='hidden' name='productId'
								value="${product.productId}"> <input type="button"
								class="btn btn-dark mt-3" id="joinCart" role="button"
								value="加入購物車"
								onclick="buyForm.action='<c:url value="/order/shoppingCart" />';buyForm.submit();">
						</div>


					</div>
					<!-- topProduct結束標前 -->
				</div>
			</form>
			<div class="productDesc">
				<p style="text-align: center;">
					<span>商品介紹</span>
				</p>
				<span>${detail}</span>
			</div>

		</div>

	</div>



	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous">
		
	</script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous">
		
	</script>
</body>
</html>
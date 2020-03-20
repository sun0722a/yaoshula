<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒啦--購物區</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_05_product/productList.css" />
</head>
<body>
	<div class="product">
		<div class="w-75 m-auto">

			<!-- 搜尋選擇列=================================== -->
			<form action="" id="searchForm">
				<div id="searchTop" class="row">
					<div class="input-group col-9 my-3">
						<div class="input-group-prepend">
							<span class="input-group-text" id="addon-wrapping"><img
								src="../../image/_05_product/search.png" /></span>
						</div>
						<input type="search" class="form-control" placeholder="關鍵字搜尋"
							name="search" aria-label="Recipient's username"
							aria-describedby="button-addon2" />
						<div class="input-group-append">
							<button class="btn btn-outline-secondary" type="button"
								id="button-addon2">搜尋</button>
						</div>
					</div>
					<div id="arrange" class="col-3 my-3">
						依 <select name="arrange">
							<option value="popular">熱門</option>
							<option value="time">最新</option>
						</select> 排列
					</div>
				</div>
			</form>
			<!-- products=================================== -->
			<div id="products">
				<div class="row">
					<c:forEach var="entry" items="${products_map}">
						<div class="col-12 col-sm-6 col-lg-4 mt-4">
							<div class="card border-dark">
								<img
									src="${pageContext.request.contextPath}/image/_05_product/香精油.jpg"
									class="card-img-top productImg" />
								<div class="card-body">
									<h5 class="card-title"
										style="text-align: center; font-size: 30px;">${entry.value.productName}</h5>
									<div class="card-text mt-2"
										style="text-align: center; font-size: 20px;">${entry.value.price}</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>
			<!-- 頁碼列=================================== -->
			<form action="" id="pageForm">
				<div id="pages">
					<button class="btPage" name="pageNo" value="${pageNo-1}"
						<c:if test="${pageNo==1}">style="visibility: hidden;"</c:if>>
						<img
							src="${pageContext.request.contextPath}/image/_05_product/上一頁.png"
							style="max-width: 90%;" />
					</button>
					<button class="btPage" name="pageNo" value="1"
						<c:if test="${pageNo==1}">style="visibility: hidden;"</c:if>>1</button>
					<span>．．．</span> <span>第</span> <select name="nowPage" id="nowPage">
						<c:forEach var="pages" begin="1" end="${totalPages}">
							<option value="${pages}"
								<c:if test="${pages==pageNo}"> selected </c:if>>${pages}</option>
						</c:forEach>
					</select> <span>頁</span> <span>．．．</span>
					<button class="btPage" name="pageNo" value="${totalPages}"
						<c:if test="${pageNo==totalPages}">style="visibility: hidden;"</c:if>>${totalPages}</button>
					<button class="btPage" name="pageNo" value="${pageNo+1}"
						<c:if test="${pageNo==totalPages}">style="visibility: hidden;"</c:if>>
						<img
							src="${pageContext.request.contextPath}/image/_05_product/下一頁.png"
							style="max-width: 90%;" />
					</button>
				</div>
			</form>
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
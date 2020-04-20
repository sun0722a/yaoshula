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

<script type="text/javascript">
	function checkDelete(productId, productName, searchStr) {
		$("#deleteProductModal").modal("show");
		var aSend = document.getElementById('aSend');
		modalBody = document.getElementById("modalBody");
		modalBody.innerHTML = "確認是否刪除<br> 編號：" + productId + " 名稱："
				+ productName + "之商品?";
		aSend.href = "<c:url value='/manager/addOrderDate?cmd=" + cmd + "&id="
				+ orderNo + "&searchStr=" + searchStr + "'/>"
		$("#deleteProductModal").modal("hide");
	}
</script>
<style>
</style>
</head>
<body>
	<div class="w-75 my-5 mx-auto">
		<form action="<c:url value='/manager/showProducts'/>">
			<div class="input-group my-3">
				<div class="input-group-prepend">
					<span class="input-group-text"><img
						src="../../image/_05_product/search.png" class="productImg" /></span>
				</div>
				<input type="search" class="form-control" placeholder="關鍵字搜尋"
					name="search" aria-label="Sizing example input"
					aria-describedby="inputGroup-sizing-default" />
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button"
						id="button-addon2">搜尋</button>
				</div>
			</div>
		</form>
		<div class="d-flex justify-content-end align-items-center">
			<a href="<c:url value=''/>"><input type="button" value="新增商品" /></a>
		</div>


		<ul class="nav nav-tabs nav-justified">
			<li class="nav-item"><a class="nav-link" href=""
				style="text-decoration: none; color: black;">天使</a></li>
			<li class="nav-item"><a class="nav-link" href=""
				style="text-decoration: none; color: black;">惡魔</a></li>
		</ul>

		<div class="row m-0">
			<div
				class="col-2 d-flex justify-content-center align-items-center text-center my-2"></div>
			<div
				class="col-2 d-flex justify-content-center align-items-center text-center my-2">
				商品編號</div>
			<div
				class="col-2 d-flex justify-content-center align-items-center text-center my-2">
				商品名稱</div>
			<div
				class="col-2 d-flex justify-content-center align-items-center text-center my-2">
				分類</div>
			<div
				class="col-2 d-flex justify-content-center align-items-center text-center my-2">
				價格</div>
			<div
				class="col-2 d-flex justify-content-center align-items-center text-center my-2"></div>

			<c:choose>
				<c:when test='${cmd=="angel"}'>
					<c:set var="categoryName" value="${article_map}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="categoryName" value="${comment_map}"></c:set>
				</c:otherwise>
			</c:choose>
			<c:forEach var="entry" items="${categoryName}">
				<div
					class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light my-2">
					<img src="../../image/_05_product/香精油.jpg" alt=""
						style="max-width: 100px;" />
				</div>
				<div
					class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light my-2">
					1</div>
				<div
					class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light my-2">
					香精油</div>

				<div
					class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light my-2">
					紓壓小物</div>
				<div
					class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light my-2">
					$ 5000</div>
				<div
					class="col-2 d-flex justify-content-center align-items-center text-center border-top border-light my-2">
					<i class='far fa-times-circle' style='font-size: 30px'
						onclick="checkDelete()"></i>
				</div>
			</c:forEach>

		</div>
	</div>


	<!-- 確認刪除商品 浮動視窗========== -->
	<div class="modal fade" id="deleteProductModal" tabindex="-1"
		role="dialog" aria-labelledby="deleteProductModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					確認是否刪除<br /> 編號：名稱：之商品?
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-primary" value="刪除"
						id="deleteProduct" />
				</div>
			</div>
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

	<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
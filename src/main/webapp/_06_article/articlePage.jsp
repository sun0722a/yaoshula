<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒啦--論壇首頁</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
</head>
<body>
	<div class="w-75 m-auto">
		<form action="" id="searchForm">
			<div id="searchTop" class="row">
				<div class="input-group my-3 col-9 ">
					<div class="input-group-prepend">
						<span class="input-group-text"><img
							src="${pageContext.request.contextPath}/image/_05_product/search.png"
							class="productImg" /></span>
					</div>
					<input type="search" class="form-control" placeholder="關鍵字搜尋"
						name="search" aria-label="Sizing example input"
						aria-describedby="inputGroup-sizing-default" />
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon2">搜尋</button>
					</div>
				</div>
				<div id="arrange"
					class="col-3 my-3 d-flex justify-content-end align-items-center">
					<span style="font-size: 10px;">排序：</span> <select name="arrange">
						<option value="time">最新</option>
						<option value="popular">熱門</option>
					</select>
				</div>
			</div>
		</form>
		<c:forEach var="entry" items="${articles_map}">
			<a href="">
				<div class="rounded-pill border">
					<div
						class="d-flex mx-auto justify-content-center align-items-center"
						style="text-align: center;">
						<a href="">檢舉</a> 
						<img src=""
							style="max-width: 200px; max-height: 100px;" /> 
							<img
							src="${pageContext.request.contextPath}/init/getUserImage?id=${entry.value.authorId}"
							class="rounded-circle border border-dark"
							style="height: 100px; width: 100px;" />
						<div class="ml-4 my-auto" style="height: 100px;">
							<div>${entry.value.title}
								${entry.value.category.categoryName}</div>
							<div>${entry.value.authorName}</div>
							<div>
								<fmt:formatDate value="${entry.value.publishTime}"
									pattern="yyyy-MM-dd" />
							</div>
						</div>
						<div class="d-flex">
							愛心：${article.likes} 留言數：
							<c:forEach var="entry" items="${entry.value.articleComments}" varStatus="number">
								<c:if test="number.last">${number}</c:if>
							</c:forEach>
						</div>
					</div>
				</div>
			</a>
		</c:forEach>
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
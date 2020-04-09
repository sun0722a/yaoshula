<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒啦--文章內容</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
</head>
<body>
	<div class="w-75 border">
		<div>
			<a href="">檢舉</a> <img
				src="${pageContext.request.contextPath}/init/getUserImage?id=${article.authorId}"
				class="rounded-circle border border-dark"
				style="float: left; height: 100px; width: 100px;">
			<div class="ml-4 my-auto" style="float: left; height: 100px;">
				<div>${article.title}${article.category.categoryName}</div>
				<div>${article.authorName}</div>
				<div>
					<fmt:formatDate value="${article.publishTime}"
						pattern="yyyy-MM-dd HH:mm" />
				</div>
			</div>
			<img
				src="${pageContext.request.contextPath}/init/getArticleImage?id=${article.articleId}"
				style="max-width: 200px; max-height: 100px;">
			<div class="" style="clear: both;">${content}</div>

			<div class="d-flex">
				愛心：${article.likes} 留言數：
				<c:choose>
					<c:when test="${not empty comments_set}">
						<c:forEach var="comments" items="${comments_set}"
							varStatus="number">
							<c:if test="${number.last}">${number.count}</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>0</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- 		留言區=========================================== -->
		<c:forEach var="entry" varStatus="number" items="${comments_set}">
			<hr>
			<img
				src="${pageContext.request.contextPath}/init/getUserImage?id=${entry.authorId}"
				class="rounded-circle border border-dark"
				style="float: left; height: 100px; width: 100px;">
			<div class="ml-4 my-auto" style="float: left; height: 100px;">
				<div>${entry.authorName}</div>
				<div>
					B${number.index+1}
					<fmt:formatDate value="${entry.publishTime}"
						pattern="yyyy-MM-dd HH:mm" />
				</div>
			</div>
			<div class="" style="clear: both;">${entry.content}</div>
		</c:forEach>
	</div>
	<!-- 	可留言處============================ -->
	<form method="POST" action="<c:url value='/article/AddComment'/>">
		<div class="border w-75 d-flex" style="position: fixed; bottom: 2%;">
			<img class="rounded-circle border-dark border my-auto"
				style="width: 50px; height: 50px;"
				src="${pageContext.request.contextPath}/init/getUserImage?id=${LoginOK.id}">
			<p>留言：</p>
			<textarea class="w-75" name="content" id="" cols="" rows="2"></textarea>
			<input class="ml-1 my-auto" type="submit" style="height: 40px;"
				value="送出">
		</div>
	</form>


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
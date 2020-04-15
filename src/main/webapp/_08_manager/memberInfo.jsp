<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒啦管理員--文章檢舉內容</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
</head>
<body>
	<div class="w-75 border my-5 mx-auto p-4">
		<div class="my-4 border border-dark p-4">
			<div class="d-flex">
				<img
					src="${pageContext.request.contextPath}/init/getUserImage?id=${article.authorId}"
					style="max-width: 200px; max-height: 100px;" />
				<div class="ml-4 my-auto" style="height: 100px;">
					<div>帳號 性別</div>
					<div>一般用戶</div>
					<div>刪除文章/留言：5</div>
				</div>
				<div class="ml-5">
					<a href=""><input type="button" value="封鎖帳號"></a> <a
						href=""><input type="button" value="解除封鎖"></a>
				</div>
			</div>
			<div>電話：</div>
			<div>地址：</div>
			<div>E-mail：</div>
		</div>
		<!-- 標頭============================= -->
		<div class="my-4 border border-dark p-4">
			<ul class="nav nav-tabs nav-justified">
				<li class="nav-item"><a class="nav-link" href=""
					style="text-decoration: none; color: black;">文章</a></li>
				<li class="nav-item"><a class="nav-link" href=""
					style="text-decoration: none; color: black;">留言</a></li>
				<li class="nav-item"><a class="nav-link" href=""
					style="text-decoration: none; color: black;">被刪除文章</a></li>
				<li class="nav-item"><a class="nav-link" href=""
					style="text-decoration: none; color: black;">被刪除留言</a></li>
			</ul>

			<!-- 文章留言列表============== -->

			<div class="row">
				<div
					class="col-2 d-flex justify-content-center align-items-center my-2">
					文章編號</div>
				<div
					class="col-4 d-flex justify-content-center align-items-center my-2">
					發文日期</div>
				<div
					class="col-4 d-flex justify-content-center align-items-center my-2">
					標題</div>
				<div
					class="col-2 d-flex justify-content-center align-items-center my-2">
					檢舉數</div>
			</div>

			<div class="scroll"
				style="height: 300px; overflow-y: scroll; overflow-x: hidden;">
				<c:forEach var="entry" items="${}">
					<a href="" style="text-decoration: none; color: black;">
						<div class="row">
							<div
								class="col-2 d-flex justify-content-center align-items-center my-2">
								1</div>
							<div
								class="col-4 d-flex justify-content-center align-items-center my-2">
								2020-03-27</div>
							<div
								class="col-4 d-flex justify-content-center align-items-center my-2">
								人生好難</div>
							<div
								class="col-2 d-flex justify-content-center align-items-center my-2">
								5</div>
						</div>
					</a>
				</c:forEach>
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
	<script
		src="${pageContext.request.contextPath}/js/_06_article/articleContext.js"></script>

</body>
</html>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<title>要抒啦--首頁</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/angel.css" />
 <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>

<body>
	
	<div class="top">
		<div class="logo"></div>
		<span class="option"> <a
			href="<c:url value='/_01_register/register.jsp' />">註冊</a>
		</span> 
		<span class="option"> 
			<c:choose>
			<c:when test="${ ! empty LoginOK || LoginOK.checkAuthSuccess == 'y' }">
			   <a href="<c:url value='/_02_login/logout.jsp' />">
  				登出 <i class="fas fa-sign-out-alt"></i>
	           </a>
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/_02_login/login.jsp'/>">
  				登入
	           </a>
			</c:otherwise>
			</c:choose>
		</span> 
		<span class="option"> <a
			href="<c:url value='/_03_personPage/personPage.jsp' />">個人頁面</a>
		</span><span class="option"> <a
			href="<c:url value='/article/ShowFamousArticles' />">熱門文章</a>
		</span><span class="option"> <a
			href="<c:url value='/_06_article/addArticle.jsp' />">新增文章</a>
		</span><span class="option"> <a
			href="<c:url value='/product/ShowPageProducts' />">購物區</a>
		</span><span class="option"> <a
			href="<c:url value='/product/ShowFamousProducts' />">熱門商品區</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/_04_order/shoppingCart.jsp' />">購物車</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/order/showHistoryOrder' />">歷史訂單</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/_07_letter/letterInfo' />">漂流信</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/manager/showReports' />">管理員_檢舉專區</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/manager/showMembers' />">管理員_帳號管理</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/manager/showOrders' />">管理員_訂單管理</a>
		</span>
		<span class="option"> <a
			href="<c:url value='/manager/showProducts' />">管理員_商品管理</a>
		</span>
		
	</div>
	<div class="main-menu">
		<div class="menu">
			<a href="<c:url value='/article/ShowPageArticles' />">論壇</a>
		</div>
		<div class="menu">
			<a href="">商城</a>
		</div>
		<div class="menu">
			<a href="">關於我們</a>
		</div>
	</div>

	<div class="search">
		<div class="search-post">
			<input type="text" placeholder="Search">
		</div>
		<div class="space"></div>
		<div class="options">
			<form action="">
				<select name="" id="select-menu">
					<option value="">Options</option>
					<option value="">Options1</option>
					<option value="">Options2</option>
				</select>
			</form>
		</div>
	</div>

	<div class="bigbox">
		<div class="left-menu">
			<div class="main-topic" style="margin: 10px;">天使版</div>
			<div class="topic">
				<a href="">全部</a>
			</div>
			<div class="topic">
				<a href="">生活</a>
			</div>
			<div class="topic">
				<a href="">感情</a>
			</div>
			<div class="topic">
				<a href="">工作</a>
			</div>
			<div class="topic">
				<a href="">時事</a>
			</div>
		</div>

		<div class="main-post">
			<c:if test="${not empty register}" >
				
			</c:if>
		</div>

		<div class="right-menu">
			<div class="post">
				<a href=""> <img src="" alt="">
				</a>新增文章
			</div>
			<div class="rank">
				<span>排行榜</span>
			</div>
		</div>
	</div>
	<div class="footer"></div>
</body>
</html>
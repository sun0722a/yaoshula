
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <title>要抒啦</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/angel.css" />
    
</head>
<body>
	<div class="top">
        <div class="logo"></div>
        <div class="login"></div>
    </div>
    <div class="main-menu">
        <div class="menu">
            <a href="${pageContext.request.contextPath}">論壇</a>
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
        <div class="topic"><a href="">全部</a></div>
        <div class="topic"><a href="">生活</a></div>
        <div class="topic"><a href="">感情</a></div>
        <div class="topic"><a href="">工作</a></div>
        <div class="topic"><a href="">時事</a></div>
    </div>

    <div class="main-post">

    </div>

    <div class="right-menu">
        <div class="post" >
            <a href="">
                <img src="" alt="">
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
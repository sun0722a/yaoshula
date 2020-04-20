<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value='/css/_07_letter/letters.css' /> ">
    <script src="letters.js"></script>
    <title>我的信件</title>
</head>
<body>
	 <div class="container">
        <div class="box">
            <div class="content">
                <h2>01</h2>
                <h3>天使<img src="<c:url value='/image/_07_letter/angel.png' /> " style="width: 30px;"></h3>
                <h3>寄信內容</h3>
                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Explicabo quae ullam accusamus itaque magnam laboriosam accusantium! Laborum, sit ipsa! Consectetur possimus molestiae, aspernatur amet ea dolores fuga nesciunt expedita voluptas?</p>
                <a class="wholeContent" href="#">看完整內容</a>
            </div>
                <div class="replyContent">
                    <h3>回信內容<img src="/img/replyIcon.svg" style="width: 30px;"></h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Id dolorum atque ad voluptates suscipit minima velit labore aspernatur molestias iure vel nam similique provident dicta, recusandae ipsa dolor odit? Soluta?Lorem ipsum dolor sit amet, consectetur adipisicing elit. Id dolorum atque ad voluptates suscipit minima velit labore aspernatur molestias iure vel nam similique provident dicta, recusandae ipsa dolor odit? Soluta?</p>
                    <a class="back" href="#">返回</a>   
                </div>
            
        </div>
        <div class="box">
            <div class="content">
                <h2>02</h2>
                <h3>惡魔<img src="<c:url value='/image/_07_letter/devil.png' /> " style="width: 30px;"></h3>
                <h3>寄信內容</h3>
                <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Explicabo quae ullam accusamus itaque magnam laboriosam accusantium! Laborum, sit ipsa! Consectetur possimus molestiae, aspernatur amet ea dolores fuga nesciunt expedita voluptas?</p>
                <a class="wholeContent" href="#">看完整內容</a>
            </div>
                <div class="replyContent">
                    <h3>回信內容<img src="/img/replyIcon.svg" width="30px"></h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Id dolorum atque ad voluptates suscipit minima velit labore aspernatur molestias iure vel nam similique provident dicta, recusandae ipsa dolor odit? Soluta?Lorem ipsum dolor sit amet, consectetur adipisicing elit. Id dolorum atque ad voluptates suscipit minima velit labore aspernatur molestias iure vel nam similique provident dicta, recusandae ipsa dolor odit? Soluta?</p>
                    <a class="back" href="#">返回</a>  
                </div>
            
        </div>
    </div>
</body>
</html>
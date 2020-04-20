<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/css/_07_letter/info.css' /> ">
<title>歡迎來到漂流瓶專區</title>
</head>
<body>
	<div class="container">
        <div class="info_img">
            <img src="<c:url value='/image/_07_letter/undraw_delivery_address_03n0.svg '/> " >
        </div>

        <div class="intro-container">
                <div>					
                <div class="formTop">	
                <img  class="letterImg" src="<c:url value='/image/_07_letter/undraw_blog_anyj.svg' /> ">
                    <h1>漂流信專區</h1>
                </div>
                <ul>
                    <li>會員每日可選擇寄一封信及回一封信，各限制250個字。</li>
                    <li>可以匿名的寄一封信，保證只會有一個人看到你的煩惱。</li>
                    <li>並依內心所希望的回覆類型(天使/惡魔)得到該有的回覆。</li>
                    <li>也可以匿名的回一封信，保證只有對方能得到你的回饋。</li>
                    <li>可以依照自己的能力與喜好選擇想要回覆哪類型的訊息。</li>
                    <li>如果成功幫助了對方，可以獲得對方給予的能量勳章。</li>
                    <li>也可從會員信箱看到自己曾經寄過及回過的完整信件。</li>
                </ul>
            <div class="btnGroup">
                <div class="btn"><a  href="<c:url value='/_07_letter/send.jsp' /> ">我想寄信</a></div>
                <div class="btn"><a  href="<c:url value='/_07_letter/reply.jsp' /> ">我想回信</a></div>
                <div class="btn"><a  href="<c:url value='/_07_letter/letters.jsp' /> ">我的信箱</a></div>
            </div>
           
        </div>
        </div>
    </div>
</body>
</html>
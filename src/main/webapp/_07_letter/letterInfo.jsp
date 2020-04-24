<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
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
                    <li>會員每日可選擇寄一封信及回一封信</li>
                    <li>保證只會有一個人看到你的煩惱或回覆。</li>
                    <li>可選擇該信件所呈現的主題(天使or惡魔)</li>
                    <li>如果成功幫助了對方，可以獲得對方給予的能量勳章。</li>
                    <li>也可從會員信箱看到自己曾經寄過及回過的完整信件。</li>
                </ul>
            <div class="btnGroup">
<%--             	<c:if  > --%>
<!--             		<div class="btnError" style="display:none;">我想寄信</div> -->
<%--             	</c:if> --%>
<%--             	<c:if test="${replyError!=''}" > --%>
<!--             		<div class="btnError" style="display:none;">我想回信</div> -->
<%--             	</c:if> --%>

            	<c:choose>
            		<c:when test="${! empty sendError}">
            			<div class="btnError">我想寄信</div>
            		</c:when>
            		<c:otherwise>
            			<div class="btn" ><a  href="<c:url value='/_07_letter/send.jsp' /> ">我想寄信</a></div>
            		</c:otherwise>
            	</c:choose>
            	
           		<c:choose>
            		<c:when test="${! empty replyError}">
            			<div class="btnError">我想回信</div>
            		</c:when>
            		<c:otherwise>
            			<div class="btn" ><a  href="<c:url value='/_07_letter/reply.jsp' /> ">我想回信</a></div>
            		</c:otherwise>
            	</c:choose>	
<%--             	<div class="btn" ><a  href="<c:url value='/_07_letter/send.jsp' /> ">我想寄信</a></div> --%>
<%--             	<div class="btn" ><a  href="<c:url value='/_07_letter/reply.jsp' /> ">我想回信</a></div> --%>
               	<div class="btn" ><a  href="<c:url value='/_07_letter/myletters' /> ">我的信箱</a></div>
               	<div class="btn" ><a  href="<c:url value='/index.jsp' /> ">回到首頁</a></div>
            </div>
           
        </div>
        </div>
    </div>
</body>
</html>
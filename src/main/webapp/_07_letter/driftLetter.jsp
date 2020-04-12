<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>漂流信</title>
<link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body class="bg-light">
    <div class="row justify-content-center content">
<!--         <form name="letterForm"> -->
	        <div class="heaven col-5">
	            <p id="send" class="text-center">我要寄信</p>
	            
	            <a type="button" class="btn btn-primary btn-lg btn-block" id="buttonSend" href='<c:url value="/_07_letter/sendLetter.jsp"  />' >Send</a>
	            
	        </div>
	
	
	        <div class="evil col-5">
	            <p id="reply" class="text-center">我要回信</p>
	            <a type="button" class="btn btn-primary btn-lg btn-block" id="buttonReply" href='<c:url value="/_07_letter/replyLetter.jsp"  />'>Reply</a>
	        </div>
<!--         </form> -->
    </div>
</body>
</html>
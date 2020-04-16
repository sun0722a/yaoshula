<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reply Letter</title>
</head>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<body>
	<div class="card col-5">
  <div class="card-body">
    <h5 class="card-title">${title}</h5>
    
    <p class="card-text">${content}</p>
    <a href="<c:url value='/_07_letter/goToReply.jsp' />" class="card-link">我要回覆</a>
<%--     <a href="<c:url value='/letter/replyLetter?type=${type}' />" class="card-link">再來一封</a> --%>
  </div>
</div>
</body>
</html>
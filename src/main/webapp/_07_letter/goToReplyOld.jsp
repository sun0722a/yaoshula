<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
</head>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@700&display=swap');

    body{
        font-family: 'Noto Sans TC', sans-serif;
        background: #fad2e2;
    }
</style>
<body>
<div class="card col-5">
  <div class="card-body">
    <h5 class="card-title">${title}</h5>
    <hr>
    <p class="card-text">${content}</p>
   
  </div>
</div>

	<form action="<c:url value='/letter/goToReply' />">
      
        <div class="form-group m-2">
            <textarea class="form-control col-6" name="replyContent" rows="10"
                required="required" placeholder="內文..." style="resize: none;"></textarea>
        </div>
        <button type="submit" class="btn btn-primary ml-2">送往信箱
        </button>
      </form>
</body>
</html>
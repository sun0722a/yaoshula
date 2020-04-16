<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
   
    <title>Document</title>
</head>
    <style>
         @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap');
        body{
            background: aliceblue;
            color: #0e2640;
             font-family: 'Noto Sans TC', sans-serif;
        }
        .enterBox{
            width: 300px;
            height: 200px;
            /* border: 1px solid #0e2640; */
            margin: 10px auto;
            border-radius: 3px;
        }
    </style>
<body>
	<form action="<c:url value='/findPassword' />" method="POST">
    <div class="enterBox">
        <div style="text-align: center;">請輸入註冊時的email</div>
        <div class="input-group-sm text-center">
            <input type="text" name="email" class="form-control-sm mt-3" placeholder="Email" aria-label="Username"  >
          </div>
          <div class="d-flex justify-content-center">
          <input type="submit" class="btn-sm btn-primary mt-3" role="button" >
        </div>
    </div>
	</form>

</body>
</html>
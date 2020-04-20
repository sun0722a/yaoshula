<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>漂流信</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_07_letter/style.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">
	<script
  src="https://code.jquery.com/jquery-3.5.0.min.js"
  integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
  crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/js/_07_letter/sendReply.js"></script>
</head>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@700&display=swap')
	;

body {
	font-family: 'Noto Sans TC', sans-serif;
}
</style>

<body class="bg-light">
	<div class="row justify-content-center content">
		
		
		<div class="heaven col-5">
			<p id="send" class="text-center">我要寄信</p>

			<a type="button" class="btn btn-primary btn-lg btn-block mb-3"
				id="buttonSend">Send</a>
				<div style="display: none ; color: white;" id="donotSend"
										class="errorText  ">今天已經寄過信囉!</div>
			<a type="button" class="btn btn-primary btn-lg btn-block mt-5"
				id="buttonSend">查看信件回覆</a>
			
		</div>
		
		<div class="evil col-5">
			<form action="<c:url value='/letter/replyLetter' /> ">
				<p id="reply" class="text-center">我要回信</p>
				<%-- 	            <a type="button" name="天使" class="btn btn-primary btn-lg col-5 ml-4" id="buttonReplyAngel" href='<c:url value="/_07_letter/replyLetter"  />'>Reply(天使區)</a> --%>
				<%-- 	            <a type="button" name="惡魔" class="btn btn-secondary btn-lg col-5" id="buttonReplyDemon" href='<c:url value="/_07_letter/replyLetter"  />'>Reply(惡魔區)</a> --%>
				<input type="submit" name="type"
					class="btn btn-primary btn-lg col-5 ml-5 toReply" class="buttonReply"
					value="天使" > <input type="submit" name="type"
					class="btn btn-secondary btn-lg col-5 toReply" class="buttonReply"
					value="惡魔" >
				<div style="display: none;" id="donotReply"
										class="errorText">今天已經回過信囉!</div>
			</form>
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
</body>
</html>
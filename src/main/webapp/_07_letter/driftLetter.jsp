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

</head>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@700&display=swap')
	;

body {
	font-family: 'Noto Sans TC', sans-serif;
}
</style>
<script>
	$(document).ready(function () {
	    $('.toReply').click(
	   $('#content').modal('show')
	);
	});
</script>
<body class="bg-light">
	<div class="row justify-content-center content">

		<div class="heaven col-5">
			<p id="send" class="text-center">我要寄信</p>

			<a type="button" class="btn btn-primary btn-lg btn-block"
				id="buttonSend" href='<c:url value="/letter/sendLetter"  />'>Send</a>

		</div>

		<div class="evil col-5">
			<form action="<c:url value='/letter/replyLetter' /> ">
				<p id="reply" class="text-center">我要回信</p>
				<%-- 	            <a type="button" name="天使" class="btn btn-primary btn-lg col-5 ml-4" id="buttonReplyAngel" href='<c:url value="/_07_letter/replyLetter"  />'>Reply(天使區)</a> --%>
				<%-- 	            <a type="button" name="惡魔" class="btn btn-secondary btn-lg col-5" id="buttonReplyDemon" href='<c:url value="/_07_letter/replyLetter"  />'>Reply(惡魔區)</a> --%>
				<input type="submit" name="type"
					class="btn btn-primary btn-lg col-5 ml-5 toReply" id="buttonReplyAngel"
					value="天使"> <input type="submit" name="type"
					class="btn btn-secondary btn-lg col-5 toReply" id="buttonReplyDemon"
					value="惡魔">
			</form>
		</div>
		
<%-- 		<c:if test="${! empty lb}" > --%>
<!-- 		<div class="modal fade" id="content" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true"> -->
<!-- 		    <div class="modal-dialog modal-dialog-centered" role="document"> -->
<!-- 		      <div class="modal-content"> -->
<!-- 		        <div class="modal-header"> -->
<!-- 		          <h5 class="modal-title" id="exampleModalCenterTitle">Modal title</h5> -->
<!-- 		          <button type="button" class="close" data-dismiss="modal" aria-label="Close"> -->
<!-- 		            <span aria-hidden="true">&times;</span> -->
<!-- 		          </button> -->
<!-- 		        </div> -->
<!-- 		        <div class="modal-body"> -->
<!-- 		          ... -->
<!-- 		        </div> -->
<!-- 		        <div class="modal-footer"> -->
<!-- 		          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 		          <button type="button" class="btn btn-primary">Save changes</button> -->
<!-- 		        </div> -->
<!-- 		      </div> -->
<!-- 		    </div> -->
<!--   </div> -->
<%-- 		</c:if> --%>
		
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
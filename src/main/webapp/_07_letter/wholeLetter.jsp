<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
</head>
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@700&display=swap');

    body{
        font-family: 'Noto Sans TC', sans-serif;
        background: #fad2e2;
    }
</style>
<script>
	$(document).ready(function(){
		
		
		$('.letterCard').mouseover(function(){
			$(this).css('background','#7D9ADF');
		})
		
		$('.letterCard').mouseout(function(){
			$(this).css('background','#fff');
		})
		
		
		    $('.btn').click(function(){
		    	$('#exampleModalCenter').modal('show')
		    });
		
	})
</script>
<body>
	
<!-- <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-hidden="true"> -->
<!--   <div class="modal-dialog modal-dialog-centered" role="document"> -->
<!--     <div class="modal-content"> -->
<!--       <div class="modal-header"> -->
<!--         <h5 class="modal-title" id="exampleModalCenterTitle">Modal title</h5> -->
<!--         <button type="button" class="close" data-dismiss="modal" > -->
<!--           <span aria-hidden="true">&times;</span> -->
<!--         </button> -->
<!--       </div> -->
<!--       <div class="modal-body"> -->
<%--         ${content} <br> --%>
<%--         ${reply} --%>
<!--       </div> -->
<!--       <div class="modal-footer"> -->
<!--         <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!--       </div> -->
<!--     </div> -->
<!--   </div> -->
<!-- </div> -->

	
<%-- <form action="<c:url value='/letter/getLetters' />" > --%>
<!--   <div class="letterBox list-group"> -->
<%--   <c:forEach var="lb"  items="${letter}"> --%>
<%--   <div class="col-4">${lb.value.letterTitle}  ${lb.value.letterCategory} --%>
<%--   <input type="hidden" name="letterId" value="${lb.key}"> --%>
<!--   <input type="submit" class="btn btn-primary" role="button"> -->
  
<%--   </c:forEach> --%>
<!-- </div> -->
<!--  </form>  -->
<!-- <div class="letterBox"> -->
<%-- <c:forEach var="lb"  items="${letter}"> --%>
<!-- <div class="card w-50"> -->
<!--   <div class="card-body"> -->
<%--     <h5 class="card-title">${lb.value.letterTitle}</h5> --%>
<!--     <p class="card-text"></p> -->
<!--     <input type="submit" class="btn btn-primary" > -->
<%--     <a href='<c:url value="/letter/getLetters?id=${lb.key}" />' class="btn btn-primary">Button</a> --%>
<!--   </div> -->
<!-- </div> -->
<%-- </c:forEach> --%>
<!-- </div> -->

<div class="card-deck d-flex justify-content-around mt-3">
<c:forEach var="lb"  items="${letter}">
  <div class="card col-2">
    <div class="card-body ">
      <h5 class="card-title">${lb.value.letterTitle}</h5>
      <p class="card-text">This is a longer card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
    </div>
  </div>
</c:forEach>
</div>

 <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>

</body>
</html>
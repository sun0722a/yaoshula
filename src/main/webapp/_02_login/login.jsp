
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<title>要抒啦--登入</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_02_login/login.css" />

<script type="text/javascript">
	//由<body>的onLoad事件處理函數觸發此函數
	function setFocusToUserId() {
		document.forms[0].userId.focus(); // 將游標放在userId欄位內
	}
</script>

</head>
<body onLoad="setFocusToUserId()">
	<c:set var="funcName" value="LOG" scope="session" />
	<jsp:include page="/fragment/topMenuTemp.jsp" />
	<c:if test="${ ! empty sessionScope.timeOut }">
		<!-- 表示使用逾時，重新登入 -->
		<c:set var="msg"
			value="<font color='red'>${sessionScope.timeOut}</font>" />
	</c:if>


	<!--     <div class="middle"> -->
	<div class="side_menu">
		<span><a href="">登入</a></span> <span><a href="">註冊</a></span>
	</div>

	<form class="box" action="<c:url value='/login.do' />" method="post"
		name="LoginForm">
		<h2>登入</h2>
		<div style="display: inline-block">
			<input type="text" name="userId" placeholder="帳號"
				value="${requestScope.user}${param.userId}"> &nbsp;<small><Font
				color='red' size="-3">${ErrorMsgKey.AccountEmptyError} </Font></small>
		</div>
		<div style="clear: both"></div>
		<div style="display: inline-block">
			<input type="password" name="password" placeholder="密碼"
				value="${requestScope.password}${param.pswd}"> &nbsp;<small><Font
				color='red' size="-3">${ErrorMsgKey.PasswordEmptyError} </Font></small>

		</div>

		<label class="rmButton" style="clear: both"> <input
			type="checkbox" name="rememberMe"
			<c:if test='${requestScope.rememberMe==true}' >      
                  checked='checked'  
               </c:if>
			value="true">記住我
		</label>
		<div style='font-size: 10px; color: red'>${ErrorMsgKey.LoginError}</div>
		<input type="submit" name="" value="登入">
	</form>
	<!-- 	</div> -->
	<!--     <div class="bottom" ></div> -->

</body>
</html>
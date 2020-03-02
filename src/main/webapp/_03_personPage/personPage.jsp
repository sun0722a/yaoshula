<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒拉--個人頁面</title>
</head>
<body>

	圖片:
	<img height='40px' width='30px'
		src='${pageContext.request.contextPath}/_00_init/getMemberImage?id=${LoginOK.id}'>
	<!-- 	經由session傳來 -->
	帳號:${LoginOK.name}
	<br> 性別: ${LoginOK.gender}
	<br> 生日:${LoginOK.birthday}
	<br> Email:${LoginOK.email}
	<br> 手機:${LoginOK.cellphone}
	<br> 地址:${LoginOK.address}
	<br>

</body>
</html>
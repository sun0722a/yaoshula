<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page2</title>
</head>
<body>
	<p>會員註冊</p>
<%--<p>帳號：${mIdPs.mid}</p> --%>
<%--<p>密碼：${mIdPs.password}</p> --%>
<%--<p>地址：${address.mAddress}</p> --%>

	<p>帳號：${id}</p>
	<p>密碼：${password}</p>
	<p>密碼確認：${passwordCheck}</p>
	<p>地址：${address}</p>
	<p>日期：${date}</p>
	<p>性別：${gender}</p>
	<p>E-MAIL：${Email}</p>
	<p>手機：${iPhone}</p>
	<p>程式語言：${langue}</p>
	<p>
		<a href="${pageContext.request.contextPath}/index.jsp">返回首頁</a>
	</p>
</body>
</html>
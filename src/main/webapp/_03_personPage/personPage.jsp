<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>要抒拉--個人頁面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/personPage.css" />
</head>
<body>
	<div class="personPage">
		<div>
			<img src="${pageContext.request.contextPath}/image/icons8-edit-144.png" id="edit" />
		</div>
		<div id="personHead">
			<img 
				src='${pageContext.request.contextPath}/_00_init/getMemberImage?id=${LoginOK.id}'
				id="headPicture">
		</div>
		<table>
			<tr>
				<td class="personalTitle">帳號：</td>
				<td class="personal">${LoginOK.name}</td>
			</tr>
			<tr>
				<td class="personalTitle">性別：</td>
				<td class="personal">${LoginOK.gender}</td>
			</tr>
			<tr>
				<td class="personalTitle">生日：</td>
				<td class="personal">${LoginOK.birthday}</td>
			</tr>
			<tr>
				<td class="personalTitle">E-mail：</td>
				<td class="personal">${LoginOK.email}</td>
			</tr>
			<tr>
				<td class="personalTitle">手機：</td>
				<td class="personal">${LoginOK.cellphone}</td>
			</tr>
			<tr>
				<td class="personalTitle">地址：</td>
				<td class="personal">${LoginOK.address}</td>
			</tr>
		</table>
	</div>
</body>
</html>
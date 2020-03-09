<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>要抒拉--個人頁面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personPage.css" />
<script src="${pageContext.request.contextPath}/js/updatePersonPage.js"></script>
</head>
<body>
	<form action="<c:url value='/PersonPage' />"
		method="POST" enctype='multipart/form-data' id="personForm">
		<div id="personPage">
			<div>
				<img
					src="${pageContext.request.contextPath}/image/icons8-edit-144.png"
					id="edit" />
			</div>
			<div id="boxHeadPicture">
				<img
					src="${pageContext.request.contextPath}/_00_init/getMemberImage?id=${LoginOK.id}"
					id="headPicture" />
			</div>
			<div id="boxFileSelect">
				<input name="memberMultipartFile" type="file"
					style="visibility: hidden;" id="fileSelect"
					value="${LoginOK.picture}" />
			</div>

			<table>
				<tr>
					<td class="personalTitle">帳號：&nbsp&nbsp</td>
					<td class="personal">${LoginOK.name}</td>
				</tr>
				<tr>
					<td class="personalTitle">性別：&nbsp&nbsp</td>
					<td class="personal">${LoginOK.gender}</td>
				</tr>
				<tr>
					<td class="personalTitle">生日：&nbsp&nbsp</td>
					<td class="personal">${LoginOK.birthday}</td>
				</tr>
				<tr>
					<td class="personalTitle">E-mail：&nbsp&nbsp</td>
					<td class="personalUpdate">${LoginOK.email}</td>
				</tr>
				<tr class="errorMsg">
					<td></td>
					<td>&nbsp&nbspE-mail欄位不能空白</td>
				</tr>
				<tr>
					<td class="personalTitle">手機：&nbsp&nbsp</td>
					<td class="personalUpdate">${LoginOK.cellphone}</td>
				</tr>
				<tr>
					<td class="personalTitle">地址：&nbsp&nbsp</td>
					<td class="personalUpdate">${LoginOK.address}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input id="btSubmit" type="submit" value="儲存"
								style="visibility: hidden;" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>

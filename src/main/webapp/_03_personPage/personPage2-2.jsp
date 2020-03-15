<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>要抒拉--個人頁面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personPageForm.css" />
<script
	src="${pageContext.request.contextPath}/js/updatePersonPageForm.js"></script>

<!-- 下拉式地址 -->
<script
	src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js"></script>
<script>
	new TwCitySelector();
</script>
<!-- 下拉式地址 -->

</head>
<body>
	<jsp:include page="/top/topMenuTemp.jsp" />

	<div class="side_menu">
		<span><a href="">個人頁面</a></span> <span><a href="">我的文章</a></span>
	</div>

	<form action="<c:url value='/PersonPage' />" method="POST"
		enctype='multipart/form-data' id="personForm">
		<div id="personPage">
			<div>
				<button id="btEdit" style="visibility: hidden;">
					<img
						src="${pageContext.request.contextPath}/image/icons8-edit-144.png"
						id="edit" />
				</button>
			</div>
			<div id="boxHeadPicture">
				<img
					src="${pageContext.request.contextPath}/_00_init/getMemberImage?id=${LoginOK.id}"
					id="headPicture" />
			</div>
			<div id="boxFileSelect">
				<input name="memberMultipartFile" type="file" id="fileSelect"
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
					<td class="personalTitle"><font color='red'>*&nbsp</font>E-mail：&nbsp&nbsp</td>
					<td class="personalUpdate"><input type="email" name="email"
						value="${LoginOK.email}" class="updateInput" required="required"
						placeholder="example@gmail.com" /></td>
				</tr>
				<tr>
					<td class="personalTitle">手機：&nbsp&nbsp</td>
					<td class="personalUpdate"><input type="text" name="phone"
						value="${LoginOK.cellphone}" class="updateInput"
						placeholder="0912345678" maxlength="10"
						onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
				</tr>
				<tr>
					<td class="personalTitle">地址：&nbsp&nbsp</td>
					<td class="personalUpdate">
						<div role="tw-city-selector" data-county-value="台北市"
							data-district-value="中正區" id="address"></div> <input type="text"
						name="address" value="${LoginOK.address}" class="updateInput" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<div align="center">
							<input id="btSubmit" type="submit" value="儲存" /> <input
								type="submit" id="btCancel" value="取消" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>

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
								style="visibility: hidden;" /> <input type="submit"
								id="btCancel" value="取消" style="visibility: hidden;"
								name="cancel" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>

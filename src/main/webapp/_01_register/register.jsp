<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8" />
<title>要抒啦--註冊</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/_01_register/register.css" />
<script
	src="${pageContext.request.contextPath}/js/_01_register/register.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<link rel="stylesheet"
	href="http://jqueryui.com/resources/demos/style.css" />

<script>
	let date = new Date();
	let year = date.getFullYear();
	let month = date.getMonth() + 1;
	let day = date.getDate();
	let now = year + "-" + month + "-" + day;
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange : "-150:+0",
			maxDate : now,
			dateFormat : "yy-mm-dd"
		});
	});
</script>

<!-- 下拉式地址 -->
<script
	src="https://cdn.jsdelivr.net/npm/tw-city-selector@2.1.0/dist/tw-city-selector.min.js"></script>

<script>
	new TwCitySelector();
</script>
<!-- 下拉式地址 -->
</head>
<body>
	<jsp:include page="/fragment/topMenuTemp.jsp" />

	<div class="side_menu">
		<span><a href="">個人頁面</a></span> <span><a href="">我的文章</a></span>
	</div>

	<form method="post" action="<c:url value='/register' />"
		enctype="multipart/form-data" id="registerForm">
		<!-- autocomplete="off"不要讓瀏覽器記住使用者輸入資料的歷史紀錄 -->
		<div id="registerPage">
			<div id="boxHeadPicture">
				<img
					src="${pageContext.request.contextPath}/image/_03_personPage/headPicture.jpg"
					id="headPicture" />
			</div>
			<div id="boxFileSelect">
				<input name="memberMultipartFile" type="file" id="fileSelect" />
			</div>

			<table>
				<tr>
					<td class="personalTitle"><font size="3" color="red">*</font>
						帳號：&nbsp&nbsp</td>
					<td class="tdInput"><input id="userName" name="userName"
						value="${mb.name}" placeholder="不能含有特殊字元" required="required"
						type="text" class="contentText"
						onkeyup="value=value.replace(/[\W]/g,'')" /></td>
					<td><input type="button" id="btUserName" value="檢查帳號" /></td>
				</tr>
				<tr>
					<td></td>
					<td class="errorText" id="userNameText">${errorMsg.errorId}</td>
				</tr>
				<tr>
					<td class="personalTitle"><font size="3" color="red">*</font>
						密碼：&nbsp&nbsp</td>
					<td class="tdInput"><input id="password" name="password"
						type="password" placeholder="長度為8~12字元" required="required"
						class="contentText" maxlength="15" minlength="8" /></td>
				</tr>
				<tr>
					<td class="personalTitle"><font size="3" color="red">*</font>
						密碼確認：&nbsp&nbsp</td>
					<td class="tdInput"><input id="passwordCheck"
						name="passwordCheck" type="password" placeholder="長度為8~12字元"
						class="contentText" maxlength="15" minlength="8" /></td>
				</tr>
				<tr style="display: none;" id="passwordError">
					<td></td>
					<td class="errorText">密碼確認錯誤</td>
				</tr>
				<tr>
					<td class="personalTitle"><font size="3" color="red">*</font>性別：&nbsp&nbsp
					</td>
					<td class="tdInput"><input type="radio" name="gender"
						value="男" required="required"
						<c:if test='${mb.gender=="男"}'>checked='checked'</c:if> />男 <input
						type="radio" name="gender" value="女" required="required"
						<c:if test='${mb.gender=="女"}'> checked='checked'</c:if> />女</td>
				</tr>
				<tr>
					<td class="personalTitle"><font size="3" color="red">*</font>生日：&nbsp&nbsp
					</td>
					<td class="tdInput"><input id="datepicker" name="birthday"
						placeholder="1911-01-01" value="${mb.birthday}" type="text"
						autocomplete="off" required="required" class="contentText" /></td>
				</tr>
				<tr>
					<td class="personalTitle"><font size="3" color="red">*</font>
						E-mail：&nbsp&nbsp</td>
					<td class="tdInput"><input id="email" name="email"
						placeholder="member@example.com" value="${mb.email}" type="text"
						required="required" class="contentText" /></td>
					<td><input type="button" id="btEmail" value="檢查信箱" /></td>
				</tr>
				<tr>
					<td></td>
					<td class="errorText" id="emailText">${errorMsg.errorEmail}</td>
				</tr>

				<tr>
					<td class="personalTitle">手機：&nbsp&nbsp</td>
					<td class="tdInput"><input name="phone"
						placeholder="0912345678" value="${mb.cellphone}" type="text"
						class="contentText" maxlength="10"
						onkeyup="value=value.replace(/[^\d]/g,'')" /></td>
				</tr>

				<tr>
					<td class="personalTitle">地址：&nbsp&nbsp</td>
					<td class="tdInput">
						<div role="tw-city-selector" id="address"></div> <input
						name="address" value="${mb.address}" type="text"
						class="contentText" />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<div align="center">
							<input id="btSubmit" type="submit" value="送出" /> 
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>

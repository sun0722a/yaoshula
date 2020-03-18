<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<title>要抒啦--註冊</title>
<!-- <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css"> -->
<!-- <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script> -->
<!-- <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script> -->
<!-- <link rel="stylesheet" href="jqueryui/style.css"> -->

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="./lib/padStartLibrary.js"></script>

<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker();
		console.log("#1 loading");
	});
</script>

<script type="text/javascript">
	function btclick() {
		if (!((document.getElementById("np").value)
				.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}$/))) {
			alert("your password : " + document.getElementById("np").value);
		}
	}
</script>
</head>
<body>


	<p>會員註冊</p>
	<form method="post" action="<c:url value='/register' />"
		enctype="application/x-www-form-urlencoded">
		<!-- id屬性 是給 JavaScript程式看的 -->
		<!-- name屬性 是給 後端 Servlet程式看的 -->
		<!-- autocomplete="off"不要讓瀏覽器記住使用者輸入資料的歷史紀錄 -->
		<!-- 		required="required" 會提示內容要輸入 -->
		<p>
			<font size="3" color="red">*</font> 帳號 : <input id='num' name="mId"
				value="" placeholder="不能有特殊字元" required="required" type="text"
				size="16"> ${ErrorMsg.id}
		</p>
		<p>
			<font size="3" color="red">*</font>
			<!-- 		密碼:<input id="np" type="password" name="np" id="np" class="content_text" /> -->
			密碼 : <input id="password" name="password" type="password"
				placeholder="此欄位不能空白" required="required" class="content_text">
			${ErrorMsg.password}
		</p>

		<p>
			<font size="3" color="red">*</font> 密碼確認:<input id="passwordCheck"
				name="passwordCheck" type="password" class="content_text">
			${ErrorMsg.passwordCheck}
		</p>

		<p>
			性別： <input type="radio" name="gender" value="male"
				required="required" />男 <input type="radio" name="gender"
				value="female" required="required" />女
		</p>


		<p>
			住址: <input id="mAddress" name="mAddress" value="" type="text"
				size="54">
		</p>

		<p>
			日期： <input type="text" id="datepicker" name="datepicker"
				autocomplete="off" required="required" />
		</p>

		<p>
			<%-- 								   value="${param.mWeight}" --%>
			<font size="3" color="red">*</font> E-mail : <input id="Email"
				name="Email" placeholder="此欄位不能空白" value="" type="text" size="14">
			${ErrorMsg.Email}
		</p>

		<p>
			<%-- 		<input name="mPhone" value="${param.mPhone}" type="text" size="20"></td>     --%>
			手機 : <input id="iphone" name="iPhone" value="" type="text" size="20">
		</p>
		<input type="submit" value="提交" /> <input type="reset" value="重設" />
	</form>

</body>
</html>
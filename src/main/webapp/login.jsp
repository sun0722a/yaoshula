
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <title>要抒啦</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" />
    
<script type="text/javascript">
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToUserId(){   
	 document.forms[0].userId.focus();   // 將游標放在userId欄位內
}
</script>
    
</head>
<body onLoad="setFocusToUserId()">
<c:set var="funcName" value="LOG" scope="session"/>
<c:if test="${ ! empty sessionScope.timeOut }" > <!-- 表示使用逾時，重新登入 -->
   <c:set var="msg" value="<font color='red'>${sessionScope.timeOut}</font>" />
</c:if>

    <div class="top_area">
        <div class="logo">
            <a href="">
                <img src="${pageContext.request.contextPath}/webImage/heart.png" alt="">
            </a>
        </div>
            <div class="login">
                <a href="">
                    <span>登入</span>
                </a>
            </div>
        
    </div>
        <div class="top">
            <ul class="main_menu">
                    <li class="main_menu_li"><a href="">
                            <img src="${pageContext.request.contextPath}/webImage/home.png">論壇</a>
                            <ul class="item_menu">
                                <li><a href="">天使</a>
                                </li>
                                <li><a href="">惡魔</a>
                                </li>
                                <li><a></a></li>
                            </ul>
                        
                    </li>

                    <li class="main_menu_li"><a href="">
                            <img src="${pageContext.request.contextPath}/webImage/market.png">商城</a>
                        <ul class="item_menu">
                            <li><a href="">購物區</a>
                            </li>
                            <li><a href="">購物車</a>
                            </li>
                            <li><a href="">歷史訂單</a>
                            </li>
                        </ul>

                    </li>
                    <li class="main_menu_li"><a href="" >
						<img src="${pageContext.request.contextPath}/webImage/about.png" style= "margin-right: 2px;" >關於我們</a>
                        <ul class="item_menu">
                            <li><a href="">創建理念</a>
                            </li>
                            <li><a href="">團隊介紹</a>
                            </li>
                            <li><a href="">關於我們</a>
                            </li>
                            </ul>
                    </li>
            </ul>
        </div>
    <div class="side_menu">
        <span><a href="">登入</a></span>
        <span><a href="">註冊</a></span>
    </div>

    <form class="box" action="<c:url value='/login/login.do' />" method="POST" >
    
<!--     name="LoginForm" -->
        <h2 >登入</h2>
        <div style="display: inline-block">
        <input type="text" name="userId" placeholder="帳號" 
         value="${param.userId}">
<%--          	${requestScope.user} --%>
		        <small><Font color='red' size="-3">${ErrorMsgKey.AccountEmptyError}
		            </Font></small>
         </div>
         <div style="clear:both"></div>
        <div style="display: inline-block">
            <input type="password" name="password" placeholder="密碼"
        value= "${param.password}" >    
<%--         ${requestScope.password} --%>
       <small><Font color='red'  size="-3">${ErrorMsgKey.PasswordEmptyError}
             </Font></small>
        </div>
        
        <label class="rmButton" style="clear:both">
            <input type="checkbox" name="rememberMe"
            <c:if test='${requestScope.rememberMe==true}' >      
                  checked='checked'  
               </c:if>
              
               value="true" >記住我
        </label>
        <div style= "display: inline-block">
         <small><Font color='red' size="-3">${ErrorMsgKey.LoginError}
             </Font></small>
        </div>
        <input type="submit" value="登入">
        	
    </form>
		
    <div style="clear: both;"></div>
    <div class="bottom" ></div>
    
</body>
</html>
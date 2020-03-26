<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/_04_order/cart.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/_04_order/cart.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head>
<style>
    a{
        color: black;
    }
    #a-style a{
        text-decoration: none;
    }
</style>

 <body class="bg-light">

        <div class="top_area">
            
            <div class="logo">
                <a href="<c:url value='#' />" >
                    <img src="img/logo_transparent.png" style="width: 100px; height: 100px;" alt="">
                </a>
            </div>
            <div class="top-space"></div>
            <a class="nav-link dropdown-toggle text-dark" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                登入
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="#">Another action</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<c:url value='#' />">Something else here</a>
              </div>
        </div>
        <ul id="a-style">
            <div class="test">
            <li class="dropdown ">
                <i class="fas fa-bullhorn"></i>
                <a href="#home">論壇</a>
                <div class="dropdown-content">
                    <a href="<c:url value='#' />" >惡魔版</a>
                    <a  href="<c:url value='#' />" >天使版</a>
                  </div>
            </li> 
            <li class="dropdown ">
                <i class="fas fa-shopping-bag"></i>
                <a  href="<c:url value='#' />" >商城</a>
                <div class="dropdown-content">
                    <a  href="<c:url value='#' />" >購物區</a>
                    <a  href="<c:url value='#' />" >購物車</a>
                    <a  href="<c:url value='#' />" >歷史訂單</a>
                  </div>
            </li>
            <li class="dropdown ">
            <i class="fas fa-user-friends"></i>
              <a  href="<c:url value='#' />" >關於我們</a>
              <div class="dropdown-content">
                <a  href="<c:url value='#' />" >創建理念</a>
                <a  href="<c:url value='#' />" >團隊介紹</a>
                <a  href="<c:url value='#' />" >關於我們</a>
              </div>
            </li>
        </div>
          </ul>

          <div class="cart-box">
            <div>購物車</div>
            <div class="items-menu" >
                <hr style="background: black;"> 
                <div class="cart-text">
                    <div class="text-items"><input type="checkbox" id="allCheck">全選</div>
                    <div class="text-items">商品名稱</div>
                    <div class="text-items">規格</div>
                    <div class="text-items">單價</div>
                    <div class="text-items">數量</div>
                    <div class="text-items">總價</div>
                    <div class="text-items"></div>
                </div>
                <hr style="background: black;">

           <section>
              <table>
        		<c:forEach var="cartMap" varStatus="vs" items="${ShoppingCart.content}">
                    <div class="items" >
                    <div class="items-detail" style="margin-bottom: 5px;"><input type="checkbox"><img src="img/product.jpg" style="width:80px;"></div>
                    <div class="items-detail">${cartMap.value.productName}</div>
                    <div class="items-detail"><select name="" id="" style="font-size:xx-small;">
                        <option value="">薰衣草50ml</option>
                    </select></div>
                    <div id="cost">${cartMap.value.price}</div>
                    <div class="amount" style="font-size: xx-small;"><input type="number" value="1" min="1" max="10"></div>
                    <div class="total">5000</div>
                    <div class="delete" ><a href="" style="font-size: small;">刪除</a></div>
                </div>
                </c:forEach> 
            </table>
            <hr style="background: black;">
        </section> 
<!--          <section> -->
<!--             <table> -->
      
<!--                   <div class="items"> -->
<!--                   <div class="items-detail" style="margin-bottom: 5px;"><input type="checkbox"><img src="img/product.jpg" style="width:80px;"></div> -->
<!--                   <div class="items-detail">香精油</div> -->
<!--                   <div class="items-detail"><select name="" id="" style="font-size:xx-small;"> -->
<!--                       <option value="">薰衣草50ml</option> -->
<!--                   </select></div> -->
<!--                   <div id="cost">5000</div> -->
<!--                   <div class="amount" style="font-size: xx-small;"><input type="number" value="1" min="1" max="10"></div> -->
<!--                   <div class="total">5000</div> -->
<!--               </div>  -->
<!--           </table> -->
<!--           <hr style="background: black;"> -->
<!--       </section>  -->


            
            <div class="check">
                <div class="check-kid">總金額</div>
                <div class="total">0</div>
                <div class="check-kid" id="submitBtn"><a href="/order/orderCheck">確認訂單</a></div>
            </div>
            <hr style="background: black;">
        </div>
        

        <div class="orderItem">
            
        </div>

    </div>


   
    
</body>
</html>
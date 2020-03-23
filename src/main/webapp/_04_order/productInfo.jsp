<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/_04_order/style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/_04_order/productInfo.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.7.2/animate.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
 <body class="bg-light">

        <div class="top_area">
            
            <div class="logo">
                <a href="<c:url value='' />">
                    <img src="img/logo_transparent.png" style="width: 100px; height: 100px;" alt="">
                </a>
            </div>
            <div class="top-space"></div>

                <a class="animateCart animated flash text-danger" href="<c:url value='shoppingCart.do' />" ><i class="fas fa-shopping-cart"></i></a>
                <a class="nav-link dropdown-toggle text-dark" href="<c:url value='#' />" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true">
                    登入
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value='#' />">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value='#' />">Something else here</a>
                </div>
        </div>
        <ul id="a-style">
            <div class="test ">
            <li class="dropdown">
                <i class="fas fa-bullhorn"></i>
                <a href="<c:url value='#' />">論壇</a>
                <div class="dropdown-content">
                    <a href="<c:url value='#' />">惡魔版</a>
                    <a href="<c:url value='#' />">天使版</a>
                  </div>
            </li> 
            <li class="dropdown ">
                <i class="fas fa-shopping-bag"></i>
                <a href="#news">商城</a>
                <div class="dropdown-content">
                    <a href="<c:url value='#' />">購物區</a>
                    <a href="<c:url value='#' />">購物車</a>
                    <a href="<c:url value='#' />">歷史訂單</a>
                  </div>
            </li>
            <li class="dropdown">
            <i class="fas fa-user-friends"></i>
              <a href="<c:url value='#' />">關於我們</a>
              <div class="dropdown-content">
                <a href="<c:url value='#' />">創建理念</a>
               <a href="<c:url value='#' />">團隊介紹</a>
               <a href="<c:url value='#' />">關於我們</a>
              </div>
            </li>
        </div>
          </ul>

          <div class="center-content">    
            <div class="side_menu">
                <div><h3>天使</h3></div>
                <div><a href="<c:url value='#' />"> 書籍</a></div>
                <div><a href="<c:url value='#' />">紓壓小物</a></div>
                <div><h3>惡魔</h3></div>
                <div><a href="<c:url value='#' />">書籍</a></div>
                <div><a href="<c:url value='#' />">紓壓小物</a></div>
            </div>
            <!-- <div class="column"> -->
                <div class="productBox">
                    <div class="topProductBox">		
                    <div class="productImg col-5"><img src="${pageContext.request.contextPath}/image/_04_order/product.jpg" class="img-thumbnail"></div>
                    <div class="itemSelect col-lg-6 col-sm-3 m-2">
                        <div class="mt-2">${product.name}
                            <span style="float: right;"><i class="fas fa-external-link-alt ml-5" ></i></span>
                        </div>
                        <div class="mt-2">${product.price}</div>
                        <div class="btn-group-sm mt-3" role="group">${product.categoryTitle}
                            <button type="button" class="btn btn-secondary text-monospace col-4 mr-2 ml-2">${product.categoryName}</button>
                            <button type="button" class="btn btn-secondary text-monospace col-4 ">${product.categoryName}</button>
                          </div>
                        <div class="mt-2">數量<input type="number" value="1" min="1" max="9" class="ml-3 mt-2"></div>
                        <div class="btn-group-sm" role="group" aria-label="Basic example">
                        <a type="button" class="butNow btn btn-dark mt-3" href="<c:url value='shoppingCart.do' />" role="button">立即購買</a>
                        <button type="button" class="btn btn-dark mt-3" id="joinCart">加入購物車</button>
                        </div>
                    </div>
                </div>
            
                <div class="productDesc">
                    <p style="text-align: center;"><span>商品介紹</span></p>
                    <span>${product.detail}</span>
                </div>
            </div>
            </div>



<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
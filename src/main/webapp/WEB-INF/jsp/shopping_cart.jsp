<%--
  Created by IntelliJ IDEA.
  User: khshanovskyi
  Date: 25.11.2019
  Time: 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/shopping_cart.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="shopping.cart"/></title>
</head>

<body>
<!-- navbar menu -->
<header>
    <div class="navbarMenu">
        <div class="leftPartNavbar">
            <div class="brandNameField">
                <a class="navbar-brand" href="/electron.ua/electronics"><strong id="electronLabel">ELECTRON</strong></a>
            </div>
            <div class="LookingForMenu">
                <form action="electronics">
                    <input type="text" class="searchField" name="searchField"
                           placeholder="<fmt:message key="search.field"/>">
                    <button type="submit" class="searchButton" name="searchButton"><fmt:message
                            key="search.btn"/></button>
                </form>
            </div>
        </div>
        <div class="centerLeftPartTriangle">
        </div>
        <div class="centerPartNavbar">
        </div>
        <div class="centerRightPartTriangle">
        </div>
        <div class="rightPartNavbar">
            <div id="shoppingCart">

            </div>
            <div id="userLogin">
                <c:choose>
                    <c:when test="${USER_IS_UNBLOCKED != null}">
                        <jsp:useBean id="USER_IS_UNBLOCKED" scope="session" type="ua.electron.entity.User"/>
                        <c:if test="${USER_IS_UNBLOCKED.role.equals('USER')}">
                            <a class="userLoginBtn" href="/electron.ua/distributor" title="<fmt:message key="user.setting"/>">
                                <img class="userLoginImg"src="imgForIcon/user.png"></a>
                        </c:if>
                        <c:if test="${USER_IS_UNBLOCKED.role.equals('ADMIN')}">
                            <a class="userLoginBtn" href="/electron.ua/distributor" title="<fmt:message key="user.setting"/>">
                                <img class="userLoginImg"src="imgForIcon/admin.png"></a>
                        </c:if>
                        <c:if test="${USER_IS_UNBLOCKED.role.equals('MANAGER')}">
                            <a class="userLoginBtn" href="/electron.ua/distributor" title="<fmt:message key="user.setting"/>">
                                <img class="userLoginImg"src="imgForIcon/manager.png"></a>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <a class="userLoginBtn" href="/electron.ua/login" title="<fmt:message key="Login"/>">
                            <img class="userLoginImg"src="imgForIcon/login.png"></a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</header>
<!-- main section with products -->
<main>
    <div class="content">
        <c:if test="${empty presenterProductFromCookieService}">
            <h1 id="emptyShoppingCartHeader"><fmt:message key="empty.shopping.cart"/></h1>
            <h2 id="emptyShoppingCartHref"><a href="electronics"><fmt:message key="Go.shopping"/></a></h2>
        </c:if>
        <c:if test="${not empty presenterProductFromCookieService}">
            <c:forEach var="product" items="${presenterProductFromCookieService}">
                <div class="productBlock">
                    <div class="forPictureBlock">
                        <img class="imgInSmallIcon" src="${product.pictureURL1}"/>
                    </div>
                    <div class="descriptionAndAction">
                        <div class="productNameInDescriptionAndAction">
                            <h1 class="productNameHeader">${product.productName}</h1>
                        </div>
                        <div class="price">
                            <div class="pricePerOneProduct">
                                <h2>₴ ${product.price}</h2>
                            </div>
                            <div class="changeCount">
                                <form action="shopping-cart-actions">
                                    <button name="minusBtn" class="minusBtn" type="submit" value="${product.idOfProduct}">-</button>
                                    <c:forEach var="currentCookie" items="${cookieIntegerSet}">
                                        <c:if test="${product.idOfProduct.equals(currentCookie.cookieNameEqualProductId)}">
                                            <span class="cookieQuantity">${currentCookie.cookieValueEqualProductQuantity}</span>
                                        </c:if>
                                    </c:forEach>
                                    <button name="plusBtn" class="plusBtn" type="submit" value="${product.idOfProduct}">+</button>
                                    <input name="URLFromRequest" type="hidden" value="/shopping-cart">
                                </form>
                            </div>
                            <div class="totalPricePerProduct">
                                <c:forEach var="totalPriceForOneItem" items="${totalPricePerOneProd}">
                                    <c:if test="${product.idOfProduct.equals(totalPriceForOneItem.productIdFromCookie)}">
                                        <h3 class="totalPriceForOneItem"><fmt:message key="total.sum.per.one"/> ₴ ${totalPriceForOneItem.productPrice}</h3>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="removeFromCart">
                            <form action="shopping-cart-actions" method="post">
                                <button name="removeProduct" type="submit" class="removeProduct"
                                        value="${product.idOfProduct}">
                                    <fmt:message key="remove.product"/>
                                </button>
                                <input type="hidden" name="URLFromRequest" value="/shopping-cart">
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
    <c:if test="${not empty presenterProductFromCookieService}">
        <div class="allInfoForOrder">
            <div id="totalPrice">
                <h2 id="totalSum"><fmt:message key="total.order.sum"/> ₴ ${allPrice}</h2>
            </div>
            <div id="deleteAllFromCart">
                <form action="shopping-cart-actions">
                    <button name="removeAllProduct" type="submit" id="removeAllProduct">
                        <fmt:message key="remove.all.product"/>
                    </button>
                    <input type="hidden" name="URLFromRequest" value="/shopping-cart">
                </form>
            </div>
            <div id="createOrder">
                <form action="order-actions" method="post">
                    <button name="createOrder" type="submit" id="createOrderBtn">
                        <fmt:message key="create.order"/>
                    </button>
                    <input type="hidden" name="URLFromRequest" value="/shopping-cart">
                    <c:if  test="${USER_IS_UNBLOCKED != null}">
                        <input type="hidden" name="userForOrder" value="${USER_IS_UNBLOCKED}">
                    </c:if>
                    <input type="hidden" name="productForOrder" value="${presenterProductFromCookieService}">
                </form>
            </div>
        </div>
    </c:if>
</main>

<!-- footer -->
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/shopping-cart">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit" }>RU</button>
                <input type="hidden" name="URLFromRequest" value="/shopping-cart">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/shopping-cart">
            </form>
        </div>
    </div>
</footer>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>

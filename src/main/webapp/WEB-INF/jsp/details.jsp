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
    <link rel="stylesheet" type="text/css" href="css/details.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="product.info"/></title>
</head>

<body>
<jsp:useBean id="categories" scope="application" type="java.util.Set"/>
<jsp:useBean id="brands" scope="application" type="java.util.Set"/>

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
                <form action="shopping-cart">
                    <button name="shoppingCart" id="shoppingCartImgInNavBar" type="submit" title="<fmt:message key="Shopping.cart"/>"><img
                            id="shoppingCartImg" src="imgForIcon/shopping-caaart.png"></button>
                </form>
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
<c:set var="product" scope="application" value="${productDetails}"/>
<main>
    <div class="withPictureAndActionsBlocks">
        <div class="picturesBlock">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="${product.pictureURL1}" class="d-block w-100 imgCarouselDetails" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="${product.pictureURL2}" class="d-block w-100 imgCarouselDetails" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="${product.pictureURL3}" class="d-block w-100 imgCarouselDetails" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="${product.pictureURL4}" class="d-block w-100 imgCarouselDetails" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="${product.pictureURL5}" class="d-block w-100 imgCarouselDetails" alt="...">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="actionsBlock">
            <div class="productName">
                <h1 id="nameHeader">${product.productName}</h1>
            </div>
            <div class="categoryAndBrand">
               <span class="brandAndCategory">${product.category}</span>&nbsp&nbsp<span class="brandAndCategory">|</span> &nbsp&nbsp<span class="brandAndCategory">${product.brand}</span>
            </div>
            <div class="shortCharacteristic">
                <p>${product.characteristic}</p>
            </div>
            <div class="priceAndAddToShopCart">
                <div class="price">
                    <h2 class="priceHeader">₴ ${product.price}</h2>
                </div>
                <div class="addToShoppingCart">
                    <form action="shopping-cart-actions" method="post">
                        <button name="addToShoppingCartIdOfProduct" class="addToShoppingCartBtn" value="${product.idOfProduct}" type="submit"
                                title="<fmt:message key="add.to.shopping.cart.btn"/>">
                            <img class="imgAddToShoppingCart" src="imgForIcon/shopping-caaart.png"></button>
                        <input type="hidden" name="URLFromRequest" value="/details?prodForDetails=${product.idOfProduct}">
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="productDescription">
        ${product.description}
    </div>
</main>
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/details?prodForDetails=${product.idOfProduct}">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit"}>RU</button>
                <input type="hidden" name="URLFromRequest" value="/details?prodForDetails=${product.idOfProduct}">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/details?prodForDetails=${product.idOfProduct}">
            </form>
        </div>
    </div>
</footer>
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
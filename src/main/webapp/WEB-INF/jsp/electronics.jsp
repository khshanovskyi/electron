<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="${bundle}"/>

<jsp:useBean id="productsList" scope="request" type="java.lang.Object"/>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/electronics.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="electronics"/></title>
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
<!-- left section with sorting methods -->
<div class="content">
    <aside>
        <!-- fast sorting -->
        <div id="columnNameForFastSort">
            <h3>
                <fmt:message key="fast.sort.header"/>:
            </h3>
        </div>
        <div class="fastSorting">
            <div class="fromExpensiveToCheap">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortingSmartphone"><b><fmt:message
                            key="sort.by.smartphone"/></b></button>
                </form>
            </div>
            <div class="fromExpensiveToCheap">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortingTablet"><b><fmt:message
                            key="sort.by.tablet"/></b></button>
                </form>
            </div>
            <div class="fromExpensiveToCheap">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortingTV"><b><fmt:message key="sort.by.TV"/></b>
                    </button>
                </form>
            </div>
            <div class="fromExpensiveToCheap">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortingLaptop"><b><fmt:message
                            key="sort.by.laptop"/></b></button>
                </form>
            </div>
            <div class="name">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortByName"><b><fmt:message key="sort.by.name"/></b>
                    </button>
                </form>
            </div>
            <div class="fromCheapToExpensive">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortByPriceCheap"><b><fmt:message
                            key="sort.by.cheap"/></b></button>
                </form>
            </div>
            <div class="fromExpensiveToCheap">
                <form action="electronics">
                    <button type="submit" class="fastSortButton" name="sortByPriceExpensive"><b><fmt:message
                            key="sort.by.expensive"/></b></button>
                </form>
            </div>
        </div>
        <!-- advanced search-->
        <div class="advancedSearch">
            <form name="advancedSearchForm" action="electronics">
                <div id="advancedSearchHeader">
                    <h4>
                        <fmt:message key="advanced.search.header"/>:
                    </h4>
                </div>
                <div>
                    <h4 class="groupName">
                        <b><fmt:message key="advanced.search.brand.header"/>:</b>
                    </h4>
                </div>
                <div class="checkboxes">
                    <div id="allBrands">
                        <label class="labelForAll" name="AllBrands"><input type="checkbox" name="AllBrands"
                                                                           class="checkboxAll"
                                                                           value="All" checked> <b><fmt:message
                                key="advanced.search.all"/></b></label>
                    </div>
                    <c:forEach var="brand" items="${brands}">
                        <div class="brand">
                            <label name="brand"><input type="checkbox" name="brand" value="${brand}">
                                <b>${brand}</b></label>
                        </div>
                    </c:forEach>
                </div>
                <div>
                    <h4 class="groupName">
                        <b><fmt:message key="advanced.search.category.header"/>:</b>
                    </h4>
                </div>
                <div class="checkboxes">
                    <div id="allCategory">
                        <label class="labelForAll" name="AllCategory"><input type="checkbox" name="AllCategory"
                                                                             class="AllCategory" value="All" checked>
                            <b><fmt:message key="advanced.search.all"/></b></label>
                    </div>
                    <c:forEach var="category" items="${categories}">
                        <div class="brand">
                            <label name="category"><input type="checkbox" name="category" value="${category}">
                                <b>${category}</b></label>
                        </div>
                    </c:forEach>
                </div>
                <div id="fieldForSearchButton">
                    <button id="searchBtnInAside" type="submit" name="searchButtonInAdvancedSearch"><fmt:message
                            key="search.btn"/></button>
                </div>
            </form>
        </div>
    </aside>
    <!-- main section with products -->
    <main>
        <!-- ICON -->
        <c:if test="${empty productsList}">
            <h2>
                <fmt:message key="empty.product.list"/>
            </h2>
        </c:if>
        <c:forEach var="product" items="${productsList}">
            <div class="productIcon">
                <div class="picture">
                    <form action="details">
                        <button class="picBtnForDetails" type="submit" name="prodForDetails" value="${product.idOfProduct}">
                            <img class="imgInSmallIcon" src="${product.pictureURL1}"
                                 title="<fmt:message key="tap.for.details"/>">
                        </button>
                    </form>
                </div>
                <div class="description">
                    <div class="productName">
                        <h5 class="productNameInIcon"
                            title="Product name with descriprion and somethig else">${product.productName}</h5>
                    </div>
                    <div class="characteristic">
                        <p title="${product.characteristic}">${product.characteristic}</p>
                    </div>
                </div>
                <div class="actonForBye">
                    <div class="price">
                        <b>₴ ${product.price}</b>
                    </div>
                    <div class="addtoShoppingCart">
                        <form action="shopping-cart-actions" method="post">
                            <button name="addToShoppingCartIdOfProduct" class="addtoShoppingCartBtn" value="${product.idOfProduct}" type="submit"
                                    title="<fmt:message key="add.to.shopping.cart.btn"/>">
                                <img class="imgAddToShoppingCart" src="imgForIcon/shopping-caaart.png"></button>
                            <input type="hidden" name="URLFromRequest" value="/electronics">
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </main>
</div>
<!-- footer -->
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/electronics">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit"}>RU</button>
                <input type="hidden" name="URLFromRequest" value="/electronics">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/electronics">
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
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
    <link rel="stylesheet" type="text/css" href="css/order_success.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="order.created"/></title>
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
<!-- main section with order details -->
<div id="content">
    <c:if test="${not empty lastUserOrder}">
        <jsp:useBean id="lastUserOrder" scope="request" type="ua.electron.entity.FullOrderInfo"/>
        <h3><fmt:message key="congratulation"/> ${lastUserOrder.firstName}!</h3>
            <h3> <fmt:message key="order.created.successful"/></h3>
        <h2><fmt:message key="expect.a.call.from.our.manager"/></h2>
        <div class="order-info">
            <div class="order-id">
                <div class="for-header">
                    <fmt:message key="order.id.header.div"/>
                </div>
                <div class="order-information">
                        ${lastUserOrder.orderId}
                </div>
            </div>
            <div class="products-in-order">
                <div class="for-header">
                    <fmt:message key="products.in.order"/>
                </div>
                <div class="order-information">
                    <c:forEach var="goods" items="${lastUserOrder.productList}">
                        <h5>${goods.productName}</h5>
                    </c:forEach>
                </div>
            </div>
            <div class="product-quantity">
                <div class="for-header">
                    <fmt:message key="quantity.goods.in.order"/>
                </div>
                <div class="order-information">
                    <c:forEach var="quantity" items="${lastUserOrder.quantity}">
                        <h5>${quantity}</h5>
                    </c:forEach>
                </div>
            </div>
            <div class="total-price">
                <div class="for-header">
                    <fmt:message key="amount.per.prod.in.order"/>
                </div>
                <div class="order-information">
                    <h5>₴ ${lastUserOrder.totalPrice}</h5>
                </div>
            </div>
            <div class="name-surname">
                <div class="for-header">
                    <fmt:message key="name.surname"/>
                </div>
                <div class="order-information">
                    <h6>${lastUserOrder.secondName}</h6>
                    <h6>${lastUserOrder.firstName}</h6>
                </div>
            </div>
            <div class="phone-number-and-email">
                <div class="for-header">
                    <fmt:message key="phone.number.and.email"/>
                </div>
                <div class="order-information">
                    <h6>0${lastUserOrder.phoneNumber}</h6>
                    <h6>${lastUserOrder.email}</h6>
                </div>
            </div>
        </div>
        <h3><fmt:message key="our.manager.will.call.you"/> <b>0${lastUserOrder.phoneNumber}</b></h3>
        <h4><fmt:message key="if.num.not.exist"/></h4>
        <h4><fmt:message key="change.in"/> <a href="#"><fmt:message key="user.settings"/></a></h4>
        <h4><fmt:message key="or.call.to.manager"/> <b>0932269758</b></h4>
    </c:if>
</div>

<!-- footer -->
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/order-success">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit" }>RU</button>
                <input type="hidden" name="URLFromRequest" value="/order-success">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/order-success">
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

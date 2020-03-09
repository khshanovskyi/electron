<%--
  Created by IntelliJ IDEA.
  User: Ace8
  Date: 22.02.2020
  Time: 22:05
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
    <link rel="stylesheet" type="text/css" href="css/user_interface.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="user.interface.icon.in.header"/></title>
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
                <form action="logout" method="post">
                    <button class="userLoginBtn" name="LOGOUT" title="<fmt:message key="logout.btn"/>">
                        <img class="userLoginImg" src="imgForIcon/logout.png"></button>
                </form>
            </div>
        </div>
    </div>
</header>
<!-- main section with order details -->
<jsp:useBean id="USER_IS_UNBLOCKED" scope="session" type="ua.electron.entity.User"/>
<div id="content">
    <div id="href-to-orders-info">
        <a href="/electron.ua/about-my-orders"><fmt:message key="href.to.orders.info"/></a>
    </div>
    <div class="with-setting">
        <div class="dropdown-with-settings">
            <h3 class="href-with-info-for-changing"><fmt:message key="change.e.mail"/></h3>
            <b><fmt:message key="your.email.is"/> ${USER_IS_UNBLOCKED.email}</b>
            <form action="user-interface" method="post" class="form-for-change-data">
                <input type="email" class="inputField" name="E_MAIL" id="change-email" tabindex="1"
                       placeholder="<fmt:message key="input.new.email"/>"
                       pattern="^[\w!#$%&’*+/=?`{|}~^-]+(?:\.[\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$">
                <input class="inputField" type="password" name="PASSWORD_CONFIRM" tabindex="2"
                       placeholder="<fmt:message key="password.placeholder"/>"
                       pattern="[A-Za-z0-9]{6,21}">
                <button type="submit" name="SUBMIT_CHANGE_EMAIL" class="submit-in-interface">
                    <fmt:message key="btn.submit.in.user.interface"/></button>
            </form>
        </div>
        <div class="dropdown-with-settings">
            <div class="dropdown">
                <h3 class="href-with-info-for-changing"><fmt:message key="change.phone.number"/></h3>
                <b><fmt:message key="your.phone.number"/> 0${USER_IS_UNBLOCKED.phoneNumber}</b>
                <form action="user-interface" method="post" class="form-for-change-data">
                    <input  class="inputField" type="text" name="CHANGE_PHONE_NUM" id="change-phone-num"
                           placeholder="<fmt:message key="input.new.phone.number"/>"
                           pattern="[0-9]{10}">
                    <button type="submit" name="SUBMIT_CHANGE_PHONE_NUM" class="submit-in-interface">
                        <fmt:message key="btn.submit.in.user.interface"/></button>
                </form>
            </div>
        </div>
        <div class="dropdown-with-settings">
            <h3 class="href-with-info-for-changing"><fmt:message key="change.password"/></h3>
            <form action="user-interface" method="post" class="form-for-change-data">
                <input class="inputField" type="password" name="OLD_PASSWORD" tabindex="3"
                       placeholder="<fmt:message key="your.old.password"/>"
                       pattern="[A-Za-z0-9]{6,21}">
                <input class="inputField" type="password" name="NEW_PASSWORD" tabindex="4"
                       placeholder="<fmt:message key="input.new.password"/>"
                       pattern="[A-Za-z0-9]{6,21}">
                <input class="inputField" type="password" name="NEW_PASSWORD_CONFIRM" tabindex="5"
                       placeholder="<fmt:message key="confirm.password"/>"
                       pattern="[A-Za-z0-9]{6,21}">
                <button type="submit" name="SUBMIT_CHANGE_PASSWORD" class="submit-in-interface">
                    <fmt:message key="btn.submit.in.user.interface"/></button>
            </form>
        </div>
        <div class="dropdown-with-settings">
            <h3 class="href-with-info-for-changing"><fmt:message key="change.name.surname"/></h3>
            <b>${USER_IS_UNBLOCKED.firstName} ${USER_IS_UNBLOCKED.secondName}, </b>
            <fmt:message key="info.about.name.surname"/>
            <form action="user-interface" method="post" class="form-for-change-data">
                <input class="inputField" name="CHANGE_NAME" type="text" tabindex="6"
                       placeholder="<fmt:message key="first.name.title.registration"/>"
                       pattern="[A-Za-zА-Яа-яёЁЇїІіЄєҐґ]{2,15}">
                <input class="inputField" name="CHANGE_SURNAME" type="text" tabindex="7"
                       placeholder="<fmt:message key="second.name.title.registration"/>"
                       pattern="[A-Za-zА-Яа-яёЁЇїІіЄєҐґ]{2,15}">
                <button type="submit" name="SUBMIT_CHANGE_NAME_SURNAME" class="submit-in-interface">
                    <fmt:message key="btn.submit.in.user.interface"/></button>
            </form>
        </div>
        <div id="logout-div">
            <form action="logout" method="post">
                <button class="logoutBtn" name="LOGOUT" title="<fmt:message key="logout.btn"/>">
                    <fmt:message key="logout.btn"/></button>
            </form>
        </div>
    </div>
</div>
<!-- footer -->
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/user-interface">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit" }>RU</button>
                <input type="hidden" name="URLFromRequest" value="/user-interface">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/user-interface">
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

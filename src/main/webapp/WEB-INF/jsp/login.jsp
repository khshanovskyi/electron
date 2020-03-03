<%--
  Created by IntelliJ IDEA.
  User: Ace8
  Date: 18.01.2020
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="${bundle}"/>

<html >
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="sign.in"/></title>
</head>
<body>
<!-- header -->
<header>
    <div class="navbarMenu">
        <div class="leftPartNavbar">
            <div class="brandNameField">
                <a class="navbar-brand" href="/electron.ua/electronics"><strong id="electronLabel">ELECTRON</strong></a>
            </div>
        </div>
        <div class="centerLeftPartTriangle">
        </div>
        <div class="centerPartNavbar">
        </div>
        <div class="centerRightPartTriangle">
        </div>
        <div class="rightPartNavbar">
            <a id="userRegistrateBtn" href="/electron.ua/registration" title="<fmt:message key="title.registration"/>"><img id="userRegistrImg" src="imgForIcon/group.png"></a>
        </div>
    </div>
</header>
<!-- main part -->
<main>
    <div id="loginOutsideBorder">
        <div>
            <h3><fmt:message key="header.info.for.user"/></h3>
        </div>
        <form method="post" action="authorization">
            <div>
                <h2><fmt:message key="login.header"/>:</h2>
                <input class="inputField" type="email" name="EMAIL_LOGIN" placeholder="<fmt:message key="login.placeholder"/>">
            </div>
            <div>
                <h2><fmt:message key="password.header"/>:</h2>
                <input class="inputField" type="password" name="PASSWORD_LOGIN" placeholder="<fmt:message key="password.placeholder"/>">
            </div>
            <button id="loginBtn" name="SUBMIT_LOGIN" type="submit" title="<fmt:message key="login.submit"/>"><fmt:message key="login.submit"/></button>
        </form>
        <a href="#"><fmt:message key="i.forgot.my.password"/></a>
        <a href="/electron.ua/registration"><fmt:message key="ref.to.registration"/></a>
    </div>
</main>
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/login">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit"}>RU</button>
                <input type="hidden" name="URLFromRequest" value="/login">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/login">
            </form>
        </div>
    </div>
</footer>
</body>
</html>

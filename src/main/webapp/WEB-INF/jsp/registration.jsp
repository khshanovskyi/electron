<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/registration.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="registration"/></title>
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
            <a id="userRegistrateBtn" href="/electron.ua/login" title="<fmt:message key="Login"/>"><img
                    id="userRegistrImg" src="imgForIcon/login.png"></a>
        </div>
    </div>
</header>
<!-- main part -->
<main>
    <div id="loginOutsideBorder">
        <div>
            <h3><fmt:message key="header.info.for.user.in.registration"/></h3>
        </div>
        <form method="post" action="registration-controller">
            <div class="fieldDiv">
                <h2><fmt:message key="first.name.header"/>:</h2>
                <input class="inputField" name="REGISTRATION_FIRST_NAME" type="text"
                       placeholder="<fmt:message key="first.name.title.registration"/>" pattern="[A-Za-zА-Яа-яёЁЇїІіЄєҐґ]{2,15}">
            </div>
            <div class="fieldDiv">
                <h2><fmt:message key="second.name.header"/>:</h2>
                <input class="inputField" name="REGISTRATION_SECOND_NAME" type="text"
                       placeholder="<fmt:message key="second.name.title.registration"/>" pattern="[A-Za-zА-Яа-яёЁЇїІіЄєҐґ]{2,15}">
            </div>
            <div class="fieldDiv">
                <h2><fmt:message key="email.header"/>:</h2>
                <input class="inputField" type="email" name="REGISTRATION_EMAIL"
                       placeholder="<fmt:message key="email.title.registration"/>"
                       pattern="^[\w!#$%&’*+/=?`{|}~^-]+(?:\.[\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,6}$">
            </div>
            <div class="fieldDiv">
                <h2><fmt:message key="PN"/>:</h2>
                <input class="inputField" type="text" name="REGISTRATION_PHONE_NUMBER"
                       placeholder="<fmt:message key="PNHeader"/>" pattern="[0-9]{10}">
            </div>
            <div class="fieldDiv">
                <h2><fmt:message key="cityHeader"/>:</h2>
                <select class="inputField" name="REGISTRATION_CITY" id="REGISTRATION_CITY">
                    <option value="<fmt:message key="Kyiv"/>"><fmt:message key="Kyiv"/></option>
                    <option value="<fmt:message key="Kharkiv"/>"><fmt:message key="Kharkiv"/></option>
                    <option value="<fmt:message key="Odessa"/>"><fmt:message key="Odessa"/></option>
                    <option value="<fmt:message key="Dnipro"/>"><fmt:message key="Dnipro"/></option>
                    <option value="<fmt:message key="Donetsk"/>"><fmt:message key="Donetsk"/></option>
                    <option value="<fmt:message key="Zaporizhia"/>"><fmt:message key="Zaporizhia"/></option>
                    <option value="<fmt:message key="Lviv"/>"><fmt:message key="Lviv"/></option>
                    <option value="<fmt:message key="Mykolaiv"/>"><fmt:message key="Mykolaiv"/></option>
                    <option value="<fmt:message key="Luhansk"/>"><fmt:message key="Luhansk"/></option>
                    <option value="<fmt:message key="Sevastopol"/>"><fmt:message key="Sevastopol"/></option>
                    <option value="<fmt:message key="Vinnytsia"/>"><fmt:message key="Vinnytsia"/></option>
                    <option value="<fmt:message key="Kherson"/>"><fmt:message key="Kherson"/></option>
                    <option value="<fmt:message key="Poltava"/>"><fmt:message key="Poltava"/></option>
                    <option value="<fmt:message key="Chernihiv"/>"><fmt:message key="Chernihiv"/></option>
                    <option value="<fmt:message key="Khmelnytskyi"/>"><fmt:message key="Khmelnytskyi"/></option>
                    <option value="<fmt:message key="Chernivtsi"/>"><fmt:message key="Chernivtsi"/></option>
                    <option value="<fmt:message key="Cherkasy"/>"><fmt:message key="Cherkasy"/></option>
                    <option value="<fmt:message key="Zhytomyr"/>"><fmt:message key="Zhytomyr"/></option>
                    <option value="<fmt:message key="Sumy"/>"><fmt:message key="Sumy"/></option>
                    <option value="<fmt:message key="Rivne"/>"><fmt:message key="Rivne"/></option>
                    <option value="<fmt:message key="Ternopil"/>"><fmt:message key="Ternopil"/></option>
                    <option value="<fmt:message key="Ivano-Frankivsk"/>"><fmt:message key="Ivano-Frankivsk"/></option>
                    <option value="<fmt:message key="Lutsk"/>"><fmt:message key="Lutsk"/></option>
                    <option value="<fmt:message key="Uzhhorod"/>"><fmt:message key="Uzhhorod"/></option>
                </select>
            </div>
            <div>
                <h2><fmt:message key="password.header"/>:</h2>
                <input class="inputField" type="password" name="REGISTRATION_PASSWORD" placeholder="<fmt:message key="password.placeholder"/>"
                       pattern="[A-Za-z0-9]{6,21}">
            </div>
            <div>
                <h2><fmt:message key="confirm.password"/>:</h2>
                <input class="inputField" type="password" name="REGISTRATION_PASSWORD_CONFIRM"
                       placeholder="<fmt:message key="confirm.password"/>"
                       pattern="[A-Za-z0-9]{6,21}">
            </div>
            <button id="loginBtn" type="submit" title="Login"><fmt:message key="btn.submit"/></button>
        </form>
        <a href="/electron.ua/login"><fmt:message key="ref.to.login"/></a>
    </div>
</main>
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/registration">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit">RU</button>
                <input type="hidden" name="URLFromRequest" value="/registration">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/registration">
            </form>
        </div>
    </div>
</footer>
</body>
</html>
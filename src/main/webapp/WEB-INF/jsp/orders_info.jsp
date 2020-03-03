<%--
  Created by IntelliJ IDEA.
  User: Ace8
  Date: 23.02.2020
  Time: 13:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<%@ taglib uri="/WEB-INF/tag/allOrdersInProsessing.tag" prefix="allOrdersInProsessingTag"%>--%>
<%--<%@ taglib prefix="allOrdersInProsessingTag" uri="http://java.sun.com/jsp/jstl/core" tagdir="/WEB-INF/tag/allOrdersInProsessing.tag" %>--%>
<%--<%@ taglib prefix="allOrdersInProsessingTag" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<fmt:setBundle basename="${bundle}"/>

<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <%--    my stylesheet--%>
    <link rel="stylesheet" type="text/css" href="css/orders_info.css">
    <link rel="shortcut icon" href="imgForIcon/smartphone.png" type="image/png">

    <title>ELECTRON: <fmt:message key="order.created"/></title>
</head>

<body>
<!-- navbar menu -->
<header>
    <div class="navbarMenu">
        <div class="leftPartNavbar">
            <div class="brandNameField">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/electronics"><strong
                        id="electronLabel">ELECTRON</strong></a>
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
                <a class="userLoginBtn" href="${pageContext.request.contextPath}/distributor"
                   title="<fmt:message key="user.setting"/>">
                    <img class="userLoginImg" src="imgForIcon/user.png" alt=""></a>
            </div>
        </div>
    </div>
</header>
<!-- main section with order details -->
<jsp:useBean id="NOTHING" scope="session" type="java.lang.String"/>
<jsp:useBean id="IN_PROCESSING" scope="session" type="java.lang.String"/>
<jsp:useBean id="WAITING_ANSWER" scope="session" type="java.lang.String"/>
<jsp:useBean id="PROCESSED" scope="session" type="java.lang.String"/>
<jsp:useBean id="FORMED" scope="session" type="java.lang.String"/>
<jsp:useBean id="SENT" scope="session" type="java.lang.String"/>
<jsp:useBean id="GOT" scope="session" type="java.lang.String"/>
<jsp:useBean id="CANCELED" scope="session" type="java.lang.String"/>

<div id="content">
    <%--  in processing  --%>
    <jsp:useBean id="statusInProcessing" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusInProcessing}">
        <div class="all-icon">
            <button class="btn-with-my-style btn-in-processing" type="button" data-toggle="collapse"
                    data-target="#statusInProcessing"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="in.processing"/>
            </button>
            <div class="collapse" id="statusInProcessing">
                <div class="card card-body">
                    <c:forEach var="inProcessing" items="${statusInProcessing}">
                        <div class="order-info inProcessing">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${inProcessing.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${inProcessing.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${inProcessing.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${inProcessing.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="phone-number-and-email">
                                <div class="for-header">
                                    <fmt:message key="phone.number.and.email"/>
                                </div>
                                <div class="order-information">
                                    <h6>0${inProcessing.phoneNumber}</h6>
                                    <h6>${inProcessing.email}</h6>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <h6><fmt:message key="in.processing"/></h6>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%--  waiting answer  --%>
    <jsp:useBean id="statusWaitingAnswer" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusWaitingAnswer}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse" data-target="#statusWaitingAnswer"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="waiting.answer.btn"/>
            </button>
            <div class="collapse" id="statusWaitingAnswer">
                <div class="card card-body">
                    <c:forEach var="waitingAnswer" items="${statusWaitingAnswer}">
                        <div class="order-info waitingAnswer">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${waitingAnswer.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${waitingAnswer.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${waitingAnswer.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${waitingAnswer.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="phone-number-and-email">
                                <div class="for-header">
                                    <fmt:message key="phone.number.and.email"/>
                                </div>
                                <div class="order-information">
                                    <h6>0${waitingAnswer.phoneNumber}</h6>
                                    <h6>${waitingAnswer.email}</h6>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <h6><fmt:message key="waiting.answer.info"/></h6>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%--processed    --%>
    <jsp:useBean id="statusProcessed" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusProcessed}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse" data-target="#statusProcessed"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="processed.btn"/>
            </button>
            <div class="collapse" id="statusProcessed">
                <div class="card card-body">
                    <c:forEach var="processed" items="${statusProcessed}">
                        <div class="order-info processed">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${processed.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${processed.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${processed.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${processed.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="delivery-address">
                                <div class="for-header">
                                    <fmt:message key="delivery.address"/>
                                </div>
                                <div class="order-information">
                                    <h6>${processed.deliveryAddress}</h6>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <fmt:message key="processed.info"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%--formed--%>
    <jsp:useBean id="statusFormed" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusFormed}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse" data-target="#statusFormed"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="formed.btn"/>
            </button>
            <div class="collapse" id="statusFormed">
                <div class="card card-body">
                    <c:forEach var="formed" items="${statusFormed}">
                        <div class="order-info formed">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${formed.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${formed.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${formed.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${formed.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="delivery-address">
                                <div class="for-header">
                                    <fmt:message key="delivery.address"/>
                                </div>
                                <div class="order-information">
                                    <c:if test="${NOTHING.equals(formed.deliveryAddress)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(formed.deliveryAddress)}">
                                        <h6>${formed.deliveryAddress}</h6>
                                    </c:if>
                                </div>
                            </div>
                            <div class="delivery-service-and-invoice">
                                <div class="for-header">
                                    <fmt:message key="delivery.service.and.invoice"/>
                                </div>
                                <div class="order-information">
                                    <c:if test="${NOTHING.equals(formed.deliveryService)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(formed.deliveryService)}">
                                        <h6>${formed.deliveryService}</h6>
                                    </c:if>
                                    <c:if test="${NOTHING.equals(formed.deliveryNote)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(formed.deliveryNote)}">
                                        <h6>${formed.deliveryNote}</h6>
                                    </c:if>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <fmt:message key="formed.info"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%--sent--%>
    <jsp:useBean id="statusSent" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusSent}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse" data-target="#statusSent"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="sent.btn"/>
            </button>
            <div class="collapse" id="statusSent">
                <div class="card card-body">
                    <c:forEach var="sent" items="${statusSent}">
                        <div class="order-info sent">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${sent.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${sent.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${sent.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${sent.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="delivery-address">
                                <div class="for-header">
                                    <fmt:message key="delivery.address"/>
                                </div>
                                <div class="order-information">
                                    <h6>${sent.deliveryAddress}</h6>
                                </div>
                            </div>
                            <div class="delivery-service-and-invoice">
                                <div class="for-header">
                                    <fmt:message key="delivery.service.and.invoice"/>
                                </div>
                                <div class="order-information">
                                    <c:if test="${NOTHING.equals(sent.deliveryService)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(sent.deliveryService)}">
                                        <h6>${sent.deliveryService}</h6>
                                    </c:if>
                                    <c:if test="${NOTHING.equals(sent.deliveryNote)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(sent.deliveryNote)}">
                                        <h6>${sent.deliveryNote}</h6>
                                    </c:if>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <fmt:message key="sent.info"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%--Got--%>
    <jsp:useBean id="statusGot" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusGot}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse" data-target="#statusGot"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="got"/>
            </button>
            <div class="collapse" id="statusGot">
                <div class="card card-body">
                    <c:forEach var="got" items="${statusGot}">
                        <div class="order-info got">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${got.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${got.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${got.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${got.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="delivery-address">
                                <div class="for-header">
                                    <fmt:message key="delivery.address"/>
                                </div>
                                <div class="order-information">
                                    <h6>${got.deliveryAddress}</h6>
                                </div>
                            </div>
                            <div class="delivery-service-and-invoice">
                                <div class="for-header">
                                    <fmt:message key="delivery.service.and.invoice"/>
                                </div>
                                <div class="order-information">
                                    <c:if test="${NOTHING.equals(got.deliveryService)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(got.deliveryService)}">
                                        <h6>${got.deliveryService}</h6>
                                    </c:if>
                                    <c:if test="${NOTHING.equals(got.deliveryNote)}">
                                        <h6><fmt:message key="delivery.address.nothing"/></h6>
                                    </c:if>
                                    <c:if test="${!NOTHING.equals(got.deliveryNote)}">
                                        <h6>${got.deliveryNote}</h6>
                                    </c:if>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <fmt:message key="got"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%--canceled--%>
    <jsp:useBean id="statusCanceled" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty statusCanceled}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse" data-target="#statusCanceled"
                    aria-expanded="false" aria-controls="collapseExample">
                <fmt:message key="canceled.btn"/>
            </button>
            <div class="collapse" id="statusCanceled">
                <div class="card card-body">
                    <c:forEach var="canceled" items="${statusCanceled}">
                        <div class="order-info canceled">
                            <div class="order-id">
                                <div class="for-header">
                                    <fmt:message key="order.id.header.div"/>
                                </div>
                                <div class="order-information">
                                        ${canceled.orderId}
                                </div>
                            </div>
                            <div class="products-in-order">
                                <div class="for-header">
                                    <fmt:message key="products.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="goods" items="${canceled.productList}">
                                        <h5>${goods.productName}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="product-quantity">
                                <div class="for-header">
                                    <fmt:message key="quantity.goods.in.order"/>
                                </div>
                                <div class="order-information">
                                    <c:forEach var="quantity" items="${canceled.quantity}">
                                        <h5>${quantity}</h5>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="total-price">
                                <div class="for-header">
                                    <fmt:message key="amount.per.prod.in.order"/>
                                </div>
                                <div class="order-information">
                                    <h5>₴ ${canceled.totalPrice}</h5>
                                </div>
                            </div>
                            <div class="delivery-address">
                                <div class="for-header">
                                    <fmt:message key="delivery.address"/>
                                </div>
                                <div class="order-information">
                                    <h6>${canceled.deliveryAddress}</h6>
                                </div>
                            </div>
                            <div class="order-status">
                                <div class="for-header">
                                    <fmt:message key="order.status.header"/>
                                </div>
                                <div class="order-information">
                                    <fmt:message key="canceled.info"/>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
    <%-- ALL  --%>
    <jsp:useBean id="allUserOrder" scope="request" type="java.util.List<ua.electron.entity.FullOrderInfo>"/>
    <c:if test="${not empty allUserOrder}">
        <div class="all-icon">
            <button class="btn-with-my-style" type="button" data-toggle="collapse"
                    data-target="#allOrders">
                <fmt:message key="all.btn"/>
            </button>
            <div class="collapse" id="allOrders">
                <div class="card card-body">
                    <c:forEach var="fullInfoObj" items="${allUserOrder}">
                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                            <div class="order-info inProcessing">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                            <div class="order-info waitingAnswer">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                            <div class="order-info processed">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                            <div class="order-info formed">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                            <div class="order-info sent">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                            <div class="order-info got">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                            <div class="order-info canceled">
                                <div class="order-id">
                                    <div class="for-header">
                                        <fmt:message key="order.id.header.div"/>
                                    </div>
                                    <div class="order-information">
                                            ${fullInfoObj.orderId}
                                    </div>
                                </div>
                                <div class="products-in-order">
                                    <div class="for-header">
                                        <fmt:message key="products.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="goods" items="${fullInfoObj.productList}">
                                            <h5>${goods.productName}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="product-quantity">
                                    <div class="for-header">
                                        <fmt:message key="quantity.goods.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <c:forEach var="quantity" items="${fullInfoObj.quantity}">
                                            <h5>${quantity}</h5>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div class="total-price">
                                    <div class="for-header">
                                        <fmt:message key="amount.per.prod.in.order"/>
                                    </div>
                                    <div class="order-information">
                                        <h5>₴ ${fullInfoObj.totalPrice}</h5>
                                    </div>
                                </div>
                                <div class="delivery-address">
                                    <div class="for-header">
                                        <fmt:message key="delivery.address"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <fmt:message key="delivery.address.nothing"/>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryAddress)}">
                                            <h6>${fullInfoObj.deliveryAddress}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="delivery-service-and-invoice">
                                    <div class="for-header">
                                        <fmt:message key="delivery.service.and.invoice"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryService)}">
                                            <h6>${fullInfoObj.deliveryService}</h6>
                                        </c:if>
                                        <c:if test="${NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6><fmt:message key="delivery.address.nothing"/></h6>
                                        </c:if>
                                        <c:if test="${!NOTHING.equals(fullInfoObj.deliveryNote)}">
                                            <h6>${fullInfoObj.deliveryNote}</h6>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="order-status">
                                    <div class="for-header">
                                        <fmt:message key="order.status.header"/>
                                    </div>
                                    <div class="order-information">
                                        <c:if test="${IN_PROCESSING.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="in.processing"/></h6>
                                        </c:if>
                                        <c:if test="${WAITING_ANSWER.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="waiting.answer.info"/></h6>
                                        </c:if>
                                        <c:if test="${PROCESSED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="processed.info"/></h6>
                                        </c:if>
                                        <c:if test="${FORMED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="formed.info"/></h6>
                                        </c:if>
                                        <c:if test="${SENT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="sent.info"/></h6>
                                        </c:if>
                                        <c:if test="${GOT.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="got"/></h6>
                                        </c:if>
                                        <c:if test="${CANCELED.equals(fullInfoObj.status)}">
                                            <h6><fmt:message key="canceled.info"/></h6>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>
</div>

<!-- footer -->
<footer>
    <div id="forLocale">
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnEN" id="localeBtnEN" title="English" type="submit">EN</button>
                <input type="hidden" name="URLFromRequest" value="/about-my-orders">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnRU" id="localeBtnRU" title="Русский" type="submit">RU</button>
                <input type="hidden" name="URLFromRequest" value="/about-my-orders">
            </form>
        </div>
        <div class="forLocaleBtn">
            <form action="locale">
                <button name="localeBtnUK" id="localeBtnUK" title="Українська" type="submit">UK</button>
                <input type="hidden" name="URLFromRequest" value="/about-my-orders">
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


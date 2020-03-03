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
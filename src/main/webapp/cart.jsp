<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="en_US"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="main.css">
</head>
<body>
<h1>Your cart</h1>

<c:choose>
    <c:when test="${empty cart || empty cart.items}">
        <p>Empty cart</p>
    </c:when>

    <c:otherwise>
        <table>
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
                <th></th>
            </tr>

            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>
                        <form action="cart" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="productCode" value="${item.product.code}">
                            <input type="text" name="quantity" value="${item.quantity}" size="2">
                            <input type="submit" value="Update">
                        </form>
                    </td>
                    <td>${item.product.description}</td>
                    <td><fmt:formatNumber value="${item.product.price}" type="currency"/></td>
                    <td><fmt:formatNumber value="${item.total}" type="currency"/></td>
                    <td>
                        <form action="cart" method="post" style="display:inline;"
                              onsubmit="return confirm('Are you sure to delete ${item.product.description} item?');">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="productCode" value="${item.product.code}">
                            <input type="submit" value="Remove Item">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </c:otherwise>
</c:choose>

<form action="index.html" method="get" style="display:inline;">
    <input type="submit" value="Continue Shopping">
</form>

<c:if test="${not empty cart && not empty cart.items}">
    <form action="cart" method="post" style="display:inline;">
        <input type="hidden" name="action" value="checkout">
        <input type="submit" value="Checkout">
    </form>
</c:if>
</body>
</html>
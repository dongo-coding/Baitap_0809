        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <title>Invoice Summary</title>
            <link rel="stylesheet" href="main.css">
        </head>
        <body>
            <h1>Invoice</h1>

            <p><b>Total bill:</b> <fmt:formatNumber value="${grandTotal}" type="currency"/></p>

            <form action="index.html" method="get">
                <input type="submit" value="Continue Shopping">
            </form>
        </body>
        </html>
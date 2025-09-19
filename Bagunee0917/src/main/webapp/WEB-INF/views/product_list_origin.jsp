<%--
  Created by IntelliJ IDEA.
  User: DU
  Date: 25. 9. 18.
  Time: 오후 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Product List</h2>
<a href="/cart">Go to Cart</a>
<table border="1">
    <tr><th>Name</th><th>Price</th><th>Action</th></tr>
    <c:forEach var="p" items="${products}">
        <tr>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>
                <form action="/add" method="post">
                    <input type="hidden" name="productId" value="${p.id}"/>
                    <input type="number" name="quantity" value="${1}"/>
                    <input type="submit" value="Add to Cart"/>
                </form>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Ootka
  Date: 13-Feb-17
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <ol>
        <c:forEach items="${zipNames}" var="item">
            <li><a href="/download/${item}">${item}</a></li>
        </c:forEach>
    </ol>
</div>
</body>
</html>

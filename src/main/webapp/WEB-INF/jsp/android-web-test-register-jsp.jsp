<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mr.kong
  Date: 2018/3/19
  Time: 下午5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${list}" var="item" varStatus="vs">
    <h1>这是标题：${item.id}</h1>
    <p>这是作者：${item.userName}</p>
    <a>这是内容：${item.passWord}</a>
</c:forEach>
</body>
</html>

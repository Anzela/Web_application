<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Personal page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
</head>
<body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>
    <div class="content">
        <h1>Личная страничка пользователя:</h1>
        <p>${user.name}</p>
        <p>${user.role.title}</p>

        <c:forEach var="post" items="${posts}">
            <a href="/test-mvn-app/${post.section}/${post.id}">
                <div class="post-content">
                    <h1>${post.title}</h1>
                    <p>${post.text}</p>
                    <p>Тема создана: ${data}</p>
                </div></a>
        </c:forEach>
    </div>
    <div class="footer">
    </div>
</body>
</html>
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
        <c:if test="${canDeleteUser}">
            <a href="/test-mvn-app/user/${user.id}/delete"><div class="button">Удалить аккаунт</div></a>
        </c:if>

        <h1>Личная страничка пользователя:</h1>
        <p>${user.name}</p>
        <p>${user.email}</p>
        <p>${user.role.title}</p>

        <c:forEach var="post" items="${posts}">
            <a href="/test-mvn-app/${post.section}/${post.id}">
                <div class="post-content">
                    <h1>${post.title}</h1>
                    <p>${post.text}</p>
                    <p>Тема создана: ${post.formattedCreationDate}</p>
                </div></a>
        </c:forEach>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
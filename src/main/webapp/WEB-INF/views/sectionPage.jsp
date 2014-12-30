<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Login</title>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
</head>
<body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="left-column">
            <h1>Форум: ${section.title}</h1>
            <h1>Создать новую тему:</h1>
                <form action="" method="POST">
                Название темы: <input type="postTitle" maxlength=150 name="postTitle"><br>
                Текст: <input type="postText" maxlength=10000 name="postText" /><br>
                <input type="submit" value="Создать" /></form>

            <c:forEach var="post" items="${posts}">
                <a href="/test-mvn-app/${post.section}/${post.id}">
                    <div class="post-content">
                        <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                        <h1>${post.title}</h1>
                        <p>${post.text}</p>
                        <p>Тема создана: ${data}</p>
                    </div></a>
            </c:forEach>
        </div>
    </div>
    <div class="footer">
    </div>
</body>
</html>
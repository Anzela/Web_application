<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Edit Post</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/specialPages.css"/>
</head>
<body>
    <div class="big_background_image">
        <div class="logo">
            <a href="/test-mvn-app/"><img src="/test-mvn-app/resources/images/logo.png"></a>
        </div>
        <div class="content_block">
            <h3>Страница для редактирования поста:</h3>

            <form action="./" method="POST">
                Название темы: <input type="postTitle" maxlength=150 name="postTitle" value="${post.title}"><br>
                Текст: <input type="text" maxlength=10000 name="postText" value="${post.text}" /><br>
                <ul class="redirect_buttons">
                    <li><input type="submit" value="Редактировать"></li>
                    <li><a href="javascript:history.back()">Отменить</a></li>
                </ul>

                <div class="popUp_error">
                    <c:if test="${not empty postError}">
                         <p>${postError}</p>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</body>
</html>

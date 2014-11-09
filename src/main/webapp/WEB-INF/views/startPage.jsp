<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/reset.css"/>
        <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/main.css"/>
        <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
        <title>start Page</title>
    </head>
    <body>
    <div class="main">
        <div class="toolbar">
            <ul class="toolbar_buttons">
                <li><a href="/test-mvn-app/registration">Регистрация</a></li>
                <li><a href="/test-mvn-app/">Вход</a></li>
            </ul>
        </div>
        <div class="header">
            <div class="logo">
                <a href="/test-mvn-app/"><img src="/test-mvn-app/resources/images/logo.png"></a>
            </div>
        </div>
        <div class="content-main">
            <ul class="menu">
                <li><a href="/test-mvn-app/">Главная</a></li>
                <li><a href="/test-mvn-app/forum">Форум</a></li>
                <li><a href="/test-mvn-app/users">Пользователи</a></li>
            </ul>
            <div class="content">
                <div class="left-column">
                    <div class="post-head">
                        <a href="/test-mvn-app/forum/NEWS"><h1>Новости</h1></a>
                    </div>
                    <c:forEach var="post" items="${posts}">
                        <div class="post-area">
                            <div class="post-content">
                                <img src="/test-mvn-app/resources/images/news_icon.png" class="imgStyle"/>
                                <h1>${post.postTitle}</h1>
                                <p>${post.postText}</p>
                            </div>
                        </div>
                    </c:forEach>
            </div>
                <div class="right-column">
                    <div class="post-head">
                        <h1>Последние темы</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>

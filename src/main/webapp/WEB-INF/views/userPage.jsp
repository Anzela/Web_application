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
        <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/header.css"/>
        <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/userCards.css"/>
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
                <a href="/test-mvn-app/">FORUM</a>
            </div>
        </div>
        <div class="content-main">
            <ul class="menu">
                <li><a href="/test-mvn-app/">Главная</a></li>
                <li><a href="/test-mvn-app/forum">Форум</a></li>
                <li><a href="/test-mvn-app/users">Пользователи</a></li>
                <li><a href="/test-mvn-app/users">Галерея</a></li>
            </ul>
            <div class="content">
                <h1>Welcome!</h1>

                <p>${user.name}</p>
                <p>${user.gender}</p>
                <p>${user.age}</p>
                <p>${user.role}</p>

                <h3>Posts:</h3>
            </div>
        </div>
    </div>
    </body>
</html>



<!DOCTYPE html>
<html>
<body>



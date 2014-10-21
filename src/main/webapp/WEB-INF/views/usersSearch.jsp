<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/main.css"/>
        <title>start Page</title>
    </head>
    <body>
    <div class="main">
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
                <form class="search-main" action="" method=""
                    <input class="search-txt" type="text" name="search"/>
                <form>
            </ul>
            <div class="content">
                <c:forEach var="user" items="users">
                    <b> ${user} </b>
                </c:forEach>
            </div>
        </div>
    </div>
    </body>
</html>
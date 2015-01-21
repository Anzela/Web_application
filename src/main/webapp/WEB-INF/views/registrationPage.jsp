<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Registration</title>
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
            <h3>Зарегестрироваться</h3>
            Логин:

            <div class="form">
                <form action="registration" method="POST">
                <input type="name" maxlength=20 name="name">
            </div>
            Пароль:
            <div class="form">
                 <input type="password" maxlength=12 name="password" />
            </div>
            <input type="submit" value="Зарегестрировать" /></form>

            <c:if test="${not empty error}">
                <div class="content_block_error">
                    <p>*${error}</p>
                </div>
            </c:if>
            <ul class="redirect_buttons">
                <li><h1><a href="/test-mvn-app/login">Войти на сайт</a></h1></li>
                <li><h1><a href="/test-mvn-app/">На главную</a></h1></li>
            </ul>
        </div>
    </div>
</body>
</html>
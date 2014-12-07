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
   <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/specialPages.css"/>
</head>
<body>
    <div class="main">
        <div class="logo">
            <a href="/test-mvn-app/"><img src="/test-mvn-app/resources/images/logo.png"></a>
        </div>
        <div class="big_background">
            <div class="input_block">
            <center>
                <div class="input_block_title">
                    <h3>Зарегестрироваться</h3>
                </div>
            </center>
                <div class="input_block_form">
                    <form action="registration" method="POST">
                        Логин: <br/>
                        <div class="input_form ">
                            <input type="name" name="name"><br />
                        </div>
                        Пароль: <br/>
                        <div class="input_form">
                            <input type="password" name="password" /><br />
                        </div>
                        <input type="submit" value="Зарегестрировать" />
                    </form>
                    <br />
                    <br />
                </div>
                <ul class="redirect_buttons">
                        <li><a href="/test-mvn-app/login"><h1>Войти на сайт</h1></a></li>
                        <li><a href="/test-mvn-app/"><h1>На главную</h1></a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
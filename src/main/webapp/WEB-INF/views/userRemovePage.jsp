<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Delete User</title>
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
            <h3>Вы уверены, что хотите удалить аккаунт?</h3>
            <ul class="redirect_buttons">
                <li><form action="delete" method="POST">
                    <input type="hidden" name="postId">
                    <input type="submit" value="Удалить">
                </form></li>
                <li><a href="javascript:history.back()">Отменить</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
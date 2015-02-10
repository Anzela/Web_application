<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/toolbar.css"/>
</head>
<div class="toolbar">
    <ul class="language_buttons">
        <li><a href="?language=ru">RUS</a></li>
        <li><a href="?language=en">ENG</a></li>
    </ul>
    <c:choose>
        <c:when test="${empty currentUser}">
            <ul class="toolbar_buttons">
                <li><a href="/test-mvn-app/registration"><spring:message code="registration"/></a></li>
                <li><a href="/test-mvn-app/login"><spring:message code="login"/></a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <ul class="toolbar_buttons">
                <li><a href="/test-mvn-app/login">Выход</a></li>
                <li><a href="/test-mvn-app/user/${currentUserId}">Личный кабинет</a></li>
            </ul>
        </c:otherwise>
    </c:choose>
</div>
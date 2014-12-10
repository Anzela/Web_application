<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="toolbar">
    <c:choose>
        <c:when test="${empty currentUser}">
            <ul class="toolbar_buttons">
                <li><a href="/test-mvn-app/registration">Регистрация</a></li>
                <li><a href="/test-mvn-app/login">Вход</a></li>
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
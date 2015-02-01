<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div class="footer">
    <p>Всего тем: <c:out value="${postsCount}"/></p>
    <p>Всего комментариев: <c:out value="${commentsCount}"/></p>
    <p>Всего пользователей: <c:out value="${usersCount}"/></p>
</div>
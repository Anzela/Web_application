<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
    <div class="content">
        <h1>Welcome!</h1>

        <p>${user.name}</p>
        <p>${user.role.title}</p>

        <h3>Posts:</h3>
        <c:forEach var="post" items="${posts}">
            <div class="post-area">
                <div class="post-content">
                    <h1>${post.title}</h1>
                    <p>${post.text}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</html>
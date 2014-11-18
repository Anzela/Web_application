<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
    <div class="content">
        <c:forEach var="user" items="${users}">
            <div class="userSearchCard">
                <div class="user-name">
                    <li><a href="/test-mvn-app/user/${user.id}">${user.name}</a></li>
                </div>
                <div class="user-role">
                    <p>${user.role.title}</p>
                </div>
            </div>
        </c:forEach>
    </div>
</html>
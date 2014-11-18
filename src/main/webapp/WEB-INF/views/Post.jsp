<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
    <div class="content">
        <div class="left-column">
            <h1>${post.title}</h1>
            <p>${post.text}</p>
        </div>
    </div>
</html>
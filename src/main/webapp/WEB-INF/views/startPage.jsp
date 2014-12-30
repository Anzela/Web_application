<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM</title>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
</head>
<body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="left-column">
            <div class="post-head">
                 Общение с администрацией
            </div>
            <c:forEach var="section" items="${adminSections}">
                <a href="/test-mvn-app/${section}">
                    <div class="post-content">
                        <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                        <h1>${section.title}</h1>
                        <p>${section.description}</p>
                    </div></a>
            </c:forEach>

            <div class="post-head">
                Темы форума
            </div>
            <c:forEach var="section" items="${userSections}">
                <a href="/test-mvn-app/${section}">
                    <div class="post-content">
                        <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                        <h1>${section.title}</h1>
                        <p>${section.description}</p>
                    </div></a>
            </c:forEach>
        </div>
    </div>
    <div class="footer">
    </div>
</body>
</html>
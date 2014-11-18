<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
<body>
    <div class="content">
        <div class="left-column">
            <div class="post-head">
                <a href="/test-mvn-app/forum/NEWS"><h1>Новости</h1></a>
            </div>
            <c:forEach var="post" items="${posts}">
                <div class="post-area">
                    <div class="post-content">
                        <img src="/test-mvn-app/resources/images/news_icon.png" class="imgStyle"/>
                        <a href="/test-mvn-app/forum/NEWS/${post.id}"><h1>${post.title}</h1></a>
                        <p>${post.text}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="right-column">
            <div class="post-head">
                <h1>Последние темы</h1>
            </div>
        </div>
    </div>
</body>
</html>

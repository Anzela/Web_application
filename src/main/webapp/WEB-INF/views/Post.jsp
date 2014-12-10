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
            <div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <h1>${post.title}</h1>
                    <p>${post.text}</p>
                </div>
            </div></a>

            <br></br>
            <h1>Комментарии:</h1>
            <div class="comment-area">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment-text">
                        <li><p>${comment.text}</p></li>
                        <li><img src="/test-mvn-app/resources/images/deleteIcon.png"/></li>
                    </div>
                </c:forEach>
            </div>
        </div>
    <div class="footer">
    </div>
</body>
</html>
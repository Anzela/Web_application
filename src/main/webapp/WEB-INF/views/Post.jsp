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
            <div class="post-content">
                <a href="/test-mvn-app/forum/"><p>Форум</p></a>
            </div>



            <br></br>
            <h1>${post.title}</h1>
            <p>${post.text}</p>
            <br></br>

            <h1>Комментарии:</h1>
            <div class="comment-area">
            <c:forEach var="comment" items="${comment}">
                <li><h1></h1></li>

                <li><div class="comment-text">
                            <p>${ comment.text}</p>
                </div></li>
                <li><img src="/test-mvn-app/resources/images/deleteIcon.png"/></li>
            </c:forEach>
        </div>
    </div>
</body>
</html>
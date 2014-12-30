<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Post</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
</head>
<body>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
    <div class="content">
        <div class="left-column">
            <div class="post-content">
                <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                <h1>${post.title}</h1>
                <p>${post.text}</p>
                <p>Тема создана: ${data}</p>
            </div>
            <c:if test="${canDelete}">
                <form action="delete" method="POST">
                    <input type="hidden" name="postId" value = "${post.id}">
                    <input type="submit" value="Удалить">
                </form>
            </c:if>

            <h1>Комментарии:</h1>
            <div class="comment-area">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment-text">
                        <p>${comment.text}</p>
                        <c:if test="${comment.authorId == currentUserId}">
                            <form action="" method="POST">
                                <input type="hidden" name="commentId" value = "${comment.id}">
                                <input type="submit" value="Удалить">
                            </form>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="footer">
    </div>
</body>
</html>
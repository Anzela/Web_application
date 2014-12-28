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
            <li><div class="post-area">
                <div class="post-content">
                    <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                    <h1>${post.title}</h1>
                    <p>${post.text}</p>
                    <em><small><p>Тема создана: ${data}</p></small></em>
                </div>
            </div></li>

            <li><c:if test="${canDelete}">
                <form action="delete" method="POST">
                    <input type="hidden" name="postId" value = "${post.id}">
                    <input type="submit" value="Удалить">
                </form>
            </c:if></li>

            <br><br><br><br><br><br><br><br>
            <h1>Комментарии:</h1>
            <div class="comment-area">
                <c:forEach var="comment" items="${comments}">
                    <div class="comment-text">
                        <li><p>${comment.text}</p></li>
                        <c:if test="${comment.authorId == currentUserId}">
                            <li><form action="" method="POST">
                                <input type="hidden" name="commentId" value = "${comment.id}">
                                <input type="submit" value="Удалить">
                            </form></li>
                        </c:if>
                        <c:if test="${not empty error}">
                            <font color="red">*${error}</font>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    <div class="footer">
    </div>
</body>
</html>
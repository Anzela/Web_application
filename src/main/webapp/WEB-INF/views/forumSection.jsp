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
            <h1>Создать новую тему:</h1></a>
                <form action="" method="POST">
                Название темы: <input type="postTitle" name="postTitle"><br />
                Текст: <input type="postText" name="postText" /><br />
                <input type="submit" value="Создать" />
                </form>
                <br />
            <c:forEach var="post" items="${posts}">
                <div class="post-area">
                    <div class="post-content">
                        <a href="/test-mvn-app/forum/${post.section}/${post.id}"><h1>${post.title}</h1></a>
                        <p>${post.text}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</html>
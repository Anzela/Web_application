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
            <h1>Форум: ${section.title}</h1><br />
            <right>
            <h1>Создать новую тему:</h1>
                <form action="" method="POST">
                Название темы: <input type="postTitle" maxlength=150 name="postTitle"><br />
                Текст: <input type="postText" maxlength=10000 name="postText" /><br />
                <input type="submit" value="Создать" />
                </form>
                <br />
            </right>

            <c:forEach var="post" items="${posts}">
                <a href="/test-mvn-app/${post.section}/${post.id}"><div class="post-area">
                    <div class="post-content">
                        <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                        <h1>${post.title}</h1>
                        <p>${post.text}</p>
                        <em><small><p>Тема создана: ${data}</p></small></em>
                    </div>
                </div></a>
            </c:forEach>
        </div>
    </div>
    <div class="footer">
    </div>
</body>
</html>
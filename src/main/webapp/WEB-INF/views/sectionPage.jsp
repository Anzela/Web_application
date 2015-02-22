<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM Login</title>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/popUp.css"/>
    <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="/test-mvn-app/resources/js/script.js"></script>

</head>
<body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>
    <div class="content">
        <c:choose>
            <c:when test="${empty currentUser}">
                <a href="/test-mvn-app/login"><div class="button"><spring:message code="createTheme"/></div></a>
            </c:when>
            <c:otherwise>
                <a href="" onclick="openPopUp('popUp'); return false;"><div class="button">Создать новую тему</div></a>
            </c:otherwise>
        </c:choose>
        <ul class="redirect_links">
            <li><a href="/test-mvn-app/">Разделы</a></li>
            <li>-</li>
            <li>${section.title}</li>
        </ul><br>

        <div class="post-column">
            <c:if test="${empty posts}">
                <p>В данном разделе пока нет ни одной темы.</p>
                <p>Вы можете создать свою тему, чтобы не было так пусто =)</p>
            </c:if>
            <c:if test="${not empty posts}">
                <table>
                    <tr>
                        <th>${section.title}</th>
                        <th>Просмотры</th>
                        <th>Комментарии</th>
                    </tr>
                    <c:forEach var="post" items="${posts}">
                    <tr>
                        <td width="900"><a href="/test-mvn-app/${section}/${post.id}">
                            <div class="post-content">
                                <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                                <h1>${post.title}</h1>
                                <p>${post.text}</p>
                                <p>Тема создана: ${post.formattedCreationDate}</p>
                            </div></a>
                        </td>
                        <td align="center">
                        ${post.viewCount}
                        </td>
                        <td align="center">
                        ${post.commentCount}
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>

    <div class="popUp_w __close" id="popUp">
        <div class="popUp">
            <div class="popUp_cnt">
                <div class="popUp_actions">
                    <a href="" onclick="closePopUp('popUp', errorTextId); return false;"><img src="/test-mvn-app/resources/images/x_icon.png"></a>
                </div>
                <div class="popUp_t"><h1>Создать новую тему:</h1></div>
                <div class="popUp_tx">
                    <form action="" method="POST">
                    Название темы: <input type="text" maxlength=150 name="postTitle"><br>
                    Текст: <input type="text" maxlength=10000 name="postText" /><br>
                    <input type="submit" value="Создать"/></form>
                </div>
                <div class="popUp_error">
                    <c:if test="${not empty postError}">
                        <div id="errorTextId">
                            <p>${postError}</p>
                        </div>
                        <script>
                            openPopUp('popUp');
                        </script>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
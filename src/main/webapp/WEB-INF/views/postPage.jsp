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
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/comment.css"/>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/popUp.css"/>
    <script src="/test-mvn-app/resources/js/jquery.js"></script>
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
                <a href="" onclick="openPopUp('themePopup'); return false;"><div class="button"><spring:message code="createTheme"/></div></a>
            </c:otherwise>
        </c:choose>

        <div class="left-column">
            <div class="post-content">
                <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                <h1>${post.title}</h1>
                <p>${post.text}</p>
                <c:if test="${not empty post.photoBytes}">
                    <img src="/test-mvn-app/photo/${post.id}">
                </c:if>
                <p>Тема создана: ${postCreationDate}</p>
            </div>
            <c:if test="${canManagePost}">
                <form action="delete" method="POST">
                    <input type="hidden" name="postId" value = "${post.id}">
                    <input type="submit" value="Удалить">
                </form>
                <form action="/test-mvn-app/${post.section}/${post.id}/editPost">
                    <input type="submit" value="Редактировать">
                </form>
            </c:if>
        </div>
    </div>

    <div class="comments">
        <div class="comment-area">

            <c:if test="${empty comments}">
                <p>Пока нет ни одного комментария к данной теме.</p>
                <p>Вы можете создать свой комментарий, чтобы не было так пусто =)</p>
            </c:if>

            <c:choose>
                <c:when test="${empty currentUser}">
                    <a href="/test-mvn-app/login"><div class="button"><spring:message code="createComment"/></div></a>
                </c:when>
                <c:otherwise>
                    <a href="" onclick="openPopUp('commentPopup'); return false;"><div class="button"><spring:message code="createComment"/></div></a>
                </c:otherwise>
            </c:choose>

            <c:forEach var="comment" items="${comments}">
                <div class="comment-area">
                    <div class="comment-text">
                        <p>${comment.text}</p>
                        <c:if test="${comment.canDelete}">
                            <form action="deleteComment" method="POST">
                                <input type="hidden" name="commentId" value = "${comment.id}">
                                <input type="submit" value="Удалить">
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>

    <div class="popUp_w __close" id="themePopup">
        <div class="popUp">
            <div class="popUp_cnt">
                <div class="popUp_actions">
                    <a href="" onclick="closePopUp('themePopup', 'themeErrorTextId'); return false;"><img src="/test-mvn-app/resources/images/x_icon.png"></a>
                </div>
                <div class="popUp_t"><h1>Создать новую тему:</h1></div>
                <div class="popUp_tx">
                    <form action="./" method="POST" enctype="multipart/form-data">
                        Название темы: <input type="text" maxlength=150 name="postTitle"><br>
                        Текст: <input type="text" maxlength=10000 name="postText" /><br>
                        Загрузка картинки: <input type="file" name="file"><br/>
                    <input type="submit" value="Создать"/>

                    <div class="popUp_error">
                        <c:if test="${not empty postError}">
                            <div id="themeErrorTextId">
                                <p>${postError}</p>
                            </div>
                            <script>
                                openPopUp('themePopup');
                            </script>
                        </c:if>
                    </div></form>
                </div>
            </div>
        </div>
    </div>

    <div class="popUp_w __close" id="commentPopup">
        <div class="popUp">
            <div class="popUp_cnt">
                    <div class="popUp_actions">
                        <a href="" onclick="closePopUp('commentPopup', 'commentErrorTextId'); return false;"><img src="/test-mvn-app/resources/images/x_icon.png"></a>
                    </div>
                    <div class="popUp_t"><h1>Создать новый комментарий:</h1></div>
                    <div class="popUp_tx">
                        <form action="" method="POST">
                        Текст: <input type="text" maxlength=5000 name="commentText" /><br>
                        <input type="submit" value="Создать"/>

                        <div class="popUp_error">
                            <c:if test="${not empty commentError}">
                                <div id="commentErrorTextId">
                                    <p>${commentError}</p>
                                </div>
                                <script>
                                    openPopUp('commentPopup');
                                </script>
                            </c:if>
                        </div></form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
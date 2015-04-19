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
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
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

                <script>
                    function refresh() {
                        $.get( "http://localhost:9090/test-mvn-app/api/${post.section}/${post.id}",
                                function(data) {
                                    var html = "";

                                    for (var i = 0; i < data.comments.length; i++) {
                                        html += "<span>";
                                        html += data.comments[i].text;
                                        html += "</span>";
                                        html += "<br>";
                                    }

                                    $("#comments").html(html);
                                    $("#showComments").html("");
                                }
                        );
                    }

                    function addComment() {
                        var comment = $("#comment").val();
                        $.post("http://localhost:9090/test-mvn-app/${post.section}/${post.id}",
                                {text : comment, postId : ${post.id}, authorId : currentUser.id})
                                .done (function (data) {
                                    $("#comment").val("");
                                    refresh();
                                });
                    }

                </script>

                <div id="comments">

                </div>


                <div class="keys">
                    <div id="showComments">
                        <input type="button" onclick="refresh();" value="Читать все комментарии"/>
                    </div>
                    <p><input id="comment" type="text"/></p>
                    <input type="button" onclick="addComment();" value="Добавить новый комментарий"/>
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
</body>
</html>
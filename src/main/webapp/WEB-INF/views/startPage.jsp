<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
    <title>FORUM</title>
    <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/post.css"/>
</head>
<body>
    <jsp:include page="toolbar.jsp"/>
    <jsp:include page="header.jsp"/>
    <div class="content">
        <div class="post-column">
            <table>
                <tr>
                    <th>Общение с администрацией</th>
                    <th>Темы</th>
                    <th width="230">Последняя тема</th>
                </tr>
                <c:forEach var="section" items="${adminSections}">
                <tr>
                    <td><a href="/test-mvn-app/${section}">
                        <div class="post-content">
                            <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                            <h1>${section.title}</h1>
                            <p>${section.description}</p>
                        </div></a>
                    </td>
                    <td>

                    </td>
                    <td>

                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <th>Темы форума</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                </tr>
                <c:forEach var="section" items="${userSections}">
                <tr>
                    <td><a href="/test-mvn-app/${section}">
                        <div class="post-content">
                            <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                            <h1>${section.title}</h1>
                            <p>${section.description}</p>
                        </div></a>
                    </td>
                    <td>

                    </td>
                    <td>

                    </td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</body>
</html>
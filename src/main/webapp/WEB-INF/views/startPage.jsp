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
            <div class="post-head">
                 <h1>Общение с администрацией</h1>
            </div>
                <c:forEach var="section" items="${adminSections}">
                    <a href="/test-mvn-app/${section}"><div class="post-area">
                        <div class="post-content">
                            <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                            <h1>${section.title}</h1>
                            <p>${section.description}</p>
                        </div>
                    </div></a>
                </c:forEach>

            <div class="post-head">
                <h1>Темы форума</h1>
            </div>
                <c:forEach var="section" items="${userSections}">
                    <a href="/test-mvn-app/${section}"><div class="post-area">
                        <div class="post-content">
                            <img src="/test-mvn-app/resources/images/topic_icon.png" class="imgStyle"/>
                            <h1>${section.title}</h1>
                            <p>${section.description}</p>
                        </div>
                    </div></a>
                </c:forEach>
        </div>
    </div>
    <div class="footer">
    </div>
</body>
</html>
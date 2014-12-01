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
        <center>
        <form action="login" method="POST" modelAttribute="loginInfo">
            <h3>Авторизируйтесь/<a href="/test-mvn-app/registration">регистрация</a></h3>
            Имя: <input type="name" name="name"><br />
            Текст: <input type="password" name="password" /><br />
            <input type="submit" value="Авторизироваться" />
        </form>
        <c:if test="${not empty error}">
            <font color="red">*Неправильно введен логин или пароль</font>
        </c:if>
        </center>
    </div>
</body>
</html>
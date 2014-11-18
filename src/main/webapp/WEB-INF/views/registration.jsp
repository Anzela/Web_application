<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<jsp:include page="toolbar.jsp"/>
<jsp:include page="header.jsp"/>
    <div class="content">
        <center>
            <form action="" method="POST">
                Имя: <input type="userName" name="userName"><br />
                Текст: <input type="password" name="password" /><br />
                <input type="submit" value="Создать" />
            </form>
        </center>
        </div>
    </div>
</html>
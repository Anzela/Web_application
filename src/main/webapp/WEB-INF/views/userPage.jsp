<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>


<!DOCTYPE html>
<html>
<body>

<h1>Welcome!</h1>

<p>${user.name}</p>
<p>${user.gender}</p>
<p>${user.age}</p>
<p>${user.role}</p>

<h3>Posts:</h3>

</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/reset.css"/>
   <link rel="stylesheet" type="text/css" href="/test-mvn-app/resources/css/main.css"/>
</head>
   <div class="header">
       <div class="logo">
           <a href="/test-mvn-app/"><img src="/test-mvn-app/resources/images/logo.png"></a>
       </div>
   </div>
   <ul class="menu">
       <a href="/test-mvn-app/"><li><spring:message code="forum"/></li></a>
       <a href="/test-mvn-app/users"><li><spring:message code="users"/></li></a>
       <li><spring:message code="gallery"/></li>
       <a href="/test-mvn-app/calculator"><li><spring:message code="calculator"/></li></a>
   </ul>
</div>

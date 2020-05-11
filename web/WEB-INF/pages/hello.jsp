<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/6/2020
  Time: 10:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Hello Ya Java</title>
</head>
<body>
<h1>Hello Page</h1>
<%--<a href="<c:url value="/logOut" var="logoutUrl" />">Logout</a>--%>
<a href="logOut">logout</a>

<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

</form>
<script>
    function formSubmit() {
            document.getElementById("logoutForm").submit();
    }
</script>
<c:if test="${pageContext.request.userPrincipal.name !=null}">
    <h2>
        Welcome : ${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()">Log Out</a>
    </h2>
</c:if>
<%--<a href="/logout">logout</a>--%>
</body>
</html>

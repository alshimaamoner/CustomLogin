<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/7/2020
  Time: 3:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<sec:authorize access="hasRole('ADMIN')">
    Hello Ya Admin

</sec:authorize>
<sec:authorize access="hasRole('USER')">
    Hello Ya User

</sec:authorize>
<a href="<c:url value="/logOut" />"> Logout</a>
<c:url value="/logOut" var="logoutUrl">
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
    <script>
        function  formSubmit() {
           document.getElementById("logoutForm").submit();
        }
    </script>
    <c:if test="${pageContext.request.userPrincipal.name !=null}">
        <h2>
            Welcome:${pageContext.request.userPrincipal.name} | <a href="javascript:formSubmit()">logOut</a>
        </h2>
    </c:if>

</c:url>

</div>
</body>
</html>

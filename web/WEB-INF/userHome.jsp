<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.DefaultCsrfToken"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: eds2809
  Date: 10.02.2020
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User profile</title>
</head>
<body>
<label>Вы успешно авторизовались</label><br><br><br><br>

<label>Логин: </label><label><c:out value="${user.login}"/></label><br>
<label>Пароль BCrypt: </label><label><c:out value="${user.pass}"/></label><br>
<label>Роли: </label>

<label><c:forEach var="role" items="${user.roles}">
    ${role.role}
</c:forEach></label><br>

<br>
<br>
<br>

<label>ID сессии: </label><label><c:out value="${pageContext.session.id}"/></label><br>

<form method="POST" action="${pageContext.request.contextPath}/logout">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit">выйти</button>
</form>


</body>
</html>

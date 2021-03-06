<%--@elvariable id="_csrf" type="org.springframework.security.web.csrf.DefaultCsrfToken"--%>
<%@ page language="java" contentType="text/html; charset=UTF-16"
         pageEncoding="UTF-16"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <style>
        table {
            table-layout: fixed; /* ????????????? ?????? ????? */
            width: 100%; /* ?????? ??????? */
            word-wrap: break-word;
        }
        .col1 { width: 160px; }
        .coln { width: 60px; }
    </style>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin Panel</title>
</head>
<body>
<form method="GET" action="${pageContext.request.contextPath}/">
    <button type="submit">профиль</button>
</form>
<br>
<br>
<form action="${pageContext.request.contextPath}/admin/add-new-user" method="POST">
    <label>LOGIN</label>
    <input type="text" name="login">
    <label>PASS</label>
    <input type="text" name="pass">
    <label>EMAIL</label>
    <input type="text" name="email">
    <select name='role'>
        <c:forEach items="${roles}" var="role">
            <option value="${role.role}">${role.role}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="ADD">
</form>






<c:if test="${fn:length(users) > 0}">
    </table>
    <table border="1" cellspacing="0" cellpadding="2">
        <br>
        <br>
        <tr>
            <td>ID</td>
            <td>LOGIN</td>
            <td>PASS</td>
            <td>EMAIL</td>
            <td>ROLE</td>
        </tr>

        <jsp:useBean id="users" scope="request" type="java.util.List"/>
        <c:forEach items="${users}" var="user" >

            <tr>
                <td>
                        ${user.id}
                </td>
                <td>
                        ${user.login}
                </td>

                <td>
                        ${user.pass}
                </td>

                <td>
                        ${user.email}
                </td>

                <td>
                    <c:forEach var="role" items="${user.roles}">
                        <p>${role.role}</p>
                    </c:forEach>
                </td>
                <td>
                    <input type="button" value="update" onClick='location.href="${pageContext.request.contextPath}/admin/update/"+${user.id}'>
                    <input type="button" value="delete" onClick='location.href="${pageContext.request.contextPath}/admin/delete/"+${user.id}'>
                </td>
            </tr>

        </c:forEach>
    </table>
</c:if>

</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>List of users</title>
    </head>
    <body>
        <table>
            <tr><th>id</th><th>fullName</th></tr>
            <c:forEach var="user" items="${users}">
            <tr>
            <td>${user.get("id")}</td>
            <td><a href='/users/show?id=${user.get("id")}'>${user.get("firstName")} ${user.get("lastName")}</a></td>
            </tr>
            </c:forEach>
        <table>
    </body>
</html>
<!-- END -->

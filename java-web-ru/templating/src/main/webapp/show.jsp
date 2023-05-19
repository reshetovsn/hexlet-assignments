<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>User</title>
    </head>
    <body>
        <table>
            <tr><td>firstName: ${user.get("firstName")}</td></tr>
            <tr><td>lastName: ${user.get("lastName")}</td></tr>
            <tr><td>id: ${user.get("id")}</td></tr>
            <tr><td>email: ${user.get("email")}</td></tr>
            <td><a href='/users/delete?id=${user.get("id")}'>Delete user</a></td>
        <table>
    </body>
</html>
<!-- END -->

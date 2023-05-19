<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>DeletePage</title>
    </head>
    <body>
    Do you really want to delete user?
        <table>
            <tr><td>firstName: ${user.get("firstName")}</td></tr>
            <tr><td>lastName: ${user.get("lastName")}</td></tr>
            <tr><td>id: ${user.get("id")}</td></tr>
            <tr><td>email: ${user.get("email")}</td></tr>
        <table>
    <form action='/users/delete?id=${user.get("id")}' method="post">
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    </body>
</html>
<!-- END -->

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
    <form action='/users/delete?id=${user.get("id")}' method="post">
        <button type="submit" class="btn btn-danger">Удалить</button>
    </form>
    </body>
</html>
<!-- END -->

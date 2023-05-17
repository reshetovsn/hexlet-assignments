package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Map<String, Object>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        Path filePath = Paths.get("src/main/resources/users.json");
        String data = Files.readString(filePath);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data, new TypeReference<List<Map<String, Object>>>(){});
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, Object>> users = getUsers();

        StringBuilder body = new StringBuilder();
        body.append("""
            <!DOCTYPE html>
            <html lang=\"ru\">
                <head>
                    <meta charset=\"UTF-8\">
                    <title>List of users</title>
                </head>
                <body>
                    <table>
                    <tr><th>id</th><th>fullName</th></tr>
            """);
        for (var user : users) {
            body.append("<tr>" +
                    "<td>" + user.get("id")  + "</td>" +
                    "<td>" +
                    "<a href=\"/users/" + user.get("id") + "\">" +
                    user.get("firstName") + " " + user.get("lastName") + "</a>" +
                    "</td>"
                    + "</tr>");
        }
        body.append("""
                    <table>
                </body>
            </html>
            """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, Object>> users = getUsers();
        StringBuilder body = new StringBuilder();

        for (var user : users) {
            if(user.get("id").equals(id)) {
                body.append("""
                    <!DOCTYPE html>
                    <html lang=\"ru\">
                        <head>
                            <meta charset=\"UTF-8\">
                            <title>List of users</title>
                        </head>
                        <body>
                            <table>
                    """);
                body.append(
                        "<tr><td>" + "firstName: " + user.get("firstName")  + "</td></tr>" +
                        "<tr><td>" + "lastName: " + user.get("lastName")  + "</td></tr>" +
                        "<tr><td>" + "id: " + user.get("id")  + "</td></tr>" +
                        "<tr><td>" + "email: " + user.get("email")  + "</td></tr>");
                body.append("""
                            <table>
                        </body>
                    </html>
                    """);
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(body);
                return;
            }
        }

        response.sendError(404);
        // END
    }
}

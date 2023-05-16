package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        PrintWriter out = response.getWriter();
        String reqStr = request.getQueryString();
        String searchLine = request.getParameter("search");

        if (reqStr == null || searchLine == ""|| !reqStr.contains("search") ) {
            companies.forEach(out :: println);
        }

        List<String> matchedCompanies = companies.stream()
                .filter(x -> x.contains(searchLine))
                .collect(Collectors.toList());

        if (matchedCompanies.isEmpty()) {
            out.println("Companies not found");
        }

        matchedCompanies.forEach(out::println);
        // END
    }
}

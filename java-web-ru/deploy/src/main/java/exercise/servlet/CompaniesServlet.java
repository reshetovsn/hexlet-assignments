package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = new ArrayList<>(getCompanies());
        PrintWriter out = response.getWriter();

        if (request.getQueryString() == null) {
            String allCompanies = companies.stream()
                    .collect(Collectors.joining("\n")).replaceAll("[\\[\\]]", "");
            out.println(allCompanies);

        } else if (companies.stream()
                .noneMatch(x -> x.contains(request.getParameter("search")))) {
            out.println("Companies not found");

        } else {
            List<String> matchedCompanies = Collections.singletonList(companies.stream()
                    .filter(x -> x.contains(request.getParameter("search")))
                    .collect(Collectors.joining("\n")).replaceAll("[\\[\\]]", ""));
            out.println(matchedCompanies);
        }
        // END
    }
}

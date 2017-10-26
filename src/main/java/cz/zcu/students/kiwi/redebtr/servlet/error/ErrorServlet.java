package cz.zcu.students.kiwi.redebtr.servlet.error;

import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = "/ErrorHandler")
public class ErrorServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processError(req, resp);
    }

    private void processError(HttpServletRequest req,
                              HttpServletResponse resp) throws IOException, ServletException {

        // Analyze the servlet exception
        Throwable throwable = (Throwable) req.getAttribute("javax.servlet.error.exception");


        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");

        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        req.setAttribute("errorStatusCode", statusCode);
        req.setAttribute("requestedUri", requestUri);
        req.setAttribute("requestedServlet", servletName);

        if (throwable != null) {
            req.setAttribute("errorName", throwable.getMessage());

            req.setAttribute("exception", Arrays.toString(throwable.getStackTrace()).replaceAll(", ", "\n"));
        }

        this.renderJsp(req, resp, "error/genericError");

    }
}

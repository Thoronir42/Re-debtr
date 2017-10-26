package cz.zcu.students.kiwi.redebtr.servlet.error;

import cz.zcu.students.kiwi.libs.servlet.ErrorCodeException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ErrorCodeHandler")
public class ErrorCodeServlet extends ErrorServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("javax.servlet.error.exception", new ErrorCodeException());
        try{
            super.doGet(req, resp);
        } catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post ech");
        super.doPost(req, resp);

    }
}

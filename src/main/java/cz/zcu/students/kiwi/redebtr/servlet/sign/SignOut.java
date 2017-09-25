package cz.zcu.students.kiwi.redebtr.servlet.sign;

import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignOut extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Logout pls");

        resp.sendRedirect("/");
    }
}

package cz.zcu.students.kiwi.redebtr.servlet.sign;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet(name = "Sign/InServlet", urlPatterns = "/sign/in")
public class SignIn extends BaseServlet {

    private static final String USERNAME_PARAMETER = "username";
    private static final String PASSWORD_PARAMETER = "password";

    private static final String ERR_ATTRIBUTE = "err";

    private AuthenticationService authService;

    @Autowired
    public void setAuthService(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.renderJsp(req, resp, "sign/in");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter(USERNAME_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        boolean authenticated = authService.authenticate(req.getSession(), username, password);
        if(authenticated) {
            resp.sendRedirect("secret/vip");
        } else {
            req.setAttribute(ERR_ATTRIBUTE, "Invalid credentials!");

            this.renderJsp(req, resp, "sign/in");
        }
    }
}

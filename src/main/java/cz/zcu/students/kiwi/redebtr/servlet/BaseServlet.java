package cz.zcu.students.kiwi.redebtr.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cz.zcu.students.kiwi.libs.domain.User;
import cz.zcu.students.kiwi.libs.security.IUser;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

public abstract class BaseServlet extends HttpServlet {

    protected AutowireCapableBeanFactory ctx;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        ctx = context.getAutowireCapableBeanFactory();
        ctx.autowireBean(this);
    }

    protected void dispatch(HttpServletRequest req, HttpServletResponse resp, String template) throws ServletException, IOException {
        req.setAttribute("pageTemplate", template);
        req.setAttribute("user", (IUser) () -> System.currentTimeMillis() % 2 == 0);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

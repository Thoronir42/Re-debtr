package cz.zcu.students.kiwi.redebtr.servlet.post;

import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/post", name = "PostServlet")
public class PostServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String targetDashboard = req.getParameter("targetDashboard");
        String type = req.getParameter("postType");
        String text = req.getParameter("text");

        System.out.println("Posting " + type + ", to " + targetDashboard);
        System.out.println("Post content: " + text);

        resp.sendRedirect("/user/profile?u=" + targetDashboard);

    }
}

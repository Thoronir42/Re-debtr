package cz.zcu.students.kiwi.redebtr.servlet.user;


import com.sun.deploy.net.HttpResponse;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDaoJpa;
import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/user/profile", name = "UserProfileServlet")
public class UserProfileServlet extends BaseServlet {

    private PostDaoJpa postDao;

    @Autowired
    public void setPostDao(PostDaoJpa postDao) {
        this.postDao = postDao;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("u");

        if (query == null || query.length() < 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (query.matches("\\d+")) {
            resp.sendError(Integer.parseInt(query), "Some message");
            return;
        }


        UserProfile profile = new UserProfile(query);
        List<UserProfile> friends = new ArrayList<>();

        friends.add((new UserProfile("Carl")).setName("Carl", "Optic"));
        friends.add((new UserProfile("Petr")).setName("Petr", "Carrot"));
        friends.add((new UserProfile("Tina")).setName("Tina", "Box"));
        friends.add((new UserProfile("Pedro")).setName("Pedro", "Banana"));

        profile.setName(query, "Peep")
                .setConnections(friends);

        req.setAttribute("profile", profile);

        req.setAttribute("posts", this.postDao.findPost());


        this.renderJsp(req, resp, "user/profile");
    }
}

package cz.zcu.students.kiwi.redebtr.servlet.user;


import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDaoJpa;
import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/user/profile", name = "UserProfileServlet")
public class UserProfileServlet extends BaseServlet {

    private PostDaoJpa postDao;

    @Autowired
    public void setPostDao(PostDaoJpa postDao) {
        this.postDao = postDao;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        UserProfile profile = new UserProfile();
        List<UserProfile> friends = new ArrayList<>();

        friends.add((new UserProfile()).setFirstName("Carl").setLastName("Optic"));
        friends.add((new UserProfile()).setFirstName("Petr").setLastName("Carrot"));
        friends.add((new UserProfile()).setFirstName("Tína").setLastName("Box"));
        friends.add((new UserProfile()).setFirstName("Pedro").setLastName("Banana"));

        profile.setFirstName(username)
                .setLastName("Peep")
                .setConnections(friends);

        req.setAttribute("profile", profile);

        req.setAttribute("posts", this.postDao.findPost());


        this.dispatch(req, resp, "user/profile");
    }
}

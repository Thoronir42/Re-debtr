package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user/")
public class ProfileController extends BaseController {

    private PostDaoJpa postDao;

    @Autowired
    public void setPostDao(PostDaoJpa postDao) {
        this.postDao = postDao;
    }

    @RequestMapping("profile")
    public String h() {
        return "home/guest.jsp";
    }

    //@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView getProfile(ModelMap model, @PathVariable long id) {
        String query = "" + id;
        if (id < 1 || query.length() < 1) {
            return this.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }

        if (query.matches("\\d+")) {
            return this.sendError(Integer.parseInt(query), "Some message");
        }


        UserProfile profile = new UserProfile(query);
        List<UserProfile> friends = new ArrayList<>();

        friends.add((new UserProfile("Carl")).setName("Carl", "Optic"));
        friends.add((new UserProfile("Petr")).setName("Petr", "Carrot"));
        friends.add((new UserProfile("Tina")).setName("Tina", "Box"));
        friends.add((new UserProfile("Pedro")).setName("Pedro", "Banana"));

        profile.setName(query, "Peep")
                .setConnections(friends);

        model.put("profile", profile);

        model.put("posts", this.postDao.findPost());


        return new ModelAndView("user/profile", model);
    }


}

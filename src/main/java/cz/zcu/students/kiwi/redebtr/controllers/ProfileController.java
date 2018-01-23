package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.exceptions.BadRequestException;
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

    @Autowired
    private PostDaoJpa postDao;

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    public ModelAndView getProfile(ModelMap model, @PathVariable String id) {
        if (id.length() < 1) {
            throw new BadRequestException("Id must not be empty");
        }


        UserProfile profile = new UserProfile().setLocator(id).setName(id, "Peep");
        List<UserProfile> contacts = new ArrayList<>();

        contacts.add((new UserProfile()).setLocator("Carl").setName("Carl", "Optic"));
        contacts.add((new UserProfile()).setLocator("Petr").setName("Petr", "Carrot"));
        contacts.add((new UserProfile()).setLocator("Tina").setName("Tina", "Box"));
        contacts.add((new UserProfile()).setLocator("Pedro").setName("Pedro", "Banana"));

        model.put("profile", profile);
        model.put("contacts", contacts);

        model.put("posts", this.postDao.findPost());

        return new LayoutMAV("user/profile.jsp", model);
    }


}

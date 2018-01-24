package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.exceptions.BadRequestException;
import cz.zcu.students.kiwi.libs.exceptions.NotFoundException;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.PostDaoJpa;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/user/")
public class ProfileController extends BaseController {

    @Autowired
    private UserProfileDao profiles;

    @Autowired
    private PostDaoJpa postDao;

    // regex because trailing dot is truncated otherwise
    @RequestMapping(value = "/profile/{locator:.+}", method = RequestMethod.GET)
    public ModelAndView getProfile(ModelMap model, @PathVariable String locator) {
        if (locator.length() < 1) {
            throw new BadRequestException("Id must not be empty");
        }

        UserProfile profile = profiles.findByLocator(locator);
        if(profile == null) {
            throw new NotFoundException("User " + locator + " could not be found");
        }

        List<UserProfile> contacts = profiles.findConnectionsOf(profile);

        model.put("profile", profile);
        model.put("contacts", contacts);

        model.put("posts", this.postDao.prepareDashboard(profile));

        return new LayoutMAV("user/profile.jsp", model);
    }


}

package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.exceptions.ForbiddenException;
import cz.zcu.students.kiwi.libs.exceptions.NotFoundException;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController extends BaseController {

    @Autowired
    UserDao users;

    @Autowired
    UserProfileDao profiles;

    @GetMapping("users")
    public ModelAndView getDefault() {
        return new LayoutMAV("users/list.jsp");
    }

    @GetMapping("users/search")
    public ModelAndView search(HttpServletRequest req, ModelMap model) {
        String search = req.getParameter("q");

        if (search != null && search.length() > 0) {
            List<UserProfile> profiles = this.profiles.searchEverywhere(search);
            model.addAttribute("profiles", profiles);
        }
        model.addAttribute("listCaption", "Search results");

        return new LayoutMAV("users/list.jsp");
    }

    @GetMapping("my-contacts")
    public ModelAndView listMyContacts(HttpServletRequest req, ModelMap model) {
        User currentUser = authHelper.getCurrentUser(req);
        if (currentUser == null) {
            throw new ForbiddenException();
        }

        List<UserProfile> profiles = this.profiles.findConnectionsOf(currentUser.getProfile());
        model.addAttribute("profiles", profiles);

        model.addAttribute("listCaption", "My contacts");

        return new LayoutMAV("users/list.jsp", model);
    }

    @GetMapping("user/{locator}/contacts")
    public ModelAndView viewContacts(HttpServletRequest req, ModelMap model,
                                     @PathVariable String locator) {
        UserProfile profile = profiles.findByLocator(locator);
        if(profile == null) {
            throw new NotFoundException("Profile " + locator + " could not be found");
        }

        List<UserProfile> profiles = this.profiles.findConnectionsOf(profile);
        model.addAttribute("profiles", profiles);

        model.addAttribute("listCaption", "Contacts of " + profile.getFullName());

        return new LayoutMAV("users/list.jsp", model);

    }
}

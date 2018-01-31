package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.auth.AclAction;
import cz.zcu.students.kiwi.libs.auth.AclResource;
import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.exceptions.ForbiddenException;
import cz.zcu.students.kiwi.libs.exceptions.NotFoundException;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.ProfileContactDao;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersController extends BaseController {

    @Autowired
    UserDao users;

    @Autowired
    ProfileContactDao contacts;

    @Autowired
    UserProfileDao profiles;

    @GetMapping("users")
    public ModelAndView getDefault(HttpServletRequest req, ModelMap model) {
        List<UserProfile> users = this.profiles.searchEverywhere("");

        User currentUser = authHelper.getCurrentUser(req);
        if(currentUser != null) {
            users = this.profiles.markProfileContacts(users, currentUser.getProfile());
        }

        model.addAttribute("profiles", users);

        return new LayoutMAV("users/list.jsp", model);
    }

    @GetMapping("users/search")
    public ModelAndView search(HttpServletRequest req, ModelMap model) {
        User currentUser = authHelper.getCurrentUser(req);

        String search = req.getParameter("q");

        if (search != null && search.length() > 0) {
            List<UserProfile> profiles = this.profiles.searchEverywhere(search);
            if (currentUser != null) {
                profiles = this.profiles.markProfileContacts(profiles, currentUser.getProfile());
            }

            model.addAttribute("profiles", profiles);
        }
        model.addAttribute("listCaption", "Search results");

        return new LayoutMAV("users/list.jsp");
    }

    @GetMapping("my-contacts")
    public ModelAndView listMyContacts(HttpServletRequest req, ModelMap model) {
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser authUser = authHelper.getAuthUser(currentUser);

        if (!authUser.isAllowedTo(AclResource.OwnContacts)) {
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
        if (profile == null) {
            throw new NotFoundException("Profile " + locator + " could not be found");
        }

        List<UserProfile> profiles = this.profiles.findConnectionsOf(profile);
        model.addAttribute("profiles", profiles);

        model.addAttribute("listCaption", "Contacts of " + profile.getFullName());

        return new LayoutMAV("users/list.jsp", model);

    }

    @GetMapping("user/{locator}/add-contact")
    public String addContact(HttpServletRequest req, ModelMap model,
                                     @PathVariable String locator) {
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser authUser = authHelper.getAuthUser(currentUser);
        if(!authUser.isAllowedTo(AclResource.ProfileContacts, AclAction.UPDATE)) {
            throw new ForbiddenException("You are not allowed to manage contacts");
        }

        UserProfile profile = profiles.findByLocator(locator);
        if (profile == null) {
            throw new NotFoundException("Profile " + locator + " could not be found");
        }

        contacts.addContact(currentUser.getProfile(), profile);

        return "redirect:/user/profile/" + locator;
    }

    @GetMapping("user/{locator}/remove-contact")
    public String removeContact(HttpServletRequest req, ModelMap model,
                             @PathVariable String locator) {
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser authUser = authHelper.getAuthUser(currentUser);
        if(!authUser.isAllowedTo(AclResource.ProfileContacts, AclAction.UPDATE)) {
            throw new ForbiddenException("You are not allowed to manage contacts");
        }

        UserProfile profile = profiles.findByLocator(locator);
        if (profile == null) {
            throw new NotFoundException("Profile " + locator + " could not be found");
        }

        contacts.removeContact(currentUser.getProfile(), profile);

        return "redirect:/user/profile/" + locator;
    }
}

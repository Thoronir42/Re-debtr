package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.FlashMessage;
import cz.zcu.students.kiwi.libs.auth.AclAction;
import cz.zcu.students.kiwi.libs.auth.AclResource;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.exceptions.ForbiddenException;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/settings/profile")
public class ProfileSettingsController extends BaseController {

    @Autowired
    public UserProfileDao userProfiles;

    private Pattern locatorPattern = Pattern.compile("\\d");

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet(ModelMap model) {
        if (!this.authUser.isAllowedTo(AclResource.UserProfile, AclAction.UPDATE)) {
            throw new ForbiddenException();
        }

        UserProfile profile = userProfiles.findByUser(this.currentUser);
        if (profile == null) {
            profile = new UserProfile(this.currentUser);
        }

        model.put("profile", profile);

        return new LayoutMAV("settings/profile.jsp", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView doPost(HttpServletRequest req, ModelMap model) {
        boolean valid = true;

        UserProfile profile = userProfiles.findByUser(this.currentUser);

        profile
                .setFirstName(req.getParameter("firstName"))
                .setLastName(req.getParameter("lastName"))
                .setLocator(req.getParameter("locator"));

        String firstName = profile.getFirstName();
        if (firstName == null || firstName.length() < 1) {
            model.put("err.firstName", "First name must be an non-empty string");
            valid = false;
        }
        String lastName = profile.getLastName();
        if (lastName == null || lastName.length() < 1) {
            model.put("err.lastName", "Last name must be an non-empty string");
            valid = false;
        }

        String locator = profile.getLocator();
        if (locator == null || locator.length() < 1) {
            model.put("err.lastName", "Custom locator must be an non-empty string");
            valid = false;
        } else {
            if (locatorPattern.matcher(locator).find()) {
                model.put("err.locator", "Locator must not contain numbers");
                valid = false;
            }
        }

        String error;
        if (valid) {
            try {
                userProfiles.update(profile, true);

                return new ModelAndView("redirect:/settings/profile");
            } catch (ValidationException e) {
                error = e.getMessage();
            }
        } else {
            error = "One or more fields contain invalid values";
        }

        model.put("flashMessage", FlashMessage.Error(error));
        model.put("profile", profile);

        return new LayoutMAV("settings/profile.jsp", model);
    }
}

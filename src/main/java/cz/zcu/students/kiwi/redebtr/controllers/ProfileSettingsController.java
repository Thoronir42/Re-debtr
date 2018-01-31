package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.FlashMessage;
import cz.zcu.students.kiwi.libs.auth.AclAction;
import cz.zcu.students.kiwi.libs.auth.AclResource;
import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.exceptions.ForbiddenException;
import cz.zcu.students.kiwi.redebtr.helpers.ExceptionHelper;
import cz.zcu.students.kiwi.redebtr.io.RedebtrFiles;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.regex.Pattern;

@Controller
@RequestMapping(value = "/settings/profile")
public class ProfileSettingsController extends ASettingsController {

    @Autowired
    public UserProfileDao userProfiles;

    @Autowired
    public RedebtrFiles files;

    private Pattern locatorPattern = Pattern.compile("\\d");

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet(ModelMap model, HttpServletRequest req) {
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser authUser = authHelper.getAuthUser(currentUser);

        if (!authUser.isAllowedTo(AclResource.UserProfile, AclAction.UPDATE)) {
            throw new ForbiddenException();
        }

        UserProfile profile = userProfiles.findByUser(currentUser);
        if (profile == null) {
            profile = new UserProfile(currentUser);
        }

        model.put("caption", "Profile settings");

        model.put("profile", profile);

        return new LayoutMAV("settings/profile.jsp", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView doPost(HttpServletRequest req, ModelMap model) {
        boolean valid = true;
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser authUser = authHelper.getAuthUser(currentUser);

        if (!authUser.isAllowedTo(AclResource.UserProfile, AclAction.UPDATE)) {
            throw new ForbiddenException();
        }

        UserProfile profile = userProfiles.findByUser(currentUser);

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

        try {
            Part avatar = req.getPart("avatar");
            if (avatar != null) {
                String fileName = files.saveAvatar(avatar);
                if(fileName != null) {
                    profile.setAvatar(fileName);
                }
            }

        } catch (IOException | ServletException ex) {
            System.err.println(ex.toString());
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

    @RequestMapping(value = "remove-avatar")
    protected String removeAvatar(HttpServletRequest req) {
        User currentUser = authHelper.getCurrentUser(req);
        AuthUser authUser = authHelper.getAuthUser(currentUser);

        if (!authUser.isAllowedTo(AclResource.UserProfile, AclAction.UPDATE)) {
            throw new ForbiddenException();
        }

        UserProfile profile = userProfiles.findByUser(currentUser);

        profile.setAvatar(null);
        userProfiles.update(profile);

        return "redirect:/settings/profile";
    }
}

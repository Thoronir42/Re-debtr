package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.redebtr.model.UserProfile;
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

    private UserProfile profile = new UserProfile().setName("Karel", "Petrklíč").setLocator("kapetr");

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet(ModelMap model){
        model.put("profile", this.profile);

        return new LayoutMAV("settings/profile.jsp", model);
    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView doPost(HttpServletRequest req, ModelMap model) {
        boolean valid = true;

        // todo: adjust request parameters
        String firstName = req.getParameter("firstName");
        if (firstName == null || firstName.length() < 1) {
            model.put("err.firstName", "First name must be an non-empty string");
            valid = false;
        }
        String lastName = req.getParameter("lastName");
        if (lastName == null || lastName.length() < 1) {
            model.put("err.lastName", "Last name must be an non-empty string");
            valid = false;
        }
        String locator = req.getParameter("locator");
        if (locator == null || locator.length() < 1) {
            model.put("err.lastName", "Custom locator must be an non-empty string");
            valid = false;
        } else {
            Pattern p = Pattern.compile("\\d");
            if (p.matcher(locator).find()){
                model.put("err.locator", "Locator must not contain numbers");
                valid = false;
            }
        }

        if (valid) {
            this.profile.setFirstName(firstName);
            this.profile.setLastName(lastName);
            this.profile.setLocator(locator);

            return new ModelAndView("redirect:/settings/profile");
        }

        model.put("err.form", "Not valid");
        model.put("profile", new UserProfile().setName(firstName, lastName).setLocator(locator));


        return new LayoutMAV("settings/profile.jsp", model);
    }
}

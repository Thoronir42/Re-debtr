package cz.zcu.students.kiwi.redebtr.servlet.settings;

import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "ProfileSettingsServlet", urlPatterns = "/settings/profile")
public class ProfileSettings extends BaseServlet {

    private UserProfile profile = new UserProfile().setName("Karel", "Petrklíč").setLocator("kapetr");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("profile", this.profile);
        this.renderJsp(req, resp, "settings/profile");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean valid = true;

        String firstName = req.getParameter("firstName");
        if (firstName == null || firstName.length() < 1) {
            req.setAttribute("err.firstName", "First name must be an non-empty string");
            valid = false;
        }
        String lastName = req.getParameter("lastName");
        if (lastName == null || lastName.length() < 1) {
            req.setAttribute("err.lastName", "Last name must be an non-empty string");
            valid = false;
        }
        String locator = req.getParameter("locator");
        if (locator == null || locator.length() < 1) {
            req.setAttribute("err.lastName", "Custom locator must be an non-empty string");
            valid = false;
        } else {
            Pattern p = Pattern.compile("\\d");
            if (p.matcher(locator).find()){
                req.setAttribute("err.locator", "Locator must not contain numbers");
                valid = false;
            }
        }

        if (valid) {
            this.profile.setFirstName(req.getParameter("firstName"));
            this.profile.setLastName(req.getParameter("lastName"));
            this.profile.setLocator(req.getParameter("locator"));
            resp.sendRedirect("/settings/profile");
            return;
        }

        req.setAttribute("err.form", "Not valid");
        req.setAttribute("profile", new UserProfile().setName(firstName, lastName).setLocator(locator));

        this.renderJsp(req, resp, "settings/profile");
    }
}

package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.auth.AuthorizationService;
import cz.zcu.students.kiwi.libs.flash.FlashesService;
import cz.zcu.students.kiwi.libs.security.IUser;
import cz.zcu.students.kiwi.redebtr.helpers.AuthHelper;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Autowired
    protected AuthHelper authHelper;

    @ModelAttribute
    public void init(ModelMap model, HttpServletRequest req) {
        User user = authHelper.getCurrentUser(req);

        model.addAttribute("user", user);
        model.addAttribute("authUser", authHelper.getAuthUser(user));
    }

    @ModelAttribute
    public void initFlashes(ModelMap model, FlashesService flashes) {
        model.addAttribute("flashes", flashes);
    }
}

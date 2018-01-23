package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.auth.AuthorizationService;
import cz.zcu.students.kiwi.libs.security.IUser;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public abstract class BaseController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AuthorizationService authorizator;
    @Autowired
    private UserDao users;

    protected User currentUser;

    protected AuthUser authUser;

    @ModelAttribute
    public void init(ModelMap model, HttpServletRequest req) {
        IUser user = this.authenticationService.getUser(req.getSession());

        this.currentUser = users.findByUsername(user.getIdentification());
        this.authUser = new AuthUser(user, authorizator);

        model.addAttribute("user", this.currentUser);
        model.addAttribute("authUser", this.authUser);
    }

}

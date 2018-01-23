package cz.zcu.students.kiwi.redebtr.controllerAdvisors;

import cz.zcu.students.kiwi.libs.auth.AuthUser;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.auth.AuthorizationService;
import cz.zcu.students.kiwi.libs.security.IUser;
import cz.zcu.students.kiwi.redebtr.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AuthAdvisor {

    @Autowired
    public AuthenticationService authenticationService;
    @Autowired
    public AuthorizationService authorizator;

    @Autowired
    public UserDao users;

    @ModelAttribute
    public void init(Model model, HttpServletRequest req) {
        IUser user = this.authenticationService.getUser(req.getSession());

        model.addAttribute("user", users.findByUsername(user.getIdentification()));
        model.addAttribute("authUser", new AuthUser(user, authorizator));
    }

}

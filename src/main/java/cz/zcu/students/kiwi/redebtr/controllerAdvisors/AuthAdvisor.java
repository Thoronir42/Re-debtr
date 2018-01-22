package cz.zcu.students.kiwi.redebtr.controllerAdvisors;

import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
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
    public UserDao users;

    protected IUser user;

    @ModelAttribute
    public void init(Model model, HttpServletRequest req) {
        this.user = this.authenticationService.getUser(req.getSession());

        model.addAttribute("aUser", this.user);
        model.addAttribute("user", users.findByUsername(this.user.getIdentification()));
    }

}

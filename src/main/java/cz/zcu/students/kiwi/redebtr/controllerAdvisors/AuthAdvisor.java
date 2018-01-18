package cz.zcu.students.kiwi.redebtr.controllerAdvisors;

import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.security.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AuthAdvisor {

    @Autowired
    public AuthenticationService authenticationService;

    protected IUser user;

    @ModelAttribute
    public void init(Model model) {
        this.user = this.authenticationService.getUser();

        model.addAttribute("user", this.user);
    }

}

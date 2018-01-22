package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.FlashMessage;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.redebtr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/sign/")
public class SignController {

    private AuthenticationService authService;
    private UserManager userManager;

    @Autowired
    public void setAuthService(AuthenticationService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }


    @RequestMapping(value = "txt", method = RequestMethod.GET)
    public @ResponseBody
    String getTxt() {
        return "ASDF";
    }

    @RequestMapping(value = "in", method = RequestMethod.GET)
    public ModelAndView getIn(ModelMap model) {
        System.out.println("Get in!");

        return new LayoutMAV("sign/in.jsp");
    }

    @RequestMapping(value = "in", method = RequestMethod.POST)
    public ModelAndView postIn(HttpServletRequest req, ModelMap model,
                               @RequestParam String username,
                               @RequestParam String password) {
        System.out.println("Post in!");
        boolean authenticated = authService.authenticate(req.getSession(), username, password);

        System.out.println(username + "@" + password + " = " + authenticated);
        if (authenticated) {
            return new ModelAndView("redirect:/");
        }

        model.put("err", "Invalid credentials!");

        return new ModelAndView("forward:/sign/in.jsp", model);
    }

    @RequestMapping(value = "out", method = RequestMethod.GET)
    public String getOut(HttpServletRequest req, RedirectAttributes ra) {
        this.authService.clear(req.getSession());

//        ra.addAttribute("flashMessage", FlashMessage.Info("You have been signed out."));

        return "redirect:/";
    }

    @RequestMapping(value = "up", method = RequestMethod.GET)
    public ModelAndView getUp() {
        return new LayoutMAV("sign/up.jsp");
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public ModelAndView postUp(HttpServletRequest req, ModelMap model) {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        String password = req.getParameter("password");
        String confirmPwd = req.getParameter("confirmPwd");

        try {
            if (!Objects.equals(password, confirmPwd)) {
                throw new ValidationException("The password and confirm password fields do not match!");
            }

            userManager.register(new User(username, email, password));

            model.put("flashMessage", FlashMessage.Success("Account has been created."));
            return new LayoutMAV("forward:/sign/in", model);
        } catch (ValidationException e) {
            model.put("flashMessage", FlashMessage.Error(e.getMessage()));

            return new LayoutMAV("/sign/up.jsp", model);
        }
    }

}

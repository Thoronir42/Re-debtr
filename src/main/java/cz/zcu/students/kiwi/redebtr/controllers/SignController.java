package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.FlashMessage;
import cz.zcu.students.kiwi.libs.auth.AuthenticationService;
import cz.zcu.students.kiwi.libs.domain.ValidationException;
import cz.zcu.students.kiwi.libs.flash.FlashesService;
import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.libs.security.registration.RegistrationQuestions;
import cz.zcu.students.kiwi.redebtr.model.User;
import cz.zcu.students.kiwi.redebtr.model.UserProfile;
import cz.zcu.students.kiwi.redebtr.persistence.UserProfileDao;
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
public class SignController extends BaseController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserProfileDao userProfiles;
    @Autowired
    private RegistrationQuestions questions;


    @RequestMapping(value = "in", method = RequestMethod.GET)
    public ModelAndView getIn(ModelMap model) {
        return new LayoutMAV("sign/in.jsp");
    }

    @RequestMapping(value = "in", method = RequestMethod.POST)
    public ModelAndView postIn(HttpServletRequest req, ModelMap model,
                               @RequestParam String username,
                               @RequestParam String password) {

        boolean remember = "on".equals(req.getParameter("forgetMeNot"));
        boolean authenticated = authService.authenticate(req.getSession(), username, password, remember);

        if (authenticated) {
            return new ModelAndView("redirect:/");
        }

        model.put("err", "Invalid credentials!");

        return new LayoutMAV("/sign/in.jsp", model);
    }

    @RequestMapping(value = "out", method = RequestMethod.GET)
    public String getOut(HttpServletRequest req, FlashesService flashes) {
        req.getSession().invalidate();

        flashes.add(FlashMessage.Info("You have been signed out."));

        return "redirect:/";
    }

    @RequestMapping(value = "up", method = RequestMethod.GET)
    public ModelAndView getUp(HttpServletRequest req, ModelMap model) {
        model.addAttribute("question", questions.prepareQuestion(req.getSession()));
        return new LayoutMAV("sign/up.jsp", model);
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public ModelAndView postUp(HttpServletRequest req, ModelMap model, RedirectAttributes ra) {
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        String password = req.getParameter("password");
        String confirmPwd = req.getParameter("confirmPwd");

        String questionAnswer = req.getParameter("humanAnswer");

        try {
            if (!questions.verifyAnswer(req.getSession(), questionAnswer)) {
                throw new ValidationException("Human confirmation failed. Try again");
            }
            if (!Objects.equals(password, confirmPwd)) {
                throw new ValidationException("The password and confirm password fields do not match!");
            }

            User user = new User(username, email, password);
            userManager.register(user);

            UserProfile profile = userManager.createProfile(req.getParameter("profileName"), req.getParameter("profileSurname"), user);

            userProfiles.create(profile, true);


            model.put("flashMessage", FlashMessage.Success("Account has been created."));
            return new ModelAndView("forward:/sign/in", model);
        } catch (ValidationException e) {

            ra.addFlashAttribute("flashMessage", e.getMessage());

            return new ModelAndView("redirect:/sign/up");
        }
    }

}

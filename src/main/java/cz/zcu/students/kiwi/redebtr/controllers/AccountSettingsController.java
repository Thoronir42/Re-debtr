package cz.zcu.students.kiwi.redebtr.controllers;

import cz.zcu.students.kiwi.libs.exceptions.ForbiddenException;
import cz.zcu.students.kiwi.libs.manager.UserManager;
import cz.zcu.students.kiwi.redebtr.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller

@RequestMapping(value = "/settings/account")
public class AccountSettingsController extends ASettingsController {

    @Autowired
    UserManager userManager;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAccount(HttpServletRequest req, ModelMap model) {
        User user = authHelper.getCurrentUser(req);
        if (user == null) {
            throw new ForbiddenException();
        }

        model.put("caption", "Account settings");

        return new LayoutMAV("settings/account.jsp", model);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String deleteAccount(HttpServletRequest req, RedirectAttributes ra) {
        User user = authHelper.getCurrentUser(req);
        if (user == null) {
            throw new ForbiddenException();
        }

        userManager.delete(user);

        req.getSession().invalidate();

        ra.addAttribute("flashMessage", "Your account has been deleted");

        return "redirect:/";
    }
}

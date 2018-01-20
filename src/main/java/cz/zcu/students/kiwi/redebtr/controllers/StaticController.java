package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StaticController extends BaseController {

    @GetMapping("/terms-of-service")
    public ModelAndView getTermsOfService() {
        return new LayoutMAV("static/tos.jsp");
    }
}

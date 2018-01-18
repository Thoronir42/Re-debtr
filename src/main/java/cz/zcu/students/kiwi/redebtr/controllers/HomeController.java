package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class HomeController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    protected LayoutMAV getDefault(){

        return new LayoutMAV("home/guest.jsp");
    }
}

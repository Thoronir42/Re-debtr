package cz.zcu.students.kiwi.redebtr.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView sendError(int errorCode) {
        return new ErrorMAV(errorCode);
    }

    protected ModelAndView sendError(int errorCode, String message) {
        return new ErrorMAV(errorCode, message);
    }

}

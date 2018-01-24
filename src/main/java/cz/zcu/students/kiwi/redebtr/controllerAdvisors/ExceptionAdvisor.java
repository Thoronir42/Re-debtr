package cz.zcu.students.kiwi.redebtr.controllerAdvisors;

import cz.zcu.students.kiwi.libs.exceptions.AStatusException;
import cz.zcu.students.kiwi.redebtr.controllers.BaseController;
import cz.zcu.students.kiwi.redebtr.controllers.LayoutMAV;
import cz.zcu.students.kiwi.redebtr.helpers.ExceptionHelper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionAdvisor extends BaseController {

    @ExceptionHandler(AStatusException.class)
    public ModelAndView processError(HttpServletRequest req, AStatusException ex) {
        ModelMap model = new ModelMap();
        this.init(model, req);

        model.put("errorStatusCode", ex.getStatus().value());
        model.put("requestedUri", req.getRequestURL());
        model.put("errorName", ex.getMessage());

        model.put("stackTrace", ExceptionHelper.stringifyStackTrace(ex));

        return new LayoutMAV("error/genericError.jsp", model);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView processError(HttpServletRequest req,
                                     Exception ex) throws Exception {
//        if (AnnotationUtils.findAnnotation
//                (ex.getClass(), ResponseStatus.class) != null) {
//            throw ex;
//        }

        ModelMap model = new ModelMap();
        this.init(model, req);

        String requestUri = req.getRequestURI();

        model.put("errorStatusCode", 500);
        model.put("requestedUri", requestUri);

        if (ex != null) {
            model.put("errorName", ex.toString());

            model.put("stackTrace", ExceptionHelper.stringifyStackTrace(ex));
        }

        return new LayoutMAV("error/genericError.jsp", model);
    }
}

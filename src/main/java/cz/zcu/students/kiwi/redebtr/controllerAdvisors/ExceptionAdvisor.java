package cz.zcu.students.kiwi.redebtr.controllerAdvisors;

import cz.zcu.students.kiwi.libs.exceptions.AStatusException;
import cz.zcu.students.kiwi.redebtr.controllers.BaseController;
import cz.zcu.students.kiwi.redebtr.controllers.LayoutMAV;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

        String[] traces = Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .toArray(String[]::new);
        String stackTrace = String.join("\n", traces);

        model.put("stackTrace", stackTrace);

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

            String[] traces = Arrays.stream(ex.getStackTrace())
                    .map(StackTraceElement::toString)
                    .toArray(String[]::new);
            String stackTrace = String.join("\n", traces);

            model.put("stackTrace", stackTrace);
        }

        return new LayoutMAV("error/genericError.jsp", model);
    }
}

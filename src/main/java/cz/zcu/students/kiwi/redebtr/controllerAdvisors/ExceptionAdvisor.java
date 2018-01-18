package cz.zcu.students.kiwi.redebtr.controllerAdvisors;

import cz.zcu.students.kiwi.redebtr.controllers.LayoutMAV;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(Exception.class)
    public ModelAndView processError(HttpServletRequest req,
                                     Exception ex) throws Exception {
        if (AnnotationUtils.findAnnotation
                (ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        }

        System.out.println("Ex handle");
        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        String errMessage = (String) req.getAttribute("javax.servlet.error.message");

        System.out.println("Error. Processing " + errMessage);

        if (servletName == null) {
            servletName = "Unknown";
        }

        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        ModelMap model = new ModelMap();

        model.put("errorStatusCode", statusCode);
        model.put("requestedUri", requestUri);
        model.put("requestedServlet", servletName);

        if (ex != null) {
            model.put("errorName", ex.getMessage());

            model.put("exception", Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));
        }

        return new LayoutMAV("error/genericError.jsp", model);
    }
}

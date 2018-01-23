package cz.zcu.students.kiwi.redebtr;

import cz.zcu.students.kiwi.libs.exceptions.NotFoundException;
import org.springframework.web.servlet.HandlerExecutionChain;

import javax.servlet.http.HttpServletRequest;

public class RedebtrDispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

    @Override
    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handler = super.getHandler(request);
        if(handler == null) {
            throw new NotFoundException("We couldn't find that.");
        }

        return handler;
    }
}

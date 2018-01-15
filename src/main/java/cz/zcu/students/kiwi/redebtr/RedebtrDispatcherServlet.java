package cz.zcu.students.kiwi.redebtr;

import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;

public class RedebtrDispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

    @Override
    protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handler = super.getHandler(request);

        System.out.println(request.getRequestURL());
        System.out.println(handler);

        return handler;
    }
}

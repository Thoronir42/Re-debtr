package cz.zcu.students.kiwi.redebtr.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String requestedEncoding = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParameter = filterConfig.getInitParameter("encoding");
        if(initParameter != null) {
            this.requestedEncoding = initParameter;
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(requestedEncoding);
        resp.setCharacterEncoding(requestedEncoding);

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}

package filters;

import javax.servlet.*;
import java.io.IOException;

public class UtfFilter implements Filter {

    public void init(FilterConfig arg0) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf8");
        filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}

package filters;

import services.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpSession session = r.getSession();
        String uName = (String) session.getAttribute("current_user");
        if (uName == null) {
            HttpServletResponse httpResponse = (HttpServletResponse) resp;
            httpResponse.sendRedirect("/main");
        } else {
            UserService userService = new UserService();
            if (userService.isAdmin(uName)) {
                chain.doFilter(req, resp);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) resp;
                httpResponse.sendRedirect("/main");
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
package filters;

import org.apache.commons.codec.digest.DigestUtils;
import services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class IsAuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest r = (HttpServletRequest) req;
        HttpSession session = r.getSession();
        String uName = (String) session.getAttribute("current_user");
        if (uName != null) {
            chain.doFilter(req, resp);
        } else {
            Cookie[] cookies = r.getCookies();
            if (cookies != null) {
                UserService userService = new UserService();
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if ("user".equals(cookie.getName())) {
                        String s = cookie.getValue();
                        String userName = userService.getByInfo(DigestUtils.md5Hex(s));
                        if(userName!= null) {
                            session.setAttribute("current_user", userName);
                            break;
                        }
                    }
                }
            }
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
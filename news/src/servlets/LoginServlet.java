package servlets;

import config.ConfigSingleton;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("login1.ftl");
        try {
            tmpl.process(null, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("current_user") != null) {
            session.removeAttribute("current_user");
        }
        UserService userService = new UserService();
        String username = request.getParameter("username");
        String password = request.getParameter("userpassword");
        String rememberMe = request.getParameter("remember");
        if (userService.signIn(username, password)) {
            session.setAttribute("current_user", username);
            Cookie[] cookies = request.getCookies();
            if ("on".equals(rememberMe)) {
                Cookie userCookie = new Cookie("user", password);
                userCookie.setMaxAge(3600 * 24);
                response.addCookie(userCookie);
            } else {
                for (Cookie c : cookies) {
                    if ("user".equals(c.getName())) {
                        c.setMaxAge(0);
                        c.setValue(null);
                        response.addCookie(c);
                    }
                }
            }
            if (userService.isAdmin(username)) {
                session.setAttribute("isAdmin", true);
            }
            response.sendRedirect("/main");
        } else {
            response.sendRedirect("/login");
        }


    }
}

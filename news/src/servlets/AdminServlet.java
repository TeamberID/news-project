package servlets;

import config.ConfigSingleton;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.News;
import services.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Integer id = Integer.valueOf(request.getParameter("id"));
       NewsService newsService = new NewsService();
       newsService.delete(id);
       response.sendRedirect("/admin");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("admin.ftl");
        NewsService newsService = new NewsService();
        List<News> news = newsService.getAll();
        Map<String, Object> input = new HashMap<>();
        input.put("news",news);
        try {
            tmpl.process(input, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}

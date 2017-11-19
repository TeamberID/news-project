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

@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NewsService newsService = new NewsService();
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("search.ftl");
        List<News> news= null;
        String name = request.getParameter("name");
        news = newsService.search(name);
        List<News> topNews = newsService.getTop();
        Map<String, Object> input = new HashMap<>();
        input.put("news", news);
        input.put("topNews", topNews);
        try {
            tmpl.process(input, response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }
}

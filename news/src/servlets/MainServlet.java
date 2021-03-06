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

@WebServlet(name = "MainServlet")
public class

MainServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("index.ftl");
        String name = request.getParameter("name");
        List<News> news = getNews(name);

        try {
            tmpl.process(getTop(request, news), response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }

    }


    private List<News> getNews(String name) {
        NewsService newsService = new NewsService();
        List<News> news = null;
        if (name == null) {
            news = newsService.getAll();
        } else {
            switch (name) {
                case "sports":
                    news = newsService.getAllByCategory(1);
                    break;
                case "politics":
                    news = newsService.getAllByCategory(2);
                    break;
                case "science":
                    news = newsService.getAllByCategory(3);
                    break;
                case "economics":
                    news = newsService.getAllByCategory(4);
                    break;
                case "world":
                    news = newsService.getAllByCategory(5);
                    break;
                case "culture":
                    news = newsService.getAllByCategory(6);
                    break;
            }
        }
        return news;
    }

    private Map<String, Object> getTop(HttpServletRequest request, List<News> news) {
        NewsService newsService = new NewsService();
        List<News> topNews = newsService.getTop();
        Map<String, Object> input = new HashMap<>();
        input.put("topNews", topNews);
        input.put("news",news);
        if (request.getSession().getAttribute("admin") != null)
            input.put("admin", true);
        else{
            input.put("admin",false);
        }
        input.put("cur", request.getSession().getAttribute("current_user"));
        return input;
    }


}

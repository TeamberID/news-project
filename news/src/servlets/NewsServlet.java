package servlets;

import config.ConfigSingleton;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Com;
import models.News;
import services.ComService;
import services.NewsService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "NewsServlet")
public class NewsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("current_user")== null){
            response.sendRedirect("/login");
        } else {
            Date date = new Date(new java.util.Date().getTime());
            String text = request.getParameter("comment");
            String userName = (String) request.getSession().getAttribute("current_user");
            ComService comService = new ComService();
            UserService userService = new UserService();
            Integer userId = userService.getUserID(userName);
            Integer newsId = Integer.valueOf(request.getParameter("id"));
            comService.add(Com.builder().authorId(userId).description(text).newsId(newsId).pubDate(date).build());
            response.sendRedirect("/news?id=" + newsId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("news.ftl");
        NewsService newsService = new NewsService();
        ComService comService = new ComService();
        String idS = request.getParameter("id");
        if(idS==null)
            response.sendRedirect("/main");
        else {
            Integer newsId = Integer.valueOf(idS);
            News news = newsService.get(newsId);
            List<Com> coms = comService.getAll(newsId);
            Map<String, Object> input = getTop();
            input.put("coms",coms);
            input.put("new", news);
            input.put("news_id",newsId);
            try {
                tmpl.process(input, response.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }




    public Map<String,Object> getTop(){
        NewsService newsService = new NewsService();
        List<News> topNews = newsService.getTop();
        Map<String, Object> input = new HashMap<>();
        input.put("topNews", topNews);
        return input;
    }
}

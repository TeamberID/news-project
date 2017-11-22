package servlets;

import config.ConfigSingleton;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Image;
import models.News;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.ImageService;
import services.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "CreateServlet")
public class CreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = null;
        String description = null;
        String category = null;
        Integer number = addImage();
        String url = "upload/" + number + ".jpg";

        try {
            List<FileItem> items = null;
            ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
            items = sf.parseRequest(request);
            for (FileItem i : items) {
                if (i.isFormField()) {
                    String fieldname = i.getFieldName();
                    String fieldvalue = i.getString();
                    try {
                        if (fieldname.equals("title"))
                            title = new String(fieldvalue.getBytes("iso-8859-1"), "UTF-8");
                        if (fieldname.equals("description"))
                            description = new String(fieldvalue.getBytes("iso-8859-1"), "UTF-8");

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    if (fieldname.equals("category"))
                        category = fieldvalue;
                } else {
                    File file = new File("C:\\news-project\\news\\out\\artifacts\\news_war_exploded\\upload\\" + number + ".jpg");
                    i.write(file);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer tagId = getTagId(category);
        if (!"".equals(title) && !"".equals(description)) {
            addNews(title, description, number, tagId, url);
            response.sendRedirect("/main");
        }else {
            response.sendRedirect("/create");
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("create.ftl");
        try {
            tmpl.process(getTop(request), response.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


    private Integer addImage() {
        ImageService imageService = new ImageService();
        String url;
        Integer number = imageService.getNumber();
        url = "upload/" + number + ".jpg";
        Image image = Image.builder().id(number).url(url).build();
        imageService.save(image);
        return number;
    }


    private void addNews(String title, String description, Integer number, Integer tagId, String url) {
        Date date = new Date(new java.util.Date().getTime());
        NewsService newsService = new NewsService();
        News news = News.builder().title(title).description(description)
                .imageId(number)
                .pubDate(date)
                .rating(0)
                .tagId(tagId)
                .imageSrc(url).build();
        newsService.add(news);
    }


    private Map<String, Object> getTop(HttpServletRequest request) {
        NewsService newsService = new NewsService();
        List<News> topNews = newsService.getTop();
        Map<String, Object> input = new HashMap<>();
        input.put("cur", request.getSession().getAttribute("current_user"));
        input.put("topNews", topNews);
        return input;
    }

    public Integer getTagId(String category) {
        Integer tagId = null;
        if ("sports".equals(category))
            tagId = 1;
        if ("politics".equals(category))
            tagId = 2;
        if ("science".equals(category))
            tagId = 3;
        if ("economics".equals(category))
            tagId = 4;
        if ("world".equals(category))
            tagId = 5;
        if ("culture".equals(category))
            tagId = 6;
        return tagId;
    }
}

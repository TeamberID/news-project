package servlets;

import config.ConfigSingleton;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import models.Com;
import models.Image;
import models.News;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import services.ComService;
import services.ImageService;
import services.NewsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer newsId = null;
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
                        if (fieldname.equals("new_id"))
                            newsId = Integer.valueOf(new String(fieldvalue.getBytes("iso-8859-1"), "UTF-8"));
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
        if (!"".equals(title) && !"".equals(description) && !(newsId == null)) {
            updateNews(newsId,title, description, number, tagId, url);
            response.sendRedirect("/update?id="+newsId);
        }else {
            response.sendRedirect("/admin?id="+newsId);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = ConfigSingleton.getConfig(request.getServletContext());
        Template tmpl = cfg.getTemplate("update.ftl");
        NewsService newsService = new NewsService();
        ComService comService = new ComService();
        String idS = request.getParameter("id");
        if(idS==null)
            response.sendRedirect("/admin");
        else {
            Integer newsId = Integer.valueOf(idS);
            News news = newsService.get(newsId);
            List<Com> coms = comService.getAll(newsId);
            Map<String, Object> input = new HashMap<>();
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




    private Integer addImage() {
        ImageService imageService = new ImageService();
        String url;
        Integer number = imageService.getNumber();
        url = "upload/" + number + ".jpg";
        Image image = Image.builder().id(number).url(url).build();
        imageService.save(image);
        return number;
    }


    private void updateNews(Integer id, String title, String description, Integer number, Integer tagId, String url) {
        NewsService newsService = new NewsService();
        News news = News.builder().id(id).title(title).description(description)
                .imageId(number)
                .rating(0)
                .tagId(tagId)
                .imageId(number)
                .imageSrc(url).build();
        newsService.update(news);
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

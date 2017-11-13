package services;

import dao.implemetations.NewsDaoImpl;
import models.News;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class NewsService {
    private NewsDaoImpl newsDao;
    private ImageService imageService = new ImageService();
    private TagService tagService = new TagService();

    public NewsService(){
        newsDao = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news-project",
                    "postgres",
                    "postgres");
            newsDao = new NewsDaoImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void service(Integer i, List<News> news){
        Integer imageId = news.get(i).getImageId();
        String imageUrl = imageService.getUrl(imageId);
        news.get(i).setImageSrc(imageUrl);
        news.get(i).setTag(tagService.get(news.get(i).getTagId()));
    }

    public List<News> getAll(){
        List<News> news = newsDao.findAll();
        for(int i = 0; i< news.size();i++){
            service(i,news);
        }
        return news;
    }

    public List<News> getAllByCategory(Integer tagId){
        List<News> news = newsDao.findAllByCategory(tagId);
        for(int i = 0; i< news.size();i++){
            service(i,news);
        }
        return news;
    }

    public List<News> getTop(){
        List<News> news = newsDao.findAllinTop();
        for(int i = 0; i< news.size();i++){
            service(i,news);
        }
        return news;
    }

    public News get(Integer id){
      News news = newsDao.find(id);
        Integer imageId = news.getImageId();
        String imageUrl = imageService.getUrl(imageId);
        news.setImageSrc(imageUrl);
        news.setTag(tagService.get(news.getTagId()));
        return news;
    }

    public List<News> search(String s){

        List<News> news=  newsDao.search(s);
        for(int i = 0; i< news.size();i++){
            Integer imageId = news.get(i).getImageId();
            String imageUrl = imageService.getUrl(imageId);
            news.get(i).setImageSrc(imageUrl);
            news.get(i).setTag(tagService.get(news.get(i).getTagId()));
        }
        return news;

    }

    public void add(News news){
        newsDao.save(news);
    }

    public void update(News news){
        newsDao.update(news);
    }
    public void delete(Integer id){
        newsDao.delete(id);
    }

}

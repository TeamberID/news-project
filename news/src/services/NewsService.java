package services;

import config.SingleConnection;
import dao.implemetations.NewsDaoImpl;
import models.News;

import java.sql.Connection;
import java.util.List;

public class NewsService {
    private NewsDaoImpl newsDao;
    private ImageService imageService = new ImageService();
    private TagService tagService = new TagService();

    public NewsService(){

        Connection connection = SingleConnection.getConnection();
        newsDao = new NewsDaoImpl(connection);
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
        ComService comService = new ComService();
        comService.deleteByNewsId(id);
        newsDao.delete(id);
    }

}

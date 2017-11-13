package services;

import dao.implemetations.ImagesDaoImpl;
import models.Image;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ImageService {
    private ImagesDaoImpl imagesDao;

    public ImageService() {
        imagesDao = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news-project",
                    "postgres",
                    "postgres");
            imagesDao = new ImagesDaoImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Integer getNumber(){
        return imagesDao.getSize() + 1;
    }

    public String getUrl(Integer id){
        return imagesDao.find(id).getUrl();
    }

    public void save(Image image){
        imagesDao.save(image);
    }
}
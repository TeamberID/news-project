package services;

import config.SingleConnection;
import dao.implemetations.ImagesDaoImpl;
import models.Image;

import java.sql.Connection;

public class ImageService {
    private ImagesDaoImpl imagesDao;

    public ImageService() {
        Connection connection = SingleConnection.getConnection();
        imagesDao = new ImagesDaoImpl(connection);
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
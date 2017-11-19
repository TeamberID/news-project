package services;

import config.SingleConnection;
import dao.implemetations.TagsDaoImpl;
import models.Tag;

import java.sql.Connection;

public class TagService {
    private TagsDaoImpl tagsDao;


    public TagService(){
        Connection connection = SingleConnection.getConnection();
        tagsDao = new TagsDaoImpl(connection);
    }

    public Tag get(Integer i){
         return tagsDao.find(i);
    }
}

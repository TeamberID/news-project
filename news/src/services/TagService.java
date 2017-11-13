package services;

import dao.implemetations.TagsDaoImpl;
import models.Tag;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TagService {
    private TagsDaoImpl tagsDao;


    public TagService(){
        tagsDao = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news-project",
                    "postgres",
                    "postgres");
            tagsDao = new TagsDaoImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Tag get(Integer i){
         return tagsDao.find(i);
    }
}

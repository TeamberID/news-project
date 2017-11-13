package services;

import dao.implemetations.ComsDaoImpl;
import models.Com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ComService {
    private ComsDaoImpl comsDao;

    public ComService(){
        comsDao = null;
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news-project",
                    "postgres",
                    "postgres");
            comsDao = new ComsDaoImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Com> getAll(Integer newsId){
        return comsDao.findAllByNewsID(newsId);
    }

    public void add(Com com){
        comsDao.save(com);
    }

    public void delete(Integer id){
        comsDao.delete(id);
    }


}

package services;

import config.SingleConnection;
import dao.implemetations.ComsDaoImpl;
import models.Com;

import java.sql.Connection;
import java.util.List;

public class ComService {
    private ComsDaoImpl comsDao;

    public ComService(){
        Connection connection = SingleConnection.getConnection();
        comsDao = new ComsDaoImpl(connection);
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


    public void deleteByNewsId(Integer id) {
        comsDao.deleteByNewsId(id);
    }
}

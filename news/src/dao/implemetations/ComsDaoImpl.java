package dao.implemetations;

import dao.ComsDao;
import models.Com;
import services.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComsDaoImpl implements ComsDao {
    Connection connection;
    UserService userService = new UserService();

    public ComsDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Com model) {
        try {
            PreparedStatement statement = connection.
                    prepareStatement("INSERT INTO com(description,pub_date,author_id,news_id) VALUES(?,?,?,?)");
            statement.setString(1, model.getDescription());
            statement.setDate(2, model.getPubDate());
            statement.setInt(3, model.getAuthorId());
            statement.setInt(4, model.getNewsId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Com find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM com WHERE id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Com.builder().
                        id(resultSet.getInt("id")).
                        description(resultSet.getString("description")).
                        pubDate((resultSet.getDate("pub_date"))).
                        authorId(resultSet.getInt("author_id")).
                        newsId(resultSet.getInt("news_id")).build();
            } else throw new IllegalArgumentException("Comment not found");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public List<Com> findAllByNewsID(Integer newsId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM com WHERE news_id =? ORDER BY pub_date");
            statement.setInt(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            List<Com> coms = new ArrayList<>();
            while (resultSet.next()) {
                coms.add(Com.builder().
                        id(resultSet.getInt("id")).
                        description(resultSet.getString("description")).
                        pubDate((resultSet.getDate("pub_date"))).
                        authorId(resultSet.getInt("author_id")).
                        user(userService.getUser(resultSet.getInt("author_id"))).
                        newsId(resultSet.getInt("news_id")).build());
            }
            return coms;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM com WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Com model) {

    }

    public void deleteByNewsId(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM com WHERE news_id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

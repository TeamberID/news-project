package dao.implemetations;

import dao.NewsDao;
import models.News;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    Connection connection;

    public NewsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(News model) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (title,description,pub_date,image_id,tag_id) VALUES(?,?,?,?,?)");
            statement.setString(1, model.getTitle());
            statement.setString(2, model.getDescription());
            statement.setDate(3, model.getPubDate());
            statement.setInt(4, model.getImageId());
            statement.setInt(5,model.getTagId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public News find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM news WHERE id =?");
            statement.setInt(1, id);
            PreparedStatement statement2 = connection.prepareStatement("UPDATE news SET rating = ? WHERE id = ?");
            statement2.setInt(1,getRating(id)+1);
            statement2.setInt(2,id);
            statement2.execute();
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return News.builder().
                        id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .pubDate(resultSet.getDate("pub_date"))
                        .imageId(resultSet.getInt("image_id"))
                        .tagId(resultSet.getInt("tag_id"))
                        .rating(resultSet.getInt("rating"))
                        .build();
            } else throw new IllegalArgumentException("News(one) not found");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Integer getRating(Integer id) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM  news WHERE ID = ?");
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return 0;
    }

    public List<News> findAll(){
        PreparedStatement statement = null;
        List<News> news = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM news ORDER BY pub_date DESC, rating DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                news.add( News.builder().
                        id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                       .pubDate(resultSet.getDate("pub_date"))
                        .tagId(resultSet.getInt("tag_id"))
                        .imageId(resultSet.getInt("image_id"))
                        .rating(resultSet.getInt("rating"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM news WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(News model) {
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE news SET title = ?, description = ?, image_id = ?, tag_id = ? WHERE id = ?");
            statement.setString(1,model.getTitle());
            statement.setString(2,model.getDescription());
            statement.setInt(3,model.getImageId());
            statement.setInt(4,model.getTagId());
            statement.setInt(5,model.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<News> findAllByCategory(Integer tagID) {
        PreparedStatement statement = null;
        List<News> news = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM news WHERE  tag_id = ?");
            statement.setInt(1,tagID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                news.add( News.builder().
                        id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                       .pubDate(resultSet.getDate("pub_date"))
                        .imageId(resultSet.getInt("image_id"))
                        .tagId(resultSet.getInt("tag_id"))
                        .rating(resultSet.getInt("rating"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<News> findAllinTop() {
        PreparedStatement statement = null;
        List<News> news = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM news ORDER BY rating DESC LIMIT 8");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                news.add( News.builder().
                        id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .pubDate(resultSet.getDate("pub_date"))
                        .imageId(resultSet.getInt("image_id"))
                        .tagId(resultSet.getInt("tag_id"))
                        .rating(resultSet.getInt("rating"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    public List<News> search(String s) {
        PreparedStatement statement = null;
        List<News> news = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM news WHERE news.title LIKE ?;"); // Needs to be checked
            statement.setString(1,"%"+s+"%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                news.add( News.builder().
                        id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .pubDate(resultSet.getDate("pub_date"))
                        .tagId(resultSet.getInt("tag_id"))
                        .imageId(resultSet.getInt("image_id"))
                        .rating(resultSet.getInt("rating"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }
}

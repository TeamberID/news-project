package dao.implemetations;

import dao.NewsDao;
import models.News;

import java.sql.*;

public class NewsDaoImpl implements NewsDao {
    Connection connection;

    public NewsDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(News model) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO news (title,description,pub_date,image_id,category) VALUES(?,?,?,?,?)");
            statement.setString(1, model.getTitle());
            statement.setString(2, model.getDescription());
            statement.setDate(3, (Date) model.getPubDate());
            statement.setInt(4, model.getImageId());
            statement.setString(5, model.getCategory());
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
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return News.builder().
                        id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .description(resultSet.getString("description"))
                        .pubDate(resultSet.getDate("pub_date"))
                        .imageId(resultSet.getInt("image_id"))
                        .category(resultSet.getString("category"))
                        .rating(resultSet.getInt("rating"))
                        .build();
            } else throw new IllegalArgumentException("News(one) not found");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
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
            PreparedStatement statement = connection.prepareStatement("UPDATE news SET title = ?, description = ?, pub_date = ?, image_id = ?, category = ? WHERE id = ?");
            statement.setString(1,model.getTitle());
            statement.setString(2,model.getDescription());
            statement.setDate(3, (Date) model.getPubDate());
            statement.setInt(4,model.getImageId());
            statement.setString(5,model.getCategory());
            statement.setInt(6,model.getImageId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

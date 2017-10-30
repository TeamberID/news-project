package dao.implemetations;

import dao.ImagesDao;
import models.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImagesDaoImpl implements ImagesDao{
    private Connection connection;

    public ImagesDaoImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Image model) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO image(url) VALUES(?)");
            statement.setString(1, model.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Image find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM image WHERE id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Image.builder()
                        .id(resultSet.getInt("id"))
                        .url(resultSet.getString("url"))
                        .build();
            } else throw new IllegalArgumentException("User not found");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Image model) {

    }
}

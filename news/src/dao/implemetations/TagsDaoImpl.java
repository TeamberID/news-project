package dao.implemetations;

import dao.TagsDao;
import models.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagsDaoImpl implements TagsDao {
    Connection connection;

    public TagsDaoImpl(Connection connection){
        this.connection = connection;
    }
    @Override
    public void save(Tag model) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tag (name) VALUES(?)");
            statement.setString(1, model.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Tag find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM tag WHERE id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Tag.builder().
                        id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
            } else throw new IllegalArgumentException("Tag not found");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Tag model) {

    }
}

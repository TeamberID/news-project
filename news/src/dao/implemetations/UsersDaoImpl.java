package dao.implemetations;

import dao.UsersDao;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDaoImpl implements UsersDao {

    private Connection connection;
    public UsersDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login,pass) VALUES(?,?)");
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPass());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User find(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id =?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return User.builder().
                        id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .pass(resultSet.getString("pass"))
                        .isAdmin(resultSet.getBoolean("admin_flag"))
                        .build();
            } else throw new IllegalArgumentException("User not found");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
        public void update (User model){
        }

    public User findByLogin(String login) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login =?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return User.builder().
                        id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .pass(resultSet.getString("pass"))
                        .isAdmin(resultSet.getBoolean("admin_flag"))
                        .build();
            } else return null;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

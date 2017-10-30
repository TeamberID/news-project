package services;

import dao.implemetations.UsersDaoImpl;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserService {
    private UsersDaoImpl usersDao;

    public UserService() {
        usersDao = null;

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/news-project",
                    "postgres",
                    "postgres");
            usersDao = new UsersDaoImpl(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Boolean registr(String login, String pass, String passCheck) {
        if (pass.equals(passCheck)) {
            if(usersDao.findByLogin(login)==null) {
                usersDao.save(User.builder().login(login).pass(DigestUtils.md5Hex(pass)).build());
                System.out.println("this login is free");
                return true;
            }else {
                System.out.println("this login already exists :c");
            }
        }  else {
            System.out.println("passwords are different");
        }
        return false;
    }

    public Boolean signIn(String login, String pass) {
        User user = usersDao.findByLogin(login);
        if(user!=null) {
            if (user.getPass().equals(DigestUtils.md5Hex(pass)))
                return true;
        }
        return false;
    }


}

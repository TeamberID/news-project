package services;

import config.SingleConnection;
import dao.implemetations.UsersDaoImpl;
import models.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.Connection;

public class UserService {
    private UsersDaoImpl usersDao;

    public UserService() {

        Connection connection = SingleConnection.getConnection();
        usersDao = new UsersDaoImpl(connection);
    }


    public Boolean registr(String login, String pass, String passCheck) {
        if (pass.equals(passCheck)) {
            if (usersDao.findByLogin(login) == null) {
                if(! "".equals(login) && ! "".equals(pass)) {
                    usersDao.save(User.builder().login(login).pass(DigestUtils.md5Hex(pass)).build());
                    System.out.println("this login is free");
                    return true;
                }
            } else {
                System.out.println("this login already exists :c");
            }
        } else {
            System.out.println("passwords are different");
        }
        return false;
    }

    public Boolean signIn(String login, String pass) {
        User user = usersDao.findByLogin(login);
        if (user != null) {
            if (! "".equals(login) && ! "".equals(pass) ) {
                if (user.getPass().equals(DigestUtils.md5Hex(pass)))
                    return true;
            }
        }
        return false;
    }

    public String getByInfo(String pass) {
        return usersDao.findNameByPas(pass);
    }

    public Integer getUserID(String login){
        return usersDao.findByLogin(login).getId();
    }

    public User getUser(Integer id){
        return usersDao.find(id);
    }

    public Boolean isAdmin(String s){
        return usersDao.isAdByLogin(s);
    }
}

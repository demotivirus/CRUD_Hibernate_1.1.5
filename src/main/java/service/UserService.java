package service;

import dao.UserDao;
import daoFactory.DaoFactory;
import model.User;

import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDao userDao = DaoFactory.getDaoFactory().createUserDao();

    private UserService(){}

    public static UserService getInstance(){
        if (userService == null)
            userService = new UserService();
        return userService;
    }

    public void addUser(User user) {
        //Session session = sessionFactory.openSession();
        //UserHibernateDao userDao = new UserHibernateDao(session);
        userDao.addUser(user);
        //session.close();
    }

    public User getUser(long id){
        //Session session = sessionFactory.openSession();
        //UserHibernateDao userDao = new UserHibernateDao(session);
        return userDao.getUser(id);
    }

    public List<User> getAll() {
        //Session session = sessionFactory.openSession();
        //UserHibernateDao userDao = new UserHibernateDao(session);
        return userDao.getAll();
    }

    public void deleteUser(Long id) {
        //Session session = sessionFactory.openSession();
        //UserHibernateDao userDao = new UserHibernateDao(session);
        userDao.deleteUser(id);
    }

    public void updateUser(User user) {
        //Session session = sessionFactory.openSession();
        //UserHibernateDao userDao = new UserHibernateDao(session);
        userDao.updateUser(user);
    }

    public User loginUser(String name, String password){
        return userDao.loginUser(name, password);
    }
}

package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.DBHelper;

import java.util.List;

public class UserHibernateDao implements UserDao{
    private static UserHibernateDao instance;
    private SessionFactory sessionFactory = DBHelper.getInstance().getConfiguration();

    private UserHibernateDao(){}

    public static UserHibernateDao getInstance(){
        if (instance == null)
            return instance = new UserHibernateDao();
        return instance;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

//    public User logIn(User user){
//        Transaction transaction = session.beginTransaction();
//        Query query = session.createQuery("FROM User where name = :paramName and password = :paramPassword");
//        query.setParameter("paramName", user.getName());
//        query.setParameter("paramPassword", user.getPassword());
//        return user = (User) query.uniqueResult();
//    }

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        return session.get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> list = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User loginUser(String name, String password){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User where name = :paramName and password = :paramPassword");
        query.setParameter("paramName", name);
        query.setParameter("paramPassword", password);
        List<User> users = query.list();

        User login = null;
        for (User u : users){
            if (u.getName().equals(name) && u.getPassword().equals(password))
                login = u;
        }
        transaction.commit();
        session.close();
        return login;
    }
}

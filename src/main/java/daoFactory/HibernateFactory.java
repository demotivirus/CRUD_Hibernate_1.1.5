package daoFactory;

import dao.UserDao;
import dao.UserHibernateDao;

public class HibernateFactory extends DaoFactory{
    @Override
    public UserDao createUserDao() {
        return UserHibernateDao.getInstance();
    }

}

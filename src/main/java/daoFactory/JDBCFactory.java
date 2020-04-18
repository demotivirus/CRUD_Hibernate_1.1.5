package daoFactory;

import dao.UserDao;
import dao.UserJdbcDao;

public class JDBCFactory extends DaoFactory{
    @Override
    public UserDao createUserDao() {
        return UserJdbcDao.getInstance();
    }
}

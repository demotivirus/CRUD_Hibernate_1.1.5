package daoFactory;

import dao.UserDao;
import util.PropertiesReader;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        PropertiesReader propertiesReader = new PropertiesReader();
        String daoType = propertiesReader.readConfig();
        DaoFactory factory = null;
        if (daoType.equalsIgnoreCase("Hibernate")) {
            System.out.println("Config is : " + daoType);
            factory = new HibernateFactory();
        }
        if (daoType.equalsIgnoreCase("JDBC")) {
            System.out.println("Config is : " + daoType);
            factory = new JDBCFactory();
        }
        return factory;
    }

    public abstract UserDao createUserDao();
}

package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private String daoType;

    public String readConfig(){
        Properties properties = new Properties();

        try(InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")){
            properties.load(is);

            daoType = properties.getProperty("DaoType");
            System.out.println("Config file read successful");
        } catch (IOException e) {
            System.out.println("Config read don't success");
            //e.printStackTrace();
        }

        return daoType;
    }
}

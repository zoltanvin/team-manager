package hu.alkfejl.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
    public static final String CONNECTION_STRING;

    static {
        Properties prop = new Properties();
        try(InputStream propInput = DBConfig.class.getResourceAsStream("/db.properties")){
            prop.load(propInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CONNECTION_STRING = prop.getProperty("db_conn_string");
    }
}

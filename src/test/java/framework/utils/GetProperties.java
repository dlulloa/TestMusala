package framework.utils;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {
    private Properties prop = new Properties();
    private static Logger LOGGER = Logger.getLogger(GetProperties.class.getName());

    public GetProperties(){
        InputStream in = getClass().getResourceAsStream("/config.properties");
        InputStream on = getClass().getResourceAsStream("/invalidMail.properties");

        try {
            prop.load(in);
        }catch(FileNotFoundException e){
            LOGGER.error("The property file was not found");
        }catch (IOException e) {
            LOGGER.error("Can not read the property file");
        }

        try {
            prop.load(on);
        }catch(FileNotFoundException e){
            LOGGER.error("The property file was not found");
        }catch (IOException e) {
            LOGGER.error("Can not read the property file");
        }
    }

    public String getString(String propertyName) { return prop.getProperty(propertyName); }
}

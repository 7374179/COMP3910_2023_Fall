package ca.bcit.infosys.employee;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private Properties properties;

    public PropertiesLoader(String propertiesFileName) {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName)) {
            if (inputStream == null) {
                throw new RuntimeException("Properties file not found: " + propertiesFileName);
            }
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public void setProperty(String key, String newPassword) {
    	properties.setProperty(key, newPassword);
        
    }


}

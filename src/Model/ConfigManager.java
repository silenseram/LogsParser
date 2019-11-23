package Model;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.Properties;

public class ConfigManager {

    Properties properties;

    private String server;
    private String filePath;
    private String configFilePath;

    public ConfigManager(String configFilename) {
        this.configFilePath = FileManager.getConfigFilePath(configFilename);
        properties = new Properties();
        updateProperties();
    }

    public void updateProperties() {
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);

            server = properties.getProperty("server");
            filePath = properties.getProperty("filepath");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public String getServerName(){ return server; }

    public void setProperty(String key, String value){
        try {
            FileOutputStream out = new FileOutputStream(configFilePath);
            properties.setProperty(key, value);
            properties.store(out, null);

            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
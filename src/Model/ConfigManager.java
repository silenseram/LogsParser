package Model;

import org.w3c.dom.ls.LSOutput;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigManager {

    Properties properties;

    private String server;
    private String filePath;
    private String configFilePath;

    public ConfigManager(String configFilename) {
        this.configFilePath = "C://Users//" + System.getProperty("user.name") + "//LogsParser//config//" + configFilename;
        properties = new Properties();
        getProperty();
    }

    public void getProperty() {
        try {
            FileInputStream fis = new FileInputStream(configFilePath);
            properties.load(fis);

            server = properties.getProperty("server");
            filePath = properties.getProperty("filepath");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getFilePath() { return filePath; }

    public String getServerName(){ return server; }
}
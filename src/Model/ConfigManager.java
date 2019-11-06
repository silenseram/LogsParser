package Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigManager {

    Properties properties;

    private String server;
    private int day;
    private int month;
    private int year;
    private String filePath;

    public ConfigManager() {
        properties = new Properties();
        day = 1;
        month = 1;
        year = 2019;
        getProperty();
    }

    public void getProperty() {
        try {
            FileInputStream fis = new FileInputStream("src//config.properties");
            properties.load(fis);

            day = Integer.parseInt(properties.getProperty("day"));
            month = Integer.parseInt(properties.getProperty("month"));
            year = Integer.parseInt(properties.getProperty("year"));
            server = properties.getProperty("server");
            filePath = properties.getProperty("filepath");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateProperty(){
        try {
            FileOutputStream out = new FileOutputStream("src//config.properties");

            properties.setProperty("day", Integer.toString(day));
            properties.setProperty("month", Integer.toString(month));
            properties.setProperty("year", Integer.toString(year));
            properties.setProperty("server", server);
            properties.setProperty("filepath", filePath);

            properties.store(out, null);
            out.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getDay(){
        return day;
    }

    public int getMonth(){
        return month;
    }

    public int getYear(){
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFilePath() { return filePath; }

    public String getServerName(){
        return "EasyTech";
    }
}
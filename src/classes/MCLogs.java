package classes;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MCLogs {

    private int day;
    private int month;
    private int year;
    private String server;
    ConfigManager configManager;

    public MCLogs() {
        configManager = new ConfigManager();
        this.day = configManager.getDay();
        this.month = configManager.getMonth();
        this.year = configManager.getYear();
        this.server = configManager.getServerName();
    }

    public MCLogs(LocalDate date) {
        configManager = new ConfigManager();
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.server = configManager.getServerName();
    }

    public List<PrivateMessage> getMessages(){

        DateManager dateManager = new DateManager(day, month, year);
        //System.out.println(configManager.getDay() + " " + month + " " + year);
        LinkManager linkManager = new LinkManager(server, dateManager);

        File file = FileManager.downloadFile(linkManager.getUrl(), dateManager.getStringDate());
        if (file == null)
            return null;

        TextManager textManager = new TextManager(file.getAbsolutePath());

        List<PrivateMessage> messages = new ArrayList<>();
        for (String i : textManager.getMessages()){
            messages.add(new PrivateMessage(i));
        }

        return messages;
    }

    public String getNowFilePath(){
        return configManager.getFilePath();
    }
}

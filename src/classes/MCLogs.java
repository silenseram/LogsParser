package classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MCLogs {

    private int day;
    private int month;
    private int year;
    private String server;

    public MCLogs() {
        ConfigManager configManager = new ConfigManager();
        this.day = configManager.getDay();
        this.month = configManager.getMonth();
        this.year = configManager.getYear();
        this.server = configManager.getServerName();
    }

    public MCLogs(int day, int month, int year) {
        ConfigManager configManager = new ConfigManager();
        this.day = day;
        this.month = month;
        this.year = year;
        this.server = configManager.getServerName();
    }

    public List<PrivateMessage> getMessages(){
        ConfigManager configManager = new ConfigManager();

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
}

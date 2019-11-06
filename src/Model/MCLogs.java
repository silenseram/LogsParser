package Model;

import Model.messages.PrivateMessage;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MCLogs {

    private boolean isLocalEnabled;
    private boolean isGlobalEnabled;
    private boolean isPrivateEnabled;
    private boolean showTime;

    private int day;
    private int month;
    private int year;
    private String server;
    ConfigManager configManager;
    TextManager textManager;

    public MCLogs() {
        configManager = new ConfigManager();
        this.day = configManager.getDay();
        this.month = configManager.getMonth();
        this.year = configManager.getYear();
        this.server = configManager.getServerName();
    }

    public MCLogs(LocalDate date, boolean showTime, boolean isLocalEnabled, boolean isGlobalEnabled, boolean isPrivateEnabled) {
        this.isLocalEnabled = isLocalEnabled;
        this.isGlobalEnabled = isGlobalEnabled;
        this.isPrivateEnabled = isPrivateEnabled;
        this.showTime = showTime;

        configManager = new ConfigManager();
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.server = configManager.getServerName();
    }

    public List<String> getRequestedLines(){

        DateManager dateManager = new DateManager(day, month, year);
        //System.out.println(configManager.getDay() + " " + month + " " + year);
        LinkManager linkManager = new LinkManager(server, dateManager);

        File file = FileManager.downloadFile(linkManager.getUrl(), dateManager.getStringDate());
        if (file == null)
            return null;

        TextManager textManager = new TextManager(file.getAbsolutePath());

        List<String> lines = new ArrayList<>();
        for (String i : textManager.getNeedStrings(isLocalEnabled, isGlobalEnabled, isPrivateEnabled)){
            if (textManager.isPrivateMessage(i)){
                if (showTime){
                    lines.add(PrivateMessage.getMessageWithTime(i));
                } else{
                    lines.add(PrivateMessage.getMessage(i));
                }
            } else {
                lines.add(i);
            }
        }

        return lines;
    }

    public String getNowFilePath(){
        return configManager.getFilePath();
    }
}

package Model;

import Model.messages.PrivateMessage;
import View.LogDisplayParams;

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
    TextManager textManager;
    private LogDisplayParams params;

    public MCLogs() {
        configManager = new ConfigManager();
        this.day = configManager.getDay();
        this.month = configManager.getMonth();
        this.year = configManager.getYear();
        this.server = configManager.getServerName();
    }

    public MCLogs(LocalDate date, LogDisplayParams params) {
        this.params = params;

        configManager = new ConfigManager();
        this.day = date.getDayOfMonth();
        this.month = date.getMonthValue();
        this.year = date.getYear();
        this.server = configManager.getServerName();
    }

    public List<String> getRequestedLines(){

        DateManager dateManager = new DateManager(day, month, year);
        LinkManager linkManager = new LinkManager(server, dateManager);

        File file = FileManager.downloadFile(linkManager.getUrl(), dateManager.getStringDate());
        if (!file.exists())
            return null;

        TextManager textManager = new TextManager(file.getAbsolutePath());

        return textManager.getSelectedLogs(params);
    }

    public String getNowFilePath(){
        return configManager.getFilePath();
    }
}

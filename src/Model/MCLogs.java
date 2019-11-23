package Model;

import Model.messages.PrivateMessage;
import View.LogDisplayParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MCLogs {

    private LocalDate localDate;
    private String server;
    ConfigManager configManager;
    TextManager textManager;
    private LogDisplayParams params;

    public MCLogs() {
        configManager = new ConfigManager("config");
        this.server = configManager.getServerName();
    }

    public MCLogs(LocalDate date, LogDisplayParams params) {
        this.params = params;
        this.localDate = date;
        configManager = new ConfigManager("config");
        this.server = configManager.getServerName();
    }

    public List<String> getRequestedLines() throws FileNotFoundException {

        File file = new File(FileManager.getLogFilePath(TextUtils.getStringDate(localDate)));
        TextManager textManager = new TextManager(file.getAbsolutePath());

        return textManager.getSelectedLogs(params);
    }

    public String getNowFilePath(){ return FileManager.getLogFilePath(TextUtils.getStringDate(localDate));  }

    public LocalDate getLocalDate() {
        return localDate;
    }
}

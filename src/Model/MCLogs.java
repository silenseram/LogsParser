package Model;

import Model.TextUpdaters.TextManager;
import View.LogDisplayParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class MCLogs {

    private LocalDate localDate;
    private String server;
    ConfigManager configManager;
    TextManager textManager;
    private LogDisplayParams params;

    public MCLogs(LocalDate localDate) {
        this.localDate = localDate;
        configManager = new ConfigManager("config");
        this.server = configManager.getLogsServerName();
    }

    public MCLogs(LocalDate date, LogDisplayParams params) {
        this.params = params;
        this.localDate = date;
        configManager = new ConfigManager("config");
        this.server = configManager.getServerName();
    }

    public List<String> getRequestedLines() throws FileNotFoundException {

        File file = new File(FileManager.getLogChatFilePath(TextUtils.getStringDate(localDate)));
        TextManager textManager = new TextManager(file.getAbsolutePath());

        return textManager.getSelectedLogs(params);
    }

    public List<String> getAllLogs(){
        File file = new File(FileManager.getLogFilePath(TextUtils.getStringDate(localDate)));
        TextManager textManager = new TextManager(file.getAbsolutePath());

        return textManager.getAllLogs();
    }

    public String getNowFilePath(){ return FileManager.getLogChatFilePath(TextUtils.getStringDate(localDate));  }

    public LocalDate getLocalDate() {
        return localDate;
    }
}

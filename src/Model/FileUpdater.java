package Model;

import javax.swing.text.TabExpander;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

public class FileUpdater {

    private LinkManager linkManager;
    private LocalDate localDate;

    public FileUpdater(LinkManager linkManager, LocalDate localDate) {
        this.linkManager = linkManager;
        this.localDate = localDate;
    }

    public void updateChatFile(){
        FileManager.downloadChatFile(linkManager.getUrl(), TextUtils.getStringDate(localDate));
    }
    public void updateLogFile(){
        FileManager.downloadLogFile(linkManager.getUrl(), TextUtils.getStringDate(localDate));
    }

    public void updateLogsAccessFile(){
        FileManager.downloadLogsAccessFile(linkManager.getUrl(), TextUtils.getStringDate(localDate));
    }
}

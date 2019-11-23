package Model;

import java.io.File;
import java.time.LocalDate;

public class FileUpdater {

    private LinkManager linkManager;
    private LocalDate localDate;

    public FileUpdater(LinkManager linkManager, LocalDate localDate) {
        this.linkManager = linkManager;
        this.localDate = localDate;
    }

    public void update(){
        FileManager.downloadFile(linkManager.getUrl(), TextUtils.getStringDate(localDate));
    }
}

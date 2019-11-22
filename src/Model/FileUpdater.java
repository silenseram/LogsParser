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
        File oldFile = new File(FileManager.getLogFilePath(TextUtils.getStringDate(localDate)));
        if (oldFile.exists()) {
            File newFile = FileManager.updateFile(linkManager.getUrl(), "1");

            oldFile.delete();
            newFile.renameTo(oldFile);
        }else {
            FileManager.updateFile(linkManager.getUrl(), TextUtils.getStringDate(localDate));
        }
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

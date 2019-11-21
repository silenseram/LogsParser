package Model.Threads;
import Model.FileManager;
import Model.LinkManager;
import Model.TextUtils;

import java.io.File;
import java.time.LocalDate;

public class RealtimeFileUpdater implements Runnable {

    private LinkManager linkManager;
    private String mainThreadName;
    private LocalDate localDate;

    public final static String threadName = "fileUpdateThread";

    public RealtimeFileUpdater(LinkManager linkManager, String mainThreadName, LocalDate localDate) {
        this.linkManager = linkManager;
        this.mainThreadName = mainThreadName;
        this.localDate = localDate;
    }


    @Override
    public void run() {
        Thread.currentThread().setName(RealtimeFileUpdater.threadName);
        while (true) {

            boolean isMainThreadExist = false;
            for (Thread t : Thread.getAllStackTraces().keySet()) {          //if main thread is end then over this thread
                if (t.getName().equals(mainThreadName)) isMainThreadExist = true;
            }
            if (!isMainThreadExist)
                return;

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
}

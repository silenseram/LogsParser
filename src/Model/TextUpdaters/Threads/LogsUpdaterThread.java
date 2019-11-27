package Model.TextUpdaters.Threads;

import Model.ConfigManager;
import Model.FileUpdater;
import Model.LinkManager;
import Model.MCLogs;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LogsUpdaterThread implements Runnable {

    public static final String threadName = "LogsHandler";
    private LocalDate localDate;
    private ConfigManager configManager;
    private String mainThreadName;
    private MCLogs mcLogs;
    private FileUpdater fileUpdater;
    private TextArea textArea;
    private boolean isStartWrited = false;

    public LogsUpdaterThread(TextArea textArea, LocalDate localDate, ConfigManager configManager, String mainThreadName, MCLogs mcLogs) {
        this.textArea = textArea;
        this.localDate = localDate;
        this.configManager = configManager;
        this.mainThreadName = mainThreadName;
        this.mcLogs = mcLogs;
        this.fileUpdater = new FileUpdater(new LinkManager(configManager.getLogsServerName(), localDate), localDate);
    }

    @Override
    public void run() {
        String logRecord = "";

        fileUpdater.updateLogFile();
        String text = "";

        Thread.currentThread().setName(threadName);

        while (true){


            boolean isMainThreadExist = false;
            for (Thread t : Thread.getAllStackTraces().keySet()) {          //if main thread is end then over this thread
                if (t.getName().equals(mainThreadName)) {
                    isMainThreadExist = true;
                }
            }
            if (!isMainThreadExist)
                return;

            fileUpdater.updateLogFile();

            List<String> newMessages = null;
            try {
                newMessages = mcLogs.getCuttedLogs();

                for (String i : newMessages){
                    text += i + "\n";
                }
                if (!text.equals(textArea.getText())) {
                    textArea.setText(text);
                }

                textArea.setScrollTop(Double.MAX_VALUE);
            } catch (Exception e){
                e.printStackTrace();
            }

            text = "";
            Thread.yield();

        }

    }
}

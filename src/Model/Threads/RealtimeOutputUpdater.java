package Model.Threads;

import Model.ConfigManager;
import Model.FileUpdater;
import Model.LinkManager;
import Model.MCLogs;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class RealtimeOutputUpdater implements Runnable{

    private TextArea textArea;
    private MCLogs logs;
    public final static String threadName = "RealtimeHandler";
    private String mainThreadName;
    private boolean showTime;
    private File file;
    private FileUpdater fileUpdater;

    public RealtimeOutputUpdater(MCLogs logs, TextArea textArea, String mainThreadName, LocalDate localDate, ConfigManager configManager) {
        this.logs = logs;
        this.textArea = textArea;
        this.mainThreadName = mainThreadName;
        this.file = new File(logs.getNowFilePath());
        fileUpdater = new FileUpdater(new LinkManager(configManager.getServerName(), localDate), localDate); // mb dich
    }

    @Override
    public void run() {
        Thread.currentThread().setName(RealtimeOutputUpdater.threadName);
        String text = "";
        while (true){

            boolean isMainThreadExist = false;
            for (Thread t : Thread.getAllStackTraces().keySet()) {          //if main thread is end then over this thread
                if (t.getName().equals(mainThreadName)) {
                    isMainThreadExist = true;
                }
            }
            if (!isMainThreadExist)
                return;

            fileUpdater.update();

            List<String> messages;
            try {
                messages = logs.getRequestedLines();
            } catch (FileNotFoundException e) {
                continue;
            }
            if (messages == null) {
                continue;
            }

            for (String i : messages){
//                if (showTime) {
//                    text += i + "\n";
//                } else {
//                    text += i + "\n";
//                }
                text += i + "\n";
            }

            if (textArea.getText() != text && !text.equals(""))
                textArea.setText(text);
            textArea.setScrollTop(Double.MAX_VALUE);
            text = "";

        }
    }
}

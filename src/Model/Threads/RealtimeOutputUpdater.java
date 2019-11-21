package Model.Threads;

import Model.MCLogs;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class RealtimeOutputUpdater implements Runnable{

    private TextArea textArea;
    private MCLogs logs;
    public final static String threadName = "RealtimeHandler";
    private String mainThreadName;
    private boolean showTime;
    private File file;

    public RealtimeOutputUpdater(MCLogs logs, TextArea textArea, String mainThreadName, boolean showTime) {
        this.showTime = showTime;
        this.logs = logs;
        this.textArea = textArea;
        this.mainThreadName = mainThreadName;
        //System.out.println(logs.getNowFilePath());
        this.file = new File(logs.getNowFilePath());
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

            List<String> messages = null;
            try {
                messages = logs.getRequestedLines();
            } catch (FileNotFoundException e) {
                continue;
            }
            if (messages == null) {
                continue;
            }

            for (String i : messages){
                if (showTime) {
                    text += i + "\n";
                } else {
                    text += i + "\n";
                }
            }

            if (textArea.getText() != text)
                textArea.setText(text);
            textArea.setScrollTop(Double.MAX_VALUE);
            text = "";

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

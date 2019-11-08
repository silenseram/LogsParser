package Model.realtime;

import Model.MCLogs;
import Model.messages.PrivateMessage;
import javafx.scene.control.TextArea;

import java.io.File;
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
        this.file = new File(logs.getNowFilePath());
    }

    @Override
    public void run() {
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

            List<String> messages = logs.getRequestedLines();
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
            //System.out.println(text);
            text = "";

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

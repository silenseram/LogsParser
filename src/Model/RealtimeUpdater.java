package Model;

import Model.messages.PrivateMessage;
import javafx.scene.control.TextArea;

import java.util.List;

public class RealtimeUpdater implements Runnable{

    private TextArea textArea;
    private MCLogs logs;
    public final static String threadName = "RealtimeHandler";
    private String mainThreadName;
    private boolean showTime;

    public RealtimeUpdater(MCLogs logs, TextArea textArea, String mainThreadName, boolean showTime) {
        this.showTime = showTime;
        this.logs = logs;
        this.textArea = textArea;
        this.mainThreadName = mainThreadName;
    }

    @Override
    public void run() {
        String text = "";
        while (true){

            boolean isMainThreadExist = false;
            for (Thread t : Thread.getAllStackTraces().keySet()) {          //if main thread is end then over this thread
                if (t.getName().equals(mainThreadName)) isMainThreadExist = true;
            }
            if (!isMainThreadExist)
                return;

            List<String> messages = logs.getRequestedLines();

            for (String i : messages){
                if (showTime) {
                    text += i + "\n";
                } else {
                    text += i + "\n";
                }
            }

            textArea.setText(text);
            textArea.setScrollTop(Double.MAX_VALUE);
            text = "";

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

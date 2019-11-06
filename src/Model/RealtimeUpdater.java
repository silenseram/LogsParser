package Model;

import Model.messages.PrivateMessage;
import javafx.scene.control.TextArea;

import java.util.List;

public class RealtimeUpdater implements Runnable{

    private TextArea textArea;
    private MCLogs logs;
    public final static String threadName = "RealtimeHandler";
    private String mainThreadName;

    public RealtimeUpdater(MCLogs logs, TextArea textArea, String mainThreadName) {
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

            List<PrivateMessage> messages = logs.getMessages();

            for (PrivateMessage i : messages){
                text += i.getSender() + " -> " + i.getReciever() + ": " + i.getText() + "\n";
            }

            textArea.setText(text);
            textArea.setScrollTop(Double.MAX_VALUE);
            text = "";

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

package classes;

import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.util.List;

public class RealtimeUpdater implements Runnable{

    private String filePath;
    private TextArea textArea;
    private MCLogs logs;
    public final static String threadName = "RealtimeHandler";

    public RealtimeUpdater(MCLogs logs, TextArea textArea) {
        this.logs = logs;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        textArea.setText("12");
        String text = "";
        while (true){
            List<PrivateMessage> messages = logs.getMessages();

            for (PrivateMessage i : messages){
                text += i.getSender() + " -> " + i.getReciever() + ": " + i.getText() + "\n";
            }

            textArea.setText(text);
            text = "";

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
